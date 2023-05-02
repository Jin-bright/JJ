<%@page import="com.sh.obtg.column.model.dto.Column"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Column column = (Column)request.getAttribute("column");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/columnView.css" />
    <section id="col_container">
    	<div id="col_etc">
	    	<span style="float: left;"><%= column.getRegDate() %></span>
	    	<span style="float: right;">조회수 <%= column.getReadCount() %></span>
	    	<hr/>    	
    	</div>
        <h2><%= column.getTitle() %></h2>
        <h3><%= column.getSubtitle() %></h3>
        <img src="${pageContext.request.contextPath}/upload/column/<%= column.getRenamedFilename() %>" alt="" style="width: 500px;">
        <p><%= column.getContent() %></p>
        <span id="editor">editor.<%= column.getWriter() %></span>
    </section>
<% 
	if(loginMember != null 
		&& loginMember.getMemberRole() == MemberRole.A){
%>
    <div id="edit_wrap">
    	<input type="button" value="modify" onclick="updateColumn()"/>
    	<input type="button" value="delete" onclick="deleteColumn()"/>
    </div>
<form 
	action="${pageContext.request.contextPath}/column/columnDelete"
	method="post"
	name="columnDeleteFrm">
	<input type="hidden" name="no" value="<%= column.getNo() %>"/>
</form>
<script>
/* 컬럼 수정폼 요청 */
const updateColumn = () => {
	location.href = "${pageContext.request.contextPath}/column/columnUpdate?no=<%= column.getNo() %>";
};

/* 컬럼 삭제 요청 */
const deleteColumn = () => {
	if(confirm("해당 컬럼을 삭제하시겠습니까?")){
		document.columnDeleteFrm.submit();
	}
};
<% } %>
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>