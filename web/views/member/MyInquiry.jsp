<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ArrayList, inquiry.model.vo.Inquiry"%>
<%
	ArrayList<Inquiry> list = (ArrayList<Inquiry>) request.getAttribute("list");
	int currentPage = (Integer) request.getAttribute("page");
	int startPage = (Integer) request.getAttribute("startPage");
	int endPage = (Integer) request.getAttribute("endPage");
	int totalList = (Integer) request.getAttribute("totalList");
	int totalPage = (Integer) request.getAttribute("totalPage");
	String selected = null;
	String keyword = null;
	if (request.getAttribute("selected") != null && request.getAttribute("keyword") != null) {
		selected = (String) request.getAttribute("selected");
		keyword = (String) request.getAttribute("keyword");
	}
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
                    <h3>'Anavada'의 내 정보를 확인할 수 있습니다.</h3>
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

            <!-- 리스트 -->
            <div class="mypage_area list-area">
                <h2 class="mypage_title">나의 문의하기내역</h2>
               <!--종류 리스트-->
                <div class="sort-area">  
                    <h4>전체 <%= totalList %>개</h4>
                    <a href="/anavada/views/inquiry/myinquiry_write.jsp" class="write_btn">글쓰기</a>
                    <div>
                        <form action="/anavada/isearch" method="post" id="">
                        <input type="hidden" value="<%= loginMember.getMemberId() %>" name="member">
                            목록 분류 : <select name="selected" class="ListSelect">
                                    <option value="none" selected disabled>분류 선택</option>
                                    <option value="title">제목</option>
                                    <option value="content">내용</option>
                            </select>
                            
                            <input type="text" placeholder="검색어를 입력해주세요." name="keyword">
                            <button class="top-search"><i class="xi-search"></i></button>
                        </form>
                    </div>
                </div>
              
                <% if(totalList > 0) { %>
                	<table>
                    	<tbody>
                    	<% for(Inquiry i : list) { %>
                    	
                    		<% if(selected == null && keyword == null) { %>
                        	<tr onclick="javascript:location.href='/anavada/imdetail?no=<%= i.getIqNo() %>&page=<%= currentPage %>';">
                        	<% }else { %>
                        	<tr onclick="javascript:location.href='/anavada/imdetail?no=<%= i.getIqNo() %>&page=<%= currentPage %>&selected=<%= selected %>&keyword=<%= keyword %>';">
                        	<% } %>
                        	
                            	<td class="number"><%= i.getIqNo() %></td>
                            	<td class="title">
                                	<h2><span class="inquiry">문의</span><%= i.getIqTitle() %></h2>
                                	<ul>
                                    	<li>작성자 : <%= i.getIqId() %></li>
                                    	<li>작성일 : <%= i.getIqDate() %></li>
                                	</ul>
                            	</td>
                            	<td class="fileDown">
                            	<% if(i.getIqOriginal() != null || i.getIqOriginal2() != null || i.getIqOriginal3() != null) { %>
                            	<i class="glyphicon glyphicon-floppy-saved"></i><% } %></td>
                            	
                            <% if(i.getIqAnswer().equals("Y")) { %>
                            	<td class="declare_btn"><span><i class="glyphicon glyphicon-bell"></i>처리완료</span></td>
                            <% }else { %><td></td><% } %>
                        	</tr>
                        <% } %>
                    	</tbody>
                	</table>
				<% }else { %>
                <div class="list-no">
                    <p><img src="/anavada/resources/images/btnIcn/icn_big_listNo.png" alt="" title="" /></p>
                    <h1>목록이 없습니다.</h1>
                </div>
				<% } %>

                <div class="write-btn">
                    <a href="/anavada/views/inquiry/myinquiry_write.jsp">글쓰기</a>
                </div>

            </div>
            <!-- 리스트 끝 -->

            <!-- 페이지넘버 -->
            <% if(totalPage > 1) { %>
            <dl class="list-paging pb80">
                <dd>
                <% if(selected == null && keyword == null) { %>
                	<% if(currentPage == 1) { %>
                    	<a href="#none"><i class="glyphicon glyphicon-menu-left"></i></a>
                    <% }else { %>
                    	<a href="/anavada/miq?member=<%= loginMember.getMemberId() %>&page=1"><i class="glyphicon glyphicon-menu-left"></i></a>
                    <% } %>
                    
                    <% for(int p=startPage; p<=endPage; p++) { %>
                    	<% if(p == currentPage) { %>
                    	<a href="#none" class="active"><%= p %></a>
                    	<% }else { %>
                    	<a href="/anavada/miq?member=<%= loginMember.getMemberId() %>&page=<%= p %>"><%= p %></a>
                    <% } } %>
                    
                    <% if(currentPage == totalPage) { %>
                    	<a href="#none"><i class="glyphicon glyphicon-menu-right"></i></a>
                    <% }else { %>
                    	<a href="/anavada/miq?member=<%= loginMember.getMemberId() %>&page=<%= totalPage %>"><i class="glyphicon glyphicon-menu-right"></i></a>
                    <% } %>
                    
				<% }else { %>
                    <% if(currentPage == 1) { %>
                       <a href="#none"><i class="glyphicon glyphicon-menu-left"></i></a>
                    <% }else { %>
                       <a href="/anavada/isearch?page=1&selected=<%= selected %>&keyword=<%= keyword %>&member=<%= loginMember.getMemberId() %>"><i class="glyphicon glyphicon-menu-left"></i></a>
                    <% } %>
                    
                    <% for(int p=startPage; p<=endPage; p++) { %>
                       <% if(p == currentPage) { %>
                       <a href="#none" class="active"><%= p %></a>
                       <% }else { %>
                       <a href="/anavada/isearch?page=<%= p %>&selected=<%= selected %>&keyword=<%= keyword %>&member=<%= loginMember.getMemberId() %>"><%= p %></a>
                    <% } } %>
                    
                    <% if(currentPage == totalPage) { %>
                       <a href="#none"><i class="glyphicon glyphicon-menu-right"></i></a>
                    <% }else { %>
                       <a href="/anavada/isearch?page=<%= totalPage %>&selected=<%= selected %>&keyword=<%= keyword %>&member=<%= loginMember.getMemberId() %>"><i class="glyphicon glyphicon-menu-right"></i></a>
                    <% } %>
				<% } %>
                </dd>
            </dl>
            <% } else { %><br><br><br><br><% } %>
            <!-- 페이지넘버 끝 -->

        </div>
        <!-- 컨텐츠 끝 -->

    <%@ include file="../include/footer.jsp" %>
    </div>
</body>
</html>