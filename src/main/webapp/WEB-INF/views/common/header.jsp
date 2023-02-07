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

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" /> 
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css">
<script src="https://unpkg.com/swiper/swiper-bundle.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/index.css" />
<script src="<%=request.getContextPath()%>/js/jquery-3.6.1.js"></script>
<% if(loginMember != null){ %>
<script src="<%= request.getContextPath() %>/js/ws.js"></script>
<% } %>
</head>
<body>
<header style="hight:263.67px;">
<!--   메뉴바 -->
<br /> <br /><br />
<table id="tdloginSignup">
<% if(loginMember == null) { %>
 <tr>
 	<td><button id="loginSignup" value="로그인/회원가입" onclick="location.href = '${pageContext.request.contextPath}/member/login';"> LOGIN / SIGNUP </button></td>
 </tr>	
 
</table>
<% } else { %>

				 <table id="login" style="margin-left:50%; text-align:right;">
					<tr>
						<td>
						<nav id="colorNav">
    						<ul>
       						 <li class="green">
            					<a href="#"><img id="defaultimg" src="<%=request.getContextPath()%>/image/default.png" alt="defaultimg" style="width:30px; height:30px;"/></a>
 							<% if(loginMember.getMemberRole() == MemberRole.A) { %>
            						<ul>
						                <li><a href="<%= request.getContextPath() %>/admin/memberList;">관리자페이지</a></li>
						                <li><a href="<%= request.getContextPath() %>/member/logout;">로그아웃</a></li>
							            </ul>
							        </li>
							    </ul>
							</nav>
 							 <%--<ul class="dd-menu">
	      						<li><a href="<%= request.getContextPath() %>/admin/memberList;">관리자페이지</a></li>
	      						<li><a href="<%= request.getContextPath() %>/member/logout;">로그아웃</a></li>
	    					</ul>--%>
 							<% } else{ %>
							<ul class="dd-menu">
	      						<li><a href="<%= request.getContextPath() %>/member/memberView;">My Page</a></li>
	      						<li><a href="<%= request.getContextPath() %>/member/logout;">로그아웃</a></li>
	    					</ul>
							<%} %>
							<%= loginMember.getNickname() %>님
							<i style="position: absolute;"><img src="<%= request.getContextPath() %>/image/notification.png" alt="알림" class="bell bell-hiden" /></i>
							<div id="report_wrap"></div>
						</td>
					</tr>
				</table> 
			
			<% } %>

		<%--		<h1  class="main-title"  style="margin : 0 auto ">O B T G</h1> --%>

<br />
<!-- <hr style="border: solid 1px black; margin:0;"> -->
<nav class="menu">
	<h1  class="main-title"  >O B T G</h1>
	<a class="menu__item" id="i-0" href="<%=request.getContextPath()%>/index.jsp"><span class="menu__text"><b>HOME</b></span></a>	
	<a class="menu__item" id="i-1" href="<%=request.getContextPath()%>/ootd/ootdWholeList"><span class="menu__text">OOTD</span></a>
	<a class="menu__item" id="i-2" href="<%=request.getContextPath()%>/share/shareWholeList"><span class="menu__text">SHARES</span></a>
	<a class="menu__item" id="i-4" href="<%= request.getContextPath() %>/column/columnList"><span class="menu__text">COLUMN</span></a>
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

/* */
// window.addEventListener("load", mainFunc);


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
