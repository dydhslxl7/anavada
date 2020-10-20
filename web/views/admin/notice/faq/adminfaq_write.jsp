<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../../../include/head.jsp" %> 
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
    <div id="wrap">
       <%@ include file="../../../include/header.jsp" %>

        <!-- 컨텐츠 -->
        <div id="content">

            <!--서브 비주얼/타이틀-->
            <div class="visual-sub-vagas notice-vagas">
                <div class="vsv-copy sub-title">
                   <div>
                        <ul class="navi">
                            <li><a href="#none">홈</a></li>
                            <li class="glyphicon glyphicon-menu-right"><a href="#none">고객센터</a></li>
                            <li class="glyphicon glyphicon-menu-right"><a href="#none">문의하기</a></li>
                        </ul>
                    </div>
                    <h2><span>문의하기</span></h2>
                    <h3>관리자에게 문의할 수 있는 공간입니다.</h3>
                </div>
            </div>
            <!--서브 비주얼/타이틀 끝-->


            <!-- 글쓰기 -->
            <div class="write-area">
                <h2>관리자용 FAQ 작성</h2>
                <form action="/anavada/afinsert.ss" method="post">
                    <table>
                        <colgroup>
                            <col width="20%">
                            <col width="80%">
                        </colgroup>
                        <tbody>
                            <tr>
                                <td>카테고리</td>
                                <td>
                                    <label><input type="radio" name="checkCate" value="1" required> 회원정보</label>
                                    <label><input type="radio" name="checkCate" value="2"> 중고거래</label>
                                    <label><input type="radio" name="checkCate" value="3"> 커뮤니티</label>
                                    <label><input type="radio" name="checkCate" value="4"> 지역축제</label>
                                </td>
                            </tr>
                            <tr>
                                <td>제목</td>
                                <td><input type="text" name="title" title="" class="form-control w100p" placeholder="제목" required></td>
                            </tr>
                            <tr>
                                <td>내용</td>
                                <td><textarea name="content"  class="form-control" style="resize: none; width:100%; min-height:300px; max-height:300px;" required></textarea></td>
                            </tr>

                        </tbody>
                    </table>

                    <div class="write-btn">
                    	<input type="reset" class="btn btn-list" value="작성취소">
                        <a onclick="javascipt:history.go(-1); return false;" class="btn btn-list">목록</a>
                        <input type="submit" class="btn btn-success" value="작성하기">
                    </div>
                </form>
            </div>
            <!-- 글쓰기 끝 -->

        </div>
        <!-- 컨텐츠 끝 -->

        <%@ include file="../../../include/footer.jsp" %>
    </div>
</body>
</html>