<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<%
  //文字コードの指定
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");

  //入力データ受信
  String session_id = (String)session.getAttribute("login_id");
  String day  = request.getParameter("day");
  String s_hour  = request.getParameter("s_hour");
  String s_mine  = request.getParameter("s_mine");
  String f_hour  = request.getParameter("f_hour");
  String f_mine  = request.getParameter("f_mine");
  String place  = request.getParameter("place");
  String details  = request.getParameter("details");
  String importance  = request.getParameter("importance");

  %>

<html>

  <head>

    <meta charset="utf-8">

    <title>予定更新</title>

    <link rel="stylesheet" type="text/css" href="./css/info.css">

  </head>

  <body>

    <h1 class="title">予定更新</h1>


    <table>
      <form  name="form" action="./schedule_updatecheck.jsp" method="post" action="#" onsubmit="return formCheck()">

        <input type="hidden" name="day" value="<%= day %>">
        <input type="hidden" name="s_hour" value="<%= s_hour %>">
        <input type="hidden" name="s_mine" value="<%= s_mine %>">

      <tr>
        <td class="title">
          <p>時間</p>
          <td colspan="2">
            開始：
            <select name="s_hour_up">
              <option value="00">00</option>
              <option value="01">01</option>
              <option value="02">02</option>
              <option value="03">03</option>
              <option value="04">04</option>
              <option value="05">05</option>
              <option value="06">06</option>
              <option value="07">07</option>
              <option value="08">08</option>
              <option value="09">09</option>
              <option value="10">10</option>
              <option value="11">11</option>
              <option value="12">12</option>
              <option value="13">13</option>
              <option value="14">14</option>
              <option value="15">15</option>
              <option value="16">16</option>
              <option value="17">17</option>
              <option value="18">18</option>
              <option value="19">19</option>
              <option value="20">20</option>
              <option value="21">21</option>
              <option value="22">22</option>
              <option value="23">23</option>
            </select>時
            <select name="s_min_up">
              <option value="00">00</option>
              <option value="10">10</option>
              <option value="20">20</option>
              <option value="30">30</option>
              <option value="40">40</option>
              <option value="50">50</option>
            </select>分～
            終了：
            <select name="f_hour_up">
              <option value="00">00</option>
              <option value="01">01</option>
              <option value="02">02</option>
              <option value="03">03</option>
              <option value="04">04</option>
              <option value="05">05</option>
              <option value="06">06</option>
              <option value="07">07</option>
              <option value="08">08</option>
              <option value="09">09</option>
              <option value="10">10</option>
              <option value="11">11</option>
              <option value="12">12</option>
              <option value="13">13</option>
              <option value="14">14</option>
              <option value="15">15</option>
              <option value="16">16</option>
              <option value="17">17</option>
              <option value="18">18</option>
              <option value="19">19</option>
              <option value="20">20</option>
              <option value="21">21</option>
              <option value="22">22</option>
              <option value="23">23</option>
            </select>時
            <select name="f_min_up">
              <option value="00">00</option>
              <option value="10">10</option>
              <option value="20">20</option>
              <option value="30">30</option>
              <option value="40">40</option>
              <option value="50">50</option>
            </select>分
          </td>
        </tr>
      <tr>
        <td class="title">
          <p>場所</p>
        </td>
        <td>
          <input type="text" name="place_up" size="25" value="<%=place%>">
        </td>
      </tr>
      <tr>
        <td class="title">
          <p>詳細</p>
        </td>
        <td>
          <textarea name="details_up" maxlength="200"><%=details%></textarea>
        </td>
      </tr>
      <tr>
        <td class="title">
          <p>重要</p>
        </td>
        <td>
          <label><input type="checkbox" name="importance_up">※この予定は重要です</label>
        </td>
      </tr>


      <tr class="no-line">
        <td class="no-line" id="button" colspan="2">
            <p>
              <input type="submit" id="submit" value="更新">
            </p>
        </td>
      </form>
      </tr>

        <tr class="no-line">
          <td class="no-line" colspan="2">
            <p><a href="./myag_main.jsp">メイン画面に戻る</a></p>
          </td>

        </tr>
      </table>

  <script type="text/javascript" src="./js/info.js"></script>
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
