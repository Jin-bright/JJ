package com.sh.obtg.faq.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.faq.model.dto.faq;
import com.sh.obtg.faq.model.service.FaqService;

@WebServlet("/faq/faqEnroll")
public class FaqEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FaqService faqService = new FaqService();

	// 작성폼 요청
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String writer = request.getParameter("writer");
//		request.setAttribute("writer", writer);
		request.getRequestDispatcher("/WEB-INF/views/faq/faqEnroll.jsp")
		.forward(request, response);
	}

	// faqEnroll.jsp에서 글쓰기 버튼 눌렀을때 등록 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	try {
		// 사용자 입력값
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		
		
		// 업무 로직
		faq f = new faq();
		f.setTitle(title);
		f.setWriter(writer);
		f.setContent(content);
		
		int result = 0;
		result = faqService.insertFaq(f);
		if(result > 0) {
			System.out.println("게시글 등록 성공");
		} else {
			System.out.println("게시글 등록 실패");
		}
		
		
		
		// view단 처리

		response.sendRedirect(request.getContextPath() + "/faq/faqList");
		
	} catch (Exception e) {
		e.printStackTrace();
		request.getSession().setAttribute("msg", "게시글 등록중 오류가 발생했습니다.");
		response.sendRedirect(request.getContextPath() + "/faq/faqList");
		}
	}
}
