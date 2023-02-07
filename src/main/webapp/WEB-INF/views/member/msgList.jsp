<%@page import="com.sh.obtg.message.model.dto.Message"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Message> messages = (List<Message>)request.getAttribute("msgList");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/msgList.css" />
<script type="text/javascript" src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
<div class="main">
	<div class="container-menu">
		<ul>
			<div class="container-li">
				<a href="<%= request.getContextPath()%>/member/memberView">
					<img src="<%= request.getContextPath()%>/image/modify.png" alt="" />
					<li>&nbsp;Info</li>
				</a>
			</div>
			<div class="container-li">
				<a href="<%= request.getContextPath() %>/member/memberBoardList">
					<img src="<%= request.getContextPath()%>/image/list.png" alt="" />
					<li>&nbsp;List</li>
				</a>
			</div>
			<div class="container-li">
				<a href="<%= request.getContextPath() %>/message/messageList">
					<img src="<%= request.getContextPath()%>/image/chat.png" alt="" />
					<li>&nbsp;Message</li>
				</a>
			</div>
			<div class="container-li">
				<a href="<%= request.getContextPath() %>/member/memberOotdLike">
					<img src="<%= request.getContextPath()%>/image/like.png" alt="" />
					<li>&nbsp;Like</li>
				</a>
			</div>
		</ul>
	</div>
	<hr />
	<section id="msg-container">
		<h2>나에게 온 쪽지</h2>
		<table id="msg-wrap">
			<thead>
				<tr>
					<td colspan="4" style="border-style: none; float: left;"><button onclick="msgDelete();">삭제</button></td>
				</tr>
				<tr>
					<th id="check">
						<!-- <input type="checkbox" name="checkAll" id="checkAll" onchange="fnCheckAll()"/> -->
					</th>
					<th id="sen">보낸사람</th>
					<th id="tit">제목</th>
					<th id="reg">날짜</th>
				</tr>
			</thead>
			<tbody>
				<% if(messages.size() > 0){
					for(Message message : messages){
				%>
				<tr id="msg-content">
					<td>
						<input type="checkbox" name="selectMsg" id="selectMsg" value = <%= message.getMessageNO() %> onclick="checkOnlyOne(this)"/>
					</td>
					<td class="msg"><%= message.getMessageSender() %></td>
					<td class="msg">
						<button id="msgBtn" 
							data-receiver=<%= message.getMessageReceiver() %>
							data-sender=<%= message.getMessageSender() %>
							data-title=<%= message.getMessageTitle() %>
							data-content=<%= message.getMessageContent() %>
							data-reg-date=<%= message.getMessageRegdate() %>>
							<%= message.getMessageTitle() %>
						</button>
					</td>
					<td class="msg"><%= message.getMessageRegdate() %></td>
				</tr>
				<% 	} 
			  	 }else{
				%>
				<tr>
					<td colspan="4">도착한 쪽지가 없어요!</td>
				</tr>	
				<% } %>
			</tbody>
		</table>
		<div id="pagebar">
			<%= request.getAttribute("pagebar") %>
		</div>
	</section>
</div>
<!-- 읽는 폼 -->
<div class="frmwrapper">			
	<form class="frmPopRe">
		<h1 style="font-weight:900; margin : 0 auto; text-align:center; padding-bottom:10px "> MESSAGE </h1>
		<table id="msgTable" style= "margin-top : 0px;" >
			<tr>
				<th class="msgtg"> TO.🙆 </th>
				<td class="msgtd" ><input type="text" id="receiver" style="width:220px; line-height:20px" readonly > <!--  받는 사람  --> 	</td>
			</tr>
		
			<tr>
				<th  class="msgtg" > FROM.🙋‍♀️ </th>
				<td class="msgtd" ><input type="text" id="sender" style="width:220px;  line-height:20px" readonly>  <!--  보내는 사람  --></td>
			</tr>
			<tr>
				<th  class="msgtg" > 제목 </th>
				<td class="msgtd" ><input type="text" id="msgTitle" style="width:220px;  line-height:20px" readonly></td>
			</tr>
			<tr>
				<th  class="msgtg" > 내용 </th>
				<td class="msgtd" ><textarea id="msgContent" style="width:220px" readonly></textarea></td>
			</tr>
		</table>
		<input class="msgbt" id="msgsubmit" type="button" value="✔️답장하기" data-answer/>
		<span id="msgclose" onclick="closeMsg();"> 취소 </span>			
	</form>
