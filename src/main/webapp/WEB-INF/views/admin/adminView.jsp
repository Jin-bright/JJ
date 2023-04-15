<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <link rel="stylesheet" href="normalize.css" charset="utf-8" />
  <link rel="stylesheet" href="admin--test.css" charset="utf-8" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/adminView.css"/> 
  <body class="admin">
    <nav class="admin__sidepanel">
      <ul>
        <li>
          <i class="fa fa-file-text-o"></i><a href="<%= request.getContextPath() %>/admin/memberList">회원 관리</a>
        </li>
        <li>
          <i class="fa fa-plus-square-o"></i><a href="<%= request.getContextPath() %>/report/reportList">신고 내역</a> 
        </li>
      </ul>
    </nav>
  </body>
</html>