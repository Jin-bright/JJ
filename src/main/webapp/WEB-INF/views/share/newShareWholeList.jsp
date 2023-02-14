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
	  <h4 style="padding-top:10px" > 필터 <span id="countFilter"></span>
	  		<a id="deleteAll" style="font-size:12px; color:gray; text-decoration:underline; padding-left:50px; display:inline-block;" href="${pageContext.request.contextPath}/share/newShareWholeList"></a><br /></h4> 
	  <div class="accordion">
	    <div class="accordion-item"> <!-- 아코디언 -->
	      <button id="accordion-button-1" style="padding-top:10px" aria-expanded="false">
	      		<span class="accordion-title"><b>카테고리</b></span>
	      		<p style="color : gray; margin-left : 5px; font-size:15px">모든 카테고리</p>
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
          	<input type="checkbox" name="searchKeyword" value="여"  id="여" onclick="searchClothes(this);" /><label for="여">여성</label><br />
	        <input type="checkbox" name="searchKeyword" value="남"  id="남" onclick="searchClothes(this);" /><label for="남">남성</label><br />
	      </div>
	    </div>
	    
	    <div class="accordion-item"> <!-- 아코디언 -->
	      <button id="accordion-button-5" style="padding-top:10px"  aria-expanded="false">
	      <span class="accordion-title"><b>색상</b></span>
	        <p style="color : gray; margin-left : 5px; font-size:15px"> 모든 색상 </p>
	      <span class="icon" aria-hidden="true"></span></button>
     	<div class="accordion-content">   	        
   	        <input type="checkbox" name="searchKeyword" value="빨강"  id="CR1" onclick="searchColorStyle(this);" /><label for="CR1">빨강</label><br />
  	        <input type="checkbox" name="searchKeyword" value="주황"  id="CR2" onclick="searchColorStyle(this);" /><label for="CR2">주황</label><br />
  	        <input type="checkbox" name="searchKeyword" value="노랑"  id="CR3" onclick="searchColorStyle(this);" /><label for="CR3">노랑</label><br />
  	        <input type="checkbox" name="searchKeyword" value="파랑"  id="CR4" onclick="searchColorStyle(this);" /><label for="CR4">파랑</label><br />
  	        <input type="checkbox" name="searchKeyword" value="하얀"  id="CR5" onclick="searchColorStyle(this);" /><label for="CR5">하얀</label><br />
  	        <input type="checkbox" name="searchKeyword" value="초록"  id="CR6" onclick="searchColorStyle(this);" /><label for="CR6">초록</label><br />
  	        <input type="checkbox" name="searchKeyword" value="보라"  id="CR7" onclick="searchColorStyle(this);" /><label for="CR7">보라</label><br />
  	        <input type="checkbox" name="searchKeyword" value="베이지" id="CR8" onclick="searchColorStyle(this);" /><label for="CR8">베이지</label><br />
  	        <input type="checkbox" name="searchKeyword" value="하늘"  id="CR9" onclick="searchColorStyle(this);" /><label for="CR9">하늘</label><br />
       		<input type="checkbox" name="searchKeyword" value="검정"  id="CR10" onclick="searchColorStyle(this);" /><label for="CR10">검정</label><br />
	      </div>
	    </div>
    
     	<div class="accordion-item"> <!-- 아코디언 -->
	      <button id="accordion-button-6" style="padding-top:10px" aria-expanded="false">
		      <span class="accordion-title"><b>스타일</b></span>
		      <p style="color : gray; margin-left : 5px; font-size:15px"> 모든 스타일 </p>
		      <span class="icon" aria-hidden="true"></span></button>
	      <div class="accordion-content">
          	<input type="checkbox" name="searchKeyword" value="S1"  id="S1" onclick="searchColorStyle(this);" /><label for="S1">러블리</label><br />
	        <input type="checkbox" name="searchKeyword" value="S2"  id="S2" onclick="searchColorStyle(this);" /><label for="S2">댄디</label><br />
	        <input type="checkbox" name="searchKeyword" value="S3"  id="S3" onclick="searchColorStyle(this);" /><label for="S3">포멀</label><br />
	        <input type="checkbox" name="searchKeyword" value="S4"  id="S4" onclick="searchColorStyle(this);" /><label for="S4">스트릿</label><br />
	        <input type="checkbox" name="searchKeyword" value="S5"  id="S5" onclick="searchColorStyle(this);" /><label for="S5">걸리쉬</label><br />
	        <input type="checkbox" name="searchKeyword" value="S6"  id="S6" onclick="searchColorStyle(this);" /><label for="S6">레트로</label><br />
	        <input type="checkbox" name="searchKeyword" value="S7"  id="S7" onclick="searchColorStyle(this);" /><label for="S7">로맨틱</label><br />
	        <input type="checkbox" name="searchKeyword" value="S8"  id="S8" onclick="searchColorStyle(this);" /><label for="S8">시크</label><br />
	        <input type="checkbox" name="searchKeyword" value="S9"  id="S9" onclick="searchColorStyle(this);" /><label for="S9">아메카지</label><br />
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
				<td ><div style="width:280px;">
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





