package com.sh.obtg.member.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.sh.obtg.common.HelloMvcFileRenamePolicy;
import com.sh.obtg.common.HelloMvcUtils;
import com.sh.obtg.member.model.dto.Gender;
import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.member.model.dto.Style;
import com.sh.obtg.member.model.service.MemberService;

@WebServlet("/member/memberEnroll")
public class MemberEnrollServlet extends HttpServlet {
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
		try {
			// MultipartRequest 객체 생성
			String saveDircetory = getServletContext().getRealPath("/upload/profile");
			int maxPostSize = 10 * 1024 * 1024;
			String encoding = "utf-8";
			
			FileRenamePolicy  policy = new HelloMvcFileRenamePolicy();
			MultipartRequest multiReq = new MultipartRequest(request, saveDircetory, maxPostSize, encoding, policy);
			
			// 사용자 입력값처리
			String memberId = multiReq.getParameter("memberId");
			String password = HelloMvcUtils.getEncryptedPassword(multiReq.getParameter("pwd"), memberId);
			String email = multiReq.getParameter("email");
			String phone = multiReq.getParameter("phone");
			String[] _style = multiReq.getParameterValues("style");
			String name = multiReq.getParameter("memberName");
			String _birth = multiReq.getParameter("birth");
			String _gender = multiReq.getParameter("gender");
			String original = multiReq.getOriginalFileName("profile");
			String renamed = multiReq.getFilesystemName("profile");
			String nickName = multiReq.getParameter("nickName");
			String introduce = multiReq.getParameter("introduce");
			
			// 후처리
			Date birthday = !"".equals(_birth) ? Date.valueOf(_birth) : null;
			Gender gender = _gender != null ? Gender.valueOf(_gender) : null;
			String style = _style != null ? String.join(",", _style) : null;
			
			Member member = new Member(memberId, style, name, password, email, phone, birthday, null, null, 
										nickName, gender, introduce, original, renamed);
			
			int result = memberService.insertMember(member);
			System.out.println("회원가입" + (result > 0 ? "성공!" : "실패ㅠ"));
			
			// 리다이렉트
			// 이거 왜.. session으로 하면 되고 request로 하면 안되는걸깜..?
			request.getSession().setAttribute("msg", "OBTG의 회원이 되신걸 환영합니다!");
			response.sendRedirect(request.getContextPath() + "/");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("msg", "회원가입오류");
			response.sendRedirect(request.getContextPath() + "/");
		}
		
	}

}
