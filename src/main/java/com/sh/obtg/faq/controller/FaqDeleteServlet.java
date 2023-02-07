package com.sh.obtg.faq.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.faq.model.service.FaqService;


@WebServlet("/faq/faqDelete")
public class FaqDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FaqService faqService = new FaqService();

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자 입력값 
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 업무 로직
		int result = 0;
		result = faqService.deleteFaq(no);
		if(result > 0) {
			System.out.println("게시글 삭제 처리 성공!");
		} else {
			System.out.println("게시글 삭제 처리 실패!");
		}
		
		// view단 처리
		response.sendRedirect(request.getContextPath() + "/faq/faqList");
	}

}
