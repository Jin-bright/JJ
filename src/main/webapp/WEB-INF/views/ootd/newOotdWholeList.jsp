<%@page import="com.sh.obtg.ootd.model.dto.OotdBoardandAttachment"%>
<%@page import="com.sh.obtg.member.model.dto.Member"%>
<%@page import="com.sh.obtg.ootd.model.dto.OotdBoard"%>
<%@page import="com.sh.obtg.ootd.model.dto.OotdAttachment"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

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
			onclick="location.href='<%=request.getContextPath()%>/ootd/ootdEnroll';"/> <%-- get&post다있는데/ 로그인한 상태에서만 노출 되게 수정해야됨 --%> 
	<% } %>

	<div id='btn-more-container'>
		<button id="btn-more" value="" > 더보기( <span id="page"></span> / <span id="totalPage"><%=totalPage%></span> ) </button>
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
<%-- 
<table id="tblBoard">

 <% for(int i=0; i<ootdAttachments.size(); i++){ 
 		if(i%5==0){%>
 		<tr>
 	<% } %>
     <td class="maketd" style= "height : 300px; width:190px;">
     	<a class="atags" style="display :inline;" href="<%=request.getContextPath()%>/ootd/ootdView?no=<%=ootdAttachments.get(i).getBoardNo() %>">
     	<img style=" margin-left : 7px"  src="<%=request.getContextPath()%>/uploadootds/ootd/<%=ootdAttachments.get(i).getRenamedFilename()%>" ></a><br/>
		<p class="non">NO <span style="color : black; font-weight : light"><%=ootdAttachments.get(i).getAttachNo()%></span></p>
		<p class="non">N  <span style=" color : black; font-weight : light"><%=ootdAttachments.get(i).getRegDate()%></span></p>
     </td>  
     <% if(i%5==4){%>
 		</tr>
 	 <% }
   }%>
</table>
--%>

<br /><br /><br /><br />
<jsp:include page="/WEB-INF/views/common/footer.jsp" />