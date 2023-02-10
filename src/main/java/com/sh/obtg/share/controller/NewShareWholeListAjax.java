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
import com.sh.obtg.share.model.dto.NshareAttachment;
import com.sh.obtg.share.model.dto.NshareBoard;
import com.sh.obtg.share.model.service.ShareService;

/**
 * Servlet implementation class NewShareWholeListAjax
 */
@WebServlet("/share/NewShareWholeListAjax")
public class NewShareWholeListAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShareService shareService = new ShareService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 입력값 
		// 1~5개 이렇게 끊어서 가져오는거같음
//		int page = Integer.parseInt( request.getParameter("page")); //1페이지, 2페이지, 3페이지
//		int limit = 5;
//		int start = (page-1) * limit + 1;    // 스타트 페이지 : 1 2345  6    11  이걸 서블릿에서 어떻게 구해 ? 
//		int end  = page * limit;
			
	
//		Map<String, Integer> param = new HashMap<>(); //
//		param.put("start", start);
//		param.put("end", end);
		
		//2. 업무로직 
//		List<NshareBoard> shareboards;
//		List<NshareAttachment> shareAttachments;
		
		
//		shareAttachments = shareService.viewNShareAttachment(param);
//		shareboards = shareService.viewNShareBoards(param);
	
		
//		shareAttachments = shareService.viewNShareAttachment();
//		shareboards = shareService.viewNShareBoards();
		
		
//		Map<String, Object> map = new HashMap<>();
//		map.put("shareboards", shareboards);
//		map.put("shareAttachments", shareAttachments);
	//	request.setAttribute("shareAttachments", shareAttachments);
	//	request.setAttribute("shareboards", shareboards);

	//	request.setAttribute("map", map);
	//	request.getRequestDispatcher("/WEB-INF/views/share/newShareWholeList.jsp")
	//	.forward(request, response);

		
		//3. json연결
	//	response.setContentType("application/json; charset=utf-8");
	//	new Gson().toJson(map, response.getWriter());

		
	}

}
