<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css" />
<section id="login_container">
	<div class="login_wrap">
		<form action="${pageContext.request.contextPath}/member/login"
			  method="post"
			  name="loginFrm">
			<h2 class="login_title">OBTG</h2>
			<div class="login_box">
				<h4>아이디</h4>
				<input type="text" name="memberId" id="memberId" class="login_input" placeholder="아이디를 입력해주세요."/>
				<p class="input_error" id="id_error">올바른 아이디를 입력해주세요.</p>
			</div>
			<div class="login_box">
				<h4>비밀번호</h4>
				<input type="password" name="memberPwd" id="memberPwd" class="login_input" placeholder="비밀번호를 입력해주세요."/>
				<p class="input_error" id="pwd_error">영문, 숫자, 특수문자를 조합해서 입력해주세요. (8-16자)</p>
			</div>
			<div class="login_btn_box">
				<input type="submit" value="로그인" class="login_btn"/>
			</div>
		</form>
		<ul class="etc_list">
			<li class="etc_link">회원가입</li>
			<li class="etc_link">아이디 찾기</li>
			<li class="etc_link">비밀번호 찾기</li>
		</ul>
	</div>	
</section>
<script>
/* 필수 입력값 확인 */
document.querySelector("#memberId").addEventListener('blur', (e) => {
	const errorMsg = document.querySelector("#id_error");
    if(e.target.value.length === 0)
    	errorMsg.style.visibility = "visible"
    else
    	errorMsg.style.visibility = "hidden";
});

document.querySelector("#memberPwd").addEventListener('blur', (e) => {
	const errorMsg = document.querySelector("#pwd_error");
    if(e.target.value.length === 0)
    	errorMsg.style.visibility = "visible"
	else
    	errorMsg.style.visibility = "hidden";
});

/* 로그인요청 */
document.loginFrm.onsubmit = (e) => {
	const memberId = document.querySelector("#memberId");
	const memberPwd = document.querySelector("#memberPwd");
	
	/* 유효성검사 */
	if(!/^[A-Za-z0-9]{4,}$/.test(memberId.value)){
		alert("아이디는 영문자/숫자 4글자이상이어야합니다.");
		memberId.select();
		return false;
	}
	
	if(!/^[A-Za-z0-9!@#$%]{4,}$/.test(memberPwd.value)){
		// alert("비밀번호는는 영문자/숫자/특수문자(!@#$%) 4글자이상이어야 합니다.");
		alert("비밀번호는 4글자 이상이어야 합니다.");
		memberPwd.select();
		return false;
	}
};
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>