<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>    
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/board2.css" /> --%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/css.css" />
<section id="board-container">

<style>

#btn-add, .submit-button {
	font-size: 16px;
	border: 0;
	outline: 0;
	border-radius: 8px;
	background-color: #525252;
	color: white;
	padding: 8px 16px;
	font-weight: 500;

    margin-right: 0;
	margin-bottom: 8px;
}


</style>

	<form
		name="faqEnrollFrm"
		action="<%=request.getContextPath() %>/faq/faqEnroll" 
		method="post">
		<table id="tbl-board">
		    <div class="board_wrap">
        <div class="board_title">
            <strong>INFO 작성</strong>
            <p>문의사항을 빠르고 정확하게 안내해드립니다.</p>
        </div>
        <div class="board_write_wrap">
            <div class="board_write">
                <div class="title">
                    <dl>
                        <dt>제목</dt>
                        <dd><input type="text" name="title"></dd>
                    </dl>
                </div>
                <div class="info">
                    <dl>
                        <dt>작성자</dt>
                        <dd><input type="text" name="writer" value="<%= loginMember.getMemberId() %>" readonly></dd>
                    </dl>
<!--                     <dl>
                        <dt>비밀번호</dt>
                        <dd><input type="password" placeholder="비밀번호 입력" value="1234"></dd>
                    </dl> -->
                </div>
                <div class="cont">
                    <textarea name="content" placeholder="문의사항을 입력해주세요. 최대한 빠르게 답변해드리겠습니다."></textarea>
                </div>
            </div>
            <div class="bt_wrap">
                <input id="btn-add" type ="submit" value ="등록" class="submit-button">
                <input id="btn-add" type ="button" value ="취소" onclick="history.go(-1);">
            </div>
        </div>
    </div>
	</table>
	</form>
</section>
<script>
/**
* faqEnrollFrm 유효성 검사
*/
document.faqEnrollFrm.onsubmit = (e) => {
	const title = e.target.title;
	const content = e.target.content;
	console.log(title, content);
	
	//제목을 작성하지 않은 경우 폼제출할 수 없음.
	if(!/^.+$/.test(title.value)){
		alert("제목을 작성해주세요.");
		title.select();
		return false;
	}
					   
	//내용을 작성하지 않은 경우 폼제출할 수 없음.
	if(!/^(.|\n)+$/.test(content.value)){
		alert("내용을 작성해주세요.");
		content.select();
		return false;
	}
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
