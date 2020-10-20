<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, faq.model.vo.Faq"%>
<%
	ArrayList<Faq> list = (ArrayList<Faq>)request.getAttribute("list");
%>
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
        $(function(){
            $(".ctn").hide();
        });
        $(function(){
            $('.faqTap a').click(function(){
                var tab_data = $(this).attr('data-tab');
				
                $('.faqTap a').removeClass('active');
                $('.qna_list').removeClass('on');
				
                $(this).addClass('active');
                $("#" + tab_data).addClass('on');
            })
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

            <!-- 리스트 -->
            <div class="list-area">
                <div class="faqTap">
                    <a href="#" class="active" data-tab="tab01">전체</a>
                    <a href="#" data-tab="tab02">회원정보</a>
                    <a href="#" data-tab="tab03">중고거래</a>
                    <a href="#" data-tab="tab04">커뮤니티</a>
                    <a href="#" data-tab="tab05">지역축제</a>
                </div>
                <!--종류 리스트-->
                <div class="sort-area" style="margin-top:30px;"><br>
                    <!-- <h4>전체 개</h4>
                    <div>
                        <form action="" method="" id="">
                            목록 분류 : <select name="" class="ListSelect">
                                    <option value="분류 선택" selected="selected">분류 선택</option>
                                    <option value="제목">제목</option>
                                    <option value="내용">내용</option>
                            </select>
                            
                            <input type="text" placeholder="검색어를 입력해주세요.">
                            <button class="top-search"><i class="xi-search"></i></button>
                        </form>
                    </div> -->
                </div>
<!-- ********************************************************* -->
                <div class="qna_list on" id="tab01">
                    <% for(Faq f : list) { %>
                    	<ul class="question">
                        	<li class="Qmarker"><span>Q</span></li>
                        	<li class="title">
                        	
                        	<% switch(f.getFaqCategory()) { 
                        	   case 1 : %><span class="Msel">회원정보</span><%break;
                        	   case 2 : %><span class="Psel">중고거래</span><%break;
                        	   case 3 : %><span class="Csel">커뮤니티</span><%break;
                        	   case 4 : %><span class="Esel">지역축제</span><%break;
                        	 } %>
                        	
                        	<%= f.getFaqTitle() %></li>
                        	<li>관리자</li>
                        	<li><%= f.getFaqDate() %></li>
                    	</ul>
                    	<ul class="ctn">
                        	<li class="Amarker"><span>A</span></li>
                        	<li>
                            <h4>답변</h4>
                            <p><%= f.getFaqContent() %><p>
                        	</li>
                    	</ul>
                    <% } %>
                </div>
<!-- ********************************************************* -->
                <div class="qna_list" id="tab02">
                	<% for(Faq f : list) { %>
                		<% if(f.getFaqCategory() == 1) { %>
                    	<ul class="question">
                        	<li class="Qmarker"><span>Q</span></li>
                        	<li class="title">
                        	
                        	<% switch(f.getFaqCategory()) { 
                        	   case 1 : %><span class="Msel">회원정보</span><%break;
                        	   case 2 : %><span class="Psel">중고거래</span><%break;
                        	   case 3 : %><span class="Csel">커뮤니티</span><%break;
                        	   case 4 : %><span class="Esel">지역축제</span><%break;
                        	 } %>
                        	
                        	<%= f.getFaqTitle() %></li>
                        	<li>관리자</li>
                        	<li><%= f.getFaqDate() %></li>
                    	</ul>
                    	<ul class="ctn">
                        	<li class="Amarker"><span>A</span></li>
                        	<li>
                            <h4>답변</h4>
                            <p><%= f.getFaqContent() %><p>
                        	</li>
                    	</ul>
                    <% } }%>
                </div>
<!-- ********************************************************* -->
                <div class="qna_list" id="tab03">
                <% for(Faq f : list) { %>
                		<% if(f.getFaqCategory() == 2) { %>
                    	<ul class="question">
                        	<li class="Qmarker"><span>Q</span></li>
                        	<li class="title">
                        	
                        	<% switch(f.getFaqCategory()) { 
                        	   case 1 : %><span class="Msel">회원정보</span><%break;
                        	   case 2 : %><span class="Psel">중고거래</span><%break;
                        	   case 3 : %><span class="Csel">커뮤니티</span><%break;
                        	   case 4 : %><span class="Esel">지역축제</span><%break;
                        	 } %>
                        	
                        	<%= f.getFaqTitle() %></li>
                        	<li>관리자</li>
                        	<li><%= f.getFaqDate() %></li>
                    	</ul>
                    	<ul class="ctn">
                        	<li class="Amarker"><span>A</span></li>
                        	<li>
                            <h4>답변</h4>
                            <p><%= f.getFaqContent() %><p>
                        	</li>
                    	</ul>
                    <% } }%>
                </div>
<!-- ********************************************************* -->
                <div class="qna_list" id="tab04">
                <% for(Faq f : list) { %>
                		<% if(f.getFaqCategory() == 3) { %>
                    	<ul class="question">
                        	<li class="Qmarker"><span>Q</span></li>
                        	<li class="title">
                        	
                        	<% switch(f.getFaqCategory()) { 
                        	   case 1 : %><span class="Msel">회원정보</span><%break;
                        	   case 2 : %><span class="Psel">중고거래</span><%break;
                        	   case 3 : %><span class="Csel">커뮤니티</span><%break;
                        	   case 4 : %><span class="Esel">지역축제</span><%break;
                        	 } %>
                        	
                        	<%= f.getFaqTitle() %></li>
                        	<li>관리자</li>
                        	<li><%= f.getFaqDate() %></li>
                    	</ul>
                    	<ul class="ctn">
                        	<li class="Amarker"><span>A</span></li>
                        	<li>
                            <h4>답변</h4>
                            <p><%= f.getFaqContent() %><p>
                        	</li>
                    	</ul>
                    <% } }%>
                </div>
<!-- ********************************************************* -->
                <div class="qna_list" id="tab05">
                    <% for(Faq f : list) { %>
                		<% if(f.getFaqCategory() == 4) { %>
                    	<ul class="question">
                        	<li class="Qmarker"><span>Q</span></li>
                        	<li class="title">
                        	
                        	<% switch(f.getFaqCategory()) { 
                        	   case 1 : %><span class="Msel">회원정보</span><%break;
                        	   case 2 : %><span class="Psel">중고거래</span><%break;
                        	   case 3 : %><span class="Csel">커뮤니티</span><%break;
                        	   case 4 : %><span class="Esel">지역축제</span><%break;
                        	 } %>
                        	
                        	<%= f.getFaqTitle() %></li>
                        	<li>관리자</li>
                        	<li><%= f.getFaqDate() %></li>
                    	</ul>
                    	<ul class="ctn">
                        	<li class="Amarker"><span>A</span></li>
                        	<li>
                            <h4>답변</h4>
                            <p><%= f.getFaqContent() %><p>
                        	</li>
                    	</ul>
                    <% } }%>
                </div>
                <br><br><br><br><br><br>
            </div>
            <!-- 리스트 끝 -->


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