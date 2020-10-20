<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cboard.model.vo.Cboard, java.util.ArrayList, java.sql.Date"%>
<%
	ArrayList<Cboard> list = (ArrayList<Cboard>) request.getAttribute("list");
	String local = String.valueOf(request.getAttribute("local"));
	String search = String.valueOf(request.getAttribute("search"));
	String keyword = String.valueOf(request.getAttribute("keyword"));
	int listCount = ((Integer) request.getAttribute("listCount")).intValue();
	int startPage = ((Integer) request.getAttribute("startPage")).intValue();
	int endPage = ((Integer) request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer) request.getAttribute("maxPage")).intValue();
	int currentPage = ((Integer) request.getAttribute("currentPage")).intValue();
	String[] localArr = { "강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구",
			"마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구" };
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
                            <li><a href="views/main/main.jsp">홈</a></li>
                            <li class="glyphicon glyphicon-menu-right"><a href="/anavada/mypage.cp?memberId=<%= loginMember.getMemberId() %>">MYPAGE</a></li>
                            <li class="glyphicon glyphicon-menu-right"><a href="/anavada/mycmnt?memberID=<%=loginMember.getMemberId()%>">커뮤니티조회</a></li>
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
                    <li class="active"><a href="/anavada/mycmnt?memberID=<%=loginMember.getMemberId()%>">커뮤니티조회</a></li>
                    <li><a href="/anavada/miq?member=<%= loginMember.getMemberId() %>">문의하기조회</a></li>
                    <li><a href="/anavada/dbomylist.ss?member=<%= loginMember.getMemberId()%>">신고하기조회</a></li>
                </ul>
            </div>
            <!--서브 카테고리 끝-->


            <!--마이페이지컨텐츠-->
            <div class="mypage_area list-area">
                <h2 class="mypage_title">나의 커뮤니티내역</h2>
               <!--종류 리스트-->
                <div class="sort-area">
					<h4>
						전체 게시글
						<%=listCount%>개
					</h4>
					<%
						if (loginMember != null) {
					%>
					<a href="views/cboard/community_write.jsp" class="write_btn">글쓰기</a>
					<%
						}
					%>

					<div>
						<form action="/anavada/mycmnt" method="get" style="display: inline-block;">
							<input type="hidden" name="memberID" value="<%=loginMember.getMemberId()%>">
							지역 분류 :
							<select name="local" class="LocationSelect" onchange="this.form.submit()">
								<option value="0" selected="selected">지역선택</option>
								<option value="1" <%if (local.equals("1")) {%> selected="selected" <%}%>>강남구</option>
								<option value="2" <%if (local.equals("2")) {%> selected="selected" <%}%>>강동구</option>
								<option value="3" <%if (local.equals("3")) {%> selected="selected" <%}%>>강북구</option>
								<option value="4" <%if (local.equals("4")) {%> selected="selected" <%}%>>강서구</option>
								<option value="5" <%if (local.equals("5")) {%> selected="selected" <%}%>>관악구</option>
								<option value="6" <%if (local.equals("6")) {%> selected="selected" <%}%>>광진구</option>
								<option value="7" <%if (local.equals("7")) {%> selected="selected" <%}%>>구로구</option>
								<option value="8" <%if (local.equals("8")) {%> selected="selected" <%}%>>금천구</option>
								<option value="9" <%if (local.equals("9")) {%> selected="selected" <%}%>>노원구</option>
								<option value="10" <%if (local.equals("10")) {%> selected="selected" <%}%>>도봉구</option>
								<option value="11" <%if (local.equals("11")) {%> selected="selected" <%}%>>동대문구</option>
								<option value="12" <%if (local.equals("12")) {%> selected="selected" <%}%>>동작구</option>
								<option value="13" <%if (local.equals("13")) {%> selected="selected" <%}%>>마포구</option>
								<option value="14" <%if (local.equals("14")) {%> selected="selected" <%}%>>서대문구</option>
								<option value="15" <%if (local.equals("15")) {%> selected="selected" <%}%>>서초구</option>
								<option value="16" <%if (local.equals("16")) {%> selected="selected" <%}%>>성동구</option>
								<option value="17" <%if (local.equals("17")) {%> selected="selected" <%}%>>성북구</option>
								<option value="18" <%if (local.equals("18")) {%> selected="selected" <%}%>>송파구</option>
								<option value="19" <%if (local.equals("19")) {%> selected="selected" <%}%>>양천구</option>
								<option value="20" <%if (local.equals("20")) {%> selected="selected" <%}%>>영등포구</option>
								<option value="21" <%if (local.equals("21")) {%> selected="selected" <%}%>>용산구</option>
								<option value="22" <%if (local.equals("22")) {%> selected="selected" <%}%>>은평구</option>
								<option value="23" <%if (local.equals("23")) {%> selected="selected" <%}%>>종로구</option>
								<option value="24" <%if (local.equals("24")) {%> selected="selected" <%}%>>중구</option>
								<option value="25" <%if (local.equals("25")) {%> selected="selected" <%}%>>중랑구</option>
							</select>
						</form>

						<form action="/anavada/mycmnt" method="post" style="display: inline-block;">
							<input type="hidden" name="local" value="<%=local%>">
							<input type="hidden" name="memberID" value="<%=loginMember.getMemberId()%>">
							<select name="search" class="ListSelect">
								<option value="title">제목</option>
								<option value="content">내용</option>
							</select>
							<input type="text" name="keyword" placeholder="검색어를 입력해주세요.">
							<button class="top-search" type="submit">
								<i class="xi-search"></i>
							</button>
						</form>
					</div>
				</div>

                <table class="cmnt_list">
					<tbody>
						<%
							for (Cboard c : list) {
						%>
						<tr onclick="location.href='/anavada/cdetail?cnum=<%=c.getCboardNo()%>&page=<%=currentPage%>&local=<%=local%>&search=<%=search%>&keyword=<%=keyword%>'">
							<td class="number"><%=c.getCboardNo()%></td>
							<td class="title">
								<h2>
									<span><%=localArr[Integer.parseInt(c.getLocalNo()) - 1]%></span><%=c.getCboardTitle()%>
								</h2>
								<ul>
									<li>작성자 : <%=c.getMemberId()%></li>
									<li>작성일 : <%=c.getDate()%></li>
									<li>조회수 : <%=c.getCboardViewCount()%></li>
									<li><i class="good_i glyphicon glyphicon-heart-empty">좋아요<span><%=c.getLikeCount()%></span></i></li>
								</ul>
							</td>
							<td class="fileDown">
								<%
									if ((c.getCfilesOriginalFilepath1() != null && !c.getCfilesOriginalFilepath1().equals("null"))
												|| (c.getCfilesOriginalFilepath2() != null && !c.getCfilesOriginalFilepath2().equals("null"))
												|| (c.getCfilesOriginalFilepath3() != null && !c.getCfilesOriginalFilepath3().equals("null"))
												|| (c.getCfilesOriginalFilepath4() != null && !c.getCfilesOriginalFilepath4().equals("null"))) {
								%>
								<i class="glyphicon glyphicon-picture"></i>
								<%
									}
								%>
							</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
				
				<% if(listCount == 0) { %>
				<div class="list-no">

					<p>
						<img src="/anavada/resources/images/btnIcn/icn_big_listNo.png" alt="" title="" />
					</p>
					<h1>목록이 없습니다.</h1>

				</div>
				<% } %>

					
					<%
						if (loginMember != null) {
					%>
				<div class="write-btn">
					<a href="views/cboard/community_write.jsp">글쓰기</a>
				</div>
					<%
						}
					%>

			</div>


			<!-- 리스트 끝 -->


			<!-- 페이지넘버 -->
			<dl class="list-paging pb80">
				<dd>
					<%
						if (currentPage <= 1) {
					%>
					<a><i class="glyphicon glyphicon-backward"></i></a>
					<%
						} else {
					%>
					<a href="/anavada/mycmnt?local=<%=local%>&search=<%=search%>&keyword=<%=keyword%>&memberID=<%=loginMember.getMemberId()%>"><i class="glyphicon glyphicon-backward"></i></a>
					<%
						}
					%>
					<%
						if (startPage <= 1) {
					%>
					<a><i class="glyphicon glyphicon-menu-left"></i></a>
					<%
						} else {
					%>
					<a href="/anavada/mycmnt?page=<%=startPage - 10%>&local=<%=local%>&search=<%=search%>&keyword=<%=keyword%>&memberID=<%=loginMember.getMemberId()%>"><i class="glyphicon glyphicon-menu-left"></i>
					</a>
					<%
						}
					%>
					<%
						for (int p = startPage; p <= endPage; p++) {
					%>
					<%
						if (currentPage == p) {
					%>
					<a href="/anavada/mycmnt?page=<%=p%>&local=<%=local%>&search=<%=search%>&keyword=<%=keyword%>&memberID=<%=loginMember.getMemberId()%>" class="active"><%=p%></a>
					<%
						} else {
					%>
					<a href="/anavada/mycmnt?page=<%=p%>&local=<%=local%>&search=<%=search%>&keyword=<%=keyword%>&memberID=<%=loginMember.getMemberId()%>"><%=p%></a>
					<%
						}
					%>
					<%
						}
					%>
					<%
						if (endPage >= maxPage) {
					%>
					<a><i class="glyphicon glyphicon-menu-right"></i></a>
					<%
						} else {
					%>
					<a href="/anavada/mycmnt?page=<%=endPage + 1%>&local=<%=local%>&search=<%=search%>&keyword=<%=keyword%>&memberID=<%=loginMember.getMemberId()%>"><i class="glyphicon glyphicon-menu-right"></i></a>
					<%
						}
					%>
					<%
						if (currentPage >= maxPage) {
					%>
					<a><i class="glyphicon glyphicon-forward"></i></a>
					<%
						} else {
					%>
					<a href="/anavada/mycmnt?page=<%=maxPage%>&local=<%=local%>&search=<%=search%>&keyword=<%=keyword%>&memberID=<%=loginMember.getMemberId()%>"><i class="glyphicon glyphicon-forward"></i></a>
					<%
						}
					%>
				</dd>
			</dl>
			<!-- 페이지넘버 끝 -->
        </div>
    <%@ include file="../include/footer.jsp" %>
    </div>
</body>
</html>