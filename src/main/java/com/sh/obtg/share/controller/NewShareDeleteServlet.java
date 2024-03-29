package com.sh.obtg.share.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.share.model.dto.NshareAttachment;
import com.sh.obtg.share.model.service.ShareService;

/**
 * Servlet implementation class ShareDeleteServlet
 */
@WebServlet("/share/newShareDelete")
public class NewShareDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShareService shareService = new ShareService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//1. 사용자 입력값 가져오기 
			int no = Integer.parseInt( request.getParameter("no") );
			System.out.println("■■■ 삭제 no = " + no );
			
			//2. 업무로직  
			// -  1. board 삭제 /// 2. attachment도 삭제해야됨 / 쿼리두개써야돼
			
			//저장된 첨부파일삭제 
			List<NshareAttachment> attachments = shareService.selectAttachmentByBoardNo(no); //일단 첨부파일 하나가져오기 
			
			String saveDirectory = getServletContext().getRealPath("/uploadshares/newShare"); 
			for (NshareAttachment attach : attachments ) {
				File delFile = new File(saveDirectory, attach.getRenamedFilename());   //  특정파일을 가리키는 자바파일 
				boolean bool = delFile.delete(); // delete는 boolen타입 반환 (삭제 잘되면 true  ) 
				System.out.println( bool ? "파일삭제성공!! " : "파일삭제실패 ");
			}
			
			int result = shareService.deleteNewBoard(no); 
			System.out.println( "■■ 삭제성공 ?  : "  + result );
			
			//3. 리다이렉트 /board/boardList 로 
			request.getSession().setAttribute("msg", "게시글을 성공적으로 삭제했습니다.");
			response.sendRedirect(request.getContextPath() + "/share/newShareWholeList");

			
	}

}
