<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="member.model.vo.Member"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../include/admin_head.jsp" %> 
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
    <div id="wrap">
        <%@ include file="../include/admin_header.jsp" %> 

        <div id="admin_container">
    
            <!-- 상단 타이틀 -->
            <div class="admin_title">
                <div class="admin_path">
                    <h3>관리자</h3>
                    <h2>| 정보변경</h2>
                </div>
            </div>
            <!-- //상단 타이틀 -->

            <!-- 본문내용 ---------------------------------------------------------------------------------------------------------------------------------------------------->
            <div class="admin_content">

            <!-- 카테고리 -->
            <form method="post" onsubmit="return validate();">
                <div class="admin-area">
                    <h2>관리자 정보변경</h2>
                    <div>
                        <table>
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
                            </tbody>
                        </table>
                    </div>
	                <div class="write-btn" style="border:none;">
	                    <input class="btn btn-cancel" type="reset" value="수정취소">
	                    <input class="btn btn-success" type="submit" value="수정하기" onclick="action='/anavada/mupdate.cp'">
	                </div>
                </div>
            </form>
            <!-- //카테고리 -->
            </div>
        <%@ include file="../include/admin_footer.jsp" %> 
        </div>
    </div>
</body>
</html>