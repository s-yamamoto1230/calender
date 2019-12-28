<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%
  //文字コードの指定
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");

  //入力データ受信
  String kaiin_idStr  = request.getParameter("kaiin_id");
  String dayStr  = request.getParameter("day");
  String s_timeStr  = request.getParameter("s_time");

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
    SQL.append("select * from yotei_tbl where kaiin_id = '");
    SQL.append(kaiin_idStr);
    SQL.append("' and day ='");
    SQL.append(dayStr);
    SQL.append("' and s_time ='");
    SQL.append(s_timeStr);
    SQL.append("'");
//      System.out.println(SQL.toString());

    //SQL文の実行（選択クエリ）
    rs = stmt.executeQuery(SQL.toString());

    //入力したデータがデータベースに存在するか調べる
    if(rs.next()){  //存在する
      //ヒットフラグON
      hit_flag = 1;

        //検索データをHashMapへ格納する
        map = new HashMap<String,String>();
      map.put("kaiin_id",rs.getString("kaiin_id"));
      map.put("day",rs.getString("day"));
      map.put("s_time",rs.getString("s_time"));
      map.put("f_time",rs.getString("f_time"));
      map.put("place",rs.getString("place"));
      map.put("details",rs.getString("details"));
      map.put("importance",rs.getString("importance"));

      //1件分のデータ(HashMap)をArrayListへ追加
      list.add(map);
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

    <title>予定確認</title>

    <link rel="stylesheet" type="text/css" href="./css/info.css">

  </head>

  <body>

<table border="1">
  <tr>
    <td>
      <p>時間</p>
    </td>
    <td class="check">
      <%= list.get(0).get("s_time") %>～<%= list.get(0).get("f_time") %>
    </td>
  </tr>
  <tr>
    <td>
      <p>場所</p>
    </td>
    <td class="check">
      <%= list.get(0).get("place") %>
    </td>
  </tr>
  <tr>
    <td>
      <p>詳細</p>
    </td>
    <td class="check">
      <%= list.get(0).get("details") %>
    </td>
  </tr>
  <tr>
    <td>
      <p>重要</p>
    </td>
    <td class="check">
      <% if(list.get(0).get("importance").equals("1")) { %>
      めちゃくちゃ重要です。
      <%}else{%>
      そこまで重要ではありません。
      <% } %>
    </td>
  </tr>

</table>
<br>

</body>
</html>
