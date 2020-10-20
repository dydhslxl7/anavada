<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="member.model.vo.Member"%>
<%
	int listCount = ((Integer)request.getAttribute("listCount")).intValue();
	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
	int listNum = ((Integer)request.getAttribute("listNum")).intValue();
%>
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
                    <h3>회원가입</h3>
                    <h2>| 리스트</h2>
                </div>
            </div>
            <!-- //상단 타이틀 -->

            <!-- 본문내용 -->
            <div class="list-area">
                <!-- 검색영역 -->
                <div class="sort-area">  
                    <h4>전체 회원 <%= listCount %>명</h4>
                    <div>
                        <form action="/anavada/mlist.ad?secessionOK=Y" method="get" id="">
                        	<input type="hidden" value="N" name="secessionOK">
                           	 목록 분류 : <select name="search" class="ListSelect">
                                    <!--option value="분류 선택" selected="selected">분류 선택</option-->
                                    <option value="userId" elected="selected">아이디</option>
                                    <option value="userName">이름</option>
                                    <option value="userEmail">이메일</option>
                                    <option value="userPhone">전화번호</option>
                            </select>
                            <input type="text" name="keyword" placeholder="검색어를 입력해주세요.">
                            <button type="submit" class="top-search"><i class="xi-search"></i></button>
                        </form>
                    </div>
                </div>
                <!-- 검색영역 끝 -->

                <table class="memberTable">
                    <colgroup>
                        <col width="5%">
                        <col width="8%">
                        <col width="10%">
                        <col width="10%">
                        <col width="*">
                        <col width="18%">
                        <col width="10%">
                        <col width="10%">
                        <col width="8%">
                    </colgroup>
                    <tbody>
                        <tr>
                            <th>선택</th>
                            <th>번호</th>
                            <th>아이디</th>
                            <th>이름</th>
                            <th>이메일</th>
                            <th>전화번호</th>
                            <th>가입일</th>
                            <th>접속일</th>
                            <th>신고여부</th>
                        </tr>
						<% for(Member m : list){ %>
						<% if(m.getMemberId().equals("admin")){%>
                        <tr style="background-color: #fcf8e3;">
                            <td class="checkBox"><input type="checkbox" name="leaveChk" id="leaveChk" value="<%= m.getMemberId()%>" disabled></td>
                            <td class="number"><%= listNum %></td>
                            <td class="id"><%= m.getMemberId() %></td>
                            <td class="name"><%= m.getMemberName() %></td>
                            <td class="email"><%= m.getMemberEmail() %></td>
                            <td class="phone"><%= m.getMemberPhone() %></td>
                            <td class="joinDate"><%= m.getJoinDate() %></td>
                            <td class="lastAccessDate"><%= m.getLastAccessDate() %></td>
                            <td class="declareOK"><%= m.getDeclareOK() %></td>
                        </tr>
                        <% } else if(listCount > 0) { %>
                        <tr>
                            <td class="checkBox"><input type="checkbox" name="leaveChk" id="leaveChk" value="<%= m.getMemberId()%>"></td>
                            <td class="number"><%= listNum %></td>
                            <td class="id"><%= m.getMemberId() %></td>
                            <td class="name"><%= m.getMemberName() %></td>
                            <td class="email"><%= m.getMemberEmail() %></td>
                            <td class="phone"><%= m.getMemberPhone() %></td>
                            <td class="joinDate"><%= m.getJoinDate() %></td>
                            <td class="lastAccessDate"><%= m.getLastAccessDate() %></td>
                            <td class="declareOK"><%= m.getDeclareOK() %></td>
                        </tr>
                        <% } else { %>
						<tr class="list-no">
							<td colspan="9">
								<p><img src="/anavada/resources/images/btnIcn/icn_big_listNo.png" alt="" title="" /></p>
								<h1>목록이 없습니다.</h1>
							</td>
						</tr>
                        <% } %>
                        <% listNum--; %>
                        <% } %>

                    </tbody>
                </table>
                <p class="warning_text"> *탈퇴된 회원은 되돌릴 수 없습니다. 신중하게 선택하세요.</p>
                <!-- //게시판 -->

                <!-- 버튼 -->
                <div class="btn_wrap">
                    <a href="#none" id="leaveBtn" class="btn-left btn_gray">탈퇴선택</a>
                </div>
                <!-- //버튼 -->

                <!-- 페이징 -->
                <dl class="list-paging">
                    <dd>
                   	<% if(currentPage <= 1){ %>
                    	<a href="#none"><i class="glyphicon glyphicon-backward"></i></a>
                   	<% } else { %>
                   		<a href="/anavada/mlist.ad?secessionOK=N&page=<%= startPage %>"><i class="glyphicon glyphicon-backward"></i></a>
                    <% } %>
                    
                   	<% if(1 < currentPage){ %>
                   		<a href="/anavada/mlist.ad?secessionOK=N&page=<%= currentPage - 1 %>"><i class="glyphicon glyphicon-menu-left"></i></a>
                    	<% } else { %>
                   		<a href="#none"><i class="glyphicon glyphicon-menu-left"></i></a>
                   	<% } %>
                    
                    <% for(int p = startPage; p <= endPage; p++){ %>
                    	<% if(currentPage == p){ %>
                        <a href="#none" class="active"><%= p %></a>
                   	<% } else { %>
                        <a href="/anavada/mlist.ad?secessionOK=N&page=<%= p %>"><%= p %></a>
                    	<% } %>
                    <% } %>
                    
					<% if(currentPage < maxPage){ %>
                        <a href="/anavada/mlist.ad?secessionOK=N&page=<%= currentPage + 1 %>"><i class="glyphicon glyphicon-menu-right"></i></a>
					<% } else { %>
                        <a href="#none"><i class="glyphicon glyphicon-menu-right"></i></a>
					<% } %>
					<% if(currentPage >= maxPage){ %>
                        <a href="#none"><i class="glyphicon glyphicon-forward"></i></a>
					<% } else { %>
                        <a href="/anavada/mlist.ad?secessionOK=N&page=<%= maxPage %>"><i class="glyphicon glyphicon-forward"></i></a>
					<% } %>					
                    </dd>
                </dl>
                <!-- //페이징 -->

            </div>
        <%@ include file="../include/admin_footer.jsp" %> 
        </div>
    </div>
</body>
</html>