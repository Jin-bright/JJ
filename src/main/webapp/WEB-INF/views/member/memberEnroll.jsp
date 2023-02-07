<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Cookie[] cookies = request.getCookies();
	String saveId = null;
	if(cookies != null){
		for(Cookie cookie : cookies){
		String name = cookie.getName();
		String value = cookie.getValue();
			if("saveId".equals(name))
				saveId = value;
		}
	} 
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/memberEnroll.css">
<%if(loginMember != null){ %>
	<script src = "<%=request.getContextPath()%>/js/ws.js"></script>
<% } %>
<div class="form">

      <ul class="tab-group">
        <li class="tab active"><a id="atag" href="#signup">Sign Up</a></li>
        <li class="tab"><a id="atag" href="#login">Log In</a></li>
      </ul>
      <div class="tab-content">
      <div id="login">
          <form id="loginFrm" name="loginFrm" action="<%= request.getContextPath() %>/member/login" method="post">
            <div class="field-wrap">
            <label>
              ID<span class="req"></span>
            </label>
            <td><input type="text" name="memberId" id="memberId"  value="<%= saveId != null ? saveId : "" %>" required></td>
          </div>
          <div class="field-wrap">
            <label>
              Password<span class="req"></span>
            </label>
            <input type="password" name="password" id="password" required />
          </div>
          <div class="field-wrap">
          <input type="checkbox" name="saveId" id="saveId" <%= saveId != null ? "checked" : "" %> style="display:inline-block; width:30px;"/>
		  <label style="font-size:15px">ID 저장</label>
          </div>
          <button class="button button-block"/>LogIn</button>
          </form>
        </div>
        <!-- login form 끝 -->
      
        <div id="signup">   
          <form name="memberEnrollFrm" method="POST" action ="<%= request.getContextPath()%>/member/memberEnroll">
          <div class="top-row">
            <div class="field-wrap">
              <label class="top">
                ID<span class="req">*</span>
              </label>              
              <input type="text"  name="memberId" id="_memberId" placeholder="영문자/숫자 4글자이상" required autocomplete="off" required />
              <input type="button" value="ID 중복검사" onclick = "checkIdDuplicate();" style="margin-top: 5px; background-color: lightpink; border: 0px; cursor: pointer;"/>
			  <input type = "hidden" id = "idValid" name="idValid" value= "0"/>
			  
            </div>
            </div>
            <div class="field-wrap">
              <label>
                Password<span class="req">*</span>
              </label>
              <input type="password" name="password" id="_password" placeholder="영문자/숫자/특수문자 !@#$% 4글자이상" required autocomplete="off"/>
            </div>
          <div class="field-wrap">
            <label>
              Password-Check<span class="req">*</span>
            </label>
            <input type="password" id="passwordCheck" autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Name<span class="req">*</span>
            </label>
            <input type="text" name="name" id="name" autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Nick Name<span class="req">*</span>
            </label>
            <input type="text" name="nickname" id="nickname" autocomplete="off"/>
          </div>
          
           <div class="field-wrap">
            <label style="display: block;">
              Gender<span class="req">*</span>
            </label>
            <div id="gender-group">
					<label for="gender0">남</label>
					<input type="radio" name="gender" id="gender0" value="M">
					<label for="gender1">여</label>
					<input type="radio" name="gender" id="gender1" value="F" checked>
			</div>	
			</div>
			
          <div class="field-wrap">
            <label>
             Birthday <span class="req"></span>
            </label>
            <input type="date" name="birthday" id="birthday" autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Email<span class="req">*</span>
            </label>
            <input type="email" name="email" id="email" required autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Phone<span class="req">*</span>
            </label>
            <input type="text" name="phone" id="phone" autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Introduce<span class="req">*</span>
            </label>
            <input type="text" name="introduce" id="introduce" autocomplete="off"/>
          </div>
          
          <!-- <div class="field-wrap">
            <label for="celeb-enroll-profile">Profile</label>
            <input type="file" name="profile" id="celeb-enroll-profile" />
            <button type="submit">등록</button>
          </div>
           -->
          <fieldset class="checkbox-group">
	<legend class="checkbox-group-legend" style="text-align:center; font-size:x-large; margin-bottom:10px;">Choose your Style*</legend>
	<tr id="check-box">
		<td >
			<input type="checkbox" class="style" name="style" id="S1" value="러블리"><label class="stylelb" for="S1"><img src="<%=request.getContextPath()%>/image/love.png" alt="">&nbsp;lovely</label>		
		</td>
		<td>
			<input type="checkbox" class="style" name="style" id="S2" value="댄디"><label class="stylelb" for="S2"><img src="<%=request.getContextPath() %>/image/dandy.png" alt=""/>&nbsp;Dandy</label>
		</td>
		<td>
			<input type="checkbox" class="style" name="style" id="S3" value="포멀" checked><label class="stylelb" for="S3"><img src="<%=request.getContextPath()%>/image/formal.png" alt="">&nbsp;Formal</label>
		</td>
		<td>
			<input type="checkbox" class="style" name="style" id="S4" value="스트리트"><label class="stylelb" for="S4"><img src="<%=request.getContextPath()%>/image/street.png" alt="">&nbsp;Street</label>	
		</td>
		<td>
			<input type="checkbox" class="style" name="style" id="S5" value="걸리쉬"><label class="stylelb" for="S5"><img src="<%=request.getContextPath()%>/image/girl.png" alt="">&nbsp;Girlish</label>
		</td>
		<td>
			<input type="checkbox" class="style" name="style" id="S6" value="레트로" checked><label class="stylelb" for="S6"><img src="<%=request.getContextPath()%>/image/retro.png" alt="">&nbsp;Retro</label>
		</td>
		<td>
			<input type="checkbox" class="style" name="style" id="S7" value="로맨틱"><label class="stylelb" for="S7"><img src="<%=request.getContextPath()%>/image/romantic.png" alt="">&nbsp;Romantic</label>
		</td>
		<td>
			<input type="checkbox" class="style" name="style" id="S8" value="시크"><label class="stylelb" for="S8"><img src="<%=request.getContextPath()%>/image/chic.png" alt="">&nbsp;Chic</label>	
		</td>
		<td>
			<input type="checkbox" class="style" name="style" id="S9" value="아메카지"><label class="stylelb" for="S9"><img src="<%=request.getContextPath()%>/image/amekaji.png" alt="">&nbsp;Amekaji</label>		
		</td>
	</tr>
