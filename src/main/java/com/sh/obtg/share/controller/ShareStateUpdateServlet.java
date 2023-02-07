package com.sh.obtg.share.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.share.model.service.ShareService;

/**
 * Servlet implementation class ShareStateUpdateServlet
 */
@WebServlet("/share/shareStateUpdate")
public class ShareStateUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShareService shareService = new ShareService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 사용자입력값
			int boardNo = Integer.parseInt(request.getParameter("shareNo"));
			
			// 업무로직
			int result = shareService.updateShareState(boardNo);
			System.out.println(result > 0 ? "상태 변경 성공" : "상태 변경 실패");
			
			// 리다이렉트
			response.sendRedirect(request.getContextPath() + "/member/memberShareList");
			request.getSession().setAttribute("msg", "거래 상태가 변경되었습니다.");
			
		} catch (Exception e) {
			request.getSession().setAttribute("msg", "거래 상태 변경 실패!");
			response.sendRedirect(request.getContextPath() + "/member/memberShareList");
		}
	}

}
