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
    <%@ include file="../include/head.jsp" %> 
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
    <div id="wrap">
        <%@ include file="../include/header.jsp" %>

        <!-- 컨텐츠 -->
        <div id="content">

            <!--서브 비주얼/타이틀-->
            <div class="visual-sub-vagas notice-vagas">
                <div class="vsv-copy sub-title">
                   <div>
                        <ul class="navi">
                            <li><a href="/anavada">홈</a></li>
                            <li><a href="/anavada/nlist">고객센터</a></li>
                            <li class="glyphicon glyphicon-menu-right"><a href="/anavada/ilist">문의하기</a></li>
                        </ul>
                    </div>
                    <h2><span>문의하기</span></h2>
                    <h3>관리자에게 문의할 수 있는 공간입니다.</h3>
                </div>
            </div>
            <!--서브 비주얼/타이틀 끝-->

            <!-- 상세 -->
            <div class="view-area">
                <h2><span class="inquiry">문의</span> <%= inquiry.getIqTitle() %></h2>
                <ul>
                    <li><span>작성자 : </span><%= inquiry.getIqId() %></li>
                    <li><span>등록일 : </span><%= inquiry.getIqDate() %></li>
                    <li><span>첨부파일 : </span></li>
                    <% if(inquiry.getIqOriginal() != null) { %>
                    <li><a href="/anavada/ifdown?ofile=<%= inquiry.getIqOriginal() %>&rfile=<%= inquiry.getIqRename() %>" download><%= inquiry.getIqOriginal() %></a></li>
                    <% } if(inquiry.getIqOriginal2() != null) { %>
                    <li><a href="/anavada/ifdown?ofile=<%= inquiry.getIqOriginal2() %>&rfile=<%= inquiry.getIqRename2() %>" download><%= inquiry.getIqOriginal2() %></a></li>
                    <% } if(inquiry.getIqOriginal3() != null) { %>
                    <li><a href="/anavada/ifdown?ofile=<%= inquiry.getIqOriginal3() %>&rfile=<%= inquiry.getIqRename3() %>" download><%= inquiry.getIqOriginal3() %></a></li>
                    <% } %>
                </ul>

                <div class="view-ctn"><%= inquiry.getIqContent().replace("\r\n","<br>") %></div>	
				
				<% if(selected == null && keyword == null) { %>
                <div class="view-btn">
                    <a href="/anavada/ilist?page=<%= currentPage %>" class="btn btn-list" style="background-color: #fff; color:#FF8A3D;">목록</a>
                    <a href="/anavada/idelete?no=<%= inquiry.getIqNo() %>&rfile=<%= inquiry.getIqRename() %>&rfile2=<%= inquiry.getIqRename2() %>&rfile3=<%= inquiry.getIqRename3() %>" class="btn btn-list" style="background-color: #fff; color:#FF8A3D;">삭제</a>
                    <a href="/anavada/iupdateview?no=<%= inquiry.getIqNo() %>&page=<%= currentPage %>" class="btn btn-list">수정</a>
                </div>
                <% }else { %>
                <div class="view-btn">
                    <a href="/anavada/isearch?page=<%= currentPage %>&selected=<%= selected %>&keyword=<%= keyword %>" class="btn btn-list">목록</a>
                    <a href="/anavada/idelete?no=<%= inquiry.getIqNo() %>&rfile=<%= inquiry.getIqRename() %>&rfile2=<%= inquiry.getIqRename2() %>&rfile3=<%= inquiry.getIqRename3() %>" class="btn btn-list" style="background-color: #fff; color:#FF8A3D;">삭제</a>
                    <a href="/anavada/iupdateview?no=<%= inquiry.getIqNo() %>&page=<%= currentPage %>" class="btn btn-list">수정</a>
                </div>
                <% } %>
                
                
                <% if(answer != null) { %>
                <div class="cmt_wrap">
                    <ul class="cmt_con">
                        <li>
                            <div>
                                <h4 style="font-size:13pt;">● user : 관리자</h4><span><%= answer.getAnDate() %></span>
                            </div><br>
                            <p style="font-size:11pt;"><%= answer.getAnContent().replace("\r\n", "<br>") %></p>
                        </li>
                    </ul>
                </div>
                <% } %>
                
            </div>
            <!-- 상세 끝 -->

        </div>
        <!-- 컨텐츠 끝 -->

        <%@ include file="../include/footer.jsp" %>
    </div>
</body>
</html>