package com.sh.obtg.column.model.dao;

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

import com.sh.obtg.column.model.dto.Column;
import com.sh.obtg.column.model.exception.ColumnException;

public class ColumnDao {

	private Properties prop = new Properties();
	
	public ColumnDao() {
		String path = ColumnDao.class.getResource("/sql/column/column-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Column 쿼리 로드 완료! - " + prop);
	}
	
	/**
	 * 컬럼 저장
	 * @param conn
	 * @param column
	 * @return
	 */
	public int insertColumn(Connection conn, Column column) {
		// insert into obtg_column values (seq_column_no.nextval, ?, ?, ?, ?, ?, ?, default, default)
		String sql = prop.getProperty("insertColumn");
		int result = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, column.getWriter());
			pstmt.setString(2, column.getTitle());
			pstmt.setString(3, column.getSubtitle());
			pstmt.setString(4, column.getContent());
			pstmt.setString(5, column.getOriginalFilename());
			pstmt.setString(6, column.getRenamedFilename());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw new ColumnException("👻컬럼 저장 오류👻", e);
		}	
		
		return result;
	}

	/**
	 * 컬럼 총 개수 조회
	 * @param conn
	 * @return
	 */
	public int getTotalCount(Connection conn) {
		String sql = prop.getProperty("getTotalCount");
		int totalCount = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rset = pstmt.executeQuery()) {
			
			if(rset.next()) {
				totalCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			throw new ColumnException("👻컬럼 총 개수 조회 오류👻", e);
		}
		
		return totalCount;
	}

	private Column handleColumnResultSet(ResultSet rset) throws SQLException {
		Column column = new Column();
		column.setNo(rset.getInt("no"));
		column.setWriter(rset.getString("writer"));
		column.setTitle(rset.getString("title"));
		column.setSubtitle(rset.getString("subtitle"));
		column.setContent(rset.getString("content"));
		column.setOriginalFilename(rset.getString("original_filename"));
		column.setRenamedFilename(rset.getString("renamed_filename"));
		column.setReadCount(rset.getInt("read_count"));
		column.setRegDate(rset.getDate("reg_date"));
		return column;
	}
	
	/**
	 * 컬럼 리스트 조회
	 * @param conn
	 * @param param
	 * @return
	 */
	public List<Column> selectColumnList(Connection conn, Map<String, Integer> param) {
		String sql = prop.getProperty("selectColumnList");
		List<Column> columnList = new ArrayList<>();
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, param.get("start"));
			pstmt.setInt(2, param.get("end"));
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					Column column = handleColumnResultSet(rset);
					columnList.add(column);
				}
			}
			
		} catch (SQLException e) {
			throw new ColumnException("👻컬럼 리스트 조회 오류👻", e);
		}
		
		return columnList;
	}

	/**
	 * 컬럼 한건 조회
	 * @param conn
	 * @param no
	 * @return
	 */
	public Column selectOneColumn(Connection conn, int no) {
		// select * from obtg_colunm where no = ?
		String sql = prop.getProperty("selectOneColumn");
		Column column = null;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					column = handleColumnResultSet(rset);
				}
			}
			
		} catch (Exception e) {
			throw new ColumnException("👻컬럼 한건 조회 오류👻", e);
		}
		
		return column;
	}
	
	
	/**
	 * 컬럼 조회수 처리
	 * @param conn
	 * @param no
	 * @return
	 */
	public int updateReadCount(Connection conn, int no) {
		// update obtg_column set read_count = read_count + 1 where no = ?
		String sql = prop.getProperty("updateReadCount"); 
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw new ColumnException("👻컬럼 조회수 처리 오류👻", e);
		}
		
		return result;
	}

	/**
	 * 컬럼 수정
	 * @param conn
	 * @param column
	 * @return
	 */
	public int updateColumn(Connection conn, Column column) {
		// update obtg_column set title = ?, subtitle = ?, original_filename = ?, renamed_filename = ?, content = ? where no = ?
		String sql = prop.getProperty("updateColumn");
		int result = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, column.getTitle());
			pstmt.setString(2, column.getSubtitle());
			pstmt.setString(3, column.getOriginalFilename());
			pstmt.setString(4, column.getRenamedFilename());
			pstmt.setString(5, column.getContent());
			pstmt.setInt(6, column.getNo());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw new ColumnException("👻컬럼 업데이트 오류👻", e);
		}
		
		return result;
	}

	/**
	 * 컬럼 삭제
	 * @param conn
	 * @param no
	 * @return
	 */
	public int deleteColumn(Connection conn, int no) {
		// delete obtg_column where no = ?
		String sql = prop.getProperty("deleteColumn");
		int result = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw new ColumnException("👻컬럼 삭제 오류👻", e);
		}
	
		return result;
	}

}
