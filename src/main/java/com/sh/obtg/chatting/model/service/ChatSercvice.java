package com.sh.obtg.chatting.model.service;

import static com.sh.obtg.common.JdbcTemplate.*;

import java.sql.Connection;

import com.sh.obtg.chatting.model.dao.ChatDAo;
import com.sh.obtg.chatting.model.dto.Chatroom;

public class ChatSercvice {
	private ChatDAo chatdao = new ChatDAo();

	public int insertToChatRoom(Chatroom chatRoom) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = chatdao.insertToChatRoom(conn, chatRoom );
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			close(conn);
		}
		return result;
	}
	

}
