<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, faq.model.vo.Faq"%>
<%
	ArrayList<Faq> listFaq = (ArrayList<Faq>)request.getAttribute("list");
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
<script type="text/javascript">
    function checkAll(){
        if($("input[name=checkAll]").is(":checked")){
            $("input[name=checkDel]").prop("checked", true);
        }else{
            $("input[name=checkDel]").prop("checked", false);
        }
    }
    
	function deleteAction() {
		var checkRow = "";
		$("input[name='checkDel']:checked").each(function() {
			checkRow = checkRow + $(this).val() + ",";
		});
		checkRow = checkRow.substring(0, checkRow.lastIndexOf(","));

		if (checkRow == "") {
			alert("삭제할 대상을 선택하세요.");
			return false;
		}
		console.log("### checkRow => {" + checkRow + "}");
		if (confirm("삭제 하시겠습니까?"))
			location.href = "/anavada/afdelete?checkRow=" + checkRow;
	}
</script>
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
    <div id="wrap">
        <%@ include file="../../include/admin_header.jsp" %> 

        <div id="admin_container">

 			

            <!-- 상단 타이틀 -->
            <div class="admin_title">
                <div class="admin_path">
                    <h3>FAQ</h3>
                    <h2>| 리스트</h2>
                </div>
            </div>
            <!-- //상단 타이틀 -->
			
            <!-- 본문내용 class="active" -->
            <div class="list-area">
                <div class="faqTap">
                	<% if(keyword == null) { %>
                    <a href="#none" class="active" data-tab="tab01" onclick="javascript:location.href='/anavada/aflist.ss'">전체</a>
                    <% }else { %><a href="#none" data-tab="tab01" onclick="javascript:location.href='/anavada/aflist.ss'">전체</a><% } %>
                    
                    <% if(keyword != null && keyword.equals("1")) { %>
                    <a href="#none" class="active" data-tab="tab02" onclick="javascript:location.href='/anavada/afsearch?selected=cate&keyword=1'">회원가입</a>
                    <% }else { %><a href="#none" data-tab="tab02" onclick="javascript:location.href='/anavada/afsearch?selected=cate&keyword=1'">회원가입</a><% } %>
                    <% if(keyword != null && keyword.equals("2")) { %>
                    <a href="#none" class="active" data-tab="tab03" onclick="javascript:location.href='/anavada/afsearch?selected=cate&keyword=2'">중고거래</a>
                    <% }else { %><a href="#none" data-tab="tab03" onclick="javascript:location.href='/anavada/afsearch?selected=cate&keyword=2'">중고거래</a><% } %>
                    <% if(keyword != null && keyword.equals("3")) { %>
                    <a href="#none" class="active" data-tab="tab04" onclick="javascript:location.href='/anavada/afsearch?selected=cate&keyword=3'">커뮤니티</a>
                    <% }else { %><a href="#none" data-tab="tab04" onclick="javascript:location.href='/anavada/afsearch?selected=cate&keyword=3'">커뮤니티</a><% } %>
                    <% if(keyword != null && keyword.equals("4")) { %>
                    <a href="#none" class="active" data-tab="tab05" onclick="javascript:location.href='/anavada/afsearch?selected=cate&keyword=4'">지역축제</a>
                    <% }else { %><a href="#none" data-tab="tab05" onclick="javascript:location.href='/anavada/afsearch?selected=cate&keyword=4'">지역축제</a><% } %>
                    
                </div>
            
                <!-- 검색영역 -->
				<div class="sort-area mt20">
					<h4>
						전체
						<%= totalList %>개
					</h4>
					<div>
						<form action="/anavada/afsearch" method="post" id="">
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
                            <th colspan="2">제목</th>
                        </tr>
                    <% if(selected != null && keyword != null) {%>
                    <% for(Faq f : listFaq) { %>
                        <tr>
                            <td class="checkBox"><input type="checkbox" name="checkDel" value="<%= f.getFaqNo() %>"></td>
                            <td class="number" onclick="location.href='/anavada/afdetail?page=<%= currentPage %>&no=<%= f.getFaqNo() %>&selected=<%= selected %>&keyword=<%= keyword %>'"><%= f.getFaqNo() %></td>
                            <td class="title" onclick="location.href='/anavada/afdetail?page=<%= currentPage %>&no=<%= f.getFaqNo() %>&selected=<%= selected %>&keyword=<%= keyword %>'">
                                <h2><span>
                                <% switch(f.getFaqCategory()) {
                                	case 1 : %>회원<% ; break;
                                	case 2 : %>중고<% ; break;
                                	case 3 : %>커뮤<% ; break;
                                	case 4 : %>축제<% ; break;
                                } %>
                               	</span><%= f.getFaqTitle() %></h2>
                                <ul>
                                    <li>작성자 : <%= f.getFaqId() %></li>
                                    <li>작성일 : <%= f.getFaqDate() %></li>
                                </ul>
                            </td>
                        </tr>
                     <% } }else { %>
                     <% for(Faq f : listFaq) { %>
                        <tr>
                            <td class="checkBox"><input type="checkbox" name="checkDel" value="<%= f.getFaqNo() %>"></td>
                            <td class="number" onclick="location.href='/anavada/afdetail?page=<%= currentPage %>&no=<%= f.getFaqNo() %>'"><%= f.getFaqNo() %></td>
                            <td class="title" onclick="location.href='/anavada/afdetail?page=<%= currentPage %>&no=<%= f.getFaqNo() %>'">
                                <h2><span>
                                <% switch(f.getFaqCategory()) {
                                	case 1 : %>회원<% ; break;
                                	case 2 : %>중고<% ; break;
                                	case 3 : %>커뮤<% ; break;
                                	case 4 : %>축제<% ; break;
                                } %>
                               	</span><%= f.getFaqTitle() %></h2>
                                <ul>
                                    <li>작성자 : <%= f.getFaqId() %></li>
                                    <li>작성일 : <%= f.getFaqDate() %></li>
                                </ul>
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
                    <a href="/anavada/views/admin/notice/faq/adminfaq_write.jsp" class="btn-right btn_white">등록</a>
                </div>
                <!-- //버튼 -->

                <!-- 페이징 -->
                <% if(totalPage > 1) { %>
                <dl class="list-paging">
                    <dd>
                    <% if(selected != null && keyword != null) { %>
                    	<% if(currentPage <= 1) { %>
                        <a href="#none"><i class="glyphicon glyphicon-menu-left"></i></a>
                        <% }else {%><a href="/anavada/afsearch?page=1&selected=<%= selected %>&keyword=<%= keyword %>"><i class="glyphicon glyphicon-menu-left"></i></a><% } %>
                        
                        <% for(int p=startPage; p<=endPage; p++) {%>
                        	<% if(p == currentPage) {%>
                        	<a href="#none" class="active"><%= p %></a>
                        	<% }else { %>
                        	<a href="/anavada/afsearch?page=<%= p %>&selected=<%= selected %>&keyword=<%= keyword %>"><%= p %></a>
                        	<% } %>
                        <% } %>
                        
                        <% if(currentPage < totalPage) { %>
                        <a href="/anavada/afsearch?page=<%= totalPage %>&selected=<%= selected %>&keyword=<%= keyword %>"><i class="glyphicon glyphicon-menu-right"></i></a>
                        <% }else {%><a href="#none"><i class="glyphicon glyphicon-menu-right"></i></a><% } %>
                        
                     <% }else { %>   
                     	<% if(currentPage <= 1) { %>
                        <a href="#none"><i class="glyphicon glyphicon-menu-left"></i></a>
                        <% }else {%><a href="/anavada/aflist.ss?page=1"><i class="glyphicon glyphicon-menu-left"></i></a><% } %>
                        
                        <% for(int p=startPage; p<=endPage; p++) {%>
                        	<% if(p == currentPage) {%>
                        	<a href="#none" class="active"><%= p %></a>
                        	<% }else { %>
                        	<a href="/anavada/aflist.ss?page=<%= p %>"><%= p %></a>
                        	<% } %>
                        <% } %>
                        
                        <% if(currentPage < totalPage) { %>
                        <a href="/anavada/aflist.ss?page=<%= totalPage %>"><i class="glyphicon glyphicon-menu-right"></i></a>
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