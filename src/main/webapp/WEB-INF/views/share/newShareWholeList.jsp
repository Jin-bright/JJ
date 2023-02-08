<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>        
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ taglib uri ="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shareWholeList.css" /> 
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@100;400;700&family=Noto+Sans+KR:wght@900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">



<div id="board-container">
	<br /><br />
	<h2 id = "shareboardlist" > SHARE  </h2>
	<%-- get&post다있는데/ 로그인한 상태에서만 노출 되게 수정해야됨 --%> 
	<input type="button" value="글쓰기" id="btnAdd"  onclick="location.href='<%=request.getContextPath()%>/share/newShareEnroll';"/>
	
	<div id="menuimages">
		<img 	style="margin-left : -20px" class="menuimgs" src="${pageContext.request.contextPath}/uploadshares/small.png"  />
			<span class="menuimgssp" style="margin-left:-70px">아우터</span>		
		<img class="menuimgs" src="${pageContext.request.contextPath}/uploadshares/small2.png"  />
			<span class="menuimgssp"  style="margin-left:-60px">긴팔</span>	
		<img class="menuimgs" src="${pageContext.request.contextPath}/uploadshares/small3.png"  />
			<span class="menuimgssp"  style="margin-left:-65px">주얼리</span>			
		<img class="menuimgs" src="${pageContext.request.contextPath}/uploadshares/small4.png"  />
			<span class="menuimgssp"  style="margin-left:-65px">구두</span>			
		<img class="menuimgs" src="${pageContext.request.contextPath}/uploadshares/small5.png"  />
			<span class="menuimgssp"  style="margin-left:-65px">시계</span>			
		<img class="menuimgs" src="${pageContext.request.contextPath}/uploadshares/small6.png"  />	
			<span class="menuimgssp"  style="margin-left:-56px">가방</span>		
		<img class="menuimgs" src="${pageContext.request.contextPath}/uploadshares/small7.png"  />	
			<span class="menuimgssp"  style="margin-left:-65px">운동화</span>	
		<img class="menuimgs" src="${pageContext.request.contextPath}/uploadshares/small8.png"  />	
			<span class="menuimgssp"  style="margin-left:-65px">슬랙스</span>	
		<img class="menuimgs" src="${pageContext.request.contextPath}/uploadshares/small9.png"  />	
			<span class="menuimgssp"  style="margin-left:-65px">모자</span>	
	</div>	
</div>
<section>

<div class="sectiondivs" id="filters">

	<div class="container">
	  <h4 style="padding-top:10px" > 필터 </h4><br />
	  <div class="accordion">
	    <div class="accordion-item"> <!-- 아코디언 -->
	      <button id="accordion-button-1" style="padding-top:10px" aria-expanded="false">
	      		<span class="accordion-title"><b>카테고리</b></span>
	      		<p style="color : gray; margin-left : 5px; font-size:15px"> 모든 카테고리 </p>
	      		<span class="icon" aria-hidden="true"></span></button>
	      <div class="accordion-content">
	        <p> 상의 </p>
	        <p> 하의 </p>
	        <p> 악세서리 </p>
	        <p> 신발 </p>
	      </div>
	    </div>
	    
	    <div class="accordion-item"> <!-- 아코디언 -->
	      <button id="accordion-button-4" style="padding-top:10px" aria-expanded="false">
	      <span class="accordion-title"><b>성별</b></span>
	      <p style="color : gray; margin-left : 5px; font-size:15px"> 모든 성별 </p>
	      <span class="icon" aria-hidden="true"></span></button>
	      <div class="accordion-content">
	        <p> 여성 </p>
	        <p> 남성 </p>
	      </div>
	    </div>
	    <div class="accordion-item"> <!-- 아코디언 -->
	      <button id="accordion-button-5" style="padding-top:10px"  aria-expanded="false">
	      <span class="accordion-title"><b>스타일</b></span>
	        <p style="color : gray; margin-left : 5px; font-size:15px"> 모든 스타일 </p>
	      <span class="icon" aria-hidden="true"></span></button>
	      <div class="accordion-content">
	        <p> 러블리 </p>
 			<p> 댄디 </p>
   	        <p> 포멀 </p> 
   	        <p> 스트릿 </p>
   	        <p> 걸리쉬 </p>
   	        <p> 레트로 </p>   	        
   	        <p> 로맨틱 </p>
   	        <p> 시크 </p>
	       	<p> 아메카지 </p>

   	          
	      </div>
	    </div>
	  </div>
	</div>
	
</div>


<div class="sectiondivs"  id="sharelists">
<!-- 리스트 출력 일단 서블릿만들고 실행해보기 -->
<table>
<c:forEach  items="${shareBoard}" var="board" varStatus="vs">
	<%-- <c:if test="${integer.parseInt(vs.index)%2==0}">
		<tr>
	</c:if> --%>
	<tr>
		<td>
			<p>${board}</p>
			<img src="${pageContext.request.contextPath}/uploadshares/newShare/'${board[vs.index].getShareAttachments().get[vs.index].getRenamed_filename()}'" alt="" />	
	<!-- 				    	shareBoard.getShareAttachments().get(0).getRenamed_filename() -->
		</td>
	</tr>
<%-- 	<c:if test="${integer.parseInt(vs.index)%2==1}">
		</tr>
	</c:if>--%>
 </c:forEach>
</table>

</div>

</section>
<br /><br /><br /><br /><br />
<script>
//메뉴토글
const items = document.querySelectorAll(".accordion button");

function toggleAccordion() {
  const itemToggle = this.getAttribute('aria-expanded');
  
  for (i = 0; i < items.length; i++) {
    items[i].setAttribute('aria-expanded', 'false');
  }
  
  if (itemToggle == 'false') {
    this.setAttribute('aria-expanded', 'true');
  }
}

items.forEach(item => item.addEventListener('click', toggleAccordion));
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />