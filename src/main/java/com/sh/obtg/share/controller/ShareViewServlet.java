package com.sh.obtg.share.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.share.model.dto.ShareBoard;
import com.sh.obtg.share.model.service.ShareService;

/**
 * Servlet implementation class ShareViewServlet
 */
@WebServlet("/share/shareView")
public class ShareViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShareService shareService = new ShareService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	try {	
		//1.사용자입력값 처리 
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
//				select * from OOTD_board where OOTD_no = ?
	// selectAttachmentByBoardNo = select * from attachment where board_no = ?
//		 	select * from  OOTD_attachment	where board_no = ?
			
		ShareBoard shareBoard = shareService.selectOneBoard(no, hasRead); //no는 게시판 번호 (share_no) 
		System.out.println("shareBoard = " + shareBoard);
		
		//개행문자 처리 - textarea에서 필요하다 
//				ootdboard.setOOTDContents( 	
//						HelloMvcUtils.convertLineFeedToBr(
//								HelloMvcUtils.escapeHtml( ootdboard.getOOTDContents()))
//				); 
		
		// 좋아요 조회
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");	
		Map<String, Object> param = new HashMap<>();
		param.put("memberId", loginMember != null ? loginMember.getMemberId() : "null");
		param.put("boardNo", no);
		int count = shareService.selectShareLike(param);
		System.out.println("shareLike = " + count);
				
		request.setAttribute("shareBoard", shareBoard);
		request.setAttribute("likeCnt", count);
		request.getRequestDispatcher("/WEB-INF/views/share/shareView.jsp")
		.forward(request, response);
		
	}catch (Exception e) {
		
		request.getSession().setAttribute("msg", " 홈페이지 오류가 발생했습니다. 로그인 후 재시도 바랍니다.");
		e.printStackTrace();
		throw e;
		

	}	
		
	}

}
