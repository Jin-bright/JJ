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
import com.sh.obtg.ootd.model.dto.OotdBoardandAttachment;
import com.sh.obtg.ootd.model.service.OotdBoardService;


/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/ootd/morePage")
public class NewOotdMorePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OotdBoardService ootdBoardService = new OotdBoardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			final int limit = 12;
			int page  = Integer.parseInt(request.getParameter("page"));
			
			
			Map<String, Object> param = new HashMap<>();
			param.put("page", page);
			param.put("limit", limit);
			
		 	System.out.println( page );
		 	System.out.println( limit );
		 	
			// 1.  인코딩 x 사용자입력값 x  전체게시글출력 (처음 매개변수x -> 매개변수 param)
			List<OotdBoardandAttachment> ootdAttachments = ootdBoardService.viewOotdBoardandAttachment(param);
			for(int i=0; i < ootdAttachments.size(); i++) {
				System.out.println("출력해주세여");
				System.out.println(  ootdAttachments.get(i).getOotdNo()  );
			}
		//	System.out.println(  ootdAttachments   );{
				
			
			
		//	int totalCount = ootdBoardService.getTotalCount();  // 	1.  dql 전체 게시글 수 구하기  -- int를 반환하는 dql임 (select count(*) from board 
		//	System.out.println( "**토탈카운트 : " +  totalCount  );
		//	String url = request.getRequestURI(); // /mvc/board/boardList
		//	String pagebar = HelloMvcUtils.getPagebar(page, limit, totalCount, url);
			//System.out.println("pagebar : " + pagebar );
		//	request.setAttribute("pagebar", pagebar);
		
	
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(ootdAttachments, response.getWriter());
	}

}
