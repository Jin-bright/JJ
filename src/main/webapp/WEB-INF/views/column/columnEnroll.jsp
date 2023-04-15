<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/columnEnroll.css" />
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

<section id="col_enroll">
	<h2 style="text-align: center; padding: 20px; font-weight: bold">컬럼 작성폼</h2>
	<form 
		name="columnFrm"
		action="<%= request.getContextPath() %>/column/columnEnroll" 
		enctype="multipart/form-data"
		method="post"		
	>
		<table class="enroll-table">
			<tbody>
				<tr>
                    <th style="text-align: center;">제목</th>
                    <td style="padding: .7em;"><input type="text" class="col_txt" name="col_title" placeholder="제목을 입력해주세요." required></td>
                </tr>
				<tr>
                    <th style="text-align: center;">소제목</th>
                    <td style="padding: .7em;"><input type="text" class="col_txt" name="col_subtitle" placeholder="소제목을 입력해주세요." required></td>
                </tr>
                <tr>
                    <th style="text-align: center;">작성자</th>
                    <td style="padding: .7em;"><input type="text" class="col_txt" name="col_writer" value="mango" readonly></td>
                </tr>
                <tr>
	                <th style="text-align: center;">첨부파일</th>
	                <td style="padding: .7em;"><input type="file" id="col_file" name="col_file" required accept="image/*"></td>
	            </tr>
	            <tr>
					<th>미리보기</th>
					<td style="padding: .7em 8.8em;">
						<div id="col_img">
							<img id="col_img_viewer" width="350px">
						</div>
					</td>
				</tr>
				<tr>
					<th style="text-align: center;">내용</th>
	                <td  colspan="2" style="padding: .7em;">
	           			<div class="container">
	               			<textarea id="summernote"  class="summernote" name ="col_content" cols="70" rows="10" style="resize: none;" required></textarea>    
	            		</div>
	                	<p style="text-align: right;"><span id="counter">0</span>/2000</p>	
            		</td>
	            </tr>
			</tbody>
	        <tfoot>
				<td colspan="2" style="text-align: center; padding: .7em;">
					<input type="submit" value="저장" id="btn">
	                <input type="button" value="취소" id="btn">
	            </td>
			</tfoot>
		</table>
	</form>
</section>

<script>
/* 첨부파일 이미지 미리보기 */
document.querySelector("#col_file").addEventListener('change', (e) => {
	const img = e.target;
	
	if(img.files[0]){
		// 파일 선택한 경우
		const fr = new FileReader(); // html5 api
		fr.readAsDataURL(img.files[0]); // 비동기처리 - 언제끝날지 몰라 백그라운드에서 작업함
		fr.onload = (e) => {
			// 읽기 작업 완료시 호출될 load이벤트핸들러
			document.querySelector("#col_img_viewer").src = e.target.result; // result속성은 dataUrl임
		};
	}
	else {
		// 파일 선택 취소한 경우
		document.querySelector("#col_img_viewer").src = "";
	}
});

/* 유효성검사 - 글자수제어하기 성공하면 같이 넣어 놓자,,, */
document.columnFrm.addEventListener('submit', (e) => {
	const title = e.target.col_title; // name 값을 입력해줘야하나보다
	const content = e.target.editordata;  // 이거 어케 지정하는거지..?
	const file = e.target.col_file;
	
	/* 글자수 제어 */
	content.addEventListener('keyup', (e) => {
		const len = e.target.children.value.length;
		const counter = document.querySelector("#counter");
		
		counter.innerHTML = len;
		if(len >= 2000){
			counter.innerText = len;
			counter.classList.add('red');
		}
		else {
			counter.innerText = len;
			counter.classList.remove('red');
		} 
	});
	
	// 제목을 작성하지 않은 경우 폼제출 불가
	if(!/^.+$/.test(title.value)){
		alert("제목을 작성해주세요."); /* 이거 왜 안나옴...? */
		title.focus();
		return false;
	}
	
	// 내용을 작성하지 않은 경우 폼제출 불가
	if(!/^(.|\n)+$/.test(content.value)){
		alert("내용을 작성해주세요.");
		return false;
	}
	
	// 이미지를 등록하지 않은 경우 폼제출 불가
	if(!(file.value)){
		alert("이미지를 등록해주세요.");
		file.select();
		return false;
	}
	
	if(content.value.length >= 2000){
		e.preventDefault();
		alert("내용에 2000글자 이내로 입력해주세요.");
		return false;
	}
});


/* 써머노트 */
$(document).ready(function() {
	var toolbar = [
		// 글꼴 설정
		['fontname', ['fontname']],
		// 글자 크기 설정
		['fontsize', ['fontsize']],
		// 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
		['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
		// 글자색
		['color', ['forecolor','color']],
		// 글머리 기호, 번호매기기, 문단정렬
		['para', ['ul', 'ol', 'paragraph']],
		// 줄간격
		['height', ['height']],
	];

	var setting = {
		width : 600,
		height : 300,
		lang : 'ko-KR',
		toolbar : toolbar,
		placeholder : "내용을 입력해주세요.",
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

/* 글자수 제어 (해결해야하는 숙제,,,,)*/
document.columnFrm.addEventListener("submit", (e) => {
	const content = e.target.editordata;
	if(content.value.length >= 2000){
		e.preventDefault();
		alert("내용에 2000글자 이내로 입력해주세요.");
		return;
	}
});

/* document.querySelector(".note-editable").editordata.addEventListener("keyup", (e) => {
	console.log(e.currentTarget.children.value);
	const len = e.target.children.value.length;
	const counter = document.querySelector("#counter");
	
	counter.innerHTML = len;
	if(len >= 2000){
		counter.innerText = len;
		counter.classList.add('red');
	}
	else {
		counter.innerText = len;
		counter.classList.remove('red');
	} 
}); */
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>