<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@  page import="jboard.model.vo.Jboard , java.util.ArrayList, java.sql.Date, java.awt.Image"%>
 <%
 	ArrayList<Jboard> list = (ArrayList<Jboard>) request.getAttribute("list");
   int listCount = ((Integer) request.getAttribute("listCount")).intValue();
   int startPage = ((Integer) request.getAttribute("startPage")).intValue();
   int endPage = ((Integer) request.getAttribute("endPage")).intValue();
   int maxPage = ((Integer) request.getAttribute("maxPage")).intValue();
   int currentPage = ((Integer) request.getAttribute("page")).intValue();
   	String titleSearch = request.getParameter("titlesearch");
	String local = request.getParameter("local"); 
    String listSearch = request.getParameter("listsearch");
	String[] localArr = { "강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구" };
%>


<!DOCTYPE html>
<html>
<head>
    <%@ include file="../include/head.jsp" %> 
    <script type="text/javascript">
   function showWriteForm() {
      location.href = "/anavada/views/jboard/product_write.jsp";
   }
</script>

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

            <!--중고거래-->
            <div class="product_list">

                <!--종류 리스트-->
                <div class="sort-area">  
                    <h4>전체 <%=listCount%>개</h4>
                     <% if (loginMember != null){ %>
                    <button onclick="showWriteForm();" class="write_btn">글쓰기</button>
                	 <% }else{ %>
                    <button onclick="alert('로그인 후 이용해주세요');location.href='/anavada/views/member/login.jsp';" class="write_btn">글쓰기</button>
                    <%} %>
                    <div>
                        <form action="/anavada/jblist" method="post" id="sel" name="form1">
                        <!-- 셀렉트바 -->
                            지역선택 : <select name="local" class="LocationSelect"   onchange=this.form.submit()>
                            	
                                    <option value="0"  ${param.local eq"0"?"selected" :"" }>전체보기</option>
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
                            목록 분류 : <select name="listsearch" class="ListSelect" onchange=this.form.submit()>
                                    <option value="latestposts" ${param.listsearch eq"latestposts"?"selected" :"" }>최신등록순</option>
                                    <option value="highprice"${param.listsearch eq"highprice"?"selected" :"" }>가격높은순</option>
                                    <option value="lowprice"${param.listsearch eq"lowprice"?"selected" :"" }>가격낮은순</option>
                                    <option value="highlike" ${param.listsearch eq"highlike"?"selected" :"" }>좋아요순</option>
                            </select>
                            제목 검색 :
                            <input type="text" name="titlesearch" maxlength="16" minlength="2" placeholder="검색어를 입력해주세요.">
                            <button class="top-search"><i class="xi-search"></i></button>
                        </form>
                    </div>
                </div>
<!-- 검색 폼 끝 -->
<!-- 게시물 출력 -->
                <ul class="product">
                <% for (Jboard jboard : list ){ %>
                    <li onclick="location.href='/anavada/jbdetail?jboardno=<%= jboard.getJboardNo() %>&page=<%=currentPage%>'">
                    <% if (jboard.getJboardRenameFilePath1() !=null){%>
                        <div><img src="/anavada/resources/jboardfiles/<%=jboard.getJboardRenameFilePath1()%>"/></div>
                        <%}else{ %>
                        <div><img src="/anavada/resources/jboardfiles/test.jpg"/></div>
                        <%}  %>
                        <h2><%= jboard.getJboardTitle()%></h2>
                        <h3><%=jboard.getJboardPrice() %><span> 원 </span></h3>
                       
                        <p><i class="good_i glyphicon glyphicon-heart-empty">좋아요<span><%=jboard.getJboardLike() %></span></i>
                        <span></span>
                        </p>
                    </li>
                    <%} %>
                    <%if (listCount < 1) {%>
                    <div class="list-no">
					<p><img src="/anavada/resources/images/btnIcn/icn_big_listNo.png" alt="" title="" /></p>
					<h1>목록이 없습니다.</h1>
					</div>
					<%}%>
				</div>
                <% if (loginMember != null){ %>
                <div class="write-btn">
                    <a href="views/jboard/product_write.jsp?memberid=<%=loginMember.getMemberId()%>" class="write_btn">글쓰기</a>
                  </div>
                 <% }else{ %>
                  <div class="write-btn">
                    <a onclick="alert('로그인 후 이용해주세요');location.href='/anavada/views/member/login.jsp';" class="write_btn">글쓰기</a>
                    </div>
                 <%} %>
        

            </div>
            <!--  페이징 처리 -->
            <dl class="list-paging pb80">
                <dd>
                	<% if (currentPage < 1){ %>
              		<a><i class= "glyphicon glyphicon-backward"></i></a>
              		<%}else {%>
              		<a href="/anavada/jblist?page=1&local=<%=local%>&listsearch=<%=listSearch%>&titlesearch=<%=titleSearch%>"><i class= "glyphicon glyphicon-backward"></i></a>
              		<%} %>
              		<% if (startPage -1 >=10 ){ %>
                    <a href="/anavada/jblist?page=<%= startPage- 10%>&local=<%=local%>&listsearch=<%=listSearch%>&titlesearch=<%=titleSearch%>"><i class="glyphicon glyphicon-menu-left"></i></a>
					<%} %>
					<!-- 현재 페이지가 속한 페이지 그룹의 숫자 출력처리 -->
					<% for (int p = startPage; p <= endPage; p++){
						if (p == currentPage){%>
                    <a href="#none" class="active"><%=p %></a>
                    <%} else {%>
                    <a href="/anavada/jblist?page=<%=p%>&local=<%=local%>&listsearch=<%=listSearch%>&titlesearch=<%=titleSearch%>"><%=p %></a>
                    <%}} %>
                    <!--  다음 그룹으로 이동처리 -->
                    <% if (endPage +1 <= maxPage){ %>
                    <a href="/anavada/jblist?page=<%= endPage +1%>&local=<%=local%>&listsearch=<%=listSearch%>&titlesearch=<%=titleSearch%>"><i class="glyphicon glyphicon-menu-right"></i></a>
                    <%} %>
                    
                    <% if (currentPage >= maxPage){ %>
                    <a><i class="glyphicon glyphicon-forward"></i></a>
                    <%}else{ %>
                    <a href="/anavada/jblist?page=<%=maxPage%>&local=<%=local%>&listsearch=<%=listSearch%>&titlesearch=<%=titleSearch%>"><i class="glyphicon glyphicon-forward"></i> </a>
                    <%} %>
                </dd>
            </dl>
            <!-- 페이지넘버 끝 -->

        </div>
        <!-- 컨텐츠 끝 -->

        <%@ include file="../include/footer.jsp" %>
    </div>
</body>
</html>