</div>			
<!-- 답장하는 폼 -->
<div class="frmwrapper">			
	<form class="frmPopAn" name="answerFrm" action="<%= request.getContextPath() %>/chat/MessageMain" method="post">
		<h1 style="font-weight:900; margin : 0 auto; text-align:center; padding-bottom:10px "> MESSAGE </h1>
		<table id="msgTable" style= "margin-top : 0px;" >
			<tr>
				<th class="msgtg"> TO.🙆 </th>
				<td class="msgtd" ><input type="text" id="anwerReceiver" name="receiver" style="width:220px; line-height:20px" readonly > <!--  받는 사람  --> 	</td>
			</tr>
			<tr>
				<th  class="msgtg" > FROM.🙋‍♀️ </th>
				<td class="msgtd" ><input type="text" id="anwerSender" name="sender" value="<%= loginMember.getMemberId() %>"  style="width:220px;  line-height:20px" readonly>  <!--  보내는 사람  --></td>
			</tr>
			<tr>
				<th  class="msgtg" > 제목 </th>
				<td class="msgtd" ><input type="text" id="title" name="msgTitle"  style="width:220px;  line-height:20px" ></td>
			</tr>
			<tr>
				<th  class="msgtg" > 내용 </th>
				<td class="msgtd" ><textarea id="content" name="msgContent" style="width:220px" required></textarea></td>
			</tr>
		</table>
		<input class="msgbt"  id="msgsubmit" type="submit" value="✔️보내기"   >
		<span id="msgclose" onclick="closeAns();"> 취소 </span>			
	</form>
</div>
<script>
document.querySelectorAll("#msgBtn").forEach((btn) => {
	btn.onclick = (e) => {
		const frm = document.querySelector(".frmPopRe");
		const receiver = document.querySelector("#receiver")
		const sender = document.querySelector("#sender")
		const msgTitle = document.querySelector("#msgTitle")
		const msgContent = document.querySelector("#msgContent")
		/* const msgDate = document.querySelector("#msgDate") */
		const anReceiver = document.querySelector("#anwerReceiver");
		
		receiver.value = e.target.dataset.receiver;
		sender.value = e.target.dataset.sender;
		msgTitle.value = e.target.dataset.title;
		msgContent.value = e.target.dataset.content;
		anReceiver.value = e.target.dataset.sender;
		
		e.target.answer = e.target.dataset.sender;
		console.log(e.target.answer);
		
		frm.classList.toggle("showPopRe");
		
	}
});

document.querySelector("#msgsubmit").addEventListener('click', (e) => {
	const answerFrm = document.querySelector(".frmPopAn");
	const readFrm = document.querySelector(".frmPopRe");
	
	readFrm.classList.toggle("showPopRe");
	answerFrm.classList.toggle("showPopAn");
	
});

document.answerFrm.addEventListener('submit', (e) => {
	e.preventDefault();
	if(confirm("답장을 보내시겠습니까?")){
		document.querySelector(".frmPopAn").submit();
	}
});

/* 모달창 움직이기(약간 허접ㅎ,,,) */
$(function(){
	$('.frmPopRe').draggable({'cancel':'#msgTable'});
});
$(function(){
	$('.frmPopRe').draggable({'cancel':'#msgTable'});
});

const closeMsg = () => {
	const frm = document.querySelector(".frmPopRe");
	frm.classList.toggle("showPopRe");
}

const closeAns = () => {
	const frm = document.querySelector(".frmPopAn");
	frm.classList.toggle("showPopAn");
}

/* 체크박스 제어 */
function fnCheckAll(){
    const msgs = document.querySelectorAll("#selectMsg");
    console.log(msgs);

    const checkAll = document.getElementById("checkAll");

    for(let i = 0; i < msgs.length; i++){
        const msg = msgs[i];
        msg.checked = checkAll.checked;
    }
}


const msgDelete = () => {
    if(confirm("해당 쪽지를 삭제하시겠습니까?")){
         const msgs = document.querySelectorAll("#selectMsg");
        for(let i = 0; i < msgs.length; i++){
               if(msgs[i].checked){
                   const delNo = document.querySelector("#delNo");
                   delNo.value = msgs[i].value;
                document.msgDeleteFrm.submit();
               }
        } 
    }
};

const checkOnlyOne = (e) => {
    const checkbox = document.getElementsByName("selectMsg");

    checkbox.forEach((cb) => {
        cb.checked = false;
    })

    e.checked = true;
} 

</script>
<form action="<%= request.getContextPath() %>/message/messageDelete" method="post" name="msgDeleteFrm">
	<input type="hidden" name="delNo" id="delNo"/>
</form>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>