package com.sh.obtg.column.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sh.obtg.column.model.dto.Column;
import com.sh.obtg.column.model.service.ColumnService;

/**
 * Servlet implementation class ColumnMorePageServlet
 */
@WebServlet("/column/morePage")
public class ColumnMorePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ColumnService columnService = new ColumnService();

	/**
	 * 컬럼 페이지 추가
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자입력값 처리
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = 5;
				
		// 1 : 1 ~ 5, 2: 6 ~ 10, 3 : 11 ~ 15, ...
		int start = (page - 1) * limit + 1;
		int end = page * limit;
		Map<String, Integer> param = new HashMap<>();
		param.put("start", start);
		param.put("end", end);
				
		// 업무로직
		List<Column> columnList = columnService.selectColumnList(param);
		// System.out.println("columnList = " + columnList);
				
		// 응답처리 - json
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(columnList, response.getWriter());		
	}

}
