//ラジオボタンチェック
function openflg1(ischecked){
    if(ischecked == true){
      document.getElementById("password").disabled = false;
      document.getElementById("confirm").disabled = false;
    } else {
      document.getElementById("password").disabled = true;
      document.getElementById("confirm").disabled = true;
    }
  }

  function openflg0(ischecked){
    if(ischecked == true){
      document.getElementById("password").disabled = true;
      document.getElementById("confirm").disabled = true;
    } else {
      document.getElementById("password").disabled = false;
      document.getElementById("confirm").disabled = false;
    }
  }

  //フォーム内文字数チェック
    function formCheck(){
      var flag = 0;

      var reset_target1 = document.getElementById("confirm");
      // 入力文字数をチェック
      var input_text_0_length = document . form . id . value . length;
      var input_text_1_length = document . form . password . value . length; // 入力文字数を、変数に格納
      if (input_text_0_length > 15) {
        flag = 1;
        document . getElementById( 'notice-input-text-0') . innerHTML = input_text_0_length - 15 + "文字オーバーしています。";
        document . getElementById( "notice-input-text-0") . style . display = "block";
      }else{
        document . getElementById( "notice-input-text-0") . style . display = "none";
      }
      if ( input_text_1_length < 6){ // 入力文字数が不足している場合
          flag = 1;
          document . getElementById( 'notice-input-text-1' ) . innerHTML = 6 - input_text_1_length + "文字不足しています。";
          document . getElementById( 'notice-input-text-1' ) . style . display = "block";
          reset_target1.value = '';
      }else if ( input_text_1_length  > 20){ // 入力文字数が超過している場合
          flag = 1;
          document . getElementById( 'notice-input-text-1' ) . innerHTML = input_text_1_length - 20 + "文字オーバーしています。";
          document . getElementById( 'notice-input-text-1' ) . style . display = "block";
          reset_target1.value = '';
      }else{
        document . getElementById( "notice-input-text-1") . style . display = "none";
      }

      if( flag ){ // 入力文字数が、不足もしくは超過している場合
          window . alert( '入力内容に不備があります。' ); // アラートを表示
          return false; // 送信中止
      }else{ // 入力文字数が、不足もしくは超過していない場合
          document . getElementById( 'notice-input-text-1' ) . style . display = "none";
          return true; // 送信実行
      }

  }

  //フォーム内文字数チェック
    function formChecksub(){
      var flag = 0;

      var reset_target1 = document.getElementById("confirm");
      // 入力文字数をチェック
      var input_text_0_length = document . form . id . value . length; // 入力文字数を、変数に格納
      if (input_text_0_length > 15) {
        flag = 1;
        document . getElementById( 'notice-input-text-0') . innerHTML = input_text_0_length - 15 + "文字オーバーしています。";
        document . getElementById( "notice-input-text-0") . style . display = "block";
      }else{
        document . getElementById( "notice-input-text-0") . style . display = "none";
      }

      if( flag ){ // 入力文字数が、不足もしくは超過している場合
          window . alert( '入力内容に不備があります。' ); // アラートを表示
          return false; // 送信中止
      }else{ // 入力文字数が、不足もしくは超過していない場合
          document . getElementById( 'notice-input-text-1' ) . style . display = "none";
          return true; // 送信実行
      }
  }

  //パスワードとパスワード確認の一致確認
    function CheckPassword(confirm){
      // 入力値取得
      var input1 = password.value;
      var input2 = confirm.value;
      var reset_target = document.getElementById("confirm");
      var flag = 0;
      // パスワード比較
      if(input1 != input2){
        flag =1;
      }
      if (flag) {
        confirm.setCustomValidity("入力値が一致しません。");
      }else{
        confirm.setCustomValidity('');
      }
    }

const weeks = ['日', '月', '火', '水', '木', '金', '土']
const date = new Date()
let year = date.getFullYear()
let month = date.getMonth() + 1
const config = {
    show: 1,
}

//カレンダー追加用
function showCalendar(year, month) {
    for ( i = 0; i < config.show; i++) {
        const calendarHtml = createCalendar(year, month)
        const sec = document.createElement('section')
        sec.innerHTML = calendarHtml
        document.querySelector('#calendar').appendChild(sec)

        month++
        if (month > 12) {
            year++
            month = 1
        }
    }
}


