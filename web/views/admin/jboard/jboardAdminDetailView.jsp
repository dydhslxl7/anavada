
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  page
	import="jboard.model.vo.Jboard , java.util.ArrayList, java.sql.Date, jboard.model.vo.Comment, java.text.SimpleDateFormat"%>
<%
	ArrayList<Comment> clist = (ArrayList<Comment>) request.getAttribute("list");
	//SimpleDateFormat lastUpdate =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	Jboard jboard = (Jboard)(request.getAttribute("jboardno"));
	String post = String.valueOf(request.getAttribute("post"));
	String meet = String.valueOf(request.getAttribute("meet"));
	String titleSearch = String.valueOf(request.getParameter("titlesearch"));
	String listSearch = String.valueOf(request.getParameter("listsearch"));
	int commentListCount = ((Integer) request.getAttribute("commentlistcount")).intValue();
	int currentPage = ((Integer)request.getAttribute("page")).intValue();
	String[] localArr = { "강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구" };
%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="../include/admin_head.jsp"%>

<script type="text/javascript">
        $(function (){ /* 댓글버튼 */
            $(".Subcmt_btn").click(function () {
               $(this).siblings(".Subcmt").toggle(200);
            });
            $(".Subcmt_update_btn").click(function () {
               $(this).siblings(".Subcmt_update").toggle(200);
            });
            $(".Cmt_update_btn").click(function () {
               $(this).siblings(".Cmt_update").toggle(200);
            });
         });
</script>
<style>
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

.swiper-container {
	width: 100%;
	height: 300px;
	margin-left: auto;
	margin-right: auto;
}

.swiper-slide {
	background-size: cover;
	background-position: center;
}

.gallery-top {
	height: 80%;
	width: 100%;
}

.gallery-thumbs {
	height: 20%;
	box-sizing: border-box;
	padding: 10px 0;
}

.gallery-thumbs .swiper-slide {
	width: 25%;
	height: 100%;
	opacity: 0.4;
}

