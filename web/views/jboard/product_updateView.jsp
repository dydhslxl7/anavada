<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jboard.model.vo.Jboard, java.util.ArrayList, java.sql.Date" %>
<%
	Jboard jboard = (Jboard)request.getAttribute("jboardno");
	int currentPage = ((Integer)request.getAttribute("page")).intValue();
	String post = String.valueOf(request.getAttribute("post"));	
	String meet =String.valueOf(request.getAttribute("meet"));
	String local = String.valueOf(request.getAttribute("local"));
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
                <h2>중고거래 수정</h2>
                <form action="/anavada/jbupdate" method="post" enctype="multipart/form-data">
                <input type= "hidden" name="memberid"  value="<%=loginMember.getMemberId()%>">
                <input type= "hidden" name="jboardno" value = "<%=jboard.getJboardNo() %>">
                <input type= "hidden" name= "page" value = "<%= currentPage %>">
                <input type= "hidden" name= "ofile1" value="<%=jboard.getJboardOrignalFilePath1() %>">
                <input type= "hidden" name= "rfile1" value="<%=jboard.getJboardRenameFilePath1() %>">
                <input type= "hidden" name= "ofile2" value="<%=jboard.getJboardOrignalFilePath2() %>">
                <input type= "hidden" name= "rfile2" value="<%=jboard.getJboardRenameFilePath2() %>">
                <input type= "hidden" name= "ofile3" value="<%=jboard.getJboardOrignalFilePath3() %>">
                <input type= "hidden" name= "rfile3" value="<%=jboard.getJboardRenameFilePath3() %>">
                <input type= "hidden" name= "ofile4" value="<%=jboard.getJboardOrignalFilePath4() %>">
                <input type= "hidden" name= "rfile4" value="<%=jboard.getJboardRenameFilePath4() %>">
                <input type= "hidden" name= "memberip" value= "<%=jboard.getMemberIp() %>">
                
                    <table>
                        <colgroup>
                            <col width="20%">
                            <col width="80%">
                        </colgroup>
                        <tbody>
                            <tr>
                                <td>거래방법</td>
                                <td>
                                    <label><input type="checkbox" name="meet" id="ck1" title="직거래" value="Y"  <% if (meet.equals("Y"))  { %>checked<% } %>>직거래</label>
                                    <label><input type="checkbox" name="post" id="ck2" title="우편거래" value="Y" <% if (post.equals("Y"))  { %>checked<% } %>> 우편거래</label>
                                </td>
                            </tr>
                            <tr>
                                <td>지역선택</td>
                                <td>
                                  <select name="local" class="LocationSelect" required="required">
                              <option selected="selected" value="">지역선택</option>
                              <option value="1" <% if (local.equals("1"))  { %>selected="selected"<% } %>>강남구</option>
                              <option value="2" <% if (local.equals("2"))  { %>selected="selected"<% } %>>강동구</option>
                              <option value="3" <% if (local.equals("3"))  { %>selected="selected"<% } %>>강북구</option>
                              <option value="4" <% if (local.equals("4"))  { %>selected="selected"<% } %>>강서구</option>
                              <option value="5" <% if (local.equals("5"))  { %>selected="selected"<% } %>>관악구</option>
                              <option value="6" <% if (local.equals("6"))  { %>selected="selected"<% } %>>광진구</option>
                              <option value="7" <% if (local.equals("7"))  { %>selected="selected"<% } %>>구로구</option>
                              <option value="8" <% if (local.equals("8"))  { %>selected="selected"<% } %>>금천구</option>
                              <option value="9" <% if (local.equals("9"))  { %>selected="selected"<% } %>>노원구</option>
                              <option value="10"<% if (local.equals("10")) { %>selected="selected"<% } %>>도봉구</option>
                              <option value="11"<% if (local.equals("11")) { %>selected="selected"<% } %>>동대문구</option>
                              <option value="12"<% if (local.equals("12")) { %>selected="selected"<% } %>>동작구</option>
                              <option value="13"<% if (local.equals("13")) { %>selected="selected"<% } %>>마포구</option>
                              <option value="14"<% if (local.equals("14")) { %>selected="selected"<% } %>>서대문구</option>
                              <option value="15"<% if (local.equals("15")) { %>selected="selected"<% } %>>서초구</option>
                              <option value="16"<% if (local.equals("16")) { %>selected="selected"<% } %>>성동구</option>
                              <option value="17"<% if (local.equals("17")) { %>selected="selected"<% } %>>성북구</option>
                              <option value="18"<% if (local.equals("18")) { %>selected="selected"<% } %>>송파구</option>
                              <option value="19"<% if (local.equals("19")) { %>selected="selected"<% } %>>양천구</option>
                              <option value="20"<% if (local.equals("20")) { %>selected="selected"<% } %>>영등포구</option>
                              <option value="21"<% if (local.equals("21")) { %>selected="selected"<% } %>>용산구</option>
                              <option value="22"<% if (local.equals("22")) { %>selected="selected"<% } %>>은평구</option>
                              <option value="23"<% if (local.equals("23")) { %>selected="selected"<% } %>>종로구</option>
                              <option value="24"<% if (local.equals("24")) { %>selected="selected"<% } %>>중구</option>
                              <option value="25"<% if (local.equals("25")) { %>selected="selected"<% } %>>중랑구</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>상품 이미지</td>
                                <td>
                                    <% if (jboard.getJboardOrignalFilePath1() != null && !jboard.getJboardOrignalFilePath1().equals("null")){ %>
                                    		<%= jboard.getJboardOrignalFilePath1() %> &nbsp;
                                    		<input type = "checkbox" name= "delflag1" value="yes" > 1번째파일삭제
                                    		<br>
                                    		<%} %>
                                    <input type="file" name="upfile1" style="margin-bottom:10px;" required="required" accept="image/gif,image/jpeg,image/png"/>
                                    <% if (jboard.getJboardOrignalFilePath2() != null && !jboard.getJboardOrignalFilePath2().equals("null")){ %>
                                    		<%= jboard.getJboardOrignalFilePath2() %> &nbsp;
                                    		<input type = "checkbox" name= "delflag2" value="yes"> 2번째파일삭제
                                    		<br>
                                    		<%} %>
                                    <input type="file" name="upfile2" style="margin-bottom:10px;"accept="image/gif,image/jpeg,image/png"/>
                                    <% if (jboard.getJboardOrignalFilePath3() != null && !jboard.getJboardOrignalFilePath3().equals("null")){ %>
                                    		<%= jboard.getJboardOrignalFilePath3() %> &nbsp;
                                    		<input type = "checkbox" name= "delflag3" value="yes"> 3번째파일삭제
                                    		<br>
                                    		<%} %>
                                    <input type="file" name="upfile3" style="margin-bottom:10px;"accept="image/gif,image/jpeg,image/png"/>
                                    <% if (jboard.getJboardOrignalFilePath4() != null && !jboard.getJboardOrignalFilePath4().equals("null")){ %>
                                    		<%= jboard.getJboardOrignalFilePath4() %> &nbsp;
                                    		<input type = "checkbox" name= "delflag4" value="yes"> 4번째파일삭제
                                    		<br>
                                    		<%} %>
                                    <input type="file" name="upfile4" style="margin-bottom:10px;"accept="image/gif,image/jpeg,image/png"/>
                                    <p>대표 이미지는 첫번째로 올려주세요</p>
                                
                                </td>
                            </tr>
                            <tr>
                                <td>제목</td>
                                <td><input type="text" name="title" class="form-control w100p"   minlength="8"   maxlength="20" placeholder="제목" value= <%=jboard.getJboardTitle() %>></td>
                            </tr>
                            <tr>
                                <td>판매희망가격</td>
                                <td><input type="text" name="price"class="form-control w50p" minlength="3"  placeholder="판매가"  onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"
                                		value ="<%=jboard.getJboardPrice() %>" style="float:left; margin-right: 20px;"/> 원</td>
                            </tr>
                            <tr>
                                <td>상품설명</td>
                                <td>
										<textarea name="content" class="form-control" minlength="20" 
										style="resize: none; width: 100%; min-height: 600px; max-height: 600px;"><%=jboard.getJboardContent() %></textarea>
								</td>
                            </tr>
                        </tbody>
                    </table>

                    <div class="write-btn">
                    
                        <input type= "button" class="btn btn-warning" value="등록취소" onclick="javascript:history.go(-1); return false;">
                        <input type="submit" class="btn btn-success" value="상품수정">
                        
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