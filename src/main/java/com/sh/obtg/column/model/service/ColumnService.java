package com.sh.obtg.column.model.service;

import static com.sh.obtg.common.JdbcTemplate.*;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.sh.obtg.column.model.dao.ColumnDao;
import com.sh.obtg.column.model.dto.Column;

public class ColumnService {
	private ColumnDao columnDao = new ColumnDao();
	
	/**
	 * 컬럼 등록
	 * @param column
	 * @return
	 */
	public int insertColumn(Column column) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = columnDao.insertColumn(conn, column);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	/**
	 * 컬럼 총 페이지수
	 * @return
	 */
	public int getTotalCount() {
		Connection conn = getConnection();
		int totalCount = columnDao.getTotalCount(conn);
		close(conn);
		return totalCount;
	}

	/**
	 * 컬럼 리스트 조회
	 * @param param
	 * @return
	 */
	public List<Column> selectColumnList(Map<String, Integer> param) {
		Connection conn = getConnection();
		List<Column> columnList = columnDao.selectColumnList(conn, param);
		close(conn);
		return columnList;
	}

	/**
	 * 컬럼 한건 조회
	 * @param no
	 * @param hasRead
	 * @return
	 */
	public Column selectOneColumn(int no, boolean hasRead) {
		Connection conn = getConnection();
		if(!hasRead) updateReadCount(no, conn);
		Column column = columnDao.selectOneColumn(conn, no);
		close(conn);
		return column;
	}

	public Column selectOneColumn(int no) {
		return selectOneColumn(no, true);
	}
	
	/**
	 * 컬럼 조회수 처리
	 * @param no
	 * @param conn
	 */
	private void updateReadCount(int no, Connection conn) {
		try {
			int result = columnDao.updateReadCount(conn, no); 
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
	}

	/**
	 * 컬럼 수정
	 * @param column
	 * @return
	 */
	public int updateColumn(Column column) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = columnDao.updateColumn(conn, column);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	/**
	 * 컬럼 삭제
	 * @param no
	 * @return
	 */
	public int deleteColumn(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = columnDao.deleteColumn(conn, no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	
}
