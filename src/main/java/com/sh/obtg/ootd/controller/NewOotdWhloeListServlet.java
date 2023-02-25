package com.sh.obtg.ootd.controller;

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
import com.sh.obtg.common.HelloMvcUtils;
import com.sh.obtg.ootd.model.dto.OotdAttachment;
import com.sh.obtg.ootd.model.service.OotdBoardService;


/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/ootd/newOotdWholeList")
public class NewOotdWhloeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OotdBoardService ootdBoardService = new OotdBoardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int totalCount = ootdBoardService.getTotalCount();  // 	1.  dql 전체 게시글 수 구하기  -- int를 반환하는 dql임 (select count(*) from board 
		System.out.println( "■■ totalCount(전체사진갯수) = " + totalCount   );
		
		final int limit = 15;
		int totalPage = (int)Math.ceil( (double)totalCount/ limit );
		System.out.println( "■■ totalPage(전체 페이지 수) = " + totalPage   );
		
		//3. view단응답처리 (연결)
		request.setAttribute("totalPage", totalPage);
		
		request.getRequestDispatcher("/WEB-INF/views/ootd/newOotdWholeList.jsp")
		.forward(request, response); // 
	}

}
