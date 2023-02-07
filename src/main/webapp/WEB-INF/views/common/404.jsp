<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%


	// error-code로 넘어온 경우는 exception객체 null이다.
	// isErrorPage="true"설정을 통해 던져진 예외객체에 선언없이 접근이 가능함.
	// String msg = exception.getMessage();
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
</style>
</head>
<body>
	<h1>텅</h1>
	<p id="msg">찾으시는 페이지가 존재하지 않습니다.</p>
	<p>
		<a href="<%= request.getContextPath() %>">홈으로</a>
	</p>



</body>
</html>