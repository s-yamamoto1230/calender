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

    int[] num = new int[37];
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
/*	String USER ="root";
String PASSWORD = "";
String URL ="jdbc:mysql://localhost/nhs90345db";*/

//サーバーのMySQLに接続する設定
String USER = "nhs90345";
String PASSWORD = "b19931230";
String URL ="jdbc:mysql://192.168.121.16/nhs90345db";

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
  SQL.append("select kaiin_id,day,s_time,f_time,place,details,importance from yotei_tbl where kaiin_id = '");
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
    map.put("s_time",rs.getString("s_time"));
    map.put("f_time",rs.getString("f_time"));
    map.put("place",rs.getString("place"));
    map.put("details",rs.getString("details"));
    map.put("importance",rs.getString("importance"));

    map.put("",rs.getString(""));

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
    <li><a href="addresscange.html">メールアドレス変更</a></li>
    <li><a href="./ad_favodel.html">お気に入り削除</a></li>
    <li><a href="./ad_del.html">Agenda削除</a></li>
    <li><a href="./agenda_make.jsp?">Agenda作成</a></li>
    <li><a href="./ag_search.html">Agenda検索</a></li>
  </ul>
  <table id="cal">

        <tr border="0" cellspacing="1" cellpadding="1" bgcolor="#CCCCCC" style="font: 12px; color: #666666;">
            <td align="center" colspan="7" bgcolor="#EEEEEE" height="30" style="color: #666666;">
              <div class="tuki">
                <button>前月</button>
              </div>
              <div class="tuki">
                <h1><%= year %>年<%= month+1 %>月</h1>
              </div>
              <div class="tuki">
               <button>翌月</button>
             </div>
            </div>
          </tr>
            <td align="center" width="60" height="30" bgcolor="#FF3300" style="color: #FFFFFF;">日</td>
            <td align="center" width="60" bgcolor="#ffe4e1" style="color: #666666;">月</td>
            <td align="center" width="60" bgcolor="#ffe4e1" style="color: #666666;">火</td>
            <td align="center" width="60" bgcolor="#ffe4e1" style="color: #666666;">水</td>
            <td align="center" width="60" bgcolor="#ffe4e1" style="color: #666666;">木</td>
            <td align="center" width="60" bgcolor="#ffe4e1" style="color: #666666;">金</td>
            <td align="center" width="60" bgcolor="#00a1e9" style="color: #666666;">土</td>
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
                    for(int j = 0; j < list.size(); i++){
                  %>
                  <%
                    if (<%= year %>+<%= month %>+<%= num[0].equals(<%= list.get(j).get("day")) %>) {
                  %>
                  <%= list.get(j).get("place")) %>
                  <%= list.get(j).get("details")) %>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
                  <p>学校</p>
                  <p>バイト</p>
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
