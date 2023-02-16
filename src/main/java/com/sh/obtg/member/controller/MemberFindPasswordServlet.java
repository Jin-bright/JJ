package com.sh.obtg.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sh.obtg.common.HelloMvcUtils;
import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.member.model.service.MemberService;

@WebServlet("/member/findPassword")
public class MemberFindPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * 비밀번호 찾기 폼 doGet요청 || 비밀번호 찾기결과 / 비밀번호 재발급
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 사용자 입력값 처리
			String id = request.getParameter("id");
			String email = request.getParameter("email");
			
			// 비밀번호 찾기 폼을 입력했을 경우
			if(id != null && email != null) {
				Map<String, String> param = new HashMap<>();
				param.put("memberId", id);
				param.put("email", email);
				System.out.println("param : " + param);
				
				// 업무로직
				int member = memberService.findMemebrPwd(param);
				
				Map<String, Object> map = new HashMap<>();
				map.put("member", member);
				
				// 이메일과 아이디가 모두 일치하다면
				if(member == 1) {
					// 임시비밀번호 발급
					String tempPwd = "t";
					for(int i = 0; i < 6; i++) {
						// 0이면 문자를, 1이면 숫자를 입력
						int rmd = (int)Math.floor(Math.random() * 2);					
						if(rmd == 0) tempPwd += (char)(Math.random() * 10 + '0') ;
						else tempPwd += (char)(Math.random() * 26 + 'A');
					}
					System.out.println("임시비번 : " + tempPwd);
					map.put("tempPwd", tempPwd);
					
					// 비밀번호변경
					String password = HelloMvcUtils.getEncryptedPassword(tempPwd, id);
					Member newMember = new Member();
					newMember.setMemberId(id);
					newMember.setPassword(password);
					int result = memberService.updatePassword(newMember);
					System.out.println("비밀번호 변경" + (result > 0 ? "성공!" : "실패ㅠ"));
				}
				
				// 응답요청
				response.setContentType("application/json; charset=utf-8");
				new Gson().toJson(map, response.getWriter());
			}
			else {
				request.getRequestDispatcher("/WEB-INF/views/member/findPassword.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
