package com.sh.obtg.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.obtg.member.model.dto.Style;
import com.sh.obtg.member.model.service.MemberService;

@WebServlet("/member/memberEnroll")
public class MemberEnroll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
       
	/**
	 * 회원가입 폼 doGet요청
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// style 테이블 조회
			List<Style> styleList = memberService.selectStyleList();
			System.out.println(styleList);
			
			request.setAttribute("styleList", styleList);
			request.getRequestDispatcher("/WEB-INF/views/member/memberEnroll.jsp")
				.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 회원정보 입력 doPost요청
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("여기옴?");
	}

}
