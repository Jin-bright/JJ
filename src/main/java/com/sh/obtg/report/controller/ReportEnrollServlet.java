package com.sh.obtg.report.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.report.model.dto.Reason;
import com.sh.obtg.report.model.dto.Report;
import com.sh.obtg.report.model.service.ReportService;

@WebServlet("/report/reportEnroll")
public class ReportEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReportService reportService = new ReportService();

	/**
	 * 신고내역 등록
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 사용자 입력값
			String memberId = request.getParameter("reportedMemberId");
			
			// ootd게시판인지 share 게시판인지 구분용
			String no = request.getParameter("boardNo");
			System.out.println("신고가 접수된 번호 = " + no);
			
			String _reason = request.getParameterValues("reason")[0];
			Reason reason = null;
			
			System.out.println("id = " + memberId 
					  +" no = " + no 
					  +" reason = " + _reason);
			
			switch (_reason) {
			case "R1":
				reason = Reason.R1;
				break;
			case "R2":
				reason = Reason.R2;
				break;
			case "R3":
				reason = Reason.R3;
				break;
			case "R4":
				reason = Reason.R4;
				break;
			case "R5":
				reason = Reason.R5;
				break;
			}

			Report report = new Report();
			report.setReportedUserId(memberId);
			report.setBoardNo(no);
			report.setReportReason(reason);
			
			// 업무로직 insert
			int result = reportService.insertReport(report);
			
			// view단 처리
			String board = no.substring(0, 1);
			System.out.println("어느 게시판일까?(O - ootd, S - share) " + board);
			
			int num = Integer.parseInt(no.substring(1));
			
			// 게시판에 따라 리다이렉트
			if(board.equals("O")) {
				response.sendRedirect(request.getContextPath() + "/ootd/newOotdView?no=" + num);				
			}
			else if(board.equals("S")) {
				response.sendRedirect(request.getContextPath() + "/share/newShareView?no=" + num);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}	
	}

}
