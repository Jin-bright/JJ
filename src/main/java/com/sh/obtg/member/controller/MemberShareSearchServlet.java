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

@WebServlet("/member/shareSearch")
public class MemberShareSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자 입력값 처리
			Member loginMember = (Member)request.getSession().getAttribute("loginMember");
			String status = null;
			try {
				status = request.getParameter("status");
			} catch (NullPointerException e) {}
			
			final int limit = 8;
			int page = 1;
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {}
			
			String sort = null;
			try {
				sort = request.getParameter("regDate");
			} catch (NullPointerException e) {}
			
			if(sort == null) sort = "desc";
			
			String query = request.getQueryString();
			// &page= 가 반복적으로 나오는걸 방지 - 이렇게 하는게 맞남ㅎ,,
			if(query.contains("&page=" + page)) {
				query = query.replace("&page=" + page, "");
			}
			
			String url = request.getContextPath() + "/member/shareSearch?" + query;
			
			Map<String, Object> param = new HashMap<>();
			param.put("page", page);
			param.put("limit", limit);
			param.put("memberId", loginMember.getMemberId());
			param.put("sort", sort);
			
			// 2. 업무로직
			int totalCount = 0;
			List<Map<String, Object>> shareList = null;
			
			Map<String, String> keyParam = new HashMap<>();
			keyParam.put("memberId", loginMember.getMemberId());
			
			// 거래 상태가 넘어왔다면
			if(status != null) {
				param.put("keyType", "product_status");
				param.put("keyword", status);
				shareList = memberService.searchShareList(param);
				System.out.println("shareList : " + shareList);
				
				keyParam.put("keyType", "product_status");
				keyParam.put("keyword", status);
				totalCount = memberService.myShareTotalCount(keyParam);
			}
			
			// 정렬만 넘어왔을때
//			if(status == null && sort != null) {
//				shareList = memberService.selectMyShare(param);
//			}
			
			String pagebar = HelloMvcUtils.getPagebar(page, limit, totalCount, url, status);
			
			request.setAttribute("shareList", shareList);
			request.setAttribute("pagebar", pagebar);
			
			// 3. jsp 포워딩
			request.getRequestDispatcher("/WEB-INF/views/member/myShareList.jsp")
				.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
