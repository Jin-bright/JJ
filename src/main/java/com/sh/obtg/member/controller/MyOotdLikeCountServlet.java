package com.sh.obtg.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.member.model.service.MemberService;

@WebServlet("/member/ootdLikeCnt")
public class MyOotdLikeCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * 현재 ootd 좋아요 수 조회
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 사용자 입력값
			HttpSession session = request.getSession();
			Member logiMember = (Member)session.getAttribute("loginMember");
			String memberId = logiMember.getMemberId();
			
			// 업무로직
			int myOotdLikeCount = memberService.myOotdLikeCount(memberId);
			
			// 총 페이지수
			int limit = 6; 
			int myLikeTotalPage = (int)Math.ceil((double)myOotdLikeCount / limit);
			
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(myLikeTotalPage, response.getWriter());	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
