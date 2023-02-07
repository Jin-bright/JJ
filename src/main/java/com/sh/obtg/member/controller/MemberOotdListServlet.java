package com.sh.obtg.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.obtg.common.HelloMvcUtils;
import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.member.model.dto.MyPost;
import com.sh.obtg.member.model.service.MemberService;
import com.sh.obtg.ootd.model.dto.OotdBoard;

/**
 * Servlet implementation class MemberBoardListServlet
 */
@WebServlet("/member/memberOotdList")
public class MemberOotdListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try { 
			HttpSession session = request.getSession();
			Member loginMember = (Member)session.getAttribute("loginMember");
			String memberId = loginMember.getMemberId();
			
			List<MyPost> ootdBoardList = memberService.selectMyOotdPost(memberId);
		 	System.out.println(ootdBoardList);
			
//			int totalCount = memberService.getMyOotdPostCnt(memberId);
//			System.out.println("> 내가 쓴 게시글 수 : " +  totalCount);

//			String url = request.getRequestURI();
//			String pagebar = HelloMvcUtils.getPagebar(page, limit, totalCount, url);
//			System.out.println("> pagebar" + pagebar);
			
//			request.setAttribute("pagebar", pagebar);
			request.setAttribute("ootdBoardList", ootdBoardList);
				
			request.getRequestDispatcher("/WEB-INF/views/member/memberOotdList.jsp")
				.forward(request, response);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
				
	}

}
