<%@page import="java.util.List"%>
<%@page import="com.sh.obtg.faq.model.dto.faq"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	faq faq = (faq) request.getAttribute("faq");
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>    
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/board2.css" /> --%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/css.css" />

<section id="board-container">
<!-- <h2>게시판 수정</h2> -->
<form 
	name="boardUpdateFrm" 
	action="<%=request.getContextPath() %>/faq/faqUpdate" 
	method="post">
	<table id="tbl-board">
		    <div class="board_wrap">
        <div class="board_title">
            <strong>INFO 수정</strong>
<!--             <p></p> -->
        </div>
        <div class="board_write_wrap">
            <div class="board_write">
                <div class="title">
                    <dl>
                        <dt>제목</dt>
                        <dd><input type="text" name="title" value="<%= faq.getTitle() %>" ></dd>
                    </dl>
                </div>
                <div class="info">
                    <dl>
                        <dt>작성자</dt>
                        <dd><input type="text" name="writer" value="<%= faq.getWriter() %>" readonly></dd>
                    </dl>
<!--                     <dl>
                        <dt>비밀번호</dt>
                        <dd><input type="password" placeholder="비밀번호 입력" value="1234"></dd>
                    </dl> -->
                </div>
                <div class="cont">
                    <textarea name="content" value="<%= faq.getContent()%>" ></textarea>
                </div>
            </div>
            <div class="bt_wrap">
                <input id="btn-add" type ="submit" value ="수정" class="submit-button">
                <input id="btn-add" type ="submit" value ="취소" onclick="history.go(-1);">
            </div>
        </div>
    </div>
	</table>
	<input type="hidden" name="no" value="<%= faq.getNo() %>" />
</form>
</section>
<script>
document.faqUpdateFrm.onsubmit = (e) => {
	const frm = e.target;
	//제목을 작성하지 않은 경우 폼제출할 수 없음.
	const titleVal = frm.title.value.trim(); // 좌우공백
	if(!/^.+$/.test(titleVal)){
		alert("제목을 작성해주세요.");
		frm.title.select();
		return false;
	}		
					   
	//내용을 작성하지 않은 경우 폼제출할 수 없음.
	const contentVal = frm.content.value.trim();
	if(!/^(.|\n)+$/.test(contentVal)){
		alert("내용을 작성해주세요.");
		frm.content.select();
		return false;
	}
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
