<%@page import="java.util.List"%>
<%@ page import="com.sh.obtg.member.model.dto.Member" %>
<%@ page import="com.sh.obtg.member.model.dto.MemberRole" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Member> members = (List<Member>) request.getAttribute("members");
	
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
%>   
<%@ include file="/WEB-INF/views/common/adminView.jsp" %>
<!-- 관리자용 admin.css link -->
  <style type="text/css">
 a:link { color: unset; text-decoration: none;}
 a:visited { color: unset; text-decoration: none;}
 a:hover { color: unset; text-decoration: underline;}
</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css" />
<style>
div#search-container  { width: 100%; margin:0 0 10px 0; padding:3px; }
div#search-memberId	  { display: <%= searchType == null || "member_id".equals(searchType) ? "inline-block" : "none" %>; }
div#search-memberName { display: <%= "name".equals(searchType) ? "inline-block" : "none" %>; }
div#search-gender     { display: <%= "gender".equals(searchType) ? "inline-block" : "none" %>; }

#btn-add1, .submit-button {
	font-size: 16px;
	border: 0;
	outline: 0;
	border-radius: 8px;
	background-color: #525252;
	color: white;
	padding: 3px 16px;
	font-weight: 500;

    margin-right: 0;
	margin-bottom: 8px;
}

</style>
<script>
window.addEventListener('load', () => {
	document.querySelector("#searchType").addEventListener('change', (e) => {
		console.log(e.target.value); // member_id, name, gender
		
	
		
		// 현재선택된 값에 상응하는 div만 노출
		let id; 
		switch(e.target.value){
		case "member_id" : id = "search-memberId"; break; 
		case "name" : id = "search-Name"; break; 
		case "gender" : id = "search-gender"; break; 
		}
		
		document.querySelector("#" + id).style.display = "inline-block";
	});
});

</script>


<section id="memberList-container">
	
	<br><br><br><br>

	<h1>회원관리</h1>
	
	<br><br><br>
	
	<div id="search-container">
	    <label for="searchType">검색타입 :</label> 
	    <select id="searchType">
	        <option value="member_id" <%= "member_id".equals(searchType) ? "selected" : "" %>>아이디</option>		
	        <option value="name" <%= "name".equals(searchType) ? "selected" : "" %>>회원명</option>
	        <option value="gender" <%= "gender".equals(searchType) ? "selected" : "" %>>성별</option>
	    </select>
	    <div id="search-memberId" class="search-type">
	        <form action="<%=request.getContextPath()%>/admin/memberFinder">
	            <input type="hidden" name="searchType" value="member_id"/>
	            <input type="text" name="searchKeyword"  size="25" placeholder="검색할 아이디를 입력하세요." 
	            	value="<%= "member_id".equals(searchType) ? searchKeyword : "" %>"/>
	            <button type="submit">검색</button>			
	        </form>	
	    </div>
	    <div id="search-memberName" class="search-type">
	        <form action="<%=request.getContextPath()%>/admin/memberFinder">
	            <input type="hidden" name="searchType" value="name"/>
	            <input type="text" name="searchKeyword" size="25" placeholder="검색할 이름을 입력하세요."
	            	value="<%= "name".equals(searchType) ? searchKeyword : "" %>" />
	            <button type="submit">검색</button>			
	        </form>	
	    </div>
	    <div id="search-gender" class="search-type">
	        <form action="<%=request.getContextPath()%>/admin/memberFinder">
	            <input type="hidden" name="searchType" value="gender"/>
	            <input type="radio" name="searchKeyword" value="M" <%= "gender".equals(searchType) && "M".equals(searchKeyword) ? "checked" : "" %>> 남
	            <input type="radio" name="searchKeyword" value="F" <%= "gender".equals(searchType) && "F".equals(searchKeyword) ? "checked" : "" %>> 여
	            <button type="submit">검색</button>
	        </form>
	    </div>
    </div>
    
    <br><br><br>
	
	
	<table id="tbl-admin">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>가입일</th>
				<th>성별</th>
				<th>생년월일</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>회원권한</th>
				<th>블랙리스트</th>
			</tr>
		</thead>
		<tbody>
		<% if(members.isEmpty()){ %>
			<tr>
				<td colspan="10">조회된 회원이 없습니다.</td>
			</tr>
		<% 
		   } else { 
			  for(Member member : members){
		%>
				<tr>
					<td><%= member.getMemberId() %></td>
					<td><%= member.getName() %></td>
					<td><%= member.getEnrollDate() %></td>
					<td><%= member.getGender() != null ? member.getGender() : "" %></td>
					<td><%= member.getBirthday() != null ? member.getBirthday() : "" %></td>
					<td><%= member.getEmail() != null ? member.getEmail() : "" %></td>
					<td><%= member.getPhone() %></td>
					<%-- <td><%= member.getStyle() %></td> --%>
					<td>
						<select class="member-role" data-member-id="<%= member.getMemberId() %>" data-member-birthday="<%= member.getBirthday() %>">
							<option value="<%= MemberRole.U %>" <%= member.getMemberRole() == MemberRole.U ? "selected" : "" %>>일반사용자</option>
							<option value="<%= MemberRole.A %>" <%= member.getMemberRole() == MemberRole.A ? "selected" : "" %>>관리자</option>
						</select>
					</td>
					<td><input id="btn-add1" type="button" data-member-id="<%= member.getEmail() %>" class="delMember" value="이동" /></td>
					
				</tr>
		<%
			  }			
			} 
		%>
		</tbody>
	</table>
	<div id="pagebar">
		<%= request.getAttribute("pagebar") %>
	</div>
	
	
	<form 
	action="<%= request.getContextPath() %>/admin/memberDelete"
	name="admMemberDelFrm"
	method="POST">
	<input type="hidden" name="memberId"/>
</form>
	
</section>
<form action="<%= request.getContextPath() %>/admin/updateMemberRole" name="memberRoleUpdateFrm" method="POST">
	<input type="hidden" name="memberId" />
	<input type="hidden" name="memberRole" />
</form>

<script>
document.querySelectorAll(".member-role").forEach((select) => {
	select.addEventListener('change', (e) => {
		console.log(e.target.value);
		console.log(e.target.dataset.memberId);
		const memberId = e.target.dataset.memberId;
		const memberRole = e.target.value;
		
		
		if(confirm(`[\${memberId}]회원의 권한을 \${memberRole}로 변경하시겠습니까?`)){			
			const frm = document.memberRoleUpdateFrm;
			frm.memberId.value = memberId;
			frm.memberRole.value = memberRole;
			frm.submit();
		}
		else {
			
			e.target.querySelector("option[selected]").selected = true;
		}
		
		
		
	});
});



//블랙리스트 추가 
$(".delMember").click(function(){
	const memberId = $(this).data("memberId");
	if(confirm(memberId+"를 정말 추방시키겠습니까?")){
		const $frm = $(document.admMemberDelFrm);
		$frm.find("[name=memberId]").val(memberId);
		$(document.admMemberDelFrm).submit();
	}
});

</script>



<%@ include file="/WEB-INF/views/common/footer.jsp" %>