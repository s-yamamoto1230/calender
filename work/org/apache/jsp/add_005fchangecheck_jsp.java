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

public final class add_005fchangecheck_jsp extends org.apache.jasper.runtime.HttpJspBase
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
 request.setCharacterEncoding("UTF-8"); 
      out.write('\r');
      out.write('\n');

  //入力データ受信
  String session_id = (String)session.getAttribute("login_id");
  String mailStr = request.getParameter("mail");
  String idStr = request.getParameter("id");
  String passStr = request.getParameter("password");
  String usernameStr = request.getParameter("name");
  String yearStr = request.getParameter("year");
  String monthStr = request.getParameter("month");
  String dayStr = request.getParameter("day");
  String bday = yearStr+monthStr+dayStr;

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
  /*  String USER = "nhs90345";
  String PASSWORD = "b19931230";
  String URL ="jdbc:mysql://192.168.121.16/agenda";*/

  String DRIVER = "com.mysql.jdbc.Driver";

  //確認メッセージ
  StringBuffer ERMSG = null;

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
    SQL.append(session_id);
    SQL.append("'");
  //      System.out.println(SQL.toString());

    //SQL文の実行（選択クエリ）
    rs = stmt.executeQuery(SQL.toString());

        //検索データをHashMapへ格納する
        while(rs.next()){
      //DBのデータをHashMapへ格納する
          map = new HashMap<String,String>();
          map.put("kaiin_id",rs.getString("kaiin_id"));
          map.put("kaiin_name",rs.getString("kaiin_name"));
          map.put("kaiin_add",rs.getString("kaiin_add"));
          map.put("kaiin_pass",rs.getString("kaiin_pass"));
          map.put("kaiin_bday",rs.getString("kaiin_bday"));

          //1件分のデータ(HashMap)をArrayListへ追加
          list.add(map);
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


      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("  <head>\r\n");
      out.write("\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("\r\n");
      out.write("    <title>登録内容変更(確認)</title>\r\n");
      out.write("\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/info.css\">\r\n");
      out.write("\r\n");
      out.write("  </head>\r\n");
      out.write("\r\n");
      out.write("  <body>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <h1>登録内容変更（確認）</h1>\r\n");
      out.write("    <h2>以下の内容で変更しますか？</h2>\r\n");
      out.write("\r\n");
      out.write("    <table>\r\n");
      out.write("    <form action=\"./add_changecomplete.jsp\" method=\"post\">\r\n");
      out.write("\r\n");
      out.write("      <input type=\"hidden\" name=\"mail\" value=\"");
      out.print( mailStr );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"id\" value=\"");
      out.print( idStr );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"pass\" value=\"");
      out.print( passStr );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"username\" value=\"");
      out.print( usernameStr );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"bday\" value=\"");
      out.print( bday );
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td>\r\n");
      out.write("          <p>メールアドレス</p>\r\n");
      out.write("        </td>\r\n");
      out.write("        <td class=\"check\">\r\n");
      out.write("          ");

            if (list.get(0).get("kaiin_add").equals(mailStr)) {
          
      out.write("\r\n");
      out.write("              変更なし\r\n");
      out.write("          ");

            }else{
          
      out.write("\r\n");
      out.write("            ");
      out.print( mailStr );
      out.write("\r\n");
      out.write("          ");

            }
          
      out.write("\r\n");
      out.write("        </td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td>\r\n");
      out.write("          <p>パスワード</p>\r\n");
      out.write("        </td>\r\n");
      out.write("        <td class=\"check\">\r\n");
      out.write("          ");

            if (list.get(0).get("kaiin_pass").equals(passStr)) {
          
      out.write("\r\n");
      out.write("              変更なし\r\n");
      out.write("          ");

            }else{
          
      out.write("\r\n");
      out.write("            ");
      out.print( "入力されたパスワード" );
      out.write("\r\n");
      out.write("          ");

            }
          
      out.write("\r\n");
      out.write("        </td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td>\r\n");
      out.write("          <p>ユーザー名</p>\r\n");
      out.write("        </td>\r\n");
      out.write("        <td class=\"check\">\r\n");
      out.write("          ");

            if (list.get(0).get("kaiin_name").equals(usernameStr)) {
          
      out.write("\r\n");
      out.write("              変更なし\r\n");
      out.write("          ");

            }else{
          
      out.write("\r\n");
      out.write("            ");
      out.print( usernameStr );
      out.write("\r\n");
      out.write("          ");

            }
          
      out.write("\r\n");
      out.write("        </td>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td>\r\n");
      out.write("          <p>生年月日</p>\r\n");
      out.write("        </td>\r\n");
      out.write("        <td class=\"check\">\r\n");
      out.write("          ");
      out.print( yearStr+"年"+monthStr+"月"+dayStr+"日" );
      out.write("\r\n");
      out.write("        </td>\r\n");
      out.write("\r\n");
      out.write("      <tr class=\"no-line\">\r\n");
      out.write("        <td  id=\"button\" class=\"no-line\" colspan=\"2\">\r\n");
      out.write("            <p>\r\n");
      out.write("              <input type=\"submit\" value=\"登録\">\r\n");
      out.write("              <button id=\"correction\" type=\"button\" href=\"javascript:void(0)\" onclick=\"javascript:history.back()\">修正</button>\r\n");
      out.write("            </p>\r\n");
      out.write("        </td>\r\n");
      out.write("      </form>\r\n");
      out.write("        <td class=\"no-line\">\r\n");
      out.write("        </td>\r\n");
      out.write("      </tr>\r\n");
      out.write("\r\n");
      out.write("        <tr class=\"no-line\">\r\n");
      out.write("          <td class=\"no-line\"></td>\r\n");
      out.write("          <td class=\"no-line\"></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("      </table>\r\n");
      out.write("      <p id=\"back\"><a href=\"./main.jsp\">メイン画面に戻る</a></p>\r\n");
      out.write("\r\n");
      out.write("      <ul class=\"circles\">\r\n");
      out.write("        <li></li>\r\n");
      out.write("        <li></li>\r\n");
      out.write("        <li></li>\r\n");
      out.write("        <li></li>\r\n");
      out.write("        <li></li>\r\n");
      out.write("        <li></li>\r\n");
      out.write("        <li></li>\r\n");
      out.write("        <li></li>\r\n");
      out.write("        <li></li>\r\n");
      out.write("        <li></li>\r\n");
      out.write("        <li class=\"right\"></li>\r\n");
      out.write("        <li class=\"right\"></li>\r\n");
      out.write("        <li class=\"right\"></li>\r\n");
      out.write("        <li class=\"right\"></li>\r\n");
      out.write("        <li class=\"right\"></li>\r\n");
      out.write("        <li class=\"right\"></li>\r\n");
      out.write("        <li class=\"right\"></li>\r\n");
      out.write("        <li class=\"right\"></li>\r\n");
      out.write("        <li class=\"right\"></li>\r\n");
      out.write("        <li class=\"right\"></li>\r\n");
      out.write("      </ul>\r\n");
      out.write("  </body>\r\n");
      out.write("\r\n");
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
