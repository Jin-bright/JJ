<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>

/**
#msg-container {
	width : 600px;
	height: 400px;
	overflow-y: scroll;
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
	width:500px;
	margin-right: 5px;
	resize: none;
	height: 5vh;
}

#btn-userList {
	margin: 10px 100px;
}
**/

</style>
<title>Insert title here</title>
</head>
<body>

<h1> 이것은 쪽지 </h1>
	<section id="chat-container" style="width:500px">	
		<h2> 쪽지  </h2>
		<h2> post방식도 전송되니  </h2>
		
		<div id="msg-container">
			<ul></ul>
		</div>
		
		<div id="msg-editor" class="editor" style="border: 1px solid black;">
			<form name="chatMsgFrm">
				<textarea style="width:500px" name="msg" id="msg" cols="30" rows="2" pattern="(.|\n)+"
					required></textarea>
				<button type="submit" id="send">Send</button>
			</form>
		</div>
	</section>
</body>
</html>