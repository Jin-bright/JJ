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
import com.sh.obtg.share.model.dto.NshareAttachment;
import com.sh.obtg.share.model.dto.NshareBoard;
import com.sh.obtg.share.model.service.ShareService;

/**
 * Servlet implementation class NewShareWholeList
 */
@WebServlet("/share/newShareWholeList")
public class NewShareWholeList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShareService shareService = new ShareService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 입력값 - 페이징 처리를 할거에요 

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
		
		//2. 업무로직 
		List<NshareBoard> shareboards;
		List<NshareAttachment> shareAttachments;
		
		
		shareAttachments = shareService.viewNShareAttachment(param);
		shareboards = shareService.viewNShareBoards(param);
//		System.out.println( "■■ shareAttachments : " + shareAttachments );
//		System.out.println( "■■ shareboards : " + shareboards );
//		System.out.println( "■■ param : "  + param  );
		
		Map<String, Object> map = new HashMap<>();
		map.put("shareboards", shareboards);
		map.put("shareAttachments", shareAttachments);
	
		
		int totalCount = shareService.getTotalCount();  // 	1.  dql 전체 게시글 수 구하기  (=첨부파일수랑 같음 ) 
//		System.out.println( "■■ 토탈카운트 : " +  totalCount  );
		String url = request.getRequestURI(); // 
		String pagebar = HelloMvcUtils.getPagebar(page, limit, totalCount, url);


		
		request.setAttribute("shareAttachments", shareAttachments);
		request.setAttribute("shareboards", shareboards);
		request.setAttribute("pagebar", pagebar);
		request.setAttribute("map", map);

		
		//3. forward 연결
		request.getRequestDispatcher("/WEB-INF/views/share/newShareWholeList.jsp").forward(request, response);
	}

}
