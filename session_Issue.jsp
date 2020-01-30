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
	String yotei_id  = request.getParameter("yotei_id");
	String yotei_name = request.getParameter("yotei_name");
	String open_set = request.getParameter("open_set");
	String favorite = request.getParameter("favorite");
	String session_id = (String)session.getAttribute("login_id");
	String yotei_ids = (String)session.getAttribute("yotei_id");

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

	//追加件数
  int ins_count=0;

	if (favorite != null) {
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
			SQL.append("select * from favorite_tbl where kaiin_id = '");
			SQL.append(session_id);
			SQL.append("' and yotei_id = '");
			SQL.append(yotei_ids);
			SQL.append("'");
				System.out.println(SQL.toString());

			//SQL文の実行（選択クエリ）
			rs = stmt.executeQuery(SQL.toString());

			//入力したデータがデータベースに存在するか調べる
			if(rs.next()){  //存在する
				//メインページへ遷移
				response.sendRedirect("myag_main.jsp?hit_flag=1");
			}else{  //存在しない(追加OK)
				//SQLステートメントの作成（選択クエリ）
				SQL=new StringBuffer();

				//SQL文の構築
				SQL.append("insert into favorite_tbl(kaiin_id,yotei_id)");
				SQL.append(" values('");
				SQL.append(session_id);
				SQL.append("','");
				SQL.append(yotei_ids);
				SQL.append("')");
				System.out.println(SQL.toString());
			}

			//SQL文の実行(DB追加)
			ins_count=stmt.executeUpdate(SQL.toString());
			//メインページへ遷移
			response.sendRedirect("myag_main.jsp?hit_flag=0");

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
	}

if(yotei_id != "" && yotei_name != ""){
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
		SQL.append("select yotei_id,yotei_name from open_tbl where yotei_id = '" + yotei_id + "'");

		//SQL文の実行（選択クエリ）
		rs = stmt.executeQuery(SQL.toString());

		//入力したデータがデータベースに存在するか調べる
		if(rs.next()==true){  //存在する
						//セッションにバインド
            session.setAttribute("yotei_id",rs.getString("yotei_id"));
            session.setAttribute("yotei_name",rs.getString("yotei_name"));
						if (open_set != null){
							if (open_set.equals("2")) {
								response.sendRedirect("password_input.jsp");
							}
						}
						//メインページへ遷移
						response.sendRedirect("myag_main.jsp");
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
    <title>セッション発行</title>
  </head>

  <link rel="stylesheet" type="text/css" href="./css/main.css">

  <body>

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
  発行NG<br>
    <p><a href="./index.jsp">ログインに戻る</a></p>


  </body>
</html>