<%---- 비동기 - 옷 & 성별 검색 필터 검색시작 !!!!! --%>
<script>
const searchClothes = (e) => {

	const searchKeyword = document.getElementsByName("searchKeyword");
	//const page = document.querySelector("#pageaj");
	
	searchKeyword.forEach((cb) => {
	    cb.checked = false;
	})
	  
	e.checked = true; 
	//$(e).attr('name','color');

	console.log ( e );
	
	const searchdata = e.value; //이걸로찾을거야

	$.ajax({
		url:"${pageContext.request.contextPath}/share/findShareWholeListClothes",
		method : "get",
		data : {searchKeyword : searchdata},
		success(data){
			console.log( data )

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
				//카테고리
				let v = data.shareboards[i].subcategoryId;
				if(v == 'T1'){ v = "패딩" }
				else if(v == 'T2'){ v = "코트" }else if(v == 'T3'){ v = "니트웨어" }else if(v == 'T4'){ v = "자켓" }else if(v == 'T5'){ v = "후드" }
				else if(v == 'T6'){ v = "긴팔티셔츠" }else if(v == 'T7'){ v = "반팔티셔츠" }else if(v == 'T8'){ v = "민소매" }else if(v == 'T9' || v== 'A7' || v== 'S7' ){ v = "기타" }
				else if(v == 'B1'){ v = "청바지" }else if(v == 'B2'){ v = "슬랙스" }else if(v == 'B3'){ v = "반바지" }else if(v == 'B4'){ v = "스커트" }
				else if(v == 'A1'){ v = "가방" }else if(v == 'A2'){ v = "시계" }else if(v == 'A3'){ v = "주얼리" }else if(v == 'A4'){ v = "모자" }
				else if(v == 'A5'){ v = "스카프" }else if(v == 'A6'){ v = "아이웨어" }
				else if(v == 'S1'){ v = "운동화" }else if(v == 'S2'){ v = "부츠" }else if(v == 'S3'){ v = "로퍼" }else if(v == 'S4'){ v = "샌들" }else if(v == 'S5'){ v = "슬리퍼" }else if(v == 'S6'){ v = "구두" }
				
				//가격
				let price = data.shareboards[i].productPrice;
				let p = price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
	
				//스타일
				let s = data.shareboards[i].styleName;
				if(s == 'S1'){ s = "#Lovely" }
				else if(s == 'S2'){ s = "#Dandy" }else if(s == 'S3'){ s = "#Formal" }else if(s == 'S4'){ s = "#Street" }else if(s == 'S5'){ s = "#Girlish" }
				else if(s == 'S6'){ s = "#Retro" }else if(s == 'S7'){ s = "#Romantic" }else if(s == 'S8'){ s = "#Chic" }else if(s == 'S9'){ s = "#Amekaji" }
				
				if( parseInt(i/4) == 0){

					tr1.innerHTML += 
				      `<td>
						 <div style="width:280px;">
						 	<img src="${pageContext.request.contextPath}/image/heart.png" class="heartsempty" alt="좋아요"/> <!-- 하트 -->
						 	<a style="display:inline; margin-left: 150px" href="${pageContext.request.contextPath}/share/newShareView?no=\${data.shareboards[i].productId}">
					     	<img class="itemimg" src="${pageContext.request.contextPath}/uploadshares/newShare/\${data.shareAttachments[i].renamedFilename}" /></a>
					     </div>
					     <div id="categories" style="margin-left:10px;" >
					        <p>\${v}</p>
					    </div>
					     <div style="margin-left:10px;" >
					     	<p id="pn">[\${data.shareboards[i].productName}]</p>
					     	<p class="styles" >\${s}</p>
					     	<p><b>\${p}원</b></p>
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
						<div id="categories" style="margin-left:10px;" >
					        <p>\${v}</p>
					    </div>
						<div style="margin-left:10px;" >
					     	<p id="pn">[\${data.shareboards[i].productName}]</p>
					     	<p class="styles" >\${s}</p>
					     	<p><b>\${p}원</b></p>
					  	</div>
					 </td>`;
				}
				if( parseInt(i/4) == 2 ){
				  tr3.innerHTML += 
				    `<td><div style="width:280px">
					    <img src="${pageContext.request.contextPath}/image/heart.png" class="heartsempty" alt="좋아요"/> <!-- 하트 -->
					    <a style="display:inline; margin-left: 150px" href="${pageContext.request.contextPath}/share/newShareView?no=\${data.shareboards[i].productId}">
				  		<img class="itemimg" src="${pageContext.request.contextPath}/uploadshares/newShare/\${data.shareAttachments[i].renamedFilename}" /></a></div>
					     <div id="categories" style="margin-left:10px;" >
					        <p>\${v}</p>
					    </div>
					     <div style="margin-left:10px;" >
					     	<p id="pn">[\${data.shareboards[i].productName}]</p>
					     	<p class="styles" >\${s}</p>
					     	<b>\${p}원</b>
					  	</div>
					  </td>`;
				}
				
			}
		},//end-success
		complete(data){
			document.querySelector("#pagebar").innerHTML = "";
			document.querySelector("#countFilter").innerHTML  = "1" ;
			document.querySelector("#deleteAll").innerHTML += "모두삭제";

		}
	});//end-ajax	
}
</script>



