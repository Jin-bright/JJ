<%@page import="com.sh.obtg.member.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Noto+Sans+KR:wght@100;300;500;900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shareEnroll.css" />
<!-- 글꼴  -->

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
	Member loginMember=(Member) session.getAttribute("loginMember");
    String msg = (String) session.getAttribute("msg");  
%>
<style>
#top,#bottom,#accessary,#shoes{
	display : none;
}
</style>
<br />

<section id="board-container">
	<h2 id="sharewrite"> SHARE 게시글 수정</h2>
	  <h6>단, 거래가 시작될 경우 해당 게시물은 수정하실 수 없습니다.</h6><br /><br /><br /><br />
	<form
		name="shareBoardUpdateFrm"
		action="<%=request.getContextPath()%>/share/newShareUpdate" 
		enctype ="multipart/form-data"
		method="post">
		<input type="hidden"  name ="no" value="${shareboard.productId}" />		
		<table id="tbl-board-view">
		<tr>
			<th style="vertical-align: top;">상품 이미지</th>
			<td >
				<div id="col_img"  style="margin-top : 0px" >
					<img id="col_img_viewer" src="${pageContext.request.contextPath}/uploadshares/newShare/${shareboard.shareAttachments[0].renamedFilename}" style="width : 255px; height : 170px; padding-right: 30px">
				</div>
				
				<div class="filebox">
					<input class="upload-name"   id="upload-name1"  placeholder="첨부파일을 다시 선택해주세요(위 사진은 이전 사진 입니다)" readonly>
	    			<label for="upFile1">파일찾기</label>
					<input type="file" name="upFile1" id="upFile1" accept="image/*" required /> <br />	
				</div>
				
				<div id="imgexplain">
					<p>- 사진은 한개만 등록가능합니다. </p>
					<p>- 이미지는 상품 등록 시 정사각형으로 잘려서 등록됩니다.</p>
				</div>
			</td>
		</tr>

		<tr >
			<th>상품명</th>
			<td>
				<input style="width : 500px;" class="inputtext" type="text" name="ShareTitle" maxlength="18" value="${shareboard.productName}" required>
				<input class="satustext" type="text" name="ShareState" value="${shareboard.productStatus}" readonly />	
			</td>
		</tr>
		
		<tr>
			<th>아이디</th>
			<td>
				<input type="text"  class="inputtext" name="memberId" value="${loginMember.memberId}"  readonly style="background-color: #e8e8e8"/>
			</td>
		</tr>
		<tr>
			<th>카테고리</th>
			<td>
				<select   onclick = "selectbig(this.value);" >
				    <option value="상의" > 상의 </option>
				    <option  value="하의" > 하의 </option>
				    <option  value="악세서리및기타" > 악세서리및기타 </option>
				    <option  value="신발" > 신발 </option>
				</select>
			    	<select name="ShareCategory"  id="top">
			    	    <option  value="" > 선택 </option>
			    		<option  value="T1" > 패딩 </option>
			    		<option   value="T2" > 코트 </option>
			    		<option   value="T3" > 니트웨어 </option>
			    		<option    value="T4" >자켓</option>
			    		<option    value="T5" >후드</option>
			    		<option  value="T6" >긴팔티셔츠</option>
			    		<option   value="T7" >반팔티셔츠</option>
			    		<option   value="T8" >민소매</option>
			    		<option  value="T9" >기타</option>	
			    	</select>
				    <select name="ShareCategory" id="bottom">
				    		<option  value="" > 선택 </option>
				    		<option   value="B1" > 청바지 </option>
				    		<option   value="B2" > 슬랙스 </option>
				    		<option value="B3" >반바지</option>
				    		<option  value="B4" >스커트</option>
			    	</select>	
			    		
			    	<select  name="ShareCategory"  id="accessary">
			    		<option  value="" > 선택 </option>
			    		<option   value="A1" >가방</option>
			    		<option   value="A2" >시계</option>
			    		<option   value="A3" >주얼리</option>
			    		<option   value="A4" >모자</option>
			    		<option   value="A5" >스카프</option>
			    		<option   value="A6" >아이웨어</option>
			    		<option   value="A7" >기타</option>		
			    	</select>
			    
			    	<select  name="ShareCategory"  id="shoes">
			    		<option  value="" > 선택 </option>
			    		<option   value="S1" >운동화</option>
			    		<option   value="S2" >부츠</option>
			    		<option   value="S3" >로퍼</option>
			    		<option   value="S4" >샌들</option>
			    		<option   value="S5" >슬리퍼</option>
			    		<option   value="S6" >구두</option>		
				    	<option   value="S7" >기타</option>		
			    	</select>
			 <span style="font-size : 12px; color : red;">※ 세부카테고리까지 선택해주세요 😊</span></td>
		</tr>
		<tr>
			<th> 컬러 </th>
			<td>
					<select name="sharecolor" id="sharecolors" required >
			    		<option  name="sharecolor"  value="검정" >검정</option>
			    		<option  name="sharecolor"  value="빨강" >빨강</option>
			    		<option  name="sharecolor"  value="주황" >주황</option>
			    		<option  name="sharecolor"  value="노랑" >노랑</option>
			    		<option  name="sharecolor"  value="파랑" >파랑</option>
			    		<option  name="sharecolor"  value="하얀" >하얀</option>
			    		<option  name="sharecolor"  value="초록" >초록</option>
			    		<option  name="sharecolor"  value="보라" >보라</option>
			    		<option  name="sharecolor"  value="베이지" >베이지</option>
			    		<option  name="sharecolor"  value="하늘" >하늘</option>						
			    	</select>
			
				<span style="font-size : 12px; color : red;">&nbsp;&nbsp; ※ 비슷한 색상으로 선택하셔도 좋습니다 😊 </span>
			</td>
		</tr>
		<tr>
			<th>상품상태</th>
			<td>
				<input type="checkbox" name="ShareProductStatus" id="P1" value="상" onclick='checkOnlyOneTwo(this)' ><label for="P1"> 상 &nbsp;</label>		
				<input type="checkbox" name="ShareProductStatus" id="P2" value="중" onclick='checkOnlyOneTwo(this)' ><label for="P2"> 중 &nbsp;</label>		
				<input type="checkbox" name="ShareProductStatus" id="P3" value="하" onclick='checkOnlyOneTwo(this)' ><label for="P3"> 하 &nbsp;</label>
			</td>
		<tr>
		<tr>
			<th>성별</th>
			<td>
				<input type="checkbox" name="productGender" id="G1" value="남" onclick='checkOnlyTwo(this)' ><label for="G1">남</label>		
				<input type="checkbox" name="productGender" id="G2" value="여" onclick='checkOnlyTwo(this)' ><label for="G2">여</label>
				<input type="checkbox" name="productGender" id="G3" value="공용" onclick='checkOnlyTwo(this)' ><label for="G3">공용</label>		
			</td>
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
			<th>가격</th>
			<td>
				<input type="number"  class="inputtext" name="productPrice"  style="width :200px" value="${shareboard.productPrice}"   required/>원
			</td>
		</tr>
		<tr>
			<th  colspan="2" style="padding : 0; border:none" >
			<div class="summernotecontainer">
			  <textarea colspan="2" id="summernote"  class="summernote" name ="editordata">${shareboard.productContent}</textarea>
			</div>
			</th>
		</tr>
		<tr>
			<th colspan="2" style="padding : 10px 0 0 0; border:none">
				<input style="margin-left : 544px"  class ="inputbuttons"  type="submit" value="SUBMIT">
				<input class ="inputbuttons"  type="button" value="CANCEL" onclick="history.go(-1);"/>
			</th>
		</tr>
	</table>
	</form>
