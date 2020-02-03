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

public final class myag_005fmain_jsp extends org.apache.jasper.runtime.HttpJspBase
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


    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

    String session_id = (String)session.getAttribute("login_id");
    String yotei_ids = (String)session.getAttribute("yotei_id");
    String yotei_names = (String)session.getAttribute("yotei_name");
  	String favorite = request.getParameter("hit_flag");
  	if (session_id == null) {
  		response.sendRedirect("index.jsp");
  	}

    //現在の日付取得
    Date today = new Date();
    //Calendarクラスのオブジェクト生成
    Calendar calendar = Calendar.getInstance();
    //現在の日付設定
    calendar.setTime(today);
    //年、月、日の取得
    int year = calendar.get(Calendar.YEAR);
    int show_year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int show_month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DATE);
    int show_day = calendar.get(Calendar.DATE);
    calendar.set(year,month,1);
    int ww = calendar.get(Calendar.DAY_OF_WEEK)-1;

    if (request.getParameter("month") != null) {
      if (!(request.getParameter("month").equals(month))) {
        year = Integer.valueOf(request.getParameter("year"));
        month = Integer.valueOf(request.getParameter("month"));
      }
    }
      if (month>11) {
        year = year+1;
        month = 0;
        calendar.set(year,month,1);
        ww = calendar.get(Calendar.DAY_OF_WEEK)-1;
      }else if(month<0){
        year = year-1;
        month = 11;
        calendar.set(year,month,1);
        ww = calendar.get(Calendar.DAY_OF_WEEK)-1;
      }else{
      month = month;
      calendar.set(year,month,1);
      ww = calendar.get(Calendar.DAY_OF_WEEK)-1;
      }

    //うるう年
    int a = year%4;
    int b = year%100;
    int c = year%400;
    int leap=0;
    if(a ==0 && b != 0 || c ==0){
      leap =29;
    }else{
      leap =28;
    }

    int tuki_max;

    if(month==0 || month==2 || month==4 || month==6 || month==7 || month==9 || month==11){
      tuki_max =31;
    }else if(month==3 || month==5 || month==8 || month==10){
      tuki_max =30;
    }else{
      tuki_max =leap;
    }

    int num[] = new int[37];
    Integer blank = 0;

    //配列へデータを入力
    for(int i=0;i<37;i++){
      if(ww>i){
        num[i] = blank;
      }else{
      num[i] = (i+1)-ww;
      }
    }
    for(int j=0;j<37;j++){
      if(num[j]>=32){
        num[j] = blank;
      }
    }


//--データベース--

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
/*String USER = "nhs90345";
String PASSWORD = "b19931230";
String URL ="jdbc:mysql://192.168.121.16/nhs90345db";*/

String DRIVER = "com.mysql.jdbc.Driver";

//確認メッセージ
StringBuffer ERMSG = null;
//ヒットフラグ
int hit_flag = 0;
int user_hit = 0;

//HashMap（1件分のデータを格納する連想配列）
HashMap<String,String> map = null;

//ArrayList（すべての件数を格納する配列）
ArrayList<HashMap> list = null;
list = new ArrayList<HashMap>();

