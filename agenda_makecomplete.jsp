<%-- 共有カレンダー作成バックエンド処理 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
  //文字コードの指定
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");

  //入力データ受信
  String idStr = request.getParameter("id");
  String titleStr = request.getParameter("title");
  String openStr = request.getParameter("open");
  String passStr = request.getParameter("password");
  String permissionStr = request.getParameter("permission");
  String session_id = (String)session.getAttribute("login_id");

  if (passStr == null) {
    passStr = "";
  }


  //データベースに接続するために使用する変数宣言
  Connection con = null;
  Statement stmt = null;
  StringBuffer SQL = null;
  ResultSet rs = null;

  //ローカルのMySQLに接続する設定
  String USER ="root";
  String PASSWORD = "";
  String URL ="jdbc:mysql://localhost/agenda";

  //サーバーのMySQLに接続する設定
/*  String USER = "nhsxxxxx";
  String PASSWORD = "byyyymmdd";
  String URL ="jdbc:mysql://192.168.121.16/nhs90345db";
*/

  String DRIVER = "com.mysql.jdbc.Driver";

  //確認メッセージ
  StringBuffer ERMSG = null;

  //ヒットフラグ
  int hit_flag = 0;

  //追加件数
  int ins_count=0;

  try{  // ロードに失敗したときのための例外処理
    // JDBCドライバのロード
    Class.forName(DRIVER).newInstance();

    // Connectionオブジェクトの作成
    con = DriverManager.getConnection(URL,USER,PASSWORD);

    //Statementオブジェクトの作成
    stmt = con.createStatement();

    //SQLステートメントの作成（選択クエリ）
    SQL = new StringBuffer();

    //SQL文の構築（選択クエリ）
    SQL.append("select yotei_id,yotei_name,open_set,yotei_pass,yotei_writing,open_tbl.kaiin_id from open_tbl where yotei_id = '");
    SQL.append(idStr);
    SQL.append("'");
      System.out.println(SQL.toString());

    //SQL文の実行（選択クエリ）
    rs = stmt.executeQuery(SQL.toString());

    //入力したデータがデータベースに存在するか調べる
    if(rs.next()){  //存在する
      //メインページへ遷移
      response.sendRedirect("main.jsp?create=0&page_no=1");
    }else{  //存在しない(追加OK)
    //SQLステートメントの作成（選択クエリ）
    SQL=new StringBuffer();

    //SQL文の構築
    SQL.append("insert into open_tbl(yotei_id,yotei_name,open_set,yotei_pass,yotei_writing,kaiin_id)");
    SQL.append("values('");
    SQL.append(idStr);
    SQL.append("','");
    SQL.append(titleStr);
    SQL.append("','");
    SQL.append(openStr);
    SQL.append("','");
    SQL.append(passStr);
    SQL.append("','");
    SQL.append(permissionStr);
    SQL.append("','");
    SQL.append(session_id);
    SQL.append("')");
    }

    //System.out.println(SQL.toString)

    //SQL文の実行(DB追加)
    ins_count=stmt.executeUpdate(SQL.toString());
    //メインページへ遷移
    response.sendRedirect("main.jsp?create=1&page_no=1");

  } //tryブロック終了
  catch(ClassNotFoundException e){
    ERMSG = new StringBuffer();
    ERMSG.append(e.getMessage());
  }
  catch(SQLException e){
    ERMSG = new StringBuffer();
    ERMSG.append(e.getMessage());
  }
  catch(Exception e){
    ERMSG = new StringBuffer();
    ERMSG.append(e.getMessage());
  }

  finally{
    //各種オブジェクトクローズ
      try{
        if(rs != null){
          rs.close();
        }
        if(stmt != null){
          stmt.close();
      }
        if(con != null){
          con.close();
      }
      }
    catch(SQLException e){
    ERMSG = new StringBuffer();
    ERMSG.append(e.getMessage());
    }
  }
%>
