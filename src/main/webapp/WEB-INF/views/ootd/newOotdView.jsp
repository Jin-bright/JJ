<%@page import="com.sh.obtg.member.model.dto.MemberRole"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.sh.obtg.member.model.dto.Member"%>
<%@page import="com.sh.obtg.ootd.model.dto.OotdBoardComment"%>
<%@page import="com.sh.obtg.ootd.model.dto.OotdBoard"%>
<%@page import="java.util.List"%>
<%@page import="com.sh.obtg.ootd.model.dto.OotdAttachment"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>        
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ taglib uri ="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/ootdView.css" />
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Noto+Sans+KR:wght@900&family=Solitreo&display=swap" rel="stylesheet">

<%
//String msg = (String)session.getAttribute("msg");
//int totalPage = (int)request.getAttribute("totalPage");

//if( msg != null )
//	session.removeAttribute("msg");
// 리스트 가져오는거 필요함 
 Member loginMember = (Member) session.getAttribute("loginMember");  
 OotdBoard ootdboard = (OotdBoard) request.getAttribute("ootdboard");
 List<OotdAttachment> ootdAttachments = (List<OotdAttachment>)request.getAttribute("ootdAttachments");
 List<OotdBoardComment> comments  = (List<OotdBoardComment>)request.getAttribute("comments");
 int likeCnt = (int)request.getAttribute("likeCnt");
%>


<section id="board-container" > <!-- 0201마진값추가 -->
	<br /><br /><br /><br /><br />
<div id="bigDiv">
	<div id="imgNtableContainer" >
	   <img id="profileimg"  src="${pageContext.request.contextPath}/uploadootds/ootd/profile.png" alt="profileimg" />
	   
	   <span id="writersp"><b>${ootdboard.getOotdWriter()}</b> </span><br />
	   <span id="datep"> <fmt:parseDate value="${ootdboard.getOOTDRegDate()}" pattern="yyyy-mm-dd" var="date"/>
	       <fmt:formatDate value="${date}" pattern="yyyy년 mm월 dd일"  /> </span>
       <button id="viewProfile" onclick="open_pop('${ootdboard.getOotdWriter()}')">프로필보기</button>
	</div>
	<div class="box">
		<img src="${pageContext.request.contextPath}/uploadootds/ootd/${ootdboard.ootdAttachments[0].renamedFilename}"  id="ootdimg" >
	</div>
	
	<div id="infos">
	<% if(likeCnt == 0) { %>
		<img src="<%= request.getContextPath() %>/image/heart.png" class="heart" alt="좋아요"/>
	<% } else { %>
		<img src="<%= request.getContextPath() %>/image/heart _over.png" class="heart" alt="좋아요" />
	<% } %>
	<br/><br />
	<span class="exs">#${ootdboard.OOTDTitle} #${ootdboard.OOTDTop} #${ootdboard.OOTDBottom} #${ootdboard.OOTDShoes}</span>
		<c:if test="${ootdboard.getStyleNo()=='S1'}"><span id="forStyle" >#러블리</span></c:if>
		<c:if test="${ootdboard.getStyleNo()=='S2'}"><span id="forStyle" >#댄디</span></c:if>
		<c:if test="${ootdboard.getStyleNo()=='S3'}"><span id="forStyle" >#포멀</span></c:if>
		<c:if test="${ootdboard.getStyleNo()=='S4'}"><span id="forStyle" >#스트릿</span></c:if>
		<c:if test="${ootdboard.getStyleNo()=='S5'}"><span id="forStyle" >#걸리쉬</span></c:if>
		<c:if test="${ootdboard.getStyleNo()=='S6'}"><span id="forStyle" >#레트로</span></c:if>
		<c:if test="${ootdboard.getStyleNo()=='S7'}"><span id="forStyle" >#로맨틱</span></c:if>
		<c:if test="${ootdboard.getStyleNo()=='S8'}"><span id="forStyle" >#시크</span></c:if>
		<c:if test="${ootdboard.getStyleNo()=='S9'}"><span id="forStyle" >#아메카지</span></c:if>
		<br />
		<a id="reporta" onclick="reportFrm()"> 신고하기 </a>
		<img src="<%= request.getContextPath() %>/image/siren.png" alt="" id="siren" />
		
		<hr style="margin-top: 10px"/><br />
		${ootdboard.getOOTDContents()}<br />
		<br /><hr />
		<%
			boolean canEdit = loginMember != null && 
								(loginMember.getMemberRole() == MemberRole.A ||
									loginMember.getMemberId().equals(ootdboard.getOotdWriter()));
			if(canEdit){
		%>
			<%-- 작성자와 관리자만 마지막행 수정/삭제버튼이 보일수 있게 할 것 --%>
				<button class ="ootdmodidel" type="submit" onclick="updateBoard()"> 수정하기 </button>
				<button class ="ootdmodidel"  type="submit"  onclick="deleteBoard()"> 삭제하기 </button>
		<% 
			}
		%>
	</div>
</div>	
<!-- 코멘트 -->
<div>

</div>

<br /><br /><br /><br /><br />	
<form name="frmPopup">
	<input type="hidden" name="memberID" >
</form>

</section>
<% if(canEdit){  %> 
	<form action="<%=request.getContextPath()%>/ootd/newOotdDelete" name = "boardDeleteFrm" method="post">
		<input type="hidden" name="no" value="<%= ootdboard.getOotdNo()%>" />
	</form>
<% } %>
<script>
const  siren = document.querySelector("#siren");
siren.style.display = 'none';

const  reporta = document.querySelector("#reporta");
reporta.addEventListener('mouseenter', () => {
	siren.style.display = 'inline';
})

reporta.addEventListener('mouseleave', () => {
	siren.style.display = 'none';
})


const loginAlert = () => {
	alert("로그인 후 이용할 수 있습니다.");
	document.querySelector("#loginSignup").focus();
};



function open_pop( ${ootdboard.getOotdWriter()} ){
    const frmPop= document.frmPopup;
    const url = '${pageContext.request.contextPath}/profile/profileView';
    window.open('','popupView','width=600, height=600');   
     
    frmPop.action = url; 
    frmPop.target = 'popupView'; //window,open()의 두번째 인수와 같아야 하며 필수다.   
    frmPop.memberID.value = ${ootdboard.getOotdWriter()};
    frmPop.submit();    
     
}


//게시글 수정 / 삭제 
const deleteBoard = () => { 
	if(confirm("정말 게시글을 삭제하시겠습니까? ")){
	  document.boardDeleteFrm.submit();	
	}	
};

const updateBoard = () => { 
	location.href = "<%=request.getContextPath()%>/ootd/newOotdUpdate?no=<%=ootdboard.getOotdNo()%>";
}
</script>





<%@ include file="/WEB-INF/views/common/footer.jsp" %>