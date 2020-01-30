package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class radio_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<meta charset=\"utf-8\">\r\n");
      out.write("\r\n");
      out.write("  <body>\r\n");
      out.write("  ã  <th>Q1ï¼å¥é¢ã®çµé¨ã¯ããã¾ãã</th>\r\n");
      out.write("      <label><input type=\"radio\" id=\"hospital0\" name=\"hospital\" onClick=\"hospitalflg0(this.checked);\"/> ã¯ã</label>\r\n");
      out.write("      <label><input type=\"radio\" id=\"hospital1\" name=\"hospital\" onClick=\"hospitalflg1(this.checked);\"/> ããã</label>\r\n");
      out.write("      <br>\r\n");
      out.write("      <th>Q2: Q1ã§ãã¯ããã¨ç­ããæ¹ã«è³ªåãã¾ããã©ã®ãããªçæ°ã§å¥é¢ããã¾ãããï¼</th>\r\n");
      out.write("      <input type=\"text\" id=\"hospitalization\" value=\"\" disabled=\"disabled\">\r\n");
      out.write("  </body>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("function hospitalflg0(ischecked){\r\n");
      out.write("    if(ischecked == true){\r\n");
      out.write("      document.getElementById(\"hospitalization\").disabled = false;\r\n");
      out.write("    } else {\r\n");
      out.write("      document.getElementById(\"hospitalization\").disabled = true;\r\n");
      out.write("    }\r\n");
      out.write("  }\r\n");
      out.write("\r\n");
      out.write("  function hospitalflg1(ischecked){\r\n");
      out.write("    if(ischecked == true){\r\n");
      out.write("      document.getElementById(\"hospitalization\").disabled = true;\r\n");
      out.write("    } else {\r\n");
      out.write("      document.getElementById(\"hospitalization\").disabled = false;\r\n");
      out.write("    }\r\n");
      out.write("  }\r\n");
      out.write("</script>\r\n");
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
