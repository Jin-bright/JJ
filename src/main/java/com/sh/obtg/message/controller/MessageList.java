package com.sh.obtg.message.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.obtg.common.HelloMvcUtils;
import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.message.model.dto.Message;
import com.sh.obtg.message.model.service.MessageService;

/**
 * Servlet implementation class MessageList
 */
@WebServlet("/message/messageList")
public class MessageList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MessageService messageService = new MessageService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {			
			final int limit = 10;
			int page = 1; // 기본값
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {} 
			
			HttpSession session = request.getSession();
			Member member = (Member)session.getAttribute("loginMember");
			String memberId = member.getMemberId();
			
			Map<String, Object> param = new HashMap<>();
			param.put("page", page);
			param.put("limit", limit);
			param.put("memberId", memberId);
			
			// 업무로직(select)
			List<Message> msgList = messageService.selectMsgList(param);
			System.out.println(msgList);
			
			int totalCount = messageService.selectTotalCount(memberId);
			System.out.println(">>totalCnt : " +totalCount);
			String url = request.getRequestURI();
			String pagebar = HelloMvcUtils.getPagebar(page, limit, totalCount, url);

			request.setAttribute("msgList", msgList);
			request.setAttribute("pagebar", pagebar);
			request.getRequestDispatcher("/WEB-INF/views/member/msgList.jsp")
				.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("msg", "쪽지 내역을 조회 오류");
			String referer = request.getHeader("Referer"); // http://~
			response.sendRedirect(referer);
		}
		
		
	}

}
