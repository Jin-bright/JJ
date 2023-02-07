package com.sh.obtg.message.model.dto;

import java.sql.Date;

public class Message {

	private int messageNO;
	private String messageTitle;
	private String messageSender;
	private String messageReceiver;
	private String messageContent;
	private Date messageRegdate;
	
	//기본생성자 
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	//매개변수생성자 
	public Message(int messageNO, String messageTitle, String messageSender, String messageReceiver,
			String messageContent, Date messageRegdate) {
		super();
		this.messageNO = messageNO;
		this.messageTitle = messageTitle;
		this.messageSender = messageSender;
		this.messageReceiver = messageReceiver;
		this.messageContent = messageContent;
		this.messageRegdate = messageRegdate;
	}
	
	//get set 
	public int getMessageNO() {
		return messageNO;
	}
	public void setMessageNO(int messageNO) {
		this.messageNO = messageNO;
	}
	public String getMessageTitle() {
		return messageTitle;
	}
	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}
	public String getMessageSender() {
		return messageSender;
	}
	public void setMessageSender(String messageSender) {
		this.messageSender = messageSender;
	}
	public String getMessageReceiver() {
		return messageReceiver;
	}
	public void setMessageReceiver(String messageReceiver) {
		this.messageReceiver = messageReceiver;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public Date getMessageRegdate() {
		return messageRegdate;
	}
	public void setMessageRegdate(Date messageRegdate) {
		this.messageRegdate = messageRegdate;
	}
	
	
	
	@Override
	public String toString() {
		return "Message [messageNO=" + messageNO + ", messageTitle=" + messageTitle + ", messageSender=" + messageSender
				+ ", messageReceiver=" + messageReceiver + ", messageContent=" + messageContent + ", messageRegdate="
				+ messageRegdate + "]";
	}
	

}

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
