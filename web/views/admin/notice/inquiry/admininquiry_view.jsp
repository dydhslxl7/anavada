<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="inquiry.model.vo.Inquiry, admin.notice.answer.model.vo.Answer"%>
<%
	Inquiry inquiry = (Inquiry)request.getAttribute("inquiry");
	int currentPage = ((Integer)request.getAttribute("page")).intValue();
	String selected = null;
	String keyword = null;
	if(request.getAttribute("selected") != null && request.getAttribute("keyword") != null) {
		selected = (String)request.getAttribute("selected");
		keyword = (String)request.getAttribute("keyword");
	}
	Answer answer = (Answer)request.getAttribute("answer");
%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../../../include/head.jsp" %> 
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
    <div id="wrap">
        <%@ include file="../../../include/header.jsp" %>

        <!-- 컨텐츠 -->
        <div id="content">

			<!--서브 비주얼/타이틀-->
            <div class="visual-sub-vagas notice-vagas">
                <div class="vsv-copy sub-title">
                   <div>
                        <ul class="navi">
                            <li><a href="#none">홈</a></li>
                            <li class="glyphicon glyphicon-menu-right"><a href="#none">고객센터</a></li>
                            <li class="glyphicon glyphicon-menu-right"><a href="#none">공지사항</a></li>
                        </ul>
                    </div>
                    <h2><span>공지사항</span></h2>
                    <h3>'Anavada'의 소식을 빠르게 확인할 수 있는 공간입니다.</h3>
                </div>
            </div>
            <!--서브 비주얼/타이틀 끝-->

            <!-- 상세 -->
            <div class="view-area">
                <h2><span class="inquiry">관리자용 문의 상세뷰</span> <%= inquiry.getIqTitle() %></h2>
                <ul>
                    <li><span>작성자 : </span><%= inquiry.getIqId() %></li>
                    <li><span>등록일 : </span><%= inquiry.getIqDate() %></li>
                    <% if(inquiry.getIqOriginal() != null) { %>
                    <li><span>첨부파일 : </span><a href="/anavada/ifdown?ofile=<%= inquiry.getIqOriginal() %>&rfile=<%= inquiry.getIqRename() %>" download><%= inquiry.getIqOriginal() %></a></li>
                    <% } if(inquiry.getIqOriginal2() != null) { %>
                    <li><a href="/anavada/ifdown?ofile=<%= inquiry.getIqOriginal2() %>&rfile=<%= inquiry.getIqRename2() %>" download><%= inquiry.getIqOriginal2() %></a></li>
                    <% } if(inquiry.getIqOriginal3() != null) { %>
                    <li><a href="/anavada/ifdown?ofile=<%= inquiry.getIqOriginal3() %>&rfile=<%= inquiry.getIqRename3() %>" download><%= inquiry.getIqOriginal3() %></a></li>
                    <% } %>
                </ul>

                <div class="view-ctn"><%= inquiry.getIqContent().replace("\r\n", "<br>") %></div>
				
				<p class="warning_text" style="color:red;">
                    *삭제한 게시글은 복구가 불가능 하오니 신중하게 선택하시기 바랍니다.
                </p>
				
				<% if(selected == null && keyword == null) { %>
                <div class="view-btn">
                    <a href="/anavada/ailist.ss?page=<%= currentPage %>" class="btn btn-list">목록</a>
                    <a href="/anavada/aidelete?no=<%= inquiry.getIqNo() %>&rfile=<%= inquiry.getIqRename() %>&rfile2=<%= inquiry.getIqRename2() %>&rfile3=<%= inquiry.getIqRename3() %>" class="btn btn-delete">삭제</a>
                </div>
                <% }else { %>
                <div class="view-btn">
                    <a href="/anavada/aisearch?page=<%= currentPage %>&selected=<%= selected %>&keyword=<%= keyword %>" class="btn btn-list">목록</a>
                </div>
                <% } %>
                
                
               <!-- 댓글 --> 
<!-- *********************************************************************************** -->                
                  <div class="cmt_wrap">
                <% if(answer == null) { %>
                    <form action="aainsert.ss" method="post">
                    <input type="hidden" name="id" value="<%= inquiry.getIqId() %>">
                    <input type="hidden" name="no" value="<%= inquiry.getIqNo() %>">
                        <fieldset>
                            <div class="cmt_form">
                                <h4 class="cmt_head">문의답변</h4>
                                <div class="cmt_body">
<textarea name="content" style="resize: none; width:100%; min-height:100px; max-height:100px;" onfocus="this.value='';">비방글은 작성하실 수 없습니다.</textarea>
                                    <div class="cmt_ok"><input type="submit" value="등록"></div>
                                </div>
                            </div>
                        </fieldset>
                    </form>
<!-- *********************************************************************************** -->                    
                    <% }else { %>
                    <ul class="cmt_con">
                        <li>
                            <div>
                                <h4 style="font-size:15pt"> ● 관리자 답변입니다.</h4><span><%= answer.getAnDate() %></span>
                            </div><br>
                            <p style="font-size:12pt"><%= answer.getAnContent().replace("\r\n", "<br>") %></p>
                        </li>
                        
                    
                </div>
                <a href="/anavada/aaupdateview?page=<%= currentPage %>&iqNo=<%= answer.getIqNo() %>&anNo=<%= answer.getAnNo() %>" class="btn btn-list" style="background-color:lightgray; border-color:white; color:white;">수정</a>
				<a href="/anavada/aadelete?page=<%= currentPage %>&iqNo=<%= answer.getIqNo() %>&anNo=<%= answer.getAnNo() %>" class="btn btn-list" style="background-color:lightgray; border-color:white; color:white;">삭제</a>
				<% } %>
            </div>
            <!-- 상세 끝 -->

        </div>
        <!-- 컨텐츠 끝 -->

        <%@ include file="../../../include/footer.jsp" %>
    </div>
</body>
</html>