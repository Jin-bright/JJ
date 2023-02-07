<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/updatePassword.css">
<div class= "main">
 <div class="container-menu">
	 <ul>
	 <div class="container-li">
	 <a href="<%= request.getContextPath()%>/member/memberView">
	 <img src="<%= request.getContextPath()%>/image/modify.png" alt="" />
	 	<li>&nbsp;Info</li>
	 </a>
	 </div>
	 <div class="container-li">
	 <a href="<%= request.getContextPath() %>/member/memberBoardList">
	 <img src="<%= request.getContextPath()%>/image/list.png" alt="" />
	 	 <li>&nbsp;List</li>
	 </a>
	 </div>
	 <div class="container-li">
	 <a href="">
	 <img src="<%= request.getContextPath()%>/image/chat.png" alt="" />
	 	<li>&nbsp;Message</li>
	 </a>
	 </div>
	 <div class="container-li">
	 <a href="">
	 <img src="<%= request.getContextPath()%>/image/like.png" alt="" />
	 	<li>&nbsp;Like</li>
	 </a>
	 </div>
	 </ul>
</div>
<hr />
	<section id=enroll-container>
	<div class="form">
		<h2>비밀번호 변경</h2>
		<form 
			name="passwordUpdateFrm" 
			method="post" >
			<table>
				<tr>
					<th>현재 비밀번호</th>
					<td><input type="password" name="oldPassword" id="oldPassword" required></td>
				</tr>
				<tr>
					<th>변경할 비밀번호</th>
					<td>
						<input type="password" name="newPassword" id="newPassword" required>
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>	
						<input type="password" id="newPasswordCheck" required><br>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<input type="submit"  value="변경" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	</section>
	</div>
	<script>
const oldPassword = document.querySelector("#oldPassword");
const newPassword = document.querySelector("#newPassword");
const newPasswordCheck = document.querySelector("#newPasswordCheck");
/**
 * 폼제출 유효성검사
 */ 
document.passwordUpdateFrm.onsubmit = function(){
	
	if(!passwordValidate()){
		return false;
	}
	
	if(oldPassword.value == newPassword.value){
		alert("기존비밀번호와 신규비밀번호는 같을 수 없습니다.");
		oldPassword.select();
		return false;
	}
	
};
document.body.addEventListener('submit', (e) => {

	if(!/^[A-Za-z0-9!@#$%]{4,}$/.test(newPassword.value)){
		alert("비밀번호는는 영문자/숫자/특수문자(!@#$%) 4글자이상이어야 합니다.");
		newPassword.select();
		return;
	}	
	}
	
});

document.querySelector("#newPasswordCheck").onblur(passwordValidate);

/**
 * 신규비밀번호 일치 검사 
 */ 
function passwordValidate(){
	if(newPassword.value !== newPasswordCheck.value){
		alert("입력한 비밀번호가 일치하지 않습니다.");
		newPassword.select();
		return false;
	}
	return true;	
}
</script>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
