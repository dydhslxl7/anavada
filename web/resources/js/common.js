
//즐겨찾기
$(document).ready(function() { 
    $('#favorite').on('click', function(e) { 
        var bookmarkURL = window.location.href; 
        var bookmarkTitle = document.title; 
        var triggerDefault = false; 
        
        if (window.sidebar && window.sidebar.addPanel) { 
            // Firefox version < 23 
            window.sidebar.addPanel(bookmarkTitle, bookmarkURL, '');
        } else if ((window.sidebar && (navigator.userAgent.toLowerCase().indexOf('firefox') > -1)) || (window.opera && window.print)) { 
            // Firefox version >= 23 and Opera Hotlist
            var $this = $(this); $this.attr('href', bookmarkURL); 
            $this.attr('title', bookmarkTitle); 
            $this.attr('rel', 'sidebar'); 
            $this.off(e); 
            triggerDefault = true; 
        } else if (window.external && ('AddFavorite' in window.external)) { 
            // IE Favorite 
            window.external.AddFavorite(bookmarkURL, bookmarkTitle);
        } else { 
            // WebKit - Safari/Chrome
            alert((navigator.userAgent.toLowerCase().indexOf('mac') != -1 ? 'Cmd' : 'Ctrl') + '+D 키를 눌러 즐겨찾기에 등록하실 수 있습니다.'); 
        } 
        return triggerDefault; 
    }); 
});




//헤더
$(document).ready(function() {
	$('#header-wrap .header > dd .gnb li > a').on('click',function(){
		$('.menuBtn').removeClass('on');
		$('#header').removeClass('active');
		$('.menuAll').slideUp(200);
		$('.menuAll').removeClass('on');
	});

	$('.menuBtn').on('click',function(){
		if($(this).hasClass('on')){

			$(this).removeClass('on');
			$('#header').removeClass('active');
			$('.menuAll').slideUp(200);
			$('.menuAll').removeClass('on');
		}else{
			$(this).addClass('on');
			$('#header').addClass('active');
			$('.menuAll').slideDown(200);
			$('.menuAll').addClass('on');
		}
	});

	$(window).on('scroll',function(){
		if($(this).scrollTop() > 10){
			$('#header').addClass('on');
			$('#header-mobile').addClass('on');
		}else{
			$('#header').removeClass('on');
			$('#header-mobile').removeClass('on');
		}
	});

    
    
});





// 서브 비주얼 //
$(document).ready(function() {
	$(".product-vagas").vegas({
    slides: [
			{ src: "/anavada/resources/images/content/product_visual.jpg" },
			{ src: "/anavada/resources/images/content/product_visual.jpg" }
		],
		//animation: 'kenburns',
		animation: 'random',
		delay: 7000,
		overlay: '/anavada/resources/images/btnIcn/overlay_01.png'
	});
	$(".community-vagas").vegas({
    slides: [
			{ src: "/anavada/resources/images/content/community_visual.jpg" },
			{ src: "/anavada/resources/images/content/community_visual.jpg" }
		],
		//animation: 'kenburns',
		animation: 'random',
		delay: 7000,
		overlay: '/anavada/resources/images/btnIcn/overlay_01.png'
	});
	$(".areaEvent-vagas").vegas({
    slides: [
			{ src: "/anavada/resources/images/content/areaEvent_visual.jpg" },
			{ src: "/anavada/resources/images/content/areaEvent_visual.jpg" }
		],
		//animation: 'kenburns',
		animation: 'random',
		delay: 7000,
		overlay: '/anavada/resources/images/btnIcn/overlay_01.png'
	});
	$(".notice-vagas").vegas({
    slides: [
			{ src: "/anavada/resources/images/content/notice_visual.jpg" },
			{ src: "/anavada/resources/images/content/notice_visual.jpg" }
		],
		//animation: 'kenburns',
		animation: 'random',
		delay: 7000,
		overlay: '/anavada/resources/images/btnIcn/overlay_01.png'
	});
	$(".mypage-vagas").vegas({
    slides: [
			{ src: "/anavada/resources/images/content/mypage_visual.jpg" },
			{ src: "/anavada/resources/images/content/mypage_visual.jpg" }
		],
		//animation: 'kenburns',
		animation: 'random',
		delay: 7000,
		overlay: '/anavada/resourcest/images/btnIcn/overlay_01.png'
	});
	$(".member-vagas").vegas({
    slides: [
			{ src: "/anavada/resources/images/content/member_visual.jpg" },
			{ src: "/anavada/resources/images/content/member_visual.jpg" }
		],
		//animation: 'kenburns',
		animation: 'random',
		delay: 7000,
		overlay: '/anavada/resources/images/btnIcn/overlay_01.png'
	});
});
