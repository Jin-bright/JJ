<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/ootdList.css" />
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Noto+Sans+KR:wght@900&display=swap" rel="stylesheet"><link rel="stylesheet" href="<%=request.getContextPath()%>/css/ootdEnroll.css" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<section id="board-container2">
<br />
<img src="<%=request.getContextPath()%>/uploadootds/color.png" style = "width : 1200px; height:100px; margin-left : 330px;">
<br />
	<h2 id = "ootdboardlist" > Outfit Of The Day </h2>
	<h3 id="ootdboardlist2"> 오늘 입은 옷을 공유해보세요! </h3>
<input type="button" value="글쓰기" id="btn-add" style="margin-right : 450px"
	onclick="location.href='<%=request.getContextPath()%>/ootd/ootdEnroll';"/> <%-- get&post다있는데/ 로그인한 상태에서만 노출 되게 수정해야됨 --%> 
	<br />	<br />	<br />
	
	<table id="type11">  
	  <tr id="trone">
		  <!--  여기 동적태그 생긴다  -->
	  </tr>
	  
	  <tr id="trtwo">
		  <!--  여기 동적태그 생긴다  -->
	  </tr id="trthree">
	  
	  <tr>
		  <!--  여기 동적태그 생긴다  -->
	  </tr id="trfour">
	  
	  <tr>
		  <!--  여기 동적태그 생긴다  -->
	  </tr>
	 
	</table>
<div id="pagebar" style="background-color: gray;">
		<%=(String)request.getAttribute("pagebar")%>
</div>

<script> 
// 테스트 
document.querySelector(".cPage").addEventListener('click', () => {
	const page = document.querySelector(".cPage");
	getPage(Number(page.innerText)); //Number("1") 
});

const getPage = (page) => {
	$.ajax({
		url : "<%=request.getContextPath()%>/ootd/ootdMoreList",
		method : "get",
		data : {page}, //page : 1 이런식으로.. ?
		dataType : "json",
		
		success(data){
			console.log( data );
			
			const tr =  document.querySelector("#trone");
			const tr2 =  document.querySelector("#trtwo");
			
			tr.innerHTML = "";

			data.forEach( (list, index) => {
				tr.innerHTML += 
					`<th class="newtablerowth" style="margin : 15px">
						<img src="<%=request.getContextPath()%>/uploadootds/ootd/\${list.renamedFilename}"  >
						<div class="explain"><p>NO : \${list.boardNo}</p>
							 <p><span style="color:orange">N </span> \${list.regDate}</p>
						</div>
					 </th>`;			 
			});
			<%--
				for( let i =1; i<data.length; i++){
					if( i<=(3*i)){
						tr.innerHTML += 
							`<th class="newtablerowth" style="margin : 15px">
								<img src="<%=request.getContextPath()%>/uploadootds/ootd/\${data[i].renamedFilename}"  >
								<div class="explain"><p>NO : \${data[i].boardNo}</p>
									 <p><span style="color:orange">N </span> \${data[i].regDate}</p>
								</div>
							 </th>`;
					} 
					
					else if ( i < 6) {
						tr2.innerHTML += 
							`<th  class="newtablerowth" >
								<img src="<%=request.getContextPath()%>/uploadootds/ootd/\${data[i].renamedFilename}" >
								<div class="explain"><p>NO : \${data[i].boardNo}</p>
								 	<p><span style="color:orange">N </span> \${data[i].regDate}</p>
								</div>
							</th>`;					
					}	
				}//
		 --%>
			
		},
		error : console.log, 
		complete(){
		//	document.querySelector(".cpage").innerHTML = page;
			//마지막 페이지인 경우 더보기 버튼 비활성화 처리 ★
		<%-- 	if( page === <%=totalPage%>){   --%>
		//		const button = document.querySelector("#pagebar");
		//		button.disabled = true; // 리턴값이 boolean 값 
		//		button.style.cursor = "not-allowed";
		//	}
		}
	});//ajax
}
window.addEventListener('load', () => {
	//첫페이지 내용 로드 
	getPage(1);
	
});

</script>
  
  
<%--   
<script>
window.addEventListener('load', () => {
	//첫페이지 내용 로드 
	getPage(1);
	
});


document.querySelector("#btn-more").addEventListener('click', () => {
	const page = document.querySelector("#page");
	getPage(Number(page.innerText) + 1); //Number("1") + 1  -> 2 
});

const getPage = (page) => {
	$.ajax({
		url : "<%=request.getContextPath()%>/ootd/ootdMoreList",
		method : "get",
		data : {page}, //page : 1 이런식으로.. ?
		dataType : "json",
		
		success(data){
			console.log( data );
			
			const divContainer =  document.querySelector("#photo-container");
			data.forEach( (list, index) => {
				
				divContainer.innerHTML += `
					<div class="cardEach" style="margin-bottom : 30px;">
						<img src="<%=request.getContextPath()%>/uploadootds/ootd/\${list.renamedFilename}" class="card-img-top">
						 <div class="card-body">
						    <h5 class="card-title">\${list.boardNo}</h5>
						    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
						  <!--   <a href="#" class="btn btn-primary">Go somewhere</a>  -->
						 </div>
					</div>`;
			});
		}, //success 
		error : console.log, 
		complete(){
				document.querySelector("#page").innerHTML = page;
				//마지막 페이지인 경우 더보기 버튼 비활성화 처리 ★
				if( page === <%=totalPage%>){  
					const button = document.querySelector("#btn-more");
					button.disabled = true; // 리턴값이 boolean 값 
					button.style.cursor = "not-allowed";
				}
			}
		});	//ajax
	}
		
</script>
--%>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>