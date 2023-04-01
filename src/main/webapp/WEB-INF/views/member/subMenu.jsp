<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="sub_area">
	<a href="${pageContext.request.contextPath}/member/myPage"><h3>My Page</h3></a>
	<nav class="sub">
		<div class="sub_list">
			<strong class="sub_title">게시글 정보</strong>
			<ul>
				<li><a href="${pageContext.request.contextPath}/member/myOotd">나의 ootd</a></li>
				<li><a href="${pageContext.request.contextPath}/member/myShare">나눔 목록</a></li>
				<li>좋아요</li>
			</ul>
		</div>
		<div class="sub_list">
			<strong class="sub_title">내 정보</strong>
			<ul>
				<li><a href="${pageContext.request.contextPath}/member/profile">프로필 정보</a></li>
				<li><a href="${pageContext.request.contextPath}/message/messageList">메세지</a></li>
			</ul>
		</div>
	</nav>
</div>