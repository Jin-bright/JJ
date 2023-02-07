<%@page import="com.sh.obtg.member.model.dto.MyPost"%>
<%@page import="com.sh.obtg.share.model.dto.ShareBoard"%>
<%@page import="com.sh.obtg.ootd.model.dto.OotdBoard"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<MyPost> ootdBoardList = (List<MyPost>)request.getAttribute("ootdBoardList");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/memberView.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/memberBoardList.css" />
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
	<section id="board-container">
		<div id="nav-container"> 
			<h6 style="font-size: 13px; color: darkgray;">내가 작성한 글을 확인해보세요!</h6>
			<span style="font-weight: 900"><a href="<%= request.getContextPath() %>/member/memberOotdList">OOTD</a></span>
			<span><a href="<%= request.getContextPath() %>/member/memberShareList">SHARE</a></span>
		</div>
		<table id="boardList-wrap">
		<% 
			if(ootdBoardList.size() > 0 ){
				for(int i = 0; i < ootdBoardList.size(); i++) {
					if(i % 4 == 0 ){
		%>
			<tr>
				<% } %>
				<td>
					<a href="<%= request.getContextPath() %>/ootd/ootdView?no=<%= ootdBoardList.get(i).getNo() %>">
						<img src="<%= request.getContextPath() %>/uploadootds/ootd/<%= ootdBoardList.get(i).getRenamedFilename() %>" alt="" />
						<p id="b_title"><%= ootdBoardList.get(i).getTitle() %></p>
						<p id="b_txt"><%= ootdBoardList.get(i).getRegDate() %> | 조회수 : <%= ootdBoardList.get(i).getReadCount() %></p>
					</a>
				</td>
				<% if(i % 4 == 3){%>
			</tr>
		<% 
					}   	    
				}
			} else {
		%> 
			<div id="empty-box">
				<h2>앗 아직 <span style="color: purple;">ootd</span>게시판에 글을 작성하지 않았어요!🥲</h2>
				<p><a href="<%=request.getContextPath()%>/ootd/ootdWholeList">OOTD 게시판으로 이동</a></p>
			</div>
		<% } %>
		</table>
	</section>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>