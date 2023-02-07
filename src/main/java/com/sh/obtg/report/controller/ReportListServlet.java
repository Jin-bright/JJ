package com.sh.obtg.report.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.common.HelloMvcUtils;
import com.sh.obtg.report.model.dto.Report;
import com.sh.obtg.report.model.service.ReportService;

/**
 * Servlet implementation class ReportListServlet
 */
@WebServlet("/report/reportList")
public class ReportListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReportService reportService = new ReportService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자입력값
		final int limit = 10;
		int page = 1; // 기본값
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {}
		
		Map<String, Object> param = new HashMap<>();
		param.put("page", page);
		param.put("limit", limit);
		System.out.println("param = " + param);
				
		// 업무로직
		List<Report> reports = reportService.selectReportList(param);
		System.out.println(reports);
		
		int totalCount = reportService.selectTotalCount();
		System.out.println("신고 총 개수 : " + totalCount);
		
		String url = request.getRequestURI();
		String pagebar = HelloMvcUtils.getPagebar(page, limit, totalCount, url);
		
		request.setAttribute("reports", reports);
		request.setAttribute("pagebar", pagebar);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/reportList.jsp")
			.forward(request, response);
	}

}
