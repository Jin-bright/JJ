package com.sh.obtg.message.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.message.model.service.MessageService;

/**
 * Servlet implementation class MessageDeleteServlet
 */
@WebServlet("/message/messageDelete")
public class MessageDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MessageService messageService = new MessageService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 사용자 입력값
			int no = Integer.parseInt(request.getParameter("delNo"));
			
			// 업무로직
			int result = messageService.deleteMsg(no);
			System.out.println(result > 0 ? "쪽지 삭제 성공" : "쪽지 삭제 실패");
			
			response.sendRedirect(request.getContextPath()+"/message/messageList");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("msg", "쪽지 삭제 오류");
			response.sendRedirect(request.getContextPath()+"/message/messageList");
		}
	}

}
