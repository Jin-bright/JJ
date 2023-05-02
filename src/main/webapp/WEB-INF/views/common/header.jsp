<%@ page import="com.sh.obtg.member.model.dto.Member" %>
<%@ page import="com.sh.obtg.member.model.dto.MemberRole" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	Member loginMember=(Member) session.getAttribute("loginMember");
	String msg = (String)session.getAttribute("msg");
	System.out.println(msg);
	if(msg != null) session.removeAttribute("msg");
%>
<!doctype html>

<head>
<meta charset="UTF-8" />

<meta name="viewport" content="width=device-width, initial-scale=1">

<title>OBTG Semi-Project</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" /> 
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css">
<script src="https://unpkg.com/swiper/swiper-bundle.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css" />
<script src="${pageContext.request.contextPath}/js/jquery-3.6.1.js"></script>

<style>
.menu__text:hover{
	font-weight: bolder;
}
</style>

</head>
<body>
<header style="hight:263.67px;">
<!--   메뉴바 -->
<table id="login_container">
	<c:if test="${loginMember == null}">
		<tr>
			<td><button id="loginSignup" value="로그인/회원가입" onclick="location.href = '${pageContext.request.contextPath}/member/login';"> LOGIN / SIGNUP </button></td>
		</tr>
	</c:if>
	<c:if test="${loginMember != null}">
		<tr class="login_nav">
			<td onclick="location.href = '${pageContext.request.contextPath}/member/myPage';">MY</td>
			<c:if test="${loginMember.memberRole == MemberRole.A}">
				<td onclick="location.href = '${pageContext.request.contextPath}/admin/memberList';">MANAGER</td>
			</c:if>
			<td onclick="location.href = '${pageContext.request.contextPath}/member/logout';">LOGOUT</td>
		</tr>
	</c:if>
</table>

<br /><br /><br />

<!-- <hr style="border: solid 1px black; margin:0;"> -->
<nav class="menu">
	<h1  class="main-title"  >O B T G</h1>
	<a class="menu__item" id="i-0" href="${pageContext.request.contextPath}/index.jsp"><span class="menu__text"><b>HOME</b></span></a>	
	<a class="menu__item" id="i-1" href="${pageContext.request.contextPath}/ootd/newOotdWholeList"><span class="menu__text">OOTD</span></a>
	<a class="menu__item" id="i-2" href="${pageContext.request.contextPath}/share/newShareWholeList"><span id="share" class="menu__text">SHARE</span></a>	
	<a class="menu__item" id="i-4" href="${pageContext.request.contextPath}/column/columnList"><span class="menu__text">COLUMN</span></a>
  <div id="active"></div>
  <div id="active-2"></div>
  <div id="active-3"></div>
</nav>

<br /> <br />
<hr style="border: solid 1px black; margin:0;">
</header>
<script>
window.addEventListener('load', () => {
	<% if(msg != null){ %>
		alert("<%= msg %>");
	<%} %>
});

const item = document.querySelectorAll(".menu__item");



const icon = document.querySelectorAll(".menu__icon");
const text = document.querySelectorAll(".menu__text");
const active = document.querySelector("#active");
const active2 = document.querySelector("#active-2");
const active3 = document.querySelector("#active-3");
//let colors = ["#c6a700","#c25e00", "#b91400","#5c007a" ,"black"];

let colors = ["black"];



let getIcon = (event) =>{
    for (var i = 0; i < icon.length; i++) {
        icon[i].classList.remove("is-icon-visible");
    }
    for (var i = 0; i < text.length; i++) {
        text[i].classList.remove("is-text-visible");
    }
}

</script>
