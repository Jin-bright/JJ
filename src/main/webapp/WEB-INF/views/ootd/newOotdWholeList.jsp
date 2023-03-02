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
<br /><br /><br />

<section id="board-container">
	<h2 id = "ootdboardlist" > Outfit Of The Day </h2>
	<br /><br /><br /><br /><br /><br />

	<% if(loginMember != null){ %>
		<input type="button" value="글쓰기" id="btnAdd" 
			onclick="location.href='<%=request.getContextPath()%>/ootd/newOotdEnroll';"/> <%-- get&post다있는데/ 로그인한 상태에서만 노출 되게 수정해야됨 --%> 
	<% } %>
	
	<div id="bigContainer">
	
	</div>

	<div id='btn-more-container'>
		<button id="btn-more" value="" > MORE OOTD <br/> (<span id="page"></span>/<span id="totalPage"><%=totalPage%></span>) </button>
	</div>
</section>

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


<br /><br /><br /><br />
<jsp:include page="/WEB-INF/views/common/footer.jsp" />