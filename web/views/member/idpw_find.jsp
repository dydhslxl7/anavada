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
                            <li class="glyphicon glyphicon-menu-right"><a href="#none">회원정보 찾기</a></li>
                        </ul>
                    </div>
                    <h2><span>MEMBERS</span></h2>
                    <h3>회원가입 시 인증한 이메일로 회원정보를 찾으실 수 있습니다.</h3>
                </div>
            </div>
            <!--서브 비주얼/타이틀 끝-->


            <!-- 회원정보 찾기 -->
            <form name="idpw" method="post" action="/anavada/mfind">
			<legend>회원정보 찾기</legend>
			<div class="MBwrap">
			    <div class="MBidpw">
			        <h1>회원정보 찾기</h1>
			        <h2>회원가입시 등록하신 이메일과 휴대폰 번호를 입력해 주세요.<br/>인증된 이메일로 회원정보를 보내드립니다. </h2>
			        <div>
			            <input type="email" name="memberEmail" title="이메일" class="form-control w100p" placeholder="@naver.com"/ style="margin-bottom:0;">
			            <p style="margin-bottom:10px;">네이버 이메일로 인증된 계정만 회원정보 발송이 가능합니다.</p>
			            <input type="tel" name="memberPhone" title="휴대폰 번호" class="form-control w100p" placeholder="'-' 포함 입력"/>
			            <input type="submit" value="확인"class="idpw_btn" >
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