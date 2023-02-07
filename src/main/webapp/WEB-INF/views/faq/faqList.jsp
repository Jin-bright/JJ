<%@page import="com.sh.obtg.faq.model.dto.faq"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<faq> faqList = (List<faq>) request.getAttribute("faqList");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board2.css" />
<section id="board-container">
	<h2>INFO</h2>
	
	<%-- <% if(loginMember != null){ %> --%>
	<a href="<%= request.getContextPath() %>/faq/faqEnroll">
	<button id="btn-add1">글쓰기</button>
	</a>
	<%-- <% } %> --%>

	<table id="tbl-board">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<%-- <th>첨부파일</th>첨부파일이 있는 경우 /images/file.png 표시 width:16px --%>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
		<% if(faqList.isEmpty()){ %>	
			<tr>
				<td colspan="6">조회된 게시물이 없습니다.</td>
			</tr>
		<% 
		   } else { 
			 for(faq faq : faqList){
		%>
			<tr>
				<td><%= faq.getNo() %></td>
				<td>
					<a href="<%= request.getContextPath() %>/faq/faqView?no=<%= faq.getNo() %>"><%= faq.getTitle() %></a>
				</td>
				<td><%= faq.getWriter() %></td>
				<td><%= faq.getRegDate() %></td>
				<td><%= faq.getReadCount() %></td>
			</tr>
		<%
			 }
		   } 
		%>
		</tbody>
	</table>

 	<div id='pagebar'>
		<%=(String)request.getAttribute("pagebar") %>
	</div> 
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
