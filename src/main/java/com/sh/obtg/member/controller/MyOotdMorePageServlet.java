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
import com.google.gson.JsonIOException;
import com.sh.obtg.column.model.dto.Column;
import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.member.model.service.MemberService;

@WebServlet("/member/myOotdMorePage")
public class MyOotdMorePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * 마이페이지 ootd 게시글 | 좋아요 더보기
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
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
			List<Map<String, Object>> ootdList = memberService.selectMyOotdList(param);
			System.out.println("ootdList" + ootdList);
					
			// 응답처리 - json
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(ootdList, response.getWriter());

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}	
		
	}

}
