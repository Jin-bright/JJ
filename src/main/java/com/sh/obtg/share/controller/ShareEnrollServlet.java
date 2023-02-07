package com.sh.obtg.share.controller;

import java.io.IOException;
import java.sql.Date;

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
 * Servlet implementation class ShareEnrollServlet
 */
@WebServlet("/share/shareEnroll")
public class ShareEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShareService shareService = new ShareService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/share/shareEnroll.jsp")
		.forward(request, response);	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. MultipartRequest객체 생성 - 요청메세지에서 파일을 읽어서(=input) 서버컴퓨터에서 저장 (=output) 까지 처리해준다 
		// -- (HttpServletRequest arg0, String arg1, int arg2, String arg3, FileRenamePolicy arg4) throws IOException 
		// 1. HttpServletRequest자리 / 2. String saveDirectory(실제저장할파일경로)/ 3.업로드할수있는파일최대크기 (꼭정해야됨) 일반파일은 10mb정도/ 4.인코딩(utf-8) / 5.파일이름정책객체? - 중복파일이 있는경우 어떻게할거냐  

//		try {
			String saveDirectory = getServletContext().getRealPath("/uploadshares/share"); //application 객체 반환  //  / <-- webroot를 가리킨다
			System.out.println("saveDirectory : " + saveDirectory  );
			
			int maxPostSize = 10*1024*1024;  //바이트단위로 줘야됨 (1kb = 1024byte  1mb - 1024*1kb ? )  
			String encoding = "utf-8";
			FileRenamePolicy policy = new OotdFileRenamePolicy(); //년월일_시분초밀리초_난수.tx  이렇게 만들거임 
			
			MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			// 이까지 하면 파일이 서버컴텨에 저장된다 --서버저장하는거까지끝 --- 
			// 여기까지 하고 . request가 아닌  MultipartRequest multiReq 값 꺼내는걸로 다 변경해줘야됨 

			
		    // 1. 사용자입력값 처리
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

			System.out.println("shareCategory : "  + shareCategory );
			
			String shareProductStatus = multiReq.getParameter("ShareProductStatus");
			
			
			String _shareBuyDate = multiReq.getParameter("ShareBuyDate");
			Date shareBuyDate = !"".equals(_shareBuyDate) ?  ( Date.valueOf(_shareBuyDate)) : null;
			
			
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
			
			
			
			System.out.println(" _style : " + _style );
			System.out.println(" **style : " + style );
			
			// shareBoard 에 셋팅 
			
			ShareBoard shareBoard = new ShareBoard();
			
			shareBoard.setMemberId(shareWriter);
			shareBoard.setShareTitle(shareTitle);
			shareBoard.setShareContent(shareContent);
			shareBoard.setShareCategory(shareCategory);
			shareBoard.setShareBuyDate(shareBuyDate);
			shareBoard.setShareProductStatus(shareProductStatus);
			shareBoard.setShareState(shareState);
			shareBoard.setStyleNo(style);
			
			System.out.println( "**shareBoard " + shareBoard );
			
			//업로드한 파일처리 
			if( multiReq.getFile("upFile1") !=null ) {
				ShareAttachment attach = new ShareAttachment();
				attach.setOriginalFilename(multiReq.getOriginalFileName("upFile1"));
				attach.setRenamedFilename( multiReq.getFilesystemName("upFile1")); 		// 2. 파일명 변경 (original -> renamed )?
				shareBoard.addAttachment(attach);
			}
	
			
//share  -- dml -- insert문 
//insertShareBoard = insert into SHARE_board values(seq_SHARE_board_no.nextval,?,?,?,default,default,?,?,?,?,?)

	//2. 업무로직 - 쿼리 insertBoard = insert into board (no, title, writer, content) values (seq_board_no.nextval, ?, ?, ?)
		    	int result = shareService.insertShareBoard(shareBoard); // board에 싹다넣어서 서비스요청 코드는 이거 하나임 ★★★★★내가한방식은안돼 
		    	System.out.println( "성공 ??? " + result );
	    	//3.리다이렉트
//			    	response.sendRedirect(request.getContextPath()+"/ootd/boardView?no=" + board.getNo());
		    	response.sendRedirect(request.getContextPath()+"/share/shareWholeList");

			
			
			/**
			 
			}catch( Exception e) {
				System.out.println("오류발생");
				
				e.printStackTrace();
				request.getSession().setAttribute("msg", "share 게시글 등록중 오류가 발생했습니다." );
				response.sendRedirect(request.getContextPath()+"/share/shareWholeList");
			}
			* 		
			 */
		    // 리다이렉트는 : board/boardList로 가게 -  메세지는 너무 뻔한경우는 걍 쓰지마 (왜냐면 이 글은 제일 최신으로 등록되니까 바로 확인가능 )

	}
}
