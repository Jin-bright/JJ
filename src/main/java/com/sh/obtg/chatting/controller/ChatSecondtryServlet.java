package com.sh.obtg.chatting.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.chatting.model.dto.Chatroom;
import com.sh.obtg.chatting.model.service.ChatSercvice;
import com.sh.obtg.share.model.dto.ShareBoard;
import com.sh.obtg.share.model.service.ShareService;

/**
 * Servlet implementation class ChatSecondtryServlet
 */
@WebServlet("/chat/chatsecondtry")
public class ChatSecondtryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShareService shareService = new ShareService();
	private ChatSercvice chatService = new ChatSercvice();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	// protected void doGet(HttpServletRequest request, HttpServletResponse
	// response) throws ServletException, IOException {

	/*
	 * // 1. 사용자입력값 처리 - 우리채팅방은 int인디,, 아냐 string 으로 변경 // String chatroomId =
	 * request.getParameter("chatroomId") ; // int boardno = Integer.parseInt(
	 * request.getParameter("boardno") );
	 * 
	 * // System.out.println( chatroomId ); // System.out.println( "오류나니 ? " +
	 * boardno );
	 * 
	 * if( chatroomId == null || "".equals(chatroomId) ) { throw new
	 * IllegalArgumentException("채팅방 아이디가 유효하지 않습니다!!!! "); //runtime }
	 * 
	 * // 2. 업무로직 request.getSession().setAttribute("chatroomId",
	 * chatroomId);//웹소켓세션에서chatroomid를 정보를 사용할 수 있도록 httpsession에 저장 ★★★★★
	 * System.out.println("**chatroomId나와라좀  : " + chatroomId);
	 * 
	 * 
	 * // ShareBoard shareBoard = shareService.selectOneBoard(boardno);
	 * //request.getSession().setAttribute("chatreceiver",
	 * shareBoard.getMemberId());
	 * 
	 * 
	 * 
	 * // 4. 응답처리 - jsp 포워딩 // request.setAttribute("shareBoard", shareBoard);
	 */
	// request.getRequestDispatcher("/WEB-INF/views/chat/chatsecondtry.jsp")
	// .forward(request, response);
	// }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *//*
		 * protected void doPost(HttpServletRequest request, HttpServletResponse
		 * response) throws ServletException, IOException {
		 * 
		 * // 1. 사용자입력값 처리 - 우리채팅방은 int인디,, 아냐 string 으로 변경 String chatroomId =
		 * request.getParameter("chatroomId") ; //채팅방이름 int boardno = Integer.parseInt(
		 * request.getParameter("boardno") ); // 글넘버 String memberID =
		 * request.getParameter("memberID");//memberID
		 * 
		 * if( chatroomId == null || "".equals(chatroomId) ) { throw new
		 * IllegalArgumentException("채팅방 아이디가 유효하지 않습니다!!!! "); //runtime }
		 * 
		 * System.out.println("여기는 servlet ");
		 * 
		 * // 2. 업무로직 ShareBoard shareBoard = shareService.selectOneBoard(boardno);
		 * request.setAttribute("shareBoard", shareBoard);
		 * 
		 * request.getSession().setAttribute("chatroomId",
		 * chatroomId);//웹소켓세션에서chatroomid를 정보를 사용할 수 있도록 httpsession에 저장 ★★★★★
		 * request.getSession().setAttribute("chatreceiver",shareBoard.getMemberId());
		 * 
		 * 
		 * //3. 이거처리되면 insert해야될듯싶은데
		 * 
		 * Chatroom chatRoom = new Chatroom(); chatRoom.setChatroomId(chatroomId);
		 * chatRoom.setBoardId(shareBoard.getMemberId()); //받는사람 = 게시글쓴사람
		 * chatRoom.setMemberId(memberID); System.out.println( chatRoom );
		 * 
		 * int result = chatService.insertToChatRoom(chatRoom);
		 * System.out.println(" 결과는 ? shareBoard = " + shareBoard);
		 * response.sendRedirect(request.getContextPath()+"/chat/chatsecondtry"); }
		 */
}
