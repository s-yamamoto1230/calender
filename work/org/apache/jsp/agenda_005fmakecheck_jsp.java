package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class agenda_005fmakecheck_jsp extends org.apache.jasper.runtime.HttpJspBase
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


  String idStr = request.getParameter("id");
  String titleStr = request.getParameter("title");
  String openStr = request.getParameter("open");
  String passStr = request.getParameter("password");
  String permissionStr = request.getParameter("permission");

//   String message = passStr;
//   int num = message.length();
//   String password="";
//   for (int i=1; i<=num; i++) {
//       password=password+"*";
//   }


      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("  <head>\r\n");
      out.write("\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("\r\n");
      out.write("    <title>新規登録(確認)</title>\r\n");
      out.write("\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/info.css\">\r\n");
      out.write("\r\n");
      out.write("  </head>\r\n");
      out.write("\r\n");
      out.write("  <body>\r\n");
      out.write("\r\n");
      out.write("    <h1>カレンダー新規作成（確認）</h1>\r\n");
      out.write("    <h2>以下の内容で登録しますか？</h2>\r\n");
      out.write("\r\n");
      out.write("    <table>\r\n");
      out.write("    <form action=\"./agenda_makecomplete.jsp\" method=\"post\">\r\n");
      out.write("\r\n");
      out.write("      <input type=\"hidden\" name=\"id\" value=\"");
      out.print( idStr );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"title\" value=\"");
      out.print( titleStr );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"open\" value=\"");
      out.print( openStr );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"pass\" value=\"");
      out.print( passStr );
      out.write("\">\r\n");
      out.write("      <input type=\"hidden\" name=\"permission\" value=\"");
      out.print( permissionStr );
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td>\r\n");
      out.write("          <p>ID</p>\r\n");
      out.write("        </td>\r\n");
      out.write("        <td class=\"check\">\r\n");
      out.write("          ");
      out.print( idStr );
      out.write("\r\n");
      out.write("        </td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td>\r\n");
      out.write("          <p>タイトル</p>\r\n");
      out.write("        </td>\r\n");
      out.write("        <td class=\"check\">\r\n");
      out.write("          ");
      out.print( titleStr );
      out.write("\r\n");
      out.write("        </td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td>\r\n");
      out.write("          <p>公開設定</p>\r\n");
      out.write("        </td>\r\n");
      out.write("        <td class=\"check\">\r\n");
      out.write("          ");
 if(openStr.equals("1")) { 
      out.write("\r\n");
      out.write("          全員に公開\r\n");
      out.write("          ");
}else{
      out.write("\r\n");
      out.write("          特定の人にのみ公開\r\n");
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
 if(openStr.equals("1")) { 
      out.write("\r\n");
      out.write("          なし\r\n");
      out.write("          ");
}else{
      out.write("\r\n");
      out.write("          ");
      out.print( "入力されたパスワード" );
      out.write("\r\n");
      out.write("          ");
 } 
      out.write("\r\n");
      out.write("        </td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td>\r\n");
      out.write("          <p>書き込み設定</p>\r\n");
      out.write("        </td>\r\n");
      out.write("        <td class=\"check\">\r\n");
      out.write("          ");
 if(permissionStr.equals("1")) { 
      out.write("\r\n");
      out.write("          許可\r\n");
      out.write("          ");
}else{
      out.write("\r\n");
      out.write("          禁止\r\n");
      out.write("          ");
 } 
      out.write("\r\n");
      out.write("        </td>\r\n");
      out.write("      </tr>\r\n");
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
