package com.sh.obtg.member.model.dto;

import java.sql.Date;
import java.sql.Timestamp;


public class Member {
	
	private String memberId;
	private String style;
	private String name;
	private String password;
	private String email;
	private String phone;
	private Date birthday;
	private Timestamp enrollDate;
	private MemberRole memberRole;
	private String nickname;
	private Gender gender;
	private String introduce;
	private String original;
	private String renamed;
	
	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Member(String memberId, String style, String name, String password, String email, String phone,
			Date birthday, Timestamp enrollDate, MemberRole memberRole, String nickname, Gender gender,
			String introduce, String original, String renamed) {
		super();
		this.memberId = memberId;
		this.style = style;
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.birthday = birthday;
		this.enrollDate = enrollDate;
		this.memberRole = memberRole;
		this.nickname = nickname;
		this.gender = gender;
		this.introduce = introduce;
		this.original = original;
		this.renamed = renamed;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Timestamp getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Timestamp enrollDate) {
		this.enrollDate = enrollDate;
	}
	public MemberRole getMemberRole() {
		return memberRole;
	}
	public void setMemberRole(MemberRole memberRole) {
		this.memberRole = memberRole;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getOriginal() {
		return original;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	public String getRenamed() {
		return renamed;
	}
	public void setRenamed(String renamed) {
		this.renamed = renamed;
	}
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", style=" + style + ", name=" + name + ", password=" + password
				+ ", email=" + email + ", phone=" + phone + ", birthday=" + birthday + ", enrollDate=" + enrollDate
				+ ", memberRole=" + memberRole + ", nickname=" + nickname + ", gender=" + gender + ", introduce="
				+ introduce + ", original=" + original + ", renamed=" + renamed + "]";
	}
	
	
	
}
