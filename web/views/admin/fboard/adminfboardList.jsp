<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <%@ include file="../include/admin_head.jsp" %> 
	<script type="text/javascript">
	$(function(){
		$.ajax({
			url : "/anavada/fapi.ad",
			type : "get",
			dataType : "json",
			success : function(data){
				console.log("api 가지고 오기 success : " + data);

				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);

				var values = "";
				var totalcount = 0;
				for(var i in json.item) {
					values += "<tr><td>" + "<img src='"+ json.item[i].firstimage2 + "' width='150' height='100' alt='No Image'></td><td>" + json.item[i].contentid + "</td><td>" 
					+ json.item[i].title + "</td><td>" + json.item[i].eventstartdate + " ~ " + json.item[i].eventenddate + "</td><td>" + json.item[i].modifiedtime + "</td></tr>";
					totalcount += 1;
				}	//for in
				$("#api").html(values);	//테이블에 추가하는거 (덮어쓰기 말고) */
				$("#totalcounta").text("api 축제 총 개수 " + totalcount) ;
			},
			error : function(jqXHR, textstatus, errorthrown){
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
			}
		});	//ajax
		
		$.ajax({
			url : "/anavada/fblist.ad",
			type : "get",
			dataType : "json",
			success : function(data){
				console.log("fboard 가지고 오기 success : " + data);
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);
				
				var values = "";
				var totalcount = 0;
				for(var i in json.list) {
					values += "<tr><td>" + "<img src='"+ json.list[i].thumbnail + "' alt='No Image' width='150' height='100'></td><td>" + json.list[i].fboardNo + "</td><td>" 
					+ decodeURIComponent(json.list[i].festivalTitle).replace(/\+/gi, " ") + "</td><td>" + json.list[i].festivalStartDate + " ~ " + json.list[i].festivalEndDate + "</td><td>"
					+ json.list[i].fesivalModifiedDate + "</td></tr>";
					totalcount += 1;
				}	//for in
				$("#fboard").html(values);	//테이블에 추가하는거 (덮어쓰기 말고) */
				$("#totalcountb").text("fboard 축제 총 개수 " + totalcount) ;
			},
			error : function(jqXHR, textstatus, errorthrown){
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
			}
		});	//ajax
		
	});	//document.ready
	</script>
