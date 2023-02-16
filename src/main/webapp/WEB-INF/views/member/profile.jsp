<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myPage.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css" />
<section class="container">
<jsp:include page="/WEB-INF/views/member/subMenu.jsp" />
	<div class="content_area">
		<div class="profile_wrap">
			<h3>프로필 정보</h3>
			<div class="user_profile">
				<div class="img_box">
					<c:if test="${loginMember.renamed == null}">
						<img src="${pageContext.request.contextPath}/image/망그러진곰.jpeg" class="my_profile"/>
					</c:if>
					<c:if test="${loginMember.renamed != null}">
						<img src="${pageContext.request.contextPath}/upload/profile/${loginMember.renamed}" class="my_profile"/>
					</c:if>
				</div>
				<div class="user_info">
					<strong>${loginMember.memberId}</strong>
					<div>
						<form name="profileFrm" style="display: none;">
							<input type="file" name="profile" id="upload">
						</form>
						<label for="upload" class="upload_btn">이미지 변경</label>
						<button class="my_btn" onclick="deleteImg(${loginMember.memberId});">삭제</button>
					</div>
				</div>
			</div>
		</div>
		<div class="user_info">
			<div class="required_info">
				<h4 class="info_title">필수 정보</h4>
				<div class="info_box before">
					<h5>비밀번호</h5>
					<div class="my_info_box">
						<p>●●●●●●●●</p>
						<button class="my_btn open">변경</button>
					</div>
				</div>
				<div class="modify pwd">
					<h5>비밀번호 변경</h5>
					<div class="modify_box">
						<h6>이전 비밀번호</h6>
						<input type="password" name="oldPwd" class="my_input"/>
						<p class="error_msg">비밀번호가 일치하지 않습니다.</p>
					</div>
					<div class="modify_box">
						<h6>새 비밀번호</h6>
						<input type="password" name="password" class="my_input"/>
						<p class="error_msg">영문, 숫자를 포함해서 입력해주세요. (4-16자)</p>
					</div>
					<div class="modify_btn_box">
						<button class="my_btn" onclick="hiddenFrm();">취소</button>
						<button class="my_btn" onclick="update('password');">변경</button>
					</div>
				</div>
				<div class="info_box">
					<h5>이메일 주소</h5>
					<div class="my_info_box">
						<p>${loginMember.email}</p>
						<button class="my_btn open">변경</button>
					</div>
				</div>
				<div class="modify">
					<div class="modify_box">
						<h5>이메일 주소 변경</h5>
						<input type="email" value="${loginMember.email}" class="my_input required_input" name="email" required="required"/>
						<p class="error_msg">이메일 주소를 정확히 입력해주세요.</p>
					</div>
					<div class="modify_btn_box">
						<button class="my_btn" onclick="hiddenFrm();">취소</button>
						<button class="my_btn" onclick="update('email');">변경</button>
					</div>
				</div>
				<div class="info_box">
					<h5>전화번호</h5>
					<div class="my_info_box">
						<p>${fn:substring(loginMember.phone, 0, 4)}***${fn:substring(loginMember.phone, 7, 11)}</p>
						<button class="my_btn open">변경</button>
					</div>
				</div>
				<div class="modify">
					<div class="modify_box">
						<h5>전화번호 변경</h5>
						<input type="tel" value="${fn:substring(loginMember.phone, 0, 4)}***${fn:substring(loginMember.phone, 7, 11)}" class="my_input required_input" name="phone" required="required"/>
						<p class="error_msg">전화번호를 정확히 입력해주세요.</p>
					</div>
					<div class="modify_btn_box">
						<button class="my_btn" onclick="hiddenFrm();">취소</button>
						<button class="my_btn" onclick="update('phone');">변경</button>
					</div>
				</div>
			</div>
			<div class="select_info">
				<h4 class="info_title">선택 정보</h4>
				<div class="info_box">
					<h5>이름</h5>
					<div class="my_info_box">
						<p>${loginMember.name}</p>
						<button class="my_btn open">변경</button>
					</div>
				</div>
				<div class="modify">
					<div class="modify_box">
						<h5>이름 변경</h5>
						<input type="text" value="${loginMember.name}" name="name" class="my_input"/>
					</div>
					<div class="modify_btn_box">
						<button class="my_btn" onclick="hiddenFrm();">취소</button>
						<button class="my_btn" onclick="update('name');">변경</button>
					</div>
				</div>
				<div class="info_box">
					<h5>닉네임</h5>
					<div class="my_info_box">
						<p>${loginMember.nickname}</p>
						<button class="my_btn open">변경</button>
					</div>
				</div>
				<div class="modify">
					<div class="modify_box">
						<h5>닉네임 변경</h5>
						<input type="text" value="${loginMember.nickname}" name="nickName" class="my_input"/>
					</div>
					<div class="modify_btn_box">
						<button class="my_btn" onclick="hiddenFrm();">취소</button>
						<button class="my_btn" onclick="update('nickName');">변경</button>
					</div>
				</div>
				<div class="info_box">
					<h5>생일</h5>
					<div class="my_info_box">
						<p>${loginMember.birthday}</p>
						<button class="my_btn open">변경</button>
					</div>
				</div>
				<div class="modify">
					<div class="modify_box">
						<h6>생일 변경</h6>
						<input type="date" value="${loginMember.birthday}" name="birthday" class="my_input"/>
					</div>
					<div class="modify_btn_box">
						<button class="my_btn" onclick="hiddenFrm();">취소</button>
						<button class="my_btn" onclick="update('birthday');">변경</button>
					</div>
				</div>
			</div>
		</div>
		<br />
		<div>
			<p class="sad">회원탈퇴</p>
		</div>
	</div>
