<%@page import="com.sh.obtg.member.model.service.MemberService"%>
<%@page import="com.sh.obtg.member.model.dto	.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	int Page = (int)request.getAttribute("Page");
	List<String> list = (List<String>)request.getAttribute("list");
%>
<%@ include file="/WEB-INF/views/common/adminView.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/black.css">

<style>
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
<div id="memberList-container">
<%-- 	<h2><a href="<%=request.getContextPath()%>/admin/memberList">회원관리</a> |  --%><!-- <span style="text-decoration:underline;">블랙리스트</span></h2> -->
	<!-- 회원조회 -->
	<div id="tableArea">
		<table id="tbl-member">
			<thead>
				<tr>
				<br><br><br><br>
				<h1 align="center" >블랙리스트</h1>
				<br><br><br>
					<th>no</th>
					<th>이메일</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody>
					<%for(String s : list) { %>
				<tr>
					<td><%= (Page-1)*10+list.indexOf(s)+1 %></td>
					<td><%= s %></td>
					<td><input id="btn-add1" type="button" data-email="<%=s %>" class=unBan value="해제" /></td>
				</tr>
					<%} %>
			</tbody>
		</table>
		<!-- 페이지바 -->
		<div id='pagebar'>
		<%=(String)request.getAttribute("pagebar") %>
	</div> 
	</div>
</div>

<form 
	action="<%= request.getContextPath() %>/admin/blackList"
	name="admMemberUnBanFrm"
	method="POST">
	<input type="hidden" name="email"/>
</form>

<script>
$(".unBan").click(function(){
	var email = $(this).data("email");
	if(confirm(email+"의 이메일을 블랙리스트에서 해제 하시겠습니까?")){
		var $frm = $(document.admMemberUnBanFrm);
		$frm.find("[name=email]").val(email);
		$(document.admMemberUnBanFrm).submit();
	}
});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>