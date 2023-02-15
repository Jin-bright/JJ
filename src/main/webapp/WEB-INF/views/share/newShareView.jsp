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

	<hr style="height : 700px; margin-top:90px; border : 1px solid lightgray"/>


	<div id="contentDiv" >
		<div id="con">
			<img  src="<%=request.getContextPath()%>/uploadshares/carrot.png" alt="carrot" style=" width:16px; height:16px; margin-left: 630px" />
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
			<button id="likesbt"><img src="${pageContext.request.contextPath}/image/heart.png" class="heartsempty" alt="좋아요"/></button>
			<button id="viewProfile" onclick="open_pop('${shareBoard.getMemberId()}')" > 판매자 프로필 조회</button>
			<button id="writeMessage" > 쪽지하기 </button>
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
				<td><h3>상품정보</h3>
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