<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myPage.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css" />
<!-- 글꼴 'Noto Sans Korean' -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<section class="container">
<jsp:include page="/WEB-INF/views/member/subMenu.jsp" />
	<div class="content_area">
		<div class="ootd_wrap">
			<h3>나의 OOTD</h3>
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
			<div class="ootd_select">
				<div>
					<p class="board">게시물&nbsp;<span class="my_count">${myOotdTotalCount}</span></p>
				</div>
				<div>
					<p class="like">좋아요&nbsp;<span class="my_count like_count">${myOotdLikeCount}</span></p>
				</div>
			</div>
			<div class="my_ootd_section">
				<c:if test="${myOotdTotalPage > 0}">
					<div class="my_ootd_content">
						<div class="my-ootd-container"></div>
						<div id='btn-more-container' style="text-align:center; margin-bottom: 1em;">
							<button id="btn-post-more" value="">MORE OOTD <br/> (<span id="post-page"></span>/<span id="post-totalpage">${myOotdTotalPage}</span>)</button>
						</div>
					</div>
				</c:if>
				<c:if test="${myOotdTotalPage == 0}">
					<div class="empty_box">
						<p>작성한 OOTD가 없습니다.</p>
						<button class="my_btn" id="ootd_btn">
							<a href="${pageContext.request.contextPath}/ootd/newOotdWholeList">OOTD 바로가기</a>
						</button>
					</div>
				</c:if>
			</div>
			<div class="my_like_section">
				<c:if test="${myLikeTotalPage > 0}">
					<div class="my_like_content">
						<div class="my_like_container"></div>
						<div id='btn-more-container' style="text-align:center; margin-bottom: 1em;">
							<button id="btn-like-more" value="">MORE OOTD <br/> (<span id="like-page"></span>/<span id="like-totalpage">${myLikeTotalPage}</span>)</button>
						</div>
					</div>
				</c:if>
				<c:if test="${myLikeTotalPage == 0}">
					<div class="empty_box">
						<p>추가하신 좋아요가 없습니다.</p>
						<div class="btn_wrap">
							<button class="my_btn" id="ootd_btn">
								<a href="${pageContext.request.contextPath}/ootd/newOotdWholeList">OOTD 바로가기</a>
							</button>
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</section>
<c:if test="${myOotdTotalPage > 0}">
<script>
/* 나의 ootd 로드시 내 게시물 보여주기 */
window.addEventListener('load', (e) => {
	document.querySelector(".like").style.color = "RGB(183, 183, 183)";
	getPostPage(1);
});

/* 게시물 더보기 클릭시 */
document.querySelector("#btn-post-more").addEventListener('click', (e) =>{
	const page = document.querySelector("#post-page");
	getPostPage(Number(page.innerText) + 1);
});

/* 게시물 더보기 */
const getPostPage = (page) => {
	$.ajax({
		url : "${pageContext.request.contextPath}/member/myOotdMorePage",
		data : {page},
		dataType : "json",		
		success(data){
			
			const container = document.querySelector(".my-ootd-container");
			
			// 게시물이 하나라도 있을 경우
			data.forEach((ootd, index) => {
				
				const div1 = document.createElement("div");
				div1.classList.add("my_ootd_box");
				div1.dataset.no = ootd.no;
				
				const div2 = document.createElement("div");
				div2.classList.add("ootd_img_box");
				const a = document.createElement("a");
				a.href = "${pageContext.request.contextPath}/ootd/newOotdView?no=" + ootd.no;
				const img = document.createElement("img");
				img.src = "${pageContext.request.contextPath}/uploadootds/ootd/" + ootd.img;
				
				const div3 = document.createElement("div");
				div3.classList.add("ootd_info_box");
				const span1 = document.createElement("span");
				span1.innerText = "#" + ootd.title
				
				const div4 = document.createElement("div");
				div4.classList.add("heart_box");
				const heart = document.createElement("img");
				// 좋아요 분기
				if(ootd.myLike != 0){
					heart.src = "${pageContext.request.contextPath}/image/heart _over.png"; 
				}
				else {
					heart.src = "${pageContext.request.contextPath}/image/heart.png"; 
				}
				heart.classList.add("heart1");
				heart.dataset.boardNo = ootd.no;
				const span2 = document.createElement("span");
				span2.classList.add("like_cnt");
				span2.innerText = ootd.likeCount
				
				div4.append(heart, span2);
				div3.append(span1, div4);
				a.append(img)
				div2.append(a);
				div1.append(div2, div3);
				
				container.append(div1);
				
				if(index % 3 == 2) {
					const br = document.createElement("br");
					container.append(br);
				}
			});
			
			/* 좋아요 */
			document.querySelectorAll(".heart1").forEach((like) => {
				like.onclick = (e) => {
					const no = e.target.dataset.boardNo;
					const counts = e.target.nextElementSibling;
					$.ajax({
						url : '${pageContext.request.contextPath}/ootd/OotdLike?no=' + no,
						method: "post",
						dataType: "json",
						success(data){
							if(data === 1) {
								e.target.src="${pageContext.request.contextPath}/image/heart _over.png";
								counts.innerHTML = parseInt(counts.innerHTML) + parseInt(1);
								document.querySelector(".like_count").innerHTML = parseInt(document.querySelector(".like_count").innerHTML) + parseInt(1);
							}
							else{
								e.target.src="${pageContext.request.contextPath}/image/heart.png";
								counts.innerHTML =  parseInt(counts.innerHTML) -  parseInt(1);
								document.querySelector(".like_count").innerHTML = parseInt(document.querySelector(".like_count").innerHTML) - parseInt(1);
							}
						},
					error: console.log
					});
				};
			});
		}, 
		error : console.log,
		complete(){
			const endPage = '${myOotdTotalPage}';
			document.querySelector("#post-page").innerHTML = page;
			
			// 마지막페이지인 경우 더보기버튼 비활성화처리
			if(page == endPage){
				const button = document.querySelector("#btn-post-more");
				button.disabled = true; // 버튼 비활성화
				button.style.cursor = "not-allowed";
			}
		}
	});
};
</script>
</c:if>

