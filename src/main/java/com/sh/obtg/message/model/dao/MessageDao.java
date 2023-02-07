package com.sh.obtg.message.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.sh.obtg.message.model.dto.Message;
import com.sh.obtg.message.model.exception.MessageException;

import oracle.net.aso.c;

public class MessageDao {

	//최초 설정 
	Properties prop = new Properties();
	
	public MessageDao() {

		String path = MessageDao.class.getResource("/sql/message/message-query.properties").getPath();
		try {
			prop.load( new FileReader(path));
		} catch (IOException e) {
			System.out.println("path 오류 ");
			e.printStackTrace();
		}
		System.out.println("[ ●message-query 준비완료 ]" + prop );

	}

	//1. dml - insert 메세지 넣기 
/**
 *     no number,
    title varchar2(50),
    sender varchar2(50),
    receiver varchar2(50) not null,
    content varchar2(1000) not null,
    reg_date date default sysdate,	
 */
	public int insertMessage(Connection conn, Message message) {
		String sql = prop.getProperty("insertMessage");
		int result = 0;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, message.getMessageTitle());
			pstmt.setString(2, message.getMessageSender());
			pstmt.setString(3, message.getMessageReceiver());
			pstmt.setString(4, message.getMessageContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MessageException();
			
		}
		return result;
	}


	// 내게 온 쪽지 목록 조회
	public List<Message> selectMsgList(Connection conn, Map<String, Object> param) {
		// select * from ( select row_number() over(order by reg_date desc) rnum, m.* from message m where receiver = ?) 
		// 	where rnum between ? and ?
		String sql = prop.getProperty("selectMsgList");
		List<Message> msgList = new ArrayList<>();
		int page = (int)param.get("page");
		int limit = (int)param.get("limit");
		String memberId = (String)param.get("memberId");
		
		int start = (page - 1) * limit + 1;
		int end = page * limit;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					Message msg = new Message();
					msg.setMessageNO(rset.getInt("no"));
					msg.setMessageTitle(rset.getString("title"));
					msg.setMessageSender(rset.getString("sender"));
					msg.setMessageReceiver(rset.getString("receiver"));
					msg.setMessageContent(rset.getString("content"));
					msg.setMessageRegdate(rset.getDate("reg_date"));
					msgList.add(msg);
				}
			}
			
		} catch (SQLException e) {
			throw new MessageException("👻 쪽지 조회 오류 👻", e);
		}
		
		return msgList;
	}

	// 내게 온 쪽지 수 확인
	public int selectTotalCount(Connection conn, String memberId) {
		// select count(*) from message where receiver = ?
		String sql = prop.getProperty("selectTotalCount");
		int totalCount = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					totalCount = rset.getInt(1);
				}
			}
			
		} catch (SQLException e) {
			throw new MessageException("👻 쪽지 수 오류 👻", e);
		}
		
		return totalCount;
	}

	public int deleteMsg(Connection conn, int no) {
		// delete message where no = ?
		String sql = prop.getProperty("deleteMsg");
		int result = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MessageException("👻 쪽지 삭제 오류 👻", e);
		}
		
		return result;
	}

}
