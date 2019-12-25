<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%
  //文字コードの指定
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");
  
  //入力データ受信
  String idStr  = request.getParameter("id");
  String passStr = request.getParameter("pass");



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
//  String USER = "nhs90345";
//  String PASSWORD = "b19931230";
//  String URL ="jdbc:mysql://192.168.121.16/nhsagenda";

  String DRIVER = "com.mysql.jdbc.Driver";

  //確認メッセージ
  StringBuffer ERMSG = null;
  
  //ヒットフラグ
  int hit_flag = 0;
  
  //HashMap（1件分のデータを格納する連想配列）
  HashMap<String,String> map = null;
   
  //ArrayList（すべての件数を格納する配列）
  ArrayList<HashMap> list = null;
  list = new ArrayList<HashMap>();
   
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
    SQL.append("select * from kaiin_tbl where kaiin_id = '");
    SQL.append(idStr);
//    SQL.append("' and cus_pas = '");
//    SQL.append(cus_pasStr);
    SQL.append("'");
      System.out.println(SQL.toString());

    //SQL文の実行（選択クエリ）
    rs = stmt.executeQuery(SQL.toString());

//    String id =rs.getString("kaiin_id");

    //入力したデータがデータベースに存在するか調べる
    if(rs.next()){  //存在する
      //ヒットフラグON
      hit_flag = 1;
    if(passStr.equals(rs.getString("kaiin_pass"))){
      //ヒットフラグON
      hit_flag = hit_flag+1;
    }

    if(hit_flag==2){
      
        //検索データをHashMapへ格納する
        map = new HashMap<String,String>();
      map.put("kaiin_id",rs.getString("kaiin_id"));
      map.put("kaiin_name",rs.getString("kaiin_name"));
      map.put("kaiin_add",rs.getString("kaiin_add"));
      map.put("kaiin_pass",rs.getString("kaiin_pass"));
      map.put("kaiin_bday",rs.getString("kaiin_bday"));

      
      //1件分のデータ(HashMap)をArrayListへ追加
      list.add(map);

      session.setAttribute("login", "login");
      session.setAttribute("id", "kaiin_id");
    }

    }else{  //存在しない
      //ヒットフラグOFF
      hit_flag = 0;
    }
    

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

<!DOCTYPE html>
<html>
  <meta charset="utf-8">

  <head>
    <title>メインページ</title>

<%
  if((String)session.getAttribute("login") == "login"){  //認証OK
%>

<meta http-equiv="refresh" content="0; URL='./main.jsp'" />

  </head>

  <link rel="stylesheet" type="text/css" href="./css/main.css">

  <body>

  <%
  }else{  //認証NG
%>
  認証NG<br>
    <%= "顧客IDまたはパスワードが誤っています" %>
    <p><a href="./index.jsp">ログインに戻る</a></p>
<%
  }
%>
<br><br>
<% if(ERMSG != null){ %>
予期せぬエラーが発生しました<br />
<%= ERMSG %>
<% }%>

  </body>
</html>