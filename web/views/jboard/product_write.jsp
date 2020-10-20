<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "jboard.model.vo.Jboard" %>
<%
	Jboard jboard = (Jboard)request.getAttribute("jboard");

%>
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
            <div class="visual-sub-vagas product-vagas">
                <div class="vsv-copy sub-title">
                   <div>
                        <ul class="navi">
                            <li><a href="/anavada/views/main/main.jsp">홈</a></li>
                            <li class="glyphicon glyphicon-menu-right"><a href="/anavada/jblist">중고거래</a></li>
                        </ul>
                    </div>
                    <h2><span>중고거래</span></h2>
                    <h3>우리의 이웃과 중고거래를 통해 변화되는 세상</h3>
                </div>
            </div>
            <!--서브 비주얼/타이틀 끝-->

            <!-- 글쓰기 -->
            <div class="write-area">
                <h2>중고거래 작성</h2>
                <form action="/anavada/jbinsert" method="post" enctype="multipart/form-data">
                <input type="hidden" name="memberid"  value="<%=loginMember.getMemberId()%>">
                
                    <table>
                        <colgroup>
                            <col width="20%">
                            <col width="80%">
                        </colgroup>
                        <tbody>
                            <tr>
                                <td>거래방법</td>
                                <td>
                                    <label><input type="checkbox" name="meet" id="ck1" title="직거래" value="Y" class="no[]" > 직거래</label>
                                    <label><input type="checkbox" name="post" id="ck2" title="우편거래" value="Y" class="no[]" > 우편거래</label>
                                </td>
                            </tr>
                            <tr>
                                <td>지역선택</td>
                                <td>
                                    <select name="local" class="LocationSelect" required="required">
                                    <option value=""  >지역선택</option>
                                    <option value="1" ${param.local eq"1"?"selected" :"" }>강남구</option>
                                    <option value="2" ${param.local eq"2"?"selected" :"" }>강동구</option>
                                    <option value="3" ${param.local eq"3"?"selected" :"" }>강북구</option>
                                    <option value="4" ${param.local eq"4"?"selected" :"" }>강서구</option>
                                    <option value="5" ${param.local eq"5"?"selected" :"" }>관악구</option>
                                    <option value="6" ${param.local eq"6"?"selected" :"" }>광진구</option>
                                    <option value="7" ${param.local eq"7"?"selected" :"" }>구로구</option>
                                    <option value="8" ${param.local eq"8"?"selected" :"" }>금천구</option>
                                    <option value="9" ${param.local eq"9"?"selected" :"" }>노원구</option>
                                    <option value="10" ${param.local eq"10"?"selected" :"" }>도봉구</option>
                                    <option value="11" ${param.local eq"11"?"selected" :"" }>동대문구</option>
                                    <option value="12" ${param.local eq"12"?"selected" :"" }>동작구</option>
                                    <option value="13" ${param.local eq"13"?"selected" :"" }>마포구</option>
                                    <option value="14" ${param.local eq"14"?"selected" :"" }>서대문구</option>
                                    <option value="15" ${param.local eq"15"?"selected" :"" }>서초구</option>
                                    <option value="16" ${param.local eq"16"?"selected" :"" }>성동구</option>
                                    <option value="17" ${param.local eq"17"?"selected" :"" }>성북구</option>
                                    <option value="18" ${param.local eq"18"?"selected" :"" }>송파구</option>
                                    <option value="19" ${param.local eq"19"?"selected" :"" }>양천구</option>
                                    <option value="20" ${param.local eq"20"?"selected" :"" }>영등포구</option>
                                    <option value="21" ${param.local eq"21"?"selected" :"" }>용산구</option>
                                    <option value="22" ${param.local eq"22"?"selected" :"" }>은평구</option>
                                    <option value="23" ${param.local eq"23"?"selected" :"" }>종로구</option>
                                    <option value="24" ${param.local eq"24"?"selected" :"" }>중구</option>
                                    <option value="25" ${param.local eq"25"?"selected" :"" }>중랑구</option>
                                </select>
                                </td>
                            </tr>
                            <tr>
                                <td>상품 이미지</td>
                                <td>
                                    <input type="file" name="ofile1" required="required" style="margin-bottom:10px;" accept="image/gif,image/jpeg,image/png"/>
                                    <input type="file" name="ofile2" style="margin-bottom:10px;"accept="image/gif,image/jpeg,image/png"/>
                                    <input type="file" name="ofile3" style="margin-bottom:10px;"accept="image/gif,image/jpeg,image/png"/>
                                    <input type="file" name="ofile4" style="margin-bottom:10px;"accept="image/gif,image/jpeg,image/png"/>
                                    <p>대표 이미지는 450x450 사이즈로 올려주세요.</p>
                                
                                </td>
                            </tr>
                            <tr>
                                <td>제목</td>
                                <td><input type="text" name="title" class="form-control w100p"  minlength="8"   maxlength="20" placeholder="제목" /></td>
                            </tr>
                            <tr>
                                <td>판매희망가격</td>
                                <td><input type="text" name="price"class="form-control w50p" minlength="3"  placeholder="판매가" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" style="float:left; margin-right: 20px;"/> 원</td>
                            </tr>
                            <tr>
                                <td>상품설명</td>
                                <td>
										<textarea name="content" class="form-control"  minlength="15"  
										style="resize: none; width: 100%; min-height: 600px; max-height: 600px;">
상품 설명을 상세하게 적어주세요.
설명되지 않은 하자나 문제 발생시 책임은 판매자에게 있습니다.
※ 사용자 정보 (이메일, 전화번호)
※ 구매정보 (구매일시, 구매시 가격)
※ 상품정보 (사이즈, 색상, 브랜드 등)
※상품 사용감 (스크래치, 고장 수리 여부등)</textarea>
								</td>
                            </tr>
                        </tbody>
                    </table>

                    <div class="write-btn">
                    
                        <input type= "button" class="btn btn-warning" value="등록취소" onclick="javascript:history.go(-1); return false;">
                        <input type="submit" class="btn btn-success" value="상품등록">
                        
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