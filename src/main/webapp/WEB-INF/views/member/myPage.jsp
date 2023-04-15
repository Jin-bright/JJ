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
		<div class="my_wrap">
			<div class="box1">
				<div class="my_img">
					<c:if test="${loginMember.renamed == null}">
						<img src="${pageContext.request.contextPath}/image/망그러진곰.jpeg" class="profile" />
					</c:if>
					<c:if test="${loginMember.renamed != null}">
						<img src="${pageContext.request.contextPath}/upload/profile/${loginMember.renamed}" class="profile" />
					</c:if>
				</div>
				<div class="my_info">
					<strong class="my_id">${loginMember.memberId}</strong>
					<p class="my_email">${loginMember.email}</p>
				</div>
			</div>
			<div>
				<div class="style_box">
					<c:forEach items="${loginMember.style}" var="style">
							<i><sup>#</sup>${style}</i>
					</c:forEach>
				</div>
				<button class="my_btn" onclick="location.href='${pageContext.request.contextPath}/member/profile'">프로필 수정</button>
			</div>
		</div>
		<div class="title_box">
			<h4>나의 OOTD</h4>
			<a href="${pageContext.request.contextPath}/member/myOotd">더보기</a>
		</div>
		<div class="content_container">
			<c:if test="${not empty ootdList}">
				<div class="content_wrap ootd">
					<c:forEach items="${ootdList}" var="ootd" varStatus="vs">
						<c:if test="${vs.index <= 3}">
							<div class="content_box">
								<a href="${pageContext.request.contextPath}/ootd/ootdView?no=${ootd.no}">
									<img src="${pageContext.request.contextPath}/uploadootds/ootd/${ootd.img}" class="ootd_img" />
									<p class="box_title">${ootd.title}</p>
									<p class="box_count">${ootd.readCount}</p>
								</a>
							</div>
						</c:if>
					</c:forEach>
				</div>
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
		<div class="title_box">
			<h4>나눔 목록</h4>
			<a href="${pageContext.request.contextPath}/member/myShare">더보기</a>
		</div>
		<div class="content_container">
			<c:if test="${not empty shareList}">
				<div class="content_wrap share">
					<c:forEach items="${shareList}" var="share" varStatus="vs">
						<c:if test="${vs.index <= 3}">
							<div class="content_box">
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
									<p class="box_btn" data-name="${share.name}" data-no="${share.no}">${share.status}</p>
									<p class="box_date">${share.regDate}</p>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
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
		<div class="title_box">
			<h4>관심 물품</h4>
			<a href="">더보기</a>
		</div>
		<div class="content_container">
			<div class="empty_box">
				<p>현재 등록된 관심 물품이 없습니다.</p>
				<div class="btn_wrap">
					<button class="my_btn" id="share_btn">
						<a href="${pageContext.request.contextPath}/share/newShareWholeList">SHARE 바로가기</a>
					</button>
				</div>
			</div>
		</div>
	</div>
</section>
<c:if test="${not empty shareList}">
<!-- 나눔상태 업데이트 히든폼 -->
<form name="shareStatusUpdateFrm" method="post" action="${pageContext.request.contextPath}/member/shareStatusUpdate">
	<input type="hidden" name="no" id="no">
</form>
<script>
/* 나눔 상태변경 */
document.querySelectorAll(".box_btn").forEach((btn) => {
	btn.onclick = (e) => {
		const no = e.target.dataset.no;
		const status = e.target.innerText;
		// 물품이름 길이제어
		const _name = e.target.dataset.name;
		let name;
		if(_name.length > 15){
			name = _name.substr(0,14) + "...";
		}
		else name = _name;
		
		// 이미 거래가 완료된 물품은 상태를 변경할 수 없음
		if(status == '거래완료') {
			alert("이미 거래가 완료된 물품입니다.");
			return;
		}
		
		// 상태변경전 재확인
		if(confirm(`[\${name}]의 상태를 거래완료로 변경하시겠습니까?
거래완료된 물품은 거래전으로 상태를 변경할 수 없습니다.`)){
			document.querySelector("#no").value = no;
			document.shareStatusUpdateFrm.submit();
		}
	};
});
</script>
</c:if>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />