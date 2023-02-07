<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page import="com.sh.obtg.member.model.dto.Gender"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/memberView.css">
<div class="main">
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
	 <a href="<%= request.getContextPath() %>/member/memberOotdList">
	 <img src="<%= request.getContextPath()%>/image/list.png" alt="" />
	 	 <li>&nbsp;List</li>
	 </a>
	 </div>
	 <div class="container-li">
	 <a href="<%= request.getContextPath() %>/message/messageList">
	 <img src="<%= request.getContextPath()%>/image/chat.png" alt="" />
	 	<li>&nbsp;Message</li>
	 </a>
	 </div>
	 <div class="container-li">
	 <a href="<%= request.getContextPath() %>/member/memberOotdLike">
	 <img src="<%= request.getContextPath()%>/image/like.png" alt="" />
	 	<li>&nbsp;Like</li>
	 </a>
	 </div>
	 </ul>
</div>
<hr />
<div class="form">
<h2 style="text-align: center;">개인 정보</h2>
<br />
          
          <form name="memberView" method="POST" action ="<%= request.getContextPath()%>/member/memberUpdate">
          <div class="container-main">
            <div class="field-wrap">
              <label class="top">
                ID<span class="req">*</span>
              </label>
              <input type="text" name="memberId" id="memberId" value="<%= loginMember.getMemberId() %>" readonly/>		  
            </div>
             </div>
            <!--<div class="field-wrap">
              <label>
                Password<span class="req">*</span>
              </label>
              <input type="password" name="password" id="password"/>
            </div>
          <div class="field-wrap">
            <label>
              Password-Check<span class="req">*</span>
            </label>
            <input type="password" id="passwordCheck"/>
            <button class="button password-change" onclick="updatePassword();">비밀번호 변경</button>
          </div> -->
          
          <div class="field-wrap">
            <label>
              Name<span class="req">*</span>
            </label>
            <input type="text" name="name" id="name" value="<%= loginMember.getName() %>" required/>
          </div>
          
          <div class="field-wrap">
            <label>
              Nick Name<span class="req">*</span>
            </label>
            <input type="text" name="nickname" id="nickname" value="<%= loginMember.getNickname() %>" required/>
          </div>
          
           <div class="field-wrap">
            <label style="display: block;">
              Gender<span class="req"></span>
            </label>
            	<div id="gender-group">
					<label for="gender0">남</label>
					<input type="radio" name="gender" id="gender0" value="M" <%= loginMember.getGender() == Gender.M ? "checked" : "" %>>
					
					<label for="gender1">여</label>
					<input type="radio" name="gender" id="gender1" value="F" <%= loginMember.getGender() == Gender.F ? "checked" : "" %>>
					
					</div>
			</div>	
			
          <div class="field-wrap">
            <label>
             Birthday <span class="req"></span>
            </label>
            <input type="date" name="birthday" id="birthday" value="<%= loginMember.getBirthday() %>"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Email<span class="req">*</span>
            </label>
            <input type="email" name="email" id="email" value="<%= loginMember.getEmail() %>" required/>
          </div>
          
          <div class="field-wrap">
            <label>
              Phone<span class="req">*</span>
            </label>
            <input type="text" name="phone" id="phone" value="<%= loginMember.getPhone() %>" required/>
          </div>
          
          <div class="field-wrap">
            <label>
              Introduce<span class="req"></span>
            </label>
            <input type="text" name="introduce" id="introduce" value="<%= loginMember.getIntroduce() %>"/>
          </div>
          
         <%--  <div class="field-wrap">
            <label>
              Profile-file<span class="req"></span>
            </label>
            <input type="file" name="upFile" id="upFile" value="<%= loginMember.getOriginal() %>"/>
          </div> --%>
        <%
	String style = loginMember.getStyle(); // nullable
	List<String> styleList = null;
	if(style != null){
		styleList = Arrays.asList(style.split(","));		
	}
	
