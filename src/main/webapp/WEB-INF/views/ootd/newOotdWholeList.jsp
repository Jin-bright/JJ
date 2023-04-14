<%@page import="com.sh.obtg.ootd.model.dto.OotdBoardandAttachment"%>
<%@page import="com.sh.obtg.member.model.dto.Member"%>
<%@page import="com.sh.obtg.ootd.model.dto.OotdBoard"%>
<%@page import="com.sh.obtg.ootd.model.dto.OotdAttachment"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>        
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ taglib uri ="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/ootdWholeList.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Noto+Sans+KR:wght@900&display=swap" rel="stylesheet">
<%
Member loginMember=(Member) session.getAttribute("loginMember");
//if( msg != null )
//	session.removeAttribute("msg");
// 리스트 가져오는거 필요함 
 List<OotdBoardandAttachment> ootdAttachments = ( List<OotdBoardandAttachment> )request.getAttribute("ootdAttachments");
 int totalPage = (int)request.getAttribute("totalPage");

%>
<style>
	#btn-more-two{
		display: none;
	}
</style>
<br /><br /><br />

<section id="board-container">
	<h2 id = "ootdboardlist" > Outfit Of The Day </h2>
	<br /><br /><br /><br />

	<div style="text-align: center;"  id="stdiv1" >
		<div>
			<img src="${pageContext.request.contextPath}/uploadootds/lovely.png" alt="" class="ootdsmallImg" data-style="S1" onclick="findStyle(this);" />
			<p class="ootdspan">#러블리</p>
		</div>
		<div>
			<img src="${pageContext.request.contextPath}/uploadootds/dandy.png" alt="" class="ootdsmallImg"  data-style="S2" onclick="findStyle(this);" />
			<p  class="ootdspan" >#댄디</p >	
		</div>
		<div>
			<img src="${pageContext.request.contextPath}/uploadootds/formal.png" alt="" class="ootdsmallImg" data-style="S3" onclick="findStyle(this);" />
			<p  class="ootdspan" >#포멀</p >
		</div>	
		<div>
			<img src="${pageContext.request.contextPath}/uploadootds/street.png" alt="" class="ootdsmallImg" data-style="S4" onclick="findStyle(this);" />
			<p  class="ootdspan" >#스트릿</p >
		</div>
		<div>
			<img src="${pageContext.request.contextPath}/uploadootds/girl.png" alt="" class="ootdsmallImg"   data-style="S5" onclick="findStyle(this);"/>
			<p  class="ootdspan"   > #걸리쉬</p >
		</div>
		<div>
			<img src="${pageContext.request.contextPath}/uploadootds/retro.png" alt="" class="ootdsmallImg"  data-style="S6" onclick="findStyle(this);"/>
			<p  class="ootdspan"  >#레트로</p>		
		</div>
		<div>
			<img src="${pageContext.request.contextPath}/uploadootds/roman.png" alt="" class="ootdsmallImg"  data-style="S7" onclick="findStyle(this);"/>		
			<p  class="ootdspan" >#로맨틱</p >
		</div>
		<div>
			<img src="${pageContext.request.contextPath}/uploadootds/chik.png" alt="" class="ootdsmallImg"   data-style="S8" onclick="findStyle(this);"/>
			<p  class="ootdspan" >#시크</p >
		</div>
		<div>
			<img src="${pageContext.request.contextPath}/uploadootds/ame.png" alt="" class="ootdsmallImg"    data-style="S9" onclick="findStyle(this);" />
			<p  class="ootdspan" >#아메카지</p >
		</div>
	</div>	 

	<div  id="divWriteView">
		<div>
			<span id="defaultView" style="text-align: right"> 최신순 &nbsp;&nbsp;&nbsp;</span>  <span id="countView" >  조회순 </span>
		</div>
		
		<div id="divadd">	
			<% if(loginMember != null){ %>
				<input type="button" value="글쓰기" id="btnAdd"  
					onclick="location.href='${pageContext.request.contextPath}/ootd/newOotdEnroll';"/> <%-- get&post다있는데/ 로그인한 상태에서만 노출 되게 수정해야됨 --%> 
			<% } %>
		</div>	
	</div>	
	
	<%-- ★여기 모든 내용이 들어온다★ --%>
	<div id="bigContainer">
		
	</div>
	<%-- 제일 기본 조회 시 더보기 버튼 && 히트순  --%>
	<div id='btn-more-container' style="width:1300px; margin: auto !important; text-align: center" >
		<button id="btn-more" value="" > MORE OOTD <br/> (<span id="page"> </span>/<span id="totalPage"><%=totalPage%></span>) </button>
		<button id="btn-more-two" value="" > HIT MORE OOTD <br/> (<span id="page" class="ppp"> </span>/<span id="totalPage"><%=totalPage%></span>) </button>		
	</div>

