package com.sh.obtg.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.obtg.admin.model.service.AdminService;
import com.sh.obtg.common.HelloMvcUtils;

@WebServlet("/admin/blackList")
public class AdminBlackListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final int numPerPage = 5;
		int Page = 1;
		try {
			Page = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			
		}
		
		int start = (Page -1)* numPerPage + 1;
		int end = Page * numPerPage;
		List<String> list = adminService.selectBlackList(start, end);
		System.out.println(list);
		int totalContents = adminService.selectBlackListCount();
		System.out.println("totalContents@servlet = " + totalContents);
		
		//페이지바 작업
		String url = request.getRequestURI();
		String pageBar = HelloMvcUtils.getPagebar(Page, numPerPage, totalContents, url);
		
		//jsp에 html응답메세지 작성 위임
		request.setAttribute("Page", Page);
		request.setAttribute("pagebar", pageBar);
		System.out.println("pageBar"+ pageBar);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/admin/blackList.jsp").forward(request, response);
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		System.out.println("email@servlet = "+email);
		
		
		HttpSession session = request.getSession();
		int result = 0;
		result = adminService.unbanFromBlackList(email);
		if(result > 0) {
			session.setAttribute("msg", email+"을 블랙리스트에서 해제 하였습니다.");
		}else {
			session.setAttribute("msg", "블랙리스트 해제 실패");
		}
		
		String referer = request.getHeader("Referer");
		response.sendRedirect(referer);
	}

}