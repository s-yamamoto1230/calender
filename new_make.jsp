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

    <title>新規登録</title>

    <link rel="stylesheet" type="text/css" href="./css/info.css">

  </head>

  <body>

    <h1>新規登録</h1>


    <table>
      <form  name="form" action="./new_check.jsp" method="post" action="#" onsubmit="return formCheck()">
      <tr>
        <td class="title">
          <p>メールアドレス</p>
        </td>
        <td>
          <p><input type="email" name="mail" size="25" required></p>
        </td>
      </tr>
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
      <div class="form-group">
      <tr>
        <td class="title">
          <p>パスワード</p>
        </td>
        <td>
          <br><div class="form-group">
            <p><input type="password" class="form-control" id="password" name="password" size="25" pattern="^[0-9a-z]+$" required /> </p>
          </div>
          <p id="notice-input-text-1" style="display: none; color: red;"></p>
          <p class="alert">※半角英数字6文字以上20字以内</p>
        </td>
      </tr>
      <tr>
        <td class="title">
          パスワード（確認）
        </td>
        <td>
          <div class="form-group">
            <p><input type="password" class="form-control" id="confirm" name="confirm" size="25" oninput="CheckPassword(this)" required /></p>
          </div>
        </td>
      </tr>
    </div>
      <tr>
        <td class="title">
          <p>ユーザー名</p>
        </td>
        <td>
          <input type="text" name="username" size="25" required>
        </td>
      <tr>
        <td class="title">
          <p>生年月日</p>
        </td>
        <td>
          <select name="year" onchange="dateCheck('year', 'month', 'day')" required>
            <option value="">--</option>
              <option value="1920">1920</option>
              <option value="1921">1921</option>
              <option value="1922">1922</option>
              <option value="1923">1923</option>
              <option value="1924">1924</option>
              <option value="1925">1925</option>
              <option value="1926">1926</option>
              <option value="1927">1927</option>
              <option value="1928">1928</option>
              <option value="1929">1929</option>
              <option value="1930">1930</option>
              <option value="1931">1931</option>
              <option value="1932">1932</option>
              <option value="1933">1933</option>
              <option value="1934">1934</option>
              <option value="1935">1935</option>
              <option value="1936">1936</option>
              <option value="1937">1937</option>
              <option value="1938">1938</option>
              <option value="1939">1939</option>
              <option value="1940">1940</option>
              <option value="1941">1941</option>
              <option value="1942">1942</option>
              <option value="1943">1943</option>
              <option value="1944">1944</option>
              <option value="1945">1945</option>
              <option value="1946">1946</option>
              <option value="1947">1947</option>
              <option value="1948">1948</option>
              <option value="1949">1949</option>
              <option value="1950">1950</option>
              <option value="1951">1951</option>
              <option value="1952">1952</option>
              <option value="1953">1953</option>
              <option value="1954">1954</option>
              <option value="1955">1955</option>
              <option value="1956">1956</option>
              <option value="1957">1957</option>
              <option value="1958">1958</option>
              <option value="1959">1959</option>
              <option value="1960">1960</option>
              <option value="1961">1961</option>
              <option value="1962">1962</option>
              <option value="1963">1963</option>
              <option value="1964">1964</option>
              <option value="1965">1965</option>
              <option value="1966">1966</option>
              <option value="1967">1967</option>
              <option value="1968">1968</option>
              <option value="1969">1969</option>
              <option value="1970">1970</option>
              <option value="1971">1971</option>
              <option value="1972">1972</option>
              <option value="1973">1973</option>
              <option value="1974">1974</option>
              <option value="1975">1975</option>
              <option value="1976">1976</option>
              <option value="1977">1977</option>
              <option value="1978">1978</option>
              <option value="1979">1979</option>
              <option value="1980">1980</option>
              <option value="1981">1981</option>
              <option value="1982">1982</option>
              <option value="1983">1983</option>
              <option value="1984">1984</option>
              <option value="1985">1985</option>
              <option value="1986">1986</option>
              <option value="1987">1987</option>
              <option value="1988">1988</option>
              <option value="1989">1989</option>
              <option value="1990">1990</option>
              <option value="1991">1991</option>
              <option value="1992">1992</option>
              <option value="1993">1993</option>
              <option value="1994">1994</option>
              <option value="1995">1995</option>
              <option value="1996">1996</option>
              <option value="1997">1997</option>
              <option value="1998">1998</option>
              <option value="1999">1999</option>
              <option value="2000">2000</option>
              <option value="2001">2001</option>
              <option value="2002">2002</option>
              <option value="2003">2003</option>
              <option value="2004">2004</option>
              <option value="2005">2005</option>
              <option value="2006">2006</option>
              <option value="2007">2007</option>
              <option value="2008">2008</option>
              <option value="2009">2009</option>
              <option value="2010">2010</option>
              <option value="2011">2011</option>
              <option value="2012">2012</option>
              <option value="2013">2013</option>
              <option value="2014">2014</option>
              <option value="2015">2015</option>
              <option value="2016">2016</option>
              <option value="2017">2017</option>
              <option value="2018">2018</option>
              <option value="2019">2019</option>
          </select>年
          <select name="month" onchange="dateCheck('year', 'month', 'day')" required>
            <option value="">--</option>
            <option value="1">01</option>
              <option value="2">02</option>
              <option value="3">03</option>
              <option value="4">04</option>
              <option value="5">05</option>
              <option value="6">06</option>
              <option value="7">07</option>
              <option value="8">08</option>
              <option value="9">09</option>
              <option value="10">10</option>
              <option value="11">11</option>
              <option value="12">12</option>
          </select>月
          <select name="day" required>
            <option value="">--</option>
                    <option value="1">01</option>
                    <option value="2">02</option>
                    <option value="3">03</option>
                    <option value="4">04</option>
                    <option value="5">05</option>
                    <option value="6">06</option>
                    <option value="7">07</option>
                    <option value="8">08</option>
                    <option value="9">09</option>
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
                    <option value="24">24</option>
                    <option value="25">25</option>
                    <option value="26">26</option>
                    <option value="27">27</option>
                    <option value="28">28</option>
                    <option value="29">29</option>
                    <option value="30">30</option>
                    <option value="31">31</option>
            </select>日
        </td>

      <tr class="no-line">
        <td class="no-line" id="button" colspan="2">
            <p>
              <input type="submit" id="submit" value="登録">
            </p>
        </td>
        <td class="no-line"></td>
      </form>
      </tr>

        <tr class="no-line">
          <td class="no-line" colspan="2">
            <p><a href="./index.jsp">ログインに戻る</a></p>
          </td>
          <td class="no-line"></td>

        </tr>
      </table>

  <script type="text/javascript" src="./js/info.js"></script>

  </body>

</html>
