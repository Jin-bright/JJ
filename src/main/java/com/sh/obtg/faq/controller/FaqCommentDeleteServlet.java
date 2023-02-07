package com.sh.obtg.faq.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.faq.model.service.FaqService;


@WebServlet("/faq/faqCommentDelete")
public class FaqCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private FaqService faqService = new FaqService();
    


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int faqNo = Integer.parseInt(request.getParameter("faqNo")); // 게시글 pk
		int no = Integer.parseInt(request.getParameter("no")); // 댓글 pk
		
		try {
			// 1. 파라미터값 가져오기
//			System.out.println("faqNo=" + faqNo + ", no=" + no);
			// 2. 비지니스로직 호출
			int result = faqService.deleteFaqComment(no);
			// 3. 리다이렉트
			request.getSession().setAttribute("msg", "댓글 삭제 성공!");
			response.sendRedirect(request.getContextPath() + "/faq/faqView?no=" + faqNo);
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("msg", "댓글 삭제 실패!");
			response.sendRedirect( request.getContextPath()+"/faq/faqView?no=" +  faqNo);
		}

	}

}
