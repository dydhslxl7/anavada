<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="inquiry.model.vo.Inquiry"%>
<%
	Inquiry inquiry = (Inquiry)request.getAttribute("inquiry");
	int currentPage = (Integer)request.getAttribute("page");
	
	String[] checked = new String[4];
	switch(inquiry.getIqType()){
	case "회원정보" : checked[0] = "checked"; break;
	case "오류" : checked[1] = "checked"; break;
	case "제안하기" : checked[2] = "checked"; break;
	case "기타" : checked[3] = "checked"; break;
	}
	
	boolean[] fileExist = new boolean[3];
%>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript" src="/anavada/resources/js/jquery-3.5.1.min.js"></script>
    <%@ include file="../include/head.jsp" %>
    <script type="text/javascript">
    function deleteFile(no, value, page) {
    	if(confirm("삭제하시겠습니까?"))
    		location.href = "/anavada/ideletefile?no="+no+"&value="+value+"&page="+page;
    }
    </script>
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
                            <li><a href="/anavada">홈</a></li>
                            <li><a href="/anavada/mypage.cp?memberId=<%= loginMember.getMemberId() %>">MYPAGE</a></li>
                            <li class="glyphicon glyphicon-menu-right"><a href="/anavada/miq?member=<%= loginMember.getMemberId() %>">문의하기조회</a></li>
                        </ul>
                    </div>
                    <h2><span>MYPAGE</span></h2>
                    <h3>'Anavada'의 내 정보를 확인할 수 있습니다..</h3>
                </div>
            </div>
            <!--서브 비주얼/타이틀 끝-->
			    
            <!--서브 카테고리-->
            <div class="MyTap">
                <ul>
                    <li><a href="/anavada/mypage.cp?memberId=<%= loginMember.getMemberId() %>">정보수정</a></li>
                    <li><a href="/anavada/myjblist?memberid=<%=loginMember.getMemberId()%>">중고거래조회</a></li>
                    <li><a href="/anavada/mycmnt?memberID=<%=loginMember.getMemberId()%>">커뮤니티조회</a></li>
                    <li class="active"><a href="/anavada/miq?member=<%= loginMember.getMemberId() %>">문의하기조회</a></li>
                    <li><a href="/anavada/dbomylist.ss?member=<%= loginMember.getMemberId()%>">신고하기조회</a></li>
                </ul>
            </div>
            <!--서브 카테고리 끝-->

            <!-- 글쓰기 -->
            <div class="write-area">
                <form action="/anavada/iupdateiq?my=ok" method="post" enctype="multipart/form-data">
				<input type="hidden" value="<%= inquiry.getIqNo() %>" name="no">
				<input type="hidden" value="<%= currentPage %>" name="page">
				<input type="hidden" value="<%= inquiry.getIqOriginal() %>" name="ofile">
				<input type="hidden" value="<%= inquiry.getIqRename() %>" name="rfile">
				<input type="hidden" value="<%= inquiry.getIqOriginal2() %>" name="ofile2">
				<input type="hidden" value="<%= inquiry.getIqRename2() %>" name="rfile2">
				<input type="hidden" value="<%= inquiry.getIqOriginal3() %>" name="ofile3">
				<input type="hidden" value="<%= inquiry.getIqRename3() %>" name="rfile3">
                    <h2>문의글 수정</h2>

                    <table>
                        <colgroup>
                            <col width="20%">
                            <col width="80%">
                        </colgroup>
                        <tbody>
                            <tr>
                                <td>카테고리</td>
                                <td>
                                    <label><input type="radio" name="type" value="member" <%= checked[0] %>>회원정보</label>
                                    <label><input type="radio" name="type" value="error" <%= checked[1] %>>오류</label>
                                    <label><input type="radio" name="type" value="proposal" <%= checked[2] %>>제안하기</label>
                                    <label><input type="radio" name="type" value="etc" <%= checked[3] %>>기타</label>
                                </td>
                            </tr>
                            <tr>
                                <td>제목</td>
                                <td><input type="text" name="title" class="form-control w100p" placeholder="제목" value="<%= inquiry.getIqTitle() %>"></td>
                            </tr>
                            <tr>
                                <td>내용</td>
                                <td><textarea name="content" class="form-control" style="resize: none; width:100%; min-height:300px; max-height:300px;"><%= inquiry.getIqContent() %></textarea></td>
                            </tr>
                            <tr class="filebox">
                                <td>첨부파일</td>
                                <td>
                                <% if(inquiry.getIqOriginal() != null) { fileExist[0] = true;%>
                                	<%= inquiry.getIqOriginal() %> &nbsp; <button onclick="deleteFile(<%= inquiry.getIqNo() %>, 1, <%= currentPage %>); return false;">삭제</button> &nbsp; &nbsp; &nbsp; &nbsp;
                                <% } if(inquiry.getIqOriginal2() != null) { fileExist[1] = true;%>
                                	<%= inquiry.getIqOriginal2() %> &nbsp; <button onclick="deleteFile(<%= inquiry.getIqNo() %>, 2, <%= currentPage %>); return false;">삭제</button> &nbsp; &nbsp; &nbsp; &nbsp;
                                <% } if(inquiry.getIqOriginal3() != null) { fileExist[2] = true;%>
                               		<%= inquiry.getIqOriginal3() %> &nbsp; <button onclick="deleteFile(<%= inquiry.getIqNo() %>, 3, <%= currentPage %>); return false;">삭제</button>
                                <% } %>
                                
                                <% for(int i=0; i<fileExist.length; i++) { 
                                	if(!fileExist[i]) {	
                                		switch(i) {
                                		case 0 : %><input type="file" name="newofile1" class="mb5"><%break;
                                		case 1 : %><input type="file" name="newofile2" class="mb5"><%break;
                                		case 2 : %><input type="file" name="newofile3"></td><%break;  } } } %>
                            </tr>
                        </tbody>
                    </table>

                    <div class="write-btn">
                        <a onclick="javascipt:history.go(-1); return false;" class="btn btn-list">뒤로가기</a>
                    	<input type="reset" class="btn btn-success" value="수정취소" style="width:100px;">
                        <input type="submit" class="btn btn-success" value="수정하기" style="width:100px;">
                    </div>
                </form>
            </div>
            <!-- 글쓰기 끝 -->

        </div>
        <!-- 컨텐츠 끝 -->

        <%@ include file="../include/footer.jsp" %>
    </div>
</body>
</html>