<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../include/head.jsp" %>

    <script type="text/javascript">
        $(function(){
            var qna = $(".qna_list .question");
            var i;
            for (i=0; i < qna.length; i++){
                qna.eq(i).click(function(){
                    qna.removeClass("active");
                    $(this).toggleClass("active");
                    qna.next().css("display","none");
                    $(this).next(".ctn").slideToggle(300);
                })            
            }
        });
    </script>
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
                            <li class="glyphicon glyphicon-menu-right"><a href="/anavada/flist">FAQ</a></li>
                        </ul>
                    </div>
                    <h2><span>FAQ</span></h2>
                    <h3>자주하는 질문/답변 공간입니다.</h3>
                </div>
            </div>
            <!--서브 비주얼/타이틀 끝-->

            <!-- 글쓰기 -->
            <div class="write-area">
                <h2>질문/답변 작성</h2>
                <form action="" method="">
                    <table>
                        <colgroup>
                            <col width="20%">
                            <col width="80%">
                        </colgroup>
                        <tbody>
                            <tr>
                                <td>분류</td>
                                <td>
                                    <label><input type="radio" name="FAQ_check"title="회원정보">회원정보</label>
                                    <label><input type="radio" name="FAQ_check"title="중고거래">중고거래</label>
                                    <label><input type="radio" name="FAQ_check"title="커뮤니티">커뮤니티</label>
                                    <label><input type="radio" name="FAQ_check"title="지역축제">지역축제</label>
                                </td>
                            </tr>
                            <tr>
                                <td>질문</td>
                                <td><input type="text" name="" title="" class="form-control w100p" placeholder="제목" /></td>
                            </tr>
                            <tr>
                                <td>답변</td>
                                <td><textarea name="" rows="" cols="" class="form-control" style="resize: none; width:100%; min-height:300px; max-height:300px;"></textarea></td>
                            </tr>
                        </tbody>
                    </table>

                    <div class="write-btn">
                        <a href="faq_list.jsp" class="btn btn-list">목록</a>
                        <a href="#none" class="btn btn-success">작성하기</a>
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