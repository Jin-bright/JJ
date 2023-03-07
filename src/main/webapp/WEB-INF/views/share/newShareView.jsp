<%@page import="com.sh.obtg.share.model.dto.NshareBoard"%>
<%@page import="com.sh.obtg.member.model.dto.MemberRole"%>
<%@page import="com.sh.obtg.member.model.dto.Member"%>
<%@page import="com.sh.obtg.share.model.dto.NshareAttachment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>        
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ taglib uri ="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shareView.css" /> 
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@100;400;700&family=Noto+Sans+KR:wght@900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<%
	Member loginMember = (Member) session.getAttribute("loginMember"); 
	int likeCnt = (int)request.getAttribute("likeCnt");
	NshareBoard shareBoard =  (NshareBoard)request.getAttribute("shareBoard"); 
%>


<section>

	<div id="pictureDiv">
		<img id="fiximg" src="${pageContext.request.contextPath}/uploadshares/newShare/${shareBoard.shareAttachments[0].renamedFilename}" alt="img" />
	</div>

	<hr style="height : 700px; margin-top:90px; border : 1px solid lightgray"/>


	<div id="contentDiv" >
		<div id="con">
			<img  src="${pageContext.request.contextPath}/uploadshares/carrot.png" alt="carrot" style=" width:16px; height:16px; margin-left: 630px" />
			<h4>${shareBoard.getProductStatus()}</h4>	
			  <br/><br />
			<h1> ${shareBoard.getProductName()}</h1><br /><br />
			<table id="contTable">
				<tbody>
				<tr>
					<td>  상태 </td>
					<td class="valss">${ shareBoard.getProductQuality()}</td>
				</tr>
				<tr>
					<td>  가격</td>
					<td  class="valss" style="font-size: 25px; font-weight: 700"><fmt:formatNumber value="${shareBoard.getProductPrice()}" pattern="#,###" /> 원</td>
				</tr>
				</tbody>
			</table>
			<hr style="width : 700px; margin-top:10px; border : 1px solid lightgray" />
		</div>
		<!-- 버튼들  -->
		<div class = "buttonss">
			<% if(loginMember == null || likeCnt == 0) { %>
				<button id="likesbt"><img src="${pageContext.request.contextPath}/image/heart.png" class="shareLike" id="heartsempty" alt="좋아요" /></button>
			<% } else { %>
				<button id="likesbt"><img src="${pageContext.request.contextPath}/image/heart _over.png" class="shareLike" id="heartfull"  alt="좋아요" /></button>
			<% } %>
			<button id="viewProfile" onclick="open_pop('${shareBoard.getMemberId()}')" > 판매자 프로필 조회</button>
			<button id="writeMessage" > 쪽지하기 </button>
		</div>
		<!--  쪽지하기 모달창  -->
		<div id="frmwrapper">			
			<form id="frmPopCh" name="frmPopCh" action="${pageContext.request.contextPath}/chat/MessageMain"  method="post">
				<input type="hidden" name="no" value="${shareBoard.getProductId()}" />
				<h1 style="font-weight:900; margin : 0 auto; text-align:center; padding-bottom:10px "> MESSAGE </h1>
				<table id="msgTable" style= "margin-top : 0px;" >
				<tr>
					<th class="msgtg"> TO.🙆 </th>
					<td class="msgtd" ><input type="text" id="receiver" name="receiver" style="width:220px; line-height:20px" value="${shareBoard.getMemberId()}" readonly > <!--  받는 사람  --> 	</td>
				</tr>
				
				<tr>
					<th  class="msgtg" > FROM.🙋‍♀️ </th>
					<td class="msgtd" ><input type="text" id="sender" name="sender"  style="width:220px;  line-height:20px" value="${loginMember.memberId}" readonly>  <!--  보내는 사람  --></td>
				</tr>
				
				<tr>
					<th  class="msgtg" > 제목 </th>
					<td class="msgtd" ><input type="text" id="msgTitle" name="msgTitle"  style="width:220px;  line-height:20px" ></td>
				</tr>
				
				<tr>
					<th  class="msgtg" > 내용 </th>
					<td class="msgtd" ><textarea id="msgContent" name="msgContent" style="width:220px" required></textarea></td>
				</tr>
				</table>
				<input class="msgbt"  id="msgsubmit" type="submit" value="SEND"   >
				<span id="msgclose" class="msgclose"> CANCEL </span>		
			</form>	
					
		</div>
		
		
		<!-- 등록일 ~ 텍스트 까지출력되는 테이블  -->
		<table id = "contentTable">
		<tbody>
			<tr>
				<td class="textd"><span style="color:#727272; margin-right: 120px">등록일</span>${shareBoard.getProductRegDate()}</td>		
			</tr>
			<tr>
				<td class="textd"><span style="color:#727272; margin-right: 132px">컬러</span><span style="color:black">${shareBoard.getProductColor()}</span></td>		
			</tr>
			<tr>
				<td class="textd"><span style="color:#727272; margin-right: 100px">추천성별 </span>
				 	 남<input type="checkbox" ${shareBoard.getProductGender()=='남' ? 'checked' : ''} onclick="return false;" />
					 여<input type="checkbox" ${shareBoard.getProductGender()=='여' ? 'checked' : ''} onclick="return false;" /> 
					 공용<input type="checkbox" ${shareBoard.getProductGender()=='공용' ? 'checked' : ''} onclick="return false;" /> </td>		
			</tr>
			<tr>
				<td><span style="color:#727272; font-family: 'Noto Sans KR', sans-serif; font-weight: 100; font-size: 15px; margin-right: 115px">추천룩</span>
						<c:if test="${shareBoard.getStyleName()=='S1'}"><span id="forStyle" >#러블리</span></c:if>
						<c:if test="${shareBoard.getStyleName()=='S2'}"><span id="forStyle" >#댄디</span></c:if>
						<c:if test="${shareBoard.getStyleName()=='S3'}"><span id="forStyle" >#포멀</span></c:if>
						<c:if test="${shareBoard.getStyleName()=='S4'}"><span id="forStyle" >#스트릿</span></c:if>
						<c:if test="${shareBoard.getStyleName()=='S5'}"><span id="forStyle" >#걸리쉬</span></c:if>
						<c:if test="${shareBoard.getStyleName()=='S6'}"><span id="forStyle" >#레트로</span></c:if>
						<c:if test="${shareBoard.getStyleName()=='S7'}"><span id="forStyle" >#로맨틱</span></c:if>
						<c:if test="${shareBoard.getStyleName()=='S8'}"><span id="forStyle" >#시크</span></c:if>
						<c:if test="${shareBoard.getStyleName()=='S9'}"><span id="forStyle" >#아메카지</span></c:if></td>		
			</tr>

			<tr style="margin-top: 50px">
				<td><h3>상품정보</h3> <a id="reporta" onclick="reportFrm()"> 신고하기 </a>
	<%-- 			<img src="<%= request.getContextPath() %>/image/siren.png" alt="" id="siren" /> --%>
				<hr style="width : 700px; margin-top:10px; border : 1.5px solid lightgray" />
				<div id="textdiv" style=" height:120px; margin-top:10px;">
					${shareBoard.getProductContent()}
				</div>
				<hr style="width : 700px; margin-top:10px; border : 1.5px solid lightgray" />
				</td>
			</tr>
		</tbody>
		</table>
	</div>
