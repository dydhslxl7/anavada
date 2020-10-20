<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="member.model.vo.Member, java.util.ArrayList"%>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="title" content="Anavada" />
<meta name="description" content="Anavada 설명" />
<meta name="keywords" content="Anavada 키워드" />

<title>Anavada 관리자</title>

<link rel="stylesheet" type="text/css" href="/anavada/resources/admin/css/common.css">
<script src="/anavada/resources/admin/js/jquery-3.5.1.min.js"></script>
<script src="/anavada/resources/admin/js/member.js"></script>
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
<% ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("list"); %>