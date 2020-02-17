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

    String session_id = (String)session.getAttribute("login_id");
    String session_name = (String)session.getAttribute("login_name");
    String yotei_ids = (String)session.getAttribute("yotei_id");
    String p = request.getParameter("page_no");
    String del = request.getParameter("del");
    String create = request.getParameter("create");
    String change = request.getParameter("change");
/*  	if (session_id == null) {
  		response.sendRedirect("index.jsp");
  	}*/
    if (yotei_ids != null) {
      session.removeAttribute("yotei_id");
      session.removeAttribute("yotei_name");
    }


//--データベース--
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

//ヒットフラグ
int hit_flag = 0;

//HashMap（1件分のデータを格納する連想配列）
HashMap<String,String> map = null;

//ArrayList（すべての件数を格納する配列）
ArrayList<HashMap> list = null;
list = new ArrayList<HashMap>();

if(p != null){
  if (p.equals("0")) { }
  if (p.equals("1")) {
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
      SQL.append("select yotei_id,yotei_name,open_set,yotei_pass,yotei_writing from open_tbl where kaiin_id = '");
      SQL.append(session_id);
      SQL.append("' order by yotei_id ASC");
  //      System.out.println(SQL.toString());

      //SQL文の実行（選択クエリ）
      rs = stmt.executeQuery(SQL.toString());

          //検索データをHashMapへ格納する
          while(rs.next()){
        //DBのデータをHashMapへ格納する
            map = new HashMap<String,String>();
            map.put("yotei_id",rs.getString("yotei_id"));
            map.put("yotei_name",rs.getString("yotei_name"));
            map.put("open_set",rs.getString("open_set"));
            map.put("yotei_pass",rs.getString("yotei_pass"));
            map.put("yotei_writing",rs.getString("yotei_writing"));

            //1件分のデータ(HashMap)をArrayListへ追加
            list.add(map);
          }
      //入力したデータがデータベースに存在するか調べる
      if(list.size() > 0){  //存在する
            hit_flag = 1;
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
  }
  if (p.equals("2")) { }
  if (p.equals("3")) {
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
      System.out.println(SQL.toString());
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
  }
  if (p.equals("4")) {
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
  }
}
else{

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
  SQL.append("select favorite_tbl.yotei_id,yotei_name,yotei_writing,kaiin_name from favorite_tbl,open_tbl,kaiin_tbl where favorite_tbl.yotei_id = open_tbl.yotei_id and kaiin_tbl.kaiin_id = open_tbl.kaiin_id and favorite_tbl.kaiin_id = '");
  SQL.append(session_id);
  SQL.append("' order by yotei_name ASC");
//      System.out.println(SQL.toString());

  //SQL文の実行（選択クエリ）
  rs = stmt.executeQuery(SQL.toString());

      //検索データをHashMapへ格納する
      while(rs.next()){
    //DBのデータをHashMapへ格納する
        map = new HashMap<String,String>();
        map.put("yotei_id",rs.getString("yotei_id"));
        map.put("yotei_name",rs.getString("yotei_name"));
        map.put("yotei_writing",rs.getString("yotei_writing"));
        map.put("kaiin_name",rs.getString("kaiin_name"));

        //1件分のデータ(HashMap)をArrayListへ追加
        list.add(map);
      }
  //入力したデータがデータベースに存在するか調べる
  if(list.size() > 0){  //存在する
        hit_flag = 1;
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
}
%>

<!DOCTYPE html>
<html>
  <meta charset="utf-8">

  <head>
    <title>メインページ</title>
  </head>

  <link rel="stylesheet" type="text/css" href="./css/main.css">

    <%
      if (del != null){
        if (del.equals("1")) {
    %>
      <body onLoad="loadFavodel()">
    <%
        }if (del.equals("2")) {
    %>
      <body onLoad="loadCalendardel()">
    <%
        }
      }
      else if (create != null) {
        if (create.equals("1")) {
    %>
      <body onLoad="loadCalendarmake()">
    <%
        }else{
    %>
      <body onLoad="loadCalendarNomake()">
    <%
        }
      }
      else if (change != null) {
        if (change.equals("1")) {
    %>
      <body onLoad="loadChange()">
    <%
        }
      }
      else
      {
    %>
      <body>
    <%
      }
    %>

    <div id="contents">

      <div id="wrap">

        <header>
          <h1>
            ようこそ<%= session_name %>さん
          </h1>
        </header>

        <nav id="main_nav">
          <ul>
            <li><a href="./main.jsp">お気に入り一覧</a></li>
            <li><a href="./main.jsp?page_no=0">公開カレンダー作成</a></li>
            <li><a href="./main.jsp?page_no=1">作成した公開カレンダー</a></li>
            <li><a href="./main.jsp?page_no=2">公開カレンダー検索</a></li>
            <li><a href="./main.jsp?page_no=3">会員情報変更</a></li>
            <li><a href="#" onclick="ShowAlert();">ログアウト </a></li>
            <form name="logout_info" action="./index.jsp" method="post">
              <input type="hidden" name="logout" value="logout">
            </form>
          </ul>
        </nav>
        <div id="panel">
          <main>
            <%
              if (p == null) {
            %>
              <h2>お気に入り一覧</h2>
              <%
               if (hit_flag == 1) {
              %>
                <table id="list">
                  <tr class="no-line">
                    <th class="no-line" style="padding: 20px;">カレンダー名</td>
                    <th class="no-line" style="padding: 20px;">書き込み</td>
                    <th class="no-line" style="padding: 20px;">作成者</td>
                    <th></th>
                  </tr>
                <%
                  for(int i=0; i<list.size();i++){
                %>
                  <tr class="no-line">
                    <td class="no-line" align="left" style="font-size:25px; font-weight:bold;;">
                      <%= list.get(i).get("yotei_name") %>
                    </td>
                    <td class="no-line">
                      <%
                       if(list.get(i).get("yotei_writing").equals("1")) {
                      %>
                        許可
                      <%
                        }else{
                      %>
                        禁止
                      <%
                        }
                      %>
                    </td>
                    <td class="no-line"><%= list.get(i).get("kaiin_name") %></td>
                    <td class="no-line">
                      <form action="session_Issue.jsp" method="post">
                        <input type="hidden" name="yotei_id" value="<%= list.get(i).get("yotei_id") %>">
                        <input type="hidden" name="yotei_name" value="<%= list.get(i).get("yotei_name") %>">
                        <input type="submit" value="確認する">
                      </form>
                    </td>
                    <td class="no-line">
                      <form name="favorite_del" action="favorite_deletecomplete.jsp" method="post">
                        <input type="button" value="削除" onclick="ShowFavodel();">
                        <input type="hidden" name="yotei_id" value="<%= list.get(i).get("yotei_id") %>">
                      </form>
                    </td>
                  </tr>
                  <%
                    }
                  %>
                </table>
                <%
                  }else if (hit_flag == 0) {
                %>
                  お気に入りはありません。
                <%
                  }
                %>
            <%
              }
            %>

            <%
              if (p != null && p.equals("0")) {
            %>
            <h2>カレンダー新規作成</h2>
            <table>
              <form  name="form" action="./agenda_makecomplete.jsp" method="post" onsubmit="return formChecksub()">
                <tr>
                  <td class="title">
                    <p>ID</p>
                  </td>
                  <td>
                    <p><input type="text" name="id" id="id" pattern="^[0-9a-z]+$" size="25" required></p>
                    <p id="notice-input-text-0" style="display: none; color: red;"></p>
                    <p class="alert">※半角英数字15文字以下</p>
                  </td>
                </tr>
                <tr>
                  <td class="title">
                    <p>タイトル</p>
                  </td>
                  <td>
                    <input type="text" name="title" size="25" required>
                  </td>
                </tr>
                <tr>
                  <td class="title">
                    <p>公開設定</p>
                  </td>
                  <td>
                    <label><input type="radio" name="open"  value="1" onClick="openflg0(this.checked);" checked>全員に公開</label>
                    <label><input type="radio" name="open"  value="2" onClick="openflg1(this.checked);">特定の人にのみ公開</label>
                  </td>
                </tr>
                <div class="form-group">
                  <tr>
                    <td class="title">
                      <p>パスワード</p>
                    </td>
                    <td>
                      <br><div class="form-group">
                        <p><input type="password" class="form-control" id="password" name="password" size="25" pattern="^[0-9a-z]+$" disabled="disabled"> </p>
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <td class="title">
                      パスワード（確認）
                    </td>
                    <td>
                      <div class="form-group">
                        <p><input type="password" class="form-control" id="confirm" name="confirm" size="25" oninput="CheckPassword(this)" disabled="disabled" required></p>
                      </div>
                    </td>
                  </tr>
                </div>
                <tr>
                  <td class="title">
                    <p>他人の書き込み</p>
                  </td>
                  <td>
                    <label><input type="radio" name="permission" value="1">許可</label>
                    <label><input type="radio" name="permission" value="2" checked>禁止</label>
                  </td>
                </tr>
                <tr class="no-line">
                  <td class="no-line" id="button" colspan="2">
                      <p>
                        <input type="submit" id="submit" value="登録">
                      </p>
                  </td>
                </tr>
              </form>
            </table>
            <%
              }
            %>

            <%
              if (p != null && p.equals("1")) {
            %>
              <h2>作成したカレンダー一覧</h2>
            <%
             if (hit_flag == 1) {
            %>
            <table id="list">
              <tr class="no-line">
                <th class="no-line" style="padding: 20px;">カレンダーID</td>
                <th class="no-line" style="padding: 20px;">カレンダー名</td>
                <th class="no-line" style="padding: 20px;">公開設定</td>
                <th class="no-line" style="padding: 20px;">パスワード</td>
                <th class="no-line" style="padding: 20px;">書き込み設定</td>
                <th></th>
              </tr>
            <%
              for(int i=0; i<list.size();i++){
            %>
            <tr class="no-line">
              <td class="no-line" align="left" style="font-size:25px; font-weight:bold;;">
                <%= list.get(i).get("yotei_id") %>
              </td>
              <td class="no-line"><%= list.get(i).get("yotei_name") %></td>
              <td class="no-line">
                <%if (list.get(i).get("open_set").equals("1")) { %>
                公開
                <%
                  }else{
                %>
                  非公開
                <%
                  }
                %>
              </td>
              <td class="no-line">
                <%= list.get(i).get("yotei_pass") %>
              </td>
              <td class="no-line">
                <%
                  if(list.get(i).get("yotei_writing").equals("1")) {
                %>
                  許可
                <%
                  }else{
                %>
                  禁止
                <%
                  }
                %>
              </td>
              <td class="no-line">
                <form action="session_Issue.jsp" method="post">
                  <input type="hidden" name="yotei_id" value="<%= list.get(i).get("yotei_id") %>">
                  <input type="hidden" name="yotei_name" value="<%= list.get(i).get("yotei_name") %>">
                  <input type="submit" value="確認">
                </form>
              </td>
              <td class="no-line">
                <form name="calendar_del" action="agenda_deletecomplete.jsp" method="post">
                  <input type="button" value="削除" onclick="ShowCalendardel();">
                  <input type="hidden" name="yotei_id" value="<%= list.get(i).get("yotei_id") %>">
                </form>
              </td>
            </tr>
            <%
              }
            %>
          </table>
          <%
            }else if (hit_flag == 0) {
          %>
            作成した予定はありません。
          <%
            }
          %>
          <%
            }
          %>

          <%
            if (p != null && p.equals("2")) {
          %>
          <h2>作成したカレンダー一覧</h2>
          <p>
            カレンダーIDかキーワードを入力して検索ボタンを押してください。<br>カレンダーを検索します。<br>(両方入力するとカレンダーIDを優先して検索します)
          </p>
           <form action="./agenda_searchcomplete.jsp" method="post">
            <table>
              <tr>
                <td  class="title">
                  <p>カレンダーID</p>
                </td>
                <td class="no-line">
                  <input type="text" name="id" size="25" class="text">
                </td>
              </tr>
              <tr>
                <td  class="title">
                  <p>キーワード</p>
                </td>
                <td class="no-line">
                  <input type="text" name="keyword" size="25" class="text">
                </td>

              <tr class="no-line">
                <td class="no-line" id="button" colspan="2">
                  <input type="submit" value="検索" id="button">
                </td>
              </form>
              </tr>
            </table>
          <%
            }
          %>

          <%
            if (p != null && p.equals("3")) {
          %>
          <h2>登録情報変更</h2>
          <form class="" action="main.jsp?page_no=4" method="post">
            <table id="change">
              <tr class="no-line">
                <td class="no-line">
                  会員ID
                </td>
                <td class="no-line">
                  <%= list.get(0).get("kaiin_id") %>
                  <input type="hidden" name="id" value="<%= list.get(0).get("kaiin_id") %>">
                </td>
              </tr>
              <tr class="no-line">
                <td class="no-line">
                  会員名
                </td>
                <td class="no-line">
                  <input type="text" name="name" size="40" value="<%= list.get(0).get("kaiin_name") %>" required>
                </td>
              </tr>
              <tr class="no-line">
                <td class="no-line">
                  メールアドレス
                </td>
                <td class="no-line">
                  <input type="text" name="mail" size="40" value="<%= list.get(0).get("kaiin_add") %>" required>
                </td>
              </tr>
              <tr class="no-line">
                <td class="no-line">
                  パスワード
                </td>
                <td class="no-line">
                  <input type="password" class="form-control" id="password" name="password" size="40" pattern="^[0-9a-z]+$" required value="<%= list.get(0).get("kaiin_pass") %>">
                </td>
              </tr>
              <tr class="no-line">
                <td class="no-line">
                  パスワード確認
                </td>
                <td class="no-line">
                  <input type="password" class="form-control" id="confirm" name="confirm" size="40" required oninput="CheckPassword(this)" >
                </td>
              </tr>
              <tr class="no-line">
                <td class="no-line">
                  誕生日
                </td>
                <td class="no-line">
                  <select name="year" onchange="dateCheck('year', 'month', 'day')" required>
                      <option value="">--</option>
                      <option value="1920">1920</option>
                      <option value="1921">1921</option>
                      <option value="1922">1922</option>
                      <option value="1923">1923</option>
                      <option value="1924">1924</option>
                      <option value="1925">1925</option>
                      <option value="1926">1926</option>
                      <option value="1927">1927</option>
                      <option value="1928">1928</option>
                      <option value="1929">1929</option>
                      <option value="1930">1930</option>
                      <option value="1931">1931</option>
                      <option value="1932">1932</option>
                      <option value="1933">1933</option>
                      <option value="1934">1934</option>
                      <option value="1935">1935</option>
                      <option value="1936">1936</option>
                      <option value="1937">1937</option>
                      <option value="1938">1938</option>
                      <option value="1939">1939</option>
                      <option value="1940">1940</option>
                      <option value="1941">1941</option>
                      <option value="1942">1942</option>
                      <option value="1943">1943</option>
                      <option value="1944">1944</option>
                      <option value="1945">1945</option>
                      <option value="1946">1946</option>
                      <option value="1947">1947</option>
                      <option value="1948">1948</option>
                      <option value="1949">1949</option>
                      <option value="1950">1950</option>
                      <option value="1951">1951</option>
                      <option value="1952">1952</option>
                      <option value="1953">1953</option>
                      <option value="1954">1954</option>
                      <option value="1955">1955</option>
                      <option value="1956">1956</option>
                      <option value="1957">1957</option>
                      <option value="1958">1958</option>
                      <option value="1959">1959</option>
                      <option value="1960">1960</option>
                      <option value="1961">1961</option>
                      <option value="1962">1962</option>
                      <option value="1963">1963</option>
                      <option value="1964">1964</option>
                      <option value="1965">1965</option>
                      <option value="1966">1966</option>
                      <option value="1967">1967</option>
                      <option value="1968">1968</option>
                      <option value="1969">1969</option>
                      <option value="1970">1970</option>
                      <option value="1971">1971</option>
                      <option value="1972">1972</option>
                      <option value="1973">1973</option>
                      <option value="1974">1974</option>
                      <option value="1975">1975</option>
                      <option value="1976">1976</option>
                      <option value="1977">1977</option>
                      <option value="1978">1978</option>
                      <option value="1979">1979</option>
                      <option value="1980">1980</option>
                      <option value="1981">1981</option>
                      <option value="1982">1982</option>
                      <option value="1983">1983</option>
                      <option value="1984">1984</option>
                      <option value="1985">1985</option>
                      <option value="1986">1986</option>
                      <option value="1987">1987</option>
                      <option value="1988">1988</option>
                      <option value="1989">1989</option>
                      <option value="1990">1990</option>
                      <option value="1991">1991</option>
                      <option value="1992">1992</option>
                      <option value="1993">1993</option>
                      <option value="1994">1994</option>
                      <option value="1995">1995</option>
                      <option value="1996">1996</option>
                      <option value="1997">1997</option>
                      <option value="1998">1998</option>
                      <option value="1999">1999</option>
                      <option value="2000">2000</option>
                      <option value="2001">2001</option>
                      <option value="2002">2002</option>
                      <option value="2003">2003</option>
                      <option value="2004">2004</option>
                      <option value="2005">2005</option>
                      <option value="2006">2006</option>
                      <option value="2007">2007</option>
                      <option value="2008">2008</option>
                      <option value="2009">2009</option>
                      <option value="2010">2010</option>
                      <option value="2011">2011</option>
                      <option value="2012">2012</option>
                      <option value="2013">2013</option>
                      <option value="2014">2014</option>
                      <option value="2015">2015</option>
                      <option value="2016">2016</option>
                      <option value="2017">2017</option>
                      <option value="2018">2018</option>
                      <option value="2019">2019</option>
                  </select>年
                  <select name="month" onchange="dateCheck('year', 'month', 'day')" required>
                      <option value="">--</option>
                      <option value="1">1</option>
                      <option value="2">2</option>
                      <option value="3">3</option>
                      <option value="4">4</option>
                      <option value="5">5</option>
                      <option value="6">6</option>
                      <option value="7">7</option>
                      <option value="8">8</option>
                      <option value="9">9</option>
                      <option value="10">10</option>
                      <option value="11">11</option>
                      <option value="12">12</option>
                  </select>月
                  <select name="day" required>
                            <option value="">--</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                            <option value="13">13</option>
                            <option value="14">14</option>
                            <option value="15">15</option>
                            <option value="16">16</option>
                            <option value="17">17</option>
                            <option value="18">18</option>
                            <option value="19">19</option>
                            <option value="20">20</option>
                            <option value="21">21</option>
                            <option value="22">22</option>
                            <option value="23">23</option>
                            <option value="24">24</option>
                            <option value="25">25</option>
                            <option value="26">26</option>
                            <option value="27">27</option>
                            <option value="28">28</option>
                            <option value="29">29</option>
                            <option value="30">30</option>
                            <option value="31">31</option>
                    </select>日
                </td>
              </tr>
            </table>
            <input type="submit" name="" value="更新">
          </form>
          <%
            }
          %>

          <%
            if (p != null && p.equals("4")) {
              String mailStr = request.getParameter("mail");
              String idStr = request.getParameter("id");
              String passStr = request.getParameter("password");
              String usernameStr = request.getParameter("name");
              String yearStr = request.getParameter("year");
              String monthStr = request.getParameter("month");
              String dayStr = request.getParameter("day");
              String bday = yearStr+monthStr+dayStr;
          %>
          <h2>登録情報変更(確認)</h2>
          <h3>以下の内容で変更しますか？</h3>
          <table>
          <form action="./add_changecomplete.jsp" method="post">

            <input type="hidden" name="mail" value="<%= mailStr %>">
            <input type="hidden" name="id" value="<%= idStr %>">
            <input type="hidden" name="pass" value="<%= passStr %>">
            <input type="hidden" name="username" value="<%= usernameStr %>">
            <input type="hidden" name="bday" value="<%= bday %>">

            <tr>
              <td>
                <p>ユーザー名</p>
              </td>
              <td class="check">
                <%
                  if (list.get(0).get("kaiin_name").equals(usernameStr)) {
                %>
                    変更なし
                <%
                  }else{
                %>
                  <%= usernameStr %>
                <%
                  }
                %>
              </td>
            </tr>
            <tr>
              <td>
                <p>メールアドレス</p>
              </td>
              <td class="check">
                <%
                  if (list.get(0).get("kaiin_add").equals(mailStr)) {
                %>
                    変更なし
                <%
                  }else{
                %>
                  <%= mailStr %>
                <%
                  }
                %>
              </td>
            </tr>
            <tr>
              <td>
                <p>パスワード</p>
              </td>
              <td class="check">
                <%
                  if (list.get(0).get("kaiin_pass").equals(passStr)) {
                %>
                    変更なし
                <%
                  }else{
                %>
                  <%= "入力されたパスワード" %>
                <%
                  }
                %>
              </td>
            </tr>
            <tr>
              <td>
                <p>生年月日</p>
              </td>
              <td class="check">
                <%= yearStr+"年"+monthStr+"月"+dayStr+"日" %>
              </td>

            <tr class="no-line">
              <td  id="button" class="no-line" colspan="2">
                  <p>
                    <input type="submit" value="登録">
                    <button id="correction" type="button" href="javascript:void(0)" onclick="javascript:history.back()">修正</button>
                  </p>
              </td>
            </form>
              <td class="no-line">
              </td>
            </tr>

              <tr class="no-line">
                <td class="no-line"></td>
                <td class="no-line"></td>
              </tr>
            </table>
          <%
            }
          %>

          </main>
        </div>

        <footer>
          footer
        </footer>

      </div>

    </div>

          <ul class="circles">
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
          </ul>

  <script type="text/javascript" src="./js/main.js"></script>



  </body>
</html>
