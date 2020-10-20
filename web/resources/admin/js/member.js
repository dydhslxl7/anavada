//관리자 회원탈퇴//
$(function () {
	$("#leaveBtn").click(function() {
	var checkarr = []
		$("input[name=leaveChk]:checked").each(function() {
		   checkarr.push($(this).val());
		   console.log(checkarr);
		});
		// 배열 < 스트링 변환 < 자바에서 배열로 < 가공처리
		$.ajax({
			url:'/anavada/adminleave.ad',
			type: 'get',
			traditional: true,
			data: {
				checkarr : checkarr.join(',')
			},
			success : function(data){
				console.log("컨트롤러에서 받은 MSG : " + data);
				var dataSplit = data.split('/');
				if (dataSplit.length === 2) {
					alert(/*dataSplit[1] + */dataSplit[0] + '명의 회원이 탈퇴되었습니다.');
				location.reload();
				}
			},
			//Ajax 실패시 호출
			error : function(jqXHR, textStatus, errorThrown){
			console.log("jqXHR : " +jqXHR +"textStatus : " + textStatus + "errorThrown : " + errorThrown);
			}
		});
	});
});


//관리자 회원삭제//
$(function () {
	$("#deleteBtn").click(function() {
	var checkarr = []
		$("input[name=leaveChk]:checked").each(function() {
		   checkarr.push($(this).val());
		   console.log(checkarr);
		});
		// 배열 < 스트링 변환 < 자바에서 배열로 < 가공처리
		$.ajax({
			url:'/anavada/admindelete.ad',
			type: 'get',
			traditional: true,
			data: {
				checkarr : checkarr.join(',')
			},
			success : function(data){
				console.log("컨트롤러에서 받은 MSG : " + data);
				var dataSplit = data.split('/');
				if (dataSplit.length === 2) {
				alert(/*dataSplit[1] + */dataSplit[0] + '명의 회원이 삭제되었습니다.');
				location.reload();
				}
			},
			//Ajax 실패시 호출
			error : function(jqXHR, textStatus, errorThrown){
			console.log("jqXHR : " +jqXHR +"textStatus : " + textStatus + "errorThrown : " + errorThrown);
			}
		});
	});
});