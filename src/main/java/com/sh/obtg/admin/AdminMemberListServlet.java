package com.sh.obtg.admin;

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

@WebServlet("/admin/memberList")
public class AdminMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	/**
	 * 전체 사용자 정보 요청
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		final int limit = 10;
		int page = 1; // 기본값
		try {
			page = Integer.parseInt(request.getParameter("page"));			
		} catch (NumberFormatException e) {
			// NumberFormatException이 발생했지만, 기본값은 1로 처리.
		}
		Map<String, Object> param = new HashMap<>();
		param.put("page", page);
		param.put("limit", limit);
		System.out.println("param = " + param);
		
		// 2. 업무로직
		// a. content 영역
		List<Member> members = memberService.selectAllMember(param);
		System.out.println(members);
		// b. pagebar 영역
		int totalCount = memberService.selectTotalCount();
		System.out.println(totalCount);
		String url = request.getRequestURI(); // /mvc/admin/memberList
		String pagebar = HelloMvcUtils.getPagebar(page, limit, totalCount, url);
		System.out.println(pagebar);
		
		// 3. view단 처리
		request.setAttribute("members", members);
		request.setAttribute("pagebar", pagebar);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp")
			.forward(request, response);
		
	}

}
