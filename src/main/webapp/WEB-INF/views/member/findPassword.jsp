<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css" />
<section class="container">
	<div class="wrap">
		<div class="before_box">
			<h2 class="findId_title">비밀번호 찾기</h2>
			<div class="ex_msg">
				<p>
					가입시 등록하신 이메일과 아이디를 입력하면
					<br />
					임시 비밀번호를 알려드립니다.
				</p>
			</div>	
			<div>
				<h4>아이디</h4>
				<input type="text" name="memberId" id="memberId" class="find_input" placeholder="아이디를 입력해주세요." required/>
				<p class="input_error" id="id_error">올바른 아이디를 입력해주세요.</p>
			</div>
			<div>
				<h4>이메일</h4>
				<input type="email" name="email" id="email" class="find_input" placeholder="가입하신 이메일을 입력해주세요." required/>
				<p class="input_error" id="email_error">이메일 형식이 올바르지 않습니다.</p>
			</div>
			<div class="find_btn_box">
				<button class="find_btn" onclick="findPwd();">비밀번호 찾기</button>
			</div>
		</div>
	</div>
</section>
<script>
const findPwd = () => {
	const id = document.querySelector("#memberId");
	const email = document.querySelector("#email");
	const idMsg = document.querySelector("#id_error");
	const emailMsg = document.querySelector("#email_error");
	
	/* 유효성검사 */
	if(!/^[A-Za-z0-9]{4,}$/.test(id.value)){
		idMsg.style.visibility = "visible";
		id.select();
		return false;
	}
	if(!/^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/.test(email.value)){
		emailMsg.style.visibility = "visible";
		email.select();
		return false;
	}
	
	/* 비밀번호찾기결과 */
	$.ajax({
		url : "${pageContext.request.contextPath}/member/find_pwd",
		data : {id : id.value, email : email.value},
		dataType : "json",		
		success(data){
			console.log(data);

			const before = document.querySelector(".before_box");
			before.style.display = "none";
			
			const wrap = document.querySelector(".wrap");
			
			const div1 = document.createElement("div");
			div1.classList.add('after_box');
			const span = document.createElement("span");
			/* 회원정보 일치에 따른 처리 */
			if(data.member == 1) span.innerText = "비밀번호 찾기에 성공하였습니다.";				
			else span.innerText = "비밀번호 찾기에 실패하였습니다.";
			
			const div2 = document.createElement("div");
			div2.classList.add('find_info');
			const p = document.createElement("p");
			/* 회원정보 일치에 따른 처리 */
			if(data.member == 1) p.innerText = "임시비밀번호";
			else p.innerText = "";
			const h4 = document.createElement("h4");
			/* 회원정보 일치에 따른 처리 */
			if(data.member == 1) h4.append(data.tempPwd);
			else h4.innerText = "일치하는 회원정보를 찾을 수 없습니다.";
			
			const div3 = document.createElement("div");
			div3.classList.add('success_btn_box');
			const a1 = document.createElement("a");
			a1.href = "${pageContext.request.contextPath}/member/findPassword";
			a1.innerText = "비밀번호찾기";
			const a2 = document.createElement("a");
			a2.href = "${pageContext.request.contextPath}/member/login";
			a2.innerText = "로그인";
			
			if(data.member == 1) div3.append(a2);
			else div3.append(a1, a2);
			div2.append(p, h4);
			div1.append(span);
			wrap.append(div1, div2, div3);
		},
		error : console.log
	});
};
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />