<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="notice.model.vo.Notice"%>
<%
	Notice notice = (Notice)request.getAttribute("notice");
	int currentPage = (Integer)request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../../include/head.jsp" %> 
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
    <div id="wrap">
    
 			<!--서브 비주얼/타이틀-->
            <div class="visual-sub-vagas notice-vagas">
                <div class="vsv-copy sub-title">
                   <div>
                        <ul class="navi">
                            <li><a href="#none">홈</a></li>
                            <li class="glyphicon glyphicon-menu-right"><a href="#none">고객센터</a></li>
                            <li class="glyphicon glyphicon-menu-right"><a href="#none">공지사항</a></li>
                        </ul>
                    </div>
                    <h2><span>공지사항</span></h2>
                    <h3>'Anavada'의 소식을 빠르게 확인할 수 있는 공간입니다.</h3>
                </div>
            </div>
            <!--서브 비주얼/타이틀 끝-->
            
            <!-- 글쓰기 -->
            <div class="write-area">
                <h2>공지사항 수정</h2>
                <form action="/anavada/anupnotice" method="post" enctype="multipart/form-data">
				<input type="hidden" value="<%= notice.getNoNo()%>" name="no">
				<input type="hidden" value="<%= currentPage %>" name="page">
				<input type="hidden" value="<%= notice.getNoOriginal() %>" name="ofile">
				<input type="hidden" value="<%= notice.getNoRename() %>" name="rfile">
                    <table>
                        <colgroup>
                            <col width="20%">
                            <col width="80%">
                        </colgroup>
                        <tbody>
                            <tr>
                                <td>제목</td>
                                <td><input type="text" name="title" title="" class="form-control w100p" value="<%= notice.getNoTitle() %>"></td>
                            </tr>
                            <tr>
                                <td>내용</td>
                                <td><textarea name="content" class="form-control" style="resize: none; width:100%; min-height:300px; max-height:300px;"><%= notice.getNoContent() %></textarea></td>
                            </tr>
                            <tr>
                                <td>첨부파일</td>
                                <td>
                                <% if(notice.getNoOriginal() != null) { %>
                                <%= notice.getNoOriginal() %> &nbsp; &nbsp; &nbsp; &nbsp;<input type="checkbox" value="yes" name="delcheck"> 파일삭제
                                <% } %>
                                <input type="file" name="upfile"></td>
                            </tr>
                        </tbody>
                    </table>

                    <div class="write-btn">
                    	<input type="reset" class="btn btn-list" value="수정취소">
                        <a onclick="javascipt:history.go(-1); return false;" class="btn btn-list">목록</a>
                        <input type="submit" class="btn btn-success" value="수정하기">
                    </div>
                </form>
            </div>
            <!-- 글쓰기 끝 -->

        </div>
        <!-- 컨텐츠 끝 -->

        <%@ include file="../../include/footer.jsp" %>
</body>
</html>