<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../include/head.jsp" %>
    <script type="text/javascript">
		$(function(){
			$.ajax({
				url : "/anavada/ctop2",
				type : "get",
				dataType : "json",
				success : function(data) {
					console.log("success : " + data);
					// object ==> string으로 변환
					var jsonStr = JSON.stringify(data);
					// string ==> json 객채로 바꿈
					var json = JSON.parse(jsonStr);
					var idx = 1;
					var values = "";
					for (var i in json.list) {
						values += "<dl><dt><span>" + idx + "</span></dt><dd>"
							+ "<h3 onclick=\"location.href='/anavada/cdetail?cnum=" + json.list[i].cnum +"'\"><span>[" + json.list[i].local + "] </span>" 
							+ decodeURIComponent(json.list[i].ctitle).replace(/\+/gi, " ") + "</h3>"
							+ "<p class=\"con\" onclick=\"location.href=''\">"
							+ decodeURIComponent(json.list[i].ccontent).replace(/\+/gi, " ")
							+ "</p>" 
							+ " <p><i class=\"good_i glyphicon glyphicon-heart-empty\">좋아요<span>" + json.list[i].clike
							+ "</span></i>"
							+ "<i class=\"score_i glyphicon glyphicon-user\">조회수" + json.list[i].cview
							+ "</span></i></p> </dd></dl>";
							idx++;
					} // for in
					if (json.list.length === 0) {
						values += "<div class=\"no-content\">없음</div>";
					}
					
					$("#ctoplist").html($("#ctoplist").html() + values);
				},
				error : function(jqXHR, textstatus, errorthrown) {
					console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
				}
			});
		});
    </script>
     <script type="text/javascript">
                    $(function(){
                    	
                    	$.ajax({
            				url: "/anavada/jtop3",
            				type: "get",
            				dataType: "json",
            				success : function(data) {
            					console.log("success : " + data);
            					// object ==> string으로 변환
            					var jsonStr = JSON.stringify(data);
            					// string ==> json 객채로 바꿈
            					var json = JSON.parse(jsonStr);
								var values ="";
								for (var i in json.list) {
        						values +="<li onclick=\"location.href='/anavada/jbdetail?jboardno="+ json.list[i].jboardno +"'\">"
                                +"<div><img src=/anavada/resources/jboardfiles/"+ json.list[i].pic+">"
                                +"</div>"
                                +"<h3>"+decodeURIComponent(json.list[i].title).replace(/\+/gi, " ")
                                +"</h3>"
                                +"<h4>"+json.list[i].price+ "<span>원</span></h4>"
                                +"<p><p><i class=\"good_i glyphicon glyphicon-heart-empty\">좋아요<span>" +json.list[i].like+"</span></i></p>"
                            	+"</li>";
        					} // for in

                    	$("#jtoplist").html($("#jtoplist").html() + values);
        				},
        				error: function(jqXHR, textstatus, errorthrown) {
        					console.log("error : " + jqXHR + ", " + textstatus + ", "+ errorthrown);
        				}
        		});
        	
        });
</script>
<!-- 공지사항 ajax -->
<script type="text/javascript">
	$.ajax({
		url: "/anavada/anTop3",
        type: "get",
        dataType: "json",
        success: function(data) {
				var jsonStr = JSON.stringify(data);
        		var json = JSON.parse(jsonStr);
        			
        		var values = "<li><h2><span>[공지]</span> 'Anavada' 조회수가 높은 공지사항 3개입니다!</h2></li>";
        		for(var i in json.list){
        			values += "<li><h2 onclick='movePage("+ json.list[i].no +");'>"+ json.list[i].title +"</h2>";
        			values += "<div>조회수 : "+ json.list[i].count +"</div></li>";
        		}
        		$("#topNotice").html(values);console.log(values);
        		},
        		error: function(jqXHR, textStatus, errorThrown){
        			console.log("error : "+jqXHR+", "+textStatus+", "+errorThrown);
        		}
	});
        	
    $.ajax({
        url: "/anavada/anRecent3",
        type: "get",
        dataType: "json",
        success: function(data) {
        		var jsonStr = JSON.stringify(data);
        		var json = JSON.parse(jsonStr);
        			
        		var values = "<li><h2><span>[공지]</span> 'Anavada' 최근에 등록한 공지사항 3개입니다!</h2></li>";
        		for(var i in json.list){
        			values += "<li><h2 onclick='movePage("+ json.list[i].no +");'>"+ json.list[i].title +"</h2>";
        			values += "<div>조회수 : "+ json.list[i].count +"</div></li>";
        		}
        		$("#recentNotice").html(values);console.log(values);
        		},
        		error: function(jqXHR, textStatus, errorThrown){
        			console.log("error : "+jqXHR+", "+textStatus+", "+errorThrown);
        		}
	});

