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
import com.sh.obtg.ootd.model.dto.OotdBoardandAttachment;
import com.sh.obtg.ootd.model.service.OotdBoardService;

/**
 스타일 카테고리 비동기 --  쓰는서블렛으로확인046
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
			
			String searchKeyword = request.getParameter("searchKeyword"); //searchKeyword =  style 이름   
			

			
			//페이지구하기 
			int page = 1;
			int limit = 12;
			try {
				page = Integer.parseInt( request.getParameter("page")); //1페이지, 2페이지, 3페이지	
			} catch (NumberFormatException e) {
			}
			
			
			Map<String, Object> param = new HashMap<>();
			param.put("page", page);
			param.put("limit", limit);
			

			System.out.println("searchKeyword = " + searchKeyword ); //S7
			
			//2. 업무로직 - 한번요청할때마다 게시물 최대 12개 
			// 조인 쿼리 - 게시글 		
			List<OotdBoardandAttachment> findootdAll = ootdBoardService.findOotdBoardandAttachment(searchKeyword, param);
			System.out.println("15개 findootdAll : " + findootdAll.size() );
			
			int styletotalPage =   (int)(findootdAll.size()/limit )+1; //총토탈페이지(12개부터 2페이지로 보일거임)
			System.out.println("styletotalPage : " + styletotalPage);
			
			//3. 응답처리 - json		
			Map<String, Object> findootdAllmap = new HashMap<>();
			findootdAllmap.put("findootdAll", findootdAll);
			findootdAllmap.put("styletotalPage", styletotalPage);
			findootdAllmap.put("searchKeyword", searchKeyword);


			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(findootdAllmap, response.getWriter());

		} catch (Exception e) {
			e.printStackTrace();
			throw e; //예외전담페이지로 던짐
			
		}
	}

}
