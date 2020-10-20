<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="faq.model.vo.Faq"%>
<%
	Faq	faq = (Faq)request.getAttribute("faq");
	int currentPage = (Integer)request.getAttribute("page");
	
	String[] checked = new String[4];
	switch(faq.getFaqCategory()){
	case 1 : checked[0] = "checked"; break;
	case 2 : checked[1] = "checked"; break;
	case 3 : checked[2] = "checked"; break;
	case 4 : checked[3] = "checked"; break;
	}
%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../../../include/head.jsp" %> 
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
                <h2>FAQ 수정</h2>
                <form action="/anavada/afupfaq" method="post">
				<input type="hidden" value="<%= faq.getFaqNo() %>" name="no">
				<input type="hidden" value="<%= currentPage %>" name="page">
                    <table>
                        <colgroup>
                            <col width="20%">
                            <col width="80%">
                        </colgroup>
                        <tbody>
                            <tr>
                                <td>제목</td>
                                <td><input type="text" name="title" title="" class="form-control w100p" value="<%= faq.getFaqTitle() %>"></td>
                            </tr>
                            <tr>
                                <td>내용</td>
                                <td><textarea name="content" class="form-control" style="resize: none; width:100%; min-height:300px; max-height:300px;"><%= faq.getFaqContent() %></textarea></td>
                            </tr>
                            <tr>
                                <td>카테고리</td>
                                <td>
                                    <label><input type="radio" name="checkCate" value="1" <%= checked[0] %>> 회원정보</label>
                                    <label><input type="radio" name="checkCate" value="2" <%= checked[1] %>> 중고거래</label>
                                    <label><input type="radio" name="checkCate" value="3" <%= checked[2] %>> 커뮤니티</label>
                                    <label><input type="radio" name="checkCate" value="4" <%= checked[3] %>> 지역축제</label>
                                </td>
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

        <%@ include file="../../../include/footer.jsp" %>
</body>
</html>