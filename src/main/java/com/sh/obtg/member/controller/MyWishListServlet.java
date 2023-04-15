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

import com.sh.obtg.common.HelloMvcUtils;
import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.member.model.service.MemberService;

@WebServlet("/member/myWishList")
public class MyWishListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * 마이페이지 관심상품 목록 조회
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 사용자 입력값
			Member loginMember = (Member)request.getSession().getAttribute("loginMember");
			String memberId = loginMember.getMemberId();
			
			final int limit = 8;
			int page = 1; // 기본값
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {}
			
			Map<String, Object> param = new HashMap<>();
			param.put("page", page);
			param.put("limit", limit);
			param.put("memberId", memberId);
			System.out.println("param = " + param);
					
			// 업무로직
			List<Map<String, Object>> wishList = memberService.selectWishList(param);
			System.out.println("wishList : " + wishList);
			
			int totalCount = memberService.myWishListTotalCount(memberId);
			System.out.println("관심 총 개수 : " + totalCount);
			
			String url = request.getRequestURI();
			String pagebar = HelloMvcUtils.getPagebar(page, limit, totalCount, url);
			
			// forward
			request.setAttribute("wishList", wishList);
			request.setAttribute("pagebar", pagebar);
			request.getRequestDispatcher("/WEB-INF/views/member/myWishList.jsp")
				.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
