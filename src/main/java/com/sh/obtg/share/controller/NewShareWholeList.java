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
import javax.servlet.http.HttpSession;

import com.sh.obtg.common.HelloMvcUtils;
import com.sh.obtg.member.model.dto.Member;
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
	
		
		int totalCount = shareService.getTotalCount();  // 	1.  dql 전체 게시글 수 구하기 (=첨부파일수랑 같음 ) 
//		System.out.println( "■■ 토탈카운트 : " +  totalCount  );
		String url = request.getRequestURI(); // 
		String pagebar = HelloMvcUtils.getPagebar(page, limit, totalCount, url);


		//좋아요
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");	
		

		int[] likearr = new int[12];

		if( loginMember != null) {
		
			Map<String, Object> likeparam = new HashMap<>();
			likeparam.put("memberId", loginMember != null ? loginMember.getMemberId() : "null");
			
			int boardNo = 0;
			int likecount = 0;
			
			for(int i=0; i< shareboards.size(); i++) {
				boardNo = shareboards.get(i).getProductId();

				likeparam.put("boardNo", boardNo );
				
				likecount = shareService.selectShareLike(likeparam);
				
			//	System.out.println("■■ shareLike 여부 0 또는 1  = " + likecount);  정녕이렇게해야만 하는걸까 ?
				likearr[i] = likecount;
			}

		}
				
		
		request.setAttribute("shareAttachments", shareAttachments);
		request.setAttribute("shareboards", shareboards);
		request.setAttribute("pagebar", pagebar);
		session.setAttribute("likearr", likearr);	
		
		//3. forward 연결
		request.getRequestDispatcher("/WEB-INF/views/share/newShareWholeList.jsp").forward(request, response);
	}

}
