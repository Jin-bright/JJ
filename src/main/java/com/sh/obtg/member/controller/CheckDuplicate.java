package com.sh.obtg.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.member.model.service.MemberService;

@WebServlet("/member/checkDuplicate")
public class CheckDuplicate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * 아이디, 이메일 중복 검사
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String memberId = request.getParameter("memberId");
			System.out.println("memberId : " + memberId);
			String email = request.getParameter("email");
			System.out.println("email : " + email);
			
			int checkNum = 0;
			if(memberId != null) {
				checkNum = memberService.checkDuplicate("member_id", memberId);
			}
			else if(email != null) {
				checkNum = memberService.checkDuplicate("email", email);
			}
			
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(checkNum, response.getWriter());		
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}