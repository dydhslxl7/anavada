<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../include/head.jsp" %> 
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
    <div id="wrap">
        <%@ include file="../include/header.jsp" %>

        <!-- 컨텐츠 -->
        <div id="content">

            <!--서브 비주얼/타이틀-->
            <div class="visual-sub-vagas member-vagas">
                <div class="vsv-copy sub-title">
                   <div>
                        <ul class="navi">
                            <li><a href="#none">홈</a></li>
                            <li class="glyphicon glyphicon-menu-right"><a href="#none">MEMBER</a></li>
                            <li class="glyphicon glyphicon-menu-right"><a href="#none">회원정보 변경</a></li>
                        </ul>
                    </div>
                    <h2><span>MEMBERS</span></h2>
                    <h3>회원정보 찾기를 통해 발급받은 인증번호를 통해<br/>인증번호 확인 후 비밀번호를 변경할 수 있습니다.</h3>
                </div>
            </div>
            <!--서브 비주얼/타이틀 끝-->


            <!-- 패스워드 바꾸기 -->
            <form name="idpw" method="post" action="/anavada/pwdChange.cp">
            <legend>패스워드 바꾸기</legend>
			<div class="MBwrap">
			    <div class="MBidpw">
			        <h1>회원정보 찾기</h1>
			        <h2>회원정보 찾기를 통해 발급받은 인증번호를 통해<br/>인증번호 확인 후 비밀번호를 변경할 수 있습니다.</h2>
			        <div>
			            <input type="text" name="AuthenticationUser" id="AuthenticationUser" title="인증번호" class="form-control w100p" placeholder="발급 인증번호 입력"/>
			            <input type="text" name="memberId" title="아이디" class="form-control w100p" placeholder="아이디" maxlength="12"/>
			            <input type="password" name="memberPwd" title="새 비밀번호" class="form-control w100p" placeholder="새 비밀번호 입력(4자이상 12자이내)" maxlength="12"/>
			            <input type="password" name="memberPwd2" title="새 비밀번호 재입력" class="form-control w100p" placeholder="새 비밀번호 재입력" maxlength="12"/>
			            <input type="submit" value="비밀번호 변경하기"class="idpw_btn" >
			        </div>
			    </div>
			</div>
            </form>
            <!-- 회원정보 찾기 끝 -->
        </div>
        <!-- 컨텐츠 끝 -->
    <%@ include file="../include/footer.jsp" %>
    </div>
</body>
</html>