<script>
/* ootd 좋아요 */
document.querySelector(".like").addEventListener('click', (e) => {
	
	document.querySelector(".board").style.color = "RGB(183, 183, 183)";
	document.querySelector(".like").style.color = "black";
	
	// 게시물 숨기기
	const myPost = document.querySelector(".my_ootd_section");
	myPost.style.display = "none";
	
	const checkPage = '${myLikeTotalPage}';
	
	document.querySelector(".my_like_section").style.display = "block";
	
	// 좋아요가 있을경우
	if(checkPage > 0){
		document.querySelector(".my_like_container").innerHTML = ''; // 초기화	
		
		// 내 게시글에서 좋아요 수정했을시 대비
		$.ajax({
			url : '${pageContext.request.contextPath}/member/ootdLikeCnt',
			dataType : "json",		
			success(data){
				console.log("좋아요 총 페이지수 : ", data);
				document.querySelector("#like-totalpage").innerHTML = data;
			},
			error : console.log
		});
		
		getLikePage(1);
	}
	
});

/* ootd 게시물 */
document.querySelector(".board").addEventListener('click', (e) => {
	
	document.querySelector(".board").style.color = "black";
	document.querySelector(".like").style.color = "RGB(183, 183, 183)";
	
	// 좋아요 숨기기
	const myLike = document.querySelector(".my_like_section");
	myLike.style.display = "none";
	
	const checkPage = '${myOotdTotalPage}';
	const post = document.querySelector(".my_ootd_section");
	post.style.display = "block";
	
	// 게시물이 있을경우
	if(checkPage > 0){
		document.querySelector(".my-ootd-container").innerHTML = ''; // 초기화		
		getPostPage(1);
	}
	
});
</script>

<c:if test="${myLikeTotalPage > 0}">
<script>
/* 좋아요 더보기 클릭시 */
document.querySelector("#btn-like-more").addEventListener('click', (e) => {
	const page = document.querySelector("#like-page");
	getLikePage(Number(page.innerText) + 1);
});

/* 좋아요 불러오기 */
const getLikePage = (page) => {
	$.ajax({
		url : '${pageContext.request.contextPath}/member/myLikeMorePage',
		data : {page},
		dataType : "json",		
		success(data){
			
			const container = document.querySelector(".my_like_container");
			
			// 게시물이 하나라도 있을 경우
			data.forEach((ootd, index) => {
				
				const div1 = document.createElement("div");
				div1.classList.add("my_ootd_box");
				
				const div2 = document.createElement("div");
				div2.classList.add("ootd_img_box");
				const a = document.createElement("a");
				a.href = "${pageContext.request.contextPath}/ootd/newOotdView?no=" + ootd.no;
				const img = document.createElement("img");
				img.src = "${pageContext.request.contextPath}/uploadootds/ootd/" + ootd.img;
				
				const div3 = document.createElement("div");
				div3.classList.add("ootd_info_box");
				const span1 = document.createElement("span");
				span1.innerText = "#" + ootd.title
				
				const div4 = document.createElement("div");
				div4.classList.add("heart_box");
				const heart = document.createElement("img");
				heart.classList.add("heart2");
				heart.dataset.boardNo = ootd.no;
				heart.src = "${pageContext.request.contextPath}/image/heart _over.png"; 
				const span2 = document.createElement("span");
				span2.innerText = ootd.likeCount
				
				div4.append(heart, span2);
				div3.append(span1, div4);
				a.append(img);
				div2.append(a);
				div1.append(div2, div3);
				
				container.append(div1);
				
				if(index % 3 == 2) {
					const br = document.createElement("br");
					container.append(br);
				}
			});
			
			/* 좋아요 */
			document.querySelectorAll(".heart2").forEach((like) => {
				like.onclick = (e) => {
					const no = e.target.dataset.boardNo;
					const counts = e.target.nextElementSibling;
					$.ajax({
						url : '${pageContext.request.contextPath}/ootd/OotdLike?no=' + no,
						method: "post",
						dataType: "json",
						success(data){
							if(data === 1) {
								e.target.src="${pageContext.request.contextPath}/image/heart _over.png";
								counts.innerHTML = parseInt(counts.innerHTML) + parseInt(1);
								document.querySelector(".like_count").innerHTML = parseInt(document.querySelector(".like_count").innerHTML) + parseInt(1);
							}
							else{
								e.target.src="${pageContext.request.contextPath}/image/heart.png";
								counts.innerHTML =  parseInt(counts.innerHTML) -  parseInt(1);
								document.querySelector(".like_count").innerHTML = parseInt(document.querySelector(".like_count").innerHTML) -  parseInt(1);
							}
						},
					error: console.log
					});
				};
			});
			
		}, 
		error : console.log,
		complete(){
			const endPage = document.querySelector("#like-totalpage").innerText;
			document.querySelector("#like-page").innerHTML = page;
			
			// 마지막페이지인 경우 더보기버튼 비활성화처리
			if(page == endPage){
				const button = document.querySelector("#btn-like-more");
				button.disabled = true; // 버튼 비활성화
				button.style.cursor = "not-allowed";
			}
		}
	});
};
</script>
</c:if>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />