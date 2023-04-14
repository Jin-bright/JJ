package com.sh.obtg.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.member.model.service.MemberService;

@WebServlet("/member/myLikeMorePage")
public class MyOotdLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * 마이페이지 ootd 좋아요 목록 조회
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 사용자정보
			HttpSession session = request.getSession();
			Member logiMember = (Member)session.getAttribute("loginMember");
			String memberId = logiMember.getMemberId();
			
			// 사용자입력값 처리
			int page = Integer.parseInt(request.getParameter("page"));
			int limit = 6;
			
			// 한페이지의 게시물 수
			int start = (page - 1) * limit + 1;
			int end = page * limit;
			Map<String, Object> param = new HashMap<>();
			param.put("start", start);
			param.put("end", end);
			param.put("memberId", memberId);
					
			// 업무로직
			List<Map<String, Object>> likeList = memberService.selectOotdLike(param);
			System.out.println("likeList" + likeList);
					
			// 응답처리 - json
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(likeList, response.getWriter());	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
