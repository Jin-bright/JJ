package com.sh.obtg.member.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.member.model.service.MemberService;

@WebServlet("/member/myPage")
public class MemberViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * 마이페이지 doGet요청
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Member logiMember = (Member)session.getAttribute("loginMember");
			String memberId = logiMember.getMemberId();
			
			// ootdList 조회
			List<Map<String, Object>> ootdList = memberService.selectMyOotd(memberId);
			System.out.println(ootdList);
			
			// shareList 조회
			List<Map<String, Object>> shareList = memberService.selectMyShare(memberId);
			System.out.println(shareList);
			
			// wishList 조회
			List<Map<String, Object>> wishList = memberService.selectWish(memberId);
			System.out.println(wishList);
			
			// forward
			Member member = memberService.selectOneMember(memberId);
			
			request.setAttribute("loginMember", member);
			request.setAttribute("ootdList", ootdList);
			request.setAttribute("shareList", shareList);
			request.setAttribute("wishList", wishList);
			request.getRequestDispatcher("/WEB-INF/views/member/myPage.jsp")
				.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
