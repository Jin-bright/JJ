package com.sh.obtg.member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.obtg.member.model.dto.Gender;
import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.member.model.service.MemberService;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		try {
			
			// 2. 사용자입력값 -> member
			String memberId = request.getParameter("memberId");
			String name = request.getParameter("name");
			String nickname = request.getParameter("nickname"); 
			String _gender = request.getParameter("gender");
			String _birthday = request.getParameter("birthday");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String introduce = request.getParameter("introduce");
			String original = request.getParameter("original");
			String renamed = request.getParameter("renamed");
			String[] _style = request.getParameterValues("style");
			
			// 후처리
			Date birthday = !"".equals(_birthday) ? Date.valueOf(_birthday) : null;
			Gender gender = _gender != null ? Gender.valueOf(_gender) : null;
			String style = _style != null ? String.join(",", _style) : null;
			
			Member member = new Member(memberId, style, name, null, email, phone, birthday, null, null, nickname, gender, introduce, original, renamed);
			System.out.println(member);
			
			// 3. 업무로직 - db update
			int result = memberService.updateMember(member);
			System.out.println("result = " + result);
			
			
			
			if(result > 0) {
				session.setAttribute("msg", "회원 정보를 성공적으로 수정했습니다.");
				
				session.setAttribute("loginMember", memberService.selectOneMember(memberId));
				
			}
			
		} catch (Exception e) {
			session.setAttribute("msg", "회원 정보 수정중 오류가 발생했습니다.");
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/member/memberView");
		
		
	}

}
