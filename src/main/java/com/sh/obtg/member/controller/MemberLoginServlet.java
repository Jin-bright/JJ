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

@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	/**
	 * 로그인폼 doGet요청
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response);
	}

	/**
	 * 로그인 doPost요청
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 사용자 입력값
			String memberId = request.getParameter("memberId");
			String memberPwd = HelloMvcUtils.getEncryptedPassword(request.getParameter("memberPwd"), memberId);
			
			// 업무로직
			Member member = memberService.selectOneMember(memberId);
			
			HttpSession session = request.getSession();
			if(member != null && memberPwd.equals(member.getPassword())) {
				session.setAttribute("loginMember", member);
				// 리다이렉트
				response.sendRedirect(request.getContextPath() + "/");
			}
			else {
				session.setAttribute("msg", "아이디가 존재하지 않거나 비밀번호가 틀립니다.");		
				// 리다이렉트
				response.sendRedirect(request.getContextPath() + "/member/login");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
