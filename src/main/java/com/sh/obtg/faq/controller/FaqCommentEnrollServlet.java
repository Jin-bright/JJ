package com.sh.obtg.faq.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.faq.model.dto.faqComment;
import com.sh.obtg.faq.model.service.FaqService;


@WebServlet("/faq/faqCommentEnroll")
public class FaqCommentEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FaqService faqService = new FaqService();


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값처리 
		int no = Integer.parseInt(request.getParameter("faqNo"));
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		int commentLevel = Integer.parseInt(request.getParameter("commentLevel"));
		int commentRef = Integer.parseInt(request.getParameter("commentRef"));
//		faqComment fc = new faqComment(0, commentLevel, writer, content, FaqNo, commentRef, null);
		
		faqComment faqComment = new faqComment();
		faqComment.setFaqNo(no);
		faqComment.setCommentLevel(commentLevel);
		faqComment.setWriter(writer);
		faqComment.setContent(content);
		faqComment.setCommentRef(commentRef);
		
		
		// 2. 업무로직 - faq_comment 행추가
		int result = faqService.insertFaqComment(faqComment);
		
		// 3. 리다이렉트 /faq/faqView
		response.sendRedirect(request.getContextPath() + "/faq/faqView?no=" + faqComment.getFaqNo());
	}


}
