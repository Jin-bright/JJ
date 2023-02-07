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
			String searchType = request.getParameter("searchType"); // searchType = member_id
			String searchKeyword = request.getParameter("searchKeyword"); //searchKeyword =  tiger  
			
			System.out.println( "searchType = " + searchType );
			System.out.println("searchKeyword = " + searchKeyword );
			
			Map<String, String> paramss = new HashMap<>();
			paramss.put("searchType", searchType);
			
			if ( searchKeyword.contains("러블리") || searchKeyword.contains("사랑")) {
				searchKeyword = "S1";
			}else if(searchKeyword.contains("댄디")){
				searchKeyword = "S2";
			}else if(  searchKeyword.contains("포멀") || searchKeyword.contains("정장") || searchKeyword.contains("단정")) {
				searchKeyword = "S3";
			}else if( searchKeyword.contains("스트릿") || searchKeyword.contains("거리") || searchKeyword.contains("힙")) {
				searchKeyword = "S4";
			}else if(  searchKeyword.contains("걸리쉬") ||  searchKeyword.contains("걸") || searchKeyword.contains("여성") ) {
				searchKeyword = "S5";
			}else if(  searchKeyword.contains("레트로")|| searchKeyword.contains("복고") ) {
				searchKeyword = "S6";
			}else if(  searchKeyword.contains("로맨")) {
				searchKeyword = "S7";
			}else if(  searchKeyword.contains("시크")) {
				searchKeyword = "S8";
			}else if(  searchKeyword.contains("아메카지") || searchKeyword.contains("아메") ) {
				searchKeyword = "S9";
			}
			
			paramss.put("searchKeyword", searchKeyword); ////searchKeyword =  tiger   
			
			System.out.println("**paramss = " + paramss);
			System.out.println("**searchKeyword = " + searchKeyword);
			
			
			
			
			//2. 업무로직 - 한번요청할때마다 5개씩 끊어온다 		
			// 조인 쿼리 - 게시글 		
			List<OotdBoardandAttachment> ootdboardAndAttachmentsbyStyle = ootdBoardService.SearchOotdBymemberStyle( paramss );
			System.out.println(  ootdboardAndAttachmentsbyStyle   );
			String msgnull = "검색결과가 없습니다 😣";
			
			if(  ootdboardAndAttachmentsbyStyle.isEmpty()) {
				System.out.println("검색결과없음");
				request.setAttribute("msgnull", msgnull );
			}
			
			//3. 응답처리 - json		
			request.setAttribute("ootdboardAndAttachmentsbyStyle", ootdboardAndAttachmentsbyStyle);
			request.getRequestDispatcher("/WEB-INF/views/ootd/ootdFindbyStyleAj.jsp")
			.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			throw e; //예외전담페이지로 던짐
			
		}
	}

}
