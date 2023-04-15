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

@WebServlet("/notification/notificationUpdate")
public class NotificationUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NotificationService notificationService = new NotificationService();
	
	/**
	 * 알림 읽음 처리
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 사용자 입력값
			String memberId = request.getParameter("receiver");
			
			// 업무로직
			int result = notificationService.updateNoti(memberId);
			//System.out.println(result > 0 ? "알림 업뎃 성공" : "알림 업뎃 실패");

			Map<String, Object> map = new HashMap<>();
			map.put("result", "알림 업뎃 성공");
			
			// 응답처리 - json
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(result, response.getWriter());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
