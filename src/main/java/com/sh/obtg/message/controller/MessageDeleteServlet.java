package com.sh.obtg.message.controller;

import java.io.IOException;

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
			// 사용자 입력값 - 문자열로 받아온 값
			String noList = request.getParameter("delNo");
			noList = noList.replaceAll("undefined,", ""); // undefined 제거
			String[] strArray = noList.split(",");
			
			// 메시지가 여러개일 경우 해당 메시지 번호를 int 배열에 담아 전달
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
