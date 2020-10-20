<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ArrayList, member.model.vo.Member,declare.model.vo.DBo"%>
<%
	ArrayList<DBo> list = (ArrayList<DBo>) request.getAttribute("list");
	int totalList = ((Integer) request.getAttribute("totalList")).intValue();
	int startPage = ((Integer) request.getAttribute("startPage")).intValue();
	int endPage = ((Integer) request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer) request.getAttribute("maxPage")).intValue();
	int currentPage = ((Integer) request.getAttribute("currentPage")).intValue();
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
                            <li class="glyphicon glyphicon-menu-right"><a href="/anavada/mypage.cp?memberId=<%= loginMember.getMemberId() %>">MYPAGE</a></li>
                            <li class="glyphicon glyphicon-menu-right"><a href="/anavada/dbomylist.ss?member=<%= loginMember.getMemberId()%>">신고하기조회</a></li>
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
                    <li><a href="/anavada/miq?member=<%= loginMember.getMemberId() %>">문의하기조회</a></li>
                    <li class="active"><a href="/anavada/dbomylist.ss?member=<%= loginMember.getMemberId()%>">신고하기조회</a></li>
                </ul>
            </div>
            <!--서브 카테고리 끝-->

            
            <!-- 리스트 -->
            <div class="mypage_area list-area">
                <h2 class="mypage_title">나의 신고내역</h2>
               <!--종류 리스트-->
                <div class="sort-area">  
                    <h4>전체 <%= totalList %> 개</h4>
                    <!-- <a href="../declare/declare_write.jsp" class="write_btn">글쓰기</a> -->
                    <div>
                       <!--  <form action="" method="" id="">
                            목록 분류 : <select name="" class="ListSelect">
                                    <option value="분류 선택" selected="selected">분류 선택</option>
                                    <option value="제목">제목</option>
                                    <option value="내용">내용</option>
                                    <option value="작성자">작성자</option>
                            </select>
                            
                            <input type="text" placeholder="검색어를 입력해주세요.">
                            <button class="top-search"><i class="xi-search"></i></button>
                        </form> -->
                    </div>
                </div>
         <% if(totalList == 0 ) { %>
          <div class="list-no">
				<p>
				  <img src="/anavada/resources/images/btnIcn/icn_big_listNo.png" alt="" title="" />
				</p>
				<h1>작성한 글이 없습니다.</h1>
		       </div>
		  <% }else { %>
             <form action="">
                <table>
                    <tbody>
                   <%--  <% if(d.getDboMid() == loginMember.getMemberId()){ %> --%>
                      <%for(DBo d : list) { System.out.println(d);%>
                        <tr id="click" onclick="location.href='/anavada/dbodetail?dboNo=<%= d.getDboNo()%>';" >
                            <td class="number"><%= d.getDboNo() %></td>
                            <td class="title">
                                <h2><span class="declare">신고</span><%= d.getDboTitle() %></h2>
                                <ul>
										
										<li>작성자 : <%= d.getDboMid() %></li>
										<li>작성일 : <%= d.getDboDate() %></li>
										<li>조회수 : 30 <!-- 나중에 --></li>
										<li>유형 : <%= d.getDboType() %></li>
								</ul>
							</td>
                            <td class="declare_btn">
                            		<% if(d.getDboChe().equals("Y")){ %>
                    				<span><i class="glyphicon glyphicon-bell"></i>처리완료</span>
                    				<% }else{// 처리완료된건%>
            		    			<span><i class="glyphicon glyphicon-bell"></i>처리중</span>
            		    			<% }  %>
            		    	</td> 
                       	 </tr>
                       	 <% } %>
							<!-- <div class="list-no">
								<p>
									<img src="/anavada/resources/images/btnIcn/icn_big_listNo.png" alt="" title="" />
								</p>
								<h1>작성한 글이 없습니다.</h1>
							</div> -->
                  </table>
             <% } %>


                <div class="write-btn">
                    <a href="/anavada/views/declare/declare_write.jsp">글쓰기</a>
                </div>
                </form>

            </div>
            <!-- 리스트 끝 -->

            <!-- 페이지넘버 -->
            <dl class="list-paging pb80">
                <dd>
                    <% if(currentPage <= 1){ %>
           		     <a><i class="glyphicon glyphicon-menu-left"></i></a>
                <% }else{ %>
                	<a href="/anavada/dbomylist.ss?page=1"><i class="glyphicon glyphicon-menu-left"></i></a>
                <% } %>
                <!-- 이전으로 이동처리 -->
                <%-- <% if((currentPage) - 10 < startPage && (currentPage - 10) > 1){ %>
                
                <% }else{ %>
                <% } %> --%>
                <!-- 현재 페이지가 속한 페이지그룹의 숫자 출력 처리 -->
                 <% for(int p = startPage; p <= endPage; p++){ 
                 		if(p == currentPage){%>
                 		<a class="active"><%= p %></a>
                		<% }else{ %>
                		<a href="/anavada/dbomylist.ss?page=<%= p %>"><%= p %></a>
                <% }} %>
                <!-- 맨끝 페이지로 이동처리 -->
                <% if(currentPage >= maxPage){ %>
                     <a><i class="glyphicon glyphicon-menu-right"></i></a>
                <% }else{ %>
                	<a href="/anavada/dbomylist.ss?page=<%= maxPage %>"><i class="glyphicon glyphicon-menu-right"></i></a>
                <% } %>
                </dd>
            </dl>
            <!-- 페이지넘버 끝 -->

        </div>
        <!-- 컨텐츠 끝 -->

    <%@ include file="../include/footer.jsp" %>
    </div>
</body>
</html>