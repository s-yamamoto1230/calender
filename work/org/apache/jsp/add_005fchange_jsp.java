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

public final class add_005fchange_jsp extends org.apache.jasper.runtime.HttpJspBase
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
  String session_id = (String)session.getAttribute("login_id");

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
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("  <head>\r\n");
      out.write("\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("\r\n");
      out.write("    <title>会員情報変更</title>\r\n");
      out.write("\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/info.css\">\r\n");
      out.write("\r\n");
      out.write("  </head>\r\n");
      out.write("\r\n");
      out.write("  <body>\r\n");
      out.write("\r\n");
      out.write("    <h1>\r\n");
      out.write("      会員情報変更\r\n");
      out.write("    </h1>\r\n");
      out.write("    <form class=\"\" action=\"add_changecheck.jsp\" method=\"post\">\r\n");
      out.write("      <table id=\"change\">\r\n");
      out.write("        <tr class=\"no-line\">\r\n");
      out.write("          <td class=\"no-line\">\r\n");
      out.write("            会員ID\r\n");
      out.write("          </td>\r\n");
      out.write("          <td class=\"no-line\">\r\n");
      out.write("            ");
      out.print( list.get(0).get("kaiin_id") );
      out.write("\r\n");
      out.write("            <input type=\"hidden\" name=\"id\" value=\"");
      out.print( list.get(0).get("kaiin_id") );
      out.write("\">\r\n");
      out.write("          </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr class=\"no-line\">\r\n");
      out.write("          <td class=\"no-line\">\r\n");
      out.write("            会員名\r\n");
      out.write("          </td>\r\n");
      out.write("          <td class=\"no-line\">\r\n");
      out.write("            <input type=\"text\" name=\"name\" size=\"40\" value=\"");
      out.print( list.get(0).get("kaiin_name") );
      out.write("\" required>\r\n");
      out.write("          </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr class=\"no-line\">\r\n");
      out.write("          <td class=\"no-line\">\r\n");
      out.write("            メールアドレス\r\n");
      out.write("          </td>\r\n");
      out.write("          <td class=\"no-line\">\r\n");
      out.write("            <input type=\"text\" name=\"mail\" size=\"40\" value=\"");
      out.print( list.get(0).get("kaiin_add") );
      out.write("\" required>\r\n");
      out.write("          </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr class=\"no-line\">\r\n");
      out.write("          <td class=\"no-line\">\r\n");
      out.write("            パスワード\r\n");
      out.write("          </td>\r\n");
      out.write("          <td class=\"no-line\">\r\n");
      out.write("            <input type=\"password\" class=\"form-control\" id=\"password\" name=\"password\" size=\"40\" pattern=\"^[0-9a-z]+$\" required value=\"");
      out.print( list.get(0).get("kaiin_pass") );
      out.write("\">\r\n");
      out.write("          </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr class=\"no-line\">\r\n");
      out.write("          <td class=\"no-line\">\r\n");
      out.write("            パスワード確認\r\n");
      out.write("          </td>\r\n");
      out.write("          <td class=\"no-line\">\r\n");
      out.write("            <input type=\"password\" class=\"form-control\" id=\"confirm\" name=\"confirm\" size=\"40\" required oninput=\"CheckPassword(this)\" >\r\n");
      out.write("          </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr class=\"no-line\">\r\n");
      out.write("          <td class=\"no-line\">\r\n");
      out.write("            誕生日\r\n");
      out.write("          </td>\r\n");
      out.write("          <td class=\"no-line\">\r\n");
      out.write("            <select name=\"year\" onchange=\"dateCheck('year', 'month', 'day')\" required>\r\n");
      out.write("                <option value=\"\">--</option>\r\n");
      out.write("                <option value=\"1920\">1920</option>\r\n");
      out.write("                <option value=\"1921\">1921</option>\r\n");
      out.write("                <option value=\"1922\">1922</option>\r\n");
      out.write("                <option value=\"1923\">1923</option>\r\n");
      out.write("                <option value=\"1924\">1924</option>\r\n");
      out.write("                <option value=\"1925\">1925</option>\r\n");
      out.write("                <option value=\"1926\">1926</option>\r\n");
      out.write("                <option value=\"1927\">1927</option>\r\n");
      out.write("                <option value=\"1928\">1928</option>\r\n");
      out.write("                <option value=\"1929\">1929</option>\r\n");
      out.write("                <option value=\"1930\">1930</option>\r\n");
      out.write("                <option value=\"1931\">1931</option>\r\n");
      out.write("                <option value=\"1932\">1932</option>\r\n");
      out.write("                <option value=\"1933\">1933</option>\r\n");
      out.write("                <option value=\"1934\">1934</option>\r\n");
      out.write("                <option value=\"1935\">1935</option>\r\n");
      out.write("                <option value=\"1936\">1936</option>\r\n");
      out.write("                <option value=\"1937\">1937</option>\r\n");
      out.write("                <option value=\"1938\">1938</option>\r\n");
      out.write("                <option value=\"1939\">1939</option>\r\n");
      out.write("                <option value=\"1940\">1940</option>\r\n");
      out.write("                <option value=\"1941\">1941</option>\r\n");
      out.write("                <option value=\"1942\">1942</option>\r\n");
      out.write("                <option value=\"1943\">1943</option>\r\n");
      out.write("                <option value=\"1944\">1944</option>\r\n");
      out.write("                <option value=\"1945\">1945</option>\r\n");
      out.write("                <option value=\"1946\">1946</option>\r\n");
      out.write("                <option value=\"1947\">1947</option>\r\n");
      out.write("                <option value=\"1948\">1948</option>\r\n");
      out.write("                <option value=\"1949\">1949</option>\r\n");
      out.write("                <option value=\"1950\">1950</option>\r\n");
      out.write("                <option value=\"1951\">1951</option>\r\n");
      out.write("                <option value=\"1952\">1952</option>\r\n");
      out.write("                <option value=\"1953\">1953</option>\r\n");
      out.write("                <option value=\"1954\">1954</option>\r\n");
      out.write("                <option value=\"1955\">1955</option>\r\n");
      out.write("                <option value=\"1956\">1956</option>\r\n");
      out.write("                <option value=\"1957\">1957</option>\r\n");
      out.write("                <option value=\"1958\">1958</option>\r\n");
      out.write("                <option value=\"1959\">1959</option>\r\n");
      out.write("                <option value=\"1960\">1960</option>\r\n");
      out.write("                <option value=\"1961\">1961</option>\r\n");
      out.write("                <option value=\"1962\">1962</option>\r\n");
      out.write("                <option value=\"1963\">1963</option>\r\n");
      out.write("                <option value=\"1964\">1964</option>\r\n");
      out.write("                <option value=\"1965\">1965</option>\r\n");
      out.write("                <option value=\"1966\">1966</option>\r\n");
      out.write("                <option value=\"1967\">1967</option>\r\n");
      out.write("                <option value=\"1968\">1968</option>\r\n");
      out.write("                <option value=\"1969\">1969</option>\r\n");
      out.write("                <option value=\"1970\">1970</option>\r\n");
      out.write("                <option value=\"1971\">1971</option>\r\n");
      out.write("                <option value=\"1972\">1972</option>\r\n");
      out.write("                <option value=\"1973\">1973</option>\r\n");
      out.write("                <option value=\"1974\">1974</option>\r\n");
      out.write("                <option value=\"1975\">1975</option>\r\n");
      out.write("                <option value=\"1976\">1976</option>\r\n");
      out.write("                <option value=\"1977\">1977</option>\r\n");
      out.write("                <option value=\"1978\">1978</option>\r\n");
      out.write("                <option value=\"1979\">1979</option>\r\n");
      out.write("                <option value=\"1980\">1980</option>\r\n");
      out.write("                <option value=\"1981\">1981</option>\r\n");
      out.write("                <option value=\"1982\">1982</option>\r\n");
      out.write("                <option value=\"1983\">1983</option>\r\n");
      out.write("                <option value=\"1984\">1984</option>\r\n");
      out.write("                <option value=\"1985\">1985</option>\r\n");
      out.write("                <option value=\"1986\">1986</option>\r\n");
      out.write("                <option value=\"1987\">1987</option>\r\n");
      out.write("                <option value=\"1988\">1988</option>\r\n");
      out.write("                <option value=\"1989\">1989</option>\r\n");
      out.write("                <option value=\"1990\">1990</option>\r\n");
      out.write("                <option value=\"1991\">1991</option>\r\n");
      out.write("                <option value=\"1992\">1992</option>\r\n");
      out.write("                <option value=\"1993\">1993</option>\r\n");
      out.write("                <option value=\"1994\">1994</option>\r\n");
      out.write("                <option value=\"1995\">1995</option>\r\n");
      out.write("                <option value=\"1996\">1996</option>\r\n");
      out.write("                <option value=\"1997\">1997</option>\r\n");
      out.write("                <option value=\"1998\">1998</option>\r\n");
      out.write("                <option value=\"1999\">1999</option>\r\n");
      out.write("                <option value=\"2000\">2000</option>\r\n");
      out.write("                <option value=\"2001\">2001</option>\r\n");
      out.write("                <option value=\"2002\">2002</option>\r\n");
      out.write("                <option value=\"2003\">2003</option>\r\n");
      out.write("                <option value=\"2004\">2004</option>\r\n");
      out.write("                <option value=\"2005\">2005</option>\r\n");
      out.write("                <option value=\"2006\">2006</option>\r\n");
      out.write("                <option value=\"2007\">2007</option>\r\n");
      out.write("                <option value=\"2008\">2008</option>\r\n");
      out.write("                <option value=\"2009\">2009</option>\r\n");
      out.write("                <option value=\"2010\">2010</option>\r\n");
      out.write("                <option value=\"2011\">2011</option>\r\n");
      out.write("                <option value=\"2012\">2012</option>\r\n");
      out.write("                <option value=\"2013\">2013</option>\r\n");
      out.write("                <option value=\"2014\">2014</option>\r\n");
      out.write("                <option value=\"2015\">2015</option>\r\n");
      out.write("                <option value=\"2016\">2016</option>\r\n");
      out.write("                <option value=\"2017\">2017</option>\r\n");
      out.write("                <option value=\"2018\">2018</option>\r\n");
      out.write("                <option value=\"2019\">2019</option>\r\n");
      out.write("            </select>年\r\n");
      out.write("            <select name=\"month\" onchange=\"dateCheck('year', 'month', 'day')\" required>\r\n");
      out.write("                <option value=\"\">--</option>\r\n");
      out.write("                <option value=\"1\">1</option>\r\n");
      out.write("                <option value=\"2\">2</option>\r\n");
      out.write("                <option value=\"3\">3</option>\r\n");
      out.write("                <option value=\"4\">4</option>\r\n");
      out.write("                <option value=\"5\">5</option>\r\n");
      out.write("                <option value=\"6\">6</option>\r\n");
      out.write("                <option value=\"7\">7</option>\r\n");
      out.write("                <option value=\"8\">8</option>\r\n");
      out.write("                <option value=\"9\">9</option>\r\n");
      out.write("                <option value=\"10\">10</option>\r\n");
      out.write("                <option value=\"11\">11</option>\r\n");
      out.write("                <option value=\"12\">12</option>\r\n");
      out.write("            </select>月\r\n");
      out.write("            <select name=\"day\" required>\r\n");
      out.write("                      <option value=\"\">--</option>\r\n");
      out.write("                      <option value=\"1\">1</option>\r\n");
      out.write("                      <option value=\"2\">2</option>\r\n");
      out.write("                      <option value=\"3\">3</option>\r\n");
      out.write("                      <option value=\"4\">4</option>\r\n");
      out.write("                      <option value=\"5\">5</option>\r\n");
      out.write("                      <option value=\"6\">6</option>\r\n");
      out.write("                      <option value=\"7\">7</option>\r\n");
      out.write("                      <option value=\"8\">8</option>\r\n");
      out.write("                      <option value=\"9\">9</option>\r\n");
      out.write("                      <option value=\"10\">10</option>\r\n");
      out.write("                      <option value=\"11\">11</option>\r\n");
      out.write("                      <option value=\"12\">12</option>\r\n");
      out.write("                      <option value=\"13\">13</option>\r\n");
      out.write("                      <option value=\"14\">14</option>\r\n");
      out.write("                      <option value=\"15\">15</option>\r\n");
      out.write("                      <option value=\"16\">16</option>\r\n");
      out.write("                      <option value=\"17\">17</option>\r\n");
      out.write("                      <option value=\"18\">18</option>\r\n");
      out.write("                      <option value=\"19\">19</option>\r\n");
      out.write("                      <option value=\"20\">20</option>\r\n");
      out.write("                      <option value=\"21\">21</option>\r\n");
      out.write("                      <option value=\"22\">22</option>\r\n");
      out.write("                      <option value=\"23\">23</option>\r\n");
      out.write("                      <option value=\"24\">24</option>\r\n");
      out.write("                      <option value=\"25\">25</option>\r\n");
      out.write("                      <option value=\"26\">26</option>\r\n");
      out.write("                      <option value=\"27\">27</option>\r\n");
      out.write("                      <option value=\"28\">28</option>\r\n");
      out.write("                      <option value=\"29\">29</option>\r\n");
      out.write("                      <option value=\"30\">30</option>\r\n");
      out.write("                      <option value=\"31\">31</option>\r\n");
      out.write("              </select>日\r\n");
      out.write("          </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("      </table>\r\n");
      out.write("      <input type=\"submit\" name=\"\" value=\"更新\">\r\n");
      out.write("    </form>\r\n");
      out.write("    <p id=\"back\"><a href=\"./main.jsp\">メイン画面に戻る</a></p>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"./js/info.js\"></script>\r\n");
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
