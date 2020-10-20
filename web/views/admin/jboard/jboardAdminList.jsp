<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="jboard.model.vo.Jboard"%>
<%
	ArrayList<Jboard> jlist = (ArrayList<Jboard>)request.getAttribute("jlist");
	String local = String.valueOf(request.getAttribute("local"));
	String listSearch = String.valueOf(request.getAttribute("listsearch"));
	String titleSearch = String.valueOf(request.getAttribute("titlesearch"));
	int listCount = ((Integer) request.getAttribute("listCount")).intValue();
	int startPage = ((Integer) request.getAttribute("startPage")).intValue();
	int endPage = ((Integer) request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer) request.getAttribute("maxPage")).intValue();
	int currentPage = ((Integer) request.getAttribute("currentPage")).intValue();
	String[] localArr = { "강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", 
			"서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구" };
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/admin_head.jsp"%>
<script type="text/javascript">
	$(function () { 
		
		$("#checkAll").click(function() {
			if ($("#checkAll").prop("checked")) {
				$("input[name=check]").prop("checked", true);
			} else {
				$("input[name=check]").prop("checked", false);
			}
		});
		
	    $("#delete_btn").click(function() {
		    var checkarr = []
			$("input[name=check]:checked").each(function() {
				checkarr.push($(this).val());
				console.log(checkarr);
			});
		    
		    $.ajax({
		    	url:'/anavada/adjdelete.ad',
		    	type: 'get',
		    	traditional: true,
		    	data: {
		    		checkarr : checkarr.join(',')
		    	},
		    	success : function(data){
		            console.log("컨트롤러에서 받은 MSG : " + data);
		            var dataSplit = data.split('/');
		            if (dataSplit.length === 2) {
		            	alert(dataSplit[1] + '개 중 ' + dataSplit[0] + '개가 삭제되었습니다.');
		            }
		        },
		     
		        //Ajax 실패시 호출
		        error : function(jqXHR, textStatus, errorThrown){
		            console.log("jqXHR : " +jqXHR +"textStatus : " + textStatus + "errorThrown : " + errorThrown);
		        }
		    });
		    location.reload();
		});
	    
	});
	</script>
