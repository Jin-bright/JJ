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
import com.sh.obtg.common.HelloMvcUtils;
import com.sh.obtg.member.model.service.MemberService;

/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet("/member/memberEnroll")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberEnroll.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		  try { 
			  
		  String memberId = request.getParameter("memberId"); 
		  String[] _style = request.getParameterValues("style"); 
		  String name = request.getParameter("name");
		  String password = HelloMvcUtils.getEncryptedPassword(request.getParameter("password"), memberId);
		  String email = request.getParameter("email");
		  String phone = request.getParameter("phone");
		  String _birthday = request.getParameter("birthday");
		  String nickname = request.getParameter("nickname");
		  String _gender = request.getParameter("gender");
		  String introduce = request.getParameter("introduce");
		  String original = request.getParameter("original");
		  String renamed = request.getParameter("renamed");
				  
		// 후처리
		Date birthday = !"".equals(_birthday) ? Date.valueOf(_birthday) : null;
		Gender gender = _gender != null ? Gender.valueOf(_gender) : null;
		String style = _style != null ? String.join(",", _style) : null;
					
		Member member = new Member(memberId, style, name, password, email, phone, birthday, null, null, nickname, gender, introduce, original, renamed);
		System.out.println(member);
					
					
		// 2. 업무로직 - db insert
		int result = memberService.insertMember(member);
		
			if(result > 0) {
				// 회원가입성공 메세지
				session.setAttribute("msg", "회원가입을 축하드립니다.");

			}
		}catch(Exception e) {
			// 회원가입실패 메세지
			session.setAttribute("msg", "회원가입 실패했습니다.");

			// 예외로깅
			e.printStackTrace();
		}
			
			// 4. 리다이렉트 - /mvc/
			response.sendRedirect(request.getContextPath() + "/");
			
		}

}
