package com.sh.obtg.profile.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.member.model.service.MemberService;

/**
 * Servlet implementation class ProfileViewServlet
 */
@WebServlet("/profile/profileView")
public class ProfileViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberID");
		//System.out.println(" 나오니  " + memberId );
		
		Member member = memberService.selectOneMember(memberId);
		//System.out.println(member );
		
		request.setAttribute("member", member);
	
		request.getRequestDispatcher("/WEB-INF/views/profile/profileView.jsp")
		.forward(request, response);
	}

}
