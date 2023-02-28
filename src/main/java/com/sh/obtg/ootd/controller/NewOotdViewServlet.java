package com.sh.obtg.ootd.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.obtg.common.HelloMvcUtils;
import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.ootd.model.dto.OotdBoard;
import com.sh.obtg.ootd.model.dto.OotdBoardComment;
import com.sh.obtg.ootd.model.service.OotdBoardService;

/**
 * Servlet implementation class OotdViewServlet
 */
@WebServlet("/ootd/newOotdView")
public class NewOotdViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OotdBoardService ootdBoardService = new OotdBoardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println(" 게시판 no = " + no);
		
		// board 쿠키 처리 ( 클라이언트쪽에 board [번호] 저장 
		String boardCookieVal = "";
		boolean hasRead = false;
		Cookie[] cookies = request.getCookies();
				
		if( cookies != null) {
			for( Cookie cookie : cookies) {
				String name = cookie.getName();
				String value = cookie.getValue();
				
				if("board".equals(name)) {
					boardCookieVal  = value; // board = "[84][22]" 이런식으로 담김 
					if(value.contains("[" + no + "]" )){
						hasRead = true;
					}
				}
			}
		}
		
		//응답쿠키
		if(!hasRead) {
			Cookie cookie = new Cookie("board", boardCookieVal + "[" + no + "]" );
			cookie.setMaxAge(365*24*60*60);
			cookie.setPath(request.getContextPath() + "/board/boardView");
			response.addCookie(cookie);
		}
		
		// 2. 업무로직 - 게시판/첨부파일테이블 조회
// selectOneBoard = select * from board where no = ?
//		select * from OOTD_board where OOTD_no = ?
// selectAttachmentByBoardNo = select * from attachment where board_no = ?
// 	select * from  OOTD_attachment	where board_no = ?
		
		OotdBoard ootdboard = ootdBoardService.selectOneBoard(no, hasRead);
		System.out.println("■■ ootdboard = " + ootdboard);
		
		//개행문자 처리 - textarea에서 필요하다 
//		ootdboard.setOOTDContents( 	
//				HelloMvcUtils.convertLineFeedToBr(
//						HelloMvcUtils.escapeHtml( ootdboard.getOOTDContents()))
//		); 
		
		//댓글목록조회 필요 0105
		List<OotdBoardComment> comments = ootdBoardService.selectBoardCommentList(no); //이거 보드넘버
		System.out.println( " ■■  댓글 comments = " + comments  );
			
		// 좋아요 조회
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");	
		Map<String, Object> param = new HashMap<>();
		param.put("memberId", loginMember != null ? loginMember.getMemberId() : "null");
		param.put("boardNo", no);
		int count = ootdBoardService.selectOotdLike(param);
		System.out.println("■■ boardLike = " + count);
				
		
		// 3. view단 위임
		request.setAttribute("ootdboard", ootdboard);
		request.setAttribute("comments", comments);
		request.setAttribute("likeCnt", count);
		request.getRequestDispatcher("/WEB-INF/views/ootd/newOotdView.jsp")
		.forward(request, response);
	}

	

}
