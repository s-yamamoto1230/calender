<%-- パスワード変更バックエンド処理 --%>
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
	String password  = request.getParameter("password");
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
			SQL.append("select * from open_tbl where yotei_pass = '");
			SQL.append(password);
			SQL.append("' and yotei_id = '");
			SQL.append(yotei_ids);
			SQL.append("'");
			System.out.println(SQL.toString());

			//SQL文の実行（選択クエリ）
			rs = stmt.executeQuery(SQL.toString());

			//入力したデータがデータベースに存在するか調べる
			if(rs.next()){  //パスワード一致
				//メインページへ遷移
				response.sendRedirect("myag_main.jsp");
			}else{	//パスワード不一致
				response.sendRedirect("password_input.jsp?result=none");
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
