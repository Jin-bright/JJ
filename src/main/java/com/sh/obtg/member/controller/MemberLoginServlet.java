package com.sh.obtg.member.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.obtg.common.HelloMvcUtils;
import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.member.model.service.MemberService;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberId");
		String password = HelloMvcUtils.getEncryptedPassword(request.getParameter("password"), memberId);
		String saveId = request.getParameter("saveId");
		
		System.out.println("memberId = "+ memberId);
		System.out.println("password = "+ password);
		System.out.println("saveId = "+ saveId);
		
		Member member = memberService.selectOneMember(memberId);
		System.out.println("member = " + member);
		
		HttpSession session = request.getSession();
		
		System.out.println("id = " + session.getId());
		System.out.println("유효시간 = " + session.getMaxInactiveInterval());
		System.out.println("생성시각 = " + new Date(session.getCreationTime()));
		System.out.println("마지막접속시각 = " + new Date(session.getLastAccessedTime()));
		
		if(member != null && password.equals(member.getPassword())) {
			session.setAttribute("loginMember", member);
			
		Cookie cookie = new Cookie("saveId", memberId);
		cookie.setPath(request.getContextPath());
		if(saveId != null) {
			cookie.setMaxAge(60*60*24*7);
		}
		else {
			cookie.setMaxAge(0);
		}
		response.addCookie(cookie);
		}
		else {
			session.setAttribute("msg", "아이디가 존재하지 않거나 비밀번호가 틀립니다.");		
		}
		String referer = request.getHeader("Referer");
		System.out.println("referer = " + referer);
		response.sendRedirect(request.getContextPath() + "/");
		}
		
	}
