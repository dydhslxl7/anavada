<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            <div class="visual-sub-vagas mypage-vagas">
                <div class="vsv-copy sub-title">
                   <div>
                        <ul class="navi">
                            <li><a href="/anavada">홈</a></li>
                            <li><a href="/anavada/mypage.cp?memberId=<%= loginMember.getMemberId() %>">MYPAGE</a></li>
                            <li class="glyphicon glyphicon-menu-right"><a href="/anavada/miq?member=<%= loginMember.getMemberId() %>">문의하기조회</a></li>
                        </ul>
                    </div>
                    <h2><span>MYPAGE</span></h2>
                    <h3>'Anavada'의 내 정보를 확인할 수 있습니다..</h3>
                </div>
            </div>
            <!--서브 비주얼/타이틀 끝-->
			    
            <!--서브 카테고리-->
            <div class="MyTap">
                <ul>
                    <li><a href="/anavada/mypage.cp?memberId=<%= loginMember.getMemberId() %>">정보수정</a></li>
                    <li><a href="/anavada/myjblist?memberid=<%=loginMember.getMemberId()%>">중고거래조회</a></li>
                    <li><a href="/anavada/mycmnt?memberID=<%=loginMember.getMemberId()%>">커뮤니티조회</a></li>
                    <li class="active"><a href="/anavada/miq?member=<%= loginMember.getMemberId() %>">문의하기조회</a></li>
                    <li><a href="/anavada/dbomylist.ss?member=<%= loginMember.getMemberId()%>">신고하기조회</a></li>
                </ul>
            </div>
            <!--서브 카테고리 끝-->

            <!-- 글쓰기 -->
            <div class="write-area">
                <form action="/anavada/iinsert.ss" method="post" enctype="multipart/form-data">
				<input type="hidden" value="<%= loginMember.getMemberId() %>" name="id">
				<input type="hidden" value="ok" name="my">
                    <h2>나의 문의하기 작성</h2>

                    <table>
                        <colgroup>
                            <col width="20%">
                            <col width="80%">
                        </colgroup>
                        <tbody>
                            <tr>
                                <td>카테고리</td>
                                <td>
                                    <label><input type="radio" name="type" value="member" required>회원정보</label>
                                    <label><input type="radio" name="type" value="error">오류</label>
                                    <label><input type="radio" name="type" value="proposal">제안하기</label>
                                    <label><input type="radio" name="type" value="etc">기타</label>
                                </td>
                            </tr>
                            <tr>
                                <td>제목</td>
                                <td><input type="text" name="title" class="form-control w100p" placeholder="제목" required></td>
                            </tr>
                            <tr>
                                <td>내용</td>
                                <td><textarea name="content" class="form-control" style="resize: none; width:100%; min-height:300px; max-height:300px;" required></textarea></td>
                            </tr>
                            <tr>
                                <td>첨부파일</td>
                                <td>
                                    <input type="file" name="ofile1" class="mb5">
                                    <input type="file" name="ofile2" class="mb5">
                                    <input type="file" name="ofile3">
                                </td>
                            </tr>
                        </tbody>
                    </table>

                    <div class="write-btn">
                        <a href="javascript:history.go(-1);" class="btn btn-list">목록</a>
                        <input type="submit" class="btn btn-success" value="작성하기" style="width:100px;">
                    </div>
                </form>
            </div>
            <!-- 글쓰기 끝 -->

        </div>
        <!-- 컨텐츠 끝 -->

        <%@ include file="../include/footer.jsp" %>
    </div>
</body>
</html>