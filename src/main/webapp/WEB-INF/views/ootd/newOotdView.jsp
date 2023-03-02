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
<%-- 	   <img id="profileimg"  src="${pageContext.request.contextPath}/upload/profile/${ootdboard.getOotdWriter().renamed}" alt="profileimg" /> --%>
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
	<img src="<%= request.getContextPath() %>/image/comment.png" id="comt" alt="댓글"/>
	<p id="likesco" >좋아요 <span id="likescounts" style="font-weight: bolder"> </span>개</p>
	<br/><br /><br />
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
		<hr />
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
	<div style="margin-left : 518px;">
		<br /><p  style="margin-left : 50px;">댓글</p>
	</div>
      <%
       	if(!comments.isEmpty() ){
       %>
            <table id="tbl-comment">
            <%
            	for(OotdBoardComment bc : comments){
            		if(bc.getCmtLevel() == 1 ){
            %>
            <%-- 댓글인 경우 tr.level1 --%>
            <tr class="level1">
    	         <td>
	    	        <img id="cmtprofileimg"  src="${pageContext.request.contextPath}/uploadootds/ootd/profile.png" alt="profileimg" />
                    <sub class="comment-writer" style="font-weight:bolder"><b><%=bc.getMemberId() %></b></sub>
                    <sub class="comment-date" style="font-weight:bolder" ><b><%=bc.getCmtRegDate()%></b></sub>
                    <br />
                    <%-- 댓글내용 --%>
                    <%=bc.getCmtContent()%>
                </td>
                <td>
                    <button class="btn-reply" value="<%= bc.getCmtNo()%>" >답글</button>
                    <% if( loginMember != null && 
	                		 ( ( loginMember.getMemberId()).equals( bc.getMemberId()) || (loginMember.getMemberRole() == MemberRole.A ))) {%>
                 	<button class="btn-delete" value="<%= bc.getCmtNo() %>" >삭제</button>
					<% } %>  	
                </td>
            </tr>
            <%
          		}  else {
            %>
            <%-- 대댓글인 경우 tr.level2 --%>
            <tr class="level2">
                <td>
   	    	        <img id="cmtprofileimg"  src="${pageContext.request.contextPath}/uploadootds/ootd/profile.png" alt="profileimg" />
                    <sub class=comment-writer><b><%=bc.getMemberId()%></b></sub>
                    <sub class=comment-date><b><%=bc.getCmtRegDate()%></b></sub>
                <br />
                    <%-- 대댓글 내용 --%>
                    <%=bc.getCmtContent()%>
                </td>
                <td> 
	                <% if( loginMember != null && 
	                		 ( ( loginMember.getMemberId()).equals( bc.getMemberId()) || (loginMember.getMemberRole() == MemberRole.A ))) {%>
						<button class="btn-delete"  value="<%= bc.getCmtNo()%>"> 삭제</button>
					<% } %>   	
				</td>
            </tr>
            <%
          			}
            	}
            %>
  		
        </table>
        <%
        	}
        %>
<form action="<%= request.getContextPath() %>/ootd/ootdCommentDelete"  name="boardCommentDelFrm" method="POST">
	<input type="hidden" name="no" />
	<input type="hidden" name="boardNo" value="<%=ootdboard.getOotdNo() %>"/>
</form>

<!-- 신고하기 폼(누르면 나타나용) -->
<% if(loginMember != null){ %>
<form 
	class="report_container"
	name="reportEnrollFrm"
	method="post"
	action="<%= request.getContextPath() %>/report/reportEnroll">
	<span class="close-button" onclick="closeFrm()">&times;</span>
    <h2 style="text-align: center; margin: 5px;" >신고하기</h2>
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
                <td><input type="text" value="O<%= ootdboard.getOotdNo() %>" name="boardNo" readonly="readonly"/></td>
            </tr>
            <tr>
                <td colspan="2"><hr style="width: 95%;" /></td>
            </tr>
        </tbody>
    </table>
    <table id="reason_wrap">
        <tbody>
        	<tr>
        		<th colspan="2">사유선택</th>
        	</tr>
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
		location.href = "${pageContext.request.contextPath}/ootd/newOotdUpdate?no=<%=ootdboard.getOotdNo()%>";
	}
</script>

<script>
// ★★★★ 좋아요 
document.querySelector(".heart").addEventListener("click", (e) => {
	
	const counts = document.querySelector("#likescounts");
	
	<% if(loginMember == null){ %>
		 loginAlert();
	<% } else { %>
		$.ajax({
			url: "${pageContext.request.contextPath}/ootd/OotdLike?no=<%= ootdboard.getOotdNo() %>",
			method: "post",
			dataType: "json",
			success(data){
				if(data === 1) {
					e.target.src="${pageContext.request.contextPath}/image/heart _over.png";
					counts.innerHTML = parseInt( counts.innerHTML) + parseInt(1);
				}
				else{
					e.target.src="${pageContext.request.contextPath}/image/heart.png";
					counts.innerHTML =  parseInt( counts.innerHTML) -  parseInt(1);
				}
				
				
			},
		error: console.log
		});
	<% } %>
});


</script>

<script>
//좋아요 총 갯수
window.onload = () => {
	const counts = document.querySelector("#likescounts");
	
	$.ajax({
		url: "${pageContext.request.contextPath}/ootd/newOotdWholeLike?no=<%= ootdboard.getOotdNo() %>",
		method: "get",
		dataType: "json",
		success(data){
			console.log( data );
			counts.innerHTML = parseInt(data);
		},
	error: console.log
	});
}

</script>

<script>
/* 신고 */
	const reportFrm = () => {
		const frm = document.querySelector(".report_container");
		<% if(loginMember != null){ %>
		frm.classList.toggle("showPopup");
		<% } else { %>
		loginAlert();
		<% } %>
	}
	
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
</script>

<script>
//대댓글
document.querySelectorAll(".btn-reply").forEach((button) => {
button.onclick = (e) => {
	console.log(e.target.value);

	<% if(loginMember == null){ %>
		loginAlert();
	<% } else { %>

	
	const tr = `
	<tr>
		<td colspan="2" style="text-align:left">
			<form
				action="<%=request.getContextPath()%>/ootd/ootdCommentEnroll" method="post" name="boardCommentFrm">
                <input type="hidden" name="boardNo" value="<%= ootdboard.getOotdNo() %>" />
	            <input type="hidden" name="writer" value="<%= loginMember != null ? loginMember.getMemberId() : "" %>" /> 
                <input type="hidden" name="commentLevel" value="2" />
                <input type="hidden" name="commentRef" value="\${e.target.value}" />    
				<textarea id="cmtcmtcontent"  name="content" cols="58" rows="1"></textarea>
                <button type="submit" class="btn-comment-enroll2">등록</button>
            </form>
      	</td>
    </tr>
	`;
	
	const target = e.target.parentElement.parentElement; // tr
	console.log( e.target );
	console.log( target);
	target.insertAdjacentHTML('afterend', tr);
	
	button.onclick = null; // 이벤트핸들러 제거

 	<% } %>  
	};
});

//삭제
document.querySelectorAll(".btn-delete").forEach((button) => {
	button.onclick = (e) => {
		if(confirm("해당 댓글을 삭제하시겠습니까?")){
			const frm = document.boardCommentDelFrm;
			frm.no.value = e.target.value;
			frm.submit();
		};
	}; 
});	


</script>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>