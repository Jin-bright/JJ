package com.sh.obtg.ootd.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.ootd.model.dto.OotdBoardComment;
import com.sh.obtg.ootd.model.service.OotdBoardService;

/**
 * Servlet implementation class OotdCommentEnrollServlet
 */
@WebServlet("/ootd/ootdCommentEnroll")
public class OotdCommentEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OotdBoardService ootdBoardService = new OotdBoardService();


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값 처리 
		int boardNo = Integer.parseInt( request.getParameter("boardNo"));
		int commentLevel  = Integer.parseInt(  request.getParameter("commentLevel"));
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		int commentRef = Integer.parseInt(  request.getParameter("commentRef"));
	
		OotdBoardComment boardComment = new OotdBoardComment();
		boardComment.setBoardNo(boardNo);
		boardComment.setCmtLevel(commentLevel);
		boardComment.setMemberId(writer);
		boardComment.setCmtContent(content);
		boardComment.setCommentRef(commentRef);
		
		System.out.println( " boardComment 정보 = " + boardComment  );
		
		//2. 업무로직 - board_comment 행추가 
// 주의 : comment_ref 컬럼값 셋팅시 , 0이면 setInt 안되서 setObject 사용해서 0이면 null을 대입할수있도록 한다 		
		int result = ootdBoardService.insertBoardComment( boardComment );
		
		//3. redirect - /board/boardView?no=~~ 
		request.setAttribute("boardComment", boardComment);
		response.sendRedirect(request.getContextPath() + "/ootd/newOotdView?no="+boardComment.getBoardNo());
		
	}

}
