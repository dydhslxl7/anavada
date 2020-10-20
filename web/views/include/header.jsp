<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="member.model.vo.Member"%>
<div id="header">

	<!-- 상단메뉴 -->
    <dl class="gnbWrap clearfix">
    	<dt><h1><a href="/anavada/" title="Anavada">Anavada</a></h1></dt>
        <dd>
            <ul class="gnb clearfix">
                <li><a href="/anavada/jblist">중고거래</a></li>
                <li><a href="/anavada/clistview?page=1&local=0">커뮤니티</a></li>
                <li><a href="/anavada/fbklist">지역축제</a></li>
                <li>
                    <a href="/anavada/nlist">고객센터</a>
                    <div class="subGnb">
                        <a href="/anavada/nlist">공지사항</a>
                        <a href="/anavada/flist">FAQ</a>
                        <% if(loginMember != null) { %>
                        <a href="/anavada/ilist">문의하기</a>
                        <% }else { %><a href="/anavada/views/member/login.jsp">문의하기</a><% } %>
                        <% if(loginMember != null && loginMember.getMemberId().equals("admin")){ %>
                        <a href="/anavada/dbolist.ad">신고하기</a>
                        <% }else{ %>
                        <a href="/anavada/dbolist">신고하기</a>
                        <% } %>
                   		
                     
                    </div>
                </li>
            </ul>
        </dd>
        <dt>
            <ul class="util">
                <li>
                    <form action="/anavada/jblist" method="post">
                        <input type="text" name="titlesearch" placeholder="중고거래 상품을 입력해주세요">
                        <button class="top-search"><i class="xi-search"></i></button>
                    </form>
                </li>
                <% if(loginMember == null){ %>
                <li><a class="hover_line01" href="/anavada/views/member/join_agree.jsp">JOIN</a></li>
                <li><a class="hover_line01" href="/anavada/views/member/login.jsp">LOGIN</a></li>
                <% } else if(loginMember.getMemberId().equals("admin")){ %>
                <li><a class="hover_line01" href="/anavada/mlist.ad?memberId=<%= loginMember.getMemberId() %>&secessionOK=N">관리자페이지</a></li>
                <li><a class="hover_line01" href="/anavada/logout">LOGOUT</a></li>
                <% } else { %>
                <li><a class="hover_line01" href="/anavada/mypage.cp?memberId=<%= loginMember.getMemberId() %>">MYPAGE</a></li>
                <li><a class="hover_line01" href="/anavada/logout">LOGOUT</a></li>
                <% } %>
                <li><i id="favorite" class="xi-star-o" title="즐겨찾기 등록"></i></li>
            </ul>
        </dt>
    </dl>
    <!-- //상단메뉴 -->
</div>
