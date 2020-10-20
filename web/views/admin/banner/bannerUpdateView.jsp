<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="banner.model.vo.Banner"%>
<%
		Banner banner = (Banner)request.getAttribute("banner");
%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배너상세글</title>
<%@ include file="/views/admin/include/admin_head.jsp" %>
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
    <div id="wrap">
        <%@ include file="../include/admin_header.jsp" %> 

        <!-- 컨텐츠 -->
        <div id="admin_container">

			<!-- 상단 타이틀 -->
           	   <div class="admin_title">
             	   <div class="admin_path">
              	      <h3>배너관리</h3>
                	    <h2>등록</h2>
               	 </div>
    	        </div>
    	    <!-- 상단 타이틀 -->
            <!-- 상세글보기 및 수정페이지 -->
            <div class="admin_content">
            <form action="/anavada/bupdate.ad"  method="post" enctype="multipart/form-data" 
               		 onesubmit="return validate();"  class="form-inline">
               		 <input type="hidden" name="no" value=<%=  banner.getBannerNo() %>>
               		 <input type="hidden" name="ofile" value=<%= banner.getBannerOriginal()  %>> 
					 <input type="hidden" name="rfile" value=<%= banner.getBannerRename() %>>
               		 
                    <fieldset>
                        <div class="write-area">

                            <h2>배너 등록 수정</h2>

                            <table>
                                <colgroup>
                                    <col width="20%">
                                    <col width="80%">
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <td><span>제목 : </span></td>
                                        <td><input type="text" name="title" title="" class="form-control w100p" placeholder="<%= banner.getBannerTitle() %>"  value="<%= banner.getBannerTitle() %>" required="required"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><span>게시한배너H/W : </span>
                                         <td>
                                         <%if(banner.getBannerChk() != null){ %>
                                        	  <%if(banner.getBannerChk().equals("H")){ %>
                                        		<label><input type="radio" name="controller" id="숨기기" value="H" checked>숨기기</label> &nbsp;
                                    		    <label><input type="radio" name="controller" id="보이기"  value="W">보이기</label>
                                        	  <% }else{ %>
                                        		<label><input type="radio" name="controller" id="숨기기" value="H" >숨기기</label> &nbsp;
                                    	   		<label><input type="radio" name="controller" id="보이기"  value="W" checked>보이기</label>
                                    	   	  <% } %>
                                    	   	<%}else{ %>	
                                        <% } %>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>설명</td>
                                        <td><textarea name="content" rows="" cols="" class="form-control" 
                                        style="resize: none; width:100%; min-height:200px; max-height:200px;" placeholder="" required="required" ><%= banner.getBannerContent() %></textarea></td>
                                    </tr>
                                    <tr>
                                        <td>배너파일등록</td>
                                        <td><% if(banner.getBannerOriginal() != null){ %>
                                        		<%= banner.getBannerOriginal() %>
                                        		<input type="checkbox" name="deleteFlag" value="yes">파일삭제
                                        		<a href="/anavada/bfdown?ofile=<%= banner.getBannerOriginal()%>&rfile=<%= banner.getBannerRename()%>"> <%= banner.getBannerOriginal() %></a>
                                        		<% } %>
                                        		<input type="file" name="upfile" >
                                        </td>
                                        
                                    </tr>
									<tr>
										<td>배경사이즈</td>
										<td><input type="text" name="size" title="" class="form-control w100p" placeholder="" required="required" value="<%= banner.getBannerSize() %>"/></td>
									</tr>
									<tr>
										<td>URL등록</td>
										<td><input type="text" name="url" title="" class="form-control w100p" placeholder="" required="required" value="<%= banner.getBannerUrl() %>"/></td>
									</tr>
								</tbody>
                            </table>
             		     </div>
                   </fieldset>

                <div class="view-btn">
                    <!-- <a href="#none" class="btn btn-prev">이전글</a> -->
                    
                    <!-- <a href="#none" class="btn btn-next">다음글</a> -->
                    <a href="/anavada/blist.ad" class="btn btn-list">목록</a>
                    <%-- <a href="/anavada/dboupmove.ad?dboNo=<%= banner.getBannerNo() %>" class="btn btn-success">상태 수정하기</a> &nbsp; --%>
                    <input type="submit" class="btn btn-success" value="상태수정하기">
                    <a href="/anavada/bdelete.ad?bannerNo=<%= banner.getBannerNo()%>&rfile=<%= banner.getBannerRename() %>"  class="btn btn-delete"> 삭제하기 </a>
                </div>
            </div>
            <!-- 상세 끝 -->
		 </form>
         <%@ include file="../include/admin_footer.jsp" %> 
        </div>
        <!-- 컨텐츠 끝 -->
    </div>
</body>
</html>