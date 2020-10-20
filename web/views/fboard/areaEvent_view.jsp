<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar, java.text.SimpleDateFormat, java.util.Date"%>
<%
	request.setCharacterEncoding("UTF-8");
	String fboardNo = request.getParameter("fboardno");
 	String festivalEndDate = request.getParameter("festivalEndDate");
%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../include/head.jsp" %> 
</head>
	<!-- 현재 진행중이지 않은 축제 알리기 -->
	<script type="text/javascript">
	<% 
	int ifestivalEndDate = Integer.parseInt(festivalEndDate);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    Calendar c1 = Calendar.getInstance();
	int strToday = Integer.parseInt(sdf.format(c1.getTime()));
	
	if(ifestivalEndDate < strToday) { %>
		alert('지난 축제 입니다');
	<% } %>
	
	</script>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
    <div id="wrap">
        <%@ include file="../include/header.jsp" %>

        <!-- 컨텐츠 -->
        <div id="content">

            <!--서브 비주얼/타이틀-->
            <div class="visual-sub-vagas areaEvent-vagas">
                <div class="vsv-copy sub-title">
                   <div>
                        <ul class="navi">
                            <li><a href="/anavada/views/main/main.jsp">홈</a></li>
                            <li class="glyphicon glyphicon-menu-right"><a href="#none">지역축제</a></li>
                        </ul>
                    </div>
                    <h2><span>지역축제</span></h2>
                    <h3>우리의 이웃과 'Anavada'를 통해 소통할 수 있는 공간입니다.</h3>
                </div>
            </div>
            <!--서브 비주얼/타이틀 끝-->
            
            <!-- 축제 상세보기 ajax -->
            <script type="text/javascript">
				$(function() {
					$.ajax({
						url : "/anavada/fbdetail",
						type : "get",
						data : {fboardno : <%= fboardNo%>},
						dataType : "json",
						success : function(data) {
							console.log("success : " + data);
								
						$('#title').text(data.title);
						$('#localname').text("서울시  ", data.localName);
						$('#createdd').text("축제 수정일 : " + data.festivalmodifiedDate);
						$('#readcount').text("조회수 " + data.readcount);
						$('#firstimage').attr("src", data.firstimage);
						
						$('#festivaloverview').html(data.overview);
						
						var values = '<tr><th>축제 상세 주소</th><td colspan="3">' + data.addr1 + data.addr2 + '</td></tr>' +
                         '<tr><th>축제 기간</th><td colspan="3">'+ data.period  + '</td></tr>' +
                    	'<tr><th>홈페이지</th><td colspan="3">'  + data.homepage + '</td></tr>' +
                    	'<tr><th>주최/주최기간 문의</th><td colspan="3">' + data.telname + ", " + data.tel + '</td></tr>';
                    	
						$('#fbdetail').append(values);
						},
						error : function(jqXHR, textstatus, errorthrown) {
							console.log("error : " + jqXHR + ", " + textstatus
									+ ", " + errorthrown);
						}
					}); //ajax
				}); //document.ready
		</script>
		<!-- 축제 상세보기 ajax 끝 -->

            <!-- 상세 div -->
            <div class="view-area areaEvent_view">
                 <h2 id="title"><span id="localname"> </span></h2>
                <ul>
                    <li><span>작성자 : </span>관리자</li>
                    <li id="createdd"></li>
                    <li id="readcount"></li>
                </ul>
                 <!-- 상세 table -->
                <div class="areaEvent_info">
                   <div><img id="firstimage" src=""></div>
                    <table id="fbdetail">
                        <colgroup>
                            <col width="15%">
                            <col width="35%">
                            <col width="15%">
                            <col width="35%">
                        </colgroup>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <h2>축제설명</h2>
                <div id="festivaloverview" class="view-ctn"></div>  
                <!-- 상세 div -->

                <div class="view-btn">
                  	<!--  <a href="#none" class="btn btn-prev">이전글</a> -->
                    <a href="/anavada/fbklist" class="btn btn-list">목록</a>
                   <!--  <a href="#none" class="btn btn-next">다음글</a> -->
                </div>
                
               <div class="cmt_wrap">
                    
                    <!-- 댓글 작성 폼 -->
                       <form name="replysubmit" method="post" action="">
                        <fieldset>
                            <div class="cmt_form">
                            <h4 id="totalcount" class="cmt_head"></h4>
                             <% if(!(loginMember == null)) { %>
                                <div class="cmt_body">
                                <input type="hidden" id="boardno" name="boardno" value="<%= fboardNo%>">    
                                <input type="hidden" id= "memberid" name="memberid" value="<%= loginMember.getMemberId()%>"> 
								<textarea id="replyContent" name="replyContent" style="resize: none; width:100%; min-height:100px; max-height:100px;" onfocus="this.value='';">비방글은 작성하실 수 없습니다.</textarea>
                                 <div class="cmt_ok"><input type="button" value="등록" onclick="ReplySubmit(${result.code})"></div>
                               	 </div>
                               	 <% } %>
                            </div>
                        </fieldset>
                    </form>
                    <!-- 댓글 작성 폼 끝 -->
                    
                    
                    <!-- 댓글 리스트 -->
                     <script type="text/javascript">
                      $(function(){
                    	  getCommentList()
                      });
                    	  
                    //댓글 리스트
                      function getCommentList() {	
                    	  $.ajax({
      						url : "/anavada/fbreplylist",
      						type : "get",
      						data : {fboardno : <%= fboardNo%>},
      						dataType : "json",
      						success : function(data) {
      							console.log("success : " + data);
      							console.log("댓글 나오기")
      							
      							var jsonStr = JSON.stringify(data);
								var json = JSON.parse(jsonStr);
								var totalcount = (json.list).length;	//가지고온 축제 개수
								var values = "";
								$("#totalcount").text('댓글 : ' + totalcount );
								
								for(var i in json.list) {
								values += '<li><div>' +
                                '<h4>user : ' + json.list[i].memberId + '</h4><span>' + json.list[i].fboardReplyCreatDate + '</span>' +
                                '</div><pre>' + decodeURIComponent(json.list[i].fboardReplyContent).replace(/\+/gi, " ") + '</pre>' + 
                                '<div class="cmt_delete" style="float: right;"><input type="button" value="삭제" onclick="Replydelete(' + json.list[i].fboardReplyNo + ');">' + 
                             	'</div><br></li>'
								}	//for in
								
								 $("#fbreply").empty();
								 $("#fbreply").html(values);

								/* $("#reply").html($("#reply").html() + values); */
      						},
      						error : function(jqXHR, textstatus, errorthrown) {
      							console.log("error : " + jqXHR + ", " + textstatus
      									+ ", " + errorthrown);
      						}
      					}); //ajax
                      }	
                      
                      //댓글  추가
                      function ReplySubmit(code) {
                          if (document.getElementById("replyContent").value == "") {
                              alert("내용을 입력해주세요");
                              return false;
                    	}else
                  	 		 $.ajax({
                  				url : "/anavada/fbreplyinsert.ss",
                	        	type:'POST',
                	        	data : {replyContent : $('#replyContent').val(), memberid : $('#memberid').val(), boardno : $('#boardno').val()},
                	        	success : function(data){
                	          		if(data == 1) {
                  	        		 	alert("댓글이 등록되었습니다."); 
                  	        		} else {
                  	        			alert("댓글 등록을 실패했습니다."); 
                  	        		}
                	        			 $("#replyContent").val("");
                 	            	    getCommentList();
	                	        },
    	            	        error:function(request,status,error){
        	        	        	alert("로그인후 이용해주세요.");
            	    	        	console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
                		       }
                		    });
                    	}
                    
                      // 댓글 삭제
                    function Replydelete(fboardReplyNo, memberId) {
                  	  console.log(fboardReplyNo + "댓글 삭제하기")
                  	  
                  	 	 $.ajax({
                    		url : "/anavada/fbreplydelete.ss",
                  	        type:'POST',
                  	        data : {fboardReplyno : fboardReplyNo, memberid : $('#memberid').val() },
                  	        success : function(data){
                  	        	if(data == 1) {
                  	        	 	alert("댓글이 삭제되었습니다."); 
                  	        	} else {
                  	        		alert("댓글 삭제를 실패했습니다."); 
                  	        	}
                  	                getCommentList();
                  	        },
                  	        error:function(request,status,error){
                  	        	console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
                  	     	  }
                  	   	 });
                    }

                    </script>
                    
                    <ul id="fbreply" class="cmt_con">
                    </ul>
                   <!--  <button class="cmt_in">댓글 더보기 <i class="glyphicon glyphicon-menu-right"></i></button> -->
             	 </div>
            </div>
            <!-- 상세 끝 -->

        </div>
        <!-- 컨텐츠 끝 -->

        <%@ include file="../include/footer.jsp" %>
    </div>
</body>
</html>