package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class new_005fmake_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("  <head>\r\n");
      out.write("\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("\r\n");
      out.write("    <title>新規登録</title>\r\n");
      out.write("\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/info.css\">\r\n");
      out.write("\r\n");
      out.write("  </head>\r\n");
      out.write("\r\n");
      out.write("  <body>\r\n");
      out.write("\r\n");
      out.write("    <h1>新規登録</h1>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <table>\r\n");
      out.write("      <form  name=\"form\" action=\"./new_check.jsp\" method=\"post\" action=\"#\" onsubmit=\"return formCheck()\">\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td class=\"title\">\r\n");
      out.write("          <p>メールアドレス</p>\r\n");
      out.write("        </td>\r\n");
      out.write("        <td>\r\n");
      out.write("          <p><input type=\"email\" name=\"mail\" size=\"25\" required></p>\r\n");
      out.write("        </td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td class=\"title\">\r\n");
      out.write("          <p>ID</p>\r\n");
      out.write("        </td>\r\n");
      out.write("        <td>\r\n");
      out.write("          <p><input type=\"text\" name=\"id\" id=\"id\" pattern=\"^[0-9a-z]+$\" size=\"25\" required></p>\r\n");
      out.write("          <p id=\"notice-input-text-0\" style=\"display: none; color: red;\"></p>\r\n");
      out.write("          <p class=\"alert\">※半角英数字15文字以下</p>\r\n");
      out.write("        </td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <div class=\"form-group\">\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td class=\"title\">\r\n");
      out.write("          <p>パスワード</p>\r\n");
      out.write("        </td>\r\n");
      out.write("        <td>\r\n");
      out.write("          <br><div class=\"form-group\">\r\n");
      out.write("            <p><input type=\"password\" class=\"form-control\" id=\"password\" name=\"password\" size=\"25\" pattern=\"^[0-9a-z]+$\" required /> </p>\r\n");
      out.write("          </div>\r\n");
      out.write("          <p id=\"notice-input-text-1\" style=\"display: none; color: red;\"></p>\r\n");
      out.write("          <p class=\"alert\">※半角英数字6文字以上20字以内</p>\r\n");
      out.write("        </td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td class=\"title\">\r\n");
      out.write("          パスワード（確認）\r\n");
      out.write("        </td>\r\n");
      out.write("        <td>\r\n");
      out.write("          <div class=\"form-group\">\r\n");
      out.write("            <p><input type=\"password\" class=\"form-control\" id=\"confirm\" name=\"confirm\" size=\"25\" onChange=\"CheckPassword(this)\" required /></p>\r\n");
      out.write("          </div>\r\n");
      out.write("        </td>\r\n");
      out.write("      </tr>\r\n");
      out.write("    </div>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td class=\"title\">\r\n");
      out.write("          <p>ユーザー名</p>\r\n");
      out.write("        </td>\r\n");
      out.write("        <td>\r\n");
      out.write("          <input type=\"text\" name=\"username\" size=\"25\" required>\r\n");
      out.write("        </td>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td class=\"title\">\r\n");
      out.write("          <p>生年月日</p>\r\n");
      out.write("        </td>\r\n");
      out.write("        <td>\r\n");
      out.write("          <select name=\"year\" onchange=\"dateCheck('year', 'month', 'day')\" required>\r\n");
      out.write("            <option value=\"\">--</option>\r\n");
      out.write("              <option value=\"1920\">1920</option>\r\n");
      out.write("              <option value=\"1921\">1921</option>\r\n");
      out.write("              <option value=\"1922\">1922</option>\r\n");
      out.write("              <option value=\"1923\">1923</option>\r\n");
      out.write("              <option value=\"1924\">1924</option>\r\n");
      out.write("              <option value=\"1925\">1925</option>\r\n");
      out.write("              <option value=\"1926\">1926</option>\r\n");
      out.write("              <option value=\"1927\">1927</option>\r\n");
      out.write("              <option value=\"1928\">1928</option>\r\n");
      out.write("              <option value=\"1929\">1929</option>\r\n");
      out.write("              <option value=\"1930\">1930</option>\r\n");
      out.write("              <option value=\"1931\">1931</option>\r\n");
      out.write("              <option value=\"1932\">1932</option>\r\n");
      out.write("              <option value=\"1933\">1933</option>\r\n");
      out.write("              <option value=\"1934\">1934</option>\r\n");
      out.write("              <option value=\"1935\">1935</option>\r\n");
      out.write("              <option value=\"1936\">1936</option>\r\n");
      out.write("              <option value=\"1937\">1937</option>\r\n");
      out.write("              <option value=\"1938\">1938</option>\r\n");
      out.write("              <option value=\"1939\">1939</option>\r\n");
      out.write("              <option value=\"1940\">1940</option>\r\n");
      out.write("              <option value=\"1941\">1941</option>\r\n");
      out.write("              <option value=\"1942\">1942</option>\r\n");
      out.write("              <option value=\"1943\">1943</option>\r\n");
      out.write("              <option value=\"1944\">1944</option>\r\n");
      out.write("              <option value=\"1945\">1945</option>\r\n");
      out.write("              <option value=\"1946\">1946</option>\r\n");
      out.write("              <option value=\"1947\">1947</option>\r\n");
      out.write("              <option value=\"1948\">1948</option>\r\n");
      out.write("              <option value=\"1949\">1949</option>\r\n");
      out.write("              <option value=\"1950\">1950</option>\r\n");
      out.write("              <option value=\"1951\">1951</option>\r\n");
      out.write("              <option value=\"1952\">1952</option>\r\n");
      out.write("              <option value=\"1953\">1953</option>\r\n");
      out.write("              <option value=\"1954\">1954</option>\r\n");
      out.write("              <option value=\"1955\">1955</option>\r\n");
      out.write("              <option value=\"1956\">1956</option>\r\n");
      out.write("              <option value=\"1957\">1957</option>\r\n");
      out.write("              <option value=\"1958\">1958</option>\r\n");
      out.write("              <option value=\"1959\">1959</option>\r\n");
      out.write("              <option value=\"1960\">1960</option>\r\n");
      out.write("              <option value=\"1961\">1961</option>\r\n");
      out.write("              <option value=\"1962\">1962</option>\r\n");
      out.write("              <option value=\"1963\">1963</option>\r\n");
      out.write("              <option value=\"1964\">1964</option>\r\n");
      out.write("              <option value=\"1965\">1965</option>\r\n");
      out.write("              <option value=\"1966\">1966</option>\r\n");
      out.write("              <option value=\"1967\">1967</option>\r\n");
      out.write("              <option value=\"1968\">1968</option>\r\n");
      out.write("              <option value=\"1969\">1969</option>\r\n");
      out.write("              <option value=\"1970\">1970</option>\r\n");
      out.write("              <option value=\"1971\">1971</option>\r\n");
      out.write("              <option value=\"1972\">1972</option>\r\n");
      out.write("              <option value=\"1973\">1973</option>\r\n");
      out.write("              <option value=\"1974\">1974</option>\r\n");
      out.write("              <option value=\"1975\">1975</option>\r\n");
      out.write("              <option value=\"1976\">1976</option>\r\n");
      out.write("              <option value=\"1977\">1977</option>\r\n");
      out.write("              <option value=\"1978\">1978</option>\r\n");
      out.write("              <option value=\"1979\">1979</option>\r\n");
      out.write("              <option value=\"1980\">1980</option>\r\n");
      out.write("              <option value=\"1981\">1981</option>\r\n");
      out.write("              <option value=\"1982\">1982</option>\r\n");
      out.write("              <option value=\"1983\">1983</option>\r\n");
      out.write("              <option value=\"1984\">1984</option>\r\n");
      out.write("              <option value=\"1985\">1985</option>\r\n");
      out.write("              <option value=\"1986\">1986</option>\r\n");
      out.write("              <option value=\"1987\">1987</option>\r\n");
      out.write("              <option value=\"1988\">1988</option>\r\n");
      out.write("              <option value=\"1989\">1989</option>\r\n");
      out.write("              <option value=\"1990\">1990</option>\r\n");
      out.write("              <option value=\"1991\">1991</option>\r\n");
      out.write("              <option value=\"1992\">1992</option>\r\n");
      out.write("              <option value=\"1993\">1993</option>\r\n");
      out.write("              <option value=\"1994\">1994</option>\r\n");
      out.write("              <option value=\"1995\">1995</option>\r\n");
      out.write("              <option value=\"1996\">1996</option>\r\n");
      out.write("              <option value=\"1997\">1997</option>\r\n");
      out.write("              <option value=\"1998\">1998</option>\r\n");
      out.write("              <option value=\"1999\">1999</option>\r\n");
      out.write("              <option value=\"2000\">2000</option>\r\n");
      out.write("              <option value=\"2001\">2001</option>\r\n");
      out.write("              <option value=\"2002\">2002</option>\r\n");
      out.write("              <option value=\"2003\">2003</option>\r\n");
      out.write("              <option value=\"2004\">2004</option>\r\n");
      out.write("              <option value=\"2005\">2005</option>\r\n");
      out.write("              <option value=\"2006\">2006</option>\r\n");
      out.write("              <option value=\"2007\">2007</option>\r\n");
      out.write("              <option value=\"2008\">2008</option>\r\n");
      out.write("              <option value=\"2009\">2009</option>\r\n");
      out.write("              <option value=\"2010\">2010</option>\r\n");
      out.write("              <option value=\"2011\">2011</option>\r\n");
      out.write("              <option value=\"2012\">2012</option>\r\n");
      out.write("              <option value=\"2013\">2013</option>\r\n");
      out.write("              <option value=\"2014\">2014</option>\r\n");
      out.write("              <option value=\"2015\">2015</option>\r\n");
      out.write("              <option value=\"2016\">2016</option>\r\n");
      out.write("              <option value=\"2017\">2017</option>\r\n");
      out.write("              <option value=\"2018\">2018</option>\r\n");
      out.write("              <option value=\"2019\">2019</option>\r\n");
      out.write("          </select>年\r\n");
      out.write("          <select name=\"month\" onchange=\"dateCheck('year', 'month', 'day')\" required>\r\n");
      out.write("            <option value=\"\">--</option>\r\n");
      out.write("            <option value=\"1\">1</option>\r\n");
      out.write("              <option value=\"2\">2</option>\r\n");
      out.write("              <option value=\"3\">3</option>\r\n");
      out.write("              <option value=\"4\">4</option>\r\n");
      out.write("              <option value=\"5\">5</option>\r\n");
      out.write("              <option value=\"6\">6</option>\r\n");
      out.write("              <option value=\"7\">7</option>\r\n");
      out.write("              <option value=\"8\">8</option>\r\n");
      out.write("              <option value=\"9\">9</option>\r\n");
      out.write("              <option value=\"10\">10</option>\r\n");
      out.write("              <option value=\"11\">11</option>\r\n");
      out.write("              <option value=\"12\">12</option>\r\n");
      out.write("          </select>月\r\n");
      out.write("          <select name=\"day\" required>\r\n");
      out.write("            <option value=\"\">--</option>\r\n");
      out.write("                    <option value=\"1\">1</option>\r\n");
      out.write("                    <option value=\"2\">2</option>\r\n");
      out.write("                    <option value=\"3\">3</option>\r\n");
      out.write("                    <option value=\"4\">4</option>\r\n");
      out.write("                    <option value=\"5\">5</option>\r\n");
      out.write("                    <option value=\"6\">6</option>\r\n");
      out.write("                    <option value=\"7\">7</option>\r\n");
      out.write("                    <option value=\"8\">8</option>\r\n");
      out.write("                    <option value=\"9\">9</option>\r\n");
      out.write("                    <option value=\"10\">10</option>\r\n");
      out.write("                    <option value=\"11\">11</option>\r\n");
      out.write("                    <option value=\"12\">12</option>\r\n");
      out.write("                    <option value=\"13\">13</option>\r\n");
      out.write("                    <option value=\"14\">14</option>\r\n");
      out.write("                    <option value=\"15\">15</option>\r\n");
      out.write("                    <option value=\"16\">16</option>\r\n");
      out.write("                    <option value=\"17\">17</option>\r\n");
      out.write("                    <option value=\"18\">18</option>\r\n");
      out.write("                    <option value=\"19\">19</option>\r\n");
      out.write("                    <option value=\"20\">20</option>\r\n");
      out.write("                    <option value=\"21\">21</option>\r\n");
      out.write("                    <option value=\"22\">22</option>\r\n");
      out.write("                    <option value=\"23\">23</option>\r\n");
      out.write("                    <option value=\"24\">24</option>\r\n");
      out.write("                    <option value=\"25\">25</option>\r\n");
      out.write("                    <option value=\"26\">26</option>\r\n");
      out.write("                    <option value=\"27\">27</option>\r\n");
      out.write("                    <option value=\"28\">28</option>\r\n");
      out.write("                    <option value=\"29\">29</option>\r\n");
      out.write("                    <option value=\"30\">30</option>\r\n");
      out.write("                    <option value=\"31\">31</option>\r\n");
      out.write("            </select>日\r\n");
      out.write("        </td>\r\n");
      out.write("\r\n");
      out.write("      <tr class=\"no-line\">\r\n");
      out.write("        <td class=\"no-line\" id=\"button\" colspan=\"2\">\r\n");
      out.write("            <p>\r\n");
      out.write("              <input type=\"submit\" id=\"submit\" value=\"登録\">\r\n");
      out.write("            </p>\r\n");
      out.write("        </td>\r\n");
      out.write("        <td class=\"no-line\"></td>\r\n");
      out.write("      </form>\r\n");
      out.write("      </tr>\r\n");
      out.write("\r\n");
      out.write("        <tr class=\"no-line\">\r\n");
      out.write("          <td class=\"no-line\" colspan=\"2\">\r\n");
      out.write("            <p><a href=\"./index.jsp\">ログインに戻る</a></p>\r\n");
      out.write("          </td>\r\n");
      out.write("          <td class=\"no-line\"></td>\r\n");
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
