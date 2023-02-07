package com.sh.obtg.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.obtg.common.HelloMvcUtils;
import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.member.model.service.MemberService;
/**
 * Servlet implementation class MemberUpdatePasswordServlet
 */
@WebServlet("/member/updatePassword")
public class MemberUpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/updatePassword.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try {
			Member loginMember = (Member) session.getAttribute("loginMember");
			String location = request.getContextPath();
			String msg = null;
			
			String memberId = loginMember.getMemberId();
			String oldPassword = HelloMvcUtils.getEncryptedPassword(request.getParameter("oldPassword"), memberId);
			String newPassword = HelloMvcUtils.getEncryptedPassword(request.getParameter("newPassword"), memberId);
			
			boolean pass = oldPassword.equals(loginMember.getPassword());
			
			if(pass) {
				loginMember.setPassword(newPassword);
				int result = memberService.updatePassword(loginMember);
				msg = "비밀번호를 성공적으로 변경했습니다.";
				location += "/member/memberView";
			}else {
				msg = "비밀번호가 일치하지 않습니다.";
				location+="/member/updatePassword";
			}
			
			session.setAttribute("msg", msg);
			response.sendRedirect(location);
			
			
		}catch(Exception e) {
			session.setAttribute("msg", "비밀번호 변경 처리 도중 오류가 발생했습니다.");
			e.printStackTrace();
		}
	}

}
