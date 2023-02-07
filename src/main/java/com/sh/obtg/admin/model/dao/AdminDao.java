package com.sh.obtg.admin.model.dao;

import static com.sh.obtg.common.JdbcTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sh.obtg.admin.model.exception.AdminException;
import com.sh.obtg.ootd.model.dao.OotdBoardDao;

public class AdminDao {
	
	private Properties prop = new Properties();

	public AdminDao() {
		
		String fileName = OotdBoardDao.class.getResource("/sql/admin/admin-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> selectBlackList(Connection conn, int start, int end) {
		List<String> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBlackList");
		//select * from (select rownum rnum,b.* from (select * from blacklist order by email asc) b) where rnum between ? and ?
		try {
			list = new ArrayList<String>();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				list.add(rset.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AdminException("블랙리스트 조회 실패", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int selectBlackListCount(Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBlackListCount");
		//select count(*) from blacklist
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result = rset.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AdminException("블랙리스트 totalContent 조회 실패", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public int unbanFromBlackList(Connection conn, String email) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("unbanFromBlackList");
		//delete blacklist where email = ?
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new  AdminException("블랙리스트 목록에서 제거 실패", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
}