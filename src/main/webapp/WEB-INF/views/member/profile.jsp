<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="com.sh.obtg.member.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	Member loginMember = (Member)session.getAttribute("loginMember");
	List<String> myStyleList = 
			loginMember.getStyle() != null ?
					Arrays.asList(loginMember.getStyle().split(",")) : null;
	pageContext.setAttribute("myStyleList", myStyleList);
%>
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
						<button class="my_btn" onclick="deleteImg();">삭제</button>
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
						<p class="error_msg pwd_msg">비밀번호가 일치하지 않습니다.</p>
					</div>
					<div class="modify_box">
						<h6>새 비밀번호</h6>
						<input type="password" name="password" class="my_input"/>
						<p class="error_msg">영문, 숫자를 포함해서 입력해주세요. (4-16자)</p>
					</div>
					<div class="modify_btn_box">
						<button class="my_btn close">취소</button>
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
						<button class="my_btn close">취소</button>
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
						<button class="my_btn close">취소</button>
						<button class="my_btn" onclick="update('phone');">변경</button>
					</div>
				</div>
				<div class="info_box">
					<h5>선호하는 스타일</h5>
					<div class="my_info_box">
						<p>
							<c:forEach items="${loginMember.style}" var="style">
								<i><sup>#</sup>${style}</i>
							</c:forEach>
						</p>
						<button class="my_btn open">변경</button>
					</div>
				</div>
				<div class="modify">
					<div class="modify_box">
						<h5>선호하는 스타일 변경</h5>
						<form name="styleFrm" method="post">
							<table class="style_box">
								<c:forEach items="${styleList}" var="style" varStatus="vs">
									<c:if test="${vs.index % 5 == 0}">
										<tr>
									</c:if>
									<td>
										<input type="checkbox" name="style" class="style" id="${style.name}" value="${style.name}" ${myStyleList.contains(style.name) ? "checked" : ""}/>
										<label for="${style.name}" class="style_name">${style.name}</label>
									</td>
									<c:if test="${vs.index % 5 == 4}">
										</tr>
									</c:if>
								</c:forEach>
							</table>
						</form>
						<p class="error_msg style_msg">한 개 이상 선택해 주세요.</p>
					</div>
					<div class="modify_btn_box">
						<button class="my_btn close">취소</button>
						<button class="my_btn" onclick="styleUpdate();">변경</button>
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
						<button class="my_btn close">취소</button>
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
						<button class="my_btn close">취소</button>
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
						<button class="my_btn close">취소</button>
						<button class="my_btn" onclick="update('birthday');">변경</button>
					</div>
				</div>
				<div class="info_box">
					<h5>소개말</h5>
					<div class="my_info_box">
						<p>${loginMember.introduce}</p>
						<button class="my_btn open">변경</button>
					</div>
				</div>
				<div class="modify">
					<div class="modify_box">
						<h5>소개말 변경</h5>
						<input type="text" value="${loginMember.introduce}" name="introduce" class="my_input"/>
					</div>
					<div class="modify_btn_box">
						<button class="my_btn close">취소</button>
						<button class="my_btn" onclick="update('introduce');">변경</button>
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

/* 프로필 사진 삭제 */
const deleteImg = () => {
	const frm = document.createElement("form");
	const input = document.createElement("imput")
	input.type = "file";
	input.name = "profile";
	input.value = null;
	frm.append(input);
	
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
			document.querySelector(".my_profile").src = "${pageContext.request.contextPath}/image/망그러진곰.jpeg"
		},
		error : console.log
	});
};

/* 수정폼 열기 */
document.querySelectorAll(".open").forEach((open) => {
	open.onclick = (e) => {
		const target = e.target.parentElement.parentElement;
		console.log(target);
		
		target.classList.add("hidden");
		target.nextElementSibling.classList.add("show");
		
	};
});

/* 수정폼 닫기 */
document.querySelectorAll(".close").forEach((close) => {
	close.onclick = (e) => {
		const target = e.target.parentElement.parentElement;
		console.log(target); // .modify
		
		target.classList.remove("show");
		target.previousElementSibling.classList.remove("hidden");
		
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
		const oldPwd = document.querySelector(".pwd_msg");
		if(oldPwd.classList.contains("show2")){
			oldPwd.previousElementSibling.select();
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

/* 스타일 유효성 검사(어려워서 따로 뻄ㅠ) */
const styleUpdate = () => {
	const styles = document.querySelectorAll(".style");
	const msg = document.querySelector(".style_msg");
	let count = 0;
	
	styles.forEach((style) => {
		if(style.checked == true)
			count++;
	});
	
	if(count <= 0){
		msg.classList.add("show2");
		return false;		
	}
	else {
		document.styleFrm.submit();
	}
}		
		
/* 회원탈퇴 */
document.querySelector(".sad").addEventListener('click', (e) => {
	if(confirm("정말 탈퇴하시겠습니까?🥲")){
		document.deleteFrm.submit();
	}
});
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />