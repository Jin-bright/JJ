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
import com.sh.obtg.share.model.dto.NshareAttachment;
import com.sh.obtg.share.model.dto.NshareBoard;
import com.sh.obtg.share.model.dto.ShareAttachment;
import com.sh.obtg.share.model.dto.ShareBoard;
import com.sh.obtg.share.model.dto.Style;
import com.sh.obtg.share.model.dto.Subcategory;
import com.sh.obtg.share.model.service.ShareService;

/**
 * Servlet implementation class ShareEnrollServlet
 */
@WebServlet("/share/newShareEnroll")
public class ShareEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShareService shareService = new ShareService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/share/newShareEnroll.jsp")
		.forward(request, response);	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. MultipartRequest객체 생성 - 요청메세지에서 파일을 읽어서(=input) 서버컴퓨터에서 저장 (=output) 까지 처리해준다 

		try {
			String saveDirectory = getServletContext().getRealPath("/uploadshares/newShare"); //application 객체 반환  //  / <-- webroot를 가리킨다
			System.out.println("saveDirectory : " + saveDirectory  );
			
			int maxPostSize = 10*1024*1024;  //바이트단위로 줘야됨 (1kb = 1024byte  1mb - 1024*1kb ? )  
			String encoding = "utf-8";
			FileRenamePolicy policy = new OotdFileRenamePolicy(); //년월일_시분초밀리초_난수.tx  이렇게 만들거임 
			
			MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			// 여기까지 하고 . request가 아닌  MultipartRequest multiReq 값 꺼내는걸로 다 변경해줘야됨 

			
		    // 1. 사용자입력값 처리
			String _subcategoryId = "";
			_subcategoryId = multiReq.getParameter("real");
			/*
			 * if ( multiReq.getParameter("ShareCategory") != null ){ _subcategory_id =
			 * multiReq.getParameter("ShareCategory"); System.out.println( _subcategory_id
			 * ); }else if(multiReq.getParameter("ShareCategorybt") != null ) {
			 * _subcategory_id = multiReq.getParameter("ShareCategorybt");
			 * System.out.println( _subcategory_id ); }else
			 * if(multiReq.getParameter("ShareCategoryac") != null ) { _subcategory_id =
			 * multiReq.getParameter("ShareCategoryac"); System.out.println( _subcategory_id
			 * ); }
			 */
		
			System.out.println( "enum 전 : " + _subcategoryId  );
		
	
			Subcategory subcategoryId = Subcategory.valueOf( _subcategoryId ); //2 카테고리 
			System.out.println("subcategory_id : "  + subcategoryId );

		
			String memberId = multiReq.getParameter("memberId");//3.아이디
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
			
			Style styleName = Style.valueOf(_style); //4.스타일
			String productName = multiReq.getParameter("ShareTitle");//5. 상품명
			String productContent = multiReq.getParameter("editordata"); //6.내용
			int productPrice =  Integer.parseInt( multiReq.getParameter("productPrice"));//7.가격
			String productStatus = multiReq.getParameter("ShareState"); //9 거래전
			String productQuality = multiReq.getParameter("ShareProductStatus"); //10.상중하
			String productColor = multiReq.getParameter("sharecolor"); //11. 컬러
			System.out.println("★색상 : " + productColor );
			String productGender = multiReq.getParameter("productGender"); //13. 성별

			
		
			
			// 2-1. NshareBoard 에 셋팅 
			NshareBoard shareBoard = new NshareBoard();
			shareBoard.setSubcategoryId(subcategoryId);
			shareBoard.setMemberId(memberId);
			shareBoard.setStyleName(styleName);
			shareBoard.setProductName(productName);
			shareBoard.setProductContent(productContent);
			shareBoard.setProductPrice(productPrice);
			shareBoard.setProductStatus(productStatus);
			shareBoard.setProductQuality(productQuality);
			shareBoard.setProductColor(productColor);
			shareBoard.setProductGender(productGender);
			System.out.println( "**shareBoard " + shareBoard );
			
			
			
			
			//2-2. 업로드한 파일처리 
			if( multiReq.getFile("upFile1") !=null ) {
				NshareAttachment attach = new NshareAttachment();
				attach.setOriginalFilename( multiReq.getOriginalFileName("upFile1"));
				attach.setRenamedFilename(multiReq.getFilesystemName("upFile1") );
				shareBoard.addAttachment(attach);
			}
			if( multiReq.getFile("upFile2") !=null ) {
				NshareAttachment attach = new NshareAttachment();
				attach.setOriginalFilename( multiReq.getOriginalFileName("upFile2"));
				attach.setRenamedFilename(multiReq.getFilesystemName("upFile2") );
				shareBoard.addAttachment(attach);
			}
	
	
			
// share게시글  -- dml -- insert문 
// insertNShareBoard  = insert into NSHARE_BOARD values(seq_SHARE_board_no.nextval,?,?,?,?,?,?,default,?,?,?,default,?)
			// 2-3. 업무로직 
			int result = shareService.insertNShareBoard(shareBoard);
		    	System.out.println( "성공 ??? " + result );
	    	//3.리다이렉트
//			    	response.sendRedirect(request.getContextPath()+"/ootd/boardView?no=" + board.getNo());
		    	request.setAttribute("shareBoard", shareBoard);//
		    	response.sendRedirect(request.getContextPath()+"/share/newShareWholeList");
		    	
			}catch( Exception e) {
				System.out.println("오류발생");
				
				e.printStackTrace();
				request.getSession().setAttribute("msg", "share 게시글 등록중 오류가 발생했습니다." );
			}

	}
}
