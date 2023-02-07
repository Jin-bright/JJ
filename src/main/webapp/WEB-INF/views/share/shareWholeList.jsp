<%@page import="org.apache.catalina.authenticator.NonLoginAuthenticator"%>
<%@page import="com.sh.obtg.share.model.dto.ShareBoard"%>
<%@page import="com.sh.obtg.share.model.dto.ShareBoardEntity"%>
<%@page import="com.sh.obtg.share.model.dto.ShareAttachment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/shareWholeList.css" />
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Noto+Sans+KR:wght@900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<%
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
	
	List<ShareAttachment> shareAttachments = (List<ShareAttachment>)request.getAttribute("shareAttachments");
	List<ShareBoard> shareboards = (List<ShareBoard> )request.getAttribute("shareboards");

	int	totalfindPage = (int)request.getAttribute("totalfindPage");		
%>

<section id="board-container">
<br /><br /><br /><br />
<h2 id = "shareboardlist" > SHARE  </h2>
<h3 id="shareboardlist2"> 더이상 입지 않는 아이템을 다른 회원들에게 무료로 양도해보세요 </h3>
<br />
<%----  검색상자  --%>
<style>
 div#search-container {
	margin-left : 300px;
	margin-top : 53px;
 	width: 280px; 
 	padding:3px; 
 	background-color: white;
	float : left;
	font-family: 'Nanum Gothic Coding', monospace;		
	font-size : 17px;
	
 }
 
 div#search-memberId {
 	display: <%= searchType == null || "member_id".equals(searchType) ? "inline-block" : "none" %>; 
 }
 
 div#search-category{
 	display: <%= "share_category".equals(searchType) ?  "inline-block" :  "none" %> ; 
 }
 
 div#search-content{
 	display: <%= "sahre_content".equals(searchType) ?  "inline-block" :  "none" %> ; 
 }
 
 #btn-more-container{

    visibility: hidden;
 	color : white;
	width: 100px;
	heignt : 40px;
	text-align: center;
	font-size : 17px;
	margin-left : 200px;
	padding-bottom : 100px;
	padding-top : 100px;
 }
 
 #btn-more, #btn-more-id{
/* 3. 내용찾기용*/ 	
	color : white;
	width: 200px;
	height : 35px;
	margin-left : 90px;
	background-color: black;
	color : white;	
 }
 
  #btn-more:hover{
 	color : black;
	background-color: white ;
 }
 
 #btn-more-id:hover{
 	color : black;
	background-color: white ;
}
 
 /*2. 카테고리용 */
  #btn-more-cate{
 	color : white;
	width: 200px;
	height : 35px;
	margin-left : 90px;
	background-color: black;
	color : white;	
 }
 
  #btn-more-cate:hover{
 	color : black;
	background-color: white ;
 }
 
 #goback{
 	font-size : 15px;
	visibility : hidden;
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
		 case "member_id" : 
			 id = "search-memberId";
			 break;
			 
		 case "share_category" : 
			 id = "search-category";
			 break;
			 
		 case "sahre_content" :
			 id = "search-content";
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
        <label for="searchType" >검색타입 :</label> 
        <select id="searchType"  style="width:90px; margin-top:10px; height : 30px; border : 3px solid black" >
            <option value="member_id" <%= "member_id".equals(searchType) ? "selected" : "" %>  style="width:90px; line-height : 30px; text-align: center" > 아이디</option>        
            <option value="share_category" <%= "share_category".equals(searchType) ? "selected" : ""%>  style="width:90px; line-height : 30px; text-align: center" > 카테고리 </option>
            <option value="sahre_content" <%= "sahre_content".equals(searchType) ? "selected" : ""%>  style="width:90px; line-height : 30px; text-align: center" > 내용 </option>
        </select>
<!-- 검색타입  : 체크박스 상자 -->        

        <div id="search-memberId" class="search-type"> <!-- 1.멤버아이디로검색 -->
