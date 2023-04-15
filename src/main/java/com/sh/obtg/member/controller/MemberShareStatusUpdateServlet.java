package com.sh.obtg.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.member.model.service.MemberService;

@WebServlet("/member/shareStatusUpdate")
public class MemberShareStatusUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * 마이페이지 나눔 물품 거래완료로 상태변경
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 사용자 입력값 
			int no = Integer.parseInt(request.getParameter("no"));
			
			// 업무로직
			int result = memberService.updateShareStatus(no);
			System.out.println(result > 0 ? "상태변경성공" : "상태변경실패");

			// 리다이렉트
			String referer = request.getHeader("Referer");
			response.sendRedirect(referer);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
