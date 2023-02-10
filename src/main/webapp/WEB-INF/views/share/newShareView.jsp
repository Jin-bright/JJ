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



<section>

	<div id="pictureDiv">
		<img id="fiximg" src="${pageContext.request.contextPath}/uploadshares/newShare/${shareBoard.shareAttachments[0].renamedFilename}" alt="img" />
	</div>

	<hr style="height : 800px; margin-top:50px; border : 1px solid lightgray"/>


	<div id="contentDiv" >
		<div id="con">
			<img  src="<%=request.getContextPath()%>/uploadshares/carrot.png" alt="carrot" style=" width:16px; height:16px; margin-left: 630px" />
			<h4>${shareBoard.getProductStatus()}</h4>	
			<br/><br />
			<h1> ${shareBoard.getProductName()}</h1><br />
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
		
		<div class = "buttonss">
			<button id="viewProfile" onclick="open_pop('${shareBoard.getMemberId()}')" > 판매자 프로필 조회</button>
			<button id="writeMessage" > 쪽지하기 </button>
		</div>
		
		
		
		<button id="likesbt"> 좋아요 </button>
		<table id = "contentTable">
		<tbody>
			<tr>
				<td><span style="color:gray; margin-right: 80px">등록일</span>${shareBoard.getProductRegDate()}</td>		
			</tr>
			<tr>
				<td><span style="color:gray; margin-right: 95px">컬러</span>${shareBoard.getProductColor()}</td>		
			</tr>
			<tr>
				<td><span style="color:gray; margin-right: 80px">스타일</span>${shareBoard.getStyleName()} -- 변환필요 </td>		
			</tr>
			<tr>
				<td><h3>상품정보</h3>
				<hr style="width : 700px; margin-top:10px; border : 1px solid lightgray" />
				<div style="border : 1px solid gray; height:80px; margin-top:10px;">
				${shareBoard.getProductContent()}
				</div>
				</td>
			</tr>
		</tbody>
		</table>
	</div>



</section>
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






















<jsp:include page="/WEB-INF/views/common/footer.jsp" />