</head>
<body oncontextmenu="return false" onselectstart="return false"
	ondragstart="return false">
	<div id="wrap">
		<%@ include file="../include/admin_header.jsp"%>

		<div id="admin_container">

			<!-- 상단 타이틀 -->
			<div class="admin_title">
				<div class="admin_path">
					<h3>중고장터</h3>
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
						<%= listCount %>개
					</h4>
					<div>
						<form action="/anavada/adjlistview.ad" method="get"
							style="display: inline-block;">
							지역 분류 : <select name="local" class="LocationSelect"
								onchange="this.form.submit()">
								<option value="0" selected="selected">지역선택</option>
								<option value="1" <% if (local.equals("1"))  { %>selected="selected" <% } %>>강남구</option>
								<option value="2" <% if (local.equals("2"))  { %>selected="selected" <% } %>>강동구</option>
								<option value="3" <% if (local.equals("3"))  { %>selected="selected" <% } %>>강북구</option>
								<option value="4" <% if (local.equals("4"))  { %>selected="selected" <% } %>>강서구</option>
								<option value="5" <% if (local.equals("5"))  { %>selected="selected" <% } %>>관악구</option>
								<option value="6" <% if (local.equals("6"))  { %>selected="selected" <% } %>>광진구</option>
								<option value="7" <% if (local.equals("7"))  { %>selected="selected" <% } %>>구로구</option>
								<option value="8" <% if (local.equals("8"))  { %>selected="selected" <% } %>>금천구</option>
								<option value="9" <% if (local.equals("9"))  { %>selected="selected" <% } %>>노원구</option>
								<option value="10" <% if (local.equals("10")) { %>selected="selected" <% } %>>도봉구</option>
								<option value="11" <% if (local.equals("11")) { %>selected="selected" <% } %>>동대문구</option>
								<option value="12" <% if (local.equals("12")) { %>selected="selected" <% } %>>동작구</option>
								<option value="13" <% if (local.equals("13")) { %>selected="selected" <% } %>>마포구</option>
								<option value="14" <% if (local.equals("14")) { %>selected="selected" <% } %>>서대문구</option>
								<option value="15" <% if (local.equals("15")) { %>selected="selected" <% } %>>서초구</option>
								<option value="16" <% if (local.equals("16")) { %>selected="selected" <% } %>>성동구</option>
								<option value="17" <% if (local.equals("17")) { %>selected="selected" <% } %>>성북구</option>
								<option value="18" <% if (local.equals("18")) { %>selected="selected" <% } %>>송파구</option>
								<option value="19" <% if (local.equals("19")) { %>selected="selected" <% } %>>양천구</option>
								<option value="20" <% if (local.equals("20")) { %>selected="selected" <% } %>>영등포구</option>
								<option value="21" <% if (local.equals("21")) { %>selected="selected" <% } %>>용산구</option>
								<option value="22" <% if (local.equals("22")) { %>selected="selected" <% } %>>은평구</option>
								<option value="23" <% if (local.equals("23")) { %>selected="selected" <% } %>>종로구</option>
								<option value="24" <% if (local.equals("24")) { %>selected="selected" <% } %>>중구</option>
								<option value="25" <% if (local.equals("25")) { %>selected="selected" <% } %>>중랑구</option>
							</select>
						</form>
						<form action="/anavada/adjlistview.ad" method="post"
							style="display: inline-block;">
							<input type="hidden" name="local" value="<%=local%>"> 목록
							분류 : <select name="listsearch" class="ListSelect"
								onchange=this.form.submit()>
								<option value="latestposts"${param.listsearch eq"latestposts"?"selected" :"" }>최신등록순</option>
								<option value="highprice"${param.listsearch eq"highprice"?"selected" :"" }>가격높은순</option>
								<option value="lowprice"${param.listsearch eq"lowprice"?"selected" :"" }>가격낮은순</option>
								<option value="highlike"${param.listsearch eq"highlike"?"selected" :"" }>좋아요순</option>
							</select> <input type="text" name="titlesearch" placeholder="검색어를 입력해주세요.">
							<button type="submit" class="top-search">
								<i class="xi-search"></i>
							</button>
						</form>
					</div>
				</div>
				<!-- 검색영역 끝 -->
				<form>
					<input type="hidden" name="vals" value="$()">
					<table class="jboardTable memberTable">
						<colgroup>
							<col width="5%">
							<col width="8%">
							<col width="10%">
							<col width="10%">
							<col width="*">
							<col width="5%">
							<col width="5%">
							<col width="10%">
							<col width="10%">
						</colgroup>
						<tbody>
							<tr>
								<th><input id="checkAll" type="checkbox"></th>
								<th>글번호</th>
								<th>아이디</th>
								<th>지역명</th>
								<th>글제목</th>
								<th>조회수</th>
								<th>좋아요</th>
								<th>작성일자</th>
								<th>수정일자</th>
							</tr>

							<%for(Jboard j : jlist) {%>
							<tr
								onclick="location.href='/anavada/jbdetail?jboardno=<%= j.getJboardNo()%>'">
								<td class="checkBox" onclick="event.cancelBubble=true"><input
									type="checkbox" name="check" value="<%= j.getJboardNo()%>">
								</td>
								<td class="jboardNo"><%= j.getJboardNo() %></td>
								<td class="memberId"><%= j.getMemberId() %></td>
								<td class="localNo"><%= localArr[Integer.parseInt(j.getLocalNo()) - 1] %></td>
								<td class="jboardtitle"><%= j.getJboardTitle() %></td>
								<td class="jboardcount"><%= j.getJboardCount() %></td>
								<td class="jboardlike"><%= j.getJboardLike() %></td>
								<td class="jboarddate"><%= j.getJboardDate() %></td>
								<td class="jboardupdate">
									<% if(j.getJboardUpdate() != null) { %> <%= j.getJboardUpdate() %></td>
								<% } %>
							</tr>
							<%}%>

						</tbody>
					</table>
					<% if (listCount == 0){ %>
					<div class="list-no">
						<p>
							<img src="/anavada/resources/images/btnIcn/icn_big_listNo.png"
								alt="" title="" />
						</p>
						<h1>목록이 없습니다.</h1>
					</div>
					<% } %>

					<p class="warning_text">*삭제한 글은 복구가 불가능하니 신중하게 선택하세요.</p>
					<!-- //게시판 -->

					<!-- 버튼 -->
					<div class="btn_wrap">
						<button id="delete_btn" class="btn-left btn_gray">선택삭제</button>
						<a href="views/jboard/product_write.jsp"
							class="btn-right btn_white">등록</a>
					</div>
				</form>



				<!-- 페이징 -->
				<dl class="list-paging pb80">
					<dd>
						<% if (currentPage <= 1){ %>
						<a><i class="glyphicon glyphicon-backward"></i></a>
						<%}else {%>
						<a
							href="/anavada/adjlistview.ad?local=<%=local%>&titleserch=<%=titleSearch%>&listsearch=<%=listSearch%>"><i
							class="glyphicon glyphicon-backward"></i></a>
						<%} %>
						<% if (startPage -1 >=10 ){ %>
						<a
							href="/anavada/adjlistview.ad?page=<%=startPage - 10%>&local=<%=local%>&listsearch=<%=listSearch%>&titlesearch=<%=titleSearch%>">
							<i class="glyphicon glyphicon-menu-left"></i> <%} %> <!-- 현재 페이지가 속한 페이지 그룹의 숫자 출력처리 -->
							<% for (int p = startPage; p <= endPage; p++){
						if (p == currentPage){%> <a href="#none" class="active"><%=p %></a>
							<%} else {%> <a
							href="/anavada/adjlistview.ad?page=<%=p%>&local=<%=local%>&listsearch=<%=listSearch%>&titlesearch=<%=titleSearch%>"><%=p%></a>
							<%}} %> <!--  다음 그룹으로 이동처리 --> <% if (endPage +1 <= maxPage){ %> <a
							href="/anavada/adjlistview.ad?page=<%=endPage + 1%>&local=<%=local%>&listsearch=<%=listSearch%>&titlesearch=<%=titleSearch%>"><i
								class="glyphicon glyphicon-menu-right"></i></a> <%} %> <% if (currentPage >= maxPage){ %>
							<a><i class="glyphicon glyphicon-forward"></i></a> <%}else{ %> <a
							href="/anavada/adjlistview.ad?page=<%=maxPage%>&local=<%=local%>&listsearch=<%=listSearch%>&titlesearch=<%=titleSearch%>"><i
								class="glyphicon glyphicon-forward"></i></a> <%} %>
						
					</dd>
				</dl>
				<!-- //페이징 -->

			</div>
			<%@ include file="../include/admin_footer.jsp"%>
		</div>
	</div>
</body>
</html>