package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Date;
import java.util.Calendar;
import java.sql.*;
import java.util.*;
import java.util.HashMap;
import java.util.ArrayList;

public final class session_005fIssue_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	//文字コードの指定
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");

	//入力データ受信
	String yotei_id  = request.getParameter("yotei_id");
	String yotei_name = request.getParameter("yotei_name");
	String favorite = request.getParameter("favorite");

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

	if (favorite != null) {
		session.setAttribute("favorite_s",favorite);
		//メインページへ遷移
		response.sendRedirect("myag_main.jsp");
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("  <meta charset=\"utf-8\">\r\n");
      out.write("\r\n");
      out.write("  <head>\r\n");
      out.write("    <title>セッション発行</title>\r\n");
      out.write("  </head>\r\n");
      out.write("\r\n");
      out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/main.css\">\r\n");
      out.write("\r\n");
      out.write("  <body>\r\n");
      out.write("\r\n");
      out.write("    ");

    	if(ERMSG!=null){
    
      out.write("\r\n");
      out.write("    \t予期せぬエラーが発生しました<br>\r\n");
      out.write("    \t  ");
      out.print( ERMSG );
      out.write("\r\n");
      out.write("    ");

    	}else{
    
      out.write("\r\n");
      out.write("    \t");
      out.print( COMPMSG );
      out.write("<br>\r\n");
      out.write("    ");

        }
    
      out.write("\r\n");
      out.write("  発行NG<br>\r\n");
      out.write("    <p><a href=\"./index.jsp\">ログインに戻る</a></p>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("  </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
