<%@page import="com.sh.obtg.member.model.dto.Like"%>
<%@page import="com.sh.obtg.share.model.dto.ShareBoard"%>
<%@page import="com.sh.obtg.ootd.model.dto.OotdBoard"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Like> ootdLikes = (List<Like>)request.getAttribute("ootdLikes");
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/memberView.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/memberLike.css" />
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
	<section id="like-container">
	<div id="like-nav">
		<h3>나의 <span style="color: orange; font-weight: 900; font-size: 15px;">좋아요</span></h3>
		<span style="font-weight: 900;"><a href="<%= request.getContextPath() %>/member/memberOotdLike">OOTD</a></span>
		<span><a href="<%= request.getContextPath() %>/member/memberShareLike">SHARE</a></span>
	</div>
	<table id="like-wrap">
	<% 
		if(ootdLikes.size() > 0){
			for(int i = 0; i < ootdLikes.size(); i++) {
				if(i % 4 == 0 ){
	%>
		<tr>
		<% } %>
			<td>
				<a href="<%= request.getContextPath() %>/ootd/ootdView?no=<%= ootdLikes.get(i).getNo() %>">
					<img src="<%= request.getContextPath() %>/uploadootds/ootd/<%= ootdLikes.get(i).getRenamed_filename() %>" alt="" />
					<p><%= ootdLikes.get(i).getTitle() %></p>
				</a>
			</td>
			<% if(i % 4 == 3){%>
  		</tr>
	<% 
				}   	    
  	 		}
		} else if(ootdLikes.size() == 0) {
	%>
		<div id="empty-box">
			<h2>앗 아직 <span style="color: purple;">ootd</span>게시판에 좋아요를 누르지 않았어요!🥲</h2>
			<p><a href="<%=request.getContextPath()%>/ootd/ootdWholeList">OOTD 게시판으로 이동</a></p>
		</div>
	<% } %>

	</table>
	</section>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>