</section>
<br /><br /><br />

<script>
$("select[name=ShareCategory]").change( function(){
	console.log("찍히냐");
	console.log( $(this).val() );
	
	$(this).attr('name', 'real');
	console.log( $(this) );

	console.log( $(this).val() );
	
	
})



</script>




<script>
//select박스 보이기
const selectbig = (e) =>{
	const top = document.querySelector("#top");
	const bottom = document.querySelector("#bottom");
	const accessary = document.querySelector("#accessary");
 	const shoes = document.querySelector("#shoes");
 	
	if( e =="상의"){
		top.style.display = "inline-block";
		bottom.style.display = "none";
		accessary.style.display = "none";
		shoes.style.display = "none";
	}
	if( e =="하의"){
		bottom.style.display = "inline-block";
		top.style.display = "none";
		accessary.style.display = "none";
		shoes.style.display = "none";
	}
	if( e =="악세서리및기타"){
		accessary.style.display = "inline-block"
		top.style.display = "none";
		bottom.style.display = "none";
		shoes.style.display = "none";	
	}
	if( e ==="신발"){
		accessary.style.display = "none"
		top.style.display = "none";
		bottom.style.display = "none";
		shoes.style.display = "inline-block";	
	}
	
console.log( e );
	
};

</script>

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
// 체크박스들 하나만 선택되게 하기 
function checkOnlyOne(element) {
	  
	  const checkstyleboxes = document.getElementsByName("style");
	  
	  checkstyleboxes.forEach((cb) => {
	    cb.checked = false;
	  })
	  
	  element.checked = true;
}


function checkOnlyOneTwo(element) {
	  
	  const checkboxes 
	      = document.getElementsByName("ShareProductStatus");
	  
	  checkboxes.forEach((cb) => {
	    cb.checked = false;
	  })
	  
	  element.checked = true;
}

function checkOnlyTwo(element) {
	  
  const checkboxes = document.getElementsByName("productGender");
  
  checkboxes.forEach((cb) => {
    cb.checked = false;
  })
  
  element.checked = true;
}
</script>


<script>	
//서머노트
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
	            width : 800,
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
* shareBoardUpdateFrm 유효성 검사
*/
document.shareBoardUpdateFrm.onsubmit = (e) => {
	const title = e.target.ShareTitle;
	const content = e.target.editordata;
	const upload = e.target.upFile1;
	
	
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
	
	if( !upload ){ // 사진하나는 꼭첨부되어야한다
		alert("사진을 첨부해주세요");
		upload.select();
		return false;
	}
}
</script>



<%@ include file="/WEB-INF/views/common/footer.jsp" %>