<%@page import="java.util.ArrayList"%>
<%@page import="cboard.model.service.CboardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="cboard.model.vo.Cboard"%>
<%@page import="creply.model.vo.Creply"%>
<%
	ArrayList<Creply> rlist = (ArrayList<Creply>) request.getAttribute("rlist");
	ArrayList<Creply> srlist = (ArrayList<Creply>) request.getAttribute("srlist");
	Cboard cboard = (Cboard) request.getAttribute("cboard");
	String local = String.valueOf(request.getAttribute("local"));
	String search = String.valueOf(request.getAttribute("search"));
	String keyword = String.valueOf(request.getAttribute("keyword"));
	int currentPage = ((Integer) request.getAttribute("page")).intValue();
	int allListCount = ((Integer) request.getAttribute("allListCount")).intValue();
	int allReplyCount = ((Integer) request.getAttribute("allReplyCount")).intValue();
	String[] localArr = {"강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구",
			"마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"};
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/head.jsp"%>
<script type="text/javascript">
	$(function() {
		/* 대댓글 버튼 토글 */
		$(".Subcmt_btn").click(function() {
			$(this).siblings(".Subcmt").toggle(200);
		});
		/* 대댓글 수정 버튼 토글 */
		$(".Subcmt_update_btn").click(function() {
			$(this).siblings(".Subcmt_update").toggle(200);
		});
		/* 댓글 수정 버튼 토글 */
		$(".Cmt_update_btn").click(function() {
			$(this).siblings(".Cmt_update").toggle(200);
		});
	});
</script>
<style type="text/css">
.like {
	width: 50px;
	height: 50px;
	padding: 1px;
	border-radius: 50%;
	border: 0px;
	margin-right: 15px;
}

.report {
	width: 50px;
	height: 50px;
	padding: 1px;
	border-radius: 50%;
	border: 0px;
	margin-right: 15px;
}

