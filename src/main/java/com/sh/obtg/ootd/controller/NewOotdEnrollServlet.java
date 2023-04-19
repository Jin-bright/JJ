package com.sh.obtg.ootd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.sh.obtg.common.OotdFileRenamePolicy;
import com.sh.obtg.ootd.model.dto.OotdAttachment;
import com.sh.obtg.ootd.model.dto.OotdBoard;
import com.sh.obtg.ootd.model.dto.Style;
import com.sh.obtg.ootd.model.service.OotdBoardService;

/**
 * Servlet implementation class OotdEnrollServlet
 */
@WebServlet("/ootd/newOotdEnroll")
public class NewOotdEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OotdBoardService ootdBoardService = new OotdBoardService();
	/**
	 * 혜진 - ootd 글쓰기 폼 서블렛 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/ootd/newOotdEnroll.jsp")
		.forward(request, response);	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			try {
				String saveDirectory = getServletContext().getRealPath("/uploadootds/ootd"); //application 객체 반환  //  / <-- webroot를 가리킨다
				System.out.println("saveDirectory : " + saveDirectory  );
				
				int maxPostSize = 10*1024*1024;  //바이트단위로 줘야됨 (1kb = 1024byte  1mb - 1024*1kb ? )  
				String encoding = "utf-8";
				
				FileRenamePolicy policy = new OotdFileRenamePolicy(); //년월일_시분초밀리초_난수.tx  이렇게 만들거임 

				MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
				// 이까지 하면 파일이 서버컴텨에 저장된다 --서버저장하는거까지끝 --- 
				// 여기까지 하고 . request가 아닌  MultipartRequest multiReq 값 꺼내는걸로 다 변경해줘야됨 

				
			    // 1. 사용자입력값 처리
				String ootdtitle = multiReq.getParameter("ootdtitle");
				String ootdwriter = multiReq.getParameter("ootdwriter");
				String ootdTop = multiReq.getParameter("ootdTop");
				String ootdBottom = multiReq.getParameter("ootdBottom");
				String ootdShoes = multiReq.getParameter("ootdShoes");
				String ootdEtc = multiReq.getParameter("ootdEtc");
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
				String ootdContents = multiReq.getParameter("editordata");
				
				System.out.println(" _style : " + _style );
				System.out.println(" **style : " + style );
				
				
				OotdBoard ootdBoard = new OotdBoard();
				ootdBoard.setOotdWriter(ootdwriter);
				ootdBoard.setStyleNo(style);
				ootdBoard.setOOTDTitle(ootdtitle);
				ootdBoard.setOOTDTop(ootdTop);
				ootdBoard.setOOTDBottom(ootdBottom);
				ootdBoard.setOOTDShoes(ootdShoes);
				ootdBoard.setOOTDEtc(ootdEtc);
				ootdBoard.setOOTDContents(ootdContents);

				System.out.println( "**ootdBoard " + ootdBoard);
				
				//업로드한 파일처리 
				if( multiReq.getFile("upFile1") !=null ) {
					OotdAttachment ootdAttach = new OotdAttachment();
					ootdAttach.setOriginalFilename(multiReq.getOriginalFileName("upFile1"));
					ootdAttach.setRenamedFilename( multiReq.getFilesystemName("upFile1")); 		// 2. 파일명 변경 (original -> renamed )?
					ootdBoard.addAttachment(ootdAttach);
				}
				
				
//  이거 내가한거  	int result = boardService.insertBoardContent(title, memberId, content);
//  - 쿼리 정하기 - dml - insert into board values(여기 sequence 맞는지확인하기 ,?,?,?,default,default); 
//insert into OOTD_board values( seq_board_no.nextval, 'tigerhj','S1','TIGER의OOTD','오늘의ootdㅎ',default,default,'나이키','나이키','나이키','없음');

		//2. 업무로직 - 쿼리 insertBoard = insert into board (no, title, writer, content) values (seq_board_no.nextval, ?, ?, ?)
			    	int result = ootdBoardService.insertOotdBoard(ootdBoard); // board에 싹다넣어서 서비스요청 코드는 이거 하나임 ★★★★★내가한방식은안돼 
			    	System.out.println( "성공 ??? " + result );
		    	//3.리다이렉트
					request.getSession().setAttribute("msg", "게시글을 성공적으로 등록했습니다😊" );
			    	response.sendRedirect(request.getContextPath()+"/ootd/newOotdWholeList");

				
						
				}catch( Exception e) {
					System.out.println("오류발생");
					
					e.printStackTrace();
					request.getSession().setAttribute("msg", "게시글 등록중 오류가 발생했습니다." );
					response.sendRedirect(request.getContextPath()+"/ootd/newOotdWholeList");
				}

			}
			
			

}
