package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class password_005finput_jsp extends org.apache.jasper.runtime.HttpJspBase
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

  String result = request.getParameter("result");


      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("  <head>\r\n");
      out.write("\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("\r\n");
      out.write("    <title>パスワード入力</title>\r\n");
      out.write("\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/info.css\">\r\n");
      out.write("\r\n");
      out.write("  </head>\r\n");
      out.write("\r\n");
      out.write("  <body>\r\n");
      out.write("\r\n");
      out.write("    <h1 class=\"title\">パスワード入力画面</h1>\r\n");
      out.write("    ");

      if (result != null) {
    
      out.write("\r\n");
      out.write("        <h2 id=\"error\">＊パスワードが違います＊</h2>\r\n");
      out.write("    ");

      }
    
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <table>\r\n");
      out.write("      <form action=\"./password_check.jsp\" method=\"post\">\r\n");
      out.write("\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td class=\"title\">\r\n");
      out.write("          <p>パスワード</p>\r\n");
      out.write("        </td>\r\n");
      out.write("        <td>\r\n");
      out.write("          <br>\r\n");
      out.write("            <p><input type=\"text\" name=\"password\" size=\"25\" pattern=\"^[0-9a-z]+$\"> </p>\r\n");
      out.write("        </td>\r\n");
      out.write("      </tr>\r\n");
      out.write("\r\n");
      out.write("      <tr class=\"no-line\">\r\n");
      out.write("        <td class=\"no-line\" id=\"button\" colspan=\"2\">\r\n");
      out.write("            <p>\r\n");
      out.write("              <input type=\"submit\" id=\"submit\" value=\"閲覧\">\r\n");
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
