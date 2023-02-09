package com.sh.obtg.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/findPassword")
public class MemberFindPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 비밀번호 찾기 폼 doGet요청 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/findPassword.jsp").forward(request, response);
	}

}
