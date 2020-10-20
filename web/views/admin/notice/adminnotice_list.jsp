<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, notice.model.vo.Notice"%>
<%
	ArrayList<Notice> listNotice = (ArrayList<Notice>)request.getAttribute("list");
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
    <%@ include file="../include/admin_head.jsp" %>
<script type="text/javascript">
function checkAll(){
	if($("input[name=checkAll]").is(":checked")){
		$("input[name=checkDel]").prop("checked", true);
	}else{
		$("input[name=checkDel]").prop("checked", false);
	}
}

function deleteAction(){
	var checkRow = "";
	$("input[name='checkDel']:checked").each(function(){
		checkRow = checkRow + $(this).val()+",";
	});
	checkRow = checkRow.substring(0, checkRow.lastIndexOf(","));
	
	if(checkRow == ""){
		alert("삭제할 대상을 선택하세요.");
		return false;
	}
	
	console.log("### checkRow => {"+checkRow+"}");
	
	if(confirm("삭제 하시겠습니까?"))
		location.href = "/anavada/andelete?checkRow="+checkRow;
}

</script>
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
    <div id="wrap">
        <%@ include file="../include/admin_header.jsp" %> 

        <div id="admin_container">

            <!-- 상단 타이틀 -->
            <div class="admin_title">
                <div class="admin_path">
                    <h3>공지사항</h3>
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
						<form action="/anavada/ansearch" method="post" id="">
							목록 분류 : <select name="selected" class="ListSelect">
								<option value="none" selected disabled>분류 선택</option>
								<option value="title">제목</option>
								<option value="content">내용</option>
							</select> <input type="text" name="keyword" placeholder="검색어를 입력해주세요.">
							<button class="top-search">
								<i class="xi-search"></i>
							</button>
						</form>
					</div>
				</div>
				<!-- 검색영역 끝 -->
				
				<% if(totalList == 0) { %>
				<div class="list-no" align="center">
					<p>
						<img src="/anavada/resources/images/btnIcn/icn_big_listNo.png"
							alt="" title="" />
					</p>
					<h1>목록이 없습니다.</h1>
				</div>

				<% }else { %>
				<table class="memberTable">
                    <tbody>
                        <tr>
                            <th><input type="checkBox" name="checkAll" onclick="checkAll();" class="checkBox"></th>
                            <th colspan="3">제목</th>
                        </tr>
                        <% if(selected != null && keyword != null) {%>
                        <% for(Notice n : listNotice) { %>
                        <tr>
                            <td class="checkBox"><input type="checkbox" name="checkDel" value="<%= n.getNoNo() %>"></td>
                            <td class="number" onclick="location.href='/anavada/andetail?page=<%= currentPage %>&no=<%= n.getNoNo() %>&selected=<%= selected %>&keyword=<%= keyword %>'"><%= n.getNoNo() %></td>
                            <td class="title" onclick="location.href='/anavada/andetail?page=<%= currentPage %>&no=<%= n.getNoNo() %>&selected=<%= selected %>&keyword=<%= keyword %>'">
                                <h2><span>공지</span><%= n.getNoTitle() %></h2>
                                <ul>
                                    <li>작성자 : <%= n.getNoId() %></li>
                                    <li>작성일 : <%= n.getNoDate() %></li>
                                    <li>조회수 : <%= n.getNoCount() %></li>
                                </ul>
                            </td>
                            <td class="fileDown" onclick="location.href='/anavada/andetail?page=<%= currentPage %>&no=<%= n.getNoNo() %>&selected=<%= selected %>&keyword=<%= keyword %>'">
                            <% if(n.getNoOriginal() != null) { %>
                            <i class="glyphicon glyphicon-floppy-saved" onclick="location.href='/anavada/andetail?page=<%= currentPage %>&no=<%= n.getNoNo() %>&selected=<%= selected %>&keyword=<%= keyword %>'"></i>
                            <% } %>
                            </td>
                        </tr>
                        <% } }else { %>
                        <% for(Notice n : listNotice) { %>
                        <tr>
                            <td class="checkBox"><input type="checkbox" name="checkDel" value="<%= n.getNoNo() %>"></td>
                            <td class="number" onclick="location.href='/anavada/andetail?page=<%= currentPage %>&no=<%= n.getNoNo() %>'"><%= n.getNoNo() %></td>
                            <td class="title" onclick="location.href='/anavada/andetail?page=<%= currentPage %>&no=<%= n.getNoNo() %>'">
                                <h2><span>공지</span><%= n.getNoTitle() %></h2>
                                <ul>
                                    <li>작성자 : <%= n.getNoId() %></li>
                                    <li>작성일 : <%= n.getNoDate() %></li>
                                    <li>조회수 : <%= n.getNoCount() %></li>
                                </ul>
                            </td>
                            <td class="fileDown" onclick="location.href='/anavada/andetail?page=<%= currentPage %>&no=<%= n.getNoNo() %>'">
                            <% if(n.getNoOriginal() != null) { %>
                            <i class="glyphicon glyphicon-floppy-saved" onclick="location.href='/anavada/andetail?page=<%= currentPage %>&no=<%= n.getNoNo() %>'"></i>
                            <% } %>
                            </td>
                        </tr>
                     <% } }%>
                    </tbody>
                </table>
				<% } %>
				
                <p class="warning_text">*삭제한 게시글은 복구가 불가능하니 신중하게 선택하시기 바랍니다.</p>
                <!-- //게시판 -->
                <!-- 버튼 -->
                <div class="btn_wrap">
                    <a onclick="deleteAction();" class="btn-left btn_gray">선택삭제</a>
                    <a href="/anavada/views/admin/notice/adminnotice_write.jsp" class="btn-right btn_white">등록</a>
                </div>
                <!-- //버튼 -->

                <!-- 페이징 -->
                <% if(totalPage > 1) { %>
                <dl class="list-paging">
                    <dd>
                    <% if(selected != null && keyword != null) { %>
                    	<% if(currentPage <= 1) { %>
                        <a href="#none"><i class="glyphicon glyphicon-menu-left"></i></a>
                        <% }else {%><a href="/anavada/ansearch?page=1&selected=<%= selected %>&keyword=<%= keyword %>"><i class="glyphicon glyphicon-menu-left"></i></a><% } %>
                        
                        <% for(int p=startPage; p<=endPage; p++) {%>
                        	<% if(p == currentPage) {%>
                        	<a href="#none" class="active"><%= p %></a>
                        	<% }else { %>
                        	<a href="/anavada/ansearch?page=<%= p %>&selected=<%= selected %>&keyword=<%= keyword %>"><%= p %></a>
                        	<% } %>
                        <% } %>
                        
                        <% if(currentPage < totalPage) { %>
                        <a href="/anavada/ansearch?page=<%= totalPage %>&selected=<%= selected %>&keyword=<%= keyword %>"><i class="glyphicon glyphicon-menu-right"></i></a>
                        <% }else {%><a href="#none"><i class="glyphicon glyphicon-menu-right"></i></a><% } %>
                        
                     <% }else { %>   
                     	<% if(currentPage <= 1) { %>
                        <a href="#none"><i class="glyphicon glyphicon-menu-left"></i></a>
                        <% }else {%><a href="/anavada/anlist.ss?page=1"><i class="glyphicon glyphicon-menu-left"></i></a><% } %>
                        
                        <% for(int p=startPage; p<=endPage; p++) {%>
                        	<% if(p == currentPage) {%>
                        	<a href="#none" class="active"><%= p %></a>
                        	<% }else { %>
                        	<a href="/anavada/anlist.ss?page=<%= p %>"><%= p %></a>
                        	<% } %>
                        <% } %>
                        
                        <% if(currentPage < totalPage) { %>
                        <a href="/anavada/anlist.ss?page=<%= totalPage %>"><i class="glyphicon glyphicon-menu-right"></i></a>
                        <% }else {%><a href="#none"><i class="glyphicon glyphicon-menu-right"></i></a><% } %>
                     <% } %>
                    </dd>
                </dl>
                <% }else { %><br><br><br><br><% } %>
                <!-- //페이징 -->

            </div>
        <%@ include file="../include/admin_footer.jsp" %> 
        </div>
    </div>
</body>
</html>