.gallery-thumbs .swiper-slide-thumb-active {
	opacity: 1;
}
</style>
<script>
    var galleryThumbs = new Swiper('.gallery-thumbs', {
        spaceBetween: 10,
        slidesPerView: 4,
        freeMode: true,
        watchSlidesVisibility: true,
        watchSlidesProgress: true,
    });
    var galleryTop = new Swiper('.gallery-top', {
        spaceBetween: 10,
        navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },
        thumbs: {
        swiper: galleryThumbs
        }
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

				<!-- 뷰 -->
				<div class="product_view_area">
					<dl class="product_info">
						<dt>
							<div class="swiper-container gallery-top">
								<ul class="swiper-wrapper">
									<%if (jboard.getJboardRenameFilePath1() !=null){ %> <!-- 파일이 있다면 출력 -->
									<li class="swiper-slide"><img src="/anavada/resources/jboardfiles/<%=jboard.getJboardRenameFilePath1()%>" /></li>
									<%}else{ %>
									<li class="swiper-slide"><img src="/anavada/resources/jboardfiles/test.jpg" /></li>
									<%} %>
									<%if (jboard.getJboardRenameFilePath2() !=null){ %>
									<li class="swiper-slide"><img src="/anavada/resources/jboardfiles/<%=jboard.getJboardRenameFilePath2()%>" /></li>
									<%}else{ %>
									<li class="swiper-slide"><img src="/anavada/resources/jboardfiles/test.jpg" /></li>
									<%} %>
									<%if (jboard.getJboardRenameFilePath3() !=null){ %>
									<li class="swiper-slide"><img src="/anavada/resources/jboardfiles/<%=jboard.getJboardRenameFilePath3()%>" /></li>
									<%}else{ %>
									<li class="swiper-slide"><img src="/anavada/resources/jboardfiles/test.jpg" /></li>
									<%} %>
									<%if (jboard.getJboardRenameFilePath4() !=null){ %>
									<li class="swiper-slide"><img src="/anavada/resources/jboardfiles/<%=jboard.getJboardRenameFilePath4()%>" /></li>
									<%}else{ %>
									<li class="swiper-slide"><img src="/anavada/resources/jboardfiles/test.jpg" /></li>
									<%} %>
								</ul>
								<!-- Add Arrows -->
								<div class="swiper-button-next swiper-button-white"></div>
								<div class="swiper-button-prev swiper-button-white"></div>
							</div>

							<div class="swiper-container gallery-thumbs">
								<ol class="swiper-wrapper">
									<%if (jboard.getJboardRenameFilePath1() !=null){ %>
									<li class="swiper-slide"><img src="/anavada/resources/jboardfiles/<%=jboard.getJboardRenameFilePath1()%>" /></li>
									<%}else{ %>
									<li class="swiper-slide"><img src="/anavada/resources/jboardfiles/테스트.jpg" /></li>
									<%} %>
									<%if (jboard.getJboardRenameFilePath2() !=null){ %>
									<li class="swiper-slide"><img src="/anavada/resources/jboardfiles/<%=jboard.getJboardRenameFilePath2()%>" /></li>
									<%}else{ %>
									<li class="swiper-slide"><img src="/anavada/resources/jboardfiles/테스트.jpg" /></li>
									<%} %>
									<%if (jboard.getJboardRenameFilePath3() !=null){ %>
									<li class="swiper-slide"><img src="/anavada/resources/jboardfiles/<%=jboard.getJboardRenameFilePath3()%>" /></li>
									<%}else{ %>
									<li class="swiper-slide"><img src="/anavada/resources/jboardfiles/테스트.jpg" /></li>
									<%} %>
									<%if (jboard.getJboardRenameFilePath4() !=null){ %>
									<li class="swiper-slide"><img src="/anavada/resources/jboardfiles/<%=jboard.getJboardRenameFilePath4()%>" /></li>
									<%}else{ %>
									<li class="swiper-slide"><img src="/anavada/resources/jboardfiles/테스트.jpg" /></li>
									<%} %>
								</ol>
							</div>

							<!-- Initialize Swiper -->
							<script>
                            var galleryThumbs = new Swiper('.gallery-thumbs', {
                                spaceBetween: 10,
                                slidesPerView: 4,
                                freeMode: true,
                                watchSlidesVisibility: true,
                                watchSlidesProgress: true,
                            });
                            var galleryTop = new Swiper('.gallery-top', {
                                spaceBetween: 10,
                                navigation: {
                                nextEl: '.swiper-button-next',
                                prevEl: '.swiper-button-prev',
                                },
                                autoplay: {
                                    delay: 2000,
                                    disableOnInteraction: false,
                                },
                                thumbs: {
                               swiper: galleryThumbs
                                }
                            });
                        </script>
						</dt>
						<dd>
							<dl>
								<dt><%=localArr[Integer.parseInt(jboard.getLocalNo())-1] %></dt>
								<dd>
									<button type="button" onclick="" class="lookup_btn">사기조회</button>
									<button
										onclick="location.href='/anavada/views/declare/declare_write.jsp'"
										class="lookup_btn">신고하기</button>
								</dd>
							</dl>
							<div>
								<i class="good_i glyphicon glyphicon-heart-empty">좋아요<span>&nbsp;<%=jboard.getJboardLike() %>
										&nbsp; <% if (meet.equals("Y"))  { %>직거래상품<% } %> <% if (post.equals("Y"))  { %>우편거래상품<% } %></span></i>
								<h2 class="product_name">
									<b>상품명 : </b><span><%=jboard.getJboardTitle() %></span>
								</h2>
								<h3 class="product_price">
									<b>판매가격 : </b><span><%=jboard.getJboardPrice() %></span>원
								</h3>
								<p class="view-ctn">
									<%= jboard.getJboardContent().replace("\r\n","<br>") %>
								</p>
								<div>
									<b>판매자 아이디 : <%= jboard.getMemberId()%> &nbsp; &nbsp; /
										&nbsp; &nbsp; 조회수 : <%=jboard.getJboardCount()%></b><br /> <b>
										등록일자: </b><%= jboard.getJboardDate()%>
									&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<b>최종수정일자 : </b><%=jboard.getJboardUpdate() %>
								</div>
							</div>
						</dd>
					</dl>
					<div class="view-btn">

						<% if (loginMember != null){%>
						<%	if(loginMember.getMemberId().equals(jboard.getMemberId())){ %>
						<a href="/anavada/jbupview?jboardno=<%=jboard.getJboardNo() %>&page=<%=currentPage %>" class="btn btn-list">수정</a> 
							<a href="/anavada/jbdelete?jboardno=<%=jboard.getJboardNo() %>&page=<%=currentPage %>&rfile1=<%=jboard.getJboardRenameFilePath1() %> 
                 	&file2=<%=jboard.getJboardRenameFilePath2() %>&file3=<%=jboard.getJboardRenameFilePath3() %>&file4=<%=jboard.getJboardRenameFilePath4() %>"
							class="btn btn-list">삭제</a>
						<%}} %>
						<a href="javascript:history.go(-1);" class="btn btn-list">목록</a>
					</div>

					<div class="cmt_wrap">
						<form action="/anavada/jbcinsert.ss" method="post">
							<fieldset>
								<div class="cmt_form">
									<h4 class="cmt_head">
										전체 댓글 수 :<%=commentListCount %></h4>
									<% if(loginMember != null) { %>
									<input type="hidden" name="jboardno"
										value="<%=jboard.getJboardNo() %>"> <input
										type="hidden" name="commentid"
										value="<%=jboard.getMemberId()%>">
									<div class="cmt_body">
										<textarea name="commentcontent"
											style="resize: none; width: 100%; min-height: 100px; max-height: 100px;"
											onfocus="this.value='';">비방글은 작성하실 수 없습니다.</textarea>
										<div class="cmt_ok">
											<input type="submit" value="등록">
										</div>
									</div>
								</div>
							</fieldset>
						</form>
						<%} %>



						<!-- 댓글부분  -->

						<% for (Comment comment : clist ){ %>
						<% if (clist.size()>0 && jboard.getJboardNo() == comment.getJboardNo()){ %>
						<ul class="cmt_con">
							<li>
								<div>
									<h4><%=comment.getCommentId() %></h4>
									<span>마지막 수정일<%=comment.getCommentLastModified() %></span>
								</div>
								<p><%=comment.getCommentContent().replace("\r\n","<br>") %></p>
								<% if(loginMember != null&& comment.getCommentLevel()<2) { %>
								<button class="Subcmt_btn">대댓글</button> <%} %> <%if(loginMember != null && loginMember.getMemberId().equals(comment.getCommentId())) { %>
								<button onclick="location.href='/anavada/jbcdelete?commentno=<%= comment.getCommentNo() %>&jboardno=<%= jboard.getJboardNo() %>'"
									style="float: right;">삭제</button>
								<button class="Cmt_update_btn"
									style="float: right; margin-right: 10px;">수정</button>
								<div class="Cmt_update" style="display: none;">
									<form action="/anavada/jbcupdate.ss" method="post">
										<input type="hidden" name="commentno"
											value="<%= comment.getCommentNo() %>"> <input
											type="hidden" name="jboardno"
											value="<%= comment.getJboardNo() %>">
										<fieldset>
											<div class="cmt_form">
												<div class="cmt_body">
													<textarea name="content"
														style="resize: none; width: 100%; min-height: 100px; max-height: 100px;"
														onfocus="this.value='';"><%= comment.getCommentContent() %></textarea>
													<div class="cmt_ok">
														<input type="submit" value="수정">
													</div>
												</div>
											</div>
										</fieldset>
									</form>
								</div> <%} %>
								<div style="display: none" class="Subcmt" id="commentReply">
									<form action="/anavada/jbcrinsert.ss" method="post">
										<input type="hidden" name="commentno"
											value="<%=comment.getCommentNo() %>"> <input
											type="hidden" name="jboardno"
											value="<%=jboard.getJboardNo() %>"> <input
											type="hidden" name="writer" value="<%=jboard.getMemberId()%>">
										<fieldset>
											<div class="cmt_form">
												<div class="cmt_body">
													<textarea name="commentcontent"
														style="resize: none; width: 100%; min-height: 100px; max-height: 100px;"
														onfocus="this.value='';">비방글은 작성하실 수 없습니다.</textarea>
													<div class="cmt_ok">
														<input type="submit" value="등록">
													</div>
												</div>
											</div>
										</fieldset>
									</form>
								</div>
							</li>

						</ul>
						<%}} %>
					</div>
				</div>
				<!-- 상세 끝 -->
				<!-- 컨텐츠 끝 -->


			</div>
			<%@ include file="../include/admin_footer.jsp"%>
		</div>
	</div>
</body>
</html>