%>
          <fieldset class="checkbox-group">
	<legend class="checkbox-group-legend"style="text-align:center; font-size:x-large; margin-bottom:10px;">Choose your Style *</legend>
	<tr id="check-box" required>
		<td>
			<input type="checkbox" class="style" name="style" id="S1" value="러블리"<%= styleList != null && styleList.contains("러블리") ? "checked" : "" %>><label class="stylelb" for="S1"><img src="<%=request.getContextPath()%>/image/love.png" alt="">&nbsp;lovely</label>	
		</td>
		<td>
			<input type="checkbox" class="style" name="style" id="S2" value="댄디"<%= styleList != null && styleList.contains("댄디") ? "checked" : "" %>><label class="stylelb" for="S2"><img src="<%=request.getContextPath()%>/image/dandy.png" alt="">&nbsp;Dandy</label>
		</td>
		<td>
			<input type="checkbox" class="style" name="style" id="S3" value="포멀"<%= styleList != null && styleList.contains("포멀") ? "checked" : "" %>><label class="stylelb" for="S3"><img src="<%=request.getContextPath()%>/image/formal.png" alt="">&nbsp;Formal</label>
		</td>
		<td>
			<input type="checkbox" class="style" name="style" id="S4" value="스트리트"<%= styleList != null && styleList.contains("스트리트") ? "checked" : "" %>><label class="stylelb" for="S4"><img src="<%=request.getContextPath()%>/image/street.png" alt="">&nbsp;Street</label>
		</td>
		<td>
			<input type="checkbox" class="style" name="style" id="S5" value="걸리쉬"<%= styleList != null && styleList.contains("걸리쉬") ? "checked" : "" %>><label class="stylelb" for="S5"><img src="<%=request.getContextPath()%>/image/girl.png" alt="">&nbsp;Girlish</label>
		</td>
		<td>
			<input type="checkbox" class="style" name="style" id="S6" value="레트로"<%= styleList != null && styleList.contains("레트로") ? "checked" : "" %>><label class="stylelb" for="S6"><img src="<%=request.getContextPath()%>/image/retro.png" alt="">&nbsp;Retro</label>
		</td>
		<td>
			<input type="checkbox" class="style" name="style" id="S7" value="로맨틱"<%= styleList != null && styleList.contains("로맨틱") ? "checked" : "" %>><label class="stylelb" for="S7"><img src="<%=request.getContextPath()%>/image/romantic.png" alt="">&nbsp;Romantic</label>
		</td>
		<td>
			<input type="checkbox" class="style" name="style" id="S8" value="시크"<%= styleList != null && styleList.contains("시크") ? "checked" : "" %>><label class="stylelb" for="S8"><img src="<%=request.getContextPath()%>/image/chic.png" alt="">&nbsp;Chic</label>
		</td>
		<td>
			<input type="checkbox" class="style" name="style" id="S9" value="아메카지"<%= styleList != null && styleList.contains("아메카지") ? "checked" : "" %>><label class="stylelb" for="S9"><img src="<%=request.getContextPath()%>/image/amekaji.png" alt="">&nbsp;Amekaji</label>
		</td>
	</tr>
</fieldset>
          <input id ="mmve" type="submit" value="정보수정"/>          
          <input id ="mmve" type="button" onclick="updatePassword();" value="비밀번호변경"/>         
          <input id ="mmve" type="button" onclick="deleteMember();" value="탈퇴"/>         
          </form>
        </div>
        </div>
     <!-- /form -->
<form action="<%= request.getContextPath() %>/member/memberDelete" method="POST" name="memberDeleteFrm"></form>
<script>
	const deleteMember = () => {
		if(confirm('정말 회원탈퇴하시겠습니까?')){
			document.memberDeleteFrm.submit();
		}
	};
	
	const updatePassword = () => {
		location.href = "<%= request.getContextPath()%>/member/updatePassword";
	};
	
	document.memberUpdateFrm.onsubmit = (e) => {
		const memberName = document.querySelector("#memberName");
		const email = document.querySelector("#email");
		const phone = document.querySelector("#phone");
		const nickname = document.querySelector("#nickname");
		
		// 이름 - 한글 2글자이상
		if(!/^[가-힣]{2,}$/.test(memberName.value)){
			alert("이름은 한글 2글자 이상이어야 합니다.");
			memberName.select();
			return false;
		}
		
		// 전화번호는 숫자 01012345678 형식
		if(!/^010[0-9]{8}$/.test(phone.value)){
			alert("전화번호가 유효하지 않습니다.");
			phone.select();
			return false;
		}
	};
</script>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>