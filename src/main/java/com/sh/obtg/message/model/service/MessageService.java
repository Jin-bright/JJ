package com.sh.obtg.message.model.service;

import static com.sh.obtg.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.message.model.dao.MessageDao;
import com.sh.obtg.message.model.dto.Message;

public class MessageService {
	
	private MessageDao messageDao = new MessageDao();

	//1. message 테이블에 넣기 
	public int insertMessage(Message message) {
		Connection conn = getConnection(); 
		int result = 0;
		try {
			result = messageDao.insertMessage( conn ,message );
			commit(conn);
		}catch (Exception e) {
			rollback(conn);
		}finally {
			close(conn);
		}
		return result;
	}

	
	
	// 내게 온 쪽지 확인
	public List<Message> selectMsgList(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Message> msgList = messageDao.selectMsgList(conn, param);
		close(conn);
		return msgList;
	}

	// 내게 온 쪽지수 확인
	public int selectTotalCount(String memberId) {
		Connection conn = getConnection();
		int totalCount = messageDao.selectTotalCount(conn, memberId);
		close(conn);
		return totalCount;
	}

	/**
	 * 메시지 삭제하기
	 * @param no
	 * @return
	 */
	public int deleteMsg(int[] noArr) {
		Connection conn = getConnection(); 
		int result = 0;
		try {
			result = messageDao.deleteMsg(conn, noArr);
			commit(conn);
		}catch (Exception e) {
			rollback(conn);
		}finally {
			close(conn);
		}
		return result;
	}
	

}