<%--             <form name="findidFrm" id="form"  action="<%=request.getContextPath()%>/share/shareWholeListFind"  >--%>
            <div class="wrap">
			   <div class="search">
                <input type="hidden" name="searchType" id="searchType" value="member_id"/>
                <input type="text" name="searchKeywordID"  size="25" placeholder=" 검색할 아이디를 입력하세요. ex)cathj " id="searchKeywordID" style=" border: 3px solid black; width : 500px
                ; border-radius: 5px 0 0 5px; font-size : 15px; margin-top : 40px; height:33px;" required value = "<%= "member_id".equals(searchType) ? searchKeyword : "" %>"/>
                <button type="submit" class="searchButton" id="searchButton" style="margin-top : 40px; height:33px;"><i class="fa fa-search"></i></button><!-- //검색버튼  -->
                </div>
             </div>   
<!--             </form>    -->
        </div>
        
        <div id="search-category" class="search-type">  <!-- 2. 카테고리로 검색 -->
            <div class="wrap">
		   		<div class="search">
	               <input type="hidden" name="searchType"  id="searchType" value="share_category" />
	               <input type="text" name="searchKeywordID" size="25" placeholder=" 카테고리를 검색해보세요  ex)상의, 하의, 악세서리   " id="searchKeywordID"  style=" border: 3px solid black; width : 500px;
	               border-radius: 5px 0 0 5px; margin-top : 40px; height:33px; font-size : 15px;" required value="<%= "share_category".equals(searchType) ? searchKeyword : ""%>"/>
	              <button type="submit" class="searchButton" id="searchButtoncate" style="margin-top : 40px; height:33px;" ><i class="fa fa-search"></i></button><!-- //검색버튼  -->
             	</div>
            </div>   
        </div>
        
         <div id="search-content" class="search-type">  <!-- 3. 내용으로 검색 -->
            <div class="wrap">
		   		<div class="search">
	               <input type="hidden" name="searchType"  id="searchType" value="sahre_content" />
	               <input type="text" tabindex="1" name="searchKeywordID" size="25" placeholder=" 내용을 검색해보세요  ex)사이즈, 겨울, 등등   " id="searchKeywordID"  style=" border: 3px solid black; width : 500px;
	               border-radius: 5px 0 0 5px; margin-top : 40px; height:33px; font-size : 15px;" required value="<%= "sahre_content".equals(searchType) ? searchKeyword : ""%>"/>
	              <button tabindex="2"  type="submit" class="searchButton" id="searchButtoncnt" style="margin-top : 40px; height:33px;"><i class="fa fa-search"></i></button><!-- //검색버튼  -->
             	</div>
            </div>   
        </div>
</div>
     
 <span id="goback" ><a style="margin-left : -160px; font-size:13px; margin-top:100px; display:inline-block; text-align:center; border:3px solid black; width : 90px; height:30px" href="<%=request.getContextPath()%>/share/shareWholeList">목록보기</a></span>
 
<% if(loginMember != null){ %>
<input type="button" value="글쓰기" id="btnAdd"  style="margin-left : -600px"
	onclick="location.href='<%=request.getContextPath()%>/share/shareEnroll';"/> <%-- get&post다있는데/ 로그인한 상태에서만 노출 되게 수정해야됨 --%> 
<% } %>


