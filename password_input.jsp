<%-- 公開カレンダー閲覧時パスワード入力画面 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%
  //文字コードの指定
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");

  String result = request.getParameter("result");

%>

<html>

  <head>

    <meta charset="utf-8">

    <title>パスワード入力</title>

    <link rel="stylesheet" type="text/css" href="./css/info.css">

  </head>

  <body>

    <h1 class="title">パスワード入力画面</h1>
    <%
      if (result != null) {
    %>
        <h2 id="error">＊パスワードが違います＊</h2>
    <%
      }
    %>


    <table>
      <form action="./password_check.jsp" method="post">

      <tr>
        <td class="title">
          <p>パスワード</p>
        </td>
        <td>
          <br>
            <p><input type="password" name="password" size="25" pattern="^[0-9a-z]+$"> </p>
        </td>
      </tr>

      <tr class="no-line">
        <td class="no-line" id="button" colspan="2">
            <p>
              <input type="submit" id="submit" value="閲覧">
            </p>
        </td>
      </form>
      </tr>

        <tr class="no-line">
          <td class="no-line" colspan="2">
            <p><a href="./main.jsp">メイン画面に戻る</a></p>
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
