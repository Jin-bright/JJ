package com.sh.obtg.ootd.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sh.obtg.ootd.model.service.OotdBoardService;

/**
 * Servlet implementation class NewOotdWholeLike
 */
@WebServlet("/ootd/newOotdWholeLike")
public class NewOotdWholeLike extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OotdBoardService ootdBoardService = new OotdBoardService();    
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("게시판 번호 = " + no);
		
		int likes = ootdBoardService.selectOotdWholeLikes(no);
		System.out.println("총likes : " + likes );

		// 3. 응답처리
		new Gson().toJson(likes, response.getWriter());
	}

	

}
