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
import com.sh.obtg.member.model.dto.MyPosts;
import com.sh.obtg.member.model.service.MemberService;
import com.sh.obtg.ootd.model.dto.OotdBoard;
import com.sh.obtg.share.model.dto.ShareBoard;

/**
 * Servlet implementation class MemberShareListServlet
 */
@WebServlet("/member/memberShareList")
public class MemberShareListServlet extends HttpServlet {
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
			
			List<MyPosts> shareBoardList = memberService.selectMySharePost(memberId);
			
			request.setAttribute("shareBoardList", shareBoardList);
				
			request.getRequestDispatcher("/WEB-INF/views/member/memberShareList.jsp")
				.forward(request, response);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
