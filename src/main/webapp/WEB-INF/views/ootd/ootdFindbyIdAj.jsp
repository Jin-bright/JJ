<%@page import="com.sh.obtg.ootd.model.dto.OotdBoardandAttachment"%>
<%@page import="com.sh.obtg.ootd.model.dto.OotdBoard"%>
<%@page import="com.sh.obtg.ootd.model.dto.OotdAttachment"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/header.jsp" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/ootdWholeList.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Noto+Sans+KR:wght@900&display=swap" rel="stylesheet"><link rel="stylesheet" href="<%=request.getContextPath()%>/css/ootdEnroll.css" />
<%--<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script> --%>

<%
	// String msg = (String)request.getAttribute("msg");

	String msgnull  = (String)request.getAttribute("msgnull");
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
	List<OotdBoardandAttachment> ootdboardAndAttachments = (List<OotdBoardandAttachment>)request.getAttribute("ootdboardAndAttachments");
//	List<OotdBoard> findootdBoardsById = (List<OotdBoard> )request.getAttribute("findootdBoardsById");
//int totalPage = (int)request.getAttribute("totalPage");
// List<OotdAttachment> ootdAttachments = (List<OotdAttachment>)request.getAttribute("ootdAttachments");
// OotdBoard ootdboard = (OotdBoard) request.getAttribute("ootdboard");
%>
 <script>
window.addEventListener('load', () => {	
	<% if( msgnull != null) {%> 
		alert("<%=msgnull%>"); 
	// alert( 사이에 ""이거 없으면  )이렇게쓰면 alert( 아이디가 존재하지 않거나 비밀번호가 틀립니다) <-- 이뜻임 
	<% } %>		
});
</script>

<br /><br /><br />
<section id="board-container">
<h2 id = "ootdboardlist"  style="margin : 0 auto;"> Outfit Of The Day </h2>
<%--  <h3 id="ootdboardlist2"> 오늘 입은 옷을 공유해보세요! </h3> --%> 
<br /><br />
<%----  검색상자  --%>
<style>
 div#search-container {
 	width: 180px; 
 	margin: 0 0 10px 0; 
 	padding:3px; 
 	background-color: white;
	float : left;
	margin-top : -360px;
	margin-left : 70px;
	font-family: 'Nanum Gothic Coding', monospace;		
	font-size : 15px;
 }

 
#searchTable{
 	margin : 0 auto;
 	margin-top : -30px;
	
	border-spacing : 30px;
	border-collapse: separate;

	table-layout:fixed; 
	border : none;

	/* width:800px;  
	height : 1500px; */
	padding : 0px;
	margin-left : 430px;
	
 }
 
td{
	margin-bottom : 20px;

}

#photo-wrapper{
	height : 200px
}

#goback{
	margin-top : -60px;
	margin-left : 800px;
	background-color : black;
	width : 110px;
	height : 30px;
	color : white;
}

#goback:hover{
	background-color : white;
	height : 30px;
	color : black;
	transition-duration : 0.5s;
	font-weight: bolder
}
 
</style>
 
<br /><br /><br /> 
 <section id="photo-wrapper">
	<h2> 검색결과 </h2> <br/>
	<h4> 아이디 &nbsp; <span style="color : orange; font-size:18px"><%=searchKeyword%>&nbsp; </span> (으)로 검색한 결과입니다 😊 </h4>
	<br /><br /><br />
	<hr />	<br /><br /><br />
	
	<div id='btn-more-container'>
		<button id ="goback" onclick="history.go(-1)" > 목록으로 가기 </button>
	</div>
	<br /><br /><br /><br /><br />
</section>


 <script>

 
 window.addEventListener('load', () => {
	 document.querySelector("#searchType").addEventListener('change', (e) => {
		 console.log( e.target.value ); //여기 나오는 값이 : member_id, 스타일 
		 
		 document.querySelectorAll(".search-type").forEach( (div) => {
			 div.style.display = "none"; // 일단 다 감춰놓기 (모두숨김 )
		 });
		 
		 //현재 선택된 값에 상응하는 div만 노출시키기 
		 let id;
		 
		 switch(e.target.value){
		 case "ootd_writer" : 
			 id = "search-memberId";
			 break;
			 
		 case "member_name" : 
			 id = "search-memberName";
			 break;
		 }
		 document.querySelector("#" + id ).style.display = "inline-block";
		
	 })
 })
 
 </script>


<%-- <% if(loginMember != null){ %>
<input type="button" value="글쓰기" id="btnAdd" 
	onclick="location.href='<%=request.getContextPath()%>/ootd/ootdEnroll';"/> get&post다있는데/ 로그인한 상태에서만 노출 되게 수정해야됨 
<% } %>
 --%>

<table id="searchTable">
<% for(int i=0; i< ootdboardAndAttachments.size(); i++){ 
	if(i%2==0){%>
	<tr>
<% } %>
	<td class="maketd" style= "height : 300px; width:190px;">
	  <a class="atags" style="display :inline;" href="<%=request.getContextPath()%>/ootd/ootdView?no=<%=ootdboardAndAttachments.get(i).getOotdNo()%>">
	  <img style="margin-left:7px" src="<%=request.getContextPath()%>/uploadootds/ootd/<%=ootdboardAndAttachments.get(i).getRenamedFilename()%>" /></a>
	  <p class="non">NO <span style="color : black; font-weight : light"><%=ootdboardAndAttachments.get(i).getOotdNo()%></span></p>
	  <p class="non">N  <span style=" color : black; font-weight : light"><%=ootdboardAndAttachments.get(i).getOOTDRegDate()%></span></p>
	</td>
	<td style="width:200px"> </td>
<%  if(i%2==1){%>
	</tr>
<% }
} %>
</table>
<br /><br /><br /><br /><br />
</section>





<script>
<script>
window.addEventListener('load', () => {	
	<% if( msg != null) {%> 
		alert("<%=msg%>"); 
	// alert( 사이에 ""이거 없으면  )이렇게쓰면 alert( 아이디가 존재하지 않거나 비밀번호가 틀립니다) <-- 이뜻임 
	<% } %>		
}
</script>






<%@ include file="/WEB-INF/views/common/footer.jsp" %>