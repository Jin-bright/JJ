package com.sh.obtg.chatting.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.sh.obtg.chatting.model.dto.Chatroom;

public class ChatDAo {
	//최초 설정 
	Properties prop = new Properties();
	public ChatDAo() {
		String path = ChatDAo.class.getResource("/sql/chat/chat-query.properties").getPath();
		try {
			prop.load( new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("[ chat-query 준비완료] + prop ");
	}
	public int insertToChatRoom(Connection conn, Chatroom chatRoom ) {
		String sql = prop.getProperty("insertToChatRoom");
		int result = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, chatRoom.getChatroomId());
			pstmt.setString(2, chatRoom.getBoardId());
			pstmt.setString(3, chatRoom.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ChatException("채팅방!에 정보넣기실패", e);	
		}
		return result;
	}///

}
