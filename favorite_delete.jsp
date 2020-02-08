<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%
  //文字コードの指定
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");

  //入力データ受信
  String session_id = (String)session.getAttribute("login_id");
  String session_name = (String)session.getAttribute("login_name");

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
    SQL.append("select favorite_tbl.yotei_id,yotei_name from favorite_tbl,open_tbl where favorite_tbl.yotei_id = open_tbl.yotei_id and favorite_tbl.kaiin_id = '");
    SQL.append(session_id);
    SQL.append("'");
//      System.out.println(SQL.toString());

    //SQL文の実行（選択クエリ）
    rs = stmt.executeQuery(SQL.toString());

        //検索データをHashMapへ格納する
        while(rs.next()){
      //DBのデータをHashMapへ格納する
          map = new HashMap<String,String>();
          map.put("yotei_id",rs.getString("yotei_id"));
          map.put("yotei_name",rs.getString("yotei_name"));

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
%>
<html>

  <head>

    <meta charset="utf-8">

    <title>お気に入り削除</title>

    <link rel="stylesheet" type="text/css" href="./css/info.css">

  </head>

  <body>

  <form action="./favorite_deletecheck.jsp" method="post">

    <h1>
    <%= session_name %>さんのお気に入り一覧
    <% if (hit_flag == 1) {%>
    <table id="list">
      <tr class="no-line">
        <th></th>
        <th class="no-line" style="padding: 20px;">カレンダー名</td>
      </tr>
    <%
      for(int i = 0; i < list.size(); i++){
    %>
          <tr class="no-line">
            <td class="no-line"><input type="checkbox" name="yotei_id" value="<%= list.get(i).get("yotei_id") %>"></td>
            <td class="no-line" align="left" style="font-size:25px; font-weight:bold;;">・<%= list.get(i).get("yotei_name") %></td>
          </tr>
        <%}%>
    </table>
    <input type="submit" value="削除">
  </form>
  <% }else if (hit_flag == 0) {%>
    <br>お気に入りはありません。
  <% }%>
    <p id="back"><a href="./main.jsp">メイン画面に戻る</a></p>
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
      <li class="right"></li>
      <li class="right"></li>
      <li class="right"></li>
      <li class="right"></li>
      <li class="right"></li>
      <li class="right"></li>
      <li class="right"></li>
      <li class="right"></li>
      <li class="right"></li>
      <li class="right"></li>
    </ul>
</body>
</html>
