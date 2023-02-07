package com.sh.obtg.column.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.column.model.dto.Column;
import com.sh.obtg.column.model.service.ColumnService;

/**
 * Servlet implementation class ColumnViewServlet
 */
@WebServlet("/column/columnView")
public class ColumnViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ColumnService columnService = new ColumnService();

	/**
	 * 컬럼 상세 페이지 get요청
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자 입력값
		int no = Integer.parseInt(request.getParameter("no"));
		// System.out.println("no = " + no);
				
		// 쿠키 처리
		String columnCookieVal = "";
		boolean hasRead = false;
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				String name = cookie.getName(); // key
				String value = cookie.getValue();
						
				if("column".equals(name)) {
					columnCookieVal = value;
					if(value.contains("[" + no+ "]" )) {
						hasRead = true;
					}
				}
			}
		}

		// 응답쿠키
		// 현재 게시글 쿠키 추가
		if(!hasRead) {
			Cookie cookie = new Cookie("column", columnCookieVal + "[" + no + "]");
			cookie.setMaxAge(365 * 24 * 60 * 60); // 365일
			cookie.setPath(request.getContextPath() + "/column/columnView");
			response.addCookie(cookie);
		}
		
		// 업무로직 
		Column column = columnService.selectOneColumn(no, hasRead);
		// System.out.println("board = " + column);
		
		request.setAttribute("column", column);
		request.getRequestDispatcher("/WEB-INF/views/column/columnView.jsp")
			.forward(request, response);
	}

}
