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

import com.sh.obtg.common.HelloMvcUtils;
import com.sh.obtg.ootd.model.dto.OotdAttachment;
import com.sh.obtg.ootd.model.service.OotdBoardService;


/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/ootd/ootdWholeList")
public class OotdWholeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OotdBoardService ootdBoardService = new OotdBoardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try { // 큰 try - catch문 
			//2. 페이징처리 (1)컨텐트) 
			final int limit = 15;
			int page = 1;
			
			try{
				page  = Integer.parseInt(request.getParameter("page"));
			}catch(NumberFormatException e) {
				//
			}
			
			Map<String, Object> param = new HashMap<>();
			param.put("page", page);
			param.put("limit", limit);
			
		 	System.out.println( page );
		 	System.out.println( limit );
		 	
			// 1.  인코딩 x 사용자입력값 x  전체게시글출력 (처음 매개변수x -> 매개변수 param)
			List<OotdAttachment> ootdAttachments;
			
			ootdAttachments = ootdBoardService.viewOotdAttachment(param);
			request.setAttribute("ootdAttachments", ootdAttachments);
			
			
			int totalCount = ootdBoardService.getTotalCount();  // 	1.  dql 전체 게시글 수 구하기  -- int를 반환하는 dql임 (select count(*) from board 
			System.out.println( "**토탈카운트 : " +  totalCount  );
			String url = request.getRequestURI(); // /mvc/board/boardList
			String pagebar = HelloMvcUtils.getPagebar(page, limit, totalCount, url);
			//System.out.println("pagebar : " + pagebar );
			request.setAttribute("pagebar", pagebar);
		
		}
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		request.getRequestDispatcher("/WEB-INF/views/ootd/ootdWholeList.jsp")
		.forward(request, response);
	}

}
