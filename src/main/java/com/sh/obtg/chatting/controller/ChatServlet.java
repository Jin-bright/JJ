package com.sh.obtg.chatting.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/chat/chatmain")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/**
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * 
	 * // 1. 사용자입력값 처리 - 우리채팅방은 int인디,, int chatroomId = Integer.parseInt(
	 * request.getParameter("chatroomId") ); System.out.println( chatroomId );
	 * 
	 * if( ! (chatroomId > 0) ) { throw new IllegalArgumentException("채팅방 아이디가 유효하지
	 * 않습니다!!!! "); //runtime }
	 * 
	 * // 2. 업무로직 request.getSession().setAttribute("chatroomId",
	 * chatroomId);//웹소켓세션에서chatroomid를 정보를 사용할 수 있도록 httpsession에 저장 ★★★★★
	 * System.out.println("**chatroomId나와라좀 : " + chatroomId);
	 * 
	 * // 3. 응답처리 - jsp 포워딩
	 * 
	 * 
	 * 
	 * request.getRequestDispatcher("/WEB-INF/views/chat/chatmain.jsp")
	 * .forward(request, response); }
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *//*
		 * protected void doPost(HttpServletRequest request, HttpServletResponse
		 * response) throws ServletException, IOException { // TODO Auto-generated
		 * method stub int fakechatroomId = Integer.parseInt(
		 * request.getParameter("fakechatroomId") ); String fakereceiver =
		 * request.getParameter("fakereceiver") ;
		 * 
		 * if( ! (fakechatroomId > 0) ) { throw new
		 * IllegalArgumentException("채팅방 아이디가 유효하지 않습니다!!!! "); //runtime }
		 * 
		 * response.sendRedirect(request.getContextPath() + "/chat/chatmain" ); //클라이언트가
		 * 다시 요청할주소
		 * 
		 * 
		 * }
		 */

}
