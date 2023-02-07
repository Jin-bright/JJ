package com.sh.obtg.notification.model.service;

import static com.sh.obtg.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.sh.obtg.notification.model.dao.NotificationDao;
import com.sh.obtg.notification.model.dto.Notification;

public class NotificationService {
	private NotificationDao notificationDao = new NotificationDao();

	/**
	 * 알림 있는지 확인
	 * @param memberId
	 * @return
	 */
	public List<Notification> selectNotiList(String receiver) {
		Connection conn = getConnection();
		List<Notification> notiList = notificationDao.selectNotiList(conn, receiver);
		close(conn);
		return notiList;
	}

	/**
	 * 알림 읽음 처리
	 * @param memberId
	 * @return
	 */
	public int updateNoti(String memberId) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = notificationDao.updateNoti(conn, memberId);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		
		return result;
	}
	
	/**
	 * 알림 등록
	 * @param noti
	 * @return
	 */
	public int insertNoti(Notification noti) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = notificationDao.insertNoti(conn, noti);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}
}
