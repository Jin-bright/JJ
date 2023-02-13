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
	      		<p style="color : gray; margin-left : 5px; font-size:15px"> <a href="${pageContext.request.contextPath}/share/newShareWholeList">모든 카테고리</a></p>
	      		<span class="icon" aria-hidden="true"></span></button>
	      <div class="accordion-content">
	      	<input type="checkbox" name="searchKeyword" value="T"  id="T" onclick="searchClothes(this);" /><label for="T">상의</label><br />
	        <input type="checkbox" name="searchKeyword" value="B"  id="B"  onclick="searchClothes(this);" /><label for="B">하의</label><br />
	        <input type="checkbox" name="searchKeyword" value="A"  id="A" onclick="searchClothes(this);" /><label for="A">악세서리</label><br />
	        <input type="checkbox" name="searchKeyword" value="S"  id="S" onclick="searchClothes(this);" /><label for="S">신발</label><br />
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
				    	<a style="display:inline; margin-left: 150px" href="${pageContext.request.contextPath}/share/newShareView?no=${attach.productId}">
				    	<img class="itemimg" src="${pageContext.request.contextPath}/uploadshares/newShare/${attach.renamedFilename}">
				    	</a>	
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


<!--  검색용 더보기 버튼
<div id='btn-more-container'>
	<button id="btn-more" value="" > 더보기( <span id="pageaj"></span> / <span id="totalPage">검색시토탈페이지</span> ) </button>
</div>
-->

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





<%---- 비동기 필터 검색시작 !!!!! --%>


<script>
const searchClothes = (e) => {

	const searchKeyword = document.getElementsByName("searchKeyword");
	const page = document.querySelector("#pageaj");
	
	searchKeyword.forEach((cb) => {
	    cb.checked = false;
	})
	  
	e.checked = true; 

	console.log( e.value );

	const searchdata = e.value; //이걸로찾을거야
	console.log( e );

	$.ajax({
		url:"${pageContext.request.contextPath}/share/findShareWholeListClothes",
		method : "get",
		data : {searchKeyword : searchdata},
		success(data){
	
			console.log ( data );
			
			const table = document.querySelector("#itemTable");
			table.innerHTML = "";
			
			
			const tbody =  document.createElement("tbody");
			table.append( tbody );
			
			const tr1 =  document.createElement("tr");
			tbody.append( tr1 );

			
			const tr2 =  document.createElement("tr");
			tbody.append( tr2 );

			
			const tr3 =  document.createElement("tr");
			tbody.append( tr3 );

			
			for( let i=0; i<data.shareAttachments.length; i++ ){
				if( parseInt(i/4) == 0){
					console.log( data.shareboards[i].subcategoryId )
					tr1.innerHTML += 
				      `<td>
						 <div style="width:280px">
						 	<img src="${pageContext.request.contextPath}/image/heart.png" class="heartsempty" alt="좋아요"/> <!-- 하트 -->
						 	<a style="display:inline; margin-left: 150px" href="${pageContext.request.contextPath}/share/newShareView?no=\${data.shareboards[i].productId}">
					     	<img class="itemimg" src="${pageContext.request.contextPath}/uploadshares/newShare/\${data.shareAttachments[i].renamedFilename}" /></a>
					     </div>
					     <div id="categories" style="margin-left:10px;" >
					     	<c:if test="{data.shareboards[i].subcategoryId == 'T2'}"><p>니트웨어</p></c:if>
							<c:if test="{data.shareboards[i].subcategoryId == 'T3'}"><p>니트웨어</p></c:if>
					  		<c:if test="{data.shareboards[i].subcategoryId == 'T4'}"><p>자켓</p></c:if>
					  		<c:if test="{data.shareboards[i].subcategoryId == 'T5'}"><p>후드</p></c:if>
				  		</div>
					     
					     <div style="margin-left:10px;" >
					     	<p id="pn">[\${data.shareboards[i].productName}]</p>
					     	<b><fmt:formatNumber value="${data.shareboards[i].productPrice}" pattern="#,###" />원</b>
					  	</div>
					  </td>`;					
				}
				if(parseInt(i/4)== 1 ){
					tr2.innerHTML += 
					  `<td>
						<div style="width:280px">
							<img src="${pageContext.request.contextPath}/image/heart.png" class="heartsempty" alt="좋아요"/> <!-- 하트 -->
							<a style="display:inline; margin-left: 150px" href="${pageContext.request.contextPath}/share/newShareView?no=\${data.shareboards[i].productId}">
							<img class="itemimg" src="${pageContext.request.contextPath}/uploadshares/newShare/\${data.shareAttachments[i].renamedFilename}" /></a>
						</div>
						<div style="margin-left:10px;" >
					     	<p id="pn">[\${data.shareboards[i].productName}]</p>
					     	<b><fmt:formatNumber value="${data.shareboards[i].productPrice}" pattern="#,###" />원</b>
					  	</div>
						</td>`;
				}
				if( parseInt(i/4) == 2 ){
				  tr3.innerHTML += 
				    `<td><div style="width:280px">
					    <img src="${pageContext.request.contextPath}/image/heart.png" class="heartsempty" alt="좋아요"/> <!-- 하트 -->
					    <a style="display:inline; margin-left: 150px" href="${pageContext.request.contextPath}/share/newShareView?no=\${data.shareboards[i].productId}">
				  		<img class="itemimg" src="${pageContext.request.contextPath}/uploadshares/newShare/\${data.shareAttachments[i].renamedFilename}" /></a></div></td>`
				}
				
			}
			
			console.log ( table );
		
		},
		complete(data){
			document.querySelector("#pagebar").innerHTML = "";
	
			
		}
	});//end-ajax	
}


</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />