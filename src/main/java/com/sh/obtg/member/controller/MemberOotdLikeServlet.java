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
 * Servlet implementation class MemberLikeServlet
 */
@WebServlet("/member/memberOotdLike")
public class MemberOotdLikeServlet extends HttpServlet {
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
//			
//			int totalCount = memberService.getMyOotdPostCnt(memberId);
//			System.out.println("totalCount = " + totalCount);
//			
//			// 전체페이지수 구하기
//			int limit = 8;
//			int totalPage = (int)Math.ceil((double)totalCount / limit);
//			System.out.println("totalPage = " + totalPage);
			
			// 업무로직
			List<Like> ootdLikes = memberService.selectOotdLike(memberId);
			
			// 응답처리
//			request.setAttribute("totalPage", totalPage);
			request.setAttribute("ootdLikes", ootdLikes);
			request.getRequestDispatcher("/WEB-INF/views/member/memberOotdLike.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