font {
	font-size: 20px;
	margin-right: 15px;
}
</style>
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

			<!-- 상세 -->
			<div class="view-area cmnt_view">
				<h2><span><%=localArr[Integer.parseInt(cboard.getLocalNo()) - 1]%></span><%=cboard.getCboardTitle()%></h2>
				<ul>
					<li><span>작성자 : </span><%=cboard.getMemberId()%></li>
					<li><span>등록일 : </span><%=cboard.getDate()%></li>
					<li><span>조회수 : </span><%=cboard.getCboardViewCount()%></li>
					<li><i class="good_i glyphicon glyphicon-heart-empty">좋아요<%=cboard.getLikeCount()%></i></li>
				</ul>
				<!-- 첨부파일이 있을 때 파일명 출력 -->
				<%
					if ((cboard.getCfilesOriginalFilepath1() != null) || (cboard.getCfilesOriginalFilepath2() != null) || (cboard.getCfilesOriginalFilepath3() != null) || (cboard.getCfilesOriginalFilepath4() != null)) {
				%>
				<ul>
					<%
						for (int i = 0; i < 4; i++) {
					%>
						<%
							if (i == 0 && (cboard.getCfilesOriginalFilepath1() != null) && !cboard.getCfilesOriginalFilepath1().equals("null")) {
						%>
						<li><span>첨부파일#1 : </span>
						<a href="/anavada/cfdown?ofile=<%=cboard.getCfilesOriginalFilepath1()%>&rfile=<%=cboard.getCfilesRenameFilepath1()%>"><%=cboard.getCfilesOriginalFilepath1()%></a></li>
						<%
							} else if (i == 1 && cboard.getCfilesOriginalFilepath2() != null && !cboard.getCfilesOriginalFilepath2().equals("null")) {
						%>
						<li><span>첨부파일#2 : </span>
						<a href="/anavada/cfdown?ofile=<%=cboard.getCfilesOriginalFilepath2()%>&rfile=<%=cboard.getCfilesRenameFilepath2()%>"><%=cboard.getCfilesOriginalFilepath2()%></a></li>
						<%
							} else if (i == 2 && cboard.getCfilesOriginalFilepath3() != null && !cboard.getCfilesOriginalFilepath3().equals("null")) {
						%>
						<li><span>첨부파일#3 : </span>
						<a href="/anavada/cfdown?ofile=<%=cboard.getCfilesOriginalFilepath3()%>&rfile=<%=cboard.getCfilesRenameFilepath3()%>"><%=cboard.getCfilesOriginalFilepath3()%></a></li>
						<%
							} else if (i == 3 && cboard.getCfilesOriginalFilepath4() != null && !cboard.getCfilesOriginalFilepath4().equals("null")) {
						%>
						<li><span>첨부파일#4 : </span>
						<a href="/anavada/cfdown?ofile=<%=cboard.getCfilesOriginalFilepath4()%>&rfile=<%=cboard.getCfilesRenameFilepath4()%>"><%=cboard.getCfilesOriginalFilepath4()%></a></li>
						<%
							}
						%>

					<%
						}
					%>
				</ul>
				<%
					}
				%>
				<div class="view-ctn">
					<%=cboard.getCboardContent().replace("\r\n", "<br>")%>
				</div>
		
				<!-- 좋아요 신고 버튼 -->
				<div style="float: center; margin: 10px; text-align: center;">
					<%
						if (loginMember != null) {
					%>
					<div style="display: inline-block;">
						<button onclick="location.href='/anavada/uplike?cnum=<%=cboard.getCboardNo()%>&memberId=<%=loginMember.getMemberId()%>'" class="like">
							<i class="xi-heart" style="font-size: 300%;"></i>
						</button>
						<br> <font><%=cboard.getLikeCount()%></font>
					</div>
		
					<div style="display: inline-block;">
						<button onclick="location.href='/anavada/views/declare/declare_write.jsp'" class="report">
							<i class="xi-error" style="font-size: 300%;"></i>
						</button>
						<br> <font>신고</font>
					</div>
					<%
						} else {
					%>
					<div style="display: inline-block;">
						<button type="button" onclick="alert('로그인 후 이용해주세요');location.href='/anavada/views/member/login.jsp';" class="like">
							<i class="xi-heart" style="font-size: 300%;"></i>
						</button>
						<br> <font><%=cboard.getLikeCount()%></font>
					</div>
		
					<div style="display: inline-block;">
						<button type="button" onclick="alert('로그인 후 이용해주세요');location.href='/anavada/views/member/login.jsp';" class="report">
							<i class="xi-error" style="font-size: 300%;"></i>
						</button>
						<br>
						<font>신고</font>
					</div>
					<%
						}
					%>
				</div>



				<div class="view-btn">
					<!-- 이전글 -->
					<%
						if (cboard.getCboardNo() > 1) {
					%>
					<a href="/anavada/cdetail?cnum=<%=cboard.getCboardNo() - 1%>&page=<%=currentPage%>&local=<%=local%>&search=<%=search%>&keyword=<%=keyword%>" class="btn btn-prev">이전글</a>
					<%
						}
					%>
					<!-- 수정 버튼 -->
					<%
						if (loginMember != null && loginMember.getMemberId().equals(cboard.getMemberId())) {
					%>
					<a href="/anavada/cupdateview.ss?cnum=<%=cboard.getCboardNo()%>&local=<%=cboard.getLocalNo()%>" class="btn btn-list">수정</a>
					<%
						}
					%>
					<!-- 삭제 버튼 -->
					<%
						if (loginMember != null && loginMember.getMemberId().equals(cboard.getMemberId())) {
					%>
					<a href="/anavada/cdelete?cnum=<%=cboard.getCboardNo()%>&rfile1=<%=cboard.getCfilesRenameFilepath1()%>
						&rfile2=<%=cboard.getCfilesRenameFilepath2()%>&rfile3=<%=cboard.getCfilesRenameFilepath3()%>&rfile4=<%=cboard.getCfilesRenameFilepath4()%>" class="btn btn-list">삭제</a>
					<%
						}
					%>

					<a href="/anavada/clistview?page=<%=currentPage%>&local=<%=local%>&search=<%=search%>&keyword=<%=keyword%>" class="btn btn-list">목록</a>
					
					<!-- 다음글 버튼 -->
					<%
						if (cboard.getCboardNo() < allListCount) {
					%>
					<a href="/anavada/cdetail?cnum=<%=cboard.getCboardNo() + 1%>&page=<%=currentPage%>&local=<%=local%>&search=<%=search%>&keyword=<%=keyword%>" class="btn btn-next">다음글</a>
					<%
						}
					%>
				</div>
				<div class="cmt_wrap">
					<h4 class="cmt_head">댓글<%=allReplyCount%></h4>
					<%
						if (loginMember != null) {
					%>
					<!-- 댓글 작성 -->
					<form action="crwrite.ss" method="post">
						<input type="hidden" name="writer" value="<%=loginMember.getMemberId()%>">
						<input type="hidden" name="cnum" value="<%=cboard.getCboardNo()%>">
						<fieldset>
							<div class="cmt_form">
								<div class="cmt_body">
									<textarea name="content" style="resize: none; width: 100%; min-height: 100px; max-height: 100px;" onfocus="this.value='';">비방글은 작성하실 수 없습니다.</textarea>
									<div class="cmt_ok">
										<input type="submit" value="등록">
									</div>
								</div>
							</div>
						</fieldset>
					</form>
					<!-- 댓글 작성 끝 -->
					<%
						}
					%>
					<ul class="cmt_con">
						<!-- 댓글 리스트 -->
						<%
							for (Creply c : rlist) {
						%>
						<li>
							<div>
								<h4><%=c.getMemberId()%></h4>
								<span><%=c.getCreplyDate()%></span>
							</div>
							<p><%=c.getCreplyContent().replace("\r\n", "<br>")%></p>
							<button class="Subcmt_btn">대댓글</button>
							<%
							 	if (loginMember != null && loginMember.getMemberId().equals(c.getMemberId())) {
							%>
							<button onclick="location.href='/anavada/crdelete?crnum=<%=c.getCreplyNo()%>&depth=<%=c.getCreplyDepth()%>&cnum=<%=cboard.getCboardNo()%>'" style="float: right;">삭제</button>
							<button class="Cmt_update_btn" style="float: right; margin-right: 10px;">수정</button>
							<!-- 댓글수정입력 폼 시작 -->
							<div class="Cmt_update" style="display: none;">
								<form action="crupdate.ss" method="post">
									<input type="hidden" name="crnum" value="<%=c.getCreplyNo()%>">
									<input type="hidden" name="cnum" value="<%=c.getCbaordNo()%>">
									<fieldset>
										<div class="cmt_form">
											<div class="cmt_body">
												<textarea name="content" style="resize: none; width: 100%; min-height: 100px; max-height: 100px;" onfocus="this.value='';"><%=c.getCreplyContent()%></textarea>
												<div class="cmt_ok">
													<input type="submit" value="수정">
												</div>
											</div>
										</div>
									</fieldset>
								</form>
							</div>
							<!-- 댓글수정입력 폼 끝 -->
							<%
								}
							%>
						<%
							if (loginMember != null) {
						%>
							<!-- 대댓글 등록 폼 시작 -->
							<div class="Subcmt" style="display: none;">
								<form action="scwrite.ss" method="post">
									<input type="hidden" name="writer" value="<%=loginMember.getMemberId()%>">
									<input type="hidden" name="cnum" value="<%=cboard.getCboardNo()%>">
									<input type="hidden" name="prnum" value="<%=c.getCreplyNo()%>">
									<fieldset>
										<div class="cmt_form">
											<div class="cmt_body">
												<textarea name="content" style="resize: none; width: 100%; min-height: 100px; max-height: 100px;" onfocus="this.value='';">비방글은 작성하실 수 없습니다.</textarea>
												<div class="cmt_ok">
													<input type="submit" value="등록">
												</div>
											</div>
										</div>
									</fieldset>
								</form>
							</div>
							<!-- 대댓글 등록 폼 끝 -->
						<%
						 	}
						%>
						<!-- 대댓글 리스트 -->
					<%
						for (Creply sc : srlist) {
					%>
						<%
							if (c.getCreplyNo() == sc.getParantReply()) {
						%>
							<div class="Subcmt_form" style="background-color: #E6E6E6;">
								<div>
									<h4><%=sc.getMemberId()%></h4>
									<span><%=sc.getCreplyDate()%></span>
								</div>
								<p><%=sc.getCreplyContent().replace("\r\n", "<br>")%></p>
								<!-- 대댓글 작성자와 로그인한 유저가 같을 떄 -->
							<%
								if (loginMember != null && loginMember.getMemberId().equals(sc.getMemberId())) {
							%>
								<button onclick="location.href='/anavada/crdelete?crnum=<%=sc.getCreplyNo()%>&depth=<%=sc.getCreplyDepth()%>&cnum=<%=cboard.getCboardNo()%>'" style="float: right;">삭제</button>
								<button class="Subcmt_update_btn" style="float: right; margin-right: 10px;">수정</button>
								<div class="Subcmt_update" style="display: none;">
									<form action="crupdate.ss" method="post">
										<input type="hidden" name="crnum" value="<%=sc.getCreplyNo()%>">
										<input type="hidden" name="cnum" value="<%=sc.getCbaordNo()%>">
										<fieldset>
											<div class="cmt_form">
												<div class="cmt_body">
													<textarea name="content" style="resize: none; width: 100%; min-height: 100px; max-height: 100px;" onfocus="this.value='';"><%=sc.getCreplyContent()%></textarea>
													<div class="cmt_ok">
														<input type="submit" value="수정">
													</div>
												</div>
											</div>
										</fieldset>
									</form>
								</div>
							<%
								}
							%>
							</div>
						<%
							}
						%>
					<%
					 	}
					%>
					<!-- 대댓글 리스트 끝 -->
					</li>
				<%
					}
				%>
				<!-- 댓글 리스트 끝 -->
					</ul>
				</div>
			</div>
			<!-- 상세 끝 -->

		</div>
		<!-- 컨텐츠 끝 -->

		<%@ include file="../include/footer.jsp"%>
	</div>
</body>
</html>