<table id="tblBoard" >
  <% for(int i=0; i<shareAttachments.size(); i++){
   	 if(i%2==0){%>
	<tr >
	<%} %>
  	<td class="maketd" style="width:220px">
     <a class="atags" style="display :inline;" href="<%=request.getContextPath()%>/share/shareView?no=<%=shareAttachments.get(i).getBoardNo()%>">
       <img id="eachimg"  style="display : inline-block; height : 200px; width:190px;  margin-left:-3px" src="<%=request.getContextPath()%>/uploadshares/share/<%=shareAttachments.get(i).getRenamedFilename()%>"/></a><br/>
		<p class="non">NO <span style="color : black; font-weight : light"><%=shareAttachments.get(i).getBoardNo()%></span></p>
		<p class="non">N  <span style=" color : black; font-weight : light"><%=shareAttachments.get(i).getRegDate()%></span></p>
	</td>
	<td class ="detailtd" style="width:220px">
		<img class="carrots"src="<%=request.getContextPath()%>/uploadshares/carrot.png" alt="carrot" style=" width:16px; height:16px;  margin-left:-5px" />
		<p  id="buttonglow"><%=shareboards.get(i).getShareState()%></p>			
		<div id="detaildiv">
		<span class="datailtitle"  ><b>제목 </b></span>    <span class="boarddetails"><%= shareboards.get(i).getShareTitle()%></span><br/><br/>
		<span class="datailtitle" ><b>카테고리 </b></span>  <span class="boarddetails"><%= shareboards.get(i).getShareCategory()%></span><br/><br/>
		<span class="datailtitle"  ><b>구매시기 </b></span> <span class="boarddetails"><%= shareboards.get(i).getShareBuyDate()%></span><br/><br/>
		<span class="datailtitle"  ><b>상품상태 </b></span> <span class="boarddetails"><%= shareboards.get(i).getShareProductStatus()%></span><br />
		</div>
	</td>
	<td style="width:100px"></td>
	
     <% if(i%2==1){%>
  		</tr>
  	 <% }   	    
  	 }%> 
  	
</table>
</section>
 <!-- /** 시험중 ** -->
<div id='btn-more-container' >
	<button id="btn-more-id" value="" > 더보기(현재<span id="pageId"></span>페이지)</button>  <!-- 1 id 검색용 -->
	<button id="btn-more-cate" value="" > 더보기(현재<span id="pageCate"></span>페이지)</button> <!-- 2카테고리검색용 -->
	<button id="btn-more" value="" > 더보기(현재<span id="pageCnt"></span>페이지)</button>  <!-- 3 내용 검색용 -->
</div>  
<div id='pagebar' style = "background-color: #f5f5f5;" > <%=request.getAttribute("pagebar")%></div>
<script>

//const img  = document.querySelectorAll("#eachimg");
function styleback(){
	const img  = document.querySelectorAll(".maketd");
	
	img.forEach( (i, index) => {
		i.addEventListener('mouseenter', (e) => {
			i.style.backgroundColor = "#db0d36";	
		})
	});
};

/* window.onload{
	styleback();
} */

</script>


<!-- 1. id로 검색  -->
<script>
const searchButton	= document.querySelector("#searchButton");

searchButton.addEventListener('click', () => {
	getPageID(1);
}); 

document.querySelector("#btn-more-id").addEventListener('click', () => {//현재
	const pageId = document.querySelector("#pageId");
	getPageID(Number(pageId.innerText) + 1); //Number("1") + 1  -> 2 
});


const getPageID = (page) => {
	$.ajax({
			url : "<%=request.getContextPath()%>/share/shareWholeListFindSecond",
			method : "get",
			data : {page,
				searchType :  document.getElementById("searchType").value,
				searchKeywordID : document.getElementById("searchKeywordID").value
			},
			dataType : "json",
		
			success(data){
				
				if(data.length == 0 ){
					alert("검색 결과가 없습니다 ❌");
					const btnmorecontainer = document.querySelector("#btn-more-container");
					btnmorecontainer.style.visibility = "hidden";

					const buttonmoreId = document.querySelector("#btn-more-id");
					buttonmoreId.style.visibility = "hidden";
					
					const goback = document.querySelector("#goback");
					goback.style.visibility = "visible"; //목록보기가생김
					return; //현재창에 남아있도록함
				}

				console.log ( data ); 
				
				const table = document.querySelector("#tblBoard")
				table.innerHTML = "";
				pagebar.innerHTML = "";
				
				const tbody = document.createElement("tbody")
				table.append(tbody);
				
				const tr = document.createElement("tr")
				tbody.append(tr);
				
				//foreach
				data.forEach( (e, index) => {
					console.log ( e )

					if(data.length != 0){
						//버튼하나만보이게해야됨
						const buttonmoreId = document.querySelector("#btn-more-id");
						buttonmoreId.style.visibility = "visible";
				
						tr.innerHTML	+= 
							`<td class="maketd" style="width:220px;">
						       <a class="atags" style="display :inline;" href="<%=request.getContextPath()%>/share/shareView?no=\${e.ShareNo}">
						       <img id="eachimg"  style="display : inline-block; height : 200px; width:190px;  margin-left:-3px" src="<%=request.getContextPath()%>/uploadshares/share/\${e.renamedFilename}"/></a><br/>
						        <p class="non">NO <span style="color : black; font-weight : light">\${e.ShareNo}</span></p>
						        <p class="non">N  <span style=" color : black; font-weight : light">\${e.ShareRegDate}</span></p>
						    </td>`;
					}					
					else if( data.length == 0 ){
						alert("더이상 검색결과가 없습니다 😣");
						const btnmorecontainer = document.querySelector("#btn-more-container");
						btnmorecontainer.style.visibility = "hidden";
						
						const buttonmoreId = document.querySelector("#btn-more-id");
						buttonmoreId.style.visibility = "hidden";
						table.innerHTML = "";
						btnmorecontainer.innerHTML = "";
						pagebar.innerHTML = "";
						
					}
				});//////end foreach
			},
			error : console.log,
			complete(data){
				 document.querySelector("#pageId").innerHTML = page;	
				if( data.length == 0 ){
					const buttonmoreId = document.querySelector("#btn-more-id");
					buttonmoreId.disabled = true; // 리턴값이 boolean 값 
					buttonmoreId.style.cursor = "not-allowed";
				}
			}	
	});//ajax
};//
</script>


