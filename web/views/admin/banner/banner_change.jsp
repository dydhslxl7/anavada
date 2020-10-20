<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../include/admin_head.jsp" %> 
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
    <div id="wrap">
        <%@ include file="../include/admin_header.jsp" %> 

        <div id="admin_container">
    
            <!-- 상단 타이틀 -->
            <div class="admin_title">
                <div class="admin_path">
                    <h3>배너관리</h3>
                    <h2>등록</h2>
                </div>
            </div>
            <!-- //상단 타이틀 -->

            <!-- 본문내용 ---------------------------------------------------------------------------------------------------------------------------------------------------->
            <div class="admin_content">

                <!-- 글쓰기 -->
                <form action="/anavada/binsert.ad"  method="post" enctype="multipart/form-data" class="form-inline" onesubmit="return validate();">
                    <fieldset>
                        <div class="write-area">

                            <h2>배너 등록</h2>

                            <table>
                                <colgroup>
                                    <col width="20%">
                                    <col width="80%">
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <td>제목</td>
                                        <td><input type="text" name="title" title="" class="form-control w100p" placeholder="제목" />
                                        </td>
                                    </tr>
                                    <!-- <tr>
                                        <td>게시한배너H/W</td>
                                        <td><input type="text" name="controller" title="" class="form-control w100p" placeholder="H/W" />
                                        	<label><input type="radio" name="controller" id="숨기기" value="H">숨기기</label>
                                    	    <label><input type="radio" name="controller" id="보이기"  value="W">보이기</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>게시배너체크(Y/N)</td>
                                        <td><input type="text" name="check" title="" class="form-control w100p" placeholder="체크하기" />
                                            <label><input type="radio" name="check" id="게시완료" value="Y">게시</label>
                                    	    <label><input type="radio" name="check" id="게시미완료"  value="N">미게시</label>
                                        </td>
                                    </tr> -->
                                    <tr>
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
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </fieldset>
                
                <!-- //글쓰기 -->

                <!-- 버튼 -->
                <div class="write-btn">
                    <a href="javascript:history.go(-1);" class="btn btn-cancel">작성취소</a>
                    <a href="/anavada/blist.ad" class="btn btn-list" >목록</a>
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