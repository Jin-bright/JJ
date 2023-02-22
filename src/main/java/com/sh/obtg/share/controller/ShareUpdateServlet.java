package com.sh.obtg.share.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.sh.obtg.common.OotdFileRenamePolicy;
import com.sh.obtg.share.model.dto.ShareAttachment;
import com.sh.obtg.share.model.dto.ShareBoard;
import com.sh.obtg.share.model.dto.Style;
import com.sh.obtg.share.model.service.ShareService;

/**
 * Servlet implementation class ShareUpdateServlet
 */
@WebServlet("/share/shareUpdate")
public class ShareUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShareService shareService = new ShareService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. jsp에 내용넣기 
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("no = " + no);
		
		
		//2. 업무로직 -- 어떤게시물을 수정할것인가 
//		ShareBoard shareBoard = shareService.selectOneBoard(no);
//		System.out.println( "shareBoard = "  + shareBoard );
		
		//3
//		request.setAttribute("shareBoard",shareBoard );
		request.getRequestDispatcher("/WEB-INF/views/share/shareUpdate.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	try {	
		String saveDirectory = getServletContext().getRealPath("/uploadshares/share"); //application 객체 반환  //  / <-- webroot를 가리킨다
		int maxPostSize = 10*1024*1024;  //바이트단위로 줘야됨 (1kb = 1024byte  1mb - 1024*1kb ? )  
		String encoding = "utf-8";
		FileRenamePolicy policy = new OotdFileRenamePolicy(); //년월일_시분초밀리초_난수.tx  이렇게 만들거임 
		
		MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		
		int no = Integer.parseInt(multiReq.getParameter("no")); //hidden으로 가져오는거맞음 ^^ 
	//	System.out.println("**update 서블릿에서 :게시글번호 : " + no);
		
		//1.사용자입력값 
		String shareTitle = multiReq.getParameter("ShareTitle");
		String shareWriter = multiReq.getParameter("memberId");

		String _shareCategory = multiReq.getParameter("ShareCategory");	
		String shareCategory="";
		if( _shareCategory.equals("상의")) {
			shareCategory = "상의";
		}
		else if( _shareCategory.equals("하의")) {
			shareCategory = "하의";
		}
		else if( _shareCategory.equals("악세서리")) {
			shareCategory = "악세서리및기타";
		}
		
		String shareProductStatus = multiReq.getParameter("ShareProductStatus");		
		
		String _shareBuyDate = multiReq.getParameter("ShareBuyDate");
		Date shareBuyDate = !"".equals(_shareBuyDate) ?  ( Date.valueOf(_shareBuyDate)) : null;

	//	System.out.println("**update 서블릿에서 : " + shareBuyDate);

		String _style = multiReq.getParameter("style");
		if( _style.equals("러블리")) {
			_style = "S1";
		}else if( _style.equals("댄디")) {
			_style = "S2";
		}else if( _style.equals("포멀")) {
			_style = "S3";
		}else if( _style.equals("스트릿")) {
			_style = "S4";
		}else if( _style.equals("걸리쉬")) {
			_style = "S5";
		}else if( _style.equals("레트로")) {
			_style = "S6";
		}else if( _style.equals("로맨틱")) {
			_style = "S7";
		}else if( _style.equals("시크")) {
			_style = "S8";
		}else if( _style.equals("아메카지")) {
			_style = "S9";
		}
		
		Style style = Style.valueOf(_style);
		
		String shareContent = multiReq.getParameter("editordata");
		String shareState = multiReq.getParameter("ShareState");
		
		ShareBoard shareBoard = new ShareBoard();
		shareBoard.setShareNo(no);
		shareBoard.setMemberId(shareWriter);
		shareBoard.setShareTitle(shareTitle);
		shareBoard.setShareContent(shareContent);
		shareBoard.setShareBuyDate(shareBuyDate);
		shareBoard.setShareProductStatus(shareProductStatus);
		shareBoard.setShareCategory(shareCategory);
		shareBoard.setShareState(shareState);
		shareBoard.setStyleNo(style);
			
		//사진 수정부분
		Enumeration<String> filenames = multiReq.getFileNames(); // upFile1, upFile2, ...
		while(filenames.hasMoreElements()) {
			String filename = filenames.nextElement(); 
			
			
			if( multiReq.getFile(filename) != null ) { // 전송된 파일이 있는가?
				ShareAttachment attach = new ShareAttachment();
				
				attach.setBoardNo(no); // fk 값대입
				attach.setOriginalFilename(multiReq.getOriginalFileName(filename));
				attach.setRenamedFilename(multiReq.getFilesystemName(filename));
				
				shareBoard.addAttachment(attach);
			}
		}//end while 
	//		System.out.println( "**update 서블렛 attach : " + shareBoard );
	//		System.out.println( "**update 서블렛 attach : " + shareBoard );
			
			//2. 업무로직 

			int result = shareService.updateBoard(shareBoard);
		
			
			//3.리다이렉트
			response.sendRedirect(request.getContextPath()+"/share/shareView?no=" + shareBoard.getShareNo() );
	}catch (Exception e) {
		e.printStackTrace();
		request.getSession().setAttribute("msg", "게시글 등록중 오류가 발생했습니다.");
		response.sendRedirect(request.getContextPath()+"/share/shareWholeList");
	}	
	}//end-

}
