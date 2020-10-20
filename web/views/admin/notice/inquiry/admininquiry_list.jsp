<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, inquiry.model.vo.Inquiry"%>
<%
	ArrayList<Inquiry> listInquiry = (ArrayList<Inquiry>)request.getAttribute("list");
	int currentPage = (Integer)request.getAttribute("currentPage");
	int totalPage = (Integer)request.getAttribute("totalPage");
	int startPage = (Integer)request.getAttribute("startPage");
	int endPage = (Integer)request.getAttribute("endPage");
	int totalList = (Integer)request.getAttribute("totalList");
	
	String selected = null;
	String keyword = null;
	if(request.getAttribute("selected") != null && request.getAttribute("keyword") != null){
		selected = (String)request.getAttribute("selected");
		keyword = (String)request.getAttribute("keyword");
	}
%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../../include/admin_head.jsp" %> 
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
    <div id="wrap">
        <%@ include file="../../include/admin_header.jsp" %> 

        <div id="admin_container">

            <!-- 상단 타이틀 -->
            <div class="admin_title">
                <div class="admin_path">
                    <h3>문의하기</h3>
                    <h2>| 리스트</h2>
                </div>
            </div>
            <!-- //상단 타이틀 -->

            <!-- 본문내용 -->
            <div class="list-area">


                <!-- 검색영역 -->
				<div class="sort-area">
					<h4>
						전체
						<%= totalList %>개
					</h4>
					<div>
						<form action="/anavada/aisearch" method="post" id="">
							목록 분류 : <select name="selected" class="ListSelect">
								<option value="none" selected disabled>분류 선택</option>
								<option value="title">제목</option>
								<option value="content">내용</option>
								<option value="writer">작성자</option>
							</select> <input type="text" name="keyword" placeholder="검색어를 입력해주세요.">
							<button class="top-search">
								<i class="xi-search"></i>
							</button>
						</form>
					</div>
				</div>
				<!-- 검색영역 끝 -->
				
				<% if(totalList == 0) { %>
				<br><br>
				<div class="list-no" align="center">
					<p>
						<img src="/anavada/resources/images/btnIcn/icn_big_listNo.png"
							alt="" title="" />
					</p>
					<h1>목록이 없습니다.</h1>
				</div><br><br>

				<% }else { %>
                <table class="memberTable">
                    <tbody>
                        <tr><th colspan="4">제목</th></tr>
                        <% if(selected != null && keyword != null) {%>
                        <% for(Inquiry i : listInquiry) { %>
                        <tr>
                            <td class="number" onclick="location.href='/anavada/aidetail?page=<%= currentPage %>&no=<%= i.getIqNo() %>&selected=<%= selected %>&keyword=<%= keyword %>'"><%= i.getIqNo() %></td>
                            <td class="title" onclick="location.href='/anavada/aidetail?page=<%= currentPage %>&no=<%= i.getIqNo() %>&selected=<%= selected %>&keyword=<%= keyword %>'">
                                <h2><span>문의</span><%= i.getIqTitle() %></h2>
                                <ul>
                                    <li>작성자 : <%= i.getIqId() %></li>
                                    <li>작성일 : <%= i.getIqDate() %></li>
                                </ul>
                            </td>
                            <td class="fileDown" onclick="location.href='/anavada/aidetail?page=<%= currentPage %>&no=<%= i.getIqNo() %>&selected=<%= selected %>&keyword=<%= keyword %>'">
                            <% if(i.getIqOriginal() != null || i.getIqOriginal2() != null || i.getIqOriginal3() != null) { %>
                            <i class="glyphicon glyphicon-floppy-saved" onclick="location.href='/anavada/aidetail?page=<%= currentPage %>&no=<%= i.getIqNo() %>&selected=<%= selected %>&keyword=<%= keyword %>'"></i>
                            <% } %>
                            </td>
                            
                            <% if(i.getIqAnswer().equals("Y")) { %>
                            <td class="declare_btn"><span><i class="glyphicon glyphicon-bell"></i>처리완료</span></td>
                            <% }else { %><td></td><% } %>
                        </tr>
                        <% } }else { %>
                        <% for(Inquiry i : listInquiry) { %>
                        <tr>
                            <td class="number" onclick="location.href='/anavada/aidetail?page=<%= currentPage %>&no=<%= i.getIqNo() %>'"><%= i.getIqNo() %></td>
                            <td class="title" onclick="location.href='/anavada/aidetail?page=<%= currentPage %>&no=<%= i.getIqNo() %>'">
                                <h2><span>문의</span><%= i.getIqTitle() %></h2>
                                <ul>
                                    <li>작성자 : <%= i.getIqId() %></li>
                                    <li>작성일 : <%= i.getIqDate() %></li>
                                </ul>
                            </td>
                            <td class="fileDown" onclick="location.href='/anavada/aidetail?page=<%= currentPage %>&no=<%= i.getIqNo() %>'">
                            <% if(i.getIqOriginal() != null || i.getIqOriginal2() != null || i.getIqOriginal3() != null) { %>
                            <i class="glyphicon glyphicon-floppy-saved" onclick="location.href='/anavada/aidetail?page=<%= currentPage %>&no=<%= i.getIqNo() %>'"></i>
                            <% } %>
                            </td>
                            
                            <% if(i.getIqAnswer().equals("Y")) { %>
                            <td class="declare_btn"><span><i class="glyphicon glyphicon-bell"></i>처리완료</span></td>
                            <% }else { %><td></td><% } %>
                        </tr>
                     <% } }%>
                    </tbody>
                </table>
				<% } %>
				
                <!-- //게시판 -->
                
                
                <!-- 페이징 -->
                <% if(totalPage > 1) { %>
                <dl class="list-paging">
                    <dd>
                    <% if(selected != null && keyword != null) { %>
                    	<% if(currentPage <= 1) { %>
                        <a href="#none"><i class="glyphicon glyphicon-menu-left"></i></a>
                        <% }else {%><a href="/anavada/aisearch?page=1&selected=<%= selected %>&keyword=<%= keyword %>"><i class="glyphicon glyphicon-menu-left"></i></a><% } %>
                        
                        <% for(int p=startPage; p<=endPage; p++) {%>
                        	<% if(p == currentPage) {%>
                        	<a href="#none" class="active"><%= p %></a>
                        	<% }else { %>
                        	<a href="/anavada/aisearch?page=<%= p %>&selected=<%= selected %>&keyword=<%= keyword %>"><%= p %></a>
                        	<% } %>
                        <% } %>
                        
                        <% if(currentPage < totalPage) { %>
                        <a href="/anavada/aisearch?page=<%= totalPage %>&selected=<%= selected %>&keyword=<%= keyword %>"><i class="glyphicon glyphicon-menu-right"></i></a>
                        <% }else {%><a href="#none"><i class="glyphicon glyphicon-menu-right"></i></a><% } %>
                        
                        
                     <% }else { %>   
                     	<% if(currentPage <= 1) { %>
                        <a href="#none"><i class="glyphicon glyphicon-menu-left"></i></a>
                        <% }else {%><a href="/anavada/ailist.ss?page=1"><i class="glyphicon glyphicon-menu-left"></i></a><% } %>
                        
                        <% for(int p=startPage; p<=endPage; p++) {%>
                        	<% if(p == currentPage) {%>
                        	<a href="#none" class="active"><%= p %></a>
                        	<% }else { %>
                        	<a href="/anavada/ailist.ss?page=<%= p %>"><%= p %></a>
                        	<% } %>
                        <% } %>
                        
                        <% if(currentPage < totalPage) { %>
                        <a href="/anavada/ailist.ss?page=<%= totalPage %>"><i class="glyphicon glyphicon-menu-right"></i></a>
                        <% }else {%><a href="#none"><i class="glyphicon glyphicon-menu-right"></i></a><% } %>
                     <% } %>
                    </dd>
                </dl>
                <% }else { %><br><br><br><br><% } %>
                <!-- //페이징 -->

            </div>
        <%@ include file="../../include/admin_footer.jsp" %> 
        </div>
    </div>
</body>
</html>