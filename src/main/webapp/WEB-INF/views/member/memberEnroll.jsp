<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css" />
<section class="container">
	<div class="wrap">
		<form method="POST" name="memberEnrollFrm">
			<h2 class="enroll_title">회원가입</h2>
			
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
				<p class="input_error">전화번호를 정확히 입력해주세요.</p>
			</div>
			<div class="enroll_box">
				<h4>선호하는 스타일<sup>*</sup></h4>
				<table class="style_box">
					<c:forEach items="${styleList}" var="style" varStatus="vs">
						<c:if test="${vs.index % 5 == 0}">
							<tr>
						</c:if>
						<td>
							<input type="checkbox" name="style" value="${style.name}"/><label class="style">${style.name}</label>
						</td>
						<c:if test="${vs.index % 5 == 4}">
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>
			<input type="submit" value="가입하기" class="enroll_btn"/>
		</form>
	</div>
</section>
<script>
window.addEventListener('load', () => {
	const btn = document.querySelector(".enroll_btn");
	btn.disabled = true;
});

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

/* 회원가입폼 제출 */
document.memberEnrollFrm.onsubmit = (e) => {
	
};
</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />