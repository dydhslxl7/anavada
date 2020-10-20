<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="faq.model.vo.Faq"%>
<%
	Faq faq = (Faq)request.getAttribute("faq");
	int currentPage = (Integer)request.getAttribute("page");
	String selected = null;
	String keyword = null;
	if(request.getAttribute("selected") != null && request.getAttribute("keyword") != null) {
		selected = (String)request.getAttribute("selected");
		keyword = (String)request.getAttribute("keyword");
	}
	String category = null;
	switch(faq.getFaqCategory()) {
    case 1 : category = "회원정보"; break;
    case 2 : category = "중고거래"; break;
    case 3 : category = "커뮤니티"; break;
    case 4 : category = "지역축제"; break;
    }
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
                <h2><span>관리자용 FAQ 상세뷰</span> <%= faq.getFaqTitle() %></h2>
                <ul>
                    <li><span>작성자 : </span><%= faq.getFaqId() %></li>
                    <li><span>등록일 : </span><%= faq.getFaqDate() %></li>
                    <li><span>카테고리 : </span><%= category %></li>
                </ul>

                <div class="view-ctn">
                <%= faq.getFaqContent() %>
                </div>

                <div class="view-btn">
                    <% if(request.getParameter("selected") != null && request.getParameter("keyword") != null) { %>
                    <a href="/anavada/afupdateview?no=<%= faq.getFaqNo() %>&page=<%= currentPage %>" class="btn btn-list">수정</a>
                    <a href="/anavada/afsearch?page=<%= currentPage %>&selected=<%= selected %>&keyword=<%= keyword %>" class="btn btn-list">목록</a>
                    <% }else { %>
                    <a href="/anavada/afupdateview?no=<%= faq.getFaqNo() %>&page=<%= currentPage %>" class="btn btn-list">수정</a>
                    <a href="/anavada/aflist.ss?page=<%= currentPage %>" class="btn btn-list">목록</a>
                    <% } %>
                </div>
            </div>
            <!-- 상세 끝 -->

        </div>
        <!-- 컨텐츠 끝 -->

        <%@ include file="../../../include/footer.jsp" %>
    </div>
</body>
</html>