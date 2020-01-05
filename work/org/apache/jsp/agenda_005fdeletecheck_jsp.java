package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;

public final class agenda_005fdeletecheck_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    String yotei_idStr[] =request.getParameterValues("yotei_id");


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
    for(int i = 0; i < yotei_idStr.length; i++){
      SQL = new StringBuffer();
      SQL.append("select * from open_tbl where yotei_id = '");
      SQL.append(yotei_idStr[i]);
      SQL.append("'");
//      System.out.println(SQL.toString());
    //SQL文の実行（選択クエリ）
    rs = stmt.executeQuery(SQL.toString());

    //入力したデータがデータベースに存在するか調べる
    if(rs.next()){  //存在する
      //ヒットフラグON
      hit_flag = 1;

        //検索データをHashMapへ格納する
        while(rs.next()){
      //DBのデータをHashMapへ格納する
          map = new HashMap<String,String>();
          map.put("yotei_id",rs.getString("yotei_id"));
          map.put("yotei_name",rs.getString("yotei_name"));
          map.put("open_set",rs.getString("open_set"));
          map.put("yotei_pass",rs.getString("yotei_pass"));
          map.put("yotei_writing",rs.getString("yotei_writing"));
          map.put("kaiin_id",rs.getString("kaiin_id"));

          //1件分のデータ(HashMap)をArrayListへ追加
          list.add(map);
        }

    }else{  //存在しない
      //ヒットフラグOFF
      hit_flag = 0;
    }
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
      out.write("    <title>Agenda削除</title>\r\n");
      out.write("\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/info.css\">\r\n");
      out.write("\r\n");
      out.write("  </head>\r\n");
      out.write("\r\n");
      out.write("  <body>\r\n");
      out.write("\r\n");
      out.write("    <h1>\r\n");
      out.write("      以下のAgendaを削除しますか？\r\n");
      out.write("    </h1>\r\n");
      out.write("    <table id=\"list\">\r\n");
      out.write("      <tr class=\"no-line\">\r\n");
      out.write("        <th class=\"no-line\" style=\"padding: 20px;\">AgendaID</td>\r\n");
      out.write("        <th class=\"no-line\" style=\"padding: 20px;\">Agenda名</td>\r\n");
      out.write("        <th class=\"no-line\" style=\"padding: 20px;\">公開設定</td>\r\n");
      out.write("        <th class=\"no-line\" style=\"padding: 20px;\">パスワード</td>\r\n");
      out.write("        <th class=\"no-line\" style=\"padding: 20px;\">他人の書き込み設定</td>\r\n");
      out.write("      </tr>\r\n");
      out.write("    ");

      for(int i = 0; i < list.size(); i++){
    
      out.write("\r\n");
      out.write("    ");
      out.print( yotei_idStr[i] );
      out.write("\r\n");
      out.write("          <tr class=\"no-line\">\r\n");
      out.write("              <td class=\"no-line\" align=\"left\" style=\"font-size:25px; font-weight:bold;;\"><a href=\"#\">・");
      out.print( list.get(i).get("yotei_id") );
      out.write("</a></td>\r\n");
      out.write("            <td class=\"no-line\">");
      out.print( list.get(i).get("yotei_name") );
      out.write("</td>\r\n");
      out.write("            <td class=\"no-line\">\r\n");
      out.write("              ");
if (list.get(i).get("open_set").equals("1")) { 
      out.write("\r\n");
      out.write("                全員に公開\r\n");
      out.write("                ");
}else{
      out.write("\r\n");
      out.write("                特定の人にのみ公開\r\n");
      out.write("              ");
}
      out.write("\r\n");
      out.write("            </td>\r\n");
      out.write("            <td class=\"no-line\">");
      out.print( list.get(i).get("yotei_pass") );
      out.write("</td>\r\n");
      out.write("            <td class=\"no-line\">\r\n");
      out.write("              ");
 if(list.get(i).get("yotei_writing").equals("1")) { 
      out.write("\r\n");
      out.write("              許可\r\n");
      out.write("              ");
}else{
      out.write("\r\n");
      out.write("              禁止\r\n");
      out.write("              ");
 } 
      out.write("\r\n");
      out.write("            </td>\r\n");
      out.write("          </tr>\r\n");
      out.write("    ");
}
      out.write("\r\n");
      out.write("  </table>\r\n");
      out.write("    <p id=\"back\"><a href=\"./logincheck.jsp\">メイン画面に戻る</a></p>\r\n");
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
