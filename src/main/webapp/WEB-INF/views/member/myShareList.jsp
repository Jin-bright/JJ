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
                	<button class="my_btn status_btn" data-status = "거래전">거래전</button>
                	<button class="my_btn status_btn" data-status = "거래완">거래완료</button>
                </div>
                <!-- 
                <div class="search_filter">
	                <select name="search" id="search" required>
	                    <option value="desc" selected>최신순</option>
	                    <option value="asc">오래된순</option>
	                </select>
                </div> 
                -->
			</div>
			<div class="share_content">
				<c:if test="${not empty shareList}">
					<table class="content_wrap share">
						<c:forEach items="${shareList}" var="share" varStatus="vs">
							<c:if test="${vs.index % 4 == 0}">
								<tr>
							</c:if>
							<td class="content_box">
								<a href="${pageContext.request.contextPath}/share/newShareView?no=${share.no}">
									<img src="${pageContext.request.contextPath}/uploadshares/newShare/${share.img}" class="share_img" />
									<p class="box_title">
										<!-- 제목 길이 제어 -->
										<c:choose>
									        <c:when test="${fn:length(share.name) > 15}">
									        	<c:out value="${fn:substring(share.name,0,14)}"/>...
									        </c:when>
									        <c:otherwise>
								            	<c:out value="${share.name}"/>
								            </c:otherwise> 
								        </c:choose>
									</p>
								</a>
								<div class="box_etc">
									<p class="box_btn">${share.status}</p>
									<p class="box_date">${share.regDate}</p>
								</div>
							</td>
							<c:if test="${vs.index % 4 == 3}">
								</tr>
							</c:if>
						</c:forEach>
					</table>
				</c:if>
				<c:if test="${empty shareList}">
					<div class="empty_box">
						<p>작성한 SHARE 없습니다.</p>
						<button class="my_btn" id="share_btn">
							<a href="${pageContext.request.contextPath}/share/newShareWholeList">SHARE 바로가기</a>
						</button>
					</div>
				</c:if>
			</div>
			<div class='pagebar'>
				${pagebar}
			</div>
		</div>
	</div>
</section>
<script>
document.querySelectorAll(".status_btn").forEach((btn) => {
	btn.onclick = (e) => {
		console.log("거래상태 : ", e.target.dataset.status);
		location.href = "${pageContext.request.contextPath}/member/shareSearch?status=" + e.target.dataset.status;
	};
});
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />