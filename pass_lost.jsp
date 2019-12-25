<!DOCTYPE html>

<html>

  <head>

    <meta charset="utf-8">

    <title>パスワード再設定</title>

    <link rel="stylesheet" type="text/css" href="./css/info.css">

  </head>

  <body>

    <h1>パスワード再設定</h1>
    <h2>登録メールアドレス宛にパスワード再設定用メールを送信します</h2>


    <table>
      <form  name="form" action="./pass_complete.jsp" method="post" action="#" onsubmit="return formCheck()">
      <tr>
        <td class="title">
          <p>メールアドレス</p>
        </td>
        <td>
          <p><input type="email" name="mail" size="40" required></p>
        </td>
      </tr>
      <tr>
        <td class="title">
          <p>ID</p>
        </td>
        <td>
          <p><input type="text" name="id" id="id" pattern="^[0-9a-z]+$" size="40" required></p>
        </td>
      </tr>
        <tr class="no-line">
        <td class="no-line" id="button" colspan="2">
            <p>
              <input type="submit" id="submit" value="送信">
            </p>
        </td>
        <td class="no-line"></td>
      </form>
      </tr>

        <tr class="no-line">
          <td class="no-line" colspan="2">
            <p id="link"><a href="./id_lost.jsp">IDを忘れた方はコチラ</a></p>
            <p id="link"><a href="./index.jsp">ログインに戻る</a></p>
          </td>
          <td class="no-line"></td>
        </tr>
      </table>