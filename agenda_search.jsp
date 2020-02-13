<!DOCTYPE html>

<html>

  <head>

    <meta charset="utf-8">

    <title>カレンダー検索</title>

    <link rel="stylesheet" type="text/css" href="./css/info.css">

  </head>

  <body>

      <h1>カレンダー検索</h1>
      <h2>
        カレンダーIDかキーワードを入力して検索ボタンを押してください。<br>カレンダーを検索します。<br>(両方入力するとカレンダーIDを優先して検索します)
      </h2>
       <form action="./agenda_searchcomplete.jsp" method="post">
        <table>
          <tr>
            <td  class="title">
              <p>カレンダーID</p>
            </td>
            <td class="no-line">
              <input type="text" name="id" size="25" class="text">
            </td>
          </tr>
          <tr>
            <td  class="title">
              <p>キーワード</p>
            </td>
            <td class="no-line">
              <input type="text" name="keyword" size="25" class="text">
            </td>

          <tr class="no-line">
            <td class="no-line" id="button" colspan="2">
              <input type="submit" value="検索" id="button">
            </td>
          </form>
          </tr>
          <tr class="no-line">
            <td class="no-line" colspan="2">
              <p id="new"><a href="./main.jsp">メイン画面に戻る </a></p>
            </td>
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
