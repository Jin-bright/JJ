package com.sh.obtg.message.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.message.model.service.MessageService;

@WebServlet("/message/messageDelete")
public class MessageDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MessageService messageService = new MessageService();

	/**
	 * 메시지 삭제 post 요청
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 사용자 입력값
			String noList = request.getParameter("delNo");
			noList = noList.replaceAll("undefined,", "");
			String[] strArray = noList.split(",");
			int[] noArr = new int[strArray.length];

			for (int i = 0; i < strArray.length; i++) {
			    noArr[i] = Integer.parseInt(strArray[i]);
			}
			
			// 업무로직
			int result = messageService.deleteMsg(noArr);
			System.out.println(result > 0 ? "쪽지 삭제 성공" : "쪽지 삭제 실패");
			
			response.sendRedirect(request.getContextPath()+"/message/messageList");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("msg", "쪽지 삭제 오류");
			response.sendRedirect(request.getContextPath()+"/message/messageList");
		}
	}

}
