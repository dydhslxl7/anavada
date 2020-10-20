<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/head.jsp"%>
</head>
<body oncontextmenu="return false" onselectstart="return false"
	ondragstart="return false">
	<div id="wrap">
		<%@ include file="../include/header.jsp"%>

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
					<h2>
						<span>MEMBERS</span>
					</h2>
					<h3>회원가입으로 'Anavada'의 서비스를 이용하실 수 있습니다.</h3>
				</div>
			</div>
			<!--서브 비주얼/타이틀 끝-->

			<!-- 회원가입 약관동의 -->
			<form name="form" method="post" action="#">
				<div class="MBwrap">
					<div class="MBjoin-agree">
						<ul class="joinDepth">
							<li class="on">약관동의</li>
							<li>회원정보 입력</li>
							<li>회원가입 완료</li>
						</ul>
						<div class="MBjoin-content">
							<div>
								<h1>이용약관 동의</h1>
								<p>
									제 1 조 (목적)<br /> <br /> 이 약관은 Anavada 주식회사 ("회사" 또는
									"Anavada")가 제공하는 Anavada 서비스의 이용과 관련하여 회사와 회원과의 권리, 의무 및 책임사항,
									기타 필요한 사항을 규정함을 목적으로 합니다.
								</p>
								<div>
									<label><input type="radio" name="agreeChk" id="ChkY" value="Y" title="동의함" checked/> 동의합니다.</label> &nbsp;
									&nbsp; &nbsp; <label><input type="radio" name="agreeChk" id="ChkN" value="N" title="동의안함"/>동의하지 않습니다.</label>
								</div>
							</div>
							<div>
								<h1>개인정보수집 동의</h1>
								<p>
									Anavada 개인정보 처리방침<br /> <br /> 주식회사 Anavada(이하 “Anavada”라
									합니다)는 정보통신망 이용촉진 및 정보보호 등에 관한 법률, 개인정보보호법 등 관련 법령에 따라 정보주체의
									개인정보를 보호하고 이와 관련한 고충을 신속하고 원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보
									처리방침을 수립 ∙ 공개합니다.
								</p>
								<div>
									<label><input type="radio" name="agreeChk2" id="ChkY2" value="Y" title="동의함" checked/> 동의합니다.</label> &nbsp;
									&nbsp; &nbsp; <label><input type="radio" name="agreeChk2" id="ChkN2" value="N" title="동의안함"/>동의하지 않습니다.</label>
								</div>
							</div>
							<p class="ME-btn">
								<a href="#none" onclick="agreeChkOn()">다음</a>
							</p>
						</div>
					</div>
				</div>
			</form>
			<!-- 회원가입 약관동의 끝 -->

		</div>
		<!-- 컨텐츠 끝 -->

		<%@ include file="../include/footer.jsp"%>
	</div>
</body>
</html>