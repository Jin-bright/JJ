<%@page import="com.sh.obtg.share.model.dto.ShareBoard"%>
<%@page import="com.sh.obtg.share.model.dto.ShareBoardEntity"%>
<%@page import="com.sh.obtg.share.model.dto.ShareAttachment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Noto+Sans+KR:wght@900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/shareView.css" />
<%
	ShareBoard shareBoard = (ShareBoard)request.getAttribute("shareBoard");
	List<ShareAttachment> shareAttachments = (List<ShareAttachment>)request.getAttribute("shareAttachments");
// ★★★★★★★★ 이걸로 난중에닉네임빼기
    Member membmer = (Member) request.getAttribute("membmer");
//	Member loginMember = (Member) session.getAttribute("loginMember"); //object -> member  			
	String chatroomId = (String)session.getAttribute("chatroomId");	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#chat-container {
	border:1px solid black;
	width:600px;
	margin: 0 auto;
	padding: 10px;
	text-align: center;
}

#msg-container {
	paddig : 20px;
	margin : auto;
	width: 400px;
	height: 400px;
	overflow-y: scroll;
	border : solid 1px red;
}

#msg-container ul {
	list-style-type: none;
}

#msg-container ul li {
	margin: 3px 0;
}

#msg-container span.badge {
	border: 1px solid #000;
	padding: 2px 10px 5px;
    border-radius: 7px;
    margin-right: 10px;
}

.left {
	display: flex;
	align-items: flex-start;
}

.line {
	display: flex;
	align-items: center;
	color: rgba(0, 0, 0, 0.35);
	font-size: 14px;
	margin: 8px 0px;
}

.line::before, .line::after {
	content: "";
	flex-grow: 1;
	margin: 0px 16px;
	background: rgba(0, 0, 0, 0.35);
	height: 1px;
	font-size: 0px;
	line-height: 0px;
}

.editor form {
	display: flex;
	flex-direction: row;
	justify-content: center;
}

.editor form textarea {
	width: 100%;
	margin:0 auto;
	margin-left : 100px;
	resize: none;
	height: 5vh;
	width: 400px;
}

#btn-userList {
	margin:0 auto;
	margin-left : 430px;
}

</style>
</head>
<body>
<h1>채팅죽일까</h1>
	<h2> 채팅방ID :  <%=chatroomId%></h2>
	<br /><br /><br /><br /><br />	
	<section id="chat-container">	
		<h2><%=shareBoard.getMemberId()%>님과의 채팅방</h2>
		<div id="msg-container">
			<ul></ul>
		</div>
		
		<div  id="msg-editor" class="editor">
			<form name="chatMsgFrm">
				<textarea name="msg" id="msg" cols="30" rows="2" pattern="(.|\n)+"	required></textarea>
				<button type="submit" id="send">Send</button>
			</form>
		</div>
	</section>
	<br /><br /><br /><br /><br />	
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>