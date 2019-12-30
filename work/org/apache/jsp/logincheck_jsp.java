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

public final class logincheck_jsp extends org.apache.jasper.runtime.HttpJspBase
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

  //現在の日付取得
  Date today = new Date();
  //Calendarクラスのオブジェクト生成
  Calendar calendar = Calendar.getInstance();
  //現在の日付設定
  calendar.setTime(today);
  //年、月、日の取得
  int year = calendar.get(Calendar.YEAR);
  int month = calendar.get(Calendar.MONTH);
  int day = calendar.get(Calendar.DATE);
  calendar.set(year,month,1);
  int ww = calendar.get(Calendar.DAY_OF_WEEK)-1;


  //入力データ受信
  String idStr  = request.getParameter("id");
  String passStr = request.getParameter("pass");


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
//  String USER = "nhs90345";
//  String PASSWORD = "b19931230";
//  String URL ="jdbc:mysql://192.168.121.16/nhsagenda";

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
    SQL.append("select * from kaiin_tbl where kaiin_id = '");
    SQL.append(idStr);
//    SQL.append("' and cus_pas = '");
//    SQL.append(cus_pasStr);
    SQL.append("'");
      System.out.println(SQL.toString());

    //SQL文の実行（選択クエリ）
    rs = stmt.executeQuery(SQL.toString());

//    String id =rs.getString("kaiin_id");

    //入力したデータがデータベースに存在するか調べる
    if(rs.next()){  //存在する
      //ヒットフラグON
      hit_flag = 1;
    if(passStr.equals(rs.getString("kaiin_pass"))){
      //ヒットフラグON
      hit_flag = hit_flag+1;
    }

    if(hit_flag==2){

        //検索データをHashMapへ格納する
        map = new HashMap<String,String>();
      map.put("kaiin_id",rs.getString("kaiin_id"));
      map.put("kaiin_name",rs.getString("kaiin_name"));
      map.put("kaiin_add",rs.getString("kaiin_add"));
      map.put("kaiin_pass",rs.getString("kaiin_pass"));
      map.put("kaiin_bday",rs.getString("kaiin_bday"));


      //1件分のデータ(HashMap)をArrayListへ追加
      list.add(map);

      session.setAttribute("login", "login");
      session.setAttribute("id", "kaiin_id");
    }

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
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("  <meta charset=\"utf-8\">\r\n");
      out.write("\r\n");
      out.write("  <head>\r\n");
      out.write("    <title>メインページ</title>\r\n");
      out.write("\r\n");

  if((String)session.getAttribute("login") == "login"){  //認証OK

      out.write("\r\n");
      out.write("\r\n");
      out.write("<meta http-equiv=\"refresh\" content=\"0; URL='./main.jsp?year=");
      out.print( year );
      out.write("&month=");
      out.print( month );
      out.write("'\" />\r\n");
      out.write("\r\n");
      out.write("  </head>\r\n");
      out.write("\r\n");
      out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/main.css\">\r\n");
      out.write("\r\n");
      out.write("  <body>\r\n");
      out.write("\r\n");
      out.write("  ");

  }else{  //認証NG

      out.write("\r\n");
      out.write("  認証NG<br>\r\n");
      out.write("    ");
      out.print( "顧客IDまたはパスワードが誤っています" );
      out.write("\r\n");
      out.write("    <p><a href=\"./index.jsp\">ログインに戻る</a></p>\r\n");

  }

      out.write("\r\n");
      out.write("<br><br>\r\n");
 if(ERMSG != null){ 
      out.write("\r\n");
      out.write("予期せぬエラーが発生しました<br />\r\n");
      out.print( ERMSG );
      out.write('\r');
      out.write('\n');
 }
      out.write("\r\n");
      out.write("\r\n");
      out.write("  </body>\r\n");
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
