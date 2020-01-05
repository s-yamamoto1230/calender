<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%

    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

    String kaiin_idStr = request.getParameter("kaiin_id");

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

    if (!(request.getParameter("month").equals(month))) {
      year = Integer.valueOf(request.getParameter("year"));
      month = Integer.valueOf(request.getParameter("month"));

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
    }

    //うるう年
    int a = year;
    int b = year;
    int c = year;
    int leap=0;
    if(a ==0 && b != 0 || c ==0){
      leap =29;
    }else{
      leap =28;
    int tuki_max;

    if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
      tuki_max =31;
    }else if(month==4 || month==6 || month==9 || month==11){
      tuki_max =30;
    }else{
      tuki_max =leap;
    }
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

  //SQL文の発行（選択クエリ）
  SQL.append("select kaiin_id,day,s_hour,s_mine,f_hour,f_mine,place,details,importance from yotei_tbl where kaiin_id = '");
  SQL.append(kaiin_idStr);
  SQL.append("'");

  //SQL文の発行（選択クエリ）
  rs = stmt.executeQuery(SQL.toString());

  //抽出したデータを繰り返し処理で表示する。
  while(rs.next()){
      //DBのデータをHashMapへ格納する
    map = new HashMap<String,String>();
    map.put("kaiin_id",rs.getString("kaiin_id"));
    map.put("day",rs.getString("day"));
    map.put("s_hour",rs.getString("s_hour"));
    map.put("s_mine",rs.getString("s_mine"));
    map.put("f_hour",rs.getString("f_hour"));
    map.put("f_mine",rs.getString("f_mine"));
    map.put("place",rs.getString("place"));
    map.put("details",rs.getString("details"));
    map.put("importance",rs.getString("importance"));

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
%>

<!DOCTYPE html>
<html>
  <meta charset="utf-8">

  <head>
    <title>メインページ</title>
  </head>

  <link rel="stylesheet" type="text/css" href="./css/main.css">

  <body>

  <ul id="nav">
    <li><a href="#" onclick="ShowAlert();">ログアウト </a></li>
    <li><a href="add_change.jsp">会員情報変更</a></li>
    <li><a href="./ad_favodel.jsp">お気に入り削除</a></li>
    <li><a href="./agenda_delete.jsp">Agenda削除</a></li>
    <li><a href="./agenda_make.jsp">Agenda作成</a></li>
    <li><a href="./ag_search.jsp">Agenda検索</a></li>
    <li><a href="./myag.jsp?kaiin_id=<%= kaiin_idStr %>">作成したAgenda</a></li>
  </ul>


  <table id="cal">

        <tr border="0" cellspacing="1" cellpadding="1" bgcolor="#CCCCCC" style="font: 12px; color: #666666;">
            <td align="center" colspan="7" bgcolor="#EEEEEE" height="30" style="color: #666666;">
              <div class="tuki">
                <a href="./main.jsp?year=<%= year %>&month=<%= month-1 %>">
                  <button>前月</button>
                </a>
              </div>
              <div class="tuki">
                <h1><%= year %>年<%= month+1 %>月</h1>
              </div>
              <div class="tuki">
                <a href="./main.jsp?year=<%= year %>&month=<%= month+1 %>">
                  <button>翌月</button>
                </a>
             </div>
          </tr>
          <tr>
              <td align="center" width="60" height="30" bgcolor="#FF3300" style="font-size: 20px; font-weight: bold; color: #FFFFFF;">日</td>
              <td align="center" width="60" bgcolor="#ffe4e1" style="font-size: 20px; font-weight: bold; color: #666666;">月</td>
              <td align="center" width="60" bgcolor="#ffe4e1" style="font-size: 20px; font-weight: bold; color: #666666;">火</td>
              <td align="center" width="60" bgcolor="#ffe4e1" style="font-size: 20px; font-weight: bold; color: #666666;">水</td>
              <td align="center" width="60" bgcolor="#ffe4e1" style="font-size: 20px; font-weight: bold; color: #666666;">木</td>
              <td align="center" width="60" bgcolor="#ffe4e1" style="font-size: 20px; font-weight: bold; color: #666666;">金</td>
              <td align="center" width="60" bgcolor="#00a1e9" style="font-size: 20px; font-weight: bold; color: #666666;">土</td>
          </tr>
          <tr>
            <%
            if (num[0]==0) {
            %>
            <td></td>
            <%
            }else{
            %>
            <td align="center" bgcolor="#FFCC99" style="color: #666666;">
              <a href="#modal-01">
                <%= num[0] %>
              </a>
              <div class="modal-wrapper" id="modal-01">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                  <h2><%= num[0] %>日の予定</h2>
                  <%
                    String year0 = String.valueOf(year);
                    String month0 = String.valueOf(month+1);
                    String num0 = String.valueOf(num[0]);
                    String day0 = year0+month0+num0;
                    for(int j = 0; j < list.size(); j++){
                  %>
                  <%
                    if (day0.equals(list.get(j).get("day"))) {
                  %>
                  <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day0 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num0 %>&year=<%= year %>&month=<%= month %>">
                  <div class="yotei">
                  <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                  <%= list.get(j).get("place") %>
                  <br>
                  </div>
                </a>
                  <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[0] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <%
            }
            %>
            <%
            if (num[1]==0) {
            %>
            <td></td>
            <%
            }else{
            %>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-02">
                <%= num[1] %>
              </a>
              <div class="modal-wrapper" id="modal-02">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[1] %>日の予定</h2>
                <%
                  String year1 = String.valueOf(year);
                  String month1 = String.valueOf(month+1);
                  String num1 = String.valueOf(num[1]);
                  String day1 = year1+month1+num1;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day1.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day1 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num1 %>&year=<%= year %>&month=<%= month %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[1] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <%
            }
            %>
            <%
            if (num[2]==0) {
            %>
            <td></td>
            <%
            }else{
            %>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-03">
                <%= num[2] %>
              </a>
              <div class="modal-wrapper" id="modal-03">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[2] %>日の予定</h2>
                <%
                  String year2 = String.valueOf(year);
                  String month2 = String.valueOf(month+1);
                  String num2 = String.valueOf(num[2]);
                  String day2 = year2+month2+num2;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day2.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day2 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num2 %>&year=<%= year %>&month=<%= month %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[2] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <%
            }
            %>
            <%
            if (num[3]==0) {
            %>
            <td></td>
            <%
            }else{
            %>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-04">
                <%= num[3] %>
              </a>
              <div class="modal-wrapper" id="modal-04">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[3] %>日の予定</h2>
                <%
                  String year3 = String.valueOf(year);
                  String month3 = String.valueOf(month+1);
                  String num3 = String.valueOf(num[3]);
                  String day3 = year3+month3+num3;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day3.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day3 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num3 %>&year=<%= year %>&month=<%= month %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[3] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <%
            }
            %>
            <%
            if (num[4]==0) {
            %>
            <td></td>
            <%
            }else{
            %>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-05">
                <%= num[4] %>
              </a>
              <div class="modal-wrapper" id="modal-05">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[4] %>日の予定</h2>
                <%
                  String year4 = String.valueOf(year);
                  String month4 = String.valueOf(month+1);
                  String num4 = String.valueOf(num[4]);
                  String day4 = year4+month4+num4;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day4.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day4 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num4 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[4] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <%
            }
            %>
            <%
            if (num[5]==0) {
            %>
            <td></td>
            <%
            }else{
            %>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-06">
                <%= num[5] %>
              </a>
              <div class="modal-wrapper" id="modal-06">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[5] %>日の予定</h2>
                <%
                  String year5 = String.valueOf(year);
                  String month5 = String.valueOf(month+1);
                  String num5 = String.valueOf(num[5]);
                  String day5 = year5+month5+num5;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day5.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day5 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num5 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[5] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <%
            }
            %>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-07">
                <%= num[6] %>
              </a>
              <div class="modal-wrapper" id="modal-07">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[6] %>日の予定</h2>
                <%
                  String year6 = String.valueOf(year);
                  String month6 = String.valueOf(month+1);
                  String num6 = String.valueOf(num[6]);
                  String day6 = year6+month6+num6;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day6.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day6 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num6 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[6] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
          </tr>
          <tr>
            <td align="center" bgcolor="#FFCC99" style="color: #666666;">
              <a href="#modal-08">
                <%= num[7] %>
              </a>
              <div class="modal-wrapper" id="modal-08">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[7] %>日の予定</h2>
                <%
                  String year7 = String.valueOf(year);
                  String month7 = String.valueOf(month+1);
                  String num7 = String.valueOf(num[7]);
                  String day7 = year7+month7+num7;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day7.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day7 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num7 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[7] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-09">
                <%= num[8] %>
              </a>
              <div class="modal-wrapper" id="modal-09">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[8] %>日の予定</h2>
                <%
                  String year8 = String.valueOf(year);
                  String month8 = String.valueOf(month+1);
                  String num8 = String.valueOf(num[8]);
                  String day8 = year8+month8+num8;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day8.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day8 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num8 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[8] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-10">
                <%= num[9] %>
              </a>
              <div class="modal-wrapper" id="modal-10">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[9] %>日の予定</h2>
                <%
                  String year9 = String.valueOf(year);
                  String month9 = String.valueOf(month+1);
                  String num9 = String.valueOf(num[9]);
                  String day9 = year9+month9+num9;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day9.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day9 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num9 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[9] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-11">
                <%= num[10] %>
              </a>
              <div class="modal-wrapper" id="modal-11">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[10] %>日の予定</h2>
                <%
                  String year10 = String.valueOf(year);
                  String month10 = String.valueOf(month+1);
                  String num10 = String.valueOf(num[10]);
                  String day10 = year10+month10+num10;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day10.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day10 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num10 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[10] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-12">
                <%= num[11] %>
              </a>
              <div class="modal-wrapper" id="modal-12">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[11] %>日の予定</h2>
                <%
                  String year11 = String.valueOf(year);
                  String month11 = String.valueOf(month+1);
                  String num11 = String.valueOf(num[11]);
                  String day11 = year11+month11+num11;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day11.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day11 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num11 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[11] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-13">
                <%= num[12] %>
              </a>
              <div class="modal-wrapper" id="modal-13">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[12] %>日の予定</h2>
                <%
                  String year12 = String.valueOf(year);
                  String month12 = String.valueOf(month+1);
                  String num12 = String.valueOf(num[12]);
                  String day12 = year12+month12+num12;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day12.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day12 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num12 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[12] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-14">
                <%= num[13] %>
              </a>
              <div class="modal-wrapper" id="modal-14">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[13] %>日の予定</h2>
                <%
                  String year13 = String.valueOf(year);
                  String month13 = String.valueOf(month+1);
                  String num13 = String.valueOf(num[13]);
                  String day13 = year13+month13+num13;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day13.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day13 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num13 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[13] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
          </tr>
          <tr>
            <td align="center" bgcolor="#FFCC99" style="color: #666666;">
              <a href="#modal-15">
                <%= num[14] %>
              </a>
              <div class="modal-wrapper" id="modal-15">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[14] %>日の予定</h2>
                <%
                  String year14 = String.valueOf(year);
                  String month14 = String.valueOf(month+1);
                  String num14 = String.valueOf(num[14]);
                  String day14 = year14+month14+num14;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day14.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day14 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num14 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[14] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-16">
                <%= num[15] %>
              </a>
              <div class="modal-wrapper" id="modal-16">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[15] %>日の予定</h2>
                <%
                  String year15 = String.valueOf(year);
                  String month15 = String.valueOf(month+1);
                  String num15 = String.valueOf(num[15]);
                  String day15 = year15+month15+num15;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day15.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day15 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num15 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[15] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-17">
                <%= num[16] %>
              </a>
              <div class="modal-wrapper" id="modal-17">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[16] %>日の予定</h2>
                <%
                  String year16 = String.valueOf(year);
                  String month16 = String.valueOf(month+1);
                  String num16 = String.valueOf(num[16]);
                  String day16 = year16+month16+num16;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day16.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day16 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num16 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[16] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-18">
                <%= num[17] %>
              </a>
              <div class="modal-wrapper" id="modal-18">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[17] %>日の予定</h2>
                <%
                  String year17 = String.valueOf(year);
                  String month17 = String.valueOf(month+1);
                  String num17 = String.valueOf(num[17]);
                  String day17 = year17+month17+num17;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day17.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day17 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num17 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[17] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-19">
                <%= num[18] %>
              </a>
              <div class="modal-wrapper" id="modal-19">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[18] %>日の予定</h2>
                <%
                  String year18 = String.valueOf(year);
                  String month18 = String.valueOf(month+1);
                  String num18 = String.valueOf(num[18]);
                  String day18 = year18+month18+num18;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day18.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day18 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num18 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[18] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-20">
                <%= num[19] %>
              </a>
              <div class="modal-wrapper" id="modal-20">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[19] %>日の予定</h2>
                <%
                  String year19 = String.valueOf(year);
                  String month19 = String.valueOf(month+1);
                  String num19 = String.valueOf(num[19]);
                  String day19 = year19+month19+num19;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day19.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day19 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num19 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[19] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-21">
                <%= num[20] %>
              </a>
              <div class="modal-wrapper" id="modal-21">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[20] %>日の予定</h2>
                <%
                  String year20 = String.valueOf(year);
                  String month20 = String.valueOf(month+1);
                  String num20 = String.valueOf(num[20]);
                  String day20 = year20+month20+num20;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day20.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day20 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num20 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[20] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
          </tr>
          <tr>
            <td align="center" bgcolor="#FFCC99" style="color: #666666;">
              <a href="#modal-22">
                <%= num[21] %>
              </a>
              <div class="modal-wrapper" id="modal-22">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[21] %>日の予定</h2>
                <%
                  String year21 = String.valueOf(year);
                  String month21 = String.valueOf(month+1);
                  String num21 = String.valueOf(num[21]);
                  String day21 = year21+month21+num21;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day21.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day21 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num21 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[21] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-23">
                <%= num[22] %>
              </a>
              <div class="modal-wrapper" id="modal-23">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[22] %>日の予定</h2>
                <%
                  String year22 = String.valueOf(year);
                  String month22 = String.valueOf(month+1);
                  String num22 = String.valueOf(num[22]);
                  String day22 = year22+month22+num22;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day22.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day22 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num22 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[22] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-24">
                <%= num[23] %>
              </a>
              <div class="modal-wrapper" id="modal-24">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[23] %>日の予定</h2>
                <%
                  String year23 = String.valueOf(year);
                  String month23 = String.valueOf(month+1);
                  String num23 = String.valueOf(num[23]);
                  String day23 = year23+month23+num23;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day23.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day23 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num23 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[23] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-25">
                <%= num[24] %>
              </a>
              <div class="modal-wrapper" id="modal-25">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[24] %>日の予定</h2>
                <%
                  String year24 = String.valueOf(year);
                  String month24 = String.valueOf(month+1);
                  String num24 = String.valueOf(num[24]);
                  String day24 = year24+month24+num24;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day24.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day24 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num24 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[24] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-26">
                <%= num[25] %>
              </a>
              <div class="modal-wrapper" id="modal-26">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[25] %>日の予定</h2>
                <%
                  String year25 = String.valueOf(year);
                  String month25 = String.valueOf(month+1);
                  String num25 = String.valueOf(num[25]);
                  String day25 = year25+month25+num25;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day25.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day25 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num25 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[25] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-27">
                <%= num[26] %>
              </a>
              <div class="modal-wrapper" id="modal-27">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[26] %>日の予定</h2>
                <%
                  String year26 = String.valueOf(year);
                  String month26 = String.valueOf(month+1);
                  String num26 = String.valueOf(num[26]);
                  String day26 = year26+month26+num26;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day26.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day26 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num26 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[26] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-28">
                <%= num[27] %>
              </a>
              <div class="modal-wrapper" id="modal-28">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[27] %>日の予定</h2>
                <%
                  String year27 = String.valueOf(year);
                  String month27 = String.valueOf(month+1);
                  String num27 = String.valueOf(num[27]);
                  String day27 = year27+month27+num27;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day27.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day27 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num27 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[27] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
          </tr>
          <tr>
            <td align="center" bgcolor="#FFCC99" style="color: #666666;">
              <a href="#modal-29">
                <%= num[28] %>
              </a>
              <div class="modal-wrapper" id="modal-29">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[28] %>日の予定</h2>
                <%
                  String year28 = String.valueOf(year);
                  String month28 = String.valueOf(month+1);
                  String num28 = String.valueOf(num[28]);
                  String day28 = year28+month28+num28;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day28.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day28 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num28 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[28] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-30">
                <%= num[29] %>
              </a>
              <div class="modal-wrapper" id="modal-30">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[29] %>日の予定</h2>
                <%
                  String year29 = String.valueOf(year);
                  String month29 = String.valueOf(month+1);
                  String num29 = String.valueOf(num[29]);
                  String day29 = year29+month29+num29;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day29.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day29 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num29 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[29] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-31">
                <%= num[30] %>
              </a>
              <div class="modal-wrapper" id="modal-31">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[30] %>日の予定</h2>
                <%
                  String year30 = String.valueOf(year);
                  String month30 = String.valueOf(month+1);
                  String num30 = String.valueOf(num[30]);
                  String day30 = year30+month30+num30;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day30.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day30 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num30 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[30] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <%
            if (num[31]==0) {
            %>
            <td></td>
            <%
            }else{
            %>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-32">
                <%= num[31] %>
              </a>
              <div class="modal-wrapper" id="modal-32">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[31] %>日の予定</h2>
                <%
                  String year31 = String.valueOf(year);
                  String month31 = String.valueOf(month+1);
                  String num31 = String.valueOf(num[31]);
                  String day31 = year31+month31+num31;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day31.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day31 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num31 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[31] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <%
            }
            %>
            <%
            if (num[32]==0) {
            %>
            <td></td>
            <%
            }else{
            %>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-33">
                <%= num[32] %>
              </a>
              <div class="modal-wrapper" id="modal-33">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[32] %>日の予定</h2>
                <%
                  String year32 = String.valueOf(year);
                  String month32 = String.valueOf(month+1);
                  String num32 = String.valueOf(num[32]);
                  String day32 = year32+month32+num32;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day32.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day32 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num32 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[32] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <%
            }
            %>
            <%
            if (num[33]==0) {
            %>
            <td></td>
            <%
            }else{
            %>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-34">
                <%= num[33] %>
              </a>
              <div class="modal-wrapper" id="modal-34">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[33] %>日の予定</h2>
                <%
                  String year33 = String.valueOf(year);
                  String month33 = String.valueOf(month+1);
                  String num33 = String.valueOf(num[33]);
                  String day33 = year33+month33+num33;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day33.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day33 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num33 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[33] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <%
            }
            %>
            <%
            if (num[34]==0) {
            %>
            <td></td>
            <%
            }else{
            %>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-35">
                <%= num[34] %>
              </a>
              <div class="modal-wrapper" id="modal-35">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[34] %>日の予定</h2>
                <%
                  String year34 = String.valueOf(year);
                  String month34 = String.valueOf(month+1);
                  String num34 = String.valueOf(num[34]);
                  String day34 = year34+month34+num34;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day34.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day34 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num34 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[34] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
              </div>
            </td>
            <%
            }
            %>
          </tr>
          <tr>
          <%
          if (num[35]==0) {
            %>
            </tr>
            <%
            }else{
            %>
            <td align="center" bgcolor="#FFCC99" style="color: #666666;">
              <a href="#modal-36">
                <%= num[35] %>
              </a>
              <div class="modal-wrapper" id="modal-36">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[35] %>日の予定</h2>
                <%
                  String year35 = String.valueOf(year);
                  String month35 = String.valueOf(month+1);
                  String num35 = String.valueOf(num[35]);
                  String day35 = year35+month35+num35;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day35.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day35 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num35 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[35] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <%
            }
            %>
            <%
            if (num[36]==0) {
            %>
            </tr>
            <%
            }else{
            %>
            <td align="center" bgcolor="#FFFFFF" style="color: #666666;">
              <a href="#modal-37">
                <%= num[36] %>
              </a>
              <div class="modal-wrapper" id="modal-37">
                <a href="#!" class="modal-overlay"></a>
                <div class="modal-window">
                  <div class="modal-content">
                <h2><%= num[36] %>日の予定</h2>
                <%
                  String year36 = String.valueOf(year);
                  String month36 = String.valueOf(month+1);
                  String num36 = String.valueOf(num[36]);
                  String day36 = year36+month36+num36;
                  for(int j = 0; j < list.size(); j++){
                %>
                <%
                  if (day36.equals(list.get(j).get("day"))) {
                %>
                <a href="schedule_check.jsp?kaiin_id=<%= list.get(j).get("kaiin_id") %>&day=<%= day36 %>&s_hour=<%= list.get(j).get("s_hour") %>&s_mine=<%= list.get(j).get("s_mine") %>&num=<%= num36 %>">
                <div class="yotei">
                <%= list.get(j).get("s_hour") %>時<%= list.get(j).get("s_mine") %>分～
                <%= list.get(j).get("place") %>
                <br>
                </div>
              </a>
                <% }} %>
                  <a href="./schedule_make.jsp?day=<%= year %><%= month+1 %><%= num[36] %>"><button>追加</button></a>
                  <a href="#!" class="modal-close">×</a>
                </div>
              </div>
            </td>
            <%
            }
            %>
          </tr>

        </table>

  <script type="text/javascript" src="./js/main.js"></script>



  </body>
</html>
