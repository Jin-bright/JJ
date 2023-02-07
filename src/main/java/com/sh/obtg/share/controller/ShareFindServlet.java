package com.sh.obtg.share.controller;

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
import com.sh.obtg.share.model.dto.ShareBoardAndAttachment;
import com.sh.obtg.share.model.service.ShareService;

/**
 * Servlet implementation class ShareFindServlet
 */
@WebServlet("/share/shareWholeListFind")
public class ShareFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShareService shareService = new ShareService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//1. 입력값 
		
		String searchType = request.getParameter("searchType"); // searchType = member_id
		String searchKeyword = request.getParameter("searchKeywordID"); //searchKeyword =  tiger  
		
		System.out.println("여기까지연결이되나" );
		System.out.println( "searchType = " + searchType );
		System.out.println("searchKeywordID = " + searchKeyword );
		
		Map<String, String> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		
		System.out.println("param = " + param);
		//2. 업무로직 - select 기능 
		//2-1. memberid로 찾기 - select * from ootd_board where memberId = ?  
	
		// 조인쿼리 도전
		List<ShareBoardAndAttachment> shareBoardAndAttachments = shareService.searchShareBykeyWord(param);
		System.out.println( "결과 -- " + shareBoardAndAttachments  );

		
	//	request.setAttribute("shareBoardAndAttachments", shareBoardAndAttachments);

		//3. 응답처리 - json
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(shareBoardAndAttachments, response.getWriter());
		
		
	}


}
