<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css" />
<section class="container">
	<div class="wrap">
		<form method="POST" name="memberEnrollFrm" enctype="multipart/form-data">
			<h2 class="enroll_title">회원가입</h2>
			
			<div class="required_info">
				<h3 class="sub_title">필수 정보</h3>
				<div class="enroll_box">
					<h4>아이디<sup>*</sup></h4>
					<input type="text" name="memberId" id="memberId" class="enroll_text" placeholder="영문자/숫자 4글자이상" required/>
					<p class="input_error" id="id_error">영문, 숫자를 포함해서 입력해주세요. (4-16자)</p>
					<p class="input_error" id="duplicateMsg">이미 사용 중인 아이디입니다.</p>
				</div>
				<div class="enroll_box">
					<h4>비밀번호<sup>*</sup></h4>
					<input type="password" name="pwd" id="pwd" class="enroll_text" placeholder="영문자/숫자 4글자이상" required/>
					<p class="input_error" id="pwd_error">영문, 숫자를 포함해서 입력해주세요. (4-16자)</p>
				</div>
				<div class="enroll_box">
					<h4>비밀번호 확인<sup>*</sup></h4>
					<input type="password" id="pwdCheck" class="enroll_text" required/>
					<p class="input_error" id="checkMsg">비밀번호가 일치하지 않습니다.</p>
				</div>
				<div class="enroll_box">
					<h4>이메일<sup>*</sup></h4>
					<input type="text" name="email" id="email" class="enroll_text" placeholder="예)obtg123@gmail.com" required/>
					<p class="input_error" id="email_error">이메일 주소를 정확히 입력해주세요.</p>
					<p class="input_error" id="duplicateEmail">이미 사용 중인 이메일입니다.</p>
				</div>
				<div class="enroll_box">
					<h4>전화번호<sup>*</sup></h4>
					<input type="tel" name="phone" id="phone" class="enroll_text" placeholder="예)01012345678" required/>
					<p class="input_error" id="phone_error">전화번호를 정확히 입력해주세요.</p>
				</div>
				<div class="enroll_box">
					<h4>선호하는 스타일<sup>*</sup></h4>
					<table class="style_box">
						<c:forEach items="${styleList}" var="style" varStatus="vs">
							<c:if test="${vs.index % 5 == 0}">
								<tr>
							</c:if>
							<td>
								<input type="checkbox" name="style" class="style" value="${style.name}"/><label class="style_name">${style.name}</label>
							</td>
							<c:if test="${vs.index % 5 == 4}">
								</tr>
							</c:if>
						</c:forEach>
					</table>
					<p class="input_error" id="style_error">한 개 이상 선택해 주세요.</p>
				</div>
				<span class="next_btn">다음</span>
			</div>
			
			<div class="select_info">
				<h3 class="sub_title">선택 정보</h3>
				<div class="enroll_box">
					<h4>이름</h4>
					<input type="text" class="enroll_text" name="memberName"/>
				</div>
				<div class="enroll_box">
					<h4>생년월일</h4>
					<input type="date" class="enroll_text" name="birth"/>
				</div>
				<div class="enroll_box">
					<h4>성별</h4>
					<input type="radio" name="gender" id="gender0" value="M">
					<label for="gender0">남</label>
					<input type="radio" name="gender" id="gender1" value="F">
					<label for="gender1">여</label>
				</div>
				<div class="enroll_box">
					<h4>프로필 사진</h4>
					<input type="file" name="profile" accept="image/*"/>
				</div>
				<div class="enroll_box">
					<h4>닉네임</h4>
					<input type="text" name="nickName" class="enroll_text"/>
				</div>
				<div class="enroll_box">
					<h4>소개말</h4>
					<input type="text" name="introduce" class="enroll_text"/>				
				</div>
				<div>
					<input type="button" value="이전" class="pre_btn" onclick="preFrm();"/>
					<input type="button" value="가입하기" class="enroll_btn" onclick="signUp();"/>
				</div>
			</div>
		</form>
	</div>
</section>
<script>
/* 필수 입력값 확인 */
document.querySelectorAll(".enroll_text").forEach((text) => {
	text.onblur = (e) => {
		if(e.target.value.length === 0){
			e.target.nextElementSibling.classList.add("check");
		}
	};
});

