package com.sh.obtg.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.obtg.member.model.dto.Member;



/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter({ 
	"/member/memberDelete", "/member/memberOotdLike", "/member/memberOotdList", 
	"/member/memberShareLike", "/member/memberShareList", "/member/updatePassword",
	"/member/memberUpdate", "/member/memberView",
	"/message/*", "/chat/MessageMain","/ootd/newOotdDelete", "/ootd/ootdCommentEnroll", "/ootd/newOotdView/*",
	"/ootd/ootdCommentDelete", "/ootd/OotdLike", "/ootd/newOotdEnroll", "/ootd/newOotdUpdate",
	"/share/newShareWholeList","/share/shareStateUpdate", "/share/newShareEnroll", "/share/newShareDelete",
	"/faq/faqEnroll", "/faq/faqUpdate", "/faq/faqDelete", 
	
})
public class LoginFilter extends HttpFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		
		HttpSession session = httpReq.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		if(loginMember == null) {
			session.setAttribute("msg", "로그인 후 이용하실 수 있습니다.");
			httpRes.sendRedirect(httpReq.getContextPath() + "/");
			return; // 조기리턴. 하위코드 실행 안함.
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
}
