package com.sh.obtg.column.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.column.model.service.ColumnService;

@WebServlet("/column/columnList")
public class ColumnListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ColumnService columnService = new ColumnService();

	/**
	 * 컬럼 총 페이지수
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int totalCount = columnService.getTotalCount();
			
			// 전체페이지수 구하기
			int limit = 5; // 7개만 나왔으면 좋겠다..?
			int totalPage = (int)Math.ceil((double)totalCount / limit); // 20 / 5 = 총 4페이지다..?
			
			// 리다이렉트
			request.setAttribute("totalPage", totalPage);
			request.getRequestDispatcher("/WEB-INF/views/column/columnList.jsp")
				.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