</section>
 	<!--  수정 /삭제하기  -->
 	<%
		boolean canEdit = loginMember != null && 
							(loginMember.getMemberRole() == MemberRole.A ||
								loginMember.getMemberId().equals( shareBoard.getMemberId() ));
		if(canEdit){
	%>
	<button class ="sharemodidel"  type="submit" onclick="updateBoard()"> 수정하기 </button>
	<button class ="sharemodidel"  id="dell" type="submit"  onclick="deleteBoard()"> 삭제하기 </button>
	<% 
		}
	%>
<!-- 게시글 삭제하기 히든폼 ( 관리자 & 작성자에게만 노출 ) -->	
<form action="${pageContext.request.contextPath}/share/newShareDelete" name = "boardDeleteFrm" method="post">
	<input type="hidden" name="no" value="${shareBoard.productId}" />
</form>

		<!-- 구매전필독 -->

	   <div class="faq-content">
	   	 <p style="margin-left:-310px; margin-bottom: 10px; font-weight: 400">✔️ 구매 전 꼭 확인해주세요! </p>
	   	 
		  <div class="faq-question">
		    <input id="q1" type="checkbox" class="panel">
<!-- 			<div class="plus"> ✔️ </div>  -->
		    <label for="q1" class="panel-title">  SHARE 아이템은 어떤건가요 ? </label>
		    <div class="panel-content"> OBTG회원들만 판매/구매할 수 있는 상품으로, 판매자는 본인이 더이상 입지 않는 상품을 일반 상품가 보다 저렴하게 판매하고
		    								구매자는 저렴하게 구매하실 수 있습니다😊 </div>
		  </div>
  
		  <div class="faq-question">
		    <input id="q2" type="checkbox" class="panel">
