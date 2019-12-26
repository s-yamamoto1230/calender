<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%
  //文字コードの指定
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");

  String kaiin_idStr = request.getParameter("kaiin_id");

%>

<html>

  <head>

    <meta charset="utf-8">

    <title>予定作成</title>

    <link rel="stylesheet" type="text/css" href="./css/info.css">

  </head>

  <body>

    <h1 class="title">予定新規作成</h1>


    <table>
      <form  name="form" action="./agenda_makecheck.jsp" method="post" action="#" onsubmit="return formCheck()">

        <input type="hidden" name="kaiin_id" value="<%= kaiin_idStr %>">

      <tr>
        <td class="title">
          <p>時間</p>
          <td colspan="2">
            開始：
            <select name="hour">
              <script>
                var i;
                for(i=0; i<24; i+=1){
                document.write('<option value="'+i+'">'+i+'時</option>');
                }
              </script>
            </select>
            <select name="min">
              <script>
                var i;
                for(i=0; i<60; i+=10){
                document.write('<option value="'+i+'">'+i+'分</option>');
                }
              </script>
            </select>
            &emsp;～&emsp;終了：
            <select name="hour">
              <script>
                var i;
                for(i=0; i<24; i+=1){
                document.write('<option value="'+i+'">'+i+'時</option>');
                }
              </script>
            </select>
            <select name="min">
              <script>
                var i;
                for(i=0; i<60; i+=10){
                document.write('<option value="'+i+'">'+i+'分</option>');
                }
              </script>
            </select>
          </td>
        </tr>
      <tr>
        <td class="title">
          <p>場所</p>
        </td>
        <td>
          <input type="text" name="title" size="25">
        </td>
      </tr>
      <tr>
        <td class="title">
          <p>詳細</p>
        </td>
        <td>
          <textarea maxlength="200"></textarea>
        </td>
      </tr>
      <tr>
        <td class="title">
          <p>重要</p>
        </td>
        <td>
          <label><input type="checkbox">※この予定は重要です</label>
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

</html>
