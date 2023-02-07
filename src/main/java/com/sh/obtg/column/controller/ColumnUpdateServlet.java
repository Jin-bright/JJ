package com.sh.obtg.column.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.sh.obtg.column.model.dto.Column;
import com.sh.obtg.column.model.service.ColumnService;
import com.sh.obtg.common.HelloMvcFileRenamePolicy;

/**
 * Servlet implementation class ColumnUpdateServlet
 */
@WebServlet("/column/columnUpdate")
public class ColumnUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ColumnService columnService = new ColumnService();

	/**
	 * 컬럼 수정폼 get요청
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값
		int no = Integer.parseInt(request.getParameter("no"));
		// System.out.println(no);
		
		Column column = columnService.selectOneColumn(no);
		
		request.setAttribute("column", column);
		request.getRequestDispatcher("/WEB-INF/views/column/columnUpdate.jsp")
			.forward(request, response);
		
	}

	/**
	 * 컬럼 수정 post요청
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// MultipartRequest 객체 생성
			String saveDirectory = getServletContext().getRealPath("/upload/column"); // 웹루트디렉토리(src/main/webapp)부터 탐색
			int maxPostSize = 10 * 1024 * 1024; // byte단위 : 1kb-1024, 1mb-1024*1kb
			String encoding = "utf-8";
			FileRenamePolicy policy = new HelloMvcFileRenamePolicy(); // 년월일_시분초밀리초_난수.txt 
			MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			
			// 사용자입력값 처리 - request가 아닌 MultipartRequest에서 값 꺼내기
			int no = Integer.parseInt(multiReq.getParameter("no"));
			String title = multiReq.getParameter("col_title");
			String subtitle = multiReq.getParameter("col_subtitle");
			String content = multiReq.getParameter("col_content");
			String originalFilename = multiReq.getOriginalFileName("col_file");
			String renamedFilename = multiReq.getFilesystemName("col_file");
			
			Column beforeColumn = columnService.selectOneColumn(no);
			
			Column column = new Column();
			column.setNo(no);
			column.setTitle(title);
			column.setSubtitle(subtitle);
			column.setContent(content);
			
			if(originalFilename != null) {
				File deFile = new File(saveDirectory, beforeColumn.getRenamedFilename());
				if(deFile.exists()) deFile.delete();
				column.setOriginalFilename(originalFilename);
				column.setRenamedFilename(renamedFilename);
			}
			else {
				column.setOriginalFilename(beforeColumn.getOriginalFilename());
				column.setRenamedFilename(beforeColumn.getRenamedFilename());
			}
			
			// 업무로직 update obtg_column set title = ?, subtitle = ?, original_filename = ?, renamed_filename = ?, content = ? where no = ?
			int result = columnService.updateColumn(column);

			// 리다이렉트
			response.sendRedirect(request.getContextPath() + "/column/columnView?no=" + column.getNo());
			
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("msg", "게시글 등록중 오류가 발생했습니다.");
			response.sendRedirect(request.getContextPath() + "/column/columnList");
		}
	}

}
