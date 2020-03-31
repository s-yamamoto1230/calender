<%-- ログインチェックバックエンド処理 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
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
	String pasStr = request.getParameter("pass");

	//データベースに接続するために使用する変数宣言
	Connection con = null;
	Statement stmt = null;
	StringBuffer SQL = null;
	ResultSet rs = null;

	//ローカルのMySQLに接続する設定
  String USER ="root";
	String PASSWORD = "";
	String URL ="";
	if (USER.equals("root")) {
		URL ="jdbc:mysql://localhost/agenda";
	}
  //サーバーのMySQLに接続する設定
	else{
		USER ="nhs90345";
		PASSWORD = "b19931230";
	 	URL ="jdbc:mysql://192.168.121.16/agenda";
	}

	String DRIVER = "com.mysql.jdbc.Driver";

	//確認メッセージ
	StringBuffer ERMSG = null;

	//確認メッセージ
	String COMPMSG = null;
	String COMPPRO = null;
	boolean flg = true;

if(idStr != "" && pasStr != ""){
	try{
		// JDBCドライバのロード
		Class.forName(DRIVER).newInstance();

		// Connectionオブジェクトの作成
		con = DriverManager.getConnection(URL,USER,PASSWORD);

		//Statementオブジェクトの作成
		stmt = con.createStatement();

		//SQLステートメントの作成（選択クエリ）
		SQL = new StringBuffer();
		//SQL文の構築（選択クエリ）
		SQL.append("select kaiin_id,kaiin_name from kaiin_tbl where kaiin_id = '" + idStr + "'and kaiin_pass = '" + pasStr +"'");

		//SQL文の実行（選択クエリ）
		rs = stmt.executeQuery(SQL.toString());

		//入力したデータがデータベースに存在するか調べる
		if(rs.next()==true){  //存在する
						//セッションにバインド
            session.setAttribute("login_id",rs.getString("kaiin_id"));
            session.setAttribute("login_name",rs.getString("kaiin_name"));
						//メインページへ遷移
						response.sendRedirect("main.jsp");
		}else{  //ログイン失敗
			COMPMSG = "顧客IDまたはパスワードが誤っています";
		}
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
}else{
		COMPMSG = "未入力の項目があります。";
	}
%>

<!DOCTYPE html>
<html>
  <meta charset="utf-8">

  <head>
    <title>ログイン認証</title>
  </head>

  <link rel="stylesheet" type="text/css" href="./css/main.css">

  <body id="logincheck">

    <%
    	if(ERMSG!=null){
    %>
    	予期せぬエラーが発生しました<br>
    	  <%= ERMSG %>
    <%
    	}else{
    %>
    	<%= COMPMSG %><br>
    <%
        }
    %>
  認証NG<br>
    <p><a href="./index.jsp">ログインに戻る</a></p>


  </body>
</html>
