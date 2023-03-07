
package com.sh.obtg.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Map;

import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;

//import com.google.gson.Gson;
//import com.sh.obtg.obtg.obtg.mvc.ws.endpoint.HelloWebSocket;

public class HelloMvcUtils {

	/**
	 * 단방향 암호화 - 복호화 불가능
	 * 
	 * 1. 암호화 처리
	 * 2. 인코딩 Base64Encoder사용
	 * 
	 * salt 비밀번호 암호화에 추가적으로 적용해서 사용자별로 다른 결과값을 얻도록 함.
	 * 
	 * @param rawPassword
	 * @return
	 */
	public static String getEncryptedPassword(String rawPassword, String salt) {
		String encryptedPassword = null;
		
		try {
			// 1. 암호화 MessageDigest
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] _salt = salt.getBytes("utf-8");
			byte[] _rawPassword = rawPassword.getBytes("utf-8");
			md.update(_salt);
			byte[] _encryptedPassword = md.digest(_rawPassword);
			
			System.out.println(new String(_encryptedPassword));
			
			// 2. 인코딩 Base64Encoder (영문자, 숫자, +, /) padding =
			Encoder encoder = Base64.getEncoder();
			encryptedPassword = encoder.encodeToString(_encryptedPassword);
			
			System.out.println(encryptedPassword);
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encryptedPassword;
	}

	/**
	 * totalPage 전체 몇 페이지
	 * pagebarSize 페이지바의 링크 몇개?
	 * pageNo 증감변수
	 * pageStart ~ pageEnd 증감변수 범위
	 * 
	 * @param page
	 * @param limit
	 * @param totalCount
	 * @param url
	 * @return
	 */
	public static String  getPagebar(int page, int limit, int totalCount, String url) {
		StringBuilder pagebar = new StringBuilder();
		url += "?page="; // /mvc/admin/memberList?page=
		
		// 전체페이지수
		int totalPage = (int) Math.ceil((double) totalCount / limit); // 11.4  -> 12.0 -> 12
		
		int pagebarSize = 5;
		// 페이지바의 시작넘버
		// 1 2 3 4 5 		-> 1
		// 6 7 8 9 10 		-> 6
		// 11 12 13 14 15 	-> 11
		int pageStart = ((page - 1) / pagebarSize) * pagebarSize + 1; // page, pagebarSize
		int pageEnd = pageStart + pagebarSize - 1; // 1 - 5, 6 - 10
		
		int pageNo = pageStart;
		
		// 1. 이전 영역
		if(pageNo == 1) {
			// 1 2 3 4 5 이므로 이동할 이전페이지 없음.
		//	pagebar.append("<span> 이전 </span>\n"); // 현재페이지가 6인 경우 /mvc/admin/memberList?page=5

		}
		else {
			pagebar.append("<a href='" + url + (pageNo - 1) + "'>이전</a>\n"); // 현재페이지가 6인 경우 /mvc/admin/memberList?page=5
		}
		
		// 2. pageNo 영역
		while(pageNo <= pageEnd && pageNo <= totalPage) {
			if(pageNo == page) {
				// 현재페이지 링크인 경우
				pagebar.append("<span class='cPage'>" + pageNo + "</span>\n");
			}
			else {
				// 현재페이지 링크가 아닌 경우
				pagebar.append("<a href='" + url + pageNo + "'>" + pageNo + "</a>\n");
			}
			pageNo++;
		}
		
		// 3. 다음 영역
		if(pageNo > totalPage) {
			// 마지막페이지이후는 다음 버튼이 필요 없음.
		}
		else {
			pagebar.append("<a href='" + url + pageNo + "'>다음<a/>\n");
		}
		
		
		return pagebar.toString();
	}

	public static String convertLineFeedToBr(String str) {
		return str.replaceAll("\n", "<br/>");
	}

	/**
	 * XSS 공격대비 방어
	 * - cross-site scripting 공격
	 * - 악성스크립트를 작성하고, 열람하는 사용자로 하여금, 자신들이 만든 가짜페이지로 이동시키거나, 브라우져내의 쿠키등 개인정보를 탈취하는 공격
	 * 
	 * @param str
	 * @return
	 */
	public static String escapeHtml(String str) {
		return str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}

	/**
	 * 페이지바 오버로딩
	 * @param page
	 * @param limit
	 * @param totalCount
	 * @param url
	 * @param status
	 * @return
	 */
	public static String getPagebar(int page, int limit, int totalCount, String url, String status) {
		StringBuilder pagebar = new StringBuilder();
		if(status == null) {
			url += "?page=";
		}
		else {
			url += "&page=";
		}
		
		int totalPage = (int) Math.ceil((double) totalCount / limit); // 11.4  -> 12.0 -> 12
		
		int pagebarSize = 5;
		int pageStart = ((page - 1) / pagebarSize) * pagebarSize + 1; // page, pagebarSize
		int pageEnd = pageStart + pagebarSize - 1; // 1 - 5, 6 - 10
		
		int pageNo = pageStart;
		
		// 1. 이전 영역
		if(pageNo == 1) {
			// 1 2 3 4 5 이므로 이동할 이전페이지 없음.
		//	pagebar.append("<span> 이전 </span>\n"); // 현재페이지가 6인 경우 /mvc/admin/memberList?page=5

		}
		else {
			pagebar.append("<a href='" + url + (pageNo - 1) + "'>이전</a>\n");
		}
		
		// 2. pageNo 영역
		while(pageNo <= pageEnd && pageNo <= totalPage) {
			if(pageNo == page) {
				// 현재페이지 링크인 경우
				pagebar.append("<span class='cPage'>" + pageNo + "</span>\n");
			}
			else {
				// 현재페이지 링크가 아닌 경우
				pagebar.append("<a href='" + url + pageNo + "'>" + pageNo + "</a>\n");
			}
			pageNo++;
		}
		
		// 3. 다음 영역
		if(pageNo > totalPage) {
			// 마지막페이지이후는 다음 버튼이 필요 없음.
		}
		else {
			pagebar.append("<a href='" + url + pageNo + "'>다음<a/>\n");
		}
		
		return pagebar.toString();
	}

}
