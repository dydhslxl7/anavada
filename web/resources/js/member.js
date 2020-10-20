
//
//
////암호일치 확인
//function validate() {
//	var pwdValue = document.getElementById("memberPwd").value;
//	var pwdValue2 = document.getElementById("memberPwd2").value;
//	if (pwdValue !== pwdValue2) {
//		alert("암호와 암호 확인의 값이 일치하지 않습니다.");
//		document.getElementById("memberPwd").select();
//		return false; //전송 취소함
//	}
//	return true; //전송함
//}

//*********로그인*********//
//아이디 중복 체크 확인을 위한 ajax 실행 처리용 함수
function joinldCheck() {
	$.ajax({
		url : "/anavada/idchk",
		type : "post",
		data : {
			memberId : $("#memberId").val()
		}, // input에 입력된 값을 가져올땐 .val() 작성
		success : function(data) {
			if (data == "ok") { // == 같다. 표현
				alert("사용 가능한 아이디입니다.");
				$("#memberPwd").focus();
			} else {
				alert("이미 사용중인 아이디입니다.\n다시 입력하세요.");
				$("#memberId").select();
			}
		},
		error : function(jqXHR, textstatus, errorthrown) { // jqXHR, textstatus, errorthrown : 에러표시 함수가 있음.
			console.log("error : " + jqXHR + ", " + textstatus + ", "
					+ errorthrown);
		}
	});
	return false;
}
//이메일 인증코드 전송
function joinEmailCheck() {
	$.ajax({
		url : "/anavada/memail",
		type : "post",
		data : {
			memberEmail : $("#memberEmail").val()
		}, // input에 입력된 값을 가져올땐 .val() 작성
		success : function(data) {
			if (data == "ok") { // == 같다. 표현
				alert("이메일 인증번호가 이메일로 발송되었습니다.\n확인해주세요.");
				$("#emailAuth").focus();
			} else {
				alert("잘못된 이메일입니다.\n다시 입력해주세요.");
				$("#memberEmail").focus();
			}
		},
		error : function(jqXHR, textstatus, errorthrown) { // jqXHR, textstatus, errorthrown : 에러표시 함수가 있음.
			console.log("error : " + jqXHR + ", " + textstatus + ", "
					+ errorthrown);
		}
	});
	return false;
}
//이메일 인증코드 확인
function joinEmailAuthOK() {
	$.ajax({
		url : "/anavada/memail2",
		type : "post",
		data : {
			emailAuth : $("#emailAuth").val()
		}, // input에 입력된 값을 가져올땐 .val() 작성
		success : function(data) {
			if (data == "ok") { // == 같다. 표현
				alert("이메일 인증이 완료되었습니다.");
				$("#memberPhone").focus();
			} else {
				alert("이메일 인증이 실패했습니다.\n다시 입력해주세요.");
				$("#emailAuth").select();
			}
		},
		error : function(jqXHR, textstatus, errorthrown) { // jqXHR, textstatus, errorthrown : 에러표시 함수가 있음.
			console.log("error : " + jqXHR + ", " + textstatus + ", "
					+ errorthrown);
		}
	});
	return false;
}
//*********로그인 끝*********//

//*********회원가입*********//
function agreeChkOn() {
	if ((document.getElementById("ChkY").checked && document.getElementById("ChkY2").checked) == true ) {
		document.location.href = "join_customer.jsp";
		return false;
	} else if (document.getElementById("ChkN").checked == true) {
		alert("동의 체크시 다음페이지로 이동 가능합니다.");
		return false;
	} else if (document.getElementById("ChkN2").checked == true) {
		alert("동의 체크시 다음페이지로 이동 가능합니다.");
		return false;
	}
}
//*********회원가입 끝*********//
	
	
	
//*********회원정보 변경*********//
//비밀번호 변경
function pwdChange(){
	var memberPwd = document.getElementById('memberPwd').value;
	var memberPwd2 = document.getElementById('memberPwd2').value;
	var memberPwd3 = document.getElementById('memberPwd3').value;
	
	if(memberPwd == null || memberPwd2 == null || memberPwd3 == null || memberPwd == "" || memberPwd2 == "" || memberPwd3 == ""){
		alert("공백은 포함할 수 없습니다.\\n다시 입력해주세요.")
		return false;
	} else if(memberPwd2 != memberPwd3){
		alert("새로운 비밀번호가 일치하지 않습니다.\\n다시 입력해주세요.");
		return false;
	} else {
		return true;
	}
}
//패스워드 변경
function myInfoPwd() {
	$.ajax({
		url : "/anavada/myInfoPwdChange.cp",
		type : "post",
		data : {
			memberId : $("#memberId").val(),
			memberPwd : $("#memberPwd").val(),
			newPwd : $("#newPwd").val(),
			newPwdOK : $("#newPwdOK").val()
		}, // input에 입력된 값을 가져올땐 .val() 작성
		success : function(data) {
			if (data == "ok") { // == 같다. 표현
				alert("비밀번호 변경이 완료되었습니다.");
				location.reload();
				$("#emailAuth").focus();
			} else if (data == "not") {
				alert("기존 비밀번호를 입력해주세요.");
				$("#memberPwd").focus();
			} else if (data == "not2") {
				alert("기존 비밀번호와 일치하지 않습니다.");
				$("#memberPwd").focus();
			} else if (data == "not3") {
				alert("새 비밀번호를 입력해주세요.");
				$("#newPwd").focus();
			} else if (data == "not4") {
				alert("새 비밀번호를 재입력해주세요.");
				$("#newPwdOK").focus();
			} else if (data == "not5") {
				alert("새 비밀번호와 새 비밀번호 입력값이 일치하지 않습니다.");
				$("#newPwdOK").focus();
			} else {
				
			}
		},
		error : function(jqXHR, textstatus, errorthrown) { // jqXHR, textstatus, errorthrown : 에러표시 함수가 있음.
			console.log("error : " + jqXHR + ", " + textstatus + ", "
					+ errorthrown);
		}
	});
	return false;
}

//이메일 인증코드 전송
function joinEmailCheck() {
	$.ajax({
		url : "/anavada/memail",
		type : "post",
		data : {
			memberEmail : $("#memberEmail").val()
		}, // input에 입력된 값을 가져올땐 .val() 작성
		success : function(data) {
			if (data == "ok") { // == 같다. 표현
				alert("이메일 인증번호가 이메일로 발송되었습니다.\n확인해주세요.");
				$("#emailAuth").focus();
			} else {

			}
		},
		error : function(jqXHR, textstatus, errorthrown) { // jqXHR, textstatus, errorthrown : 에러표시 함수가 있음.
			console.log("error : " + jqXHR + ", " + textstatus + ", "
					+ errorthrown);
		}
	});
	return false;
}

//이메일 인증코드 확인
function EmailAuthOK() {
	$.ajax({
		url : "/anavada/memail2",
		type : "post",
		data : {
			emailAuth : $("#emailAuth").val()
		}, // input에 입력된 값을 가져올땐 .val() 작성
		success : function(data) {
			if (data == "ok") { // == 같다. 표현
				alert("이메일 인증이 완료되었습니다.");
				$("#memberPhone").focus();
			} else {
				alert("이메일 인증이 실패했습니다.\n다시 입력해주세요.");
				$("#emailAuth").select();
			}
		},
		error : function(jqXHR, textstatus, errorthrown) { // jqXHR, textstatus, errorthrown : 에러표시 함수가 있음.
			console.log("error : " + jqXHR + ", " + textstatus + ", "
					+ errorthrown);
		}
	});
	return false;
}
//*********회원정보 변경 끝*********//



