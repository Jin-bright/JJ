package com.sh.obtg.ootd.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sh.obtg.ootd.model.dto.OotdBoardandAttachment;
import com.sh.obtg.ootd.model.service.OotdBoardService;

/**
 * Servlet implementation class OotdFinderMorePageServlet
 */
@WebServlet("/ootd/ootdFinderbyStyleAj")
public class OotdFindByStyleMorePageServlet extends HttpServlet {
	//// 페이지 처리 + search type 맵 같이 사용 
	
	private static final long serialVersionUID = 1L;
	private OotdBoardService ootdBoardService = new OotdBoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값 처리 --  페이지  
		try {
			
			String searchKeyword = request.getParameter("searchKeyword"); //searchKeyword =  tiger  
			final int limit = 12;
			

			System.out.println("searchKeyword = " + searchKeyword );
			
			//2. 업무로직 - 한번요청할때마다 5개씩 끊어온다 		
			// 조인 쿼리 - 게시글 		
	//		List<OotdBoardandAttachment> ootdboardAndAttachmentsbyStyle = ootdBoardService.SearchOotdBymemberStyle( paramss );
			List<OotdBoardandAttachment> findootdAll = ootdBoardService.findOotdBoardandAttachment(searchKeyword);
			System.out.println("findootdAll : " + findootdAll );
			
			//3. 응답처리 - json		
			request.setAttribute("searchKeyword", searchKeyword);
			
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(findootdAll, response.getWriter());

		} catch (Exception e) {
			e.printStackTrace();
			throw e; //예외전담페이지로 던짐
			
		}
	}

}
