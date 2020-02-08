<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%

  String session_id = (String)session.getAttribute("login_id");
  String dayStr = request.getParameter("day");
  String s_hourStr = request.getParameter("s_hour");
  String s_minStr = request.getParameter("s_min");
  String f_hourStr = request.getParameter("f_hour");
  String f_minStr = request.getParameter("f_min");
  String placeStr = request.getParameter("place");
  String detailsStr = request.getParameter("details");
  String importanceStr = request.getParameter("importance");
  String year = request.getParameter("year");
  String month = request.getParameter("month");


  if (importanceStr == null) {
    importanceStr ="0";
  }else {
    importanceStr ="1";
  }
%>

<html>

  <head>

    <meta charset="utf-8">

    <title>予定登録(確認)</title>

    <link rel="stylesheet" type="text/css" href="./css/info.css">

  </head>

  <body>


    <h1>予定新規追加（確認）</h1>
    <h2>以下の内容で登録しますか？</h2>

    <table>
    <form action="./schedule_makecomplete.jsp" method="post">

      <input type="hidden" name="day" value="<%= dayStr %>">
      <input type="hidden" name="s_hour" value="<%= s_hourStr %>">
      <input type="hidden" name="s_min" value="<%= s_minStr %>">
      <input type="hidden" name="f_hour" value="<%= f_hourStr %>">
      <input type="hidden" name="f_min" value="<%= f_minStr %>">
      <input type="hidden" name="place" value="<%= placeStr %>">
      <input type="hidden" name="details" value="<%= detailsStr %>">
      <input type="hidden" name="importance" value="<%= importanceStr %>">
      <input type="hidden" name="year" value="<%= year %>">
      <input type="hidden" name="month" value="<%= month %>">

      <tr>
        <td>
          <p>時間</p>
        </td>
        <td class="check">
        <%= s_hourStr %>：<%= s_minStr %>～<%= f_hourStr %>：<%= f_minStr %>　
        </td>
      </tr>
      <tr>
        <td>
          <p>場所</p>
        </td>
        <td class="check">
          <%= placeStr %>
        </td>
      </tr>
      <tr>
        <td>
          <p>詳細</p>
        </td>
        <td class="check">
          <%= detailsStr %>
        </td>
      </tr>
      <tr>
        <td>
          <p>重要</p>
        </td>
        <td class="check">
          <% if(importanceStr.equals("1")) { %>
          めちゃくちゃ重要です。
          <%}else{%>
          そこまで重要ではありません。
          <% } %>
        </td>
      </tr>



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
