<%@page import="com.sh.obtg.report.model.dto.Status"%>
<%@page import="com.sh.obtg.report.model.dto.Report"%>
<%@page import="java.util.List"%>
<%@ page import="com.sh.obtg.member.model.dto.Member" %>
<%@ page import="com.sh.obtg.member.model.dto.MemberRole" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%
	List<Report> reports = (List<Report>)request.getAttribute("reports");
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/adminView.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/reportList.css" />
<section id="report-container">
	<br /><br /><br /><br />
	<h1 style="font-size: 30px;">신고관리</h1>
	<p style="font-size: 13px;">게시번호를 클릭하여 이동해 보세요!</p>
	<br /><br /><br />
	<table id="report_wrap">
		<thead>
			<tr>
				<th>No</th>
				<th>신고자</th>
				<th>게시번호</th>
				<th>신고사유</th>
				<th>신고날짜</th>
				<th>처리상태</th>
			</tr>
		</thead>
		<tbody>
			<% if(reports != null){
				for(Report report : reports) {
			%>
			<tr>
				<td><%= report.getReportNo() %></td>
				<td><%= report.getReportedUserId() %></td>
				<% 
					String no = report.getBoardNo();
					String board = no.substring(0, 1);
					int num = Integer.parseInt(no.substring(1));
				%>
				<td>
				<% if(board.equals("O")) { %>
					<a href="<%= request.getContextPath() %>/ootd/ootdView?no=<%= num %>"  class="reportNo"><%= report.getBoardNo() %></a>
				<% } else { %>
					<a href="<%= request.getContextPath() %>/share/shareView?no=<%= num %>" class="reportNo"><%= report.getBoardNo() %></a>
				<% } %>
				</td>
				<%
				String reason = null;
				switch(report.getReportReason()){
				case R1:
					reason = "스팸홍보/도배글입니다.";
					break;
				case R2:
					reason = "불법정보를 포함하고있습니다.";
					break;
				case R3:
					reason = "욕설/생명경시/혐오/차별적 표현입니다.";
					break;
				case R4:
					reason = "개인정보 노출 게시물입니다.";
					break;
				case R5:
					reason = "불쾌한 표현이 있습니다.";
					break;
				}
				%>
				<td><%= reason %></td>
				<td><%= report.getRegDate() %></td>
				<td>
					<button id="update" data-receiver = <%= report.getReportedUserId() %> 
										data-reason = <%= reason %> 
										data-board-no = <%= report.getBoardNo() %>
										data-report-no = <%= report.getReportNo() %>
										value="<%= report.getReportStatus() %>">
						<%= report.getReportStatus() %>
					</button>
				</td>
			</tr>
			<% 	} 
			   }else{
			%>
			<tr>
				<td colspan="6">조회된 신고내역이 없습니다.</td>
			</tr>
			<% } %>
		</tbody>
	</table>

<div id="pagebar"  style="margin-top : 390px; cursor: pointer;">
	<%= request.getAttribute("pagebar") %>
</div>
</section>
<div id="reportUpdate" >
	<form action="<%= request.getContextPath() %>/report/reportUpdate"
		name="reportUpdatFrm"
		method="post">
		<span class="close-button" id="cBtn" onclick="closeFrm()">&times;</span>
		<h2>신고 처리</h2>
		<table id="report-wrap">
			<tr>
				<th>수신자</th>
				<td><input type="text" name="receiver" id="receiver" readonly="readonly"/></td>
			</tr>
			<tr>
				<th>게시글번호</th>
				<td><input type="text" name="boardNo" id="boardNo" readonly="readonly"/></td>
			</tr>
			<tr>
				<th>신고번호</th>
				<td><input type="text" name="reportNo" id="reportNo" readonly="readonly"/></td>
			</tr>
			<tr>
				<th>신고사유</th>
				<td><input type="text" name="reason" id="reason" readonly="readonly"/></td>
			</tr>
			<tr>
				<th>처리내용</th>
				<td><input type="text" name="content" id="content" required; style="border-bottom-style: solid;"/></td>
			</tr>
		</table>
	</form>
	<div style="text-align: center;">
		<input type="button" id="rBtn" value="처리하기" onclick="updateReportFrm();">
	</div>
	
</div>
<script>
document.querySelectorAll("#update").forEach((btn) =>{
	btn.onclick = (e) => {
		if(e.target.value === "<%= Status.O %>"){
			alert("이미 처리된 신고입니다.");
			return;
		}
		
		const div = document.querySelector("#reportUpdate");
		const boardNo = document.querySelector("#boardNo");
		const reportNo = document.querySelector("#reportNo");
		const reason = document.querySelector("#reason");
		const receiver = document.querySelector("#receiver");
		const content = document.querySelector("#content");
		
		div.style.visibility = "visible";
		content.select();

		boardNo.value = e.target.dataset.boardNo;
		reportNo.value = e.target.dataset.reportNo;
		reason.value = e.target.dataset.reason;
		receiver.value = e.target.dataset.receiver;
		
	}
});

const closeFrm = () => {
	const div = document.querySelector("#reportUpdate");
	div.style.visibility = "hidden";
}

const updateReportFrm = () => {
	if(confirm("해당 신고를 처리하시겠습니까?")){
		document.reportUpdatFrm.submit();
	}
}
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
