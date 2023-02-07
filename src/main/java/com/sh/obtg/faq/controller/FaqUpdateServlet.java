package com.sh.obtg.faq.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.faq.model.dto.faq;
import com.sh.obtg.faq.model.service.FaqService;


@WebServlet("/faq/faqUpdate")
public class FaqUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FaqService faqService = new FaqService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 입력값 처리
		int FaqNo = Integer.parseInt(request.getParameter("no"));
		
		// 업무 로직 및 view단 처리
		faq faq = faqService.selectOneFaq(FaqNo);
		
		request.setAttribute("faq", faq);
		request.getRequestDispatcher("/WEB-INF/views/faq/faqUpdate.jsp")
		.forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 입력값 처리
		int FaqNo = Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 업무 로직
		faq faq = new faq();
		faq.setTitle(title);
		faq.setContent(content);
		faq.setNo(FaqNo);
		
		int result = 0;
		result = faqService.updateFaq(faq);
		
		if(result > 0) {
			System.out.println("게시물 수정 완료");
		} else {
			System.out.println("게시물 수정 실패");
		}
		
		// 리다이렉트
		response.sendRedirect(request.getContextPath() + "/faq/faqView?no=" + faq.getNo());
	}

}
