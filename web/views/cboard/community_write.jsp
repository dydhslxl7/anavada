<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/head.jsp"%>
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
	<div id="wrap">
		<%@ include file="../include/header.jsp"%>

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
				<form action="/anavada/cinsert" method="post" enctype="multipart/form-data">
					<input type="hidden" name="writer" value="<%=loginMember.getMemberId()%>">
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
										<option selected="selected" value="">지역선택</option>
										<option value="1">강남구</option>
										<option value="2">강동구</option>
										<option value="3">강북구</option>
										<option value="4">강서구</option>
										<option value="5">관악구</option>
										<option value="6">광진구</option>
										<option value="7">구로구</option>
										<option value="8">금천구</option>
										<option value="9">노원구</option>
										<option value="10">도봉구</option>
										<option value="11">동대문구</option>
										<option value="12">동작구</option>
										<option value="13">마포구</option>
										<option value="14">서대문구</option>
										<option value="15">서초구</option>
										<option value="16">성동구</option>
										<option value="17">성북구</option>
										<option value="18">송파구</option>
										<option value="19">양천구</option>
										<option value="20">영등포구</option>
										<option value="21">용산구</option>
										<option value="22">은평구</option>
										<option value="23">종로구</option>
										<option value="24">중구</option>
										<option value="25">중랑구</option>
									</select>
								</td>
							</tr>
							<tr>
								<td>제목</td>
								<td>
									<input type="text" name="title" class="form-control w100p" placeholder="제목" required="required">
								</td>
							</tr>
							<tr>
								<td>내용</td>
								<td>
									<textarea name="content" rows="10" cols="1000" class="form-control" style="resize: none; width: 100%; min-height: 300px; max-height: 300px;" required="required"></textarea>
								</td>
							</tr>
							<%
								for (int i = 0; i < 4; i++) {
							%>
							<tr>
								<td>
									첨부파일<%=i + 1%></td>
								<td>
									<input type="file" name="ofile<%=i + 1%>">
								</td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
					<div class="write-btn">
						<a href="/anavada/clistview?page=1&local=0" class="btn btn-list">목록</a>
						<button class="btn btn-success">작성하기</button>
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