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

@WebServlet("/member/myShare")
public class MyShareListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	/**
	 * 마이페이지 share 목록 doGet요청
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자 입력값 처리
			Member loginMember = (Member)request.getSession().getAttribute("loginMember");
			
			// 2. 업무로직 - shareList 조회
			List<Map<String, Object>> shareList = memberService.selectMyShare(loginMember.getMemberId());
			System.out.println("shareList(MYPAGE) : " + shareList);
			
			request.setAttribute("shareList", shareList);
			
			// 3.jsp 포워딩
			request.getRequestDispatcher("/WEB-INF/views/member/myShareList.jsp")
				.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
