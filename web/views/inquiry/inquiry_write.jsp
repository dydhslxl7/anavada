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
            <div class="visual-sub-vagas notice-vagas">
                <div class="vsv-copy sub-title">
                   <div>
                        <ul class="navi">
                            <li><a href="/anavada">홈</a></li>
                            <li><a href="/anavada/nlist">고객센터</a></li>
                            <li class="glyphicon glyphicon-menu-right"><a href="/anavada/ilist">문의하기</a></li>
                        </ul>
                    </div>
                    <h2><span>문의하기</span></h2>
                    <h3>관리자에게 문의할 수 있는 공간입니다.</h3>
                </div>
            </div>
            <!--서브 비주얼/타이틀 끝-->

            <!-- 글쓰기 -->
            <div class="write-area">
                <form action="/anavada/iinsert.ss" method="post" enctype="multipart/form-data">
				<input type="hidden" value="<%= loginMember.getMemberId() %>" name="id">
				<input type="hidden" value="no" name="my">
                    <h2>문의하기 작성</h2>

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