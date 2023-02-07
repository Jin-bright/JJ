package com.sh.obtg.faq.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.common.HelloMvcUtils;
import com.sh.obtg.faq.model.dto.faq;
import com.sh.obtg.faq.model.service.FaqService;


@WebServlet("/faq/faqList")
public class FaqListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FaqService faqService = new FaqService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자 입력값 
	
	try {
		final int limit = 5;
		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));

		} catch (NumberFormatException e) {
			
		}

		Map<String, Object> param = new HashMap<>();
		param.put("page", page);
		param.put("limit", limit);
		
		System.out.println( param );
		// 업무로직
		List<faq> faqList = faqService.selectFaqList(param);
		System.out.println( faqList ); //
		
		int totalCount = faqService.selectTotalCount();
		System.out.println( "** faq : " + totalCount   ); //
		
		String url = request.getRequestURI();
		String pagebar = HelloMvcUtils.getPagebar(page, limit, totalCount, url);
		
		// view단 처리
		request.setAttribute("faqList", faqList);
		request.setAttribute("pagebar", pagebar);


		
		request.getRequestDispatcher("/WEB-INF/views/faq/faqList.jsp")
		.forward(request, response);
				
		}  catch (Exception e) {
			e.printStackTrace();		

//		request.getRequestDispatcher("/WEB-INF/views/faq/faqList.jsp")
//		.forward(request, response);

		}

	}
}
