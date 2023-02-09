<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css" />
<section class="container">
	<div class="wrap">
		<form action="${pageContext.request.contextPath}/member/login"
			  method="post"
			  name="loginFrm">
			<h2 class="login_title">OBTG</h2>
			<div class="login_box">
				<h4>아이디</h4>
				<input type="text" name="memberId" id="memberId" class="login_input" placeholder="아이디를 입력해주세요." required/>
				<p class="input_error" id="id_error">올바른 아이디를 입력해주세요.</p>
			</div>
			<div class="login_box">
				<h4>비밀번호</h4>
				<input type="password" name="memberPwd" id="memberPwd" class="login_input" placeholder="비밀번호를 입력해주세요." required/>
				<p class="input_error" id="pwd_error">영문, 숫자, 특수문자를 조합해서 입력해주세요. (8-16자)</p>
			</div>
			<div class="login_btn_box">
				<input type="submit" value="로그인" class="login_btn"/>
			</div>
		</form>
		<ul class="etc_list">
			<li class="etc_link">회원가입</li> |
			<li class="etc_link"><a href="${pageContext.request.contextPath}/member/findId">아이디 찾기</a></li> |
			<li class="etc_link"><a href="${pageContext.request.contextPath}/member/findPassword">비밀번호 찾기</a></li>
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
	const idMsg = document.querySelector("#id_error");
	const pwdMsg = document.querySelector("#pwd_error");
	
	/* 유효성검사 */
	if(!/^[A-Za-z0-9]{4,}$/.test(memberId.value)){
		idMsg.style.visibility = "visible";
		memberId.select();
		return false;
	}
	
	if(!/^[A-Za-z0-9!@#$%]{4,}$/.test(memberPwd.value)){
		pwdMsg.style.visibility = "visible";
		memberPwd.select();
		return false;
	}
};
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />