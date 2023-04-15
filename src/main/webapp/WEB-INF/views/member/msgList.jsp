<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myPage.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/msgList.css" />
<section class="container">
<jsp:include page="/WEB-INF/views/member/subMenu.jsp" />
	<div class="content_area">	
		<div id="msg-container">
			<h3>메시지</h3>
			<div>
			<table id="msg-wrap">
				<thead>
					<tr>
						<td colspan="4" style="border-style: none; float: left;"><button class="delete-btn" onclick="msgDelete();">삭제</button></td>
					</tr>
					<tr>
						<th id="check">
							<input type="checkbox" name="checkAll" id="checkAll" onchange="fnCheckAll()"/>
						</th>
						<th id="sen">보낸사람</th>
						<th id="tit">제목</th>
						<th id="reg">날짜</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty msgList}">
						<c:forEach items="${msgList}" var="msg">
							<tr id="msg-content">
								<td>
									<input type="checkbox" name="selectMsg" id="selectMsg" value ="${msg.messageNO}"/>
								</td>
								<td class="msg">${msg.messageSender}</td>
								<td class="msg">
									<button id="msgBtn" 
										data-receiver="${msg.messageReceiver}"
										data-sender="${msg.messageSender}"
										data-title="${msg.messageTitle}"
										data-content="${msg.messageContent}"
										data-reg-date="${msg.messageRegdate}">
										${msg.messageTitle}
									</button>
								</td>
								<td class="msg">${msg.messageRegdate}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty msgList}">
						<tr>
							<td colspan="4">도착한 메시지가 없습니다.</td>
						</tr>	
					</c:if>
				</tbody>
			</table>
			</div>
			<div id="pagebar">
				<span class="page">${pagebar}</span>
			</div>
		</div>
	</div>
	<!-- 읽는 폼 -->
	<div class="frmwrapper">
		<div class="readFrm">			
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
				<div class="btn_box">
					<input class="msgbt msgsubmit" id="msgsubmit" type="button" value="✔️답장하기" data-answer/>
					<span id="msgclose"> 취소 </span>			
				</div>
			</form>
		</div>
		<!-- 답장하기 폼 -->
		<div class="answerFrm">
			<form class="frmPopAn" name="answerFrm" action="${pageContext.request.contextPath}/chat/MessageMain" method="post">
				<h1 style="font-weight:900; margin : 0 auto; text-align:center; padding-bottom:10px "> MESSAGE </h1>
				<table id="msgTable" style= "margin-top : 0px;" >
					<tr>
						<th class="msgtg"> TO.🙆 </th>
						<td class="msgtd" ><input type="text" id="anwerReceiver" name="receiver" style="width:220px; line-height:20px" readonly > <!--  받는 사람  --> 	</td>
					</tr>
					<tr>
						<th  class="msgtg" > FROM.🙋‍♀️ </th>
						<td class="msgtd" ><input type="text" id="anwerSender" name="sender" value="${loginMember.memberId}"  style="width:220px;  line-height:20px" readonly>  <!--  보내는 사람  --></td>
					</tr>
					<tr>
						<th  class="msgtg" > 제목 </th>
						<td class="msgtd" ><input type="text" id="title" name="msgTitle"  style="width:220px;  line-height:20px" required></td>
					</tr>
					<tr>
						<th  class="msgtg" > 내용 </th>
						<td class="msgtd" ><textarea id="content" name="msgContent" style="width:220px" required></textarea></td>
					</tr>
				</table>
				<div class="btn_box">
					<input class="msgbt msgsubmit" id="msgAnswer" type="submit" value="✔️보내기">
					<span id="msgclose"> 취소 </span>	
				</div>		
			</form>
		</div>
	</div>			
</section>
<script>
/* 읽기 모달에 내용 전달 */
document.querySelectorAll("#msgBtn").forEach((btn) => {
	btn.onclick = (e) => {
		const frm = document.querySelector(".frmwrapper");
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
		
		frm.style.visibility = "visible";
		
	}
});

/* 쪽지 닫기 */
document.querySelectorAll("#msgclose").forEach((msg) => {
	msg.onclick = (e) => {
		const frm = document.querySelector(".frmwrapper");
		frm.style.visibility = "hidden";
	};
});

/* 답장하기 폼 */
document.querySelector("#msgsubmit").addEventListener('click', (e) => {
	document.querySelector(".readFrm").style.display = "none";
	document.querySelector(".answerFrm").style.display = "block";
});

/* 답장하기 */
document.answerFrm.addEventListener('submit', (e) => {
	e.preventDefault();
	
	
	
	if(confirm("답장을 보내시겠습니까?")){
		document.querySelector(".frmPopAn").submit();
	}
});

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

/* 메시지 삭제하기 */
const msgDelete = () => {
	if(confirm("해당 메시지를 삭제하시겠습니까?")){
		const msgs = document.querySelectorAll("#selectMsg");
		let msglist;
		for(let i = 0; i < msgs.length; i++){
			if(msgs[i].checked == true){
				msglist += "," + msgs[i].value;
			}
		}
		const delNo = document.querySelector("#delNo");
		delNo.value = msglist;
		document.msgDeleteFrm.submit();
	}
};

</script>
<form action="${pageContext.request.contextPath}/message/messageDelete" method="post" name="msgDeleteFrm">
	<input type="hidden" name="delNo" id="delNo"/>
</form>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />