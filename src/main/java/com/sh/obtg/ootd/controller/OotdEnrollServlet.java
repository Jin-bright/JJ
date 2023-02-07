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
@WebServlet("/ootd/ootdEnroll")
public class OotdEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OotdBoardService ootdBoardService = new OotdBoardService();
	/**
	 * 혜진 - ootd 글쓰기 폼 서블렛 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/ootd/ootdEnroll.jsp")
		.forward(request, response);	
	}

	/**
	 * 파일업로드가 포함된 post 요청 
	 *  1. 서버컴퓨터에 파일저장 -> multipartRequest가 해줌 
	 *  2. db에 게시판/첨부파일 정보를 저장한다 -> 이거는  우리가 직접해야됨 
	 *  -- MultiPartRequest 객체를 사용하면 기존 request에서는 값을 더이상 꺼낼수가 없다 (request사용불가 ) 그래서 동일하게 바꿔줘야됨 
	 *  -- 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. MultipartRequest객체 생성 - 요청메세지에서 파일을 읽어서(=input) 서버컴퓨터에서 저장 (=output) 까지 처리해준다 
		// -- (HttpServletRequest arg0, String arg1, int arg2, String arg3, FileRenamePolicy arg4) throws IOException 
		// 1. HttpServletRequest자리 / 2. String saveDirectory(실제저장할파일경로)/ 3.업로드할수있는파일최대크기 (꼭정해야됨) 일반파일은 10mb정도/ 4.인코딩(utf-8) / 5.파일이름정책객체? - 중복파일이 있는경우 어떻게할거냐  

			try {
				String saveDirectory = getServletContext().getRealPath("/uploadootds/ootd"); //application 객체 반환  //  / <-- webroot를 가리킨다
				System.out.println("saveDirectory : " + saveDirectory  );
				
				int maxPostSize = 10*1024*1024;  //바이트단위로 줘야됨 (1kb = 1024byte  1mb - 1024*1kb ? )  
				String encoding = "utf-8";
				
				FileRenamePolicy policy = new OotdFileRenamePolicy(); //년월일_시분초밀리초_난수.tx  이렇게 만들거임 
				//중복파일이 있는 경우, abc1.txt, abc2.txt 이런씩으로 네이밍을 다르게해줌 
				// 이자리에 우리꺼를 써야된다 ? (파일명 변경하려면 )
				
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
//			    	response.sendRedirect(request.getContextPath()+"/ootd/boardView?no=" + board.getNo());
			    	response.sendRedirect(request.getContextPath()+"/ootd/ootdWholeList");

				
				
						
				}catch( Exception e) {
					System.out.println("오류발생");
					
					e.printStackTrace();
					request.getSession().setAttribute("msg", "게시글 등록중 오류가 발생했습니다." );
					response.sendRedirect(request.getContextPath()+"/ootd/ootdWholeList");
				}
			    // 리다이렉트는 : board/boardList로 가게 -  메세지는 너무 뻔한경우는 걍 쓰지마 (왜냐면 이 글은 제일 최신으로 등록되니까 바로 확인가능 )

			}
			
			

}
