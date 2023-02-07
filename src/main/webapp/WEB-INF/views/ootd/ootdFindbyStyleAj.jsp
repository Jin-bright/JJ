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
	 String msgnull  = (String)request.getAttribute("msgnull");
	 List<OotdBoardandAttachment> ootdboardAndAttachmentsbyStyle  = ( List<OotdBoardandAttachment> )request.getAttribute("ootdboardAndAttachmentsbyStyle");
	 String searchType = request.getParameter("searchType");
	 String searchKeyword = request.getParameter("searchKeyword");
//	List<OotdBoardandAttachment> ootdboardAndAttachments = (List<OotdBoardandAttachment>)request.getAttribute("ootdboardAndAttachments");
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
 
h5{
	margin-left : 420px;	
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
	margin-left : 390px;	
 }
 
td{
	margin-bottom : 20px;
}

#photo-wrapper{
  height : 200px;
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
<section id="board-container">
<h2 id = "ootdboardlist"  style="margin : 0 auto;"> Outfit Of The Day </h2>
<%--  <h3 id="ootdboardlist2"> 오늘 입은 옷을 공유해보세요! </h3> --%> 
<br /><br /><br /><br />
<%----  검색상자  --%>

 
<!-- <br /><br /><br />  -->
 <section id="photo-wrapper">
	<h2> 검색결과 </h2> <br/>
	<h4> 스타일 &nbsp; <span style="color : orange; font-size:18px"><%=searchKeyword%>&nbsp; </span> (으)로 검색한 결과입니다 😊 </h4> <br/><!-- <br/><br/> --> 
	<br /><br /><br /><br />
	<h5 style="float:left"> ※ 스타일 종류  "러블리, 댄디, 포멀, 스트릿, 걸리쉬, 레트로, 로맨틱, 시크, 아메카지"  </h5>
	<br />
	<h5 style="float:left"> ※ 유사한 단어로 검색 시 가장 비슷한 스타일로 노출됩니다.</h5>
	<br /><br /><!-- <br /> -->
	<hr /> <br /><br /><br />
	
	<div id='btn-more-container'>
		<button id ="goback" onclick="history.go(-1)" > 목록으로 가기 </button>
	</div>
</section>
<br />
<br />
<br />
<br />
<br />


 


<%-- <% if(loginMember != null){ %>
<input type="button" value="글쓰기" id="btnAdd" 
	onclick="location.href='<%=request.getContextPath()%>/ootd/ootdEnroll';"/> get&post다있는데/ 로그인한 상태에서만 노출 되게 수정해야됨 
<% } %>
 --%>

<table id="searchTable" >
<% for(int i=0; i< ootdboardAndAttachmentsbyStyle.size(); i++){ 
	if(i%2==0){%>
	<tr>
<% } %>
	<td class="maketd" >
	  <a class="atags" style="display :inline;" href="<%=request.getContextPath()%>/ootd/ootdView?no=<%=ootdboardAndAttachmentsbyStyle.get(i).getOotdNo()%>">
	  <img style="margin-left :7px" src="<%=request.getContextPath()%>/uploadootds/ootd/<%=ootdboardAndAttachmentsbyStyle.get(i).getRenamedFilename()%>" /></a>
	  <p class="non">NO <span style="color : black; font-weight : light"><%=ootdboardAndAttachmentsbyStyle.get(i).getOotdNo()%></span></p>
	  <p class="non">N  <span style=" color : black; font-weight : light"><%=ootdboardAndAttachmentsbyStyle.get(i).getOOTDRegDate()%></span></p>
<%--   	  <p class="non">STYLE NO <span style=" color : black; font-weight : light"><%=ootdboardAndAttachmentsbyStyle.get(i).getStyleNo()%></span></p> --%>	</td>
	<td style="width:200px"> </td>
<%  if(i%2==1){%>
	</tr>
<% }
} %>

</table>
<br /><br /><br /><br />

</section>







<%-- 
<script>
//비동기로 뭘한다.. ?
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
			url : "<%=request.getContextPath()%>/ootd/ootdFinderbyStyleAj",
			method : "get",
			data : {page}, //page : 1 이런식으로.. ?
			dataType : "json",
		
			success(data){
				console.log( data );
				const divContainer =  document.querySelector("#photo-container");
				
				data.forEach( (list, index) => {
				
					list.ootd_writer
					list.original_filename
				})
			}, //success 
			error : console.log, 
			complete(){
				document.querySelector("#page").innerHTML = page;
				//마지막 페이지인 경우 더보기 버튼 비활성화 처리 ★
		<%--		if( page === <%=totalPage%>){
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