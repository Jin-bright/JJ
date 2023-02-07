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

import com.sh.obtg.common.HelloMvcUtils;
import com.sh.obtg.share.model.dto.ShareAttachment;
import com.sh.obtg.share.model.dto.ShareBoard;
import com.sh.obtg.share.model.service.ShareService;

/**
 * Servlet implementation class ShareWholeListServlet
 */
@WebServlet("/share/shareWholeList")
public class ShareWholeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShareService shareService = new ShareService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try { // 큰 try - catch문 
			//2. 페이징처리 (1)컨텐트) 
			final int limit = 10;
			int page = 1;
			
			try{
				page  = Integer.parseInt(request.getParameter("page"));
			}catch(NumberFormatException e) {
				//
			}
			
			Map<String, Object> param = new HashMap<>();
			param.put("page", page);
			param.put("limit", limit);
			
		 	
			// 1.  인코딩 x 사용자입력값 x  전체게시글 중에 파일 출력 (처음 매개변수x -> 매개변수 param)
			// 게시글도 출력하려면  ?
			
			List<ShareAttachment> shareAttachments;
			List<ShareBoard> shareboards;
			
			
			shareAttachments = shareService.viewShareAttachment(param);
			shareboards = shareService.viewShareBoards(param);
			
			request.setAttribute("shareAttachments", shareAttachments);
			request.setAttribute("shareboards", shareboards);

			System.out.println( "**shareAttachments : " +  shareAttachments.size()  );
			System.out.println( "**토탈카shareboards운트 : " +  shareboards.size()  );

			
			int totalCount = shareService.getTotalCount();  // 	1.  dql 전체 게시글 수 구하기  -- int를 반환하는 dql임 (select count(*) from board 
			System.out.println( "**토탈카운트 : " +  totalCount  );
			String url = request.getRequestURI(); // /mvc/board/boardList
			String pagebar = HelloMvcUtils.getPagebar(page, limit, totalCount, url);
		//	System.out.println("pagebar : " + pagebar );
			request.setAttribute("pagebar", pagebar);
			
			////////////////////////////////////////
			String searchType = request.getParameter("searchType"); // searchType = member_id
			String searchKeyword = request.getParameter("searchKeywordID"); //searchKeyword =  tiger  
			// find 페이지구하기
			//검색어/검색타입

			int totalfindPage = 1;
			
			if(  searchType != null && searchKeyword != null ) {
				Map<String, String> paramSearch = new HashMap<>();
				paramSearch.put("searchType", searchType);
				paramSearch.put("searchKeyword", searchKeyword);
			
				int FindtotalCount = shareService.getFindTotalCount( paramSearch ); //전체 find 한 게시물수
				System.out.println( "**totalCount(전체 검색 아이템 갯수) = " + FindtotalCount   );
			
				
				try{
					totalfindPage = (int)Math.ceil( (double)FindtotalCount/ limit ); //limit으로 나눠준다 전체아이템갯수를
				}catch (NumberFormatException e) {}
				
				System.out.println("★★★totalfindPage - wholelist에셋팅 = " + totalfindPage );						
			}
			
			
			request.setAttribute("totalfindPage", totalfindPage);
		//	request.setAttribute("shareBoardAndAttachments", shareBoardAndAttachments);
		}
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/views/share/shareWholeList.jsp")
		.forward(request, response);
		
	
	}



}
