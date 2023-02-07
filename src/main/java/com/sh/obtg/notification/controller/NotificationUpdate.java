package com.sh.obtg.notification.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.notification.model.service.NotificationService;

/**
 * Servlet implementation class NotificationUpdate
 */
@WebServlet("/notification/notificationUpdate")
public class NotificationUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NotificationService notificationService = new NotificationService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("읽음 처리하러 왔니?");
			String memberId = request.getParameter("receiver");
			System.out.println("[알림]" + memberId);
			
			int result = notificationService.updateNoti(memberId);
			//System.out.println(result > 0 ? "알림 업뎃 성공" : "알림 업뎃 실패");
		
			

			Map<String, Object> map = new HashMap<>();
			map.put("result", "알림 업뎃 성공");
			
			
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(result, response.getWriter());

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
