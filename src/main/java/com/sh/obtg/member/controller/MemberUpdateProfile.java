package com.sh.obtg.member.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.sh.obtg.common.HelloMvcFileRenamePolicy;
import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.member.model.service.MemberService;

@WebServlet("/member/updateProfile")
public class MemberUpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * 프로필 사진 변경 doPost요청
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// MultipartRequest 객체 생성
			String saveDircetory = getServletContext().getRealPath("/upload/profile");
			int maxPostSize = 10 * 1024 * 1024;
			String encoding = "utf-8";
			
			FileRenamePolicy  policy = new HelloMvcFileRenamePolicy();
			MultipartRequest multiReq = new MultipartRequest(request, saveDircetory, maxPostSize, encoding, policy);
			
			// 1. 사용자 입력값 처리
			Member member = (Member)request.getSession().getAttribute("loginMember");
			String memberId = member.getMemberId();
			String original = multiReq.getOriginalFileName("profile");
			String renamed = multiReq.getFilesystemName("profile");
			
			Map<String, String> param = new HashMap<>();
			param.put("memberId", memberId);
			param.put("original", original);
			param.put("renamed", renamed);
			
			int result = memberService.updateProfile(param);
			
			if(result > 0) {
				System.out.println("변경완료!");
				
				if(member.getRenamed() != null) {
					File deFile = new File(saveDircetory, member.getRenamed());
					deFile.delete();
				}
			}
			
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(result, response.getWriter());		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
