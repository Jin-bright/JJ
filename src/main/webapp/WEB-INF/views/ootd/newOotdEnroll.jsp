<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<%@page import="com.sh.obtg.member.model.dto.Member"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>        
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ taglib uri ="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!-- 글꼴  -->
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Noto+Sans+KR:wght@100;300;500;900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ootdEnroll.css" />
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
	Member loginMember = (Member) session.getAttribute("loginMember");  
%>
<br />
<section id="board-container">
	<h2 id="ootdwrite" style="font-weight : 700">OOTD 게시글 작성</h2>
	<form
		name="ootdBoardEnrollFrm"
		action="${pageContext.request.contextPath}/ootd/newOotdEnroll" 
		enctype ="multipart/form-data"
		method="post">
		<table id="tbl-board-view">
		
		<tr >
			<th>제 목</th>
			<td><input class="inputtext" type="text" name="ootdtitle" placeholder="제목을 입력해주세요." required></td>
		</tr>
		
		<tr>
			<th>아이디</th>
			<td>
				<input type="text"  class="inputtext" name="ootdwriter" value="<%=loginMember.getMemberId() %>" readonly required/>
			</td>
		</tr>
		<tr>
			<th>상의</th>
			<td>
				<input type="text" class="inputtext"  name="ootdTop" value="" required/>
			</td>
		</tr>
		<tr>
			<th>하의</th>
			<td>
				<input type="text"  class="inputtext" name="ootdBottom" value="" required/>
			</td>
		</tr>
			<tr>
			<th>신발</th>
			<td>
				<input type="text" class="inputtext" name="ootdShoes" value="" />
			</td>
		</tr>
			<tr>
			<th>기타</th>
			<td>
				<input type="text" class="inputtext" name="ootdEtc" value="" />
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
			<td>			
		<%-- 	<input type="file" name="upFile1" accept="image/*"  required>   --%>
				<div id="col_img"  style="margin-top : 0px" >
					<img id="col_img_viewer"  style="width :250px; height : 270px; padding-right: 30px">
				</div>
				
				<div class="filebox">
					<input class="upload-name"   id="upload-name1"  placeholder="첨부파일" readonly>
	    			<label for="upFile1">파일찾기</label>
					<input type="file" name="upFile1" id="upFile1" accept="image/*"  required /> <br />	
				</div>
				
				<div id="imgexplain">
					<p>- 사진은 한개만 등록가능합니다. </p>
				</div>
			</td>
		</tr>
		<tr>
			<th  colspan="2" style="padding : 0; border:none" >
			<div class="summernotecontainer">
		    <textarea colspan="2" id="summernote"  class="summernote" name ="editordata"></textarea>     
			</div>
			</th>
		</tr>
		<tr>
			<th colspan="2">
				<input  class ="inputbuttons" id="subutton"  type="submit" value="SUBMIT">
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
/* 첨부파일 이미지 미리보기 */
document.querySelector("#upFile1").addEventListener('change', (e) => {
	const img = e.target;
	
	if(img.files[0]){
		// 파일 선택한 경우
		const fr = new FileReader(); 
		fr.readAsDataURL(img.files[0]); 
		fr.onload = (e) => {
			// 읽기 작업 완료시 호출될 load이벤트핸들러
			document.querySelector("#col_img_viewer").src = e.target.result; 
			document.querySelector("#upload-name1").value = document.querySelector("#upFile1").value;
		};
	}
	else {
		// 파일 선택 취소한 경우
		document.querySelector("#col_img_viewer").src = "";
	}
});

</script>

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
			    // 코드보기, 확대해서보기, 도움말
			    ['view', ['codeview','fullscreen', 'help']]
			  ];

		var setting = {
	            height : 300,
	            width : 780,
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