<!-- 2. 카테고리로 검색  -->
<script>
const searchButtoncate = document.querySelector("#searchButtoncate");

searchButtoncate.addEventListener('click', () => {
	getPageCate(1);
}); 

document.querySelector("#btn-more-cate").addEventListener('click', () => {//현재
	const pageCate = document.querySelector("#pageCate");
	getPageCate(Number(pageCate.innerText) + 1); //Number("1") + 1  -> 2 
});


const getPageCate = (page) => {
	
	const keyword = document.getElementsByName("searchKeywordID");

	$.ajax({
			url : "<%=request.getContextPath()%>/share/shareWholeListFindSecond",
			method : "get",
			data : {page,
				searchType :  document.getElementById("searchType").value,
				searchKeywordID : keyword[1].value
			},
			dataType : "json",
		
			success(data){
				
				if(data.length == 0 ){
					alert("검색 결과가 없습니다 ❌");
					const btnmorecontainer = document.querySelector("#btn-more-container");
					btnmorecontainer.style.visibility = "hidden";
					const buttonmoreCate = document.querySelector("#btn-more-cate");
					buttonmoreCate.style.visibility = "hidden";
					
					const goback = document.querySelector("#goback");
					goback.style.visibility = "visible";
					
					return; //현재창에 남아있도록함
				}

				console.log ( data ); 
				
				const table = document.querySelector("#tblBoard")
				table.innerHTML = "";
				pagebar.innerHTML = "";
				
				const tbody = document.createElement("tbody")
				table.append(tbody);
				
				const tr = document.createElement("tr")
				tbody.append(tr);
				
				//foreach
				data.forEach( (e, index) => {
					console.log ( e )

					if(data.length != 0){
						//버튼하나만보이게해야됨
						const btnmorecontainer = document.querySelector("#btn-more-cate");
						btnmorecontainer.style.visibility = "visible";
				
						tr.innerHTML	+= 
							`<td class="maketd" style="width:220px;">
						       <a class="atags" style="display :inline;" href="<%=request.getContextPath()%>/share/shareView?no=\${e.ShareNo}">
						       <img id="eachimg"  style="display : inline-block; height : 200px; width:190px;  margin-left:-3px" src="<%=request.getContextPath()%>/uploadshares/share/\${e.renamedFilename}"/></a><br/>
						        <p class="non">NO <span style="color : black; font-weight : light">\${e.ShareNo}</span></p>
						        <p class="non">N  <span style=" color : black; font-weight : light">\${e.ShareRegDate}</span></p>
						    </td>`;
					}					
					else if( data.length == 0 ){
						alert("더이상 검색결과가 없습니다 😣");
						const btnmorecontainer = document.querySelector("#btn-more-container");
						btnmorecontainer.style.visibility = "hidden";
						
						const buttonmoreCate = document.querySelector("#btn-more-cate");
						buttonmoreCate.style.visibility = "hidden";
						table.innerHTML = "";
						btnmorecontainer.innerHTML = "";
						pagebar.innerHTML = "";
						
					}
					else if ( data.length <5 ){
						const btnmorecontainer = document.querySelector("#btn-more-cate");
						btnmorecontainer.disabled = true; // 리턴값이 boolean 값 
						btnmorecontainer.style.cursor = "not-allowed";
					}
				});//////end foreach
			},
			error : console.log,
			complete(data){
				 document.querySelector("#pageCate").innerHTML = page;	
				if( data.length == 0 ){
					const btnmorecontainer = document.querySelector("#btn-more-cate");
					btnmorecontainer.disabled = true; // 리턴값이 boolean 값 
					btnmorecontainer.style.cursor = "not-allowed";
				}
			}	
	});//ajax
};//
</script>




