<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%
  //入力データ受信
  String session_id = (String)session.getAttribute("login_id");
  String mailStr = request.getParameter("mail");
  String idStr = request.getParameter("id");
  String passStr = request.getParameter("password");
  String usernameStr = request.getParameter("name");
  String yearStr = request.getParameter("year");
  String monthStr = request.getParameter("month");
  String dayStr = request.getParameter("day");
  String bday = yearStr+monthStr+dayStr;

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

%>

<html>

  <head>

    <meta charset="utf-8">

    <title>登録内容変更(確認)</title>

    <link rel="stylesheet" type="text/css" href="./css/info.css">

  </head>

  <body>


    <h1>登録内容変更（確認）</h1>
    <h2>以下の内容で変更しますか？</h2>

    <table>
    <form action="./add_changecomplete.jsp" method="post">

      <input type="hidden" name="mail" value="<%= mailStr %>">
      <input type="hidden" name="id" value="<%= idStr %>">
      <input type="hidden" name="pass" value="<%= passStr %>">
      <input type="hidden" name="username" value="<%= usernameStr %>">
      <input type="hidden" name="bday" value="<%= bday %>">

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

  </body>

</html>