<%---- 비동기 - 색깔 검색 필터 검색시작 !!!!! --%>
<script>
const searchColorStyle = (e) => {

	const searchKeyword = document.getElementsByName("searchKeyword");
	
	searchKeyword.forEach((cb) => {
	    cb.checked = false;
	})
	  
	e.checked = true; 

	console.log ( e );

	const searchdata = e.value; //이걸로찾을거야

	$.ajax({
		url:"${pageContext.request.contextPath}/share/findShareWholeListColor",
		method : "get",
		data : {searchKeyword : searchdata},
		success(data){
			console.log( data )

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
				//카테고리
				let v = data.shareboards[i].subcategoryId;
				if(v == 'T1'){ v = "패딩" }
				else if(v == 'T2'){ v = "코트" }else if(v == 'T3'){ v = "니트웨어" }else if(v == 'T4'){ v = "자켓" }else if(v == 'T5'){ v = "후드" }
				else if(v == 'T6'){ v = "긴팔티셔츠" }else if(v == 'T7'){ v = "반팔티셔츠" }else if(v == 'T8'){ v = "민소매" }else if(v == 'T9' || v== 'A7' || v== 'S7' ){ v = "기타" }
				else if(v == 'B1'){ v = "청바지" }else if(v == 'B2'){ v = "슬랙스" }else if(v == 'B3'){ v = "반바지" }else if(v == 'B4'){ v = "스커트" }
				else if(v == 'A1'){ v = "가방" }else if(v == 'A2'){ v = "시계" }else if(v == 'A3'){ v = "주얼리" }else if(v == 'A4'){ v = "모자" }
				else if(v == 'A5'){ v = "스카프" }else if(v == 'A6'){ v = "아이웨어" }
				else if(v == 'S1'){ v = "운동화" }else if(v == 'S2'){ v = "부츠" }else if(v == 'S3'){ v = "로퍼" }else if(v == 'S4'){ v = "샌들" }else if(v == 'S5'){ v = "슬리퍼" }else if(v == 'S6'){ v = "구두" }
				
				//가격
				let price = data.shareboards[i].productPrice;
				let p = price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
	
				//스타일
				let s = data.shareboards[i].styleName;
				if(s == 'S1'){ s = "#Lovely" }
				else if(s == 'S2'){ s = "#Dandy" }else if(s == 'S3'){ s = "#Formal" }else if(s == 'S4'){ s = "#Street" }else if(s == 'S5'){ s = "#Girlish" }
				else if(s == 'S6'){ s = "#Retro" }else if(s == 'S7'){ s = "#Romantic" }else if(s == 'S8'){ s = "#Chic" }else if(s == 'S9'){ s = "#Amekaji" }
				
				if( parseInt(i/4) == 0){

					tr1.innerHTML += 
				      `<td>
						 <div style="width:280px;">
						 	<img src="${pageContext.request.contextPath}/image/heart.png" class="heartsempty" alt="좋아요"/> <!-- 하트 -->
						 	<a style="display:inline; margin-left: 150px" href="${pageContext.request.contextPath}/share/newShareView?no=\${data.shareboards[i].productId}">
					     	<img class="itemimg" src="${pageContext.request.contextPath}/uploadshares/newShare/\${data.shareAttachments[i].renamedFilename}" /></a>
					     </div>
					     <div id="categories" style="margin-left:10px;" >
					        <p>\${v}</p>
					    </div>
					     <div style="margin-left:10px;" >
					     	<p id="pn">[\${data.shareboards[i].productName}]</p>
					     	<p class="styles" >\${s}</p>
					     	<p><b>\${p}원</b></p>
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
						<div id="categories" style="margin-left:10px;" >
					        <p>\${v}</p>
					    </div>
						<div style="margin-left:10px;" >
					     	<p id="pn">[\${data.shareboards[i].productName}]</p>
					     	<p class="styles" >\${s}</p>
					     	<p><b>\${p}원</b></p>
					  	</div>
					 </td>`;
				}
				if( parseInt(i/4) == 2 ){
				  tr3.innerHTML += 
				    `<td><div style="width:280px">
					    <img src="${pageContext.request.contextPath}/image/heart.png" class="heartsempty" alt="좋아요"/> <!-- 하트 -->
					    <a style="display:inline; margin-left: 150px" href="${pageContext.request.contextPath}/share/newShareView?no=\${data.shareboards[i].productId}">
				  		<img class="itemimg" src="${pageContext.request.contextPath}/uploadshares/newShare/\${data.shareAttachments[i].renamedFilename}" /></a></div>
					     <div id="categories" style="margin-left:10px;" >
					        <p>\${v}</p>
					    </div>
					     <div style="margin-left:10px;" >
					     	<p id="pn">[\${data.shareboards[i].productName}]</p>
					     	<p class="styles" >\${s}</p>
					     	<b>\${p}원</b>
					  	</div>
					  </td>`;
				}
				
			}
		},//end-success
		complete(data){
			document.querySelector("#pagebar").innerHTML = "";
			document.querySelector("#countFilter").innerHTML  = "1";
			document.querySelector("#deleteAll").innerHTML = "모두삭제";

		}
	});//end-ajax	
}

</script>

 
<jsp:include page="/WEB-INF/views/common/footer.jsp" />