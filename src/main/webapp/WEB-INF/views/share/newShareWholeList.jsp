<%@page import="com.sh.obtg.share.model.dto.NshareAttachment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>        
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ taglib uri ="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shareWholeList.css" /> 
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@100;400;700&family=Noto+Sans+KR:wght@900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<%

List<NshareAttachment> shareAttachments = (List<NshareAttachment>)request.getAttribute("shareAttachments");

%>


<div id="board-container">
	<br /><br />
	<h2 id = "shareboardlist" > SHARE  </h2>

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
<!-- 리스트 출력 일단 서블릿만들고 실행해보기 - 페이지 전체 불러오기 비동기 시도했지만 실패했다^^ -->
<%-- get&post다있는데/ 로그인한 상태에서만 노출 되게 수정해야됨 --%> 
<input type="button" value="글쓰기" id="btnAdd"  onclick="location.href='<%=request.getContextPath()%>/share/newShareEnroll';"/>

<table id="itemTable" >
	<tbody>

		<c:forEach begin="0" step="1" items="${shareAttachments}" var="attach" varStatus="vs">
		 <c:set var="board" value="${shareboards[vs.index]}"/>
		
		<c:if test="${vs.index %4==0 }">
			<tr>
		</c:if>
				<td><div style="width:280px">
			  		<img src="${pageContext.request.contextPath}/image/heart.png" class="heartsempty" alt="좋아요"/> <!-- 하트 -->
				    <img class="itemimg" src="${pageContext.request.contextPath}/uploadshares/newShare/${attach.renamedFilename}">	
			  		</div>
			  		<div id="categories" style="margin-left:10px;" >
		  		  	<c:if test="${board.subcategoryId eq 'T1' }"><p>패딩</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'T2' }"><p>코트</p></c:if>
					<c:if test="${board.subcategoryId eq 'T3' }"><p>니트웨어</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'T4' }"><p>자켓</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'T5' }"><p>후드</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'T6' }"><p>긴팔티셔츠</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'T7' }"><p>반팔티셔츠</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'T8' }"><p>민소매</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'T9' }"><p>기타</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'B1' }"><p>청바지</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'B2' }"><p>슬랙스</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'B3' }"><p>반바지</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'B4' }"><p>스커트</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'A1' }"><p>가방</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'A2' }"><p>시계</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'A3' }"><p>주얼리</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'A4' }"><p>모자</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'A5' }"><p>스카프</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'A6' }"><p>아이웨어</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'A7' }"><p>기타</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'S1' }"><p>운동화</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'S2' }"><p>부츠</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'S3' }"><p>로퍼</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'S4' }"><p>샌들</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'S5' }"><p>슬리퍼</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'S6' }"><p>구두</p></c:if>
			  		<c:if test="${board.subcategoryId eq 'S7' }"><p>기타</p></c:if>
			  		</div>
			  		<div style="margin-left:10px;" >
			  		<p id="pn">[${board.productName}]</p>
			  		<c:if test="${board.styleName eq 'S1'}"><p class="styles">#Lovely</p></c:if>
			  		<c:if test="${board.styleName eq 'S2'}"><p class="styles">#Dandy</p></c:if>
			  		<c:if test="${board.styleName eq 'S3' }"><p class="styles">#Formal</p></c:if>
			  		<c:if test="${board.styleName eq 'S4' }"><p class="styles"> #Street</p></c:if>
					<c:if test="${board.styleName eq 'S5' }"><p class="styles"> #Girlish</p></c:if>
					<c:if test="${board.styleName eq 'S6' }"><p class="styles"> #Retro</p></c:if>
					<c:if test="${board.styleName eq 'S7' }"><p class="styles"> #Romantic</p></c:if>
					<c:if test="${board.styleName eq 'S8' }"><p class="styles"> #Chic</p></c:if>
					<c:if test="${board.styleName eq 'S9' }"><p class="styles"> #Amekaji</p></c:if>	
			  		
			  		<b><fmt:formatNumber value="${board.productPrice}"	pattern="#,###" />원</b>
			  		</div>
			  		<br /><br /><br />
			  	</td>
				<td class="itemblanks"></td>
		<c:if test="${vs.index %4==3}">
			</tr>
		</c:if>
		</c:forEach>


 </tbody>
</table>

</div>
</section>

<div id='pagebar' >${pagebar}</div> 
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

<%-- <script>

$.ajax({
	url : "${pageContext.request.contextPath}/share/NewShareWholeListAjax",
	method : "get",
	success(data){
		for( let i =0; i<data.length; i++){
			alert( data[i])
		}
		
	},
	complete(){
	}
});//end ajax

function htmlView(data) {
	var result = document.querySelector("#testTbody");
	result+="<tr>";
	result+="<td>왜</td>";
	result+="<td>안</td>";
	result+="<td>나</td>";
	result+="<td>와</td>";
	result+="</tr>";
	
};
/// ajax로 전체 리스트 출력가능할까  ?
	$(function () {
		
		$(window).scroll(function () {
			let scrollHeight = $(window).scrollTop() + $(window).height();
			let documentHeight = $(document).height();
			
			if( scrollHeight + 200 >= documentHeight ){
				//for(i=0 ; i<10 ; i++){
				//	$("#test").append("<h1>jquery 무한 스크롤</h1>");
				$.ajax({
					url : "${pageContext.request.contextPath}/share/NewShareWholeListAjax",
					method : "get",
					data : {page},
					success(data){
						console.log( data )
						const tbody = document.querySelector("#testTbody");
						const tr =  document.createElement("tr");
						
						
						for( let i=0; i<data.length; i++){
							if(i%2==0){
								tbody.append('tr')
							}
							
							const img = document.createElement("img");
							img.src = "<%=request.getContextPath()%>/uploadshares/newShare/"+data[i].renamedFilename;
							
							
							tbody.append(td);
							td.append(img);
						}
					},
					complete(){
						
					}
					
					
				});//end ajax
			//}
			}
		});
		
		for(i=0 ; i<20 ; i++){
			$("test").append("<h1>jquery 무한 스크롤</h1>");
		}
	});
</script> --%>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />