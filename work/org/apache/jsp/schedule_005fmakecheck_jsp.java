package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class schedule_005fmakecheck_jsp extends org.apache.jasper.runtime.HttpJspBase
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
 request.setCharacterEncoding("UTF-8"); 
      out.write('\r');
      out.write('\n');


  String kaiin_idStr = request.getParameter("kaiin_id");
  String dayStr = request.getParameter("day");
  String s_hourStr = request.getParameter("s_hour");
  String s_minStr = request.getParameter("s_min");
  String f_hourStr = request.getParameter("f_hour");
  String f_minStr = request.getParameter("f_min");
  String placeStr = request.getParameter("place");
  String detailsStr = request.getParameter("details");
  String importanceStr = request.getParameter("importance");


  if (importanceStr == null) {
    importanceStr ="0";
  }else {
    importanceStr ="1";
  }

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("  <head>\r\n");
      out.write("\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("\r\n");
      out.write("    <title>予定登録(確認)</title>\r\n");
      out.write("\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/info.css\">\r\n");
      out.write("\r\n");
      out.write("  </head>\r\n");
      out.write("\r\n");
      out.write("  <body>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <h1>予定新規追加（確認）</h1>\r\n");
      out.write("    <h2>以下の内容で登録しますか？</h2>\r\n");
      out.write("\r\n");
      out.write("    <table>\r\n");
      out.write("    <form action=\"./schedule_makecomplete.jsp\" method=\"post\">\r\n");
      out.write("\r\n");
      out.write("      <input type=\"hidden\" name=\"kaiin_id\" value=\"");
      out.print( kaiin_idStr );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"day\" value=\"");
      out.print( dayStr );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"s_hour\" value=\"");
      out.print( s_hourStr );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"s_min\" value=\"");
      out.print( s_minStr );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"f_hour\" value=\"");
      out.print( f_hourStr );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"f_min\" value=\"");
      out.print( f_minStr );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"place\" value=\"");
      out.print( placeStr );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"details\" value=\"");
      out.print( detailsStr );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"importance\" value=\"");
      out.print( importanceStr );
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td>\r\n");
      out.write("          <p>時間</p>\r\n");
      out.write("        </td>\r\n");
      out.write("        <td class=\"check\">\r\n");
      out.write("        ");
      out.print( s_hourStr );
      out.write('：');
      out.print( s_minStr );
      out.write('～');
      out.print( f_hourStr );
      out.write('：');
      out.print( f_minStr );
      out.write("　\r\n");
      out.write("        </td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td>\r\n");
      out.write("          <p>場所</p>\r\n");
      out.write("        </td>\r\n");
      out.write("        <td class=\"check\">\r\n");
      out.write("          ");
      out.print( placeStr );
      out.write("\r\n");
      out.write("        </td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td>\r\n");
      out.write("          <p>詳細</p>\r\n");
      out.write("        </td>\r\n");
      out.write("        <td class=\"check\">\r\n");
      out.write("          ");
      out.print( detailsStr );
      out.write("\r\n");
      out.write("        </td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td>\r\n");
      out.write("          <p>重要</p>\r\n");
      out.write("        </td>\r\n");
      out.write("        <td class=\"check\">\r\n");
      out.write("          ");
 if(importanceStr.equals("1")) { 
      out.write("\r\n");
      out.write("          めちゃくちゃ重要です。\r\n");
      out.write("          ");
}else{
      out.write("\r\n");
      out.write("          そこまで重要ではありません。\r\n");
      out.write("          ");
 } 
      out.write("\r\n");
      out.write("        </td>\r\n");
      out.write("      </tr>\r\n");
      out.write("\r\n");
      out.write("\r\n");
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
      out.write("\r\n");
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
