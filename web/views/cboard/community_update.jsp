<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cboard.model.vo.Cboard"%>
<%
	Cboard cboard = (Cboard) request.getAttribute("cboard");
	String local = String.valueOf(request.getAttribute("local"));
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
			<div class="visual-sub-vagas community-vagas">
				<div class="vsv-copy sub-title">
					<div>
						<ul class="navi">
							<li><a href="views/main/main.jsp">홈</a></li>
							<li class="glyphicon glyphicon-menu-right"><a href="/anavada/clistview">커뮤니티</a></li>
						</ul>
					</div>
					<h2>
						<span>커뮤니티</span>
					</h2>
					<h3>우리의 이웃과 'Anavada'를 통해 소통할 수 있는 공간입니다.</h3>
				</div>
			</div>
			<!--서브 비주얼/타이틀 끝-->

			<!-- 글쓰기 -->
			<div class="write-area">
				<h2>커뮤니티 작성</h2>
				<form action="/anavada/cupdate" method="post" enctype="multipart/form-data">
					<input type="hidden" name="cnum" value="<%=cboard.getCboardNo()%>">
					<input type="hidden" name="ofile1" value="<%if (cboard.getCfilesOriginalFilepath1() != null) {%><%=cboard.getCfilesOriginalFilepath1()%><%}%>">
					<input type="hidden" name="rfile1" value="<%if (cboard.getCfilesRenameFilepath1() != null) {%><%=cboard.getCfilesRenameFilepath1()%><%}%>">
					<input type="hidden" name="ofile2" value="<%if (cboard.getCfilesOriginalFilepath2() != null) {%><%=cboard.getCfilesOriginalFilepath2()%><%}%>">
					<input type="hidden" name="rfile2" value="<%if (cboard.getCfilesRenameFilepath2() != null) {%><%=cboard.getCfilesRenameFilepath2()%><%}%>">
					<input type="hidden" name="ofile3" value="<%if (cboard.getCfilesOriginalFilepath3() != null) {%><%=cboard.getCfilesOriginalFilepath3()%><%}%>">
					<input type="hidden" name="rfile3" value="<%if (cboard.getCfilesRenameFilepath3() != null) {%><%=cboard.getCfilesRenameFilepath3()%><%}%>">
					<input type="hidden" name="ofile4" value="<%if (cboard.getCfilesOriginalFilepath4() != null) {%><%=cboard.getCfilesOriginalFilepath4()%><%}%>">
					<input type="hidden" name="rfile4" value="<%if (cboard.getCfilesRenameFilepath4() != null) {%><%=cboard.getCfilesRenameFilepath4()%><%}%>">
					<table>
						<colgroup>
							<col width="20%">
							<col width="80%">
						</colgroup>
						<tbody>
							<tr>
								<td>지역 분류</td>
								<td>
									<!-- 지역 선택 -->
									<select name="local" class="LocationSelect" required="required">
										<option value="" selected="selected">지역선택</option>
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
								</td>
							</tr>
							<tr>
								<td>제목</td>
								<td>
									<input type="text" name="title" class="form-control w100p" placeholder="제목" value="<%=cboard.getCboardTitle()%>" required="required">
								</td>
							</tr>
							<tr>
								<td>내용</td>
								<td>
									<textarea name="content" rows="10" cols="1000" class="form-control" style="resize: none; width: 100%; min-height: 300px; max-height: 300px;" required="required"><%=cboard.getCboardContent()%></textarea>
								</td>
							</tr>
							<!-- 파일 첨부 -->
							<%
								for (int i = 0; i < 4; i++) {
							%>
							<tr>
								<td>
									첨부파일<%=i + 1%></td>
								<td>
									<%
										if (i == 0 && (cboard.getCfilesOriginalFilepath1() != null) && !cboard.getCfilesOriginalFilepath1().equals("null")) {
									%>
									<%=cboard.getCfilesOriginalFilepath1()%>
									&nbsp; &nbsp; &nbsp;
									<input type="checkbox" name="delflag1" value="yes">
									파일삭제
									<%
										}
									%>
									<%
										if (i == 1 && (cboard.getCfilesOriginalFilepath2() != null) && !cboard.getCfilesOriginalFilepath2().equals("null")) {
									%>
									<%=cboard.getCfilesOriginalFilepath2()%>
									&nbsp; &nbsp; &nbsp;
									<input type="checkbox" name="delflag2" value="yes">
									파일삭제
									<%
										}
									%>
									<%
										if (i == 2 && (cboard.getCfilesOriginalFilepath3() != null) && !cboard.getCfilesOriginalFilepath3().equals("null")) {
									%>
									<%=cboard.getCfilesOriginalFilepath3()%>
									&nbsp; &nbsp; &nbsp;
									<input type="checkbox" name="delflag3" value="yes">
									파일삭제
									<%
										}
									%>
									<%
										if (i == 3 && (cboard.getCfilesOriginalFilepath4() != null) && !cboard.getCfilesOriginalFilepath4().equals("null")) {
									%>
									<%=cboard.getCfilesOriginalFilepath4()%>
									&nbsp; &nbsp; &nbsp;
									<input type="checkbox" name="delflag4" value="yes">
									파일삭제
									<%
										}
									%>
									<input type="file" name="upfile<%=i + 1%>">
								</td>
							</tr>
							<%
								}
							%>

						</tbody>
					</table>
					<div class="write-btn">
						<a href="/anavada/clistview?page=1&local=0" class="btn btn-list">목록</a>
						<button class="btn btn-success" onclick="location.href=/anavada/cupdate">수정하기</button>
					</div>
				</form>
			</div>
			<!-- 글쓰기 끝 -->

		</div>
		<!-- 컨텐츠 끝 -->

		<%@ include file="../include/footer.jsp"%>
	</div>
</body>
</html>