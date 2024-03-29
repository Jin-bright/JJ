package com.sh.obtg.member.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.member.model.service.MemberService;

@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	/**
	 * 회원 탈퇴
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 사용자 입력값
			String memberId = request.getParameter("memberId");
			System.out.println("memberId : " + memberId);
			
			// 업무로직
			int result = memberService.deleteMember(memberId);
			
			// 모든 속성 제거하기
			HttpSession session = request.getSession();
			Enumeration<String> names = session.getAttributeNames();
			while(names.hasMoreElements()) {
				String name = names.nextElement();
				session.removeAttribute(name);
			}

			// 리다이렉트
			session.setAttribute("msg", "회원을 성공적으로 삭제했습니다.");
			response.sendRedirect(request.getContextPath() + "/");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
