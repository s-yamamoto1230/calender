<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%
  //文字コードの指定
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");

  String yotei_idStr[]  = request.getParameterValues("yotei_id");

%>
<html>

  <head>

    <meta charset="utf-8">

    <title>カレンダー削除</title>

    <link rel="stylesheet" type="text/css" href="./css/info.css">

  </head>

  <body>

    <h1>
      以下のカレンダーを削除しますか？
    </h1>

    <form method="post" action="./agenda_deletecomplete.jsp">
      <table id="list">
        <%
    		for(int i = 0; i < yotei_idStr.length; i++){
        %>
        <tr class="no-line">
          <td id="dcheck">
            ・<%= yotei_idStr[i] %>
            <input type="hidden" name="yotei_id" value="<%= yotei_idStr[i] %>"
          </td>
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
