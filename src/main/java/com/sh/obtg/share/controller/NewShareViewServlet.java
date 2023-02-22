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
import com.sh.obtg.share.model.dto.NshareBoard;
import com.sh.obtg.share.model.service.ShareService;

/**
 * Servlet implementation class NewShareViewServlet
 */
@WebServlet("/share/newShareView")
public class NewShareViewServlet extends HttpServlet {
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
				cookie.setPath(request.getContextPath() + "/share/newShareView");
				response.addCookie(cookie);
			}
			
			NshareBoard shareBoard = shareService.selectNewOneBoard(no, hasRead); // 게시판번호( product_id)
			System.out.println("■■ shareBoard = " + shareBoard);
			
			
			
			
			//좋아요
			HttpSession session = request.getSession();
			Member loginMember = (Member)session.getAttribute("loginMember");	
			Map<String, Object> param = new HashMap<>();
			param.put("memberId", loginMember != null ? loginMember.getMemberId() : "null");
			param.put("boardNo", no);
			int count = shareService.selectShareLike(param);
			System.out.println("■■ shareLike = " + count);
					
			
			// request 객체저장 
			request.setAttribute("shareBoard", shareBoard); //게시글+사진 request객체에 저장
			request.setAttribute("likeCnt", count);
		
			
			//3.리다이렉트
			request.getRequestDispatcher("/WEB-INF/views/share/newShareView.jsp")
			.forward(request, response);
		
		}catch (Exception e) {			
			request.getSession().setAttribute("msg", " 홈페이지 오류가 발생했습니다. 로그인 후 재시도 바랍니다.");
			e.printStackTrace();
		}

	}	

}
