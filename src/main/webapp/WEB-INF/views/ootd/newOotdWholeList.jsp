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
#btn-more-container-two, #scrtop{
	display: none;
}


</style>
<br /><br /><br />

<section id="board-container">
	<h2 id = "ootdboardlist" > Outfit Of The Day </h2>
	<br /><br /><br /><br />
	
	<div style="text-align: center;"  id="stdiv1" >
		<img src="${pageContext.request.contextPath}/uploadootds/lovely.png" alt="" class="ootdsmallImg" data-style="S1" onclick="findStyle(this);" />
		<img src="${pageContext.request.contextPath}/uploadootds/dandy.png" alt="" class="ootdsmallImg"  data-style="S2" onclick="findStyle(this);" />
		<img src="${pageContext.request.contextPath}/uploadootds/formal.png" alt="" class="ootdsmallImg" data-style="S3" onclick="findStyle(this);" />
		<img src="${pageContext.request.contextPath}/uploadootds/street.png" alt="" class="ootdsmallImg" data-style="S4" onclick="findStyle(this);" />
		<img src="${pageContext.request.contextPath}/uploadootds/girl.png" alt="" class="ootdsmallImg"   data-style="S5" onclick="findStyle(this);"/>
		<img src="${pageContext.request.contextPath}/uploadootds/retro.png" alt="" class="ootdsmallImg"  data-style="S6" onclick="findStyle(this);"/>
		<img src="${pageContext.request.contextPath}/uploadootds/roman.png" alt="" class="ootdsmallImg"  data-style="S7" onclick="findStyle(this);"/>		
		<img src="${pageContext.request.contextPath}/uploadootds/chik.png" alt="" class="ootdsmallImg"   data-style="S8" onclick="findStyle(this);"/>
		<img src="${pageContext.request.contextPath}/uploadootds/ame.png" alt="" class="ootdsmallImg"    data-style="S9" onclick="findStyle(this);" />
	</div>
	<div style="text-align: center; margin-bottom: 70px; "  id="stdiv2">
		<span class="ootdspan"  style="margin-left: 58px; margin-right: 50px; ">#러블리</span>		
		<span class="ootdspan" style="margin-right: 50px; ">#댄디</span>	
		<span class="ootdspan" >#포멀</span>
		<span class="ootdspan" style="margin-right: 40px; " >#스트릿</span>
		<span class="ootdspan" style="margin-right: 40px; "  > #걸리쉬</span>
		<span class="ootdspan" style="margin-right: 45px; " >#레트로</span>
		<span class="ootdspan" >#로맨틱</span>
		<span class="ootdspan" >#시크</span>
		<span class="ootdspan" style="margin-left: -20px" >#아메카지</span>
	</div>
	<span id="defaultView" style="margin-left: 1390px;"> 최신순 &nbsp;&nbsp;&nbsp;</span>  <span id="countView" >  조회순 </span>
	
	<% if(loginMember != null){ %>
		<input type="button" value="글쓰기" id="btnAdd" 
			onclick="location.href='${pageContext.request.contextPath}/ootd/newOotdEnroll';"/> <%-- get&post다있는데/ 로그인한 상태에서만 노출 되게 수정해야됨 --%> 
	<% } %>
	
	<div id="bigContainer">
	
	</div>

	<div id='btn-more-container'>
		<button id="btn-more" value="" > MORE OOTD <br/> (<span id="page"> </span>/<span id="totalPage"><%=totalPage%></span>) </button>
	</div>
	
	<div id='btn-more-container-two'>
		<button id="btn-more-two" value="" > HIT MORE OOTD <br/> (<span id="page" class="ppp"> </span>/<span id="totalPage"><%=totalPage%></span>) </button>
	</div>
	<button id="scrtop"><img id="scrtopimg" src="${pageContext.request.contextPath}/image/up.png"  /></button>
</section>




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
	
	const btct = document.querySelector("#btn-more-container-two");
	btct.style.display ="none";
	
	const btc = document.querySelector("#btn-more-container");
	btc.style.display ="inline";
	
	//글꼴바꿈
	const view = document.querySelector("#countView");
	view.style.fontWeight = "500";

	const dview = document.querySelector("#defaultView");
	dview.style.fontWeight = "bold";

	
	
});


document.querySelector("#btn-more").addEventListener('click', () => {
	const page = document.querySelector("#page");
	getPage(Number(page.innerText) + 1); //Number("1") + 1  -> 2 
});

const getPage = (page) => {
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
</script>


<script>
const findStyle = (e) =>{
	console.log( e);


    const btnMore = document.querySelector("#btn-more-container");
	btnMore.innerHTML = "";
	
	$.ajax({
		url:"${pageContext.request.contextPath}/ootd/ootdFinderbyStyleAj",
		method : "get",
		data : {searchKeyword : e.dataset.style,
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
			stdiv.innerHTML = "";
			stdiv.append(e);
			stdiv2.innerHTML  = `<span class="ootdspantw" >\${s}</span>`;
			
			
			// 	<span id="defaultView" style="margin-left: 1390px;"> 최신순</span> <span style="color:light-gray;"> |  </span> <span id="countView" > 조회순 </span>

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
						   <span> 조회수 | \${data[i].ootdReadCount}</span>
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
						 </div>
					  </td>`;					
				}
			
				bigContainer.append(table);
			}
			
			
			
		}
	});
	
}

// 조회순 함수 
// 최신순이 1 2 3 페이지든 상관없이 무조건 조회순누르면 1페이지 부터 조회하게 어떻게 ? 

document.querySelector("#countView").addEventListener('click', () => {
	//글꼴
	const view = document.querySelector("#countView");
	view.style.fontWeight = "bolder";
	
	const dview = document.querySelector("#defaultView");
	dview.style.fontWeight = "300";
	
	
    const bigContainer = document.querySelector("#bigContainer");
    bigContainer.innerHTML = "";
    
    const btmore = document.querySelector("#btn-more-container");
    btmore.style.display = "none";
    
    const btmoreTwo = document.querySelector("#btn-more-container-two");
    btmoreTwo.style.display = "inline";
    
	
	const ppp = document.querySelector(".ppp");
	ppp.innerText = 1;
	
	getCountViewPage(1);

	
//	getCountViewPage(Number(page.innerText) + 1); //Number("1") + 1  -> 2 
});

document.querySelector("#btn-more-two").addEventListener('click', () => {
	const page = document.querySelector(".ppp");
	getCountViewPage(Number(page.innerText) + 1); //Number("1") + 1  -> 2 
});


const getCountViewPage = (page) => {
	
	
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
			document.querySelector(".ppp").innerHTML = page;
			//마지막 페이지인 경우 더보기 버튼 비활성화 처리 ★
			if( page === <%=totalPage%>){
				const button = document.querySelector("#btn-more-two");
				button.disabled = true; // 리턴값이 boolean 값 
				button.style.cursor = "not-allowed";
				
				const scrtop = document.querySelector("#scrtop");
				scrtop.style.display = 'inline';
				
				scrtop.addEventListener( 'click', () =>{
				  $("html, body").animate({ scrollTop: 0 }, "slow");
				  return false;

				})	
				
			}
		}
	});	//ajax
}	
</script>


<br /><br /><br /><br />
<jsp:include page="/WEB-INF/views/common/footer.jsp" />