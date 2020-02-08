<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%
  //文字コードの指定
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");

%>

<html>

  <head>

    <meta charset="utf-8">

    <title>カレンダー作成</title>

    <link rel="stylesheet" type="text/css" href="./css/info.css">

  </head>

  <body>

    <h1 class="title">カレンダー新規作成</h1>


    <table>
      <form  name="form" action="./agenda_makecheck.jsp" method="post" onsubmit="return formChecksub()">

      <tr>
        <td class="title">
          <p>ID</p>
        </td>
        <td>
          <p><input type="text" name="id" id="id" pattern="^[0-9a-z]+$" size="25" required></p>
          <p id="notice-input-text-0" style="display: none; color: red;"></p>
          <p class="alert">※半角英数字15文字以下</p>
        </td>
      </tr>
      <tr>
        <td class="title">
          <p>タイトル</p>
        </td>
        <td>
          <input type="text" name="title" size="25" required>
        </td>
      </tr>
      <tr>
        <td class="title">
          <p>公開設定</p>
        </td>
        <td>
          <label><input type="radio" name="open"  value="1" onClick="openflg0(this.checked);" checked>全員に公開</label>
          <label><input type="radio" name="open"  value="2" onClick="openflg1(this.checked);">特定の人にのみ公開</label>
        </td>
      </tr>
      <div class="form-group">
      <tr>
        <td class="title">
          <p>パスワード</p>
        </td>
        <td>
          <br><div class="form-group">
            <p><input type="password" class="form-control" id="password" name="password" size="25" pattern="^[0-9a-z]+$" disabled="disabled"> </p>
          </div>
        </td>
      </tr>
      <tr>
        <td class="title">
          パスワード（確認）
        </td>
        <td>
          <div class="form-group">
            <p><input type="password" class="form-control" id="confirm" name="confirm" size="25" oninput="CheckPassword(this)" disabled="disabled"></p>
          </div>
        </td>
      </tr>
    </div>
      <tr>
        <td class="title">
          <p>他人の書き込み</p>
        </td>
        <td>
          <label><input type="radio" name="permission" value="1">許可</label>
          <label><input type="radio" name="permission" value="2" checked>禁止</label>
        </td>
      </tr>


      <tr class="no-line">
        <td class="no-line" id="button" colspan="2">
            <p>
              <input type="submit" id="submit" value="登録">
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

  </body>
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

</html>
