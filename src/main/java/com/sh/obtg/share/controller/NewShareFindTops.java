package com.sh.obtg.share.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sh.obtg.share.model.dto.NshareAttachment;
import com.sh.obtg.share.model.dto.NshareBoard;
import com.sh.obtg.share.model.service.ShareService;

/**
 * Servlet implementation class NewShareFindTops
 */
@WebServlet("/share/shareWholeListTops")
public class NewShareFindTops extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShareService shareService = new ShareService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//카테고리 - 옷은 다 이걸로 가능 
		// 함수는 다 다르게해서 
	//	String searchType = request.getParameter("searchType"); // searchType = member_id
	//	String searchKeyword = request.getParameter("searchKeywordID"); //searchKeyword =  tiger  
		String searchKeyword = request.getParameter("searchKeyword");
		System.out.println("searchKeyword : " + searchKeyword);
		
		//2. 업무로직 
		List<NshareBoard> shareboards;
		List<NshareAttachment> shareAttachments;

	//	shareAttachments = shareService.findNShareAttachment(param);
	//	shareboards = shareService.findNShareBoards(param);
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(searchKeyword, response.getWriter());
	}

}
