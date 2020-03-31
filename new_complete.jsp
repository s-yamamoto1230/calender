<%-- ユーザーID新規登録完了画面 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
  //文字コードの指定
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");

  //入力データ受信
  String idStr = request.getParameter("id");
  String usernameStr = request.getParameter("username");
  String mailStr = request.getParameter("mail");
  String passStr = request.getParameter("pass");
  String birthday = request.getParameter("bday");


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
    SQL.append("select * from kaiin_tbl where kaiin_id = '");
    SQL.append(idStr);
    SQL.append("'");
      System.out.println(SQL.toString());

    //SQL文の実行（選択クエリ）
    rs = stmt.executeQuery(SQL.toString());

    //入力したデータがデータベースに存在するか調べる
    if(rs.next()){  //存在する
      //ヒットフラグON
      hit_flag = 1;
    }else{  //存在しない(追加OK)
      //ヒットフラグOFF
      hit_flag = 0;
    //SQLステートメントの作成（選択クエリ）
    SQL=new StringBuffer();

    //SQL文の構築
    SQL.append("insert into kaiin_tbl(kaiin_id,kaiin_name,kaiin_add,kaiin_pass,kaiin_bday)");
    SQL.append("values('");
    SQL.append(idStr);
    SQL.append("','");
    SQL.append(usernameStr);
    SQL.append("','");
    SQL.append(mailStr);
    SQL.append("','");
    SQL.append(passStr);
    SQL.append("','");
    SQL.append(birthday);
    SQL.append("')");
    }

    //System.out.println(SQL.toString)

    //SQL文の実行(DB追加)
    ins_count=stmt.executeUpdate(SQL.toString());

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
<head>
  <meta charset="utf-8">
  <title>登録完了</title>
  <link rel="stylesheet" type="text/css" href="./css/info.css">
</head>
<body>

<%
  if(hit_flag == 1){  //認証NG
%>
追加NG<br>
<%= "入力された顧客IDは既に存在しています" %>
<%
}else if(ins_count==0){//追加処理失敗
%>
追加NG<br>
<%= "登録が失敗しました" %>

<%
  }else{  //認証OK
%>
    <h1>新規登録完了</h1><br>
    <%= ins_count + "件登録が完了しました" %>
<%
  }
%>
<br><br>
<% if(ERMSG != null){ %>
予期せぬエラーが発生しました<br />
<%= ERMSG %>
<% }else{ %>
※エラーは発生しませんでした<br/>
<% } %>


  <p><a href="./index.jsp">ログインに戻る</a></p>

</body>
</html>