<!--  		    <div class="plus"> ✔️ </div>  -->
		    <label for="q2" class="panel-title"> 스타일은 무엇인가요 ? </label>
		    <div class="panel-content"> 게시판 서비스는 회원 대상을 중심으로 이용 가능합니다. </div>
		  </div>
  
		  <div class="faq-question">
		    <input id="q3" type="checkbox" class="panel">
<!-- 			<div class="plus"> ✔️ </div>      -->
			<label for="q3" class="panel-title">  구매 방법/취소 안내 </label>
		    <div class="panel-content">OBTG회원들간의 개인 의사에 따라 판매/구매하는 시스템입니다.<br>
		    							 단,거래 중 문제 발생 시 신고 기능을 적극 이용 부탁드립니다. </div>
		  </div>
	</div>
 
<!-- 신고하기 폼(누르면 나타나용) -->
<% if(loginMember != null){ %>
<form 
	class="report_container"
	name="reportEnrollFrm"
	method="post"
	action="<%= request.getContextPath() %>/report/reportEnroll"
	id="report_container">
	<span class="close-button" onclick="closeFrm()">&times;</span>
    <h2 style="text-align: center; margin: 5px;" id="head">신고하기</h2>
    <hr />
    <table id="report_wrap">
        <thead>
            <tr>
                <th><label for="">신고자</label></th>
                <td><input type="text" value="<%= loginMember.getMemberId() %>" name="reportedMemberId" readonly="readonly"/></td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th><label for="">게시글 번호</label></th>
                <td><input type="text" value="S${shareBoard.productId}" name="boardNo" readonly="readonly"/></td>
            </tr>
            <tr>
                <td colspan="2"><hr style="width: 95%;" /></td>
            </tr>
        </tbody>
    </table>
    <span style="margin-left: 1em; font-weight: bold;">사유선택</span>

    <table id="reason_wrap">
        <tbody>
            <tr>
                <th><input type="checkbox" name="reason" value="R1" onclick="checkOnlyOne(this)"></th>
                <td>스팸홍보/도배글입니다.</td>
            </tr>
            <tr>
                <th><input type="checkbox" name="reason" value="R2" onclick="checkOnlyOne(this)"></th>
                <td>불법정보를 포함하고있습니다.</td>
            </tr>
            <tr>
                <th><input type="checkbox" name="reason" value="R3" onclick="checkOnlyOne(this)"></th>
                <td>욕설/생명경시/혐오/차별적 표현입니다.</td>
            </tr>
            <tr>
                <th><input type="checkbox" name="reason" value="R4" onclick="checkOnlyOne(this)"></th>
                <td>개인정보 노출 게시물입니다.</td>
            </tr>
            <tr>
                <th><input type="checkbox" name="reason" value="R5" onclick="checkOnlyOne(this)"></th>
                <td>불쾌한 표현이 있습니다.</td>
            </tr>
        </tbody>
    </table> <!-- end reason_wrap -->
    <div style="text-align: center;">
        <input type="button" value="신고하기" onclick="reportEnroll()">
    </div>
</form>
<% } %>