</head>
<body>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
<div id="wrap">
    <%@ include file="../include/admin_header.jsp" %> 

    <div id="admin_container">

        <!-- 상단 타이틀 -->
        <div class="admin_title">
            <div class="admin_path">
                <h3>지역축제 게시판</h3>
                <h2>관리</h2>
            </div>
        </div>
        <!-- //상단 타이틀 -->

        <!-- 본문내용 ---------------------------------------------------------------------------------------------------------------------------------------------------->
        <div class="admin_content" style="width: 1400px;">

                <!-- 글쓰기 -->
                <form action="/anavada/binsert.ad"  method="post" enctype="multipart/form-data" class="form-inline" onesubmit="return validate();">
                    <fieldset>
                        <div class="faqTap">
							<a href="/anavada/fbinsert.ad?memberid=<%= loginMember.getMemberId()%>">[축제 DB에 insert]</a>
							<a href="/anavada/fbupdate.ad">[축제 DB에 update]</a>
							<a href="/anavada/fbdelete.ad">[이전 축제 DB에서 삭제하기]</a>
							<a href="/anavada/fbdeleteall.ad">[축제 DB에서 전부 삭제하기]</a>
						</div>
						
						<script type="text/javascript">
						/* api에서 제공하는 리스트 가지고 오기 */
						$(function(){
							$.ajax({
								url : "/anavada/fapi.ad",
								type : "get",
								dataType : "json",
								success : function(data){
									console.log("api 가지고 오기 success : " + data);
			
									var jsonStr = JSON.stringify(data);
									var json = JSON.parse(jsonStr);
			
									var values = "";
									var totalcount = 0;
									for(var i in json.item) {
										values += "<tr><td>" + "<img src='"+ json.item[i].firstimage2 + "' width='150' height='100' alt='No Image'></td><td>" + json.item[i].contentid + "</td><td>" 
										+ json.item[i].title + "</td><td>" + json.item[i].eventstartdate + " ~ " + json.item[i].eventenddate + "</td><td>" + json.item[i].modifiedtime + "</td></tr>";
										totalcount += 1;
									}	//for in
									$("#api").html(values);	//테이블에 추가하는거 (덮어쓰기 말고) */
									$("#totalcounta").text("api 축제 총 개수 " + totalcount) ;
								},
								error : function(jqXHR, textstatus, errorthrown){
									console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
								}
							});	//ajax
							
							/* DB에 저장된 fboard 리스트 가지고 오기 */
							$.ajax({
								url : "/anavada/fblist.ad",
								type : "get",
								dataType : "json",
								success : function(data){
									console.log("fboard 가지고 오기 success : " + data);
									var jsonStr = JSON.stringify(data);
									var json = JSON.parse(jsonStr);
									
									var values = "";
									var totalcount = 0;
									for(var i in json.list) {
										values += "<tr><td>" + "<img src='"+ json.list[i].thumbnail + "' alt='No Image' width='150' height='100'></td><td>" + json.list[i].fboardNo + "</td><td>" 
										+ decodeURIComponent(json.list[i].festivalTitle).replace(/\+/gi, " ") + "</td><td>" + json.list[i].festivalStartDate + " ~ " + json.list[i].festivalEndDate + "</td><td>"
										+ json.list[i].fesivalModifiedDate + "</td></tr>";
										totalcount += 1;
									}	//for in
									$("#fboard").html(values);	//테이블에 추가하는거 (덮어쓰기 말고) */
									$("#totalcountb").text("fboard 축제 총 개수 " + totalcount) ;
								},
								error : function(jqXHR, textstatus, errorthrown){
									console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
								}
							});	//ajax
						});	//document.ready
						</script>
						
						<div class="Comparisondiv">
							<div class="apidiv">
								<h2 id="totalcounta"></h2>
								<table id="api" border="1" cellspacing="0">
								</table>
							</div>
		
							<div class="fboarddiv">
								<h2 id="totalcountb"></h2>
								<table id="fboard" border="1" cellspacing="0">
								</table>
							</div>						
                        </div>
                    </fieldset>
                <!-- //글쓰기 -->

                                var jsonStr = JSON.stringify(data);
                                var json = JSON.parse(jsonStr);

                                var values = "";
                                var totalcount = 0;
                                for(var i in json.item) {
                                    values += "<tr><td>" + "<img src='"+ json.item[i].firstimage2 + "' width='150' height='100' alt='No Image'></td><td>" + json.item[i].contentid + "</td><td>" 
                                    + json.item[i].title + "</td><td>" + json.item[i].eventstartdate + " ~ " + json.item[i].eventenddate + "</td><td>" + json.item[i].modifiedtime + "</td></tr>";
                                    totalcount += 1;
                                }	//for in
                                $("#api").html(values);	//테이블에 추가하는거 (덮어쓰기 말고) */
                                $("#totalcounta").text("api 축제 총 개수 " + totalcount) ;
                            },
                            error : function(jqXHR, textstatus, errorthrown){
                                console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
                            }
                        });	//ajax

                        /* DB에 저장된 fboard 리스트 가지고 오기 */
                        $.ajax({
                            url : "/anavada/fblist.ad",
                            type : "get",
                            dataType : "json",
                            success : function(data){
                                console.log("fboard 가지고 오기 success : " + data);
                                var jsonStr = JSON.stringify(data);
                                var json = JSON.parse(jsonStr);

                                var values = "";
                                var totalcount = 0;
                                for(var i in json.list) {
                                    values += "<tr><td>" + "<img src='"+ json.list[i].thumbnail + "' alt='No Image' width='150' height='100'></td><td>" + json.list[i].fboardNo + "</td><td>" 
                                    + decodeURIComponent(json.list[i].festivalTitle).replace(/\+/gi, " ") + "</td><td>" + json.list[i].festivalStartDate + " ~ " + json.list[i].festivalEndDate + "</td><td>"
                                    + json.list[i].fesivalModifiedDate + "</td></tr>";
                                    totalcount += 1;
                                }	//for in
                                $("#fboard").html(values);	//테이블에 추가하는거 (덮어쓰기 말고) */
                                $("#totalcountb").text("fboard 축제 총 개수 " + totalcount) ;
                            },
                            error : function(jqXHR, textstatus, errorthrown){
                                console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
                            }
                        });	//ajax
                    });	//document.ready
                    </script>

                    <div class="Comparisondiv">
                        <div class="apidiv">
                            <h2 id="totalcounta"></h2>
                            <table id="api" border="1" cellspacing="0">
                            </table>
                        </div>

                        <div class="fboarddiv">
                            <h2 id="totalcountb"></h2>
                            <table id="fboard" border="1" cellspacing="0">
                            </table>
                        </div>
                    </div>
                </fieldset>
            <!-- //글쓰기 -->

        </form>
        </div>
    <%@ include file="../include/admin_footer.jsp" %> 
    </div>
</div>
</body>
</html>