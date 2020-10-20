<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고자 관리</title>
    <%@ include file="../include/admin_head.jsp" %> 
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
    <div id="wrap">
        <%@ include file="../include/admin_header.jsp" %> 

        <div id="admin_container">

            <!-- 상단 타이틀 -->
            <div class="admin_title">
                <div class="admin_path">
                    <h3>신고관리</h3>
                    <h2>| 목록</h2>
                </div>
            </div>
            <!-- //상단 타이틀 -->

            <!-- 본문내용 -->
            <div class="list-area">


                <!-- 검색영역 -->
                <div class="sort-area">  
                    <h4>전체 150개</h4>
                    <div>
                        <form action="" method="" id="">
                            유형별 분류 : <select name="" class="ListSelect">
                                    <option value="분류 선택" selected="selected">분류 선택</option>
                                    <option value="제목">제목</option>
                                    <option value="내용">내용</option>
                                    <option value="작성자">작성자</option>
                            </select>
                            
                            <input type="text" placeholder="검색어를 입력해주세요.">
                            <button class="top-search"><i class="xi-search"></i></button>
                        </form>
                    </div>
                </div>
                <!-- 검색영역 끝 -->

                <table>
                    <tbody>
                        <tr>
                            <td class="checkBox"><input type="checkbox"></td>
                            <td class="number">6</td>
                            <td class="title">
                                <h2><span>공지</span>'Anavada'의 소식을 빠르게 확인할 수 있는 공간입니다.</h2>
                                <ul>
                                    <li>작성자 : 관리자</li>
                                    <li>작성일 : 2019.02.30</li>
                                    <li>조회수 : 30</li>
                                </ul>
                            </td>
                            <td class="fileDown"><i class="glyphicon glyphicon-floppy-saved"></i></td>
                        </tr>
                        <tr>
                            <td class="checkBox"><input type="checkbox"></td>
                            <td class="number">5</td>
                            <td class="title">
                                <h2><span>공지</span>'Anavada'의 소식을 빠르게 확인할 수 있는 공간입니다.</h2>
                                <ul>
                                    <li>작성자 : 관리자</li>
                                    <li>작성일 : 2019.02.30</li>
                                    <li>조회수 : 30</li>
                                </ul>
                            </td>
                            <td class="fileDown"><i class="glyphicon glyphicon-floppy-saved"></i></td>
                        </tr>
                        <tr>
                            <td class="checkBox"><input type="checkbox"></td>
                            <td class="number">4</td>
                            <td class="title">
                                <h2><span>공지</span>'Anavada'의 소식을 빠르게 확인할 수 있는 공간입니다.</h2>
                                <ul>
                                    <li>작성자 : 관리자</li>
                                    <li>작성일 : 2019.02.30</li>
                                    <li>조회수 : 30</li>
                                </ul>
                            </td>
                            <td class="fileDown"><i class="glyphicon glyphicon-floppy-saved"></i></td>
                        </tr>
                        <tr>
                            <td class="checkBox"><input type="checkbox"></td>
                            <td class="number">3</td>
                            <td class="title">
                                <h2><span>공지</span>'Anavada'의 소식을 빠르게 확인할 수 있는 공간입니다.</h2>
                                <ul>
                                    <li>작성자 : 관리자</li>
                                    <li>작성일 : 2019.02.30</li>
                                    <li>조회수 : 30</li>
                                </ul>
                            </td>
                            <td class="fileDown"><i class="glyphicon glyphicon-floppy-saved"></i></td>
                        </tr>
                        <tr>
                            <td class="checkBox"><input type="checkbox"></td>
                            <td class="number">2</td>
                            <td class="title">
                                <h2><span>공지</span>'Anavada'의 소식을 빠르게 확인할 수 있는 공간입니다.</h2>
                                <ul>
                                    <li>작성자 : 관리자</li>
                                    <li>작성일 : 2019.02.30</li>
                                    <li>조회수 : 30</li>
                                </ul>
                            </td>
                            <td class="fileDown"><i class="glyphicon glyphicon-floppy-saved"></i></td>
                        </tr>
                        <tr>
                            <td class="checkBox"><input type="checkbox"></td>
                            <td class="number">1</td>
                            <td class="title">
                                <h2><span>공지</span>'Anavada'의 소식을 빠르게 확인할 수 있는 공간입니다.</h2>
                                <ul>
                                    <li>작성자 : 관리자</li>
                                    <li>작성일 : 2019.02.30</li>
                                    <li>조회수 : 30</li>
                                </ul>
                            </td>
                            <td class="fileDown"><i class="glyphicon glyphicon-floppy-saved"></i></td>
                        </tr>
                    </tbody>
                </table>

                <p class="warning_text">
                    *삭제한 게시글은 복구가 불가능 하오니 신중하게 선택하시기 바랍니다.
                </p>
                <!-- //게시판 -->

                <!-- 버튼 -->
                <div class="btn_wrap">
                    <a href="#" class="btn-left btn_gray">선택삭제</a>
                    <a href="notice_write.php" class="btn-right btn_white">신고자등록</a>  
                </div>
                <!-- //버튼 -->

                <!-- 페이징 -->
                <dl class="list-paging">
                    <dd>
                        <a href="#none"><i class="glyphicon glyphicon-menu-left"></i></a>
                        <a href="#none" class="active">1</a>
                        <a href="#none">2</a>
                        <a href="#none">3</a><!-- 활성화 class="active" -->
                        <a href="#none">4</a>
                        <a href="#none">5</a>
                        <a href="#none"><i class="glyphicon glyphicon-menu-right"></i></a>
                    </dd>
                </dl>
                <!-- //페이징 -->

            </div>
        <%@ include file="../include/admin_footer.jsp" %> 
        </div>
    </div>
</body>
</html>