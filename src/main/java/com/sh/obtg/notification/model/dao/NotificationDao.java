package com.sh.obtg.notification.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sh.obtg.notification.model.dto.Checked;
import com.sh.obtg.notification.model.dto.Notification;
import com.sh.obtg.notification.model.exception.NotificationException;

import oracle.net.aso.c;

public class NotificationDao {

	private Properties prop = new Properties();
		
		public NotificationDao() {
			String path = NotificationDao.class.getResource("/sql/notification/notification-query.properties").getPath();
			try {
				prop.load(new FileReader(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("notification 쿼리 로드 완료! - " + prop);
		}
	
		/**
		 * 알림 내역
		 * @param rset
		 * @return
		 * @throws SQLException
		 */
		public Notification handleNotiResultSet(ResultSet rset) throws SQLException {
			Notification noti = new Notification();
			noti.setNo(rset.getInt("no"));
			noti.setReceiver(rset.getString("receiver"));
			noti.setMessage(rset.getString("message"));
			noti.setDatetime(rset.getDate("datetime"));
			noti.setChecked(rset.getString("checked") != null ? 
					Checked.valueOf(rset.getString("checked")) :
						null);
			return noti;
		}
		
		/**
		 * 알림 내역 조회
		 * @param conn
		 * @param receiver
		 * @return
		 */
		public List<Notification> selectNotiList(Connection conn, String receiver) {
			// select * from noti where receiver = ? and checked = 'X' 
			String sql = prop.getProperty("selectNotiList");
			List<Notification> notiList = new ArrayList<>();
			
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, receiver);
				
				try (ResultSet rset = pstmt.executeQuery()) {
					while(rset.next()) {
						Notification noti = new Notification();
						noti = handleNotiResultSet(rset);
						notiList.add(noti);
					}
				}
				
			} catch (SQLException e) {
				throw new NotificationException("👻알림 내역 조회 오류👻", e);
			}
			
			return notiList;
		}

		/**
		 * 알림 등록
		 * @param conn
		 * @param noti
		 * @return
		 */
		public int insertNoti(Connection conn, Notification noti) {
			// insert into noti(no, receiver, message) values (seq_noti_no.nextval, ?, ?)
			String sql = prop.getProperty("insertNoti");
			int result = 0;
			
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, noti.getReceiver());
				pstmt.setString(2, noti.getMessage());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				throw new NotificationException("👻 알림 등록 오류👻", e);
			}
			
			return result;
		}

		/**
		 * 알림 읽음 처리
		 * @param conn
		 * @param memberId
		 * @return
		 */
		public int updateNoti(Connection conn, String memberId) {
			// update noti set checked = 'O' where receiver = ?
			String sql = prop.getProperty("updateNoti");
			int result = 0;
			
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, memberId);
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				throw new NotificationException("👻 알림 읽음처리 오류 👻", e);
			}
			
			return result;
		}

}
