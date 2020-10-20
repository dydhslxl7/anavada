<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="member.model.vo.Member"%>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="title" content="Anavada" />
<meta name="description" content="Anavada 설명" />
<meta name="keywords" content="Anavada 키워드" />

<title>Anavada</title>

<link href="/anavada/resources/css/common.css" rel="stylesheet">

<script src="/anavada/resources/js/jquery-1.12.4.js"></script>
<script src="/anavada/resources/js/bootstrap.js"></script>
<script src="/anavada/resources/js/common.js"></script>
<script src="/anavada/resources/js/vegas.js"></script>
<script src="/anavada/resources/js/swiper.min.js"></script>
<script src="/anavada/resources/js/mainNotice.js"></script>
<script src="/anavada/resources/js/member.js"></script>

<!--티스토리 불펌금지-->
<script type="text/javascript">
    var omitformtags=["input", "textarea", "select"]
    omitformtags=omitformtags.join("|")
    function disableselect(e){
    if (omitformtags.indexOf(e.target.tagName.toLowerCase())==-1)
        return false
    }
    function reEnable(){
        return true
    }
    if (typeof document.onselectstart!="undefined")
        document.onselectstart=new Function ("return false")
    else{
        document.onmousedown=disableselect
        document.onmouseup=reEnable
    }
</script>
<% Member loginMember = (Member)session.getAttribute("loginMember"); %>
<% Member member = (Member)request.getAttribute("member"); %>