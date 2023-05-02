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
		<div class="wish_wrap">
			<h3>관심 상품</h3>
		</div>
		<div class="wish-content">
			<c:if test="${not empty wishList}">
				<table class="content_wrap share">
					<c:forEach items="${wishList}" var="wish" varStatus="vs">
						<c:if test="${vs.index % 4 == 0}">
							<tr>
						</c:if>
						<td class="content_box">
							<a href="${pageContext.request.contextPath}/share/newShareView?no=${wish.boardNo}">
								<img src="${pageContext.request.contextPath}/uploadshares/newShare/${wish.img}" class="share_img"/>
							</a>
							<div class="wish-info">
								<p class="box_title">
									<!-- 제목 길이 제어 -->
									<c:choose>
								        <c:when test="${fn:length(wish.name) > 12}">
								        	<c:out value="${fn:substring(wish.name,0,11)}"/>...
								        </c:when>
								        <c:otherwise>
							            	<c:out value="${wish.name}"/>
							            </c:otherwise> 
							        </c:choose>
								</p>
								<img src="${pageContext.request.contextPath}/image/mark_full.png" class="wish" data-board-no="${wish.boardNo}">
							</div>
						</td>
						<c:if test="${vs.index % 4 == 3}">
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</c:if>
			<c:if test="${empty wishList}">
				<div class="empty_box">
					<p>현재 등록된 관심 물품이 없습니다.</p>
					<div class="btn_wrap">
						<button class="my_btn" id="share_btn">
							<a href="${pageContext.request.contextPath}/share/newShareWholeList">SHARE 바로가기</a>
						</button>
					</div>
				</div>
			</c:if>
		</div>
		<div class='pagebar'>
			${pagebar}
		</div>
	</div>
</section>
<script type="text/javascript">
/* 관심 */
document.querySelectorAll(".wish").forEach((wish) => {
	wish.onclick = (e) => {
		// console.log(e.target);
		const no = e.target.dataset.boardNo;
		
		$.ajax({
			url : '${pageContext.request.contextPath}/share/shareLike?no=' + no,
			method: "post",
			dataType: "json",
			success(data){
				if(data === 1) e.target.src="${pageContext.request.contextPath}/image/mark_full.png"
				else e.target.src="${pageContext.request.contextPath}/image/mark_emp.png"
			},
			error : console.log
		});
	};	
});
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />