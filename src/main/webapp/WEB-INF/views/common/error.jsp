<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>    
    
<%
	// isErrorPage="true"설정을 통해 던져진 예외객체에 선언없이 접근이 가능함.
	//String msg = exception.getMessage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커스텀 예외페이지</title>

<style>
body {text-align: center;}
h1 	 {font-size: 250px; margin: 0;}
#msg {color : red;}

#errora{
	text-decoration : underline;
}

#errora:hover{
	font-size : 18px;
	background-color: #f9f4d2;
}


</style>
</head>
<body>
<br /><br /><br /><br /><br /><br />
	<img src="<%=request.getContextPath()%>/image/error-icon.png" alt="error" />
	<h2> 이용에 불편을 드려 죄송합니다. </h2> 
	  <br /><br /><br /><br />
<!-- 	<p>이용에 불편을 드려 죄송합니다.</p>
		<p id="msg"><%= msg %></p>  -->
	<p>
		<a id="errora" href="<%= request.getContextPath() %>" style="font-weight:900"> 홈으로 돌아가기 </a>
	</p>


<br /><br /><br /><br /><br /><br />
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
