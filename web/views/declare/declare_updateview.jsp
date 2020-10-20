<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="declare.model.vo.DBo"%>
<% 
		DBo dbo = (DBo)request.getAttribute("dbo");
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
            <div class="visual-sub-vagas notice-vagas">
                <div class="vsv-copy sub-title">
                   <div>
                        <ul class="navi">
                        <li><a href="/anavada">홈</a></li>
                            <li class="glyphicon glyphicon-menu-right"><a href="/anavada/nlist">고객센터</a></li>
                            <li class="glyphicon glyphicon-menu-right"><a href="#none">신고하기</a></li>
                        </ul>
                    </div>
                    <h2><span>신고하기</span></h2>
                    <h3>관리자에게 신고할 수 있는 공간입니다.</h3>
                </div>
            </div>
            <!--서브 비주얼/타이틀 끝-->

            <!-- 상태수정page -->
            <div class="view-area">
				<form action="/anavada/dboupdate.ad" method="post"
					onesubmit="return validate();" >
					<input type="hidden" name="no" value=<%= dbo.getDboNo() %>>
					<h3 class="declare_btn2">
						<span> <i class="glyphicon glyphicon-bell"></i>
						<td>
								<%if(dbo.getDboChe() != null ){%>								
								<input type="radio" name="che" value="Y" >처리완료 
								<input type="radio" name="che" value="N" >처리미완료
								<% }else{ %>&nbsp; <% } %>
						</td></span>
					</h3>
					<h2>
						<span class="declare">신고</span>
						<td><%= dbo.getDboTitle() %></td>
					</h2>
					<ul>
					    <li><span>글번호 : </span>
						<td><%= dbo.getDboNo() %></td>
						<li><span>작성자 : </span>
						<td><%= dbo.getDboMid() %></td></li>
						<li><span>등록일 : </span>
						<td><%= dbo.getDboDate() %></td></li>
						<li><span>첨부파일 : </span>
						<td>
								<% if( dbo.getDboOriginal() != null ){ %> <a
								href="/anavada/dbofdown?ofile=<%= dbo.getDboOriginal() %>&rfile=<%= dbo.getDboRename()%>"><%= dbo.getDboOriginal() %></a>
								<%}else{ %> &nbsp; <% } %>
						</td>
						<br />
						<br />
						<li><span>링크 : </span>
						<td><%= dbo.getDboUrl() %></td></li>
					</ul>

					<div class="view-ctn">
						<li><span>내용 : </span>
						<td><%= dbo.getDboContent() %></td></li>
					</div>

					<div class="view-btn">
						<!-- <a href="#none" class="btn btn-prev">이전글</a> -->
						<input type="submit" value="수정하기"> &nbsp; 
						<input type="reset" value="수정취소"> &nbsp; 
						<a href="/anavada/dbolist" class="btn btn-list">목록</a>
						<!-- <a href="#none" class="btn btn-next">다음글</a> -->
					</div>
				</form>
			</div>
            <!-- 상태수정page 끝 -->

        </div>
        <!-- 컨텐츠 끝 -->

        <%@ include file="../include/footer.jsp" %>
    </div>
</body>
</html>