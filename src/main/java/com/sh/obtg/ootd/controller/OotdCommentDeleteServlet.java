package com.sh.obtg.ootd.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.ootd.model.service.OotdBoardService;

/**
 * Servlet implementation class OotdCommentDeleteServlet
 */
@WebServlet("/ootd/ootdCommentDelete")
public class OotdCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OotdBoardService ootdBoardService = new OotdBoardService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt( request.getParameter("boardNo"));

		try {
		//1. 사용자입력값 
			int no = Integer.parseInt( request.getParameter("no"));
			System.out.println(" no가뭔데 " + no );
		//2. 로직 
			int result = ootdBoardService.boardCommentDelete(no);
			
			System.out.println("댓글 삭제 : " + result );
			//3. 리다렉
				request.getSession().setAttribute("msg", "댓글 삭제 성공!");
				response.sendRedirect( request.getContextPath()+"/ootd/ootdView?no=" + boardNo );
				
			} catch (Exception e) {
				e.printStackTrace();
//				throw e;  톰캣쪽에 예외던지는코드 
				request.getSession().setAttribute("msg", "댓삭제중오류가 발생했씁니다");
				response.sendRedirect( request.getContextPath()+"/ootd/ootdView?no=" + boardNo );

			}	
	}

}
