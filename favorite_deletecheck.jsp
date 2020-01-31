<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%
  //文字コードの指定
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");

  String yotei_idStr[]  = request.getParameterValues("yotei_id");

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

    //SQL文の構築（選択クエリ）
    for (int i =0;yotei_idStr.length>i ;i++ ) {
    //SQLステートメントの作成（選択クエリ）
      SQL = new StringBuffer();
      SQL.append("select yotei_name from open_tbl where yotei_id = '");
      SQL.append(yotei_idStr[i]);
      SQL.append("'");
     System.out.println(SQL.toString());

    //SQL文の実行（選択クエリ）
    rs = stmt.executeQuery(SQL.toString());

        //検索データをHashMapへ格納する
        while(rs.next()){
      //DBのデータをHashMapへ格納する
          map = new HashMap<String,String>();
          map.put("yotei_name",rs.getString("yotei_name"));

          //1件分のデータ(HashMap)をArrayListへ追加
          list.add(map);
        }
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

    <h1>
      以下のお気に入りを削除しますか？
    </h1>

    <form method="post" action="./agenda_deletecomplete.jsp">
      <table id="list">
        <%
    		for(int i = 0; i < yotei_idStr.length; i++){
        %>
        <tr class="no-line">
            <input type="hidden" name="yotei_id" value="<%= yotei_idStr[i] %>">
            <td class="no-line" align="left" style="font-size:25px; font-weight:bold;;">・<%= list.get(i).get("yotei_name") %></td>
        <%}%>
        </tr>
        <tr class="no-line">
          <td class="no-line">
            <input type="submit" id="dbutton" value="削除">
            <button id="dbutton" type="button" href="javascript:void(0)" onclick="javascript:history.back()">修正</button>
          </td>
        </tr>
      </table>
    </form>
  <p id="back"><a href="./main.jsp">メイン画面に戻る</a></p>
</body>
</html>