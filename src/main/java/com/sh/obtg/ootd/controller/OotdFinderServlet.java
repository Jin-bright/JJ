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
import com.sh.obtg.ootd.model.dto.OotdBoard;
import com.sh.obtg.ootd.model.dto.OotdBoardandAttachment;
import com.sh.obtg.ootd.model.service.OotdBoardService;

/**
 * Servlet implementation class OotdFinderServlet
 */
@WebServlet("/ootd/ootdFindbyIdAj")
public class OotdFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OotdBoardService ootdBoardService = new OotdBoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 여긴 only id 일때만 해당되는거임 !!!!!!!!!!  
// 1. 게시글  select * from ootd_attachment where board_no = ? 
// 2. 상응하는 attachment 
		
		String searchType = request.getParameter("searchType"); // searchType = member_id
		String searchKeyword = request.getParameter("searchKeyword"); //searchKeyword =  tiger  
		
		System.out.println( "searchType = " + searchType );
		System.out.println("searchKeyword = " + searchKeyword );
		
		Map<String, String> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		
		System.out.println("param = " + param);

		//2. 업무로직 - select 기능 
		//2-1. memberid로 찾기 - select * from ootd_board where memberId = ?  
		// 해서 구해온 board no로,,, 
		// select * from ootd_attachment where board_no = ?
//		List<OotdBoard> findootdBoardsById = ootdBoardService.SearchOotdBymemberId( param );
//		System.out.println( findootdBoardsById  );

		// 조인쿼리 도전
		List<OotdBoardandAttachment> ootdboardAndAttachments = ootdBoardService.SearchOotdBymemberId( param );
		System.out.println( "결과 왜안나와 ? " + ootdboardAndAttachments  );
		
		String msgnull = "검색결과가 없습니다 😣";
		
		if(  ootdboardAndAttachments.isEmpty()) {
			System.out.println("검색결과없음");
			request.setAttribute("msgnull", msgnull );
		}
		
		//3. 응답처리 - json 안돼 - 새 jsp
//		request.setAttribute("findootdBoardsById", findootdBoardsById);
		
		request.setAttribute("ootdboardAndAttachments", ootdboardAndAttachments);
		request.getRequestDispatcher("/WEB-INF/views/ootd/ootdFindbyIdAj.jsp")
		.forward(request, response);
			
	}

}
