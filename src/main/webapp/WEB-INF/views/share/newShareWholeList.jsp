<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>        
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ taglib uri ="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shareWholeList.css" /> 
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Noto+Sans+KR:wght@900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<div id="board-container">
	<br /><br />
	<h2 id = "shareboardlist" > SHARE  </h2>
<!-- 	<input type="button" value="글쓰기" /> -->
	<input type="button" value="글쓰기" id="btnAdd"  onclick="location.href='<%=request.getContextPath()%>/share/shareEnroll';"
	 <%-- onclick="location.href='${pageContext.request.contextPath}/share/newShareEnroll';" --%>/> 
		
	<%-- get&post다있는데/ 로그인한 상태에서만 노출 되게 수정해야됨 --%> 
</div>
<section>
<div class="sectiondivs" id="filters">

</div >

<div class="sectiondivs"  id="sharelists">

</div>



</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />