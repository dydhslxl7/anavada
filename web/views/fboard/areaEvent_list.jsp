<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList, fboard.model.vo.Fboard"%>
	
<%
	ArrayList<Fboard> list = (ArrayList<Fboard>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/head.jsp"%>

<style>
/* 카카오맵 오버레이 꾸미기  */
.wrap {
	position: absolute;
	left: 0;
	bottom: 40px;
	width: 288px;
	height: 132px;
	margin-left: -144px;
	text-align: left;
	overflow: hidden;
	font-size: 12px;
	font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;
	line-height: 1.5;
}
.wrap .info {
	width: 286px;
	height: 120px;
	border-radius: 5px;
	border-bottom: 2px solid #ccc;
	border-right: 1px solid #ccc;
	overflow: hidden;
	background: #fff;
}

.wrap .info:nth-child(1) {
	border: 0;
	box-shadow: 0px 1px 2px #888;
}

.info .title {
	padding: 5px 0 0 10px;
	height: 25px;
	background: #eee;
	border-bottom: 1px solid #ddd;
	font-size: 14px;
	font-weight: bold;
}

.info .close {
	position: absolute;
	top: 10px;
	right: 10px;
	color: #888;
	width: 17px;
	height: 17px;
	background:
		url('http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');
}

.info .close:hover {
	cursor: pointer;
}

.info .body {
	position: relative;
	overflow: hidden;
}

.info .desc {
	position: relative;
	margin: 13px 0 0 90px;
	height: 75px;
}

.desc .ellipsis {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.desc .jibun {
	font-size: 11px;
	color: #888;
	margin-top: -2px;
}

.info .img {
	position: absolute;
	top: 6px;
	left: 5px;
	width: 73px;
	height: 71px;
	border: 1px solid #ddd;
	color: #888;
	overflow: hidden;
}

.info:after {
	content: '';
	position: absolute;
	margin-left: -12px;
	left: 50%;
	bottom: 0;
	width: 22px;
	height: 12px;
	background:
		url('http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')
}

.info .link {
	color: #5085BB;
}
/* 카카오맵 오버레이 꾸미기  끝 */
</style>

</head>

<body oncontextmenu="return false" onselectstart="return false"
	ondragstart="return false">
	
	<div id="wrap">
		<%@ include file="../include/header.jsp"%>

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
					<h2>
						<span>지역축제</span>
					</h2>
					<h3>우리의 이웃과 'Anavada'를 통해 소통할 수 있는 공간입니다.</h3>
				</div>
			</div>
			<!--서브 비주얼/타이틀 끝-->

			<!-- 전체 리스트 -->
			  <div class="areaEvent_list">
			  
			    <!-- 맵 리스트 -->
                <div class="areaEvent_map">
                    <div id="map" style="width: 100%; height: 350px;"></div>
                    <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=53d444db7d449eb66c0229426868cf97"></script>
                    <script>
                        var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
                        mapOption = {
                            center : new kakao.maps.LatLng(37.5311008, 126.9810742), // 지도의 중심좌표
                            level : 9 // 지도의 확대 레벨
                        };

                        // 지도를 생성한다 
                        var map = new kakao.maps.Map(mapContainer, mapOption);
                        
                     	// 지도에 마커를 표시합니다 
                 		<% int index = 0;%>
                		<%for (Fboard f : list) { index++;%>
                		var marker<%=index%> = new daum.maps.Marker({
                			map : map,
                			position : new daum.maps.LatLng(<%=f.getMapY()%>, <%=f.getMapX()%>)
                		});

                		// 커스텀 오버레이에 표시할 컨텐츠 입니다
                		var content<%=index%> = '<div class="wrap">'
                				+ '    <div class="info">'
                				+ '        <div class="title">' + "<%=  f.getFestivalTitle()%>"
                				+ '            <div class="close" onclick="closeOverlay<%=index%>()" title="닫기"></div>'
                				+ '        </div>'
                				+ '        <div class="body">'
                				+ '            <div class="img">'
                				+ '                <img src="<%= f.getThumbnail() %>" width="73" height="70" alt="NoImage">'
                				+ '           </div>'
                				+ '            <div class="desc">'
                				+ '                <div class="ellipsis">서울시 <%= f.getLocalName()%></div>'
                				+ '                <div class="jibun ellipsis"><%=f.getFestivalStartDate()%> ~ <%=f.getFestivalEndDate()%></div>'
                				+ '                <div><a href="/anavada/views/fboard/areaEvent_view.jsp?fboardno=<%=f.getFboardNo()%>&festivalEndDate=<%=f.getFestivalEndDate()%>" class="link">상세 페이지로 이동</a></div>'
                				+ '           </div>' + '        </div>' + '    </div>'
                				+ '</div>'; 
                	
                 		// 마커 위에 커스텀오버레이를 표시합니다
                		// 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
                		var overlay<%=index%> = new daum.maps.CustomOverlay({
                			content : content<%=index%>,
                			map : map,
                			position : marker<%=index%>.getPosition()
                		});
                		
                		overlay<%=index%>.setMap(null);	// 처음 시작할때는 오버레이 표시 안하기
                		
                		// 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
                		daum.maps.event.addListener(marker<%=index%>, 'click', function() {
                			overlay<%=index%>.setMap(map);
                		});

                		// 커스텀 오버레이를 닫기 위해 호출되는 함수입니다 
                		function closeOverlay<%=index%>() {
                			overlay<%=index%>.setMap(null); 
                			}
                		<%}%>
                	</script>
                <!-- 맵 리스트 끝 -->
			
					<!--검색 리스트-->
					<div class="sort-area">
 						<h4 id="totalcount"></h4>
						<div> 
						    <form action="" name="" method="">
							<label><input type="checkbox" id="lastList">지난 축제 보기</label>&nbsp;&nbsp;/&nbsp;&nbsp; 
								지역 분류 : <select name="" id="locationSelect" class="LocationSelect">
									<option value="0" selected="selected">전체 보기</option>	<!-- 서울시 전체 고정  -->
									<option value="1">강남구</option>
									<option value="2">강동구</option>
									<option value="3">강북구</option>
									<option value="4">강서구</option>
									<option value="5">관악구</option>
									<option value="6">광진구</option>
									<option value="7">구로구</option>
									<option value="8">금천구</option>
									<option value="9">노원구</option>
									<option value="10">도봉구</option>
									<option value="11">동대문구</option>
									<option value="12">동작구</option>
									<option value="13">마포구</option>
									<option value="14">서대문구</option>
									<option value="15">서초구</option>
									<option value="16">성동구</option>
									<option value="17">성북구</option>
									<option value="18">송파구</option>
									<option value="19">양천구</option>
									<option value="20">영등포구</option>
									<option value="21">용산구</option>
									<option value="22">은평구</option>
									<option value="23">종로구</option>
									<option value="24">중구</option>
									<option value="25">중랑구</option>
								</select> 
								<select id="sortSelect" class="ListSelect" style="margin-right: 20px;">
									<option value="enddateAsc" selected="selected">종료일 최신순</option>	<!-- 고정  -->
									<option value="readcountDesc">조회수 높은순</option>
									<option value="replyDesc">댓글 많은순</option>
								</select>
								축제명 검색 : <input type="text" id=title onkeyup="searchFboard()" placeholder="축제명을 입력하세요">	<!-- placeholder 처리가 안됨 -->
							<!--  <button class="top-search"><i class="xi-search"></i></button>  -->	<!-- ajax로 바로 처리하기때문에 버튼 필요없음 -->
							</form>
						</div>
					</div>
					<!-- 검색 리스트 끝 -->
					
				<!-- 축제 게시판 리스트  -->
				<script type="text/javascript">
				
				$(document).ready(function() {
					searchFboard();	// 항상 축제 게시판 가지고 오기 
				});	//document.ready
				
				
				/* 축제 가지고 오기 ajax */
				function searchFboard() {
					console.log("searchFboard()");
					$.ajax({
						url : "/anavada/fblist",
						type : "get",
						data : { lastList : $('#lastList').val(), locationSelect : $("#locationSelect").val(), 
							sortSelect: $('#sortSelect').val(), title : $('#title').val()},
						dataType : "json",
						success : function(data){
							console.log(" fblist success : " + data);
							
							var jsonStr = JSON.stringify(data);
							var json = JSON.parse(jsonStr);
							var totalcount = (json.list).length;	//가지고온 축제 개수
							var values = "";
							$("#totalcount").text( '전체 : ' + totalcount);
							
							if (totalcount > 0) {
								$("#listNo").empty();
							} else {
								$("#listNo").html('<p><img src="/anavada/resources/images/btnIcn/icn_big_listNo.png"alt="" title="" /></p><h1>목록이 없습니다.</h1>');
							}
							
							for(var i in json.list) {
								values += "<tr onclick='moveDetailPage(" + json.list[i].fboardNo + ", " + json.list[i].festivalEndDate + ");'>" +
								"<td class='number'>" + json.list[i].fboardNo + "</td>" +
								"<td class='sum'><img src='"+ json.list[i].thumbnail +"' width='150px' height='100px' ></td>" +
								"<td  name='ftitle' class='title'><h4><span>" +  decodeURIComponent(json.list[i].festivalTitle).replace(/\+/gi, " ") + "</span></h4></td>" +
								"<td><ul>" +
										"<li>축제 지역 : " + json.list[i].localName + "</li>" +
										"<li>축제기간 : "+ json.list[i].festivalStartDate + " ~ " + json.list[i].festivalEndDate+ "</li>" +
										"<li>조회수 : " + json.list[i].readcount + "<span> 댓글 : " + json.list[i].replycount +  "</span></li>" +
									"</ul></td></tr>" 
							}	//for in
							$("#fboard-table").html(values);	
						},
						error : function(jqXHR, textstatus, errorthrown){
							console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
						}
					});	//ajax 
				}
				/* 축제 가지고 오기 ajax 끝 */
		
				
				/* 검색 ajax로 메소드 이동 */
				$(function(){
					
					/* 지난축제 check 값설정 후 ajax로 메소드 이동 */
					$('#lastList').change(function(){
			 			if($('#lastList').is(":checked")) {
			 				$('#lastList').val("true");
			 				searchFboard();
			 			} else {
			 				$('#lastList').val("on");
			 				searchFboard();
			 			} 
					});
					
					/* 지역별 ajax로 메소드 이동 */
					$('#locationSelect').change(function(){
						searchFboard();
					});
					
					/* 정렬 ajax로 메소드 이동 */
					$('#sortSelect').change(function(){
						searchFboard();
					});
					
				}); //document.ready
					
				
		/* 상세페이지로 이동 */
		function moveDetailPage(boardNo, festivalEndDate) {
			location.href = '/anavada/views/fboard/areaEvent_view.jsp?fboardno=' + boardNo + '&festivalEndDate=' + festivalEndDate ;
		}
		</script>
		<!-- 축제 게시판 리스트 끝 -->
					
					<!-- 축제 목록 table  -->
					<table id="fboard-table" class="cmnt_list">
						<tbody>

						</tbody>
					</table>
					<!-- 축제 목록 table 끝 -->
					
					<!-- 목록이 없음 표시  -->
					<div class="list-no" id="listNo">
					</div>
					
				</div>
				<!-- 전체 리스트 끝 -->

<!-- 				페이지넘버
				<dl class="list-paging pb80">
					<dd>
						<a href="#none"><i class="glyphicon glyphicon-menu-left"></i></a>
						<a href="#none" class="active">1</a> <a href="#none">2</a> <a
							href="#none">3</a>
						활성화 class="active"
						<a href="#none">4</a> <a href="#none">5</a> <a href="#none"><i
							class="glyphicon glyphicon-menu-right"></i></a>
					</dd>
				</dl>
				페이지넘버 끝 -->
				
			</div>
			<!-- 컨텐츠 끝 -->
			</div>

			<%@ include file="../include/footer.jsp"%>
		</div>
</body>
</html>