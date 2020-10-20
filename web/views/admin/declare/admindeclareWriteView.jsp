<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블랙회원등록</title>
    <%@ include file="../include/admin_head.jsp" %> 
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
    <div id="wrap">
        <%@ include file="../include/admin_header.jsp" %> 

        <div id="admin_container">
    
            <!-- 상단 타이틀 -->
            <div class="admin_title">
                <div class="admin_path">
                    <h3>신고회원</h3>
                    <h2>등록</h2>
                </div>
            </div>
            <!-- //상단 타이틀 -->

            <!-- 본문내용 ---------------------------------------------------------------------------------------------------------------------------------------------------->
            <div class="admin_content">

                <!-- 글쓰기 -->
                <form action="/anavada/dinsert.ad"  method="post" enctype="multipart/form-data" class="form-inline" onesubmit="return validate();">
                    <fieldset>
                        <div class="admin-library-write">

                            <h2>블랙회원 등록</h2>

                            <table>
                                <colgroup>
                                    <col width="20%">
                                    <col width="80%">
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <td>회원아이디</td>
                                        <td><input type="text" name="blackid" title="" class="form-control w100p" placeholder="회원아이디" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>신고당한 횟수</td>
                                        <td>
                                        	<label><input type="radio" name="count" id="숨기기" value="1">처음이면</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>로그인제한 설정(Y/N)</td>
                                        <td><!-- <input type="text" name="check" title="" class="form-control w100p" placeholder="Y/N" /> -->
                                            <label><input type="radio" name="controller" id="제한설정" value="Y">제한설정</label>
                                    	    <label><input type="radio" name="controller" id="제한없음"  value="N">제한없음</label>
                                        </td>
                                    </tr>
                                    <!-- <tr>
                                        <td>설명</td>
                                        <td><textarea name="content" rows="" cols="" class="form-control" style="resize: none; width:100%; min-height:200px; max-height:200px;"></textarea></td>
                                    </tr>
                                    <tr>
                                        <td>배너파일등록</td>
                                        <td><input type="file" name="ofile"></td>
                                    </tr>
                                    <tr>
                                        <td>URL등록</td>
                                        <td><input type="text" name="url" title="" class="form-control w100p" placeholder="http://" /></td>
                                    </tr>
                                    <tr>
                                        <td>배경사이즈</td>
                                       <td><input type="text" name="size" title="" class="form-control w100p" placeholder="사이즈" /></td>
                                    </tr> -->
                                </tbody>
                            </table>
                        </div>
                    </fieldset>
                
                <!-- //글쓰기 -->

                <!-- 버튼 -->
                <div class="write-btn">
                    <a href="javascript:history.go(-1);" class="btn btn-cancel">작성취소</a>
                    <a href="/anavada/dlist.ad" class="btn btn-list" >목록</a>
                    <input type="submit" class="btn btn-success" value="등록">
                </div>
                <!-- //버튼 -->
			</form>
            </div>
        <%@ include file="../include/admin_footer.jsp" %> 
        </div>
    </div>
</body>
</html>