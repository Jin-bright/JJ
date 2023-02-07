package com.sh.obtg.share.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.share.model.service.ShareService;

/**
 * Servlet implementation class ShareLikeServlet
 */
@WebServlet("/share/shareLike")
public class ShareLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShareService shareService = new ShareService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		// 1. 사용자 입력값
		String memberId = loginMember.getMemberId();
//		System.out.println("로그인 아이디 = " + memberId);
		int no = Integer.parseInt(request.getParameter("no"));
//		System.out.println("게시판 번호 = " + no);
		
		Map<String, Object> param = new HashMap<>();
		param.put("memberId", memberId);
		param.put("boardNo", no);
		
		int likeCheck = shareService.selectShareLike(param);
		int like = 0;
		
		// 2. 업무로직 (insert)
		if(likeCheck == 0) {
			int result = shareService.insertShareLike(param);
			System.out.println("좋아요 입력 완료!");
			
			++like;
		}
		else {
			int result = shareService.deleteShareLike(no);
			System.out.println("좋아요 입력 취소 완료!");
		}
		
		// 3. 응답처리 : Map<String, Object> 처리결과, insert된 celeb객체 반환
		new Gson().toJson(like, response.getWriter());
	}

}
