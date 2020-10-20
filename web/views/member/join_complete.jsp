<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="member.model.vo.Member"%>
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
                            <li class="glyphicon glyphicon-menu-right"><a href="#none">회원가입</a></li>
                        </ul>
                    </div>
                    <h2><span>MEMBERS</span></h2>
                    <h3>회원가입으로 'Anavada'의 서비스를 이용하실 수 있습니다.</h3>
                </div>
            </div>
            <!--서브 비주얼/타이틀 끝-->


            <!-- 회원가입 가입완료 -->
            <div class="MBwrap">
                <div class="MBjoin_complete">
                    <ul class="joinDepth">
                        <li>약관동의</li>
                        <li>회원정보 입력</li>
                        <li class="on">회원가입 완료</li>
                    </ul>
                    <div>
                        <h1>회원가입을 진심으로 축하합니다.</h1>
                        <div>
			            	회원님의 비밀번호는 암호화되어 저장되며, 아이디/비밀번호 분실시에는<br/>
				                           회원가입 시 입력하신 이메일 주소를 이용하여 찾을 수 있습니다.
                        </div>
                    </div>
                    <p class="MBjoin_input_btn">
                        <a href="/anavada/">홈으로</a>
                        <a href="login.jsp">로그인</a>
                    </p>
                </div>
            </div>
            <!-- 회원가입 가입완료 끝 -->

        </div>
        <!-- 컨텐츠 끝 -->

    <%@ include file="../include/footer.jsp" %>
    </div>
</body>
</html>