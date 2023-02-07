package com.sh.obtg.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.obtg.member.model.dto.Like;
import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.member.model.service.MemberService;

/**
 * Servlet implementation class MemberShareLikeServlet
 */
@WebServlet("/member/memberShareLike")
public class MemberShareLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Member loginMember = (Member)session.getAttribute("loginMember");
			String memberId = loginMember.getMemberId();
			
			// 업무로직
			List<Like> shareLikes = memberService.selectShareLike(memberId);
			System.out.println(shareLikes.toString());
			
			// 응답처리
			request.setAttribute("shareLikes", shareLikes);
			request.getRequestDispatcher("/WEB-INF/views/member/memberShareLike.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
