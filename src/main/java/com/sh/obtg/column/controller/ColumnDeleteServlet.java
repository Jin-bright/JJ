package com.sh.obtg.column.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.column.model.dto.Column;
import com.sh.obtg.column.model.service.ColumnService;

@WebServlet("/column/columnDelete")
public class ColumnDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ColumnService columnService = new ColumnService();

	/**
	 * 컬럼 삭제 요청
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 사용자 입력값
			int no = Integer.parseInt(request.getParameter("no"));
			
			// 업무로직 delete obtg_column where no = ?
			String delImg = columnService.selectOneColumn(no).getRenamedFilename();
			int result = columnService.deleteColumn(no);
			System.out.println(result > 0 ? "컬럼 삭제 완료!" : "컬럼 삭제 실패!");
			
			String saveDirectory = getServletContext().getRealPath("/upload/column");
			File delFile = new File(saveDirectory, delImg);
			if(delFile.exists()) {
				Boolean bool = delFile.delete();
				System.out.println(bool ? "이미지 삭제 완료!" : "이미지 삭제 실패!");
			}
			
			// 리다이렉트
			response.sendRedirect(request.getContextPath() + "/column/columnList");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
	}
}