<%--  3. 내용따라검색 / 현재페이지알려줌 /5개씩 나열 --%>
<script>
const searchButtoncnt = document.querySelector("#searchButtoncnt");

 searchButtoncnt.addEventListener('click', () => {
	getPage(1);
}); 

document.querySelector("#btn-more").addEventListener('click', () => {//현재
	const pageCnt = document.querySelector("#pageCnt");
	getPage(Number(pageCnt.innerText) + 1); //Number("1") + 1  -> 2 
});


const getPage = (page) => {
	
	const keyword = document.getElementsByName("searchKeywordID");

	$.ajax({
			url : "<%=request.getContextPath()%>/share/shareWholeListFindSecond",
			method : "get",
			data : {page,
					searchType :  document.getElementById("searchType").value,
					searchKeywordID : keyword[2].value,
			},
			dataType : "json",
		
			success(data){
				console.log ( data ); 

				if(data.length == 0 ){
					alert("검색 결과가 없습니다 ❌");
					const btnmorecontainer = document.querySelector("#btn-more-container");
					btnmorecontainer.style.visibility = "hidden";
					const buttonmor = document.querySelector("#btn-more");
					buttonmor.style.visibility = "hidden";
					
					const goback = document.querySelector("#goback");
					goback.style.visibility = "visible";
					return;
				}
				
				const table = document.querySelector("#tblBoard")
				table.innerHTML = "";
				pagebar.innerHTML = "";
				
				const tbody = document.createElement("tbody")
				table.append(tbody);
				
				const tr = document.createElement("tr")
				tbody.append(tr);
				
				//foreach - data가 있는 경우 시작 
				data.forEach( (e, index) => {
					console.log ( e )

					if(data.length != 0){
						
						const btnMore = document.querySelector("#btn-more");
						btnMore.style.visibility = "visible";
				
						tr.innerHTML	+= 
							`<td class="maketd" style="width:220px;">
						       <a class="atags" style="display :inline;" href="<%=request.getContextPath()%>/share/shareView?no=\${e.ShareNo}">
						       <img id="eachimg"  style="display : inline-block; height : 200px; width:190px;  margin-left:-3px" 
						       src="<%=request.getContextPath()%>/uploadshares/share/\${e.renamedFilename}"/></a><br/>
						        <p class="non">NO <span style="color : black; font-weight : light">\${e.ShareNo}</span></p>
						        <p class="non">N  <span style=" color : black; font-weight : light">\${e.ShareRegDate}</span></p>
						    </td>`;
					}					
				});//////end foreach
			},
			error : console.log,
			complete(data){//
						 document.querySelector("#pageCnt").innerHTML = page;		
			}//end-complete	
	});//end ajax
};//
</script>


<%--
			complete(data){//
						 document.querySelector("#pageCnt").innerHTML = page;
					
						//if( data.length == 0 ){
						//	const buttonmor = document.querySelector("#btn-more");
						//	buttonmor.disabled = true; // 리턴값이 boolean 값 
						//	buttonmor.style.cursor = "not-allowed";
						//} 
		
			}//end-complete	
			 --%>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
