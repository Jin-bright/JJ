<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="sub_area">
	<a href="${pageContext.request.contextPath}/member/myPage"><h3>My Page</h3></a>
	<nav class="sub">
		<div class="sub_list">
			<strong class="sub_title">게시글 정보</strong>
			<ul>
				<li>나의 ootd</li>
				<li><a href="${pageContext.request.contextPath}/member/myShare">나눔 목록</a></li>
				<li>좋아요</li>
			</ul>
		</div>
		<div class="sub_list">
			<strong class="sub_title">내 정보</strong>
			<ul>
				<li><a href="${pageContext.request.contextPath}/member/profile">프로필 정보</a></li>
				<li>메세지</li>
			</ul>
		</div>
	</nav>
</div>