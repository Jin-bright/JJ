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
 *  newshare - ajax - color로 찾기 (확인46)
 */
@WebServlet("/share/findShareWholeListColor")
public class NewShareFindColorNStyle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShareService shareService = new ShareService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1. 사용자입력값 
		
		
		//페이지구하기 
		int page = 1;
		int limit = 12;
		try {
			page = Integer.parseInt( request.getParameter("page")); //1페이지, 2페이지, 3페이지	
		} catch (NumberFormatException e) {
		}
		
		int start = (page-1) * limit + 1;    // 스타트 페이지 : 1 2345  6    11  이걸 서블릿에서 어떻게 구해 ? 
		int end  = page * limit;
		
		
		Map<String, Integer> param = new HashMap<>(); //
		param.put("start", start);
		param.put("end", end);
		
		System.out.println("page : " + page);
		
		String searchKeyword = request.getParameter("searchKeyword"); 
		String searchType = null;
		// 찾는값 
		System.out.println("★★★searchKeyword : " + searchKeyword);
		String colors = "빨강,검정,파랑,하얀,초록,주황,노랑,하늘,베이지,보라";

		
		if( colors.contains(searchKeyword)){
			searchType = "B.PRODUCT_COLOR";
		}
		
		else {
		  searchType = "B.STYLE_NAME";			
		}
			
		
		System.out.println("■ searchType : " + searchType);
		
		//2. 업무로직 
		List<NshareBoard> shareboards;
		List<NshareAttachment> shareAttachments;

		shareAttachments = shareService.findNShareAttachment(param, searchKeyword, searchType);
		shareboards = shareService.findNShareBoards(param, searchKeyword, searchType );
		
		//System.out.println(  shareboards   );
		
		System.out.println("end"+ end);
		int colorstyletotalPage = (int)(end%12) + 1;
		System.out.println("뭔데 : " + colorstyletotalPage);
	
		
		Map<String,Object> shareboardandAttach = new HashMap<>();
		shareboardandAttach.put("shareAttachments", shareAttachments);
		shareboardandAttach.put("shareboards", shareboards);
		shareboardandAttach.put("colorstyletotalPage", colorstyletotalPage);
		
		//start가 13개면 /12했을때 나머지가 1이면 총 2페이지
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(shareboardandAttach, response.getWriter());	}

}
