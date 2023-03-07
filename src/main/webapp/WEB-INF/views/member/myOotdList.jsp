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
		<div class="ootd_wrap">
			<div class="img_area">
				<c:if test="${loginMember.renamed == null}">
					<img src="${pageContext.request.contextPath}/image/망그러진곰.jpeg" class="profile" />
				</c:if>
				<c:if test="${loginMember.renamed != null}">
					<img src="${pageContext.request.contextPath}/upload/profile/${loginMember.renamed}" class="profile" />
				</c:if>
			</div>
			<div class="info_area">
				<p>${loginMember.memberId}</p>
				<p>${loginMember.name}</p>
			</div>
			<div class="ootd_content">
				<c:if test="${not empty ootdList}">
				</c:if>
				<c:if test="${empty ootdList}">
					<div class="empty_box">
						<p>작성한 OOTD가 없습니다.</p>
						<button class="my_btn" id="ootd_btn">
							<a href="${pageContext.request.contextPath}/ootd/newOotdWholeList">OOTD 바로가기</a>
						</button>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />