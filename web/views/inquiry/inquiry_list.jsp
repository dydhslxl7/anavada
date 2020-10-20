<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, inquiry.model.vo.Inquiry"%>
<%
   ArrayList<Inquiry> list = (ArrayList<Inquiry>)request.getAttribute("list");
   int currentPage = (Integer)request.getAttribute("page");
   int startPage = (Integer)request.getAttribute("startPage");
   int endPage = (Integer)request.getAttribute("endPage");
   int totalList = (Integer)request.getAttribute("totalList");
   int totalPage = (Integer)request.getAttribute("totalPage");
   String selected = null;
   String keyword = null;
   if(request.getAttribute("selected") != null && request.getAttribute("keyword") != null){
      selected = (String)request.getAttribute("selected");
      keyword = (String)request.getAttribute("keyword");
   }
%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../include/head.jsp" %> 
<script type="text/javascript">
function loginCheck(data, data1, no, page, selected, keyword){
   
   if(data1 == data && selected == null && keyword == null)
      location.href = "/anavada/idetail.ss?no="+no+"&page="+page;
   else if(data1 == data && selected != null && keyword != null)
      location.href = "/anavada/idetail.ss?no="+no+"&page="+page+"&selected="+selected+"&keyword="+keyword;
   else
      alert("회원님이 작성한 게시물이 아닙니다.");
}

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
                            <li class="glyphicon glyphicon-menu-right"><a href="/anavada/ilist">문의하기</a></li>
                        </ul>
                    </div>
                    <h2><span>문의하기</span></h2>
                    <h3>관리자에게 문의할 수 있는 공간입니다.</h3>
                </div>
            </div>
            <!--서브 비주얼/타이틀 끝-->

            <!-- 리스트 -->
            <div class="list-area">
               <!--종류 리스트-->
                <div class="sort-area">  
                    <h4>전체 <%= totalList %>개</h4>
                    <a href="/anavada/views/inquiry/inquiry_write.jsp" class="write_btn">글쓰기</a>
                    <div>
                        <form action="/anavada/isearch" method="post" id="">
                            목록 분류 : <select name="selected" class="ListSelect">
                                    <option value="none" selected disabled>분류 선택</option>
                                    <option value="title">제목</option>
                                    <option value="content">내용</option>
                                    <option value="writer">작성자</option>
                            </select>
                            
                            <input type="text" placeholder="검색어를 입력해주세요." name="keyword">
                            <button class="top-search"><i class="xi-search"></i></button>
                        </form>
                    </div>
                </div>
              
                <% if(totalList > 0) { %>
                   <% if(selected == null && keyword == null) { %>
                   <table>
                       <tbody>
                       <% for(Inquiry i : list) { %>
                           <tr onclick="loginCheck('<%= i.getIqId() %>','<%= loginMember.getMemberId() %>','<%= i.getIqNo() %>','<%= currentPage %>',null, null);">
                               <td class="number"><%= i.getIqNo() %></td>
                               <td class="title">
                                   <h2><span class="inquiry">문의</span><%= i.getIqTitle() %></h2>
                                   <ul>
                                       <li>작성자 : <%= i.getIqId() %></li>
                                       <li>작성일 : <%= i.getIqDate() %></li>
                                   </ul>
                                </td>
                                <td class="xeicon"><i class="xi-lock-o"></i></td>
                               <td class="fileDown">
                               <% if(i.getIqOriginal() != null || i.getIqOriginal2() != null || i.getIqOriginal3() != null) { %>
                               <i class="glyphicon glyphicon-floppy-saved"></i>
                               <% } %></td>
                               
                               <td class="declare_btn"><span>
                               <% if(i.getIqAnswer().equals("Y")) { %>
                               <i class="glyphicon glyphicon-bell"></i>처리완료
                               <% }else { %>
                               <% } %>
                               </span></td>
                            
                           </tr>
                           <% } %>
                       </tbody>
                   </table>
                   <% }else { %>
                   <table>
                       <tbody>
                       <% for(Inquiry i : list) { %>
                           <tr onclick="loginCheck('<%= i.getIqId() %>','<%= loginMember.getMemberId() %>','<%= i.getIqNo() %>','<%= currentPage %>','<%= selected %>', '<%= keyword %>');">
                               <td class="number"><%= i.getIqNo() %></td>
                               <td class="title">
                                   <h2><span class="inquiry">문의</span><%= i.getIqTitle() %></h2>
                                   <ul>
                                       <li>작성자 : <%= i.getIqId() %></li>
                                       <li>작성일 : <%= i.getIqDate() %></li>
                                   </ul>
                               </td>
                               <td class="fileDown">
                               <% if(i.getIqOriginal() != null || i.getIqOriginal2() != null || i.getIqOriginal3() != null) { %>
                               <i class="glyphicon glyphicon-floppy-saved"></i><% } %>
                               </td>
                               
                               <td class="declare_btn"><span>
                               <% if(i.getIqAnswer().equals("Y")) { %>
                               <i class="glyphicon glyphicon-bell"></i>처리완료
                               <% }else { %>
                               <% } %>
                               </span></td>
                           </tr>
                           <% } %>
                       </tbody>
                   </table>
                   
            <%} }else { %>
                <div class="list-no">
                    <p><img src="/anavada/resources/images/btnIcn/icn_big_listNo.png" alt="" title="" /></p>
                    <h1>목록이 없습니다.</h1>
                </div>
            <% } %>

                <div class="write-btn">
                    <a href="/anavada/views/inquiry/inquiry_write.jsp">글쓰기</a>
                </div>

            </div>
            <!-- 리스트 끝 -->

         
            <!-- 페이지넘버 -->
            <% if(totalPage > 1) { %>
            <dl class="list-paging pb80">
                <dd>
                <% if(selected == null && keyword == null) { %>
                   <% if(currentPage == 1) { %>
                       <a href="#none"><i class="glyphicon glyphicon-menu-left"></i></a>
                    <% }else { %>
                       <a href="/anavada/ilist?page=1"><i class="glyphicon glyphicon-menu-left"></i></a>
                    <% } %>
                    
                    <% for(int p=startPage; p<=endPage; p++) { %>
                       <% if(p == currentPage) { %>
                       <a href="#none" class="active"><%= p %></a>
                       <% }else { %>
                       <a href="/anavada/ilist?page=<%= p %>"><%= p %></a>
                    <% } } %>
                    
                    <% if(currentPage == totalPage) { %>
                       <a href="#none"><i class="glyphicon glyphicon-menu-right"></i></a>
                    <% }else { %>
                       <a href="/anavada/ilist?page=<%= totalPage %>"><i class="glyphicon glyphicon-menu-right"></i></a>
                    <% } %>
                    
                 <% }else { %>
                    <% if(currentPage == 1) { %>
                       <a href="#none"><i class="glyphicon glyphicon-menu-left"></i></a>
                    <% }else { %>
                       <a href="/anavada/isearch?page=1&selected=<%= selected %>&keyword=<%= keyword %>"><i class="glyphicon glyphicon-menu-left"></i></a>
                    <% } %>
                    
                    <% for(int p=startPage; p<=endPage; p++) { %>
                       <% if(p == currentPage) { %>
                       <a href="#none" class="active"><%= p %></a>
                       <% }else { %>
                       <a href="/anavada/isearch?page=<%= p %>&selected=<%= selected %>&keyword=<%= keyword %>"><%= p %></a>
                    <% } } %>
                    
                    <% if(currentPage == totalPage) { %>
                       <a href="#none"><i class="glyphicon glyphicon-menu-right"></i></a>
                    <% }else { %>
                       <a href="/anavada/isearch?page=<%= totalPage %>&selected=<%= selected %>&keyword=<%= keyword %>"><i class="glyphicon glyphicon-menu-right"></i></a>
                    <% } %>
                 <% } %>
                </dd>
            </dl>
            <% }else { %><br><br><br><br><% } %>
            <!-- 페이지넘버 끝 -->
         
        </div>
        <!-- 컨텐츠 끝 -->

        <%@ include file="../include/footer.jsp" %>
    </div>
<div id="frogue-container" class="position-right-bottom"
      data-chatbot="d666fc76-7aef-4b3b-84ac-83d1b295844d"
      data-user="사용자ID"
      data-init-key="value"
      ></div>
<!-- data-init-식별키=값 으로 셋팅하면 챗플로우에 파라미터와 연동가능. 식별키는 소문자만 가능 -->
<script>
(function(d, s, id){
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) {return;}
    js = d.createElement(s); js.id = id;
    js.src = "https:\/\/danbee.ai/js/plugins/frogue-embed/frogue-embed.min.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'frogue-embed'));
</script>
</body>
</html>