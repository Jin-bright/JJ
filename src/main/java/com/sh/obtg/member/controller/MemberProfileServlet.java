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
import com.sh.obtg.member.model.dto.Style;
import com.sh.obtg.member.model.service.MemberService;

@WebServlet("/member/profile")
public class MemberProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * 프로필폼 요청
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 세션의 회원 정보를 업데이트를 위해..
			HttpSession session = request.getSession();
			Member logiMember = (Member)session.getAttribute("loginMember");
			String memberId = logiMember.getMemberId();
			
			Member member = memberService.selectOneMember(memberId);
			session.setAttribute("loginMember", member);
			
			List<Style> styleList = memberService.selectStyleList();
			request.setAttribute("styleList", styleList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/views/member/profile.jsp").forward(request, response);
	}

	/**
	 * 프로필 수정 요청
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자 입력값 처리
			Member member = (Member)request.getSession().getAttribute("loginMember");
			String memberId = member.getMemberId();
			
			String _password = request.getParameter("password");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String name = request.getParameter("name");
			String birthday = request.getParameter("birthday");
			String nickName = request.getParameter("nickName");
			String[] _style = request.getParameterValues("style");
			String introduce = request.getParameter("introduce");
			
			Map<String, String> param = new HashMap<>();
			param.put("memberId", memberId);
			
			// 이게,, 최선일까,,?ㅎ
			if(_password != null) {
				String password = HelloMvcUtils.getEncryptedPassword(_password, memberId);
				param.put("type", "password");
				param.put("keyword", password);
			}
			else if(email != null) {
				param.put("type", "email");
				param.put("keyword", email);
			}
			else if (phone != null) {
				param.put("type", "phone");
				param.put("keyword", phone);
			}
			else if (name != null) {
				param.put("type", "name");
				param.put("keyword", name);
			}
			else if (birthday != null) {
				param.put("type", "birthday");
				param.put("keyword", birthday);
			}
			else if(nickName != null) {
				param.put("type", "nickname");
				param.put("keyword", nickName);
			}
			else if(_style != null) {
				String style = _style != null ? String.join(",", _style) : null;
				param.put("type", "style");
				param.put("keyword", style);
			}
			else if(introduce != null) {
				param.put("type", "introduce");
				param.put("keyword", introduce);
			}
			int result = memberService.updateMember(param);
			
			request.getSession().setAttribute("msg", "회원 정보를 수정했습니다!😆");
			response.sendRedirect(request.getContextPath() + "/member/profile");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
