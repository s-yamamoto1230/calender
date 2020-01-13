package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class schedule_005fmakecomplete_jsp extends org.apache.jasper.runtime.HttpJspBase
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

  //文字コードの指定
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");

  //入力データ受信
  String kaiin_idStr = request.getParameter("id");
  String dayStr = request.getParameter("day");
  String s_hourStr = request.getParameter("s_hour");
  String s_minStr = request.getParameter("s_min");
  String f_hourStr = request.getParameter("f_hour");
  String f_minStr = request.getParameter("f_min");
  String placeStr = request.getParameter("place");
  String detailsStr = request.getParameter("details");
  String importanceStr = request.getParameter("importance");
  String year = request.getParameter("year");
  String month = request.getParameter("month");



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
/*  String USER = "nhsxxxxx";
  String PASSWORD = "byyyymmdd";
  String URL ="jdbc:mysql://192.168.121.16/nhs90345db";
*/

  String DRIVER = "com.mysql.jdbc.Driver";

  //確認メッセージ
  StringBuffer ERMSG = null;

  //ヒットフラグ
  int hit_flag = 0;

  //追加件数
  int ins_count=0;

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
    SQL.append("select * from yotei_tbl where kaiin_id = '");
    SQL.append(kaiin_idStr);
    SQL.append("' and day ='");
    SQL.append(dayStr);
    SQL.append("' and s_hour ='");
    SQL.append(s_hourStr);
    SQL.append("' and s_mine ='");
    SQL.append(s_minStr);
    SQL.append("'");

      System.out.println(SQL.toString());

    //SQL文の実行（選択クエリ）
    rs = stmt.executeQuery(SQL.toString());

    //入力したデータがデータベースに存在するか調べる
    if(rs.next()){  //存在する
      //ヒットフラグON
      hit_flag = 1;
    }else{  //存在しない(追加OK)
      //ヒットフラグOFF
      hit_flag = 0;
    //SQLステートメントの作成（選択クエリ）
    SQL=new StringBuffer();

    //SQL文の構築
    SQL.append("insert into yotei_tbl(kaiin_id,day,s_hour,s_mine,f_hour,f_mine,place,details,importance)");
    SQL.append("values('");
    SQL.append(kaiin_idStr);
    SQL.append("','");
    SQL.append(dayStr);
    SQL.append("','");
    SQL.append(s_hourStr);
    SQL.append("','");
    SQL.append(s_minStr);
    SQL.append("','");
    SQL.append(f_hourStr);
    SQL.append("','");
    SQL.append(f_minStr);
    SQL.append("','");
    SQL.append(placeStr);
    SQL.append("','");
    SQL.append(detailsStr);
    SQL.append("','");
    SQL.append(importanceStr);
    SQL.append("')");
    }

    //System.out.println(SQL.toString)

    //SQL文の実行(DB追加)
    ins_count=stmt.executeUpdate(SQL.toString());

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
      out.write("<head>\r\n");
      out.write("  <meta charset=\"utf-8\">\r\n");
      out.write("  <title>登録完了</title>\r\n");
      out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/info.css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");

  if(hit_flag == 1){  //認証NG

      out.write("\r\n");
      out.write("追加NG<br>\r\n");
      out.print( "入力された予定時刻は既に登録してあります" );
      out.write('\r');
      out.write('\n');

}else if(ins_count==0){//追加処理失敗

      out.write("\r\n");
      out.write("追加NG<br>\r\n");
      out.print( "登録が失敗しました" );
      out.write('\r');
      out.write('\n');

  }else{  //認証OK

      out.write("\r\n");
      out.write("    <h1>新規登録完了</h1><br>\r\n");
      out.write("    ");
      out.print( ins_count + "件登録が完了しました" );
      out.write('\r');
      out.write('\n');

  }

      out.write("\r\n");
      out.write("<br><br>\r\n");
 if(ERMSG != null){ 
      out.write("\r\n");
      out.write("予期せぬエラーが発生しました<br />\r\n");
      out.print( ERMSG );
      out.write('\r');
      out.write('\n');
 }else{ 
      out.write("\r\n");
      out.write("※エラーは発生しませんでした<br/>\r\n");
 } 
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("  <p><a href=\"./logincheck.jsp?id=");
      out.print( kaiin_idStr );
      out.write("\">メイン画面に戻る</a></p>\r\n");
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
