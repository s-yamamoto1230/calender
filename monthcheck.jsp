<%-- 月チェック --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%
  //文字コードの指定
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");

  //現在の日付取得
  Date today = new Date();
  //Calendarクラスのオブジェクト生成
  Calendar calendar = Calendar.getInstance();
  //現在の日付設定
  calendar.setTime(today);
  //年、月、日の取得
  int year = calendar.get(Calendar.YEAR);
  int month = calendar.get(Calendar.MONTH);
  int day = calendar.get(Calendar.DATE);
  calendar.set(year,month,1);
  int ww = calendar.get(Calendar.DAY_OF_WEEK)-1;


  //入力データ受信
  String yotei_idStr  = request.getParameter("yotei_id");
%>

<!DOCTYPE html>
<html>
  <meta charset="utf-8">

  <head>
    <title>チェック</title>

<meta http-equiv="refresh" content="0; URL='./myag_main.jsp?year=<%= year %>&month=<%= month %>&yotei_id=<%= yotei_idStr %>'" />

  </head>

  <link rel="stylesheet" type="text/css" href="./css/main.css">

  </body>
</html>
