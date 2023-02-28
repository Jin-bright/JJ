package com.sh.obtg.ootd.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.sh.obtg.ootd.model.dto.OotdAttachment;
import com.sh.obtg.ootd.model.service.OotdBoardService;

/**
 * Servlet implementation class OotdDeleteServlet
 */
@WebServlet("/ootd/newOotdDelete")
public class NewOotdDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OotdBoardService ootdBoardService = new OotdBoardService();


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값 가져오기 
		int no = Integer.parseInt( request.getParameter("no") );
		System.out.println("no = " + no );
		
		//2. 업무로직  
		// -  1. board 삭제 /// 2. attachment도 삭제해야됨 / 쿼리두개써야돼 ?  3. 저장된 첨부파일 삭제  
		
		//저장된 첨부파일삭제 
		List<OotdAttachment> attachments = ootdBoardService.selectAttachmentByBoardNo(no);
		String saveDirectory = getServletContext().getRealPath("/uploadootds/ootd"); //application 객체 반환  //  / <-- webroot를 가리킨다

		for(OotdAttachment attach : attachments) {	
			File delFile = new File(saveDirectory, attach.getRenamedFilename());   //  특정ㅎ파일을 가리키는 자바파일 
			boolean bool = delFile.delete(); // delete는 boolen타입 반환 (삭제 잘되면 true  ) 
			System.out.println( bool ? "파일삭제성공!! " : "파일삭제실패 ");
		}
		
		//1&2 동시에 처리 되기 때문에 첨부파일만 먼저 확인해서 날랴야됨  
		int result  = ootdBoardService.deleteBoard(no);  //fk삭제옵션에따라 attachment도 같이날라감 
		
		//3. 리다이렉트 /board/boardList 로 
		request.getSession().setAttribute("msg", "게시글을 성공적으로 삭제했습니다.");
		response.sendRedirect(request.getContextPath() + "/ootd/newOotdWholeList");
	}

}
