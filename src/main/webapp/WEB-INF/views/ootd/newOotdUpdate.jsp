<%@page import="com.sh.obtg.ootd.model.dto.OotdBoard"%>
<%@page import="com.sh.obtg.ootd.model.dto.OotdAttachment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!-- 글꼴  -->
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Noto+Sans+KR:wght@900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/ootdEnroll.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet"> 
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script src=" https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- 서머노트를 위해 추가해야할 부분 -->
<script src="<%=request.getContextPath()%>/summernote/summernote-lite.js"></script>
<script src="<%=request.getContextPath()%>/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/summernote/summernote-lite.css">

<%
// 리스트 가져오는거 필요함 
// List<OotdAttachment> ootdAttachments = (List<OotdAttachment>)request.getAttribute("ootdAttachments");
	 OotdBoard ootdboard = (OotdBoard) request.getAttribute("ootdboard");
%>
<br />
<section id="board-container">
	<h2 id="ootdwrite" style="font-weight : 700">OOTD 게시글 작성</h2>
	<form
		name="ootdBoardEnrollFrm"
		action="<%=request.getContextPath()%>/ootd/ootdUpdate" 
		enctype ="multipart/form-data"
		method="post">
		<table id="tbl-board-view">
		<input type="hidden" name="no"  value="<%=ootdboard.getOotdNo()%>"/>		
		<tr >
			<th style ="padding-top:20px; font-size : 24px">제 목</th>
			<td style ="padding-top:20px" ><input  class="inputtext" type="text" name="ootdtitle"  value="<%=ootdboard.getOOTDTitle() %>" placeholder="제목을 입력해주세요." required></td>
		</tr>
		
		<tr>
			<th>아이디</th>
			<td>
				<input type="text"  class="inputtext" name="ootdwriter" value="<%=ootdboard.getOotdWriter() %>" readonly />
			</td>
		</tr>
		<tr>
			<th>상의</th>
			<td>
				<input type="text" class="inputtext"  name="ootdTop" value="<%=ootdboard.getOOTDTop() %>" required/>
			</td>
		</tr>
		<tr>
			<th>하의</th>
			<td>
				<input type="text"  class="inputtext" name="ootdBottom" value="<%=ootdboard.getOOTDBottom()%>" required/>
			</td>
		</tr>
			<tr>
			<th>신발</th>
			<td>
				<input type="text" class="inputtext" name="ootdShoes" value="<%=ootdboard.getOOTDShoes() %>" />
			</td>
		</tr>
			<tr>
			<th>기타</th>
			<td>
				<input type="text" class="inputtext" name="ootdEtc" value="<%=ootdboard.getOOTDEtc()%> " />
			</td>
		</tr>
		<tr>
			<th>스타일</th>
			<td>
				<input type="checkbox" name="style" id="S1" value="러블리" onclick='checkOnlyOne(this)'  ><label for="S1">러블리 &nbsp;</label>		
				<input type="checkbox" name="style" id="S2" value="댄디" onclick='checkOnlyOne(this)'  ><label for="S2">댄디 &nbsp;</label>		
				<input type="checkbox" name="style" id="S3" value="포멀" onclick='checkOnlyOne(this)'  ><label for="S3">포멀 &nbsp;</label>		
				<input type="checkbox" name="style" id="S4" value="스트릿" onclick='checkOnlyOne(this)'  ><label for="S4">스트릿 &nbsp;</label>		
				<input type="checkbox" name="style" id="S5" value="걸리쉬" onclick='checkOnlyOne(this)'  ><label for="S5">걸리쉬 &nbsp;</label>		
				<input type="checkbox" name="style" id="S6" value="레트로" onclick='checkOnlyOne(this)'  ><label for="S6">레트로 &nbsp;</label>	
				<input type="checkbox" name="style" id="S7" value="로맨틱" onclick='checkOnlyOne(this)'  ><label for="S7">로맨틱 &nbsp;</label>	
				<input type="checkbox" name="style" id="S8" value="시크" onclick='checkOnlyOne(this)'  ><label for="S8">시크&nbsp;</label>	
				<input type="checkbox" name="style" id="S9" value="아메카지" onclick='checkOnlyOne(this)' ><label for="S9">아메카지</label>	
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td id="filetd">
		<%
		List<OotdAttachment> ootdAttachments = ootdboard.getOotdAttachments();
			if(!ootdAttachments.isEmpty()){
				for( int i =0; i< ootdAttachments.size(); i++){
					OotdAttachment attach = ootdAttachments.get(i);
		%>
	<%--	 <img src="<%=request.getContextPath()%>/image/file.png" width="16px" />
		 <%=attach.getOriginalFilename()%>
		 <input type="checkbox" name="delFile" id="delFile<%=i%>" value="<%= attach.getAttachNo()%>" />
		 <label for="delFile<%=i%>"> 삭제 </label>  --%> 
			<br />	
			<input type="file" name="upFile1" id="upFile1">   <!-- 첨부파일의 경우 input 의 value 값은 맘대로 지정할수없다 (해도안됨) -->
		</td>
		</tr>
		<tr>
			<th>미리보기</th><!--  혜진수정0128 - 기존파일 삭제체크박스 아예 삭제하고 attachment update쿼리로 변경  --> 
			<td style="padding: 10px">
				<img id="col_img_viewer"  src="<%=request.getContextPath()%>/uploadootds/ootd/<%=attach.getRenamedFilename()%>" style = "width : 200px; height : 250px;" >
			</td>
					<%							
				}
			}
		%>
		</tr>
		<tr>
			<th  colspan="2" >
			<div class="summernotecontainer">
		  <textarea colspan="2" id="summernote"  class="summernote" name ="editordata"  value="<%=ootdboard.getOOTDContents()%>" > </textarea>
 <!-- 			  <textarea style="font-weight:lighter" colspan="2" id="summernote"  class="summernote" name ="ootdContents" required></textarea>   -->	  
			      
			</div>
			</th>
		</tr>
		<tr>
			<th colspan="2">
				<!--<input  class ="inputbuttons" type="submit" value="CANCEL"  style="margin-left : 100px"> -->
				<input  class ="inputbuttons"  type="submit" value="SUBMIT">
				<input class ="inputbuttons"  type="button" value="CANCEL" onclick="history.go(-1);"/>
			</th>
		</tr>
	</table>
	</form>
