<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, banner.model.vo.Banner"%>
<%
	ArrayList<Banner> blist = (ArrayList<Banner>) request.getAttribute("list");
	int listCount = ((Integer) request.getAttribute("listCount")).intValue();
	int startPage = ((Integer) request.getAttribute("startPage")).intValue();
	int endPage = ((Integer) request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer) request.getAttribute("maxPage")).intValue();
	int currentPage = ((Integer) request.getAttribute("currentPage")).intValue();
%>   
<!DOCTYPE html>
<html>
<head>
<title>banner</title>
   <%@ include file="/views/admin/include/admin_head.jsp" %> 
</head>
<body>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
    <div id="wrap">
        <%@ include file="../include/admin_header.jsp" %>

<script type="text/javascript" src="/anavada/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
function deleteAction(){
	var checkRow = "";
	$("input[name='checkDel']:checked").each(function(){
		checkRow = checkRow + $(this).val()+",";
	});
	checkRow = checkRow.substring(0, checkRow.lastIndexOf(","));
	
	if(checkRow == ""){
		alert("삭제할 대상을 선택하세요.");
		return false;
	}
	console.log("### checkRow => {"+checkRow+"}");
	
	if(confirm("삭제 하시겠습니까?"))
		location.href = "/anavada//bcheckdel?checkRow="+checkRow;
}
$(function(){
	$("#all").on("click", function(){
		if($('input:checkbox[id="all"]').is(":checked")==true){
			$('input:checkbox[name="checkDel"]').each(function(){
				this.checked = true;
			});
		}else{
			$('input:checkbox[name="checkDel"]').each(function(){
				this.checked = false;
			});
		}
	});
})
</script>
        <!-- 컨텐츠 -->
        <div id="admin_container">
        
        <!-- 상단 타이틀 -->
            <div class="admin_title">
                <div class="admin_path">
                    <h3>배너관리</h3>
                    <h2>등록</h2>
                </div>
            </div>

            <!-- 리스트 -->

            <div class="list-area">
                <!--종류 리스트-->
                <div class="sort-area">  
                    <h4>전체 <%= listCount %> 개</h4>
                    <a href="/anavada/views/admin/banner/banner_change.jsp" class="write_btn">배너 등록</a>
                    <div>
                        <!-- <form action="" method="" id="">
                            유형 선택 : <select name="" class="ListSelect">
                                    <option value="분류 선택" selected="selected">유형</option>
                                    <option value="중고거래 신고">중고거래</option>
                                    <option value="커뮤니티 신고">커뮤니티</option>
                            </select>
                            
                            <input type="text" placeholder="검색어를 입력해주세요.">
                            <button class="top-search"><i class="xi-search"></i></button>
                        </form>  -->
                    </div>
                </div>
                <!-- 검색영역 끝 -->
                
                <% if (list.size() == 0 ){ %>
                <br><br>
                <div class="list-no" align="center">
                    <p>
                        <img src="/anavada/resources/images/btnIcn/icn_big_listNo.png"
                            alt="" title="" />
                    </p>
                    <h1>목록이 없습니다.</h1>
                </div><br><br>
                <% }else{ %>
                
                <form action=""> 
                    <table class="memberTable">
                    <colgroup>
                        <col width="5%">
                        <col width="8%">
                        <col width="10%">
                        <col width="10%">
                        <col width="18%">
                        <col width="10%">
                        <col width="10%">
                        <col width="8%">
                    </colgroup>
                
                    <tbody>
                    <tr>
                        <th><input type="checkbox" id="all" name="checkAll" class="checkBox"></th>
                        <th>번호</th>
                        <th>제목</th>
                        <th>배너보이기/숨기기</th>
                        <th>첨부파일</th>
                        <th>사이즈</th>
                        <th>배너URL</th>
                    </tr>
                    <%for(Banner b : blist) { %>
                    <tr >
                        <td class="checkBox"><input type="checkbox" name="checkDel" value="<%= b.getBannerNo() %>" ></td>
                        <td class="number" onclick="location.href='/anavada/bselone.ad?bannerNo=<%= b.getBannerNo()%>'"><%= b.getBannerNo() %></td>
                        <td class="title" onclick="location.href='/anavada/bselone.ad?bannerNo=<%= b.getBannerNo()%>'"><%= b.getBannerTitle() %></td>
                            <td class="check"><% if(b.getBannerChk().equals("H")){ %>
                                        </i>숨기기</span> 
                                        <% }else{// 배너가 보이게 하려면%>
                                        </i>보이기</span> 
                                        <% }  %></td> 
                        <td class="original" onclick="location.href='/anavada/bselone.ad?bannerNo=<%= b.getBannerNo()%>'"><%= b.getBannerOriginal() %></td>
                        <td class="size" onclick="location.href='/anavada/bselone.ad?bannerNo=<%= b.getBannerNo()%>'"><%= b.getBannerSize() %></td>    
                        <td class="url" onclick="location.href='/anavada/bselone.ad?bannerNo=<%= b.getBannerNo()%>'"><%= b.getBannerUrl() %></td>      		          
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                    <% } %>
                    
                </form> 
              <!-- 리스트 끝 -->
            
                <!-- 버튼 -->
                <div class="btn_wrap">
                    <a onclick="deleteAction();" class="btn-left btn_gray">선택삭제</a>
                </div>

                <!-- 페이지넘버 -->
                <dl class="list-paging pb80">
                    <dd>
                    <% if(currentPage <= 1){ %>
                        <a><i class="glyphicon glyphicon-menu-left"></i></a>
                    <% }else{ %>
                        <a href="/anavada/blist.ad?page=1"><i class="glyphicon glyphicon-menu-left"></i></a>
                    <% } %>
                    <!-- 이전으로 이동처리 -->
                    <%-- <% if((currentPage) - 10 < startPage && (currentPage - 10) > 1){ %>
                    
                    <% }else{ %>
                    <% } %> --%>
                    <!-- 현재 페이지가 속한 페이지그룹의 숫자 출력 처리 -->
                    <% for(int p = startPage; p <= endPage; p++){ 
                            if(p == currentPage){%>
                            <a class="active"><%= p %></a>
                            <% }else{ %>
                            <a href="/anavada/blist.ad?page=<%= p %>"><%= p %></a>
                    <% }} %>
                    <!-- 맨끝 페이지로 이동처리 -->
                    <% if(currentPage >= maxPage){ %>
                        <a><i class="glyphicon glyphicon-menu-right"></i></a>
                    <% }else{ %>
                        <a href="/anavada/blist.ad?page=<%= maxPage %>"><i class="glyphicon glyphicon-menu-right"></i></a>
                    <% } %>
                        <!-- <a href="#none" class="active">1</a> -->
                        <!-- <a href="#none">2</a> -->
                        <!-- <a href="#none">3</a>활성화 class="active" -->
                        <!-- <a href="#none">4</a> -->
                        <!-- <a href="#none">5</a> -->
                        <!-- <a href="#none"><i class="glyphicon glyphicon-menu-right"></i></a> -->
                    </dd>
                </dl>
            <!-- 페이지넘버 끝 -->
            </div>
            <%@ include file="../include/admin_footer.jsp" %>
            <!-- 컨텐츠 끝 -->
        </div>
    </div>
</body>
</html>