</section>
<form method="post" name="updateFrm">
	<input type="hidden" id="type"/>
</form>
<form action="${pageContext.request.contextPath}/member/memberDelete"
	  method="post"
	  name="deleteFrm">
	<input type="hidden" name="memberId" value="${loginMember.memberId}"/>
</form>
<script>
/* 프로필 사진 변경 */
document.querySelector("#upload").addEventListener('change', (e) => {
	const img = e.target;
	
	if(img.files[0]){
		const fr = new FileReader();
		fr.readAsDataURL(img.files[0]);
		fr.onload = (e) => {
			document.querySelector(".my_profile").src = e.target.result;
		};
	};
	
	const frm = document.profileFrm;
	console.log(frm);
	
	const frmData = new FormData(frm);
	
	$.ajax({
		url : "${pageContext.request.contextPath}/member/updateProfile",
		method : "POST",
		dataType : "json",
		contentType : false, 
		processData : false, 
		data : frmData,
		success(data){
			console.log(data);
		},
		error : console.log
	});
});

/* 수정폼 열기 */
document.querySelectorAll(".open").forEach((open) => {
	open.onclick = (e) => {
		const target = e.target.parentElement.parentElement;
		console.log(target);
		
		target.classList.add("hidden");
		target.nextElementSibling.classList.add("show");
		
	};
});

/* 비밀번호 일치 여부 */
document.querySelector("[name=oldPwd]").addEventListener('blur' ,(e) => {
	$.ajax({
		url : "${pageContext.request.contextPath}/member/checkDuplicate",
		data : {password : e.target.value},
		success(data){
			console.log(data);
			if(data == 1) {
				console.log(e.target.nextElementSibling);
				e.target.nextElementSibling.classList.remove("show2");
			}
			else {
				e.target.nextElementSibling.classList.add("show2");
			}
		},
		error : console.log
	});
});


/* 프로필 정보 변경 */
const update = (keyType) => {
	const frm = document.updateFrm;
	const keyword = document.querySelector(`[name=\${keyType}]`);
	
	/* 유효성 검사 */
	switch (keyType){
	/* 비밀번호 */
	case 'password' :
		const oldPwd = document.querySelector("[name=oldPwd]");
		if(oldPwd.classList.contains("show2")){
			return false;
		}
		console.log("비밀번호 일치하지않으면 찍히면 안됨");
		
		if(!/^[A-Za-z0-9!@#$%]{4,}$/.test(keyword.value)){
			keyword.nextElementSibling.classList.add("show2");
			return false;
		}
		break;
	/* 이메일 */
	case 'email' :
		if(!/^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/.test(keyword.value)){
			keyword.nextElementSibling.classList.add("show2");
			return false;			
		}
		break;
	/* 전화번호 */
	case 'phone' :
		if(!/^010\d{8}$/.test(keyword.value)){
			keyword.nextElementSibling.classList.add("show2");
			return false;			
		}
		break;
	}
	
	const type = document.querySelector("#type");
	type.name = keyType;
	type.value = keyword.value;
	
	frm.submit();
};

/* 회원탈퇴 */
document.querySelector(".sad").addEventListener('click', (e) => {
	if(confirm("정말 탈퇴하시겠습니까?🥲")){
		document.deleteFrm.submit();
	}
});
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />