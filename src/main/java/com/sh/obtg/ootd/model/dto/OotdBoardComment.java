package com.sh.obtg.ootd.model.dto;

import java.sql.Date;

public class OotdBoardComment {

	private int cmtNo;
	private int boardNo;
	private String memberId;
	private int cmtLevel; // 댓글은 1 // 대댓글은 2임 	
	private String cmtContent;
	private Date cmtRegDate; 
	private int commentRef; // 참조댓글번호임 (대댓글인경우만 해당) 

	//기본생성자
	public OotdBoardComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//파라미터생성자
	public OotdBoardComment(int cmtNo, int boardNo, String memberId, int cmtLevel, String cmtContent, Date cmtRegDate,
			int commentRef) {
		super();
		this.cmtNo = cmtNo;
		this.boardNo = boardNo;
		this.memberId = memberId;
		this.cmtLevel = cmtLevel;
		this.cmtContent = cmtContent;
		this.cmtRegDate = cmtRegDate;
		this.commentRef = commentRef;
	}
	
	//get set 
	public int getCmtNo() {
		return cmtNo;
	}
	public void setCmtNo(int cmtNo) {
		this.cmtNo = cmtNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getCmtLevel() {
		return cmtLevel;
	}
	public void setCmtLevel(int cmtLevel) {
		this.cmtLevel = cmtLevel;
	}
	public String getCmtContent() {
		return cmtContent;
	}
	public void setCmtContent(String cmtContent) {
		this.cmtContent = cmtContent;
	}
	public Date getCmtRegDate() {
		return cmtRegDate;
	}
	public void setCmtRegDate(Date cmtRegDate) {
		this.cmtRegDate = cmtRegDate;
	}
	public int getCommentRef() {
		return commentRef;
	}
	public void setCommentRef(int commentRef) {
		this.commentRef = commentRef;
	}
	
	
	@Override
	public String toString() {
		return "OotdBoardComment [cmtNo=" + cmtNo + ", boardNo=" + boardNo + ", memberId=" + memberId + ", cmtLevel="
				+ cmtLevel + ", cmtContent=" + cmtContent + ", cmtRegDate=" + cmtRegDate + ", commentRef=" + commentRef
				+ "]";
	}
	
	
	
}

/**
CREATE TABLE OOTD_board_comment (
cmt_no	number,
board_no	number,
member_id	varchar2(50)		NOT NULL,
cmt_level	number		default 1,
cmt_content	varchar2(2000)		NULL,
cmt_reg_date	Date		default sysdate,
comment_ref	number,
CONSTRAINT PK_OOTD_BOARD_COMMENT PRIMARY KEY (cmt_no),
CONSTRAINT FK_OOTD_board_TO_OOTD_board_comment_1 FOREIGN KEY (board_no) REFERENCES OOTD_board (OOTD_no),
CONSTRAINT FK_Member_TO_OOTD_board_comment_1 FOREIGN KEY (member_id) REFERENCES Member (member_id),
CONSTRAINT FK_OOTD_BORAD_comment_ref foreign key(comment_ref) references OOTD_board_comment(cmt_no)
);
**/
