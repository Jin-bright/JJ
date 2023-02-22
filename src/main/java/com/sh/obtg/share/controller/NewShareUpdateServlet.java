package com.sh.obtg.share.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

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
import com.sh.obtg.share.model.dto.Style;
import com.sh.obtg.share.model.dto.Subcategory;
import com.sh.obtg.share.model.service.ShareService;

/**
 * Servlet implementation class ShareUpdateServlet
 */
@WebServlet("/share/newShareUpdate")
public class NewShareUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShareService shareService = new ShareService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. jsp에 내용넣기 
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("★updateform - no = " + no);
		
		//2. 업무로직 -- 어떤게시물을 수정할것인가 
		NshareBoard shareboard = shareService.selectNewOneBoardByNo(no);
		System.out.println( "★update shareBoard = "  + shareboard );
		
		//3 jsp에 전달 
		request.setAttribute("shareboard",shareboard );
		request.getRequestDispatcher("/WEB-INF/views/share/newShareUpdate.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	try {	
		String saveDirectory = getServletContext().getRealPath("/uploadshares/newShare"); // 1
		int maxPostSize = 10*1024*1024;  //2 바이트단위로 줘야됨 (1kb = 1024byte  1mb - 1024*1kb ? )  
		String encoding = "utf-8"; //3
		FileRenamePolicy policy = new OotdFileRenamePolicy(); //년월일_시분초밀리초_난수.tx  이렇게 만들거임 
		
		MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		
		int no = Integer.parseInt(multiReq.getParameter("no"));  
		System.out.println(" ■ 게시글번호 : " + no);
		
		//1.사용자입력값 
			String _subcategoryId = "";
			_subcategoryId = multiReq.getParameter("real");
		
			System.out.println( "★update enum 전 : " + _subcategoryId  );
		
	
			Subcategory subcategoryId = Subcategory.valueOf( _subcategoryId ); //2 카테고리 
			System.out.println("★update subcategory_id : "  + subcategoryId );

		
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
			System.out.println("★update 색상 : " + productColor );
			String productGender = multiReq.getParameter("productGender"); //13. 성별

			
		
			
			// 2-1. NshareBoard 에 셋팅 
			NshareBoard shareBoard = new NshareBoard();
			shareBoard.setProductId(no);
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
			System.out.println( "★update shareBoard " + shareBoard );
		
			//사진 수정부분
			Enumeration<String> filenames = multiReq.getFileNames(); // upFile1, upFile2, ...
			while(filenames.hasMoreElements()) {
				String filename = filenames.nextElement(); 
				
			
			if( multiReq.getFile(filename) != null ) { // 전송된 파일이 있는가?
				NshareAttachment attach = new NshareAttachment();
				
				attach.setProductId(no); // fk 값대입
				attach.setOriginalFilename(multiReq.getOriginalFileName(filename));
				attach.setRenamedFilename(multiReq.getFilesystemName(filename));
				
				shareBoard.addAttachment(attach);
				}
			}//end while 
			System.out.println( "**update 서블렛 attach : " + shareBoard );
			
			//2. 업무로직 
			int result = shareService.updateNshareBoard(shareBoard);
			System.out.println( "■ update result : " + result );
			
			//3.리다이렉트
			request.getSession().setAttribute("msg", "게시글을 성공적으로 수정하였습니다😊");
			response.sendRedirect(request.getContextPath()+"/share/newShareView?no="+no );

		}catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("msg", "게시글 등록중 오류가 발생했습니다😣");
			response.sendRedirect(request.getContextPath()+"/share/newShareWholeList");
		}	
	}//end-

}
