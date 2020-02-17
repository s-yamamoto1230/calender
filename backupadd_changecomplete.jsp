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

	//更新件数
	int upd_count = 0;

	try{	// ロードに失敗したときのための例外処理
		// JDBCドライバのロード
		Class.forName(DRIVER).newInstance();

		// Connectionオブジェクトの作成
		con = DriverManager.getConnection(URL,USER,PASSWORD);

		//Statementオブジェクトの作成
		stmt = con.createStatement();

		//SQLステートメントの作成（選択クエリ）
		SQL = new StringBuffer();

    //SQL文の構築（DB更新）
		SQL.append("update kaiin_tbl set kaiin_name = '");
		SQL.append(usernameStr);
		SQL.append("',kaiin_add = '");
		SQL.append(mailStr);
		SQL.append("',kaiin_pass = '");
		SQL.append(passStr);
		SQL.append("',kaiin_bday = '");
		SQL.append(birthday);
		SQL.append("' where kaiin_id = '");
    SQL.append(idStr);
		SQL.append("'");

      System.out.println(SQL.toString());
		upd_count = stmt.executeUpdate(SQL.toString());

	}	//tryブロック終了
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./css/info.css">
<title>『更新完了』</title>
</head>
<body>
<%
	if(upd_count == 0){  //更新処理失敗
%>
	更新NG<br>
	  <%= "更新処理が失敗しました" %>
<%
	}else{  //更新OK
%>
  <p class="mes">
	   更新OK<br>
	  <%= upd_count + "件　更新が完了しました" %>
 </p>
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
<p><a href="./main.jsp">ログインに戻る</a></p>
<ul class="circles">
  <li></li>
  <li></li>
  <li></li>
  <li></li>
  <li></li>
  <li></li>
  <li></li>
  <li></li>
  <li></li>
  <li></li>
  <li class="right"></li>
  <li class="right"></li>
  <li class="right"></li>
  <li class="right"></li>
  <li class="right"></li>
  <li class="right"></li>
  <li class="right"></li>
  <li class="right"></li>
  <li class="right"></li>
  <li class="right"></li>
</ul>
</body>
</html>