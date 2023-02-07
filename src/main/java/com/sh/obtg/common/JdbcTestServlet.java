package com.sh.obtg.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JdbcTestServlet
 */
@WebServlet("/jdbc/test")
public class JdbcTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append("jdbc 연결 테스트 중입니다.....");
		
		try {
			testJdbcConnection();
			out.append("<h2>성공</h2>");
		} catch (ClassNotFoundException|SQLException e) {
			out.append("<h2>실패</h2>");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void testJdbcConnection() throws ClassNotFoundException, SQLException {
		String driverClass = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@JVMQDI1DWN1CUE55_high?TNS_ADMIN=C:/Wallet_JVMQDI1DWN1CUE55";
		String user = "ADMIN";
		String password = "Obtgobtgobtg1";
		
		String sql = "select * from member";
		
		// 1. DriverClass 등록
		Class.forName(driverClass);
		System.out.println("[드라이버클래스 등록완료]");
		
		// 2. Connection 객체 생성
		Connection conn = DriverManager.getConnection(url, user, password);
		conn.setAutoCommit(false);
		System.out.println("[Connection객체 생성 완료]");
		
		// 3. PreparedStatement 객체 생성 및 미완성쿼리 값대입
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		// 4. 실행(DML pstmt.executeUpdate():int, DQL pstmt.executeQuery():ResultSet)
		ResultSet rset = pstmt.executeQuery();
		System.out.println("[PreparedStatment 객체 생성 및 실행 완료]");
		
		// 5. 이후처리(DML 트랙잭션처리, DQL ResultSet을 자바변수에 옮겨 담기)
		while(rset.next()) {
			String memberId = rset.getString("member_id");
			String memberName = rset.getString("name");
			
			System.out.printf("%s\t %s\n", memberId, memberName);
		}
		// 6. 자원반납
		rset.close();
		pstmt.close();
		conn.close();
		System.out.println("[자원반납 완료]");
		
	}
	
}
