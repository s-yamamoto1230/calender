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
  String idStr  = request.getParameter("id");
  String keywordStr  = request.getParameter("keyword");
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

    if (!(idStr.equals(""))) {

    //SQL文の構築（選択クエリ）
    SQL.append("select yotei_id,yotei_name,kaiin_name from open_tbl,kaiin_tbl where open_tbl.kaiin_id = kaiin_tbl.kaiin_id and open_tbl.kaiin_id != '");
    SQL.append(session_id);
    SQL.append("'  and open_tbl.yotei_id = '");
    SQL.append(idStr);
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
          map.put("kaiin_name",rs.getString("kaiin_name"));

          //1件分のデータ(HashMap)をArrayListへ追加
          list.add(map);
        }
    }else if (!(keywordStr.equals(""))) {

    //SQL文の構築（選択クエリ）
    SQL.append("select yotei_id,yotei_name,kaiin_name from open_tbl,kaiin_tbl where open_tbl.kaiin_id = kaiin_tbl.kaiin_id and open_tbl.kaiin_id != '");
    SQL.append(session_id);
    SQL.append("' and open_tbl.yotei_name like '%");
    SQL.append(keywordStr);
    SQL.append("%'");
    System.out.println(SQL.toString());

    //SQL文の実行（選択クエリ）
    rs = stmt.executeQuery(SQL.toString());

        //検索データをHashMapへ格納する
        while(rs.next()){
      //DBのデータをHashMapへ格納する
          map = new HashMap<String,String>();
          map.put("yotei_id",rs.getString("yotei_id"));
          map.put("yotei_name",rs.getString("yotei_name"));
          map.put("kaiin_name",rs.getString("kaiin_name"));

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

    <title>検索結果</title>

    <link rel="stylesheet" type="text/css" href="./css/info.css">

  </head>

  <body>

    <h1>
    カレンダー検索一覧
  </h1>
  <%
   if (hit_flag == 1) {
  %>
    <table id="list">
      <tr class="no-line">
        <th></th>
        <th class="no-line" style="padding: 20px;">カレンダー名</td>
        <th class="no-line" style="padding: 20px;">作成者</td>
      </tr>
      <%
        for(int i=0; i<list.size();i++){
      %>
            <tr class="no-line">
              <td class="no-line">
                <form action="session_Issue.jsp" method="post">
                  <input type="hidden" name="yotei_id" value="<%= list.get(i).get("yotei_id") %>">
                  <input type="hidden" name="yotei_name" value="<%= list.get(i).get("yotei_name") %>">
                  <input type="submit" value="確認する">
                </form>
              </td>
              <td class="no-line" align="left" style="font-size:25px; font-weight:bold;;">
                <%= list.get(i).get("yotei_name") %>
              </td>
              <td class="no-line"><%= list.get(i).get("kaiin_name") %></td>
          </tr>
      <%
        }
      %>
  </table>
  <%
    }else if (hit_flag == 0) {
  %>
  該当するカレンダーはありません。
  <%
    }
  %>
    <p id="back"><a href="./agenda_search.jsp">検索画面に戻る</a></p>
</body>
</html>