</section>
<br />
<br />
<br />


<script>
function checkOnlyOne(element) {
	  
	  const checkboxes 
	      = document.getElementsByName("style");
	  
	  checkboxes.forEach((cb) => {
	    cb.checked = false;
	  })
	  
	  element.checked = true;
}
</script>

<script>
/* 첨부파일 이미지 미리보기 */
document.querySelector("#upFile1").addEventListener('change', (e) => {
	const img = e.target;
	
	if(img.files[0]){
		// 파일 선택한 경우
		const fr = new FileReader(); // html5 api
		fr.readAsDataURL(img.files[0]); // 
		fr.onload = (e) => {
			document.querySelector("#col_img_viewer").src = e.target.result; // result속성은 dataUrl임
		};
	}
	else {
		// 파일 선택 취소한 경우
		document.querySelector("#col_img_viewer").src = "";
	}
});
</script>

<script>	
	$(document).ready(function() {

		var toolbar = [
			    // 글꼴 설정
			    ['fontname', ['fontname']],
			    // 글자 크기 설정
			    ['fontsize', ['fontsize']],
			    // 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
			    ['style', ['clear', 'bold', 'italic', 'underline','strikethrough']],
			    // 글자색
			    ['color', ['forecolor','color']],
			    // 표만들기
			    ['table', ['table']],
			    // 글머리 기호, 번호매기기, 문단정렬
			    ['para', ['ul', 'ol', 'paragraph']],
			    // 줄간격
			    ['height', ['height']],
			    // 그림첨부, 링크만들기, 동영상첨부
			 //   ['insert',['picture','link','video']],
			    // 코드보기, 확대해서보기, 도움말
			    ['view', ['codeview','fullscreen', 'help']]
			  ];

		var setting = {
	            height : 300,
	            width : 760,
	            minHeight : null,
	            maxHeight : null,
	            focus : true,
	            lang : 'ko-KR',
	            fontSize : 16,
	            fontWeight : 'normal',
	            toolbar : toolbar,
	            callbacks : { //여기 부분이 이미지를 첨부하는 부분
	            onImageUpload : function(files, editor,
	            welEditable) {
	            for (var i = files.length - 1; i >= 0; i--) {
	            uploadSummernoteImageFile(files[i],
	            this);
	            		}
	            	}
	            }
	         };

	        $('#summernote').summernote(setting);
	        });
</script>


<script>
/**
* boardEnrollFrm 유효성 검사
*/
document.ootdBoardEnrollFrm.onsubmit = (e) => {
	const title = e.target.ootdtitle;
	const content = e.target.editordata;
	const upload = e.target.upFile1;
	
	console.log(title, content);
	
	//제목을 작성하지 않은 경우 폼제출할 수 없음.
	if(!/^.+$/.test(title.value)){
		alert("제목을 작성해주세요");
		title.select();
		return false;
	}
						   
	//내용을 작성하지 않은 경우 폼제출할 수 없음.
	if(!/^.|\n+$/.test(content.value)){ // \n은 따로 추가해줘야됨 (왜냐면 .애는 개행이 포함안되서 ) 
		alert("내용을 작성해주세요");
		content.select();
		return false;
	}
	
	if( !upload ){ // \n은 따로 추가해줘야됨 (왜냐면 .애는 개행이 포함안되서 ) 
		alert("사진을 첨부해주세요");
		upload.select();
		return false;
	}
}
</script>



<%@ include file="/WEB-INF/views/common/footer.jsp" %>