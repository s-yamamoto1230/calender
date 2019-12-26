package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class schedule_005fmake_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\r\n");

  //文字コードの指定
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");

  String kaiin_idStr = request.getParameter("kaiin_id");


      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("  <head>\r\n");
      out.write("\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("\r\n");
      out.write("    <title>予定作成</title>\r\n");
      out.write("\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/info.css\">\r\n");
      out.write("\r\n");
      out.write("  </head>\r\n");
      out.write("\r\n");
      out.write("  <body>\r\n");
      out.write("\r\n");
      out.write("    <h1 class=\"title\">予定新規作成</h1>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <table>\r\n");
      out.write("      <form  name=\"form\" action=\"./agenda_makecheck.jsp\" method=\"post\" action=\"#\" onsubmit=\"return formCheck()\">\r\n");
      out.write("\r\n");
      out.write("        <input type=\"hidden\" name=\"kaiin_id\" value=\"");
      out.print( kaiin_idStr );
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td class=\"title\">\r\n");
      out.write("          <p>時間</p>\r\n");
      out.write("          <td colspan=\"2\">\r\n");
      out.write("            開始：\r\n");
      out.write("            <select name=\"hour\">\r\n");
      out.write("              <script>\r\n");
      out.write("                var i;\r\n");
      out.write("                for(i=0; i<24; i+=1){\r\n");
      out.write("                document.write('<option value=\"'+i+'\">'+i+'時</option>');\r\n");
      out.write("                }\r\n");
      out.write("              </script>\r\n");
      out.write("            </select>\r\n");
      out.write("            <select name=\"min\">\r\n");
      out.write("              <script>\r\n");
      out.write("                var i;\r\n");
      out.write("                for(i=0; i<60; i+=10){\r\n");
      out.write("                document.write('<option value=\"'+i+'\">'+i+'分</option>');\r\n");
      out.write("                }\r\n");
      out.write("              </script>\r\n");
      out.write("            </select>\r\n");
      out.write("            &emsp;～&emsp;終了：\r\n");
      out.write("            <select name=\"hour\">\r\n");
      out.write("              <script>\r\n");
      out.write("                var i;\r\n");
      out.write("                for(i=0; i<24; i+=1){\r\n");
      out.write("                document.write('<option value=\"'+i+'\">'+i+'時</option>');\r\n");
      out.write("                }\r\n");
      out.write("              </script>\r\n");
      out.write("            </select>\r\n");
      out.write("            <select name=\"min\">\r\n");
      out.write("              <script>\r\n");
      out.write("                var i;\r\n");
      out.write("                for(i=0; i<60; i+=10){\r\n");
      out.write("                document.write('<option value=\"'+i+'\">'+i+'分</option>');\r\n");
      out.write("                }\r\n");
      out.write("              </script>\r\n");
      out.write("            </select>\r\n");
      out.write("          </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td class=\"title\">\r\n");
      out.write("          <p>場所</p>\r\n");
      out.write("        </td>\r\n");
      out.write("        <td>\r\n");
      out.write("          <input type=\"text\" name=\"title\" size=\"25\">\r\n");
      out.write("        </td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td class=\"title\">\r\n");
      out.write("          <p>詳細</p>\r\n");
      out.write("        </td>\r\n");
      out.write("        <td>\r\n");
      out.write("          <textarea maxlength=\"200\"></textarea>\r\n");
      out.write("        </td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td class=\"title\">\r\n");
      out.write("          <p>重要</p>\r\n");
      out.write("        </td>\r\n");
      out.write("        <td>\r\n");
      out.write("          <label><input type=\"checkbox\">※この予定は重要です</label>\r\n");
      out.write("        </td>\r\n");
      out.write("      </tr>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("      <tr class=\"no-line\">\r\n");
      out.write("        <td class=\"no-line\" id=\"button\" colspan=\"2\">\r\n");
      out.write("            <p>\r\n");
      out.write("              <input type=\"submit\" id=\"submit\" value=\"登録\">\r\n");
      out.write("            </p>\r\n");
      out.write("        </td>\r\n");
      out.write("      </form>\r\n");
      out.write("      </tr>\r\n");
      out.write("\r\n");
      out.write("        <tr class=\"no-line\">\r\n");
      out.write("          <td class=\"no-line\" colspan=\"2\">\r\n");
      out.write("            <p><a href=\"./main.jsp\">メイン画面に戻る</a></p>\r\n");
      out.write("          </td>\r\n");
      out.write("\r\n");
      out.write("        </tr>\r\n");
      out.write("      </table>\r\n");
      out.write("\r\n");
      out.write("  <script type=\"text/javascript\" src=\"./js/info.js\"></script>\r\n");
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
