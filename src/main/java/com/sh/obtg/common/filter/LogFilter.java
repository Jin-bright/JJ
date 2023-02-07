package com.sh.obtg.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Filter
 * - Filter인터페이스를 구현
 * - 특정 url별로 처리되도록 등록(web.xml) -> @WebFilter사용하면 자동등록
 * 
 * - servlet/jsp에 대해서 전처리/후처리 작업 가능
 * - 전처리 : 인증여부검사, 권한검사, 인코딩처리, ...
 * - 후처리 : 파일압축.
 * 
 * - 여러개의 필터를 등록하는 경우, FilterChain으로 관리되어 순차적으로 호출.
 * 
 * - doFilter 오버라이딩시 FilterChain#doFilter를 반드시 호출해야 다음 Filter 또는 Servlet으로 넘어간다.
 * 
 * - 필터처리순서 우선순위
 *  1. web.xml에 작성된 순서
 *  2. @WebFilter등록시 Filter이름순
 *  3. url-pattern작성이 servlet이름작성보다 우선순위 높음.
 * 
 */
public class LogFilter implements Filter {

	/**
	 * Servlet#doGet, Servlet#doPost의 매개변수는 HttpServletRequest, HttpServletResponse 타입
	 * Filter#doFilter의 매개변수는 ServletRequest, ServletResponse 타입
	 * 
	 * tomcat이 만든 request, response객체의 부모타입
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request; // upcasting | downcasting
		
		// 전처리
		// 요청주소 - /mvc/member/memberView
		String uri = httpReq.getRequestURI();
		String method = httpReq.getMethod();
		
		System.out.println("═════════•°• ⚠ •°•═════════");
		System.out.println(method + " " + uri);
		System.out.println("____________________________");
		
		// FilterChain의 다음 Filter호출 또는 Servlet 호출
		chain.doFilter(request, response); 
		
		// 후처리
		System.out.println("───── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────");
		System.out.println();
		System.out.println();
		
	}

}
