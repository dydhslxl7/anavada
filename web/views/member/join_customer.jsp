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


            <!-- 회원가입 고객가입 -->
            <!-- form name="join" method="post" onsubmit="return validate();" class="form-inline" enctype="multipart/form-data"-->
            <form name="join" method="post" onsubmit="return validate();" class="form-inline">
                <div class="MBwrap">
                    <div class="MBjoin_wrap">
                        <ul class="joinDepth">
                            <li>약관동의</li>
                            <li class="on">회원정보 입력</li>
                            <li>회원가입 완료</li>
                        </ul>
                        <div class="MBjoin_input">
                            <h3>회원 정보입력</h3>
                            <div>
                                <ul>
                                    <li class="idArea">
                                        <input type="text" name="memberId" title="아이디" id="memberId" class="form-control w40p" placeholder="아이디(필수)" maxlength="12" required/>
                                        <a href="#none" class="btnS1" onclick="return joinldCheck();">중복확인</a>
                                        <p>4자이상 12자이내 입력하세요. 대소문자 구분, 영문, 숫자, 특수문자 입력 가능.</p>
                                    </li>
                                    <li class="pwArea"><input type="password" name="memberPwd" title="비밀번호" id="memberPwd" class="form-control w40p" placeholder="비밀번호(필수)" maxlength="12" required/><p>4자이상 12자이내 입력하세요. 대소문자 구분, 영문, 숫자, 특수문자 입력 가능.</p></li>
                                    <li class="pwArea2">
                                        <input type="password" name="memberPwd2" title="비밀번호 재확인" class="form-control w40p" placeholder="비밀번호 재입력(필수)" maxlength="12" required/>
                                    </li>
                                    <li style="margin-bottom: 10px;"><input type="text" name="memberName" id="memberName" title="이름" class="form-control w50p" placeholder="이름(필수)" required/></li>
                                    <li><input type="email" name="memberEmail" id="memberEmail" title="이메일" class="form-control w50p" placeholder="@naver.com(필수)" required/>
                                        <a href="#none" class="btnS1" onclick="return joinEmailCheck();">인증번호 발송</a>
                                        <p>네이버 이메일을 입력해주세요. 네이버 이메일만 인증번호 발송이 가능합니다.</p>
                                    </li>
                                    <li class="certifyArea" style="margin-bottom: 10px;">
                                        <input type="text" name="emailAuth" id="emailAuth" title="인증번호" class="form-control w50p" placeholder="인증번호 입력" required/>
                                        <a href="#none" class="btnS1" onclick="return joinEmailAuthOK();">인증확인</a>
                                    </li>
                                    <li class="telArea"><input type="tel" name="memberPhone" id="memberPhone" title="휴대폰번호" class="form-control w50p" placeholder="휴대폰 번호 '-' 포함 입력(필수)" required/></li>
                                </ul>
                            </div>
                            <p class="MBjoin_input_btn">
			                    <input type="reset" value="작성취소" title="홈으로 이동" onclick="location.href='/anavada/'">
			                    <input type="submit" value="작성완료" title="회원가입 완료" onclick="action='/anavada/join.cp'">
                            </p>
                        </div>
                    </div>
                </div>
            </form>
            <!-- 회원가입 고객가입 끝 -->

        </div>
        <!-- 컨텐츠 끝 -->

    <%@ include file="../include/footer.jsp" %>
    </div>
</body>
</html>