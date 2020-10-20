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
                            <li class="glyphicon glyphicon-menu-right"><a href="/anavada/nlist">고객센터</a></li>
                            <li class="glyphicon glyphicon-menu-right"><a href="#none">신고하기</a></li>
                        </ul>
                    </div>
                    <h2><span>신고하기</span></h2>
                    <h3>관리자에게 신고할 수 있는 공간입니다.</h3>
                </div>
            </div>
            <!--서브 비주얼/타이틀 끝-->

            <!-- 글쓰기 -->
            <div class="write-area">
                <form action="/anavada/dboinsert" method="post" enctype="multipart/form-data">
                    <h2>신고하기 작성</h2>
                    <table>
                        <colgroup>
                            <col width="20%">
                            <col width="80%">
                        </colgroup>
                        <tbody>
                            <tr>
                                <td>카테고리</td>
                                <td>
                                    <label><input type="radio" name="declare" id="중고거래 신고" value="중고거래 신고"">중고거래신고하기</label>
                                     <label><input type="radio" name="declare" id="커뮤니티 신고"  value="커뮤니티 신고">커뮤니티신고하기</label>
                                    <!--label><input type="radio" name="ctgr" id="" title="신고">신고</label-->
                                </td>
                            </tr>
                            <tr>
                                <td>제목</td>
                                <td><input type="text" name="title" title="" class="form-control w100p" placeholder="제목" /></td>
                            </tr>
                            <tr>
                                <td>작성자</td>
                                <td><input type="text" name="writer" <%-- readonly="<%= loginMember.getMemberId() %>"  --%>class="form-control w100p" /></td>
                            </tr>
                            <tr>
                                <td>신고회원 아이디</td>
                                <td><input type="text" name="bId" title="" class="form-control w100p" placeholder="신고회원 아이디" /></td>
                            </tr>
                            <tr>
                                <td>내용</td>
                                <td><textarea name="content" rows="" cols="" class="form-control" style="resize: none; width:100%; min-height:300px; max-height:300px;"></textarea></td>
                            </tr>
                            <tr>
                                <td>url</td>
                                <td><input type="text" name="url"  class="form-control w100p" placeholder="url : 신고하실 URL(링크)를 넣어주세요" /></td>
                            </tr>
                            <tr>
                                <td>첨부파일</td>
                                <td><input type="file" name="ofile"></td>
                            </tr>
                        </tbody>
                    </table>

                    <div class="write-btn">
                        <!-- <a href="declare_list.jsp" class="btn btn-list">목록</a> -->
                        <!-- <input type="reset" value="작성취소"> &nbsp; -->
                        <a href="javascript:history.go(-1);">작성취소</a>
                        <input type="submit" class="btn btn-success" value="신고등록하기">
                        <!-- <a href="/anavada/">신고 등록하기</a> -->
                        
                         <%  %>
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