document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("login_box").addEventListener("submit", function(event) {
    event.preventDefault(); //form의 submit 기능 제거
  });
  document.getElementById("login_btn").addEventListener("click", function(){
    axios.post("http://localhost:8585/api/member/login", {
      id:document.getElementById("user_id").value,
      pwd:document.getElementById("user_pwd").value
    })
    .then(function(result) {
      if(result.data.status) {
        // alert("로그인 성공")
        console.log(result.data);
        if(result.data.user.miStatus == 1) {
          //정상사용
          sessionStorage.setItem("user", JSON.stringify(result.data.user));
          location.href = "/axios_tutorial.html"
        }
        else if(result.data.user.miStatus == 2) {
          //정지
          alert("이용 정지된 사용자입니다")
        }
        else if(result.data.user.miStatus == 3) {
          //영구정지
          alert("영구 정지된 사용자입니다. \n 관리자에게 문의하세요")
        }
      }
      else {
        alert(result.data.message);
      }
    })
  })
})