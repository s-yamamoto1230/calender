<%
session.invalidate();
%>

<!DOCTYPE html>

<html>

  <head>

    <meta charset="utf-8">

    <title>My Agenda</title>

    <link rel="stylesheet" type="text/css" href="./css/index.css">

  </head>

  <body>
    <body>
    <video id="bg-video" autoplay loop muted>
    <source src="images/By The Lake.mp4" type="video/mp4">
    </video>

    <div id="contents">
      <h1>My Agenda</h1>

       <form action="./logincheck.jsp" method="post">

        <table>
          <tr>
            <td>
              <p>ＩＤ</p>
            </td>
            <td>
              <input type="text" name="id" size="50">
            </td>
          </tr>
          <tr>
            <td>
              <p>パスワード</p>
            </td>
            <td>
              <input type="password" name="pass" size="50">
            </td>

          <tr>
            <td></td>
            <td>
                <p>
                  <input type="submit" value="ログイン">
                </p>
            </td>
          </form>
          </tr>
          <tr>
            <td>
            </td>
            <td>
              <p id="new"><a href="./new_make.jsp">  新規登録の方はコチラ</a></p>
            </td>
          </tr>
            <tr>
              <td>
              </td>
              <td>
                <p id="lost"><a href="pass_lost.jsp">  ID、パスワードをを忘れた方はコチラ</a></p>
              </td>
            </tr>


       </table>
      </div>
  </body>

</html>

