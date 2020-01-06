package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;

public final class openschedule_005fcheck_jsp extends org.apache.jasper.runtime.HttpJspBase
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

  //文字コードの指定
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");

  //入力データ受信
  String yotei_idStr  = request.getParameter("yotei_id");
  String dayStr  = request.getParameter("day");
  String s_hourStr  = request.getParameter("s_hour");
  String s_mineStr  = request.getParameter("s_mine");
  String numStr  = request.getParameter("num");

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
    SQL.append("select * from openyotei_tbl where yotei_id = '");
    SQL.append(yotei_idStr);
    SQL.append("' and day ='");
    SQL.append(dayStr);
    SQL.append("' and s_hour ='");
    SQL.append(s_hourStr);
    SQL.append("' and s_mine ='");
    SQL.append(s_mineStr);
    SQL.append("'");
//      System.out.println(SQL.toString());

    //SQL文の実行（選択クエリ）
    rs = stmt.executeQuery(SQL.toString());

    //入力したデータがデータベースに存在するか調べる
    if(rs.next()){  //存在する
      //ヒットフラグON
      hit_flag = 1;

        //検索データをHashMapへ格納する
        map = new HashMap<String,String>();
      map.put("yotei_id",rs.getString("yotei_id"));
      map.put("day",rs.getString("day"));
      map.put("s_hour",rs.getString("s_hour"));
      map.put("s_mine",rs.getString("s_mine"));
      map.put("f_hour",rs.getString("f_hour"));
      map.put("f_mine",rs.getString("f_mine"));
      map.put("place",rs.getString("place"));
      map.put("details",rs.getString("details"));
      map.put("importance",rs.getString("importance"));

      //1件分のデータ(HashMap)をArrayListへ追加
      list.add(map);
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

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("  <head>\r\n");
      out.write("\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("\r\n");
      out.write("    <title>予定確認</title>\r\n");
      out.write("\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/info.css\">\r\n");
      out.write("\r\n");
      out.write("  </head>\r\n");
      out.write("\r\n");
      out.write("  <body>\r\n");
      out.write("\r\n");
      out.write("    <form action=\"./schedule_update.jsp\" method=\"post\">\r\n");
      out.write("\r\n");
      out.write("      <input type=\"hidden\" name=\"yotei_id\" value=\"");
      out.print( list.get(0).get("yotei_id") );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"day\" value=\"");
      out.print( list.get(0).get("day") );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"s_hour\" value=\"");
      out.print( list.get(0).get("s_hour") );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"s_mine\" value=\"");
      out.print( list.get(0).get("s_mine") );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"f_hour\" value=\"");
      out.print( list.get(0).get("f_hour") );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"f_mine\" value=\"");
      out.print( list.get(0).get("f_mine") );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"place\" value=\"");
      out.print( list.get(0).get("place") );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"details\" value=\"");
      out.print( list.get(0).get("details") );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"importance\" value=\"");
      out.print( list.get(0).get("importance") );
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("<h1>\r\n");
      out.print( numStr );
      out.write('日');
      out.write('の');
      out.print( list.get(0).get("s_hour") );
      out.write('時');
      out.print( list.get(0).get("s_mine") );
      out.write("分からの予定詳細\r\n");
      out.write("</h1>\r\n");
      out.write("<table>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td>\r\n");
      out.write("      <p>時間</p>\r\n");
      out.write("    </td>\r\n");
      out.write("    <td class=\"check\">\r\n");
      out.write("      ");
      out.print( list.get(0).get("s_hour") );
      out.write('時');
      out.print( list.get(0).get("s_mine") );
      out.write('分');
      out.write('～');
      out.print( list.get(0).get("f_hour") );
      out.write('時');
      out.print( list.get(0).get("f_mine") );
      out.write("分\r\n");
      out.write("    </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td>\r\n");
      out.write("      <p>場所</p>\r\n");
      out.write("    </td>\r\n");
      out.write("    <td class=\"check\">\r\n");
      out.write("      ");
      out.print( list.get(0).get("place") );
      out.write("\r\n");
      out.write("    </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td>\r\n");
      out.write("      <p>詳細</p>\r\n");
      out.write("    </td>\r\n");
      out.write("    <td class=\"check\">\r\n");
      out.write("      ");
      out.print( list.get(0).get("details") );
      out.write("\r\n");
      out.write("    </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td>\r\n");
      out.write("      <p>重要</p>\r\n");
      out.write("    </td>\r\n");
      out.write("    <td class=\"check\">\r\n");
      out.write("      ");
 if(list.get(0).get("importance").equals("1")) { 
      out.write("\r\n");
      out.write("      めちゃくちゃ重要です。\r\n");
      out.write("      ");
}else{
      out.write("\r\n");
      out.write("      そこまで重要ではありません。\r\n");
      out.write("      ");
 } 
      out.write("\r\n");
      out.write("    </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr class=\"no-line\">\r\n");
      out.write("    <td class=\"no-line\" id=\"button\" colspan=\"2\">\r\n");
      out.write("        <p>\r\n");
      out.write("          <input type=\"submit\" id=\"submit\" value=\"編集\">\r\n");
      out.write("        </p>\r\n");
      out.write("    </td>\r\n");
      out.write("  </form>\r\n");
      out.write("  </tr>\r\n");
      out.write("\r\n");
      out.write("    <tr class=\"no-line\">\r\n");
      out.write("      <td class=\"no-line\" colspan=\"2\">\r\n");
      out.write("        <p><a href=\"./logincheck.jsp\">メイン画面に戻る</a></p>\r\n");
      out.write("      </td>\r\n");
      out.write("\r\n");
      out.write("    </tr>\r\n");
      out.write("\r\n");
      out.write("</table>\r\n");
      out.write("<br>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
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
