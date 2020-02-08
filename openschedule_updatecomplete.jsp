<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
  //文字コードの指定
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");

  //入力データ受信
  String yotei_ids = (String)session.getAttribute("yotei_id");
  String dayStr = request.getParameter("day");
  String before_shour = request.getParameter("before_shour");
  String before_smin = request.getParameter("before_smin");
  String s_hourStr = request.getParameter("s_hour");
  String s_minStr = request.getParameter("s_min");
  String f_hourStr = request.getParameter("f_hour");
  String f_minStr = request.getParameter("f_min");
  String placeStr = request.getParameter("place");
  String detailsStr = request.getParameter("details");
  String importanceStr = request.getParameter("importance");
  String year = request.getParameter("year");
  String month = request.getParameter("month");



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
    SQL.append("update openyotei_tbl set day ='");
		SQL.append(dayStr);
		SQL.append("',s_hour ='");
		SQL.append(s_hourStr);
		SQL.append("',s_mine = '");
		SQL.append(s_minStr);
		SQL.append("',f_hour = '");
		SQL.append(f_hourStr);
		SQL.append("',f_mine = '");
		SQL.append(f_minStr);
		SQL.append("',place = '");
		SQL.append(placeStr);
		SQL.append("',details = '");
		SQL.append(detailsStr);
		SQL.append("',importance = '");
		SQL.append(importanceStr);
		SQL.append("' where yotei_id = '");
		SQL.append(yotei_ids);
    SQL.append("' and day = '");
		SQL.append(dayStr);
    SQL.append("' and s_hour = '");
		SQL.append(before_shour);
    SQL.append("' and s_mine = '");
    SQL.append(before_smin);
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
  <meta charset="utf-8">
  <title>更新完了</title>
  <link rel="stylesheet" type="text/css" href="./css/info.css">
</head>
<body>

  <%
  	if(upd_count == 0){  //更新処理失敗
  %>
  	<h1>更新NG</h1><br>
  	  <%= "更新処理が失敗しました" %>
  <%
  	}else{  //更新OK
  %>
  	<h1>更新OK</h1><br>
  	  <%= upd_count + "件　更新が完了しました" %>
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



  <p><a href="./myag_main.jsp">メイン画面に戻る</a></p>
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
