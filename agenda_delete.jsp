<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%

  //文字コードの指定
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");

  //データベースに接続するために使用する変数宣言
  Connection con = null;
  Statement stmt = null;
  StringBuffer SQL = null;
  ResultSet rs = null;

String USER = "";


if (USER.equals("root")) {
  //ローカルのMySQLに接続する設定
  String PASSWORD = "";
  String URL ="jdbc:mysql://localhost/nhs90345db";
}else{
  //サーバーのMySQLに接続する設定
  String USER = "nhs90345";
  String PASSWORD = "b19931230";
  String URL ="jdbc:mysql://192.168.121.16/nhs90345db";
}

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

    //SQL文の発行（選択クエリ）
    SQL.append("select syou_no,syou_name,syou_pre,syou_msg,syou_icon,pre_name from syou_tbl,ken_tbl where syou_pre = pre_no order by syou_no");

    //SQL文の発行（選択クエリ）
    rs = stmt.executeQuery(SQL.toString());

    //抽出したデータを繰り返し処理で表示する。
    while(rs.next()){
        //DBのデータをHashMapへ格納する
      map = new HashMap<String,String>();
      map.put("syou_no",rs.getString("syou_no"));
      map.put("syou_name",rs.getString("syou_name"));
      map.put("pre_name",rs.getString("pre_name"));
      map.put("syou_msg",rs.getString("syou_msg"));
      map.put("syou_icon",rs.getString("syou_icon"));
      
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

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title></title>
</head>
<body>
<% if(ERMSG != null){ %>
予期せぬエラーが発生しました<br />
<%= ERMSG %>
<% } %>
<body>

<h1>商品一覧</h1>
<br><br>
  <%
    for(int i = 0; i < list.size(); i++){
  %>
</body>
</html>