<form name="frmPopup">
	<input type="hidden" name="memberID" >
</form>
<script>
function open_pop( ${shareBoard.getMemberId()} ){
    const frmPop= document.frmPopup;
    const url = "${pageContext.request.contextPath}/profile/profileView";
    
    window.open('','popupView','width=610, height=640');        
    frmPop.action = url; 
    frmPop.target = 'popupView'; //
    frmPop.memberID.value = ${shareBoard.getMemberId()};
    frmPop.submit();    
}
</script>



<%-- 쪽지 추가  --%>

<script >
<%-- 신고  --%>
const  siren = document.querySelector("#siren");
siren.style.display = 'none';

const  reporta = document.querySelector("#reporta");
reporta.addEventListener('mouseenter', () => {
	siren.style.display = 'inline';
})

reporta.addEventListener('mouseleave', () => {
	siren.style.display = 'none';
})


const reportFrm = () => {
	const frm = document.querySelector(".report_container");
	<% if(loginMember.getMemberId() != null){ %>
	frm.classList.toggle("showPopup");
	
	<% } else { %>
	loginAlert();
	<% } %>
}

$(function(){

	$('.report_container').draggable({'cancel':'#report_wrap'});

	});

const closeFrm = () => {
	const frm = document.querySelector(".report_container");
	frm.classList.toggle("showPopup");
}

const checkOnlyOne = (e) => {
    const checkbox = document.getElementsByName("reason");

    checkbox.forEach((cb) => {
        cb.checked = false;
    })

    e.checked = true;
} 

const reportEnroll = () => {
	if(confirm("정말 신고하시겠습니까? ")){
		document.reportEnrollFrm.submit();
	} 
	alert("신고가 접수되었습니다.")
}


//쪽지제출 후 alert()
$(document).ready(function() {
    $("#msgsubmit").on('click', function(){
    alert("쪽지가 성공적으로 발송되었습니다😊");
    self.close();
	});
});
</script>

<script>
const  msgbox = document.querySelector("#writeMessage");
msgbox.addEventListener('click', () => {
//	const frm = document.frmPopCh;
	const  div = document.querySelector("#frmwrapper");
	div.style.visibility = "visible";
});
</script>

<script>
//닫혀라 쪽지야
const  msgclose = document.querySelector("#msgclose");

msgclose.addEventListener('click', () => {
//	const frm = document.frmPopCh;
	const div = document.querySelector("#frmwrapper");
	div.style.visibility = "hidden";
});
</script>




<script>
// 게시글 수정 / 삭제 
const deleteBoard = () => { 
	if(confirm("정말 게시글을 삭제하시겠습니까? ")){
	  document.boardDeleteFrm.submit();	
	}	
};

const updateBoard = () => { 
	location.href = "${pageContext.request.contextPath}/share/newShareUpdate?no=${shareBoard.getProductId()}";
}

const loginAlert = () => {
	alert("로그인 후 이용할 수 있습니다.");
	document.querySelector("#loginSignup").focus();
};
</script>	

<script>
// ★★★★ 좋아요 
document.querySelector(".shareLike").addEventListener("click", (e) => {
	<% if(loginMember == null){ %>
		 loginAlert();
	<% } else { %>
		$.ajax({
			url: "${pageContext.request.contextPath}/share/shareLike?no=${shareBoard.getProductId()}",
			method: "post",
			dataType: "json",
			success(data){
				if(data === 1) e.target.src="${pageContext.request.contextPath}/image/heart _over.png"
				else e.target.src="${pageContext.request.contextPath}/image/heart.png"
			},
			error: console.log
			});
	<% } %>
});
</script>


<jsp:include page="/WEB-INF/views/common/footer.jsp" />