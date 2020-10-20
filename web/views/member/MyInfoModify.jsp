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
            <div class="visual-sub-vagas mypage-vagas">
                <div class="vsv-copy sub-title">
                   <div>
                        <ul class="navi">
                            <li><a href="#none">홈</a></li>
                            <li class="glyphicon glyphicon-menu-right"><a href="#none">MYPAGE</a></li>
                            <li class="glyphicon glyphicon-menu-right"><a href="#none">정보수정</a></li>
                        </ul>
                    </div>
                    <h2><span>MYPAGE</span></h2>
                    <h3>'Anavada'의 내 정보를 확인할 수 있습니다.</h3>
                </div>
            </div>
            <!--서브 비주얼/타이틀 끝-->
			    
            <!--서브 카테고리-->
            <div class="MyTap">
                <ul>
                    <li class="active"><a href="/anavada/mypage.cp?memberId=<%= loginMember.getMemberId() %>">정보수정</a></li>
                    <li><a href="/anavada/myjblist?memberid=<%=loginMember.getMemberId()%>">중고거래조회</a></li>
                    <li><a href="/anavada/mycmnt?memberID=<%=loginMember.getMemberId()%>">커뮤니티조회</a></li>
                    <li><a href="/anavada/miq?member=<%= loginMember.getMemberId() %>">문의하기조회</a></li>
                    <li><a href="/anavada/dbomylist.ss?member=<%= loginMember.getMemberId()%>">신고하기조회</a></li>
                </ul>
            </div>
            <!--서브 카테고리 끝-->

            
            <!--마이페이지컨텐츠-->
            <div class="mypage_area" style="margin-bottom: 100px;">
               
                <h2 class="mypage_title">나의 정보수정</h2>

                <!-- 프로필 -->
				<dl class="profile">
					<dt>
						<div><%= member.getMemberName() %></div>
					</dt>
					<dd>
						<div><span>이름</span> : <%= member.getMemberName() %></div>
						<div><span>아이디</span> : <%= member.getMemberId() %></div>
						<div><span>전화번호</span> : <%= member.getMemberPhone() %></div>
						<div><span>이메일</span> : <%= member.getMemberEmail() %></div>
						<div><span>가입일</span> : <%= member.getJoinDate() %></div>
					</dd>
				</dl>
				<!-- 프로필 끝 -->
                <!-- form method="post" onsubmit="return validate();" enctype="multipart/form-data"-->
                <form method="post" onsubmit="return validate();">
                <table class="InfoModify_table">
                    <colgroup>
                        <col width="20%">
                        <col width="80%">
                    </colgroup>
                    <tbody>
                        <tr>
                            <td>아이디</td>
                            <td><input type="text" name="memberId" id="memberId" title="아이디" class="form-control w100p" placeholder="아이디" value="<%= member.getMemberId() %>" readonly/></td>
                        </tr>
                        <tr>
                            <td>비밀번호</td>
                            <td><input type="password" name="memberPwd" id="memberPwd" title="비밀번호" class="form-control w100p" placeholder="기존 비밀번호" required/></td>
                        </tr>
                        <tr>
                            <td>신규 비밀번호</td>
                            <td class="newPwd">
                                <input type="password" name="newPwd" id="newPwd" title="신규 비밀번호" class="form-control w70p mb5" placeholder="신규 비밀번호 입력"/><br/>
                                <input type="password" name="newPwdOK" id="newPwdOK" title="신규 비밀번호 재입력" class="form-control w70p" placeholder="신규 비밀번호 재입력"/>
                                <a href="#none" class="btnS1" onclick="return myInfoPwd();">변경하기</a><br/>
                            </td>
                        </tr>
                        <tr>
                            <td>이름</td>
                            <td><input type="text" name="memberName" title="이름" class="form-control w100p" placeholder="이름" value="<%= member.getMemberName() %>" readonly/></td>
                        </tr>
                        <tr>
                            <td>이메일</td>
                            <td class="emailArea">
                                <input type="email" name="memberEmail" id="memberEmail" title="이메일" class="form-control w70p mb5" placeholder="@naver.com(필수)" value="<%= member.getMemberEmail() %>" />
                                <a href="#none" class="btnS1" onclick="return joinEmailCheck();">인증번호 발송</a>
                                <p style="margin-bottom:5px;">네이버 이메일만 인증번호 발송이 가능합니다.</p>

                                <input type="text" name="emailAuth" id="emailAuth" title="인증번호" class="form-control w70p" placeholder="인증번호" value="<%= member.getEmailAuth() %>" />
                                <a href="#none" class="btnS1" onclick="return EmailAuthOK();">인증확인</a>
                            </td>
                        </tr>
                        <tr>
                            <td>휴대폰</td>
                            <td class="telArea"><input type="tel" name="memberPhone" title="휴대폰번호" class="form-control w100p" placeholder="'-' 포함 입력" value="<%= member.getMemberPhone() %>" /></td>
                        </tr>
                        <tr>
                            <td>회원탈퇴</td>
                            <td><button type="button" class="leaveBtn" onclick="memberLeave();">회원탈퇴</button></td>
                        </tr>
                    </tbody>
                </table>

                <div class="write-btn">
                    <a href="javascript:history.go(-1);">이전 페이지</a>
                    <input class="btn btn-list" type="reset" value="수정취소">
                    <input class="btn btn-success" type="submit" value="수정하기" onclick="action='/anavada/mupdate.cp'">
                </div>
				</form>
		        <script>
		      		//탈퇴하기
			        function memberLeave(){
			        	var memberDelete = confirm('탈퇴하면 다시 되돌릴 수 없습니다.\n신중하게 선택해주세요. 탈퇴하시겠습니까?');
			        	if(memberLeave){
			            	location.href ="/anavada/mleave.cp?memberId=<%= member.getMemberId()%>";
			        	} else {
			        	}
			        }
		        </script>
            </div>
        </div>
        <!-- 컨텐츠 끝 -->
    <%@ include file="../include/footer.jsp" %>
    </div>
</body>
</html>