</fieldset>
          <button type="submit" value="가입" class="button button-block"/>Sing up</button>          
          </form>
        </div>
        
      </div><!-- tab-content -->
      
</div> <!-- /form -->
<form action="<%= request.getContextPath() %>/member/checkIdDuplicate" name = "checkIdDuplicateFrm">
    <input type="hidden" name="memberId" />
</form>
<script>
/**
 * 중복검사이후 다시 아이디를 수정한 경우.
 */
document.querySelector("#_memberId").onfocus = (e) => {
	document.querySelector("#idValid").value = "0";
};
const checkIdDuplicate = () => {
	const memberId = document.querySelector("#_memberId");
	// 아이디 - 영문자/숫자 4글자이상
	 if(!/^[A-Za-z0-9]{4,}$/.test(memberId.value)){
		alert("아이디는 영문자/숫자 4글자이상이어야합니다.");
		memberId.select();
		return;
	}; 
	// 폼의 액션 주소를 사용하기 때문에 open의 url은 비워둔다.
	const title = "checkIdDuplicatePopup";
	open("", title,"width=300px, heigth=200px, left=100px, top=100px");
	
	const frm = document.checkIdDuplicateFrm
	frm.target = title; // 폼을 팝업에 제출
	frm.memberId.value = memberId.value;
	frm.submit();
};

document.memberEnrollFrm.onsubmit = (e) => {
	const memberId = document.querySelector("#_memberId");
	const idValid = document.querySelector("#idValid");
	const password = document.querySelector("#_password");
	const passwordCheck = document.querySelector("#passwordCheck");
	const memberName = document.querySelector("#name");
	const birthday = document.querySelector("#birthday");
	const email = document.querySelector("#email");
	const phone = document.querySelector("#phone");
	// 아이디 - 영문자/숫자 4글자이상
	if(!/^[A-Za-z0-9]{4,}$/.test(memberId.value)){
		alert("아이디는 영문자/숫자 4글자이상이어야합니다.");
		memberId.select();
		return false;
	}
	
	// 아이디중복검사 통과여부
	if(idValid.value !== '1'){
		alert("아이디 중복검사 해주세요.");
		memberId.nextElementSibling.focus(); // 중복검사 버튼
		return false;
	}
	
	// 비밀번호 - 영문자/숫자/특수문자 !@#$% 4글자이상
	if(!/^[A-Za-z0-9!@#$%]{4,}$/.test(password.value)){
		alert("비밀번호는는 영문자/숫자/특수문자(!@#$%) 4글자이상이어야 합니다.");
		password.select();
		return false;
	}
	
	// 비밀번호/비밀번호확인 동일
	if(password.value !== passwordCheck.value){
		alert("두 비밀번호가 일치하지 않습니다.");
		password.select();
		return false;
	}
	
	// 이름 - 한글 2글자이상
	if(!/^[가-힣]{2,}$/.test(memberName.value)){
		alert("이름은 한글 2글자 이상이어야 합니다.");
		memberName.select();
		return false;
	}
	
	if(email.value == "Ban"){
		alert("가입이 제한된 이메일 입니다.");
		return false;
	}
};

document.celebEnrollFrm.addEventListener("submit", (e) => {
	e.preventDefault(); // 폼제출 방지
	
	// FormData객체 생성
	const frmData = new FormData(e.target);
	
	// 등록 POST 
	$.ajax({
		url : "<%= request.getContextPath() %>/json/celeb/enroll",
		method : "POST",
		data : frmData,
		dataType : "json",
		contentType : false,
		processData : false,
		success(data){
			console.log(data);
			alert(data.result);
		},
		error : console.log,
		complete(){
			e.target.reset();
		}
		
	});
		
});

</script>

<script type="text/javascript" src="<%= request.getContextPath()%>/js/memberEnroll.js"></script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>