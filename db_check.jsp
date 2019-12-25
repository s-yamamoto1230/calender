<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
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

  //ローカルのMySQLに接続する設定
  String USER ="root";
  String PASSWORD = "";
  String URL ="jdbc:mysql://localhost/agenda";

  //サーバーのMySQLに接続する設定
//  String USER = "nhsxxxxx";
//  String PASSWORD = "byyyymmdd";
//  String URL ="jdbc:mysql://192.168.121.16/nhsxxxxxdb";

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
    SQL.append("select * from kaiin_tbl");

    //SQL文の発行（選択クエリ）
    rs = stmt.executeQuery(SQL.toString());

    //抽出したデータを繰り返し処理で表示する。
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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>『agenda　kaiin_tbl確認』</title>
</head>
<body>
<table border="1">
  <tr><th>会員id</th><th>会員名</th><th>メールアドレス</th><th>パスワード</th><th>誕生日</th></tr>

<%
  //ArryListからデータを取りだす
  for(int i=0; i<list.size(); i++){
%>
<tr>
  <td><%= list.get(i).get("kaiin_id") %></td>
  <td><%= list.get(i).get("kaiin_name") %></td>
  <td><%= list.get(i).get("kaiin_add") %></td>
  <td><%= list.get(i).get("kaiin_pass") %></td>
  <td><%= list.get(i).get("kaiin_bday") %></td>
</tr>
<%
  }
%>

</table>
<% if(ERMSG != null){ %>
予期せぬエラーが発生しました<br />
<%= ERMSG %>
<% }else{ %>
※エラーは発生しませんでした<br/>
<% } %>

</body>
</html>
