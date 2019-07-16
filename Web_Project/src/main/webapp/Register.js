(function() {
    'use strict';
    window.addEventListener('load', function() {
        // Get the forms we want to add validation styles to
        var forms = document.getElementsByClassName('needs-validation');
        // 로그인 유효성 검사 flag
        var id_flag = false;

        //id 중복확인 체크
        var id_check = document.getElementById("id_check");
        var id = document.getElementById("id");
        id_check.addEventListener('click', function() {

                var result = document.querySelector('#id_modal .modal-body');

                if (id.checkValidity() === false) {
                    id.setAttribute("style", "border:5px solid red");
                    result.innerHTML = "입력하신 아이디가 없습니다.";
                    id_flag = false;
                } else {
                    var request = new XMLHttpRequest();
                    request.open('Get', 'MainServlet?task=1&id=' + id.value, false);
                    request.onreadystatechange = searchId;
                    request.send(null);

                    //등록된 ID 조회
                    function searchId(obj) {
                        if (request.readyState === request.DONE && request.status === 200) {
                            var obj = request.responseText;
                            if (obj == 0) {
                                alert("에러가 발생했습니다.");
                            } else if (obj == 1) {
                                id.setAttribute("style", "border:5px solid red");
                                result.innerHTML = "'" + id.value + "'" + "는 이미 사용중입니다.";
                                id.value = "";
                            } else if (obj == 2) {
                                result.innerHTML = "'" + id.value + "'" + "는 사용 가능합니다";
                                id_flag = true;
                            }
                        }
                    }
                }
            })
            //id 수정시 경고 제거
        id.addEventListener('change', function(event) {
            id.removeAttribute("style");
            id_flag = false;
        })


        // 회원가입 버튼 클릭시 확인사항
        var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }

                if (from.cform.checkValidity() === true && id_flag == false) {

                }

                form.classList.add('was-validated');
            }, false);
        });

        var unvalidation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('reset', function() {
                form.classList.remove('was-validated');
                id_flag = false;
            }, false);
        });
    }, false);

    //비밀 번호 불일치시 경고 표시
    var pwdToCheck = document.getElementById("password_check");
    var pwd = document.getElementById("password");
    pwdToCheck.addEventListener('change', function(event) {
        if (event.target.value !== pwd.value) {
            event.target.value = "";
            event.target.setAttribute("placeholder", "입력한 비밀번호가 다릅니다.");
            event.target.setAttribute("style", "border:5px solid red;");
        }
    })

    // 비밀번호 확인 클릭시 경고 표시 삭제
    pwdToCheck.addEventListener('focus', function(event) {
        event.target.value = "";
        event.target.setAttribute("placeholder", "비밀번호");
        event.target.removeAttribute("style");
        event.target.focus();
    })

    //전화번호 유효성 검사(숫자만 입력)
    var cphone = document.getElementById("cphone");
    cphone.addEventListener('keyup', function(event) {
        var regexp = /[^0-9]/g;
        event.target.value = event.target.value.replace(regexp, '');

    });

    // 허용형 이메일 형태가 아닐시 경고 표시
    var email = document.getElementById("email");
    email.addEventListener('change', function(event) {
        var regexp = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        if (!regexp.test(event.target.value)) {
            event.target.value = "";
            event.target.setAttribute("placeholder", "이메일 형식이 아닙니다.");
        }
    });
    // 이메일 경고 표시 해제
    email.addEventListener('focus', function(event) {
        event.target.value = "";
        event.target.setAttribute("placeholder", "1234abcd@gmail.com");
        event.target.removeAttribute("style");
        event.target.focus();
    });


})()