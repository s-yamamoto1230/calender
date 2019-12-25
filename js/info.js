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
    if ( input_text_1_length < 6 ){ // 入力文字数が不足している場合
        flag = 1;
        document . getElementById( 'notice-input-text-1' ) . innerHTML = 6 - input_text_1_length + "文字不足しています。";
        document . getElementById( 'notice-input-text-1' ) . style . display = "block";
        reset_target1.value = '';
    }else if ( input_text_1_length  > 20 ){ // 入力文字数が超過している場合
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


var d = new Date(d, m, 0);
d.getDate();

function dateCheck(year, month, day) {
  var y = Number(document.getElementsByName(year)[0].value);
  var m = Number(document.getElementsByName(month)[0].value);
  var day = document.getElementsByName(day)[0];
  var d = Number(day.value);
  if (y && m) {
    var ds = new Date(y, m, 0);
    var dsn = Number(ds.getDate());
    var html = '<option value="">--</option>';
    for(var i = 1; i <= dsn; i++) {
      if (i === d) {
        html += '<option value="' + i + '" selected>' + i + '</option>';
      }
      else {
        html += '<option value="' + i + '">' + i + '</option>';
      }
    }
    day.innerHTML = html;
  }
}

