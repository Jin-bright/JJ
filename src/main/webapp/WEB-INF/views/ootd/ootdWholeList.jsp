<%@page import="com.sh.obtg.ootd.model.dto.OotdBoard"%>
<%@page import="com.sh.obtg.ootd.model.dto.OotdAttachment"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/header.jsp" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/ootdWholeList.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Noto+Sans+KR:wght@900&display=swap" rel="stylesheet">
<%

String searchType = request.getParameter("searchType");
String searchKeyword = request.getParameter("searchKeyword");
List<OotdBoard> findootdBoardsById = (List<OotdBoard> )request.getAttribute("findootdBoardsById");
//String msg = (String)session.getAttribute("msg");
//int totalPage = (int)request.getAttribute("totalPage");

//if( msg != null )
//	session.removeAttribute("msg");
// 리스트 가져오는거 필요함 
 List<OotdAttachment> ootdAttachments = (List<OotdAttachment>)request.getAttribute("ootdAttachments");
 OotdBoard ootdboard = (OotdBoard) request.getAttribute("ootdboard");
%>
<br /><br /><br />
<section id="board-container">
<h2 id = "ootdboardlist" > Outfit Of The Day </h2>
<h3 id="ootdboardlist2"> 오늘 입은 옷을 공유해보세요! </h3>
<br /><br /><br /><br /><br /><br />
<%----  검색상자  --%>
<style>
 div#search-container {
 	width: 180px; 
 	margin: 0 0 10px 0; 
 	padding:3px; 
 	background-color: white;
	float : left;
	margin-top : -30px;
	margin-left : 330px;
	font-family: 'Nanum Gothic Coding', monospace;		
	font-size : 15px;
 }
 div#search-memberId {
 	display: <%= searchType == null || "ootd_writer".equals(searchType) ? " inline-block" : "none" %>; 
 }
 div#search-style{
 	display: <%= "style_no".equals(searchType) ? "inline-block" :  "none" %> ; 
 }
 </style>
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
			 
		 case "style_no" : 
			 id = "search-style";
			 break;
		 
		 default :
			 id = "search-memberId";
			 break;
		 }
		 
		
		 document.querySelector("#" + id ).style.display = "inline-block";
		
	 })
 })

 </script>

 <div id="search-container">
  		<img style="width:16px; height:16px; margin :0" src="<%=request.getContextPath()%>/uploadootds/o.png" alt="" />
        <label for="searchType">검색타입 :</label> 
        <select id="searchType"  style="width:65px; margin-top:10px" >
            <option value="ootd_writer" <%= "ootd_writer".equals(searchType) ? "selected" : "" %>  style="width:50px"> 아이디</option>        
            <option value="style_no" <%= "style_no".equals(searchType) ? "selected" : ""%>  style="width:50px" > 스타일</option>
        </select>
        
        <div id="search-memberId" class="search-type">
            <form action="<%=request.getContextPath()%>/ootd/ootdFindbyIdAj">
            <div class="wrap">
			   <div class="search">
                <input type="hidden" name="searchType" value="ootd_writer"/>
                <input type="text" name="searchKeyword"  size="25" placeholder=" 검색할 아이디를 입력하세요. ex)cathj " id="idfindinput" style=" border: 3px solid black; width : 500px
                ; border-radius: 5px 0 0 5px; font-size : 15px;" value = "<%= "ootd_writer".equals(searchType) ? searchKeyword : "" %>"/>
                <button type="submit" class="searchButton"><i class="fa fa-search"></i></button><!-- //검색버튼  -->
                </div>
             </div>   
            </form>    
        </div>
        
        <div id="search-style" class="search-type">
            <form action="<%=request.getContextPath()%>/ootd/ootdFinderbyStyleAj">
             <div class="wrap">
			   <div class="search">
                <input type="hidden" name="searchType" value="style_no" />
                <input type="text" name="searchKeyword" size="25" placeholder=" 스타일을 검색해보세요  ex)스트릿  " id="stylefindinput"  style=" border: 3px solid black; width : 500px;
                border-radius: 5px 0 0 5px;  font-size : 15px;" value="<%= "style_no".equals(searchType) ? searchKeyword : ""%>"/>
               	<button type="submit" class="searchButton"><i class="fa fa-search"></i></button><!-- //검색버튼  -->
              	<input type="hidden" id="btn-more" name="page" > 
               </div>
             </div>   
            </form>    
        </div>
</div>
<br />
<br />
<br />        
        
<%---- <!-- <div class="wrap">
   <div class="search">
      <input type="text" class="searchTerm" placeholder="스타일을 검색해보세요  ex)스트릿  ">
      <button type="submit" class="searchButton">
        <i class="fa fa-search"></i>
     </button>
   </div>
</div>
 -->  --%>

<% if(loginMember != null){ %>
<input type="button" value="글쓰기" id="btnAdd" 
	onclick="location.href='<%=request.getContextPath()%>/ootd/ootdEnroll';"/> <%-- get&post다있는데/ 로그인한 상태에서만 노출 되게 수정해야됨 --%> 
<% } %>
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
<br /><br /><br /><br />
<!-- 페이지바 -->
<div id='pagebar' style = " background-color: #eeeeee;	" > <%=request.getAttribute("pagebar")%>
</div>
</section>















<%@ include file="/WEB-INF/views/common/footer.jsp" %>