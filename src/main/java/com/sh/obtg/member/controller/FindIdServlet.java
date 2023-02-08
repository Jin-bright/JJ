package com.sh.obtg.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sh.obtg.member.model.service.MemberService;

@WebServlet("/member/find_Id")
public class FindIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * 아이디찾기결과
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 사용자입력값
			String email = request.getParameter("email");
			System.out.println("email 값은? " + email);
			
			// 업무로직 
			String memberId = memberService.findMemebrId(email);
			System.out.println("아이디 : " + memberId);
			
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(memberId, response.getWriter());		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
