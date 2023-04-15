package com.sh.obtg.report.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.notification.model.dto.Notification;
import com.sh.obtg.notification.model.service.NotificationService;
import com.sh.obtg.report.model.service.ReportService;

@WebServlet("/report/reportUpdate")
public class ReportUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReportService reportService = new ReportService();
	private NotificationService notificationService = new NotificationService();
	
	/**
	 * 신고 처리 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 리포트 업뎃하기
			// 2. 알림 보내기
			// 사용자 입력값
			String receiver = request.getParameter("receiver");
			String boardNo = request.getParameter("boardNo");
			int reportNo = Integer.parseInt(request.getParameter("reportNo"));
			String reason = request.getParameter("reason");
			String content = request.getParameter("content");
			
			String message = "[" + reason + "]의 이유로 신고한 [" + boardNo + "] 게시물 처리 결과.\n" + content;
			System.out.println(message);
			
			Notification noti = new Notification();
			noti.setReceiver(receiver);
			noti.setMessage(message);
			System.out.println("noti 등록 됨?" + noti);
			
			int notiResult = notificationService.insertNoti(noti);
			System.out.println(notiResult > 0 ? "알림 등록 성공" : "알림 등록 실패");
			
			int reportResult = reportService.updateReport(reportNo);
			System.out.println(reportResult > 0? "신고 내역 업뎃 성공" : "신고 내역 업뎃 실패");
			
			response.sendRedirect(request.getContextPath() + "/report/reportList");
			
		} catch (Exception e) {
			request.getSession().setAttribute("msg", "신고 처리 오류");
			response.sendRedirect(request.getContextPath() + "/report/reportList");
			
		}
		
	}

}