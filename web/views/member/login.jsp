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
                            <li class="glyphicon glyphicon-menu-right"><a href="#none">로그인</a></li>
                        </ul>
                    </div>
                    <h2><span>MEMBERS</span></h2>
                    <h3>지금 로그인 하셔서 'Anavada'의 서비스를 이용하세요.</h3>
                </div>
            </div>
            <!--서브 비주얼/타이틀 끝-->

            <!-- 로그인 -->
            <form name="login" method="post" action="/anavada/login.cp">
            <div class="MBwrap">
                <div class="MBlogin">
                    <h1>로그인</h1>
                    <h2>'Anavada'에 오신것을 환영합니다.</h2>
                    <div>
                        <input type="text" name="memberId" title="아이디" class="form-control w100p" placeholder="아이디를 입력해주세요."/>
                        <input type="password" name="memberPwd" title="비밀번호" class="form-control w100p" placeholder="비밀번호를 입력해주세요."/>

                        <div class="join_btn"><a href="join_agree.jsp">회원가입</a><a href="idpw_find.jsp">회원정보 찾기</a></div>
						<input type="submit" value="로그인" class="login_btn">
                    </div>
                </div>
            </div>
            </form>
            <!-- 로그인 끝 -->


        </div>
        <!-- 컨텐츠 끝 -->

    <%@ include file="../include/footer.jsp" %>
    </div>
</body>
</html>