function createCalendar(year, month) {
    const startDate = new Date(year, month - 1, 1) // 月の最初の日を取得
    const endDate = new Date(year, month,  0) // 月の最後の日を取得
    const endDayCount = endDate.getDate() // 月の末日
    const lastMonthEndDate = new Date(year, month - 2, 0) // 前月の最後の日の情報
    const lastMonthendDayCount = lastMonthEndDate.getDate() // 前月の末日
    const startDay = startDate.getDay() // 月の最初の日の曜日を取得
    let dayCount = 1 // 日にちのカウント
    let calendarHtml = '' // HTMLを組み立てる変数

    calendarHtml += '<h1>' + year  + '/' + month + '</h1>'
    calendarHtml += '<table>'

    // 曜日の行を作成
    for (let i = 0; i < weeks.length; i++) {
        calendarHtml += '<td>' + weeks[i] + '</td>'
    }

    for (let w = 0; w < 6; w++) {
        calendarHtml += '<tr>'
        const addButton = document.createElement('input');
    addButton.classList.add('addition');
    addButton.type = 'button';
    addButton.value = '追加';
//    document.body.appendChild(addButton);

        for (let d = 0; d < 7; d++) {
    const addButton = document.createElement('input');
            if (w == 0 && d < startDay) {
                // 1行目で1日の曜日の前
                let num = lastMonthendDayCount - startDay + d + 1
                calendarHtml += '<td class="is-disabled">' + num + '</td>'
            } else if (dayCount > endDayCount) {
                // 末尾の日数を超えた
                let num = dayCount - endDayCount
                calendarHtml += '<td class="is-disabled">' + num + '</td>'
                dayCount++
            } else {
                calendarHtml += `<td class="calendar_td" data-date="${year}/${month}/${dayCount}">${dayCount}</td>`
                dayCount++
            }
        }
        calendarHtml += '</tr>'
    }
    calendarHtml += '</table>'

    return calendarHtml
}

function moveCalendar(e) {
    document.querySelector('#calendar').innerHTML = ''

    if (e.target.id === 'prev') {
        month--

        if (month < 1) {
            year--
            month = 12
        }
    }

    if (e.target.id === 'next') {
        month++

        if (month > 12) {
            year++
            month = 1
        }
    }

    showCalendar(year, month)
}

document.querySelector('#prev').addEventListener('click', moveCalendar)
document.querySelector('#next').addEventListener('click', moveCalendar)

document.addEventListener("click", function(e) {
    if(e.target.classList.contains("calendar_td")) {
        //alert('クリックした日付は' + e.target.dataset.date + 'です')
        const open = document.getElementById('calendar');
  const close = document.getElementById('close');
  const modal = document.getElementById('modal');
  const mask = document.getElementById('mask');

  open.addEventListener('click', function () {
    modal.classList.remove('hidden');
    mask.classList.remove('hidden');
  });
  close.addEventListener('click', function () {
    modal.classList.add('hidden');
    mask.classList.add('hidden');
  });
  mask.addEventListener('click', function () {
    modal.classList.add('hidden');
    mask.classList.add('hidden');
  });
    }
})

showCalendar(year, month)

    function ShowAlert() {
      if(confirm("ログアウトしますか？")){
        var f = document.forms["logout_info"];
        f.method = "POST";
        f.submit();
        return true;
      }
    }

    function ShowFavorite() {
      if(confirm("お気に入り登録しますか？")){
        var f = document.forms["favorite_info"];
        f.method = "POST";
        f.submit();
        return true;
      }
    }

    function loadFavorite(){
  alert("お気に入り登録が完了しました！！!");
}

function ShowDel() {
  if(confirm("予定を削除しますか？")){
    var f = document.forms["Del_info"];
    f.method = "POST";
    f.submit();
    return true;
  }
}

function ShowAlldel() {
  if(confirm("テーブル内を空にしますか？")){
    var f = document.forms["all_del"];
    f.method = "POST";
    f.submit();
    return true;
  }
}

function loadDelete(){
alert("テーブル内を空にしました！！！");
}

function ShowFavodel() {
  if(confirm("お気に入りを削除しますか？")){
    var f = document.forms["favorite_del"];
    f.method = "POST";
    f.submit();
    return true;
  }
}

function loadFavodel(){
alert("お気に入りを１件しました！！！");
}

function ShowCalendardel() {
  if(confirm("カレンダーを削除しますか？")){
    var f = document.forms["calendar_del"];
    f.method = "POST";
    f.submit();
    return true;
  }
}

function loadCalendardel(){
alert("カレンダーを１件削除しました！！！");
}

function ShowCalendarmake() {
  if(confirm("以下の内容でカレンダーを作成しますか？")){
    var f = document.forms["calendar_del"];
    f.method = "POST";
    f.submit();
    return true;
  }
}

function loadCalendarmake(){
alert("カレンダーを１件作成しました！！！");
}

function loadCalendarNomake(){
alert("登録IDは存在しているため作成に失敗しました。");
}

function loadChange(){
alert("登録内容を変更しました！！！");
}
