<%@page import="com.sh.obtg.member.model.dto.MyPosts"%>
<%@page import="com.sh.obtg.share.model.dto.ShareBoard"%>
<%@page import="com.sh.obtg.ootd.model.dto.OotdBoard"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<MyPosts> shareBoardList = (List<MyPosts>)request.getAttribute("shareBoardList");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/memberView.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/memberBoardList.css" />
<div class="main">
	<div class="container-menu">
		<ul>
			<div class="container-li">
				<a href="<%= request.getContextPath()%>/member/memberView">
					<img src="<%= request.getContextPath()%>/image/modify.png" alt="" />
					<li>&nbsp;Info</li>
				</a>
			</div>
			<div class="container-li">
				<a href="<%= request.getContextPath() %>/member/memberBoardList">
					<img src="<%= request.getContextPath()%>/image/list.png" alt="" />
					<li>&nbsp;List</li>
				</a>
			</div>
			<div class="container-li">
				<a href="<%= request.getContextPath() %>/message/messageList">
					<img src="<%= request.getContextPath()%>/image/chat.png" alt="" />
					<li>&nbsp;Message</li>
				</a>
			</div>
			<div class="container-li">
				<a href="<%= request.getContextPath() %>/member/memberOotdLike">
					<img src="<%= request.getContextPath()%>/image/like.png" alt="" />
					<li>&nbsp;Like</li>
				</a>
			</div>
		</ul>
	</div>
	<hr />
	<section id="board-container">
		<div id="nav-container"> 
			<h6 style="font-size: 13px; color: darkgray;">내가 작성한 글을 확인해보세요!</h6>
			<span><a href="<%= request.getContextPath() %>/member/memberOotdList">OOTD</a></span>
			<span style="font-weight: 900"><a href="<%= request.getContextPath() %>/member/memberShareList">SHARE</a></span>
		</div>
		<table id="boardList-wrap">
		<% 
			if(shareBoardList.size() > 0) {
				for(int i = 0; i < shareBoardList.size(); i++) {
					if(i % 4 == 0 ){
		%>
			<tr>
				<% } %>
				<td>
					<a href="<%= request.getContextPath() %>/share/shareView?no=<%= shareBoardList.get(i).getNo() %>">
						<img src="<%= request.getContextPath() %>/uploadshares/share/<%= shareBoardList.get(i).getRenamedFilename() %>" alt="" />
						<p id="b_title"><%= shareBoardList.get(i).getTitle() %></p>
						<p id="b_txt"><%= shareBoardList.get(i).getRegDate() %> | 조회수 : <%= shareBoardList.get(i).getReadCount() %></p>
					</a>
					<button id="sBtn" data-board-no="<%= shareBoardList.get(i).getNo() %>" value="<%= shareBoardList.get(i).getState() %>"><%= shareBoardList.get(i).getState() %></button>
				</td>
				<% if(i % 4 == 3){%>
			</tr>
		<% 
					}   	    
				}
			} else {
		%> 
			<div id="empty-box">
				<h2>앗 아직 <span style="color: purple;">share</span>게시판에 글을 작성하지 않았어요!🥲</h2>
				<p><a href="<%=request.getContextPath()%>/share/shareWholeList">SHARE 게시판으로 이동</a></p>
			</div>
		<% } %>
		</table>
	</section>
</div>
<form action="<%= request.getContextPath() %>/share/shareStateUpdate"
	  method="post"
	  name="stateUpdateFrm">
	<input type="text" name="shareNo" id="shareNo" style="display: none;"/>
</form>
<script>
document.querySelectorAll("#sBtn").forEach((state) =>{
	state.onclick = (e) => {
		console.log(e.target);
		if(e.target.value == "거래완료"){
			alert("거래가 완료된 게시글입니다🥲")	
			return;
		} else if(confirm("거래 상태를 변경하시겠습니까?")){
			const shareNo = document.querySelector("#shareNo");
			shareNo.value = e.target.dataset.boardNo;
			document.stateUpdateFrm.submit();
		}
	};	
});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>