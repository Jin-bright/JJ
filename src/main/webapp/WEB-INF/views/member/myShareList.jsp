<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myPage.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css" />
<section class="container">
<jsp:include page="/WEB-INF/views/member/subMenu.jsp" />
	<div class="content_area">
		<div class="share_wrap">
			<h3>나눔 목록</h3>
			<div class="search_wrap">
                <div class="status_filter">
                	<button class="my_btn">거래전</button>
                	<button class="my_btn">거래후</button>
                </div>
                <div class="search_filter">
	                <select name="search" id="search" required>
	                    <option value="desc" selected>최신순</option>
	                    <option value="asc">오래된순</option>
	                </select>
                </div>
			</div>
		</div>
	</div>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />