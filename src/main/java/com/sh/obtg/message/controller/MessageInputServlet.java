package com.sh.obtg.message.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.message.model.dto.Message;
import com.sh.obtg.message.model.service.MessageService;
import com.sh.obtg.notification.model.dto.Notification;
import com.sh.obtg.notification.model.service.NotificationService;

/**
 * Servlet implementation class MessageInputServlet
 */
@WebServlet("/chat/MessageMain")
public class MessageInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MessageService messageService = new MessageService();
	private NotificationService notificationService = new NotificationService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	//1. 사용자입력값 처리 
		int no = 0;
		try {
			try {
				no = Integer.parseInt(request.getParameter("no"));
			} catch (NumberFormatException e) {}
			String receiver = request.getParameter("receiver");
			String sender = request.getParameter("sender");
			String msgContent = request.getParameter("msgContent");
		
		
			String msgTitle = "제목없음";
			if(request.getParameter("msgTitle") != null) {
				msgTitle = request.getParameter("msgTitle");
			}; //default title 설정
			//2업무로직 
			// insert into message values(se ,?,?,?,?,default)
			
			Message message = new Message();
			message.setMessageReceiver(receiver);
			message.setMessageSender(sender);
			message.setMessageTitle(msgTitle);
			message.setMessageContent(msgContent);		
			
			int result = messageService.insertMessage(message);
			System.out.println("메세지 input 성공여부 " + result );
			
			// 알림 내역 저장
			Notification noti = new Notification();
			String msg = sender + "(으)로부터 쪽지가 도착했습니다!🥰";
			noti.setReceiver(receiver);
			noti.setMessage(msg);
			
			int notiResult = notificationService.insertNoti(noti);
			System.out.println(notiResult > 0 ? "알림 내역 저장 성공" : "알림 내역 저장 실패");
			
			if(no > 0) {
				response.sendRedirect(request.getContextPath()+"/share/newShareView?no="+no);
			}
			else {
				response.sendRedirect(request.getContextPath()+"/message/messageList");
			}

		}catch (Exception e) {
			request.getSession().setAttribute("msg", "게시글 등록중 오류가 발생했습니다." );
			e.printStackTrace();
			if(no > 0) {
				response.sendRedirect(request.getContextPath()+"/share/newShareView?no="+no);
			}
			else {
				response.sendRedirect(request.getContextPath()+"/message/messageList");
			}
		}			
	}//


}

/**
    private int messageNO;
	private String messageTitle;
	private String messageSender;
	private String messageReceiver;
	private String messageContent;
	private Date messageRegdate;
	
	/**
create table message (
no number,
title varchar2(50),
sender varchar2(50),
receiver varchar2(50) not null,
content varchar2(1000) not null,
reg_date date default sysdate,
constraint pk_message_no primary key(no),
constraint fk_message_sender foreign key(sender) references member (member_id) on delete set null
);
**/

