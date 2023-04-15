package com.sh.obtg.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.member.model.service.MemberService;

@WebServlet("/member/myOotd")
public class MyOotdListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	/**
	 * 마이페이지 나의 ootd 게시물 목록 조회
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 사용자 입력값
			HttpSession session = request.getSession();
			Member logiMember = (Member)session.getAttribute("loginMember");
			String memberId = logiMember.getMemberId();
			
			// 업무로직
			int myOotdTotalCount = memberService.myOotdTotalCount(memberId);
			int myOotdLikeCount = memberService.myOotdLikeCount(memberId);
			
			// 내가 쓴 ootd 전체페이지수 구하기 | 좋아요한 ootd 전체페이지 수 구하기
			int limit = 6; 
			int myOotdTotalPage = (int)Math.ceil((double)myOotdTotalCount / limit);
			int myLikeTotalPage = (int)Math.ceil((double)myOotdLikeCount / limit);
			
			// view 전달
			request.setAttribute("myOotdTotalCount", myOotdTotalCount);
			request.setAttribute("myOotdLikeCount", myOotdLikeCount);
			request.setAttribute("myOotdTotalPage", myOotdTotalPage);
			request.setAttribute("myLikeTotalPage", myLikeTotalPage);
			request.getRequestDispatcher("/WEB-INF/views/member/myOotdList.jsp")
				.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
