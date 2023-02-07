package com.sh.obtg.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.member.model.dto.MemberRole;


/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter({
	"/admin/*", "/column/columnEnroll", "/column/columnUpdate", "/column/columnDelete",
	"/faq/faqCommentEnroll", "/faq/faqCommentDelete", "/report/reportUpdate",
	"/report/reportList", "/report/reportUpdate"
})
public class AdminFilter implements Filter {

	/**
	 * 관리자가 아닌 부정요청에 대한 처리
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		HttpSession session = httpReq.getSession();

		Member loginMember = ((Member) session.getAttribute("loginMember"));
		// System.out.println("[관리자 권한 페이지 요청 @AdminFilter]");

		if (loginMember == null || loginMember.getMemberRole() != MemberRole.A) {
			session.setAttribute("msg", "관리자만 사용가능합니다.");
			httpRes.sendRedirect(httpReq.getContextPath() + "/");
			return;
		}
		chain.doFilter(request, response);
	}

}
