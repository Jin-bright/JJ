package com.sh.obtg.ootd.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sh.obtg.ootd.model.dto.OotdAttachment;
import com.sh.obtg.ootd.model.service.OotdBoardService;

/**
 * Servlet implementation class OotdMorePageServlet
 */
@WebServlet("/ootd/ootdMoreList")
public class OotdMorePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OotdBoardService ootdBoardService = new OotdBoardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //1 사용자입력값 - 없음 
		int page = Integer.parseInt( request.getParameter("page")); //1페이지, 2페이지, 3페이지
		int limit = 5;
		int start = (page-1) * limit + 1;    // 스타트 페이지 : 1 2345  6    11  이걸 서블릿에서 어떻게 구해 ? 
		int end  = page * limit;	
	
		Map<String, Integer> param = new HashMap<>(); //무슨맵이지 ? 넣을때 타입이  얘이름은 string  int 
		param.put("start", start);
		param.put("end", end);
		
		// 사진 어떻게 꺼내지 ?????
//selectPhotoList = select e.* from ( select row_number() over(order by no desc ) rnum, p.*  from photo  p) e where rnum between ? and ?		
//쿼리 : select e.* from ( select  row_number() over(order by attach_no desc ) rnum, p.* from OOTD_attachment  p) e 		
	//★일단ootd 사진만 가져오기 
		List<OotdAttachment> ootdAttachments = ootdBoardService.selectOotdList(param);
		System.out.println("[ootdAttachments 출력 ] " + ootdAttachments );
	
		//request.getRequestDispatcher("/WEB-INF/views/ootd/ootdList.jsp")
		//.forward(request, response); // 와이거 왜맨날까먹지? 이거안쓰면 페이지 연결안돼 ^^^^^ 

		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(ootdAttachments, response.getWriter());
	

	
	}

}
