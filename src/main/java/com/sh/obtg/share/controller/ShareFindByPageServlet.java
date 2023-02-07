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
 * Servlet implementation class ShareFindByPageServlet
 */
@WebServlet("/share/shareWholeListFindSecond")
public class ShareFindByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShareService shareService = new ShareService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//1. 입력값 
		//페이지
		int page = Integer.parseInt( request.getParameter("page")); //1페이지, 2페이지, 3페이지
		int limit = 5;
		int start = (page-1) * limit + 1;    // 스타트 페이지 : 1 2345  6    11  이걸 서블릿에서 어떻게 구해 ? 
		int end  = page * limit;
		
		
		
		Map<String, Integer> paramPage = new HashMap<>(); //
		paramPage.put("start", start);
		paramPage.put("end", end);
		
		String searchType = request.getParameter("searchType"); // searchType = member_id
		String searchKeyword = request.getParameter("searchKeywordID"); //searchKeyword =  tiger  
			
		//검색어/검색타입
		Map<String, String> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		
		
		//System.out.println( "searchType = " + searchType );
		//System.out.println("searchKeywordID = " + searchKeyword );
				
		
		//System.out.println("param = " + param);
		//System.out.println("param = " + paramPage);

		//2. 업무로직 - select 기능 
		// 조인쿼리 도전
		List<ShareBoardAndAttachment> shareBoardAndAttachmentsSecond = shareService.searchShareBykeyWord(param, paramPage);
		System.out.println( "결과 -- " + shareBoardAndAttachmentsSecond  );

		//페이지구하기 - 실패
		/*
		 * int FindtotalCount = shareService.getFindTotalCount( param ); //전체 find 한
		 * 게시물수 System.out.println( "**totalCount(전체 검색 아이템 갯수) = " + FindtotalCount );
		 * int totalfindPage = (int)Math.ceil( (double)FindtotalCount/ limit );
		 * //limit으로 나눠준다 전체아이템갯수를 System.out.println("totalfindPage" + totalfindPage );
		 */
		//	request.setAttribute("totalfindPage", totalfindPage);
		//	request.setAttribute("shareBoardAndAttachments", shareBoardAndAttachments);

		//3. 응답처리 - json
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(shareBoardAndAttachmentsSecond, response.getWriter());
	}

}
