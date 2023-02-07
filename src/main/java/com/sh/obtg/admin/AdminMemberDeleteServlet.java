package com.sh.obtg.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.member.model.service.MemberService;


@WebServlet("/admin/memberDelete")
public class AdminMemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 입력값
		String memberId = request.getParameter("memberId");
		System.out.println("memberId = " + memberId);
		
		// 2. 업무 로직
		int result = memberService.deleteMemberAD(memberId);
		
		String msg = "";
		if(result > 0)
			msg = "사용자 강제 탈퇴에 성공했습니다.";
		else 
			msg = "사용자 강제 탈퇴에 실패했습니다.";
		
		// 3. view단 처리
		request.setAttribute("msg", msg);
		String referer = request.getHeader("Referer");
		response.sendRedirect(referer);
	}


}
