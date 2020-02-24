package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Date;
import java.util.Calendar;
import java.sql.*;
import java.util.*;
import java.util.HashMap;
import java.util.ArrayList;

public final class main_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");


    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

    String session_id = (String)session.getAttribute("login_id");
    String session_name = (String)session.getAttribute("login_name");
    String yotei_ids = (String)session.getAttribute("yotei_id");
    String p = request.getParameter("page_no");
    String del = request.getParameter("del");
    String create = request.getParameter("create");
    String change = request.getParameter("change");
    String check = request.getParameter("check");
    String cal_idStr  = request.getParameter("cal_id");
    String keywordStr  = request.getParameter("keyword");
    String del_idStr[]  = request.getParameterValues("del_id");
/*  	if (session_id == null) {
  		response.sendRedirect("index.jsp");
  	}*/
    if (yotei_ids != null) {
      session.removeAttribute("yotei_id");
      session.removeAttribute("yotei_name");
    }

    if(session_id == null){
      response.sendRedirect("index.jsp");

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
  if (p.equals("2-1")) {
    try{  // ロードに失敗したときのための例外処理
      // JDBCドライバのロード
      Class.forName(DRIVER).newInstance();

      // Connectionオブジェクトの作成
      con = DriverManager.getConnection(URL,USER,PASSWORD);

      //Statementオブジェクトの作成
      stmt = con.createStatement();

      //SQLステートメントの作成（選択クエリ）
      SQL = new StringBuffer();

      if (!(cal_idStr.equals(""))) {

      //SQL文の構築（選択クエリ）
      SQL.append("select yotei_id,yotei_name,open_set,kaiin_name from open_tbl,kaiin_tbl where open_tbl.kaiin_id = kaiin_tbl.kaiin_id and open_tbl.kaiin_id != '");
      SQL.append(session_id);
      SQL.append("'  and open_tbl.yotei_id = '");
      SQL.append(cal_idStr);
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
            map.put("open_set",rs.getString("open_set"));
            map.put("kaiin_name",rs.getString("kaiin_name"));

            //1件分のデータ(HashMap)をArrayListへ追加
            list.add(map);
          }
      }else if (!(keywordStr.equals(""))) {

      //SQL文の構築（選択クエリ）
      SQL.append("select yotei_id,yotei_name,open_set,kaiin_name from open_tbl,kaiin_tbl where open_tbl.kaiin_id = kaiin_tbl.kaiin_id and open_tbl.kaiin_id != '");
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
            map.put("open_set",rs.getString("open_set"));
            map.put("kaiin_name",rs.getString("kaiin_name"));

            //1件分のデータ(HashMap)をArrayListへ追加
            list.add(map);
          }
      }else if (keywordStr.equals("") && cal_idStr.equals("")) {
        //メインページへ遷移
				response.sendRedirect("main.jsp?page_no=2");
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
else if(check != null){
  if (check.equals("1") && del_idStr != null) {
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
      for(int i = 0; i < del_idStr.length; i++){
        SQL = new StringBuffer();
        SQL.append("select * from open_tbl where yotei_id = '");
        SQL.append(del_idStr[i]);
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
            map.put("open_set",rs.getString("open_set"));
            map.put("yotei_pass",rs.getString("yotei_pass"));
            map.put("yotei_writing",rs.getString("yotei_writing"));
            map.put("kaiin_id",rs.getString("kaiin_id"));
            //1件分のデータ(HashMap)をArrayListへ追加
            list.add(map);
          }
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
  else if (check.equals("1") && del_idStr == null) {
      //メインページへ遷移
      response.sendRedirect("main.jsp?page_no=1");
  }
  if (check.equals("2") && del_idStr != null) {
    try{  // ロードに失敗したときのための例外処理
      // JDBCドライバのロード
      Class.forName(DRIVER).newInstance();

      // Connectionオブジェクトの作成
      con = DriverManager.getConnection(URL,USER,PASSWORD);

      //Statementオブジェクトの作成
      stmt = con.createStatement();

      //SQL文の構築（選択クエリ）
      for (int i =0;del_idStr.length>i ;i++ ) {
      //SQLステートメントの作成（選択クエリ）
        SQL = new StringBuffer();
        SQL.append("select yotei_name,yotei_writing,kaiin_name from open_tbl,kaiin_tbl where kaiin_tbl.kaiin_id = open_tbl.kaiin_id and yotei_id = '");
        SQL.append(del_idStr[i]);
        SQL.append("'");
       System.out.println(SQL.toString());

      //SQL文の実行（選択クエリ）
      rs = stmt.executeQuery(SQL.toString());

          //検索データをHashMapへ格納する
          while(rs.next()){
        //DBのデータをHashMapへ格納する
            map = new HashMap<String,String>();
            map.put("yotei_name",rs.getString("yotei_name"));
            map.put("yotei_writing",rs.getString("yotei_writing"));
            map.put("kaiin_name",rs.getString("kaiin_name"));

            //1件分のデータ(HashMap)をArrayListへ追加
            list.add(map);
          }
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
  else if (check.equals("2") && del_idStr == null) {
      //メインページへ遷移
      response.sendRedirect("main.jsp");
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("  <meta charset=\"utf-8\">\r\n");
      out.write("\r\n");
      out.write("  <head>\r\n");
      out.write("    <title>メインページ</title>\r\n");
      out.write("  </head>\r\n");
      out.write("\r\n");
      out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/main.css\">\r\n");
      out.write("\r\n");
      out.write("    ");

      if (del != null){
        if (del.equals("1")) {
    
      out.write("\r\n");
      out.write("      <body onLoad=\"loadFavodel()\">\r\n");
      out.write("    ");

        }if (del.equals("2")) {
    
      out.write("\r\n");
      out.write("      <body onLoad=\"loadCalendardel()\">\r\n");
      out.write("    ");

        }
      }
      else if (create != null) {
        if (create.equals("1")) {
    
      out.write("\r\n");
      out.write("      <body onLoad=\"loadCalendarmake()\">\r\n");
      out.write("    ");

        }else{
    
      out.write("\r\n");
      out.write("      <body onLoad=\"loadCalendarNomake()\">\r\n");
      out.write("    ");

        }
      }
      else if (change != null) {
        if (change.equals("1")) {
    
      out.write("\r\n");
      out.write("      <body onLoad=\"loadChange()\">\r\n");
      out.write("    ");

        }
      }
      else if (check != null) {
        if (check.equals("3")) {
    
      out.write("\r\n");
      out.write("        <body onLoad=\"loadChange()\">\r\n");
      out.write("    ");

        }
      }
      else
      {
    
      out.write("\r\n");
      out.write("      <body>\r\n");
      out.write("    ");

      }
    
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <div id=\"contents\">\r\n");
      out.write("\r\n");
      out.write("      <div id=\"wrap\">\r\n");
      out.write("\r\n");
      out.write("        <header>\r\n");
      out.write("          <h1>\r\n");
      out.write("            ようこそ");
      out.print( session_name );
      out.write("さん\r\n");
      out.write("          </h1>\r\n");
      out.write("        </header>\r\n");
      out.write("\r\n");
      out.write("        <nav id=\"main_nav\">\r\n");
      out.write("          <ul>\r\n");
      out.write("            <li><a href=\"./main.jsp\">お気に入り一覧</a></li>\r\n");
      out.write("            <li><a href=\"./main.jsp?page_no=0\">公開カレンダー作成</a></li>\r\n");
      out.write("            <li><a href=\"./main.jsp?page_no=1\">作成した公開カレンダー</a></li>\r\n");
      out.write("            <li><a href=\"./main.jsp?page_no=2\">公開カレンダー検索</a></li>\r\n");
      out.write("            <li><a href=\"./main.jsp?page_no=3\">会員情報変更</a></li>\r\n");
      out.write("            <li><a href=\"#\" onclick=\"ShowAlert();\">ログアウト </a></li>\r\n");
      out.write("            <form name=\"logout_info\" action=\"./index.jsp\" method=\"post\">\r\n");
      out.write("              <input type=\"hidden\" name=\"logout\" value=\"logout\">\r\n");
      out.write("            </form>\r\n");
      out.write("          </ul>\r\n");
      out.write("        </nav>\r\n");
      out.write("        <div id=\"panel\">\r\n");
      out.write("          <main>\r\n");
      out.write("            ");

              if (p == null && check == null) {
            
      out.write("\r\n");
      out.write("              <h2>お気に入り一覧</h2>\r\n");
      out.write("              <h3>\r\n");
      out.write("                カレンダー名をクリックするとカレンダーが見られます。<br>\r\n");
      out.write("                チェックを付けて下の削除を押すとお気に入りを削除できます。\r\n");
      out.write("              </h3>\r\n");
      out.write("              ");

               if (hit_flag == 1) {
              
      out.write("\r\n");
      out.write("              <form action=\"./main.jsp?check=2\" method=\"post\">\r\n");
      out.write("                <table id=\"list\">\r\n");
      out.write("                  <tr class=\"no-line\">\r\n");
      out.write("                    <th class=\"no-line\" style=\"padding: 20px;\">カレンダー名</td>\r\n");
      out.write("                    <th class=\"no-line\" style=\"padding: 20px;\">書き込み</td>\r\n");
      out.write("                    <th class=\"no-line\" style=\"padding: 20px;\">作成者</td>\r\n");
      out.write("                    <th></th>\r\n");
      out.write("                  </tr>\r\n");
      out.write("                ");

                  for(int i=0; i<list.size();i++){
                
      out.write("\r\n");
      out.write("                  <tr class=\"no-line\">\r\n");
      out.write("                    <td class=\"no-line\" align=\"left\" style=\"font-size:25px; font-weight:bold;;\">\r\n");
      out.write("                      <a href=\"session_Issue.jsp?yotei_id=");
      out.print( list.get(i).get("yotei_id") );
      out.write("&yotei_name=");
      out.print( list.get(i).get("yotei_name") );
      out.write("\">\r\n");
      out.write("                        ");
      out.print( list.get(i).get("yotei_name") );
      out.write("\r\n");
      out.write("                      </a>\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td class=\"no-line\">\r\n");
      out.write("                      ");

                       if(list.get(i).get("yotei_writing").equals("1")) {
                      
      out.write("\r\n");
      out.write("                        許可\r\n");
      out.write("                      ");

                        }else{
                      
      out.write("\r\n");
      out.write("                        禁止\r\n");
      out.write("                      ");

                        }
                      
      out.write("\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td class=\"no-line\">");
      out.print( list.get(i).get("kaiin_name") );
      out.write("</td>\r\n");
      out.write("                    <td class=\"no-line\">\r\n");
      out.write("                        <input type=\"checkbox\" name=\"del_id\" value=\"");
      out.print( list.get(i).get("yotei_id") );
      out.write("\">\r\n");
      out.write("                    </td>\r\n");
      out.write("                  </tr>\r\n");
      out.write("                  ");

                    }
                  
      out.write("\r\n");
      out.write("                </table>\r\n");
      out.write("                <input type=\"submit\" value=\"削除\">\r\n");
      out.write("                </form>\r\n");
      out.write("                ");

                  }else if (hit_flag == 0) {
                
      out.write("\r\n");
      out.write("                  <p class=\"not\">お気に入りはありません。</p>\r\n");
      out.write("                ");

                  }
                
      out.write("\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("\r\n");
      out.write("            ");

              if (p != null && p.equals("0")) {
            
      out.write("\r\n");
      out.write("            <h2>カレンダー新規作成</h2>\r\n");
      out.write("            <table>\r\n");
      out.write("              <form  name=\"form\" action=\"./agenda_makecomplete.jsp\" method=\"post\" onsubmit=\"ShowCalendarmake()\">\r\n");
      out.write("                <tr>\r\n");
      out.write("                  <td class=\"title\">\r\n");
      out.write("                    <p>ID</p>\r\n");
      out.write("                  </td>\r\n");
      out.write("                  <td>\r\n");
      out.write("                    <p><input type=\"text\" name=\"id\" id=\"id\" pattern=\"^[0-9a-z]+$\" size=\"25\" required></p>\r\n");
      out.write("                    <p id=\"notice-input-text-0\" style=\"display: none; color: red;\"></p>\r\n");
      out.write("                    <p class=\"alert\">※半角英数字15文字以下</p>\r\n");
      out.write("                  </td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                <tr>\r\n");
      out.write("                  <td class=\"title\">\r\n");
      out.write("                    <p>タイトル</p>\r\n");
      out.write("                  </td>\r\n");
      out.write("                  <td>\r\n");
      out.write("                    <input type=\"text\" name=\"title\" size=\"25\" required>\r\n");
      out.write("                  </td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                <tr>\r\n");
      out.write("                  <td class=\"title\">\r\n");
      out.write("                    <p>公開設定</p>\r\n");
      out.write("                  </td>\r\n");
      out.write("                  <td>\r\n");
      out.write("                    <label><input type=\"radio\" name=\"open\"  value=\"1\" onClick=\"openflg0(this.checked);\" checked>全員に公開</label>\r\n");
      out.write("                    <label><input type=\"radio\" name=\"open\"  value=\"2\" onClick=\"openflg1(this.checked);\">特定の人にのみ公開</label>\r\n");
      out.write("                  </td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                <div class=\"form-group\">\r\n");
      out.write("                  <tr>\r\n");
      out.write("                    <td class=\"title\">\r\n");
      out.write("                      <p>パスワード</p>\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td>\r\n");
      out.write("                      <br><div class=\"form-group\">\r\n");
      out.write("                        <p><input type=\"password\" class=\"form-control\" id=\"password\" name=\"password\" size=\"25\" pattern=\"^[0-9a-z]+$\" disabled=\"disabled\"> </p>\r\n");
      out.write("                      </div>\r\n");
      out.write("                    </td>\r\n");
      out.write("                  </tr>\r\n");
      out.write("                  <tr>\r\n");
      out.write("                    <td class=\"title\">\r\n");
      out.write("                      パスワード（確認）\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td>\r\n");
      out.write("                      <div class=\"form-group\">\r\n");
      out.write("                        <p><input type=\"password\" class=\"form-control\" id=\"confirm\" name=\"confirm\" size=\"25\" oninput=\"CheckPassword(this)\" disabled=\"disabled\" required></p>\r\n");
      out.write("                      </div>\r\n");
      out.write("                    </td>\r\n");
      out.write("                  </tr>\r\n");
      out.write("                </div>\r\n");
      out.write("                <tr>\r\n");
      out.write("                  <td class=\"title\">\r\n");
      out.write("                    <p>他人の書き込み</p>\r\n");
      out.write("                  </td>\r\n");
      out.write("                  <td>\r\n");
      out.write("                    <label><input type=\"radio\" name=\"permission\" value=\"1\">許可</label>\r\n");
      out.write("                    <label><input type=\"radio\" name=\"permission\" value=\"2\" checked>禁止</label>\r\n");
      out.write("                  </td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                <tr class=\"no-line\">\r\n");
      out.write("                  <td class=\"no-line\" id=\"button\" colspan=\"2\">\r\n");
      out.write("                      <p>\r\n");
      out.write("                        <input type=\"submit\" id=\"submit\" value=\"登録\">\r\n");
      out.write("                      </p>\r\n");
      out.write("                  </td>\r\n");
      out.write("                </tr>\r\n");
      out.write("              </form>\r\n");
      out.write("            </table>\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("\r\n");
      out.write("            ");

              if (p != null && p.equals("1")) {
            
      out.write("\r\n");
      out.write("            <form action=\"./main.jsp?check=1\" method=\"post\">\r\n");
      out.write("\r\n");
      out.write("              <h2>作成したカレンダー一覧</h2>\r\n");
      out.write("              <h3>\r\n");
      out.write("                カレンダー名をクリックするとカレンダーが見られます。<br>\r\n");
      out.write("                チェックを付けて下の削除を押すとお気に入りを削除できます。\r\n");
      out.write("              </h3>\r\n");
      out.write("            ");

             if (hit_flag == 1) {
            
      out.write("\r\n");
      out.write("              <table id=\"list\">\r\n");
      out.write("                <tr class=\"no-line\">\r\n");
      out.write("                  <th class=\"no-line\" style=\"padding: 20px;\">カレンダーID</td>\r\n");
      out.write("                  <th class=\"no-line\" style=\"padding: 20px;\">カレンダー名</td>\r\n");
      out.write("                  <th class=\"no-line\" style=\"padding: 20px;\">公開設定</td>\r\n");
      out.write("                  <th class=\"no-line\" style=\"padding: 20px;\">パスワード</td>\r\n");
      out.write("                  <th class=\"no-line\" style=\"padding: 20px;\">書き込み設定</td>\r\n");
      out.write("                  <th></th>\r\n");
      out.write("                </tr>\r\n");
      out.write("              ");

                for(int i=0; i<list.size();i++){
              
      out.write("\r\n");
      out.write("                <tr class=\"no-line\">\r\n");
      out.write("                  <td class=\"no-line\" align=\"left\" style=\"font-size:25px; font-weight:bold;;\">\r\n");
      out.write("                    <a href=\"session_Issue.jsp?yotei_id=");
      out.print( list.get(i).get("yotei_id") );
      out.write("&yotei_name=");
      out.print( list.get(i).get("yotei_name") );
      out.write("\">\r\n");
      out.write("                      ");
      out.print( list.get(i).get("yotei_id") );
      out.write("\r\n");
      out.write("                    </a>\r\n");
      out.write("                  </td>\r\n");
      out.write("                  <td class=\"no-line\">");
      out.print( list.get(i).get("yotei_name") );
      out.write("</td>\r\n");
      out.write("                  <td class=\"no-line\">\r\n");
      out.write("                    ");
if (list.get(i).get("open_set").equals("1")) { 
      out.write("\r\n");
      out.write("                    公開\r\n");
      out.write("                    ");

                      }else{
                    
      out.write("\r\n");
      out.write("                      非公開\r\n");
      out.write("                    ");

                      }
                    
      out.write("\r\n");
      out.write("                  </td>\r\n");
      out.write("                  <td class=\"no-line\">\r\n");
      out.write("                    ");
      out.print( list.get(i).get("yotei_pass") );
      out.write("\r\n");
      out.write("                  </td>\r\n");
      out.write("                  <td class=\"no-line\">\r\n");
      out.write("                    ");

                      if(list.get(i).get("yotei_writing").equals("1")) {
                    
      out.write("\r\n");
      out.write("                      許可\r\n");
      out.write("                    ");

                      }else{
                    
      out.write("\r\n");
      out.write("                      禁止\r\n");
      out.write("                    ");

                      }
                    
      out.write("\r\n");
      out.write("                  </td>\r\n");
      out.write("                    <td class=\"no-line\">\r\n");
      out.write("                        <input type=\"checkbox\" name=\"del_id\" value=\"");
      out.print( list.get(i).get("yotei_id") );
      out.write("\">\r\n");
      out.write("                    </td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                ");

                  }
                
      out.write("\r\n");
      out.write("              </table>\r\n");
      out.write("              <input type=\"submit\" value=\"削除\">\r\n");
      out.write("              </form>\r\n");
      out.write("          ");

            }else if (hit_flag == 0) {
          
      out.write("\r\n");
      out.write("            <p class=\"not\">作成した予定はありません。</p>\r\n");
      out.write("          ");

            }
          
      out.write("\r\n");
      out.write("          ");

            }
          
      out.write("\r\n");
      out.write("\r\n");
      out.write("          ");

            if (p != null && p.equals("2")) {
          
      out.write("\r\n");
      out.write("          <h2>カレンダー検索</h2>\r\n");
      out.write("          <p id=\"Des\">\r\n");
      out.write("            カレンダーIDかキーワードを入力して検索ボタンを押してください。<br>カレンダーを検索します。<br>(両方入力するとカレンダーIDを優先して検索します)\r\n");
      out.write("          </p>\r\n");
      out.write("           <form action=\"./main.jsp?page_no=2-1\" method=\"post\">\r\n");
      out.write("            <table>\r\n");
      out.write("              <tr>\r\n");
      out.write("                <td  class=\"title\">\r\n");
      out.write("                  <p>カレンダーID</p>\r\n");
      out.write("                </td>\r\n");
      out.write("                <td class=\"no-line\">\r\n");
      out.write("                  <input type=\"text\" name=\"cal_id\" size=\"25\" class=\"text\">\r\n");
      out.write("                </td>\r\n");
      out.write("              </tr>\r\n");
      out.write("              <tr>\r\n");
      out.write("                <td  class=\"title\">\r\n");
      out.write("                  <p>キーワード</p>\r\n");
      out.write("                </td>\r\n");
      out.write("                <td class=\"no-line\">\r\n");
      out.write("                  <input type=\"text\" name=\"keyword\" size=\"25\" class=\"text\">\r\n");
      out.write("                </td>\r\n");
      out.write("\r\n");
      out.write("              <tr class=\"no-line\">\r\n");
      out.write("                <td class=\"no-line\" id=\"button\" colspan=\"2\">\r\n");
      out.write("                  <input type=\"submit\" value=\"検索\" id=\"button\">\r\n");
      out.write("                </td>\r\n");
      out.write("              </form>\r\n");
      out.write("              </tr>\r\n");
      out.write("            </table>\r\n");
      out.write("          ");

            }
          
      out.write("\r\n");
      out.write("\r\n");
      out.write("          ");

            if (p != null && p.equals("2-1")) {
          
      out.write("\r\n");
      out.write("          <h2>\r\n");
      out.write("          カレンダー検索一覧\r\n");
      out.write("          </h2>\r\n");
      out.write("          ");

           if (hit_flag == 1) {
          
      out.write("\r\n");
      out.write("            <table id=\"list\">\r\n");
      out.write("              <tr class=\"no-line\">\r\n");
      out.write("                <th class=\"no-line\" style=\"padding: 20px;\">カレンダー名</td>\r\n");
      out.write("                <th class=\"no-line\" style=\"padding: 20px;\">作成者</td>\r\n");
      out.write("                <th class=\"no-line\" style=\"padding: 20px;\">公開設定</td>\r\n");
      out.write("                <th></th>\r\n");
      out.write("              </tr>\r\n");
      out.write("              ");

                for(int i=0; i<list.size();i++){
              
      out.write("\r\n");
      out.write("                    <tr class=\"no-line\">\r\n");
      out.write("                      <td class=\"no-line\" align=\"left\" style=\"font-size:25px; font-weight:bold;;\">\r\n");
      out.write("                        ");
      out.print( list.get(i).get("yotei_name") );
      out.write("\r\n");
      out.write("                      </td>\r\n");
      out.write("                      <td class=\"no-line\">");
      out.print( list.get(i).get("kaiin_name") );
      out.write("</td>\r\n");
      out.write("                      ");
 if(list.get(i).get("open_set").equals("1")) { 
      out.write("\r\n");
      out.write("                      <td class=\"no-line\">全員に公開</td>\r\n");
      out.write("                      ");
}else{
      out.write("\r\n");
      out.write("                      <td class=\"no-line\">特定の人にのみ公開</td>\r\n");
      out.write("                      ");
 } 
      out.write("\r\n");
      out.write("                      <td class=\"no-line\">\r\n");
      out.write("                        <form action=\"session_Issue.jsp\" method=\"post\">\r\n");
      out.write("                          <input type=\"hidden\" name=\"yotei_id\" value=\"");
      out.print( list.get(i).get("yotei_id") );
      out.write("\">\r\n");
      out.write("                          <input type=\"hidden\" name=\"yotei_name\" value=\"");
      out.print( list.get(i).get("yotei_name") );
      out.write("\">\r\n");
      out.write("                          <input type=\"hidden\" name=\"open_set\" value=\"");
      out.print( list.get(i).get("open_set") );
      out.write("\">\r\n");
      out.write("                          <input id=\"non\" type=\"submit\" value=\"確認する\">\r\n");
      out.write("                        </form>\r\n");
      out.write("                      </td>\r\n");
      out.write("                  </tr>\r\n");
      out.write("              ");

                }
              
      out.write("\r\n");
      out.write("            </table>\r\n");
      out.write("          ");

            }else if (hit_flag == 0) {
          
      out.write("\r\n");
      out.write("            <p class=\"not\">該当するカレンダーはありません。</p>\r\n");
      out.write("            <p id=\"a_link\"><a id=\"link\" href=\"./main.jsp?page_no=2\">メイン画面に戻る</a></p>\r\n");
      out.write("            ");

              }
            
      out.write("\r\n");
      out.write("          ");

            }
          
      out.write("\r\n");
      out.write("\r\n");
      out.write("          ");

            if (p != null && p.equals("3")) {
              String kaiin_bday = (String)list.get(0).get("kaiin_bday");
              int bday =Integer.parseInt(kaiin_bday);
              int y = bday /10000;
              int m =(bday-y*10000)/100;
              int d = (bday-(y*10000+m*100));
              String my_year = String.valueOf(y-1919);
              String my_month = String.valueOf(m);
              String my_day = String.valueOf(d);
              if (my_month.equals("1") || my_month.equals("2") || my_month.equals("3") || my_month.equals("4") || my_month.equals("5") || my_month.equals("6") || my_month.equals("7") || my_month.equals("8") || my_month.equals("9")) {
                my_month = "0"+my_month;
              }
              if (my_day.equals("1") || my_day.equals("2") || my_day.equals("3") || my_day.equals("4") || my_day.equals("5") || my_day.equals("6") || my_day.equals("7") || my_day.equals("8") || my_day.equals("9")) {
                my_day = "0"+my_day;
              }
          
      out.write("\r\n");
      out.write("          <h2>登録情報変更</h2>\r\n");
      out.write("          <form name=\"frm\" action=\"main.jsp?page_no=4\" method=\"post\">\r\n");
      out.write("            <table id=\"change\">\r\n");
      out.write("              <tr class=\"no-line\">\r\n");
      out.write("                <td class=\"no-line\">\r\n");
      out.write("                  会員ID\r\n");
      out.write("                </td>\r\n");
      out.write("                <td class=\"no-line\">\r\n");
      out.write("                  ");
      out.print( list.get(0).get("kaiin_id") );
      out.write("\r\n");
      out.write("                  <input type=\"hidden\" name=\"id\" value=\"");
      out.print( list.get(0).get("kaiin_id") );
      out.write("\">\r\n");
      out.write("                </td>\r\n");
      out.write("              </tr>\r\n");
      out.write("              <tr class=\"no-line\">\r\n");
      out.write("                <td class=\"no-line\">\r\n");
      out.write("                  会員名\r\n");
      out.write("                </td>\r\n");
      out.write("                <td class=\"no-line\">\r\n");
      out.write("                  <input type=\"text\" name=\"name\" size=\"40\" value=\"");
      out.print( list.get(0).get("kaiin_name") );
      out.write("\" required>\r\n");
      out.write("                </td>\r\n");
      out.write("              </tr>\r\n");
      out.write("              <tr class=\"no-line\">\r\n");
      out.write("                <td class=\"no-line\">\r\n");
      out.write("                  メールアドレス\r\n");
      out.write("                </td>\r\n");
      out.write("                <td class=\"no-line\">\r\n");
      out.write("                  <input type=\"text\" name=\"mail\" size=\"40\" value=\"");
      out.print( list.get(0).get("kaiin_add") );
      out.write("\" required>\r\n");
      out.write("                </td>\r\n");
      out.write("              </tr>\r\n");
      out.write("              <tr class=\"no-line\">\r\n");
      out.write("                <td class=\"no-line\">\r\n");
      out.write("                  パスワード\r\n");
      out.write("                </td>\r\n");
      out.write("                <td class=\"no-line\">\r\n");
      out.write("                  <input type=\"password\" class=\"form-control\" id=\"password\" name=\"password\" size=\"40\" pattern=\"^[0-9a-z]+$\" required value=\"");
      out.print( list.get(0).get("kaiin_pass") );
      out.write("\">\r\n");
      out.write("                </td>\r\n");
      out.write("              </tr>\r\n");
      out.write("              <tr class=\"no-line\">\r\n");
      out.write("                <td class=\"no-line\">\r\n");
      out.write("                  パスワード確認\r\n");
      out.write("                </td>\r\n");
      out.write("                <td class=\"no-line\">\r\n");
      out.write("                  <input type=\"password\" class=\"form-control\" id=\"confirm\" name=\"confirm\" size=\"40\" required oninput=\"CheckPassword(this)\" >\r\n");
      out.write("                </td>\r\n");
      out.write("              </tr>\r\n");
      out.write("              <tr class=\"no-line\">\r\n");
      out.write("                <td class=\"no-line\">\r\n");
      out.write("                  誕生日\r\n");
      out.write("                </td>\r\n");
      out.write("                <td class=\"no-line\">\r\n");
      out.write("                  <select name=\"year\" onchange=\"dateCheck('year', 'month', 'day')\" required>\r\n");
      out.write("                      <option value=\"\">--</option>\r\n");
      out.write("                      <option value=\"1920\">1920</option>\r\n");
      out.write("                      <option value=\"1921\">1921</option>\r\n");
      out.write("                      <option value=\"1922\">1922</option>\r\n");
      out.write("                      <option value=\"1923\">1923</option>\r\n");
      out.write("                      <option value=\"1924\">1924</option>\r\n");
      out.write("                      <option value=\"1925\">1925</option>\r\n");
      out.write("                      <option value=\"1926\">1926</option>\r\n");
      out.write("                      <option value=\"1927\">1927</option>\r\n");
      out.write("                      <option value=\"1928\">1928</option>\r\n");
      out.write("                      <option value=\"1929\">1929</option>\r\n");
      out.write("                      <option value=\"1930\">1930</option>\r\n");
      out.write("                      <option value=\"1931\">1931</option>\r\n");
      out.write("                      <option value=\"1932\">1932</option>\r\n");
      out.write("                      <option value=\"1933\">1933</option>\r\n");
      out.write("                      <option value=\"1934\">1934</option>\r\n");
      out.write("                      <option value=\"1935\">1935</option>\r\n");
      out.write("                      <option value=\"1936\">1936</option>\r\n");
      out.write("                      <option value=\"1937\">1937</option>\r\n");
      out.write("                      <option value=\"1938\">1938</option>\r\n");
      out.write("                      <option value=\"1939\">1939</option>\r\n");
      out.write("                      <option value=\"1940\">1940</option>\r\n");
      out.write("                      <option value=\"1941\">1941</option>\r\n");
      out.write("                      <option value=\"1942\">1942</option>\r\n");
      out.write("                      <option value=\"1943\">1943</option>\r\n");
      out.write("                      <option value=\"1944\">1944</option>\r\n");
      out.write("                      <option value=\"1945\">1945</option>\r\n");
      out.write("                      <option value=\"1946\">1946</option>\r\n");
      out.write("                      <option value=\"1947\">1947</option>\r\n");
      out.write("                      <option value=\"1948\">1948</option>\r\n");
      out.write("                      <option value=\"1949\">1949</option>\r\n");
      out.write("                      <option value=\"1950\">1950</option>\r\n");
      out.write("                      <option value=\"1951\">1951</option>\r\n");
      out.write("                      <option value=\"1952\">1952</option>\r\n");
      out.write("                      <option value=\"1953\">1953</option>\r\n");
      out.write("                      <option value=\"1954\">1954</option>\r\n");
      out.write("                      <option value=\"1955\">1955</option>\r\n");
      out.write("                      <option value=\"1956\">1956</option>\r\n");
      out.write("                      <option value=\"1957\">1957</option>\r\n");
      out.write("                      <option value=\"1958\">1958</option>\r\n");
      out.write("                      <option value=\"1959\">1959</option>\r\n");
      out.write("                      <option value=\"1960\">1960</option>\r\n");
      out.write("                      <option value=\"1961\">1961</option>\r\n");
      out.write("                      <option value=\"1962\">1962</option>\r\n");
      out.write("                      <option value=\"1963\">1963</option>\r\n");
      out.write("                      <option value=\"1964\">1964</option>\r\n");
      out.write("                      <option value=\"1965\">1965</option>\r\n");
      out.write("                      <option value=\"1966\">1966</option>\r\n");
      out.write("                      <option value=\"1967\">1967</option>\r\n");
      out.write("                      <option value=\"1968\">1968</option>\r\n");
      out.write("                      <option value=\"1969\">1969</option>\r\n");
      out.write("                      <option value=\"1970\">1970</option>\r\n");
      out.write("                      <option value=\"1971\">1971</option>\r\n");
      out.write("                      <option value=\"1972\">1972</option>\r\n");
      out.write("                      <option value=\"1973\">1973</option>\r\n");
      out.write("                      <option value=\"1974\">1974</option>\r\n");
      out.write("                      <option value=\"1975\">1975</option>\r\n");
      out.write("                      <option value=\"1976\">1976</option>\r\n");
      out.write("                      <option value=\"1977\">1977</option>\r\n");
      out.write("                      <option value=\"1978\">1978</option>\r\n");
      out.write("                      <option value=\"1979\">1979</option>\r\n");
      out.write("                      <option value=\"1980\">1980</option>\r\n");
      out.write("                      <option value=\"1981\">1981</option>\r\n");
      out.write("                      <option value=\"1982\">1982</option>\r\n");
      out.write("                      <option value=\"1983\">1983</option>\r\n");
      out.write("                      <option value=\"1984\">1984</option>\r\n");
      out.write("                      <option value=\"1985\">1985</option>\r\n");
      out.write("                      <option value=\"1986\">1986</option>\r\n");
      out.write("                      <option value=\"1987\">1987</option>\r\n");
      out.write("                      <option value=\"1988\">1988</option>\r\n");
      out.write("                      <option value=\"1989\">1989</option>\r\n");
      out.write("                      <option value=\"1990\">1990</option>\r\n");
      out.write("                      <option value=\"1991\">1991</option>\r\n");
      out.write("                      <option value=\"1992\">1992</option>\r\n");
      out.write("                      <option value=\"1993\">1993</option>\r\n");
      out.write("                      <option value=\"1994\">1994</option>\r\n");
      out.write("                      <option value=\"1995\">1995</option>\r\n");
      out.write("                      <option value=\"1996\">1996</option>\r\n");
      out.write("                      <option value=\"1997\">1997</option>\r\n");
      out.write("                      <option value=\"1998\">1998</option>\r\n");
      out.write("                      <option value=\"1999\">1999</option>\r\n");
      out.write("                      <option value=\"2000\">2000</option>\r\n");
      out.write("                      <option value=\"2001\">2001</option>\r\n");
      out.write("                      <option value=\"2002\">2002</option>\r\n");
      out.write("                      <option value=\"2003\">2003</option>\r\n");
      out.write("                      <option value=\"2004\">2004</option>\r\n");
      out.write("                      <option value=\"2005\">2005</option>\r\n");
      out.write("                      <option value=\"2006\">2006</option>\r\n");
      out.write("                      <option value=\"2007\">2007</option>\r\n");
      out.write("                      <option value=\"2008\">2008</option>\r\n");
      out.write("                      <option value=\"2009\">2009</option>\r\n");
      out.write("                      <option value=\"2010\">2010</option>\r\n");
      out.write("                      <option value=\"2011\">2011</option>\r\n");
      out.write("                      <option value=\"2012\">2012</option>\r\n");
      out.write("                      <option value=\"2013\">2013</option>\r\n");
      out.write("                      <option value=\"2014\">2014</option>\r\n");
      out.write("                      <option value=\"2015\">2015</option>\r\n");
      out.write("                      <option value=\"2016\">2016</option>\r\n");
      out.write("                      <option value=\"2017\">2017</option>\r\n");
      out.write("                      <option value=\"2018\">2018</option>\r\n");
      out.write("                      <option value=\"2019\">2019</option>\r\n");
      out.write("                  </select>年\r\n");
      out.write("                  <select name=\"month\" onchange=\"dateCheck('year', 'month', 'day')\" required>\r\n");
      out.write("                      <option value=\"\">--</option>\r\n");
      out.write("                      <option value=\"1\">01</option>\r\n");
      out.write("                      <option value=\"2\">02</option>\r\n");
      out.write("                      <option value=\"3\">03</option>\r\n");
      out.write("                      <option value=\"4\">04</option>\r\n");
      out.write("                      <option value=\"5\">05</option>\r\n");
      out.write("                      <option value=\"6\">06</option>\r\n");
      out.write("                      <option value=\"7\">07</option>\r\n");
      out.write("                      <option value=\"8\">08</option>\r\n");
      out.write("                      <option value=\"9\">09</option>\r\n");
      out.write("                      <option value=\"10\">10</option>\r\n");
      out.write("                      <option value=\"11\">11</option>\r\n");
      out.write("                      <option value=\"12\">12</option>\r\n");
      out.write("                  </select>月\r\n");
      out.write("                  <select name=\"day\" required>\r\n");
      out.write("                            <option value=\"\">--</option>\r\n");
      out.write("                            <option value=\"1\">01</option>\r\n");
      out.write("                            <option value=\"2\">02</option>\r\n");
      out.write("                            <option value=\"3\">03</option>\r\n");
      out.write("                            <option value=\"4\">04</option>\r\n");
      out.write("                            <option value=\"5\">05</option>\r\n");
      out.write("                            <option value=\"6\">06</option>\r\n");
      out.write("                            <option value=\"7\">07</option>\r\n");
      out.write("                            <option value=\"8\">08</option>\r\n");
      out.write("                            <option value=\"9\">09</option>\r\n");
      out.write("                            <option value=\"10\">10</option>\r\n");
      out.write("                            <option value=\"11\">11</option>\r\n");
      out.write("                            <option value=\"12\">12</option>\r\n");
      out.write("                            <option value=\"13\">13</option>\r\n");
      out.write("                            <option value=\"14\">14</option>\r\n");
      out.write("                            <option value=\"15\">15</option>\r\n");
      out.write("                            <option value=\"16\">16</option>\r\n");
      out.write("                            <option value=\"17\">17</option>\r\n");
      out.write("                            <option value=\"18\">18</option>\r\n");
      out.write("                            <option value=\"19\">19</option>\r\n");
      out.write("                            <option value=\"20\">20</option>\r\n");
      out.write("                            <option value=\"21\">21</option>\r\n");
      out.write("                            <option value=\"22\">22</option>\r\n");
      out.write("                            <option value=\"23\">23</option>\r\n");
      out.write("                            <option value=\"24\">24</option>\r\n");
      out.write("                            <option value=\"25\">25</option>\r\n");
      out.write("                            <option value=\"26\">26</option>\r\n");
      out.write("                            <option value=\"27\">27</option>\r\n");
      out.write("                            <option value=\"28\">28</option>\r\n");
      out.write("                            <option value=\"29\">29</option>\r\n");
      out.write("                            <option value=\"30\">30</option>\r\n");
      out.write("                            <option value=\"31\">31</option>\r\n");
      out.write("                    </select>日\r\n");
      out.write("                </td>\r\n");
      out.write("              </tr>\r\n");
      out.write("            </table>\r\n");
      out.write("            <input type=\"submit\" name=\"\" value=\"更新\">\r\n");
      out.write("          </form>\r\n");
      out.write("          <script>\r\n");
      out.write("            document.frm.year.selectedIndex=");
      out.print(my_year);
      out.write(";\r\n");
      out.write("            document.frm.month.selectedIndex=");
      out.print(my_month);
      out.write(";\r\n");
      out.write("            document.frm.day.selectedIndex=");
      out.print(my_day);
      out.write(";\r\n");
      out.write("          </script>\r\n");
      out.write("          ");

            }
          
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("          ");

            if (p != null && p.equals("4")) {
              String mailStr = request.getParameter("mail");
              String idStr = request.getParameter("id");
              String passStr = request.getParameter("password");
              String usernameStr = request.getParameter("name");
              String yearStr = request.getParameter("year");
              String monthStr = request.getParameter("month");
              String dayStr = request.getParameter("day");
              if (monthStr.equals("1") || monthStr.equals("2") || monthStr.equals("3") || monthStr.equals("4") || monthStr.equals("5") || monthStr.equals("6") || monthStr.equals("7") || monthStr.equals("8") || monthStr.equals("9")) {
                monthStr = "0"+monthStr;
              }
              if (dayStr.equals("1") || dayStr.equals("2") || dayStr.equals("3") || dayStr.equals("4") || dayStr.equals("5") || dayStr.equals("6") || dayStr.equals("7") || dayStr.equals("8") || dayStr.equals("9")) {
                dayStr = "0"+dayStr;
              }
              String bday = yearStr+monthStr+dayStr;
          
      out.write("\r\n");
      out.write("          <h2>登録情報変更(確認)</h2>\r\n");
      out.write("          <h3>以下の内容で変更しますか？</h3>\r\n");
      out.write("          <table>\r\n");
      out.write("          <form action=\"./add_changecomplete.jsp\" method=\"post\">\r\n");
      out.write("\r\n");
      out.write("            <input type=\"hidden\" name=\"mail\" value=\"");
      out.print( mailStr );
      out.write("\">\r\n");
      out.write("            <input type=\"hidden\" name=\"id\" value=\"");
      out.print( idStr );
      out.write("\">\r\n");
      out.write("            <input type=\"hidden\" name=\"pass\" value=\"");
      out.print( passStr );
      out.write("\">\r\n");
      out.write("            <input type=\"hidden\" name=\"username\" value=\"");
      out.print( usernameStr );
      out.write("\">\r\n");
      out.write("            <input type=\"hidden\" name=\"bday\" value=\"");
      out.print( bday );
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("            <tr>\r\n");
      out.write("              <td>\r\n");
      out.write("                <p>ユーザー名</p>\r\n");
      out.write("              </td>\r\n");
      out.write("              <td class=\"check\">\r\n");
      out.write("                ");

                  if (list.get(0).get("kaiin_name").equals(usernameStr)) {
                
      out.write("\r\n");
      out.write("                    変更なし\r\n");
      out.write("                ");

                  }else{
                
      out.write("\r\n");
      out.write("                  ");
      out.print( usernameStr );
      out.write("\r\n");
      out.write("                ");

                  }
                
      out.write("\r\n");
      out.write("              </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("              <td>\r\n");
      out.write("                <p>メールアドレス</p>\r\n");
      out.write("              </td>\r\n");
      out.write("              <td class=\"check\">\r\n");
      out.write("                ");

                  if (list.get(0).get("kaiin_add").equals(mailStr)) {
                
      out.write("\r\n");
      out.write("                    変更なし\r\n");
      out.write("                ");

                  }else{
                
      out.write("\r\n");
      out.write("                  ");
      out.print( mailStr );
      out.write("\r\n");
      out.write("                ");

                  }
                
      out.write("\r\n");
      out.write("              </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("              <td>\r\n");
      out.write("                <p>パスワード</p>\r\n");
      out.write("              </td>\r\n");
      out.write("              <td class=\"check\">\r\n");
      out.write("                ");

                  if (list.get(0).get("kaiin_pass").equals(passStr)) {
                
      out.write("\r\n");
      out.write("                    変更なし\r\n");
      out.write("                ");

                  }else{
                
      out.write("\r\n");
      out.write("                  ");
      out.print( "入力されたパスワード" );
      out.write("\r\n");
      out.write("                ");

                  }
                
      out.write("\r\n");
      out.write("              </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("              <td>\r\n");
      out.write("                <p>生年月日</p>\r\n");
      out.write("              </td>\r\n");
      out.write("              <td class=\"check\">\r\n");
      out.write("                ");

                  if (list.get(0).get("kaiin_bday").equals(bday)) {
                
      out.write("\r\n");
      out.write("                    変更なし\r\n");
      out.write("                ");

                  }else{
                
      out.write("\r\n");
      out.write("                ");
      out.print( yearStr+"年"+monthStr+"月"+dayStr+"日" );
      out.write("\r\n");
      out.write("                ");

                  }
                
      out.write("\r\n");
      out.write("              </td>\r\n");
      out.write("\r\n");
      out.write("            <tr class=\"no-line\">\r\n");
      out.write("              <td  id=\"button\" class=\"no-line\" colspan=\"2\">\r\n");
      out.write("                  <p>\r\n");
      out.write("                    <input type=\"submit\" value=\"登録\">\r\n");
      out.write("                    <button id=\"correction\" type=\"button\" href=\"javascript:void(0)\" onclick=\"javascript:history.back()\">修正</button>\r\n");
      out.write("                  </p>\r\n");
      out.write("              </td>\r\n");
      out.write("            </form>\r\n");
      out.write("              <td class=\"no-line\">\r\n");
      out.write("              </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("\r\n");
      out.write("              <tr class=\"no-line\">\r\n");
      out.write("                <td class=\"no-line\"></td>\r\n");
      out.write("                <td class=\"no-line\"></td>\r\n");
      out.write("              </tr>\r\n");
      out.write("            </table>\r\n");
      out.write("          ");

            }
          
      out.write("\r\n");
      out.write("\r\n");
      out.write("          ");

            if (check !=null && check.equals("1")) {
          
      out.write("\r\n");
      out.write("          <form method=\"post\" action=\"./agenda_deletecomplete.jsp\">\r\n");
      out.write("            <h2>カレンダー削除(確認)</h2>\r\n");
      out.write("            <h3>以下のカレンダーを削除しますか？</h3>\r\n");
      out.write("            <table id=\"list\">\r\n");
      out.write("              <tr class=\"no-line\">\r\n");
      out.write("                <th class=\"no-line\" style=\"padding: 20px;\">カレンダーID</td>\r\n");
      out.write("                <th class=\"no-line\" style=\"padding: 20px;\">カレンダー名</td>\r\n");
      out.write("                <th class=\"no-line\" style=\"padding: 20px;\">公開設定</td>\r\n");
      out.write("                <th class=\"no-line\" style=\"padding: 20px;\">パスワード</td>\r\n");
      out.write("                <th class=\"no-line\" style=\"padding: 20px;\">書き込み設定</td>\r\n");
      out.write("              </tr>\r\n");
      out.write("              ");

                for(int i = 0; i < list.size(); i++){
              
      out.write("\r\n");
      out.write("                <tr class=\"no-line\">\r\n");
      out.write("                  <td class=\"no-line\" align=\"center\" style=\"font-size:25px; font-weight:bold;;\">");
      out.print( list.get(i).get("yotei_id") );
      out.write("</td>\r\n");
      out.write("                  <input type=\"hidden\" name=\"yotei_id\" value=\"");
      out.print( del_idStr[i] );
      out.write("\">\r\n");
      out.write("                  <td class=\"no-line\">");
      out.print( list.get(i).get("yotei_name") );
      out.write("</td>\r\n");
      out.write("                  <td class=\"no-line\">\r\n");
      out.write("                    ");
if (list.get(i).get("open_set").equals("1")) { 
      out.write("\r\n");
      out.write("                      全員に公開\r\n");
      out.write("                    ");
}else{
      out.write("\r\n");
      out.write("                      特定の人にのみ公開\r\n");
      out.write("                    ");
}
      out.write("\r\n");
      out.write("                  </td>\r\n");
      out.write("                  <td class=\"no-line\">");
      out.print( list.get(i).get("yotei_pass") );
      out.write("</td>\r\n");
      out.write("                  <td class=\"no-line\">\r\n");
      out.write("                    ");
 if(list.get(i).get("yotei_writing").equals("1")) { 
      out.write("\r\n");
      out.write("                      許可\r\n");
      out.write("                    ");
}else{
      out.write("\r\n");
      out.write("                      禁止\r\n");
      out.write("                    ");
 } 
      out.write("\r\n");
      out.write("                  </td>\r\n");
      out.write("                </tr>\r\n");
      out.write("              ");
}
      out.write("\r\n");
      out.write("              <tr class=\"no-line\">\r\n");
      out.write("                <td class=\"no-line\" colspan=\"5\">\r\n");
      out.write("                  <input type=\"submit\" id=\"dbutton\" value=\"削除\">\r\n");
      out.write("                    <button class=\"button\" type=\"button\" href=\"javascript:void(0)\" onclick=\"javascript:history.back()\">修正</button>\r\n");
      out.write("                  </td>\r\n");
      out.write("                </tr>\r\n");
      out.write("            </table>\r\n");
      out.write("          </form>\r\n");
      out.write("          ");

            }
          
      out.write("\r\n");
      out.write("\r\n");
      out.write("          ");

            if (check !=null && check.equals("2") && del_idStr != null) {
          
      out.write("\r\n");
      out.write("          <form method=\"post\" action=\"./favorite_deletecomplete.jsp\">\r\n");
      out.write("          <h2>お気に入り削除(確認)</h2>\r\n");
      out.write("          <h3>以下のお気に入りを削除しますか？</h3>\r\n");
      out.write("          <table id=\"list\">\r\n");
      out.write("            <tr class=\"no-line\">\r\n");
      out.write("              <th class=\"no-line\" style=\"padding: 20px;\">カレンダー名</td>\r\n");
      out.write("              <th class=\"no-line\" style=\"padding: 20px;\">書き込み</td>\r\n");
      out.write("              <th class=\"no-line\" style=\"padding: 20px;\">作成者</td>\r\n");
      out.write("              <th></th>\r\n");
      out.write("            </tr>\r\n");
      out.write("            ");

        		for(int i = 0; i < del_idStr.length; i++){
            
      out.write("\r\n");
      out.write("            <tr class=\"no-line\">\r\n");
      out.write("              <input type=\"hidden\" name=\"yotei_id\" value=\"");
      out.print( del_idStr[i] );
      out.write("\">\r\n");
      out.write("              <td class=\"no-line\">");
      out.print( list.get(i).get("yotei_name") );
      out.write("</td>\r\n");
      out.write("              <td class=\"no-line\">\r\n");
      out.write("                ");
 if(list.get(i).get("yotei_writing").equals("1")) { 
      out.write("\r\n");
      out.write("                  許可\r\n");
      out.write("                ");
}else{
      out.write("\r\n");
      out.write("                  禁止\r\n");
      out.write("                ");
 } 
      out.write("\r\n");
      out.write("              </td>\r\n");
      out.write("              <td class=\"no-line\">");
      out.print( list.get(i).get("kaiin_name") );
      out.write("</td>\r\n");
      out.write("            ");
}
      out.write("\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr class=\"no-line\">\r\n");
      out.write("              <td class=\"no-line\" colspan=\"5\">\r\n");
      out.write("                <input type=\"submit\" id=\"dbutton\" value=\"削除\">\r\n");
      out.write("                <button class=\"button\" type=\"button\" href=\"javascript:void(0)\" onclick=\"javascript:history.back()\">修正</button>\r\n");
      out.write("              </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("          </table>\r\n");
      out.write("          ");

            }
          
      out.write("\r\n");
      out.write("\r\n");
      out.write("          </main>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("      </div>\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("          <ul class=\"circles\">\r\n");
      out.write("            <li></li>\r\n");
      out.write("            <li></li>\r\n");
      out.write("            <li></li>\r\n");
      out.write("            <li></li>\r\n");
      out.write("            <li></li>\r\n");
      out.write("            <li></li>\r\n");
      out.write("            <li></li>\r\n");
      out.write("            <li></li>\r\n");
      out.write("            <li></li>\r\n");
      out.write("            <li></li>\r\n");
      out.write("          </ul>\r\n");
      out.write("\r\n");
      out.write("  <script type=\"text/javascript\" src=\"./js/main.js\"></script>\r\n");
      out.write("\r\n");
      out.write("  </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
