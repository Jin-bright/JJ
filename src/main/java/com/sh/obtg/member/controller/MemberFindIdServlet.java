package com.sh.obtg.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sh.obtg.member.model.service.MemberService;

@WebServlet("/member/findId")
public class MemberFindIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * 아이디찾기폼 doGet요청
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email = request.getParameter("email");
			System.out.println("email 값은? " + email);
			
			if(email != null) {
				// 업무로직 
				String memberId = memberService.findMemebrId(email);
				System.out.println("아이디 : " + memberId);
				
				// 아이디 일부만 보여주기
				if(memberId != null) {
					for(int i = 3; i < memberId.length(); i++) {
						memberId = memberId.replace(memberId.charAt(i), '*');
					}
				}
				
				response.setContentType("application/json; charset=utf-8");
				new Gson().toJson(memberId, response.getWriter());					
			}
			else {
				request.getRequestDispatcher("/WEB-INF/views/member/findId.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