/* 아이디 유효성검사 */
document.querySelector("#memberId").addEventListener('keyup', (e) => {
	const idMsg = document.querySelector("#id_error");
	
	if(!/^[A-Za-z0-9]{4,}$/.test(e.target.value)){
		idMsg.classList.add("check");
	}
	else {
		idMsg.classList.remove("check");		
	}
});

/* 아이디 중복검사 */
document.querySelector("#memberId").addEventListener('blur', (e) => {
	const idMsg = document.querySelector("#duplicateMsg");
	
	$.ajax({
		url : "${pageContext.request.contextPath}/member/checkDuplicate",
		data : {memberId : e.target.value},
		success(data){
			console.log(data);
			if(data == 1) {
				idMsg.classList.add("check");
			}
			else{
				idMsg.classList.remove("check");
			}
		},
		error : console.log
	});
});

/* 비밀번호 유효성검사 */
document.querySelector("#pwd").addEventListener('keyup', (e) => {
	const checkMsg = document.querySelector("#pwd_error");
	
	if(!/^[A-Za-z0-9!@#$%]{4,}$/.test(e.target.value)){
		checkMsg.classList.add("check");
	}
	else {
		checkMsg.classList.remove("check");
	}
});

/* 비밀번호 일치 여부 */
document.querySelector("#pwdCheck").addEventListener('keyup', (e) => {
	const pwd = document.querySelector("#pwd");
	const pwdMsg = document.querySelector("#checkMsg");
	
	if(pwd.value !== e.target.value){
		pwdMsg.classList.add("check");
	}
	else{
		pwdMsg.classList.remove("check");
	}
});

/* 이메일 유효성검사 */
document.querySelector("#email").addEventListener('keyup', (e) => {
	const emailMsg = document.querySelector("#email_error");
	
	if(!/^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/.test(e.target.value)){
		emailMsg.classList.add("check");
	}
	else {
		emailMsg.classList.remove("check");
	}
});

/* 이메일 중복검사 */
document.querySelector("#email").addEventListener('blur', (e) => {
	const emailMsg = document.querySelector("#duplicateEmail");
	
	$.ajax({
		url : "${pageContext.request.contextPath}/member/checkDuplicate",
		data : {email : e.target.value},
		success(data){
			console.log(data);
			if(data == 1) {
				emailMsg.classList.add("check");
			}
			else {
				emailMsg.classList.remove("check");
			}
		},
		error : console.log
	});
});

/* 전화번호 유효성검사 */
document.querySelector("#phone").addEventListener('keyup', (e) => {
	const phoneMsg = document.querySelector("#phone_error");
	
	if(!/^010\d{8}$/.test(e.target.value)){
		phoneMsg.classList.add("check");
	}
	else {
		phoneMsg.classList.remove("check");
	}
});

/* 필수정보 -> 선택정보 */
document.querySelector(".next_btn").addEventListener('click', (e) => {
	/* 선호하는 스타일 유효성검사 */
	const styles = document.querySelectorAll(".style");
	const styleMsg = document.querySelector("#style_error");
	let count = 0;
	
	styles.forEach((style) => {
		if(style.checked == true)
			count++;
	});
	
	if(count <= 0){
		styleMsg.classList.add("check");
		return false;		
	}
	else if(count > 0)
		styleMsg.classList.remove("check");
			
	/* 모든 항목 유효성 검사 통과여부 */
	let num = 0;
	document.querySelectorAll(".enroll_text").forEach((text) => {
		if(text.value.length === 0){
			text.select();
			num++;
		}
	});
	
	if(num <= 0) {
		return false;
	}
	
	/* 에러메시지가 하나로도 있을시 넘어가지 않음 */
	const error = document.querySelectorAll(".input_error");
	const arr = [];
	
	error.forEach((e) => {
		if(e.classList.contains("check")){
			arr.push(false);
			console.log(e.classList.contains("check"));
		}
	});

	if(arr.includes(false)){
		console.log("유효성검사에 통과못했을경우");
		return false;
	}
	else {
		console.log("유효성검사에 통과했을경우");
		const before = document.querySelector(".required_info");
		const after = document.querySelector(".select_info");
		
		before.classList.add("hidden");
		after.classList.add("show");
	}
});

const preFrm = () => {
	const before = document.querySelector(".required_info");
	const after = document.querySelector(".select_info");
	
	before.classList.remove("hidden");
	after.classList.remove("show");
};

const signUp = () => {
	if(confirm('해당 정보로 가입하시겠습니까?')){
		document.memberEnrollFrm.submit();
	}
};

</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />