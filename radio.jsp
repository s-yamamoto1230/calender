<meta charset="utf-8">

  <body>
  　  <th>Q1：入院の経験はありますか</th>
      <label><input type="radio" id="hospital0" name="hospital" onClick="hospitalflg0(this.checked);"/> はい</label>
      <label><input type="radio" id="hospital1" name="hospital" onClick="hospitalflg1(this.checked);"/> いいえ</label>
      <br>
      <th>Q2: Q1で「はい」と答えた方に質問します。どのような病気で入院されましたか？</th>
      <input type="text" id="hospitalization" value="" disabled="disabled">
  </body>

<script type="text/javascript">
function hospitalflg0(ischecked){
    if(ischecked == true){
      document.getElementById("hospitalization").disabled = false;
    } else {
      document.getElementById("hospitalization").disabled = true;
    }
  }

  function hospitalflg1(ischecked){
    if(ischecked == true){
      document.getElementById("hospitalization").disabled = true;
    } else {
      document.getElementById("hospitalization").disabled = false;
    }
  }
</script>