function movePage(num){
	location.href="/anavada/ndetail?no="+num;
}
</script>
<!-- 공지사항 ajax 끝 -->
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
    <div id="wrap">
        <%@ include file="../include/header.jsp" %>

        <!-- 비주얼영역 -->

        <div id="main_visual" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#main_visual" data-slide-to="0" class="active"></li>
                <li data-target="#main_visual" data-slide-to="1"></li>
            </ol>

            <div class="carousel-inner" role="listbox">
                <div class="item active" style="background-image: url(/anavada/resources/images/main/main_visual01.jpg);" > 
                <!-- <div class="item active" style="background-image: url(/anavada/resources/bannerfiles/20200817093704.jpg);" > -->
               
                    <div class="main_vText">
                        <h2>도심 속 우리의 연결고리</h2>
                        <p>'Anavada'와 함께하는 이웃거래와 지역축제의 활성화</p>
                        <a class="btn-primary" href="/anavada/fbklist" role="button">자세히보기</a>
                    </div>
                </div>

                <div class="item">
                    <div class="main_vText">
                        <h2>여러분, 우리와 함께해요!</h2>
                        <p>'Anavada'의 커뮤니티에서 다양한 정보와 소식을 공유</p>
                        <a class="btn-primary" href="#none" role="button">자세히보기</a>
                    </div>
                </div>

            </div>
            <a class="left carousel-control" href="#main_visual" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#main_visual" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
        <!-- 비주얼영역 끝 -->

		
        <div class="main_content">
        
        	<!-- 공지사항 -->
        	
            <div class="mainNotice_wrap">
	            <div id="mainNotice" class="mainNotice">
	                <ul class="Notice" id="topNotice"><!-- 
	                <li><h2><span>[공지]</span> 'Anavada' 조회수가 높은 공지사항 3개입니다!</h2></li>
	        		<li><h2 onclick='movePage();'>title1</h2><div>조회수 : </div></li>
	        		<li><h2 onclick='movePage();'>title2</h2><div>조회수 : </div></li>
	        		<li><h2 onclick='movePage();'>title3</h2><div>조회수 : </div></li> -->
	                </ul>
	            </div>
	            <div id="mainNoticeTop3" class="mainNotice">
	                <ul class="Notice" id="recentNotice"><!-- 
	                <li><h2><span>[공지]</span> 'Anavada' 최신순 높은 공지사항 3개입니다!</h2></li>
	        		<li><h2 onclick='movePage();'>최신순1</h2><div>조회수 : </div></li>
	        		<li><h2 onclick='movePage();'>최신순2</h2><div>조회수 : </div></li>
	        		<li><h2 onclick='movePage();'>최신순3</h2><div>조회수 : </div></li> -->
	                </ul>
	            </div>
            </div><!-- 공지사항 끝 -->
			
            <div>
                <div class="mianPdt">
                    <h2>지금 당장 만나는 중고거래!</h2>
                    <a  href="/anavada/jblist" class="more">더보기 +</a>
                    <ul class="Pdt_list" id= "jtoplist">
            			<!-- AJAX로 처리 -->
                    </ul>
                </div>

                <div class="mianCnt">
                    <h2>우리동네 커뮤니티!</h2>
                    <a href="/anavada/clistview?page=1&local=0" class="more">더보기 +</a>
                    <div class="Cnt_list" id="ctoplist">
                     	<!-- AJAX로 처리 -->
                    </div>
                </div>
            </div>

		<!-- 지역축제 top8 ajax -->
		 <script type="text/javascript">
		$(function(){
			$.ajax({
				url : "/anavada/fbtop8",
				type : "post",
				dataType : "json",
				success : function(data) {
					console.log("success : " + data);
					var jsonStr = JSON.stringify(data);
					var json = JSON.parse(jsonStr);
					var values = "";
					for (var i in json.list) {
						values += '<li><a href="/anavada/views/fboard/areaEvent_view.jsp?fboardno=' 
						+ + json.list[i].fboardNo + '&festivalEndDate=' +json.list[i].festivalEndDate
						+ '"><img src="' + json.list[i].thumbnail + '" /><h3>' + decodeURIComponent(json.list[i].festivalTitle).replace(/\+/gi, " ")
						+ '</h3></a></li>&nbsp;&nbsp;';
					} // for in
					
					$("#fbtop8list").html(values);
				},
				error : function(jqXHR, textstatus, errorthrown) {
					console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
				}
			});
		});
    </script>
	<!-- 지역축제 top8 ajax 끝 -->
                       
       <div class="mainEvent">
                <h2>함께 즐기는 지역축제</h2>
                <p>남녀노소 모두와 나누는 우리 지역만의 축제로!</p>
                <ul id="fbtop8list">
                    <!-- 지역축제 top8 들어갈 자리 -->
                </ul>    
            </div>            
        </div>


        <%@ include file="../include/footer.jsp" %> 
    </div>
</body>
</html>