try{	// ロードに失敗したときのための例外処理
  // JDBCドライバのロード
  Class.forName(DRIVER).newInstance();

  // Connectionオブジェクトの作成
  con = DriverManager.getConnection(URL,USER,PASSWORD);

  //Statementオブジェクトの作成
  stmt = con.createStatement();

  //SQLステートメントの作成（選択クエリ）
  SQL = new StringBuffer();

  SQL.append("select * from favorite_tbl where kaiin_id = '");
  SQL.append(session_id);
  SQL.append("' and yotei_id = '");
  SQL.append(yotei_ids);
  SQL.append("'");

  rs = stmt.executeQuery(SQL.toString());
  //入力したデータがデータベースに存在するか調べる
  if(rs.next()){  //存在する
    hit_flag=1;
  }else{  //存在しない(追加OK)
    hit_flag=0;
  }

  SQL = new StringBuffer();

  SQL.append("select kaiin_id from open_tbl where yotei_id =  '");
  SQL.append(yotei_ids);
  SQL.append("'and kaiin_id = '");
  SQL.append(session_id);
  SQL.append("'");

  rs = stmt.executeQuery(SQL.toString());
  //入力したデータがデータベースに存在するか調べる
  if(rs.next()){  //存在する
    user_hit=1;
  }else{  //存在しない(追加OK)
    user_hit=0;
  }

  SQL = new StringBuffer();

  //SQL文の発行（選択クエリ）
  SQL.append("select day,s_hour,s_mine,f_hour,f_mine,place,yotei_name from open_tbl,openyotei_tbl where open_tbl.yotei_id = openyotei_tbl.yotei_id and  openyotei_tbl.yotei_id = '");
  SQL.append(yotei_ids);
  SQL.append("'");

  //SQL文の発行（選択クエリ）
  rs = stmt.executeQuery(SQL.toString());

  //抽出したデータを繰り返し処理で表示する。
  while(rs.next()){
      //DBのデータをHashMapへ格納する
    map = new HashMap<String,String>();
    map.put("day",rs.getString("day"));
    map.put("s_hour",rs.getString("s_hour"));
    map.put("s_mine",rs.getString("s_mine"));
    map.put("f_hour",rs.getString("f_hour"));
    map.put("f_mine",rs.getString("f_mine"));
    map.put("place",rs.getString("place"));
    map.put("yotei_name",rs.getString("yotei_name"));

    //1件分のデータ(HashMap)をArrayListへ追加
    list.add(map);
  }
}	//tryブロック終了
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
      out.write("  </head>\r\n");
      out.write("\r\n");
      out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/main.css\">\r\n");
      out.write("\r\n");
      out.write("  ");

    if (favorite != null){
      if (favorite.equals("0")) {
  
      out.write("\r\n");
      out.write("    <body onLoad=\"loadFavorite()\">\r\n");
      out.write("  ");

      }
    }else{
  
      out.write("\r\n");
      out.write("    <body>\r\n");
      out.write("  ");

    }
  
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <div id=\"contents\">\r\n");
      out.write("\r\n");
      out.write("      <ul id=\"nav\">\r\n");
      out.write("        <li id=\"today\">");
      out.print( show_year );
      out.write('/');
      out.print( show_month+1 );
      out.write('/');
      out.print( show_day );
      out.write("</li>\r\n");
      out.write("        ");

          if (user_hit == 1) {
        
      out.write("\r\n");
      out.write("            <li id=\"info\"><a href=\"./main.jsp\">メインに戻る</a></li>\r\n");
      out.write("        ");

          }else if (user_hit == 0) {
            if (hit_flag == 1) {
        
      out.write("\r\n");
      out.write("              <li id=\"info\">お気に入り登録済</li>\r\n");
      out.write("              <li><a href=\"./main.jsp\">メインに戻る</a></li>\r\n");
      out.write("        ");

            }else{
        
      out.write("\r\n");
      out.write("            <li id=\"info\"><a href=\"#\" onclick=\"ShowFavorite();\">お気に入り登録</a></li>\r\n");
      out.write("            <form name=\"favorite_info\" action=\"./session_Issue.jsp\" method=\"post\">\r\n");
      out.write("              <input type=\"hidden\" name=\"favorite\" value=\"favorite\">\r\n");
      out.write("            </form>\r\n");
      out.write("            <li><a href=\"./main.jsp\">メインに戻る</a></li>\r\n");
      out.write("        ");

            }
        
      out.write("\r\n");
      out.write("        ");

          }
        
      out.write("\r\n");
      out.write("      </ul>\r\n");
      out.write("\r\n");
      out.write("    <table id=\"cal\">\r\n");
      out.write("        <tr class=\"no-line\">\r\n");
      out.write("          <td colspan=\"7\" class=\"no-line\">\r\n");
      out.write("            <h1 id=\"name\">予定名「");
      out.print( yotei_names );
      out.write("」\r\n");
      out.write("          </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr border=\"0\" cellspacing=\"1\" cellpadding=\"1\" bgcolor=\"#CCCCCC\" style=\"font: 12px; color: #666666;\">\r\n");
      out.write("            <td align=\"center\" colspan=\"7\" bgcolor=\"#EEEEEE\" height=\"30\" style=\"color: #666666;\">\r\n");
      out.write("              <div class=\"tuki\">\r\n");
      out.write("                <form method=\"post\" action=\"./myag_main.jsp\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"year\" value=\"");
      out.print(year);
      out.write("\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"month\" value=\"");
      out.print(month-1);
      out.write("\">\r\n");
      out.write("                  <input class=\"button\" type=\"submit\" value=\"前月\">\r\n");
      out.write("                </form>\r\n");
      out.write("              </div>\r\n");
      out.write("              <div class=\"tuki\">\r\n");
      out.write("                <h1>");
      out.print( year );
      out.write('年');
      out.print( month+1 );
      out.write("月</h1>\r\n");
      out.write("              </div>\r\n");
      out.write("              <div class=\"tuki\">\r\n");
      out.write("                <form method=\"post\" action=\"./myag_main.jsp\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"year\" value=\"");
      out.print(year);
      out.write("\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"month\" value=\"");
      out.print(month+1);
      out.write("\">\r\n");
      out.write("                  <input class=\"button\" type=\"submit\" value=\"翌月\">\r\n");
      out.write("                </form>\r\n");
      out.write("             </div>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("              <td align=\"center\" width=\"60\" height=\"30\" bgcolor=\"#FF3300\" style=\"font-size: 20px; font-weight: bold; color: #FFFFFF;\">日</td>\r\n");
      out.write("              <td align=\"center\" width=\"60\" bgcolor=\"#ffe4e1\" style=\"font-size: 20px; font-weight: bold; color: #666666;\">月</td>\r\n");
      out.write("              <td align=\"center\" width=\"60\" bgcolor=\"#ffe4e1\" style=\"font-size: 20px; font-weight: bold; color: #666666;\">火</td>\r\n");
      out.write("              <td align=\"center\" width=\"60\" bgcolor=\"#ffe4e1\" style=\"font-size: 20px; font-weight: bold; color: #666666;\">水</td>\r\n");
      out.write("              <td align=\"center\" width=\"60\" bgcolor=\"#ffe4e1\" style=\"font-size: 20px; font-weight: bold; color: #666666;\">木</td>\r\n");
      out.write("              <td align=\"center\" width=\"60\" bgcolor=\"#ffe4e1\" style=\"font-size: 20px; font-weight: bold; color: #666666;\">金</td>\r\n");
      out.write("              <td align=\"center\" width=\"60\" bgcolor=\"#00a1e9\" style=\"font-size: 20px; font-weight: bold; color: #666666;\">土</td>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("            ");

            if (num[0]==0) {
            
      out.write("\r\n");
      out.write("            <td bgcolor=\"#FFFFFF\"></td>\r\n");
      out.write("            ");

            }else{
            

              String year0 = String.valueOf(year);
              String month0 = String.valueOf(month+1);
              String num0 = String.valueOf(num[0]);
              String day0 = year0+month0+num0;
              boolean flag0 = false;
              for(int j = 0; j < list.size(); j++){
                if (day0.equals(list.get(j).get("day"))) {
                  flag0 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag0 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #FF0000;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #FF0000;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-01\" style=\"color: #FF0000;\">\r\n");
      out.write("                ");
      out.print( num[0] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-01\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                  <h2>");
      out.print( num[0] );
      out.write("日の予定</h2>\r\n");
      out.write("                  ");

                    for(int j = 0; j < list.size(); j++){
                  
      out.write("\r\n");
      out.write("                  ");

                    if (day0.equals(list.get(j).get("day"))) {
                  
      out.write("\r\n");
      out.write("                  <a href=\"schedule_check.jsp?day=");
      out.print( day0 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num0 );
      out.write("&year=");
      out.print( year );
      out.write("&month=");
      out.print( month );
      out.write("\">\r\n");
      out.write("                  <div class=\"yotei\">\r\n");
      out.write("                  ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                  ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                  <br>\r\n");
      out.write("                  </div>\r\n");
      out.write("                </a>\r\n");
      out.write("                  ");
 }} 
      out.write("\r\n");
      out.write("                  <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                    <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[0] );
      out.write("\">\r\n");
      out.write("                    <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                  </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("            ");

            if (num[1]==0) {
            
      out.write("\r\n");
      out.write("            <td bgcolor=\"#FFFFFF\"></td>\r\n");
      out.write("            ");

            }else{
            

              String year1 = String.valueOf(year);
              String month1 = String.valueOf(month+1);
              String num1 = String.valueOf(num[1]);
              String day1 = year1+month1+num1;
              boolean flag1 = false;
              for(int j = 0; j < list.size(); j++){
                if (day1.equals(list.get(j).get("day"))) {
                  flag1 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag1 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-02\">\r\n");
      out.write("                ");
      out.print( num[1] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-02\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[1] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day1.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day1 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num1 );
      out.write("&year=");
      out.print( year );
      out.write("&month=");
      out.print( month );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[1] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("            ");

            if (num[2]==0) {
            
      out.write("\r\n");
      out.write("            <td bgcolor=\"#FFFFFF\"></td>\r\n");
      out.write("            ");

            }else{
            

              String year2 = String.valueOf(year);
              String month2 = String.valueOf(month+1);
              String num2 = String.valueOf(num[2]);
              String day2 = year2+month2+num2;
              boolean flag2 = false;
              for(int j = 0; j < list.size(); j++){
                if (day2.equals(list.get(j).get("day"))) {
                  flag2 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag2 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-03\">\r\n");
      out.write("                ");
      out.print( num[2] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-03\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[2] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day2.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day2 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num2 );
      out.write("&year=");
      out.print( year );
      out.write("&month=");
      out.print( month );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[2] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("            ");

            if (num[3]==0) {
            
      out.write("\r\n");
      out.write("            <td bgcolor=\"#FFFFFF\"></td>\r\n");
      out.write("            ");

            }else{
            

              String year3 = String.valueOf(year);
              String month3 = String.valueOf(month+1);
              String num3 = String.valueOf(num[3]);
              String day3 = year3+month3+num3;
              boolean flag3 = false;
              for(int j = 0; j < list.size(); j++){
                if (day3.equals(list.get(j).get("day"))) {
                  flag3 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag3 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-04\">\r\n");
      out.write("                ");
      out.print( num[3] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-04\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[3] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day3.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day3 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num3 );
      out.write("&year=");
      out.print( year );
      out.write("&month=");
      out.print( month );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[3] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("            ");

            if (num[4]==0) {
            
      out.write("\r\n");
      out.write("            <td bgcolor=\"#FFFFFF\"></td>\r\n");
      out.write("            ");

            }else{
            

              String year4 = String.valueOf(year);
              String month4 = String.valueOf(month+1);
              String num4 = String.valueOf(num[4]);
              String day4 = year4+month4+num4;
              boolean flag4 = false;
              for(int j = 0; j < list.size(); j++){
                if (day4.equals(list.get(j).get("day"))) {
                  flag4 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag4 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-05\">\r\n");
      out.write("                ");
      out.print( num[4] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-05\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[4] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day4.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day4 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num4 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[4] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("            ");

            if (num[5]==0) {
            
      out.write("\r\n");
      out.write("            <td bgcolor=\"#FFFFFF\"></td>\r\n");
      out.write("            ");

            }else{
            

              String year5 = String.valueOf(year);
              String month5 = String.valueOf(month+1);
              String num5 = String.valueOf(num[5]);
              String day5 = year5+month5+num5;
              boolean flag5 = false;
              for(int j = 0; j < list.size(); j++){
                if (day5.equals(list.get(j).get("day"))) {
                  flag5 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag5 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-06\">\r\n");
      out.write("                ");
      out.print( num[5] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-06\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[5] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day5.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day5 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num5 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[5] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            

              String year6 = String.valueOf(year);
              String month6 = String.valueOf(month+1);
              String num6 = String.valueOf(num[6]);
              String day6 = year6+month6+num6;
              boolean flag6 = false;
              for(int j = 0; j < list.size(); j++){
                if (day6.equals(list.get(j).get("day"))) {
                  flag6 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag6 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-07\">\r\n");
      out.write("                ");
      out.print( num[6] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-07\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[6] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day6.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day6 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num6 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[6] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>");

            String year7 = String.valueOf(year);
            String month7 = String.valueOf(month+1);
            String num7 = String.valueOf(num[7]);
            String day7 = year7+month7+num7;
            boolean flag7 = false;
            for(int j = 0; j < list.size(); j++){
              if (day7.equals(list.get(j).get("day"))) {
                flag7 = true;
              }
            }
          
      out.write("\r\n");
      out.write("          ");

            if (flag7 == true) {
          
      out.write("\r\n");
      out.write("              <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #FF0000;\">\r\n");
      out.write("          ");

        }else{
          
      out.write("\r\n");
      out.write("              <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #FF0000;\">\r\n");
      out.write("          ");

            }
          
      out.write("\r\n");
      out.write("              <a href=\"#modal-08\" style=\"color: #FF0000;\">\r\n");
      out.write("                ");
      out.print( num[7] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-08\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[7] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day7.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day7 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num7 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[7] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>");

              String year8 = String.valueOf(year);
              String month8 = String.valueOf(month+1);
              String num8 = String.valueOf(num[8]);
              String day8 = year8+month8+num8;
              boolean flag8 = false;
              for(int j = 0; j < list.size(); j++){
                if (day8.equals(list.get(j).get("day"))) {
                  flag8 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag8 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-09\">\r\n");
      out.write("                ");
      out.print( num[8] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-09\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[8] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day8.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day8 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num8 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[8] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>");

              String year9 = String.valueOf(year);
              String month9 = String.valueOf(month+1);
              String num9 = String.valueOf(num[9]);
              String day9 = year9+month9+num9;
              boolean flag9 = false;
              for(int j = 0; j < list.size(); j++){
                if (day9.equals(list.get(j).get("day"))) {
                  flag9 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag9 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-10\">\r\n");
      out.write("                ");
      out.print( num[9] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-10\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[9] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day9.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day9 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num9 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[9] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>");

              String year10 = String.valueOf(year);
              String month10 = String.valueOf(month+1);
              String num10 = String.valueOf(num[10]);
              String day10 = year10+month10+num10;
              boolean flag10 = false;
              for(int j = 0; j < list.size(); j++){
                if (day10.equals(list.get(j).get("day"))) {
                  flag10 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag10 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-11\">\r\n");
      out.write("                ");
      out.print( num[10] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-11\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[10] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day10.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day10 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num10 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[10] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>");

              String year11 = String.valueOf(year);
              String month11 = String.valueOf(month+1);
              String num11 = String.valueOf(num[11]);
              String day11 = year11+month11+num11;
              boolean flag11 = false;
              for(int j = 0; j < list.size(); j++){
                if (day11.equals(list.get(j).get("day"))) {
                  flag11 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag11 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-12\">\r\n");
      out.write("                ");
      out.print( num[11] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-12\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[11] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day11.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day11 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num11 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[11] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>");

              String year12 = String.valueOf(year);
              String month12 = String.valueOf(month+1);
              String num12 = String.valueOf(num[12]);
              String day12 = year12+month12+num12;
              boolean flag12 = false;
              for(int j = 0; j < list.size(); j++){
                if (day12.equals(list.get(j).get("day"))) {
                  flag12 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag12 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-13\">\r\n");
      out.write("                ");
      out.print( num[12] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-13\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[12] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day12.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day12 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num12 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[12] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>");

              String year13 = String.valueOf(year);
              String month13 = String.valueOf(month+1);
              String num13 = String.valueOf(num[13]);
              String day13 = year13+month13+num13;
              boolean flag13 = false;
              for(int j = 0; j < list.size(); j++){
                if (day13.equals(list.get(j).get("day"))) {
                  flag13 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag13 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-14\">\r\n");
      out.write("                ");
      out.print( num[13] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-14\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[13] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day13.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day13 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num13 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[13] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>");

            String year14 = String.valueOf(year);
            String month14 = String.valueOf(month+1);
            String num14 = String.valueOf(num[14]);
            String day14 = year14+month14+num14;
            boolean flag14 = false;
            for(int j = 0; j < list.size(); j++){
              if (day14.equals(list.get(j).get("day"))) {
                flag14 = true;
              }
            }
          
      out.write("\r\n");
      out.write("          ");

            if (flag14 == true) {
          
      out.write("\r\n");
      out.write("              <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #FF0000;\">\r\n");
      out.write("          ");

        }else{
          
      out.write("\r\n");
      out.write("              <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #FF0000;\">\r\n");
      out.write("          ");

            }
          
      out.write("\r\n");
      out.write("              <a href=\"#modal-15\" style=\"color: #FF0000;\">\r\n");
      out.write("                ");
      out.print( num[14] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-15\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[14] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day14.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day14 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num14 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[14] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>");

              String year15 = String.valueOf(year);
              String month15 = String.valueOf(month+1);
              String num15 = String.valueOf(num[15]);
              String day15 = year15+month15+num15;
              boolean flag15 = false;
              for(int j = 0; j < list.size(); j++){
                if (day15.equals(list.get(j).get("day"))) {
                  flag15 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag15 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-16\">\r\n");
      out.write("                ");
      out.print( num[15] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-16\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[15] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day15.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day15 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num15 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[15] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>");

              String year16 = String.valueOf(year);
              String month16 = String.valueOf(month+1);
              String num16 = String.valueOf(num[16]);
              String day16 = year16+month16+num16;
              boolean flag16 = false;
              for(int j = 0; j < list.size(); j++){
                if (day16.equals(list.get(j).get("day"))) {
                  flag16 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag16 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-17\">\r\n");
      out.write("                ");
      out.print( num[16] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-17\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[16] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day16.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day16 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num16 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[16] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>");

              String year17 = String.valueOf(year);
              String month17 = String.valueOf(month+1);
              String num17 = String.valueOf(num[17]);
              String day17 = year17+month17+num17;
              boolean flag17 = false;
              for(int j = 0; j < list.size(); j++){
                if (day17.equals(list.get(j).get("day"))) {
                  flag17 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag17 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-18\">\r\n");
      out.write("                ");
      out.print( num[17] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-18\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[17] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day17.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day17 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num17 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[17] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>");

              String year18 = String.valueOf(year);
              String month18 = String.valueOf(month+1);
              String num18 = String.valueOf(num[18]);
              String day18 = year18+month18+num18;
              boolean flag18 = false;
              for(int j = 0; j < list.size(); j++){
                if (day18.equals(list.get(j).get("day"))) {
                  flag18 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag18 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-19\">\r\n");
      out.write("                ");
      out.print( num[18] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-19\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[18] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day18.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day18 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num18 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[18] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>");

              String year19 = String.valueOf(year);
              String month19 = String.valueOf(month+1);
              String num19 = String.valueOf(num[19]);
              String day19 = year19+month19+num19;
              boolean flag19 = false;
              for(int j = 0; j < list.size(); j++){
                if (day19.equals(list.get(j).get("day"))) {
                  flag19 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag19 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-20\">\r\n");
      out.write("                ");
      out.print( num[19] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-20\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[19] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day19.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day19 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num19 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[19] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>");

              String year20 = String.valueOf(year);
              String month20 = String.valueOf(month+1);
              String num20 = String.valueOf(num[20]);
              String day20 = year20+month20+num20;
              boolean flag20 = false;
              for(int j = 0; j < list.size(); j++){
                if (day20.equals(list.get(j).get("day"))) {
                  flag20 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag20 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-21\">\r\n");
      out.write("                ");
      out.print( num[20] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-21\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[20] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day20.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day20 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num20 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[20] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>");

            String year21 = String.valueOf(year);
            String month21 = String.valueOf(month+1);
            String num21 = String.valueOf(num[21]);
            String day21 = year21+month21+num21;
            boolean flag21 = false;
            for(int j = 0; j < list.size(); j++){
              if (day21.equals(list.get(j).get("day"))) {
                flag21 = true;
              }
            }
          
      out.write("\r\n");
      out.write("          ");

            if (flag21 == true) {
          
      out.write("\r\n");
      out.write("              <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #FF0000;\">\r\n");
      out.write("          ");

        }else{
          
      out.write("\r\n");
      out.write("              <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #FF0000;\">\r\n");
      out.write("          ");

            }
          
      out.write("\r\n");
      out.write("              <a href=\"#modal-22\" style=\"color: #FF0000;\">\r\n");
      out.write("                ");
      out.print( num[21] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-22\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[21] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day21.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day21 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num21 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[21] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>");

              String year22 = String.valueOf(year);
              String month22 = String.valueOf(month+1);
              String num22 = String.valueOf(num[22]);
              String day22 = year22+month22+num22;
              boolean flag22 = false;
              for(int j = 0; j < list.size(); j++){
                if (day22.equals(list.get(j).get("day"))) {
                  flag22 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag22 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-23\">\r\n");
      out.write("                ");
      out.print( num[22] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-23\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[22] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day22.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day22 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num22 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[22] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>");

              String year23 = String.valueOf(year);
              String month23 = String.valueOf(month+1);
              String num23 = String.valueOf(num[23]);
              String day23 = year23+month23+num23;
              boolean flag23 = false;
              for(int j = 0; j < list.size(); j++){
                if (day23.equals(list.get(j).get("day"))) {
                  flag23 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag23 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-24\">\r\n");
      out.write("                ");
      out.print( num[23] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-24\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[23] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day23.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day23 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num23 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[23] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>");

              String year24 = String.valueOf(year);
              String month24 = String.valueOf(month+1);
              String num24 = String.valueOf(num[24]);
              String day24 = year24+month24+num24;
              boolean flag24 = false;
              for(int j = 0; j < list.size(); j++){
                if (day24.equals(list.get(j).get("day"))) {
                  flag24 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag24 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-25\">\r\n");
      out.write("                ");
      out.print( num[24] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-25\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[24] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day24.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day24 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num24 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[24] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>");

              String year25 = String.valueOf(year);
              String month25 = String.valueOf(month+1);
              String num25 = String.valueOf(num[25]);
              String day25 = year25+month25+num25;
              boolean flag25 = false;
              for(int j = 0; j < list.size(); j++){
                if (day25.equals(list.get(j).get("day"))) {
                  flag25 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag25 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-26\">\r\n");
      out.write("                ");
      out.print( num[25] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-26\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[25] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day25.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day25 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num25 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[25] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>");

              String year26 = String.valueOf(year);
              String month26 = String.valueOf(month+1);
              String num26 = String.valueOf(num[26]);
              String day26 = year26+month26+num26;
              boolean flag26 = false;
              for(int j = 0; j < list.size(); j++){
                if (day26.equals(list.get(j).get("day"))) {
                  flag26 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag26 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-27\">\r\n");
      out.write("                ");
      out.print( num[26] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-27\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[26] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day26.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day26 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num26 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[26] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>");

              String year27 = String.valueOf(year);
              String month27 = String.valueOf(month+1);
              String num27 = String.valueOf(num[27]);
              String day27 = year27+month27+num27;
              boolean flag27 = false;
              for(int j = 0; j < list.size(); j++){
                if (day27.equals(list.get(j).get("day"))) {
                  flag27 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag27 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-28\">\r\n");
      out.write("                ");
      out.print( num[27] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-28\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[27] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day27.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day27 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num27 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[27] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("            ");

            if (num[28]==0 || tuki_max < num[28]) {
              
      out.write("\r\n");
      out.write("              </tr>\r\n");
      out.write("              ");

              }else{
              

                String year28 = String.valueOf(year);
                String month28 = String.valueOf(month+1);
                String num28 = String.valueOf(num[28]);
                String day28 = year28+month28+num28;
                boolean flag28 = false;
                for(int j = 0; j < list.size(); j++){
                  if (day28.equals(list.get(j).get("day"))) {
                    flag28 = true;
                  }
                }
              
      out.write("\r\n");
      out.write("              ");

                if (flag28 == true) {
              
      out.write("\r\n");
      out.write("                  <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #FF0000;\">\r\n");
      out.write("              ");

            }else{
              
      out.write("\r\n");
      out.write("                  <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #FF0000;\">\r\n");
      out.write("              ");

                }
              
      out.write("\r\n");
      out.write("              <a href=\"#modal-29\" style=\"color: #FF0000;\">\r\n");
      out.write("                ");
      out.print( num[28] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-29\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[28] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day28.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day28 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num28 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[28] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("            ");

            if (num[29]==0 || tuki_max < num[29]) {
            
      out.write("\r\n");
      out.write("            <td class=\"no-line\"></td>\r\n");
      out.write("            ");

            }else{
            

              String year29 = String.valueOf(year);
              String month29 = String.valueOf(month+1);
              String num29 = String.valueOf(num[29]);
              String day29 = year29+month29+num29;
              boolean flag29 = false;
              for(int j = 0; j < list.size(); j++){
                if (day29.equals(list.get(j).get("day"))) {
                  flag29 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag29 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-30\">\r\n");
      out.write("                ");
      out.print( num[29] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-30\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[29] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day29.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day29 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num29 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[29] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("            ");

            if (num[30]==0 || tuki_max < num[30]) {
            
      out.write("\r\n");
      out.write("            <td class=\"no-line\"></td>\r\n");
      out.write("            ");

            }else{
            

              String year30 = String.valueOf(year);
              String month30 = String.valueOf(month+1);
              String num30 = String.valueOf(num[30]);
              String day30 = year30+month30+num30;
              boolean flag30 = false;
              for(int j = 0; j < list.size(); j++){
                if (day30.equals(list.get(j).get("day"))) {
                  flag30 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag30 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-31\">\r\n");
      out.write("                ");
      out.print( num[30] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-31\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[30] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day30.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day30 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num30 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[30] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("            ");

            if (num[31]==0 || tuki_max < num[31]) {
            
      out.write("\r\n");
      out.write("            <td class=\"no-line\"></td>\r\n");
      out.write("            ");

            }else{
            

              String year31 = String.valueOf(year);
              String month31 = String.valueOf(month+1);
              String num31 = String.valueOf(num[31]);
              String day31 = year31+month31+num31;
              boolean flag31 = false;
              for(int j = 0; j < list.size(); j++){
                if (day31.equals(list.get(j).get("day"))) {
                  flag31 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag31 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-32\">\r\n");
      out.write("                ");
      out.print( num[31] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-32\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[31] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day31.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day31 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num31 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[31] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("            ");

            if (num[32]==0 || tuki_max < num[32]) {
            
      out.write("\r\n");
      out.write("            <td class=\"no-line\"></td>\r\n");
      out.write("            ");

            }else{
            

              String year32 = String.valueOf(year);
              String month32 = String.valueOf(month+1);
              String num32 = String.valueOf(num[32]);
              String day32 = year32+month32+num32;
              boolean flag32 = false;
              for(int j = 0; j < list.size(); j++){
                if (day32.equals(list.get(j).get("day"))) {
                  flag32 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag32 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-33\">\r\n");
      out.write("                ");
      out.print( num[32] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-33\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[32] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day32.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day32 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num32 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[32] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("            ");

            if (num[33]==0 || tuki_max < num[33]) {
            
      out.write("\r\n");
      out.write("            <td class=\"no-line\"></td>\r\n");
      out.write("            ");

            }else{
            

              String year33 = String.valueOf(year);
              String month33 = String.valueOf(month+1);
              String num33 = String.valueOf(num[33]);
              String day33 = year33+month33+num33;
              boolean flag33 = false;
              for(int j = 0; j < list.size(); j++){
                if (day33.equals(list.get(j).get("day"))) {
                  flag33 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag33 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-34\">\r\n");
      out.write("                ");
      out.print( num[33] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-34\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[33] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day33.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day33 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num33 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[33] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("            ");

            if (num[34]==0 || tuki_max < num[34]) {
            
      out.write("\r\n");
      out.write("            <td class=\"no-line\"></td>\r\n");
      out.write("            ");

            }else{
            

              String year34 = String.valueOf(year);
              String month34 = String.valueOf(month+1);
              String num34 = String.valueOf(num[34]);
              String day34 = year34+month34+num34;
              boolean flag34 = false;
              for(int j = 0; j < list.size(); j++){
                if (day34.equals(list.get(j).get("day"))) {
                  flag34 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag34 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-35\">\r\n");
      out.write("                ");
      out.print( num[34] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-35\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[34] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day34.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day34 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num34 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[34] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("          </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("          ");

          if (num[35]==0 || tuki_max < num[35]) {
            
      out.write("\r\n");
      out.write("            </tr>\r\n");
      out.write("            ");

            }else{
            

              String year35 = String.valueOf(year);
              String month35 = String.valueOf(month+1);
              String num35 = String.valueOf(num[35]);
              String day35 = year35+month35+num35;
              boolean flag35 = false;
              for(int j = 0; j < list.size(); j++){
                if (day35.equals(list.get(j).get("day"))) {
                  flag35 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag35 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #FF0000;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #FF0000;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-36\" style=\"color: #FF0000;\">\r\n");
      out.write("                ");
      out.print( num[35] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-36\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[35] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day35.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day35 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num35 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[35] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("            ");

            if (num[36]==0 || tuki_max < num[36]) {
            
      out.write("\r\n");
      out.write("            </tr>\r\n");
      out.write("            ");

            }else{
            

              String year36 = String.valueOf(year);
              String month36 = String.valueOf(month+1);
              String num36 = String.valueOf(num[36]);
              String day36 = year36+month36+num36;
              boolean flag36 = false;
              for(int j = 0; j < list.size(); j++){
                if (day36.equals(list.get(j).get("day"))) {
                  flag36 = true;
                }
              }
            
      out.write("\r\n");
      out.write("            ");

              if (flag36 == true) {
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#fef263\" style=\"color: #666666;\">\r\n");
      out.write("            ");

          }else{
            
      out.write("\r\n");
      out.write("                <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("              <a href=\"#modal-37\">\r\n");
      out.write("                ");
      out.print( num[36] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-37\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[36] );
      out.write("日の予定</h2>\r\n");
      out.write("                ");

                  for(int j = 0; j < list.size(); j++){
                
      out.write("\r\n");
      out.write("                ");

                  if (day36.equals(list.get(j).get("day"))) {
                
      out.write("\r\n");
      out.write("                <a href=\"schedule_check.jsp?day=");
      out.print( day36 );
      out.write("&s_hour=");
      out.print( list.get(j).get("s_hour") );
      out.write("&s_mine=");
      out.print( list.get(j).get("s_mine") );
      out.write("&num=");
      out.print( num36 );
      out.write("\">\r\n");
      out.write("                <div class=\"yotei\">\r\n");
      out.write("                ");
      out.print( list.get(j).get("s_hour") );
      out.write('時');
      out.print( list.get(j).get("s_mine") );
      out.write("分～\r\n");
      out.write("                ");
      out.print( list.get(j).get("place") );
      out.write("\r\n");
      out.write("                <br>\r\n");
      out.write("                </div>\r\n");
      out.write("              </a>\r\n");
      out.write("                ");
 }} 
      out.write("\r\n");
      out.write("                <form action=\"./openschedule_make.jsp\" method=\"post\">\r\n");
      out.write("                  <input type=\"hidden\" name=\"day\" value=\"");
      out.print( year );
      out.print( month+1 );
      out.print( num[36] );
      out.write("\">\r\n");
      out.write("                  <input type=\"submit\" value=\"追加\">\r\n");
      out.write("                </form>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("          </tr>\r\n");
      out.write("\r\n");
      out.write("        </table>\r\n");
      out.write("\r\n");
      out.write("      </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"area\" >\r\n");
      out.write("          <ul class=\"circles\">\r\n");
      out.write("            <li></li>\r\n");
      out.write("            <li></li>\r\n");
      out.write("            <li></li>\r\n");
      out.write("            <li></li>\r\n");
      out.write("            <li></li>\r\n");
      out.write("            <li></li>\r\n");
      out.write("            <li></li>\r\n");
      out.write("            <li></li>\r\n");
      out.write("            <li></li>\r\n");
      out.write("            <li></li>\r\n");
      out.write("          </ul>\r\n");
      out.write("        </div >\r\n");
      out.write("\r\n");
      out.write("  <script type=\"text/javascript\" src=\"./js/main.js\"></script>\r\n");
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
