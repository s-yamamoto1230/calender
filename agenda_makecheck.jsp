<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%

  String idStr = request.getParameter("id");
  String titleStr = request.getParameter("title");
  String openStr = request.getParameter("open");
  String passStr = request.getParameter("password");
  String permissionStr = request.getParameter("permission");

//   String message = passStr;
//   int num = message.length();
//   String password="";
//   for (int i=1; i<=num; i++) {
//       password=password+"*";
//   }

%>

<html>

  <head>

    <meta charset="utf-8">

    <title>新規登録(確認)</title>

    <link rel="stylesheet" type="text/css" href="./css/info.css">

  </head>

  <body>

    <h1>カレンダー新規作成（確認）</h1>
    <h2>以下の内容で登録しますか？</h2>

    <table>
    <form action="./agenda_makecomplete.jsp" method="post">

      <input type="hidden" name="id" value="<%= idStr %>">
      <input type="hidden" name="title" value="<%= titleStr %>">
      <input type="hidden" name="open" value="<%= openStr %>">
      <input type="hidden" name="pass" value="<%= passStr %>">
      <input type="hidden" name="permission" value="<%= permissionStr %>">


      <tr>
        <td>
          <p>ID</p>
        </td>
        <td class="check">
          <%= idStr %>
        </td>
      </tr>
      <tr>
        <td>
          <p>タイトル</p>
        </td>
        <td class="check">
          <%= titleStr %>
        </td>
      </tr>
      <tr>
        <td>
          <p>公開設定</p>
        </td>
        <td class="check">
          <% if(openStr.equals("1")) { %>
          全員に公開
          <%}else{%>
          特定の人にのみ公開
          <% } %>
        </td>
      </tr>
      <tr>
        <td>
          <p>パスワード</p>
        </td>
        <td class="check">
          <% if(openStr.equals("1")) { %>
          なし
          <%}else{%>
          <%= "入力されたパスワード" %>
          <% } %>
        </td>
      </tr>
      <tr>
        <td>
          <p>書き込み設定</p>
        </td>
        <td class="check">
          <% if(permissionStr.equals("1")) { %>
          許可
          <%}else{%>
          禁止
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