</section>



<%-- style로 필터 시  더보기 버튼 --%>
<div class="btbca" style="text-align: center;	">
	<button id="search-btn-more"  class="btn" style="margin: auto; visibility: hidden;" > MORE STYLE <span id="searchPage" >1</span> </button>	
</div>


<script>
	window.addEventListener('load', () => {
		//첫페이지 내용 로드 --- 최신순
		getPage(1);
		
		const dview = document.querySelector("#defaultView");
		dview.style.fontWeight = "bold"
		
	});

// 최신순 
	document.querySelector("#defaultView").addEventListener('click', () => {
		const bigContainer =  document.querySelector("#bigContainer");
		bigContainer.innerHTML = "";
		
		getPage(1);
		
		const btct = document.querySelector("#btn-more-two");
		btct.style.display ="none";
		
		//글꼴바꿈
		const view = document.querySelector("#countView");
		view.style.fontWeight = "500";
	
		const dview = document.querySelector("#defaultView");
		dview.style.fontWeight = "bold";
	});

	// 최신순
	const getPage = (page) => {
		
		const button = document.querySelector("#btn-more");
		button.disabled = false; // 최신 -조회 - 조회 이런식으로 다시 돌아올수잇으니까  
		button.style.display = "inline";
		button.style.cursor = "pointer";
		
		
		$.ajax({
			url : "${pageContext.request.contextPath}/ootd/morePage",
			method : "get",
			data : {page}, //page : 1 이런식으로.. ?
			dataType : "json",
		
			success(data){
				console.log( data );
				
				const bigContainer =  document.querySelector("#bigContainer");
	
				const table =  document.createElement("table");
				table.setAttribute("id", "itemTable");
	
				const tbody =  document.createElement("tbody");
				table.append( tbody );
				
				const tr1 =  document.createElement("tr");
				tbody.append( tr1 );
				
				const tr2 =  document.createElement("tr");
				tbody.append( tr2 );
				
				const tr3 =  document.createElement("tr");
				tbody.append( tr3 );
				
				for(let i =0; i<data.length; i++){
					
					
	
					//스타일
					let s = data[i].styleNo;
					if(s == 'S1'){ s = "#Lovely" }
					else if(s == 'S2'){ s = "#Dandy" }else if(s == 'S3'){ s = "#Formal" }else if(s == 'S4'){ s = "#Street" }else if(s == 'S5'){ s = "#Girlish" }
					else if(s == 'S6'){ s = "#Retro" }else if(s == 'S7'){ s = "#Romantic" }else if(s == 'S8'){ s = "#Chic" }else if(s == 'S9'){ s = "#Amekaji" }
	
					
					if( parseInt(i/4) == 0){
	
						tr1.innerHTML += 
					      `<td>
							 <div class = "imgdiv" >
							   <a style="display:inline; margin-left: 150px" href="${pageContext.request.contextPath}/ootd/newOotdView?no=\${data[i].ootdNo}">
						     	<img class="itemimg" src="${pageContext.request.contextPath}/uploadootds/ootd/\${data[i].renamedFilename}" /></a>
							  </div>
							 <div class = "ootdExplainDiv" >
							   <img src="${pageContext.request.contextPath}/uploadootds/ootd/profile.png" id="profileImg" alt="profileImg" />
							   <p id="writerSp">\${data[i].ootdWriter}</p> <span class="styleSp" >\${s}</span>  <br />
							   <span class="exs">#\${data[i].OOTDTitle} #\${data[i].OOTDTop} #\${data[i].OOTDBottom} #\${data[i].OOTDShoes} </span>
							   <p  align="right" style="margin-top:10px; font-size : 13px; color : gray"> 조회수 | \${data[i].ootdReadCount}</p>
							 </div>
						  </td>`;					
					}
					
					if( parseInt(i/4) == 1){
	
						tr2.innerHTML += 
					      `<td>
							 <div class = "imgdiv" >
							   <a style="display:inline; margin-left: 150px" href="${pageContext.request.contextPath}/ootd/newOotdView?no=\${data[i].ootdNo}">
						     	<img class="itemimg" src="${pageContext.request.contextPath}/uploadootds/ootd/\${data[i].renamedFilename}" /></a>
							  </div>
							 <div class = "ootdExplainDiv" >
							   <img src="${pageContext.request.contextPath}/uploadootds/ootd/profile.png" id="profileImg" alt="profileImg" />
							   <p id="writerSp">\${data[i].ootdWriter}</p> <span class="styleSp" >\${s}</span>  <br />
							   <span class="exs">#\${data[i].OOTDTitle} #\${data[i].OOTDTop} #\${data[i].OOTDBottom} #\${data[i].OOTDShoes} </span>
							   <p  align="right" style="margin-top:10px; font-size : 13px; color : gray"> 조회수 | \${data[i].ootdReadCount}</p>
							 </div>
						  </td>`;					
					}
					
					if( parseInt(i/4) == 2){
	
						tr3.innerHTML += 
					      `<td>
							 <div class = "imgdiv" >
							   <a style="display:inline; margin-left: 150px" href="${pageContext.request.contextPath}/ootd/newOotdView?no=\${data[i].ootdNo}">
						     	<img class="itemimg" src="${pageContext.request.contextPath}/uploadootds/ootd/\${data[i].renamedFilename}" /></a>
							  </div>
							 <div class = "ootdExplainDiv" >
							   <img src="${pageContext.request.contextPath}/uploadootds/ootd/profile.png" id="profileImg" alt="profileImg" />
							   <p id="writerSp">\${data[i].ootdWriter}</p> <span class="styleSp" >\${s}</span>  <br />
							   <span class="exs">#\${data[i].OOTDTitle} #\${data[i].OOTDTop} #\${data[i].OOTDBottom} #\${data[i].OOTDShoes} </span>
							   <p  align="right" style="margin-top:10px; font-size : 13px; color : gray"> 조회수 | \${data[i].ootdReadCount}</p>
							 </div>
						  </td>`;					
					}
				
					bigContainer.append(table);
				}
				
				
			},	
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
	

// 더보기버튼으로 호출했을 경우
	document.querySelector("#btn-more").addEventListener('click', () => {
		const page = document.querySelector("#page");
		getPage(Number(page.innerText) + 1); //Number("1") + 1  -> 2 
	});

	
</script>


<script>
/* 
document.querySelector("#btn-more").addEventListener('click', () => {
	const page = document.querySelector("#page");
	findStyle(Number(page.innerText) + 1); //Number("1") + 1  -> 2 
}); */

// 스타일 클릭하면 
const findStyle = (e) =>{
	console.log( e )

	//클릭시 색깔바꾸기
	let imgtag =  e; 
	let ptag = e.nextElementSibling;
	
	imgtag.style.border="3px solid #11D226";
	ptag.style.color="green";
	ptag.style.fontWeight="600";
	
	
	
    const btnMore = document.querySelector("#btn-more-container");
	btnMore.innerHTML = "";

	// 현재페이지는 1 
	let searchPage = document.querySelector("#searchPage").innerHTML;	
	console.log( "searchPage : ", searchPage );
	
	
	$.ajax({
		url:"${pageContext.request.contextPath}/ootd/ootdFinderbyStyleAj",
		method : "get",
		data : {	page : searchPage,
					searchKeyword : e.dataset.style,
				},
		success(data){
			console.log( data );
			
			//스타일
			let s = e.dataset.style;
			if(s == 'S1'){ s = "#Lovely" }
			else if(s == 'S2'){ s = "#댄디룩" }else if(s == 'S3'){ s = "#포멀룩" }else if(s == 'S4'){ s = "#스트릿룩" }else if(s == 'S5'){ s = "#걸리쉬룩" }
			else if(s == 'S6'){ s = "#레트로룩" }else if(s == 'S7'){ s = "#로맨틱룩" }else if(s == 'S8'){ s = "#시크룩" }else if(s == 'S9'){ s = "#아메카지룩" }

			
			const stdiv =  document.querySelector("#stdiv1");
			const stdiv2 =  document.querySelector("#stdiv2");

			document.querySelector("#defaultView").innerHTML = "";
			document.querySelector("#countView").innerHTML = "";
			
			//안에 내용 채우기
			const bigContainer =  document.querySelector("#bigContainer");
			bigContainer.innerHTML = "";

			const table =  document.createElement("table");
			table.setAttribute("id", "itemTable");

			const tbody =  document.createElement("tbody");
			table.append( tbody );
			
			const tr1 =  document.createElement("tr");
			tbody.append( tr1 );
			
			const tr2 =  document.createElement("tr");
			tbody.append( tr2 );
			
			const tr3 =  document.createElement("tr");
			tbody.append( tr3 );

			
			const div = document.querySelector("#divadd");
			const input =  document.createElement("input");
			
			if(input == null){
				input.setAttribute('type', 'button');
				input.setAttribute('class', 'inputcls');
				input.setAttribute('value', '목록보기');
				input.setAttribute( 'onclick', "location.href='${pageContext.request.contextPath}/ootd/newOotdWholeList'" );
				div.append( input );
			}


			//////////			
			for(let i =0; i<data.findootdAll.length; i++){
				console.log( data.findootdAll[i] )
				
				//스타일
				let s = data.findootdAll[i].styleNo;
				
				if(s == 'S1'){ s = "#Lovely" }
				else if(s == 'S2'){ s = "#Dandy" }else if(s == 'S3'){ s = "#Formal" }else if(s == 'S4'){ s = "#Street" }else if(s == 'S5'){ s = "#Girlish" }
				else if(s == 'S6'){ s = "#Retro" }else if(s == 'S7'){ s = "#Romantic" }else if(s == 'S8'){ s = "#Chic" }else if(s == 'S9'){ s = "#Amekaji" }
				
				if( parseInt(i/4) == 0){

					tr1.innerHTML += 
				      `<td style="height:630px">
						 <div class = "imgdiv" >
						   <a style="display:inline; margin-left: 150px" href="${pageContext.request.contextPath}/ootd/newOotdView?no=\${data.findootdAll[i].ootdNo}">
					     	<img class="itemimg" src="${pageContext.request.contextPath}/uploadootds/ootd/\${data.findootdAll[i].renamedFilename}" /></a>
						  </div>
						 <div class = "ootdExplainDiv" >
						   <img src="${pageContext.request.contextPath}/uploadootds/ootd/profile.png" id="profileImg" alt="profileImg" />
						   <p id="writerSp">\${data.findootdAll[i].ootdWriter}</p> <span class="styleSp" >\${s}</span>  <br />
						   <p class="exs">#\${data.findootdAll[i].OOTDTitle} #\${data.findootdAll[i].OOTDTop} #\${data.findootdAll[i].OOTDBottom} #\${data.findootdAll[i].OOTDShoes} </p>
						   <p  class="countpcl"> 조회수 | \${data.findootdAll[i].ootdReadCount}</p>
						 </div>
					  </td>`;					
				}
				
				if( parseInt(i/4) == 1){

					tr2.innerHTML += 
				      `<td style="height:630px">
						 <div class = "imgdiv" >
						   <a style="display:inline; margin-left: 150px" href="${pageContext.request.contextPath}/ootd/newOotdView?no=\${data.findootdAll[i].ootdNo}">
					     	<img class="itemimg" src="${pageContext.request.contextPath}/uploadootds/ootd/\${data.findootdAll[i].renamedFilename}" /></a>
						  </div>
						 <div class = "ootdExplainDiv" >
						   <img src="${pageContext.request.contextPath}/uploadootds/ootd/profile.png" id="profileImg" alt="profileImg" />
						   <p id="writerSp">\${data.findootdAll[i].ootdWriter}</p> <span class="styleSp" >\${s}</span>  <br />
						   <p class="exs">#\${data.findootdAll[i].OOTDTitle} #\${data.findootdAll[i].OOTDTop} #\${data.findootdAll[i].OOTDBottom} #\${data.findootdAll[i].OOTDShoes} </p>
						   <p  class="countpcl"> 조회수 | \${data.findootdAll[i].ootdReadCount}</p>
						  </div>
					  </td>`;					
				}
				
				if( parseInt(i/4) == 2){

					tr3.innerHTML += 
				      `<td style="height:630px">
						 <div class = "imgdiv" >
						   <a style="display:inline; margin-left: 150px" href="${pageContext.request.contextPath}/ootd/newOotdView?no=\${data.findootdAll[i].ootdNo}">
					     	<img class="itemimg" src="${pageContext.request.contextPath}/uploadootds/ootd/\${data.findootdAll[i].renamedFilename}" /></a>
						  </div>
						 <div class = "ootdExplainDiv" >
						   <img src="${pageContext.request.contextPath}/uploadootds/ootd/profile.png" id="profileImg" alt="profileImg" />
						   <p id="writerSp">\${data.findootdAll[i].ootdWriter}</p> <span class="styleSp" >\${s}</span>  <br />
						   <p class="exs">#\${data.findootdAll[i].OOTDTitle} #\${data.findootdAll[i].OOTDTop} #\${data.findootdAll[i].OOTDBottom} #\${data.findootdAll[i].OOTDShoes} </p>
						   <p class="countpcl"> 조회수 | \${data.findootdAll[i].ootdReadCount}</p>
						 </div>
					  </td>`;					
				}
			
				bigContainer.append(table);

			}//end-for문 
			
			// 더보기 버튼 보인다  
	 		if(  data.styletotalPage > 1 ){								 
				document.querySelector("#search-btn-more").style.visibility="visible";
				document.querySelector("#searchPage").innerHTML = Number(searchPage)+1 // 다음페이지셋팅 
	 		}
			
			if( searchPage >= data.styletotalPage  ){ //버튼없애기
				  const button = document.querySelector("#search-btn-more");
						document.querySelector("#searchPage").innerHTML = 1;
						document.querySelector("#search-btn-more").style.visibility="hidden";
						document.querySelector("#search-btn-more").style.disabled="true";
			}
		},//end -success
		complete(){
			
			setTimeout( function(){
				imgtag.style.border= "none";
				ptag.style = "none";
			}, 2000);
			
		}
	});
	

	//더보기버튼으로 호출했을 경우  - find함수안끝났음 
	document.querySelector("#search-btn-more").addEventListener('click', () => {
		const page = document.querySelector("#searchPage");
		findStyle(e);
//		findStyle(Number(page.innerText) + 1); //Number("1") + 1  -> 2 
	});


}
</script>


<script>
// 조회순 - 조회  
document.querySelector("#countView").addEventListener('click', () => {
	//글꼴
	const view = document.querySelector("#countView");
	view.style.fontWeight = "bolder";
	
	const dview = document.querySelector("#defaultView");
	dview.style.fontWeight = "300";
	
    const bigContainer = document.querySelector("#bigContainer");
    bigContainer.innerHTML = "";
    
    const btmore = document.querySelector("#btn-more"); //btn-more btn-more-container
    btmore.style.display = "none";
    
    const btmoreTwo = document.querySelector("#btn-more-two");
    btmoreTwo.style.display = "inline";
    
	const ppp = document.querySelector(".ppp");
	ppp.innerText = 1;
	
	getCountViewPage(1);
});


//조회순 조회 
const getCountViewPage = (page) => {
	
	const button = document.querySelector("#btn-more-two");
	button.disabled = false; // 최신 -조회 - 조회 이런식으로 다시 돌아올수잇으니까  
	button.style.cursor = "pointer";
	
	
	$.ajax({
		url : "${pageContext.request.contextPath}/ootd/countMorePage",
		method : "get",
		data : {page}, //page : 1 이런식으로.. ?
		dataType : "json",
	
		success(data){
			console.log( data );
			
			const bigContainer =  document.querySelector("#bigContainer");

			const table =  document.createElement("table");
			table.setAttribute("id", "itemTable");

			const tbody =  document.createElement("tbody");
			table.append( tbody );
			
			const tr1 =  document.createElement("tr");
			tbody.append( tr1 );
			
			const tr2 =  document.createElement("tr");
			tbody.append( tr2 );
			
			const tr3 =  document.createElement("tr");
			tbody.append( tr3 );
			
			for(let i =0; i<data.length; i++){
				
				

				//스타일
				let s = data[i].styleNo;
				if(s == 'S1'){ s = "#Lovely" }
				else if(s == 'S2'){ s = "#Dandy" }else if(s == 'S3'){ s = "#Formal" }else if(s == 'S4'){ s = "#Street" }else if(s == 'S5'){ s = "#Girlish" }
				else if(s == 'S6'){ s = "#Retro" }else if(s == 'S7'){ s = "#Romantic" }else if(s == 'S8'){ s = "#Chic" }else if(s == 'S9'){ s = "#Amekaji" }

				
				if( parseInt(i/4) == 0){

					tr1.innerHTML += 
				      `<td>
						 <div class = "imgdiv" >
						   <a style="display:inline; margin-left: 150px" href="${pageContext.request.contextPath}/ootd/newOotdView?no=\${data[i].ootdNo}">
					     	<img class="itemimg" src="${pageContext.request.contextPath}/uploadootds/ootd/\${data[i].renamedFilename}" /></a>
						  </div>
						 <div class = "ootdExplainDiv" >
						   <img src="${pageContext.request.contextPath}/uploadootds/ootd/profile.png" id="profileImg" alt="profileImg" />
						   <p id="writerSp">\${data[i].ootdWriter}</p> <span class="styleSp" >\${s}</span>  <br />
						   <p class="exs">#\${data[i].OOTDTitle} #\${data[i].OOTDTop} #\${data[i].OOTDBottom} #\${data[i].OOTDShoes} </p>
						   <p  align="right" style="margin-top:10px; font-size : 13px; color : gray"> 조회수 | \${data[i].ootdReadCount}</p>
						 </div>
					  </td>`;					
				}
				
				if( parseInt(i/4) == 1){

					tr2.innerHTML += 
				      `<td>
						 <div class = "imgdiv" >
						   <a style="display:inline; margin-left: 150px" href="${pageContext.request.contextPath}/ootd/newOotdView?no=\${data[i].ootdNo}">
					     	<img class="itemimg" src="${pageContext.request.contextPath}/uploadootds/ootd/\${data[i].renamedFilename}" /></a>
						  </div>
						 <div class = "ootdExplainDiv" >
						   <img src="${pageContext.request.contextPath}/uploadootds/ootd/profile.png" id="profileImg" alt="profileImg" />
						   <p id="writerSp">\${data[i].ootdWriter}</p> <span class="styleSp" >\${s}</span>  <br />
						   <p class="exs">#\${data[i].OOTDTitle} #\${data[i].OOTDTop} #\${data[i].OOTDBottom} #\${data[i].OOTDShoes} </p>
						   <p  align="right" style="margin-top:10px; font-size : 13px; color : gray"> 조회수 | \${data[i].ootdReadCount}</p>
						 </div>
					  </td>`;					
				}
				
				if( parseInt(i/4) == 2){

					tr3.innerHTML += 
				      `<td>
						 <div class = "imgdiv" >
						   <a style="display:inline; margin-left: 150px" href="${pageContext.request.contextPath}/ootd/newOotdView?no=\${data[i].ootdNo}">
					     	<img class="itemimg" src="${pageContext.request.contextPath}/uploadootds/ootd/\${data[i].renamedFilename}" /></a>
						  </div>
						 <div class = "ootdExplainDiv" >
						   <img src="${pageContext.request.contextPath}/uploadootds/ootd/profile.png" id="profileImg" alt="profileImg" />
						   <p id="writerSp">\${data[i].ootdWriter}</p> <span class="styleSp" >\${s}</span>  <br />
						   <p class="exs">#\${data[i].OOTDTitle} #\${data[i].OOTDTop} #\${data[i].OOTDBottom} #\${data[i].OOTDShoes} </p>
						   <p  align="right" style="margin-top:10px; font-size : 13px; color : gray"> 조회수 | \${data[i].ootdReadCount}</p>
						 </div>
					  </td>`;					
				}
			
				bigContainer.append(table);
			}
			//마지막 페이지인 경우 더보기 버튼 비활성화 처리 ★
			if( page >= <%=totalPage%>){
				const button = document.querySelector("#btn-more-two");
				button.disabled = true; // 리턴값이 boolean 값 
				button.style.cursor = "not-allowed";
				
			}
			
		},	
		error : console.log, 
		complete(){
			document.querySelector(".ppp").innerHTML = page;

		}
	});	//ajax
}	

//더보기버튼 눌렀을때
document.querySelector("#btn-more-two").addEventListener('click', () => {
	const page = document.querySelector(".ppp");
	getCountViewPage(Number(page.innerText) + 1); //Number("1") + 1  -> 2 
});

</script>


<br /><br /><br /><br />
<jsp:include page="/WEB-INF/views/common/footer.jsp" />