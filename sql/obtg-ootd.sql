alter session set "_oracle_script"  = true; -- c## 접두어 없이 계정 생성가능하도록 설정

create user obtg
identified by obtg
default tablespace users;

alter user obtg quota  unlimited on users;

grant connect, resource to obtg;


---------------------------------------------------------------------------------
---------------------------------------------------------------------------------

select * from member;

CREATE TABLE Member (
	member_id	varchar2(50),
	style	varchar2(20)		NOT NULL,
	name	varchar2(200)		NULL,
	password	varchar2(50)		not NULL,
	email	varchar2(200)		not NULL,
	phone	varchar2(20)		not NULL,
	birthday	date		NULL,
	enroll_date	date		default sysdate,
	member_role	char(1)		default 'U' not null,
	nickname	varchar2(50)		NULL,
	gender	char(5)	,
	introduce	varchar2(500)		NULL,
	original	varchar2(100),
	renamed	varchar2(100),
    constraint pk_member_id primary key(member_id),
    constraint ck_member_role check(member_role in ('U', 'A')),
    constraint ck_member_gender check(gender in ('M', 'F')),
    constraint uq_member_email unique(email)
--    constraint fk_fashionstyle FOREIGN KEY (style) REFERENCES fashionstyle (style_no) 이 제약조건안씀(by혜진.0118)
-- ALTER TABLE member  ADD constraint fk_fashionstyle FOREIGN KEY (style) REFERENCES fashionstyle (style);  이 제약조건으로 추가(by혜진.0118)
);



ALTER TABLE Member ADD CONSTRAINT PK_MEMBER PRIMARY KEY (
	member_id,
	style
);

ALTER TABLE Member ADD constraint uq_nickname unique(nickname);

-------------------------------------------------
-------------------------------------------------
-------------------------------------------------
-------------------------------------------------
--CREATE TABLE fashionstyle (
--	style_no	varchar2(10)		NOT NULL,
--	style	varchar2(20)		NULL,
--   CONSTRAINT PK_STYLE_STYLE PRIMARY KEY (style_no)
--);
-- ALTER TABLE fashionstyle  ADD constraint uq_fashionstyle  unique(style); <--unique로 바꿈 (by혜진.0118) / 밑에 정보 넣은  내역 추가 
--insert into fashionstyle values ('1','러블리'); insert into fashionstyle values ('2','댄디');insert into fashionstyle values ('3','포멀');insert into fashionstyle values ('4','스트릿');
--insert into fashionstyle values ('5','걸리쉬'); --insert into fashionstyle values ('6','레트로');insert into fashionstyle values ('7','로맨틱');
--insert into fashionstyle values ('8','시크');insert into fashionstyle values ('9','아메카지');





CREATE TABLE OOTD_board (
	OOTD_no	number,
	OOTD_writer	varchar2(50)		NOT NULL,
	style_no	varchar2(10)		NOT NULL,
	OOTD_title	varchar2(50)		NOT NULL,
	OOTD_contents	varchar2(4000)	NOT NULL,
	OOTD_read_count	number		default 0,
	OOTD_reg_date	date		default sysdate,
	OOTD_top	varchar2(50)		NULL,
	OOTD_bottom	varchar2(50)		NULL,
	OOTD_shoes	varchar2(50)		NULL,
	OOTD_etc	varchar2(50)		NULL,
    CONSTRAINT PK_OOTD_BOARD PRIMARY KEY (OOTD_no),
    CONSTRAINT FK_OOTD_board_writer FOREIGN KEY (ootd_writer) REFERENCES Member (member_id) on delete set null,
    CONSTRAINT FK_fashionstyle_TO_OOTD_board_1 FOREIGN KEY (style_no) REFERENCES fashionstyle (style_no) on delete set null
);

select * from ootd_board;


create sequence seq_board_no;

select * from OOTD_board;

CREATE TABLE OOTD_attachment (
	attach_no	number,
	board_no	number		NOT NULL,
	attach_style_no	varchar2(10)		NOT NULL,
	original_filename	varchar2(255)		NOT NULL, -- 프로필용
	renamed_filename	varchar2(255)		NOT NULL, -- 프로필용
	reg_date	Date	default sysdate,
    CONSTRAINT PK_OOTD_ATTACHMENT PRIMARY KEY (attach_no),
    CONSTRAINT FK_OOTD_board_OOTD_attachment_1 FOREIGN KEY (board_no) REFERENCES OOTD_board (OOTD_no) on delete cascade
);

--CONSTRAINT FK_OOTD_board_OOTD_attachment_2 FOREIGN KEY (attach_style_no) REFERENCES OOTD_board (style_no) on delete set null

ALTER TABLE OOTD_attachment ADD CONSTRAINT FK_OOTD_board_OOTD_attachment_2 FOREIGN KEY (attach_style_no) REFERENCES fashionstyle (style_no);

create sequence seq_ootd_attachment_no;


CREATE TABLE SHARE_board (
	SHARE_no	number,
	member_id	varchar2(50)		NOT NULL,
	SAHRE_TITLE	varchar2(50)		NOT NULL,
	SAHRE_CONTENT	varchar2(4000)	NOT NULL,
	SAHRE_READ_COUNT	number	default 0,
	SAHRE_REG_DATE	Date		default sysdate,
	SHARE_BUY_DATE	Date		NULL,
	SHARE_PRODUCT_STATUS	varchar2(50)		NULL,
	SHARE_CATEGORY	varchar2(50)		NULL,
	SHARE_STATE	varchar2(50)		NULL,
    style	varchar2(20)		NOT NULL,
    CONSTRAINT PK_SHARE_BOARD PRIMARY KEY (SHARE_no),
    CONSTRAINT FK_Member_TO_SHARE_board_1 FOREIGN KEY (member_id) REFERENCES Member (member_id) on delete set null,
    CONSTRAINT FK_fashionstyle_TO_SHARE_board_1 FOREIGN KEY (style) REFERENCES fashionstyle (style_no) on delete set null
);


ALTER TABLE SHARE_board ADD constraint uq_share_member_id unique(member_id);

create sequence seq_SHARE_board_no;
-- unique 제약조건 해제 0126 ( 사유: 중복된 id로 글 못씀 ) 
-- uq 제약조건 해제완료   ALTER TABLE SHARE_board  DROP CONSTRAINT uq_share_member_id;








CREATE TABLE SHARE_attachment (
	attach_no	number		NOT NULL,
	board_no	number		NOT NULL,
	original_filename	varchar2(255)		NOT NULL,
	renamed_filename	varchar2(255)		NOT NULL,
	reg_date	Date		default sysdate,
    CONSTRAINT PK_SHARE_ATTACHMENT PRIMARY KEY (attach_no),
    CONSTRAINT FK_SHARE_board_SHARE_attachment_1 FOREIGN KEY (board_no) REFERENCES SHARE_board (SHARE_no) on delete cascade
);

alter table SHARE_attachment
rename column rename_filename to renamed_filename;

create sequence seq_SHARE_attachment_no;





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

create sequence seq_OOTD_board_comment_no;



-- FAQ 나중에 ....
-------------------------------------------------------
-------------------------------------------------------
--CREATE TABLE FAQ (
--	FAQ_no	number		NOT NULL,
--	member_id	varchar2(50)		NOT NULL,
--	FAQ_title	varchar2(50)		NULL,
--	FAQ_content	varchar2(50)		NULL,
--    CONSTRAINT PK_FAQ PRIMARY KEY (FAQ_no),
--    CONSTRAINT FK_Member_TO_FAQ_1 FOREIGN KEY (member_id) REFERENCES Member (member_id)
--);
-------------------------------------------------------
-------------------------------------------------------


CREATE TABLE Report (
    report_no    number        NOT NULL,
    reported_userId    varchar2(50)        NOT NULL,
    board_no  varchar2(50)       NOT NULL,
    reg_date    date   default sysdate,
    report_status    char(1) default 'X',
    report_reason   char(2)     NOT NULL,
    CONSTRAINT PK_REPORT PRIMARY KEY (report_no),
    CONSTRAINT FK_Member_TO_Report_1 FOREIGN KEY (reported_userId) REFERENCES Member (member_id),
    CONSTRAINT CK_report_reason check (report_reason in ('R1', 'R2', 'R3', 'R4', 'R5' )),
    CONSTRAINT CK_report_status check (report_status in ('O', 'X'))
);

create sequence seq_report_no;


-- 새로 추가한 알림 테이블
create table noti (
    no number,
    receiver varchar2(20),
    message varchar2(500),
    datetime date default sysdate,
    checked char(1) default 'X',
    constraint pk_noti_no primary key(no),
    constraint fk_noti_recevier foreign key (receiver) references member (member_id),
    constraint ck_noti_checked check (checked in ('O', 'X'))
);

create sequence seq_noti_no;



CREATE TABLE SHARE_Likes (
	LIKE_no	number,
	board_no	number		NOT NULL,
	member_id	varchar2(50)		NOT NULL,
    CONSTRAINT PK_SHARE_LIKES PRIMARY KEY (LIKE_no),
    CONSTRAINT FK_SHARE_board_TO_SHARE_Likes_1 FOREIGN KEY (board_no) REFERENCES SHARE_board (SHARE_no),
    CONSTRAINT FK_Member_TO_SHARE_Likes_1 FOREIGN KEY (member_id) REFERENCES Member (member_id)
);

create sequence seq_share_likes_no;


CREATE TABLE OOTD_Likes (
	LIKE_no	number,
	board_no	number		NOT NULL,
	member_id	varchar2(50)		NOT NULL,
    CONSTRAINT PK_OOTD_LIKES PRIMARY KEY (LIKE_no),
    CONSTRAINT FK_OOTD_board_TO_OOTD_Likes_1 FOREIGN KEY (board_no)REFERENCES OOTD_board (OOTD_no),
    CONSTRAINT FK_Member_TO_OOTD_Likes_1 FOREIGN KEY (member_id)REFERENCES Member (member_id)
);

create sequence seq_ootd_likes_no;



CREATE TABLE ootd_find (
	no	number,
	style_no	varchar2(10)		NOT NULL,
	OOTD_no	number		NOT NULL,
    CONSTRAINT PK_OOTD_FIND PRIMARY KEY (no),
    CONSTRAINT FK_fashionstyle_TO_ootd_find_1 FOREIGN KEY (style_no)REFERENCES fashionstyle (style_no),
    CONSTRAINT FK_OOTD_board_TO_ootd_find_1 FOREIGN KEY (OOTD_no)REFERENCES OOTD_board (OOTD_no)
);

create sequence seq_ootd_find_no;


CREATE TABLE SHARE_find (
	no	number,
	style_no	varchar2(10)		NOT NULL,
	share_no	number		NOT NULL,
    CONSTRAINT PK_SHARE_FIND PRIMARY KEY (no),
    CONSTRAINT FK_fashionstyle_TO_SHARE_find_1 FOREIGN KEY (style_no) REFERENCES fashionstyle (style_no),
    CONSTRAINT FK_SHARE_board_TO_SHARE_find_1 FOREIGN KEY (share_no) REFERENCES SHARE_board (SHARE_no)
);

create sequence seq_share_find_no;



--CREATE TABLE chat_log (
--	no	number,
--	chatroom_id	number		NOT NULL,
--	message	varchar2(500)		NULL,
--	datetime	Date	default sysdate,
--    CONSTRAINT PK_CHAT_LOG PRIMARY KEY (no),
--    CONSTRAINT FK_chatroom_TO_chat_log_1 FOREIGN KEY (chatroom_id) REFERENCES chatroom (chatroom_id)
--);
--
--create sequence seq_chat_log_no;
--
--
--
--CREATE TABLE chatroom (
--	chatroom_id	number		NOT NULL,
--    board_id    varchar2(50) NOT NULL,
--	member_id	varchar2(50)		NOT NULL,
--    CONSTRAINT PK_CHATROOM PRIMARY KEY (chatroom_id),
--    CONSTRAINT FK_SHARE_board_TO_chatroom_1 FOREIGN KEY (board_id) REFERENCES SHARE_board (member_id),
--    CONSTRAINT FK_Member_TO_chatroom_1 FOREIGN KEY (member_id) REFERENCES Member (member_id)
--);
--
--create sequence seq_chatroom_no;

-- share table 제약조건 해제 0126 ->  ALTER TABLE chatroom drop CONSTRAINT  FK_SHARE_board_TO_chatroom_1;


create table obtg_column (
    no number,
    writer varchar2(20),
    title varchar2(50) not null,
    subtitle varchar2(50) not null,
    content varchar2(4000) not null,
    original_filename varchar2(100),
    renamed_filename varchar2(100),
    read_count number default 0,
    reg_date date default sysdate,
    constraint pk_column_no primary key(no),
    constraint fk_column_writer foreign key(writer) references member(member_id)
);
create sequence seq_column_no;








--0126 트리거 생성 by진 (ootd) 보류 !!! 게시글 삭제에 오류남  
-- ootd
--create or replace trigger trig_table_ootd_find -- 괄호 안씀 
  after 
	insert   or update or delete on OOTD_board   --   이 테이블에 변화가 생기면  begin절에 쓴대로 trigger 가 작동되는거임 
	for each row 
begin
	if inserting then   insert into ootd_find  (no, style_no, OOTD_no )
	   values ( seq_ootd_find_no.nextval, :new.style_no, :new.OOTD_no );
	elsif updating then insert into ootd_find (no, style_no, OOTD_no)
			values ( seq_ootd_find_no.nextval, :new.style_no, :old.OOTD_no );
	elsif deleting  then insert into ootd_find(no, style_no,OOTD_no)
        values ( seq_ootd_find_no.nextval, :old.style_no, :old.OOTD_no );      
end if; 

end;

-- 0126 트리거 생성 by진 (share) ---- 보류 !!! 게시글 삭제에 오류남 
--create or replace trigger trig_table_share_find -- 괄호 안씀 
  after 
	insert or update or delete on share_board   --   이 테이블에 변화가 생기면  begin절에 쓴대로 trigger 가 작동되는거임 
	for each row 
begin
	if inserting then   insert into share_find  (no, style_no, share_no )
	   values ( seq_share_find_no.nextval, :new.style, :new.SHARE_no );
	elsif updating then insert into share_find (no, style_no, share_no)
			values ( seq_share_find_no.nextval, :new.style, :old.SHARE_no );
	elsif deleting  then insert into share_find(no, style_no,share_no)
        values ( seq_share_find_no.nextval, :old.style, :old.SHARE_no );      
end if; 

end;

-- 댓글 테이블 수정(제약조건변경 by jin)
CREATE TABLE OOTD_board_comment (
	cmt_no	number,
	board_no	number,
	member_id	varchar2(50)		NOT NULL,
	cmt_level	number		default 1,
	cmt_content	varchar2(2000)		NULL,
	cmt_reg_date	Date		default sysdate,
	comment_ref	number,
    CONSTRAINT PK_OOTD_BOARD_COMMENT PRIMARY KEY (cmt_no),
    CONSTRAINT FK_OOTD_board_comment_no FOREIGN KEY (board_no) REFERENCES OOTD_board (OOTD_no) on delete CASCADE,
    CONSTRAINT FK_Member_board_comment_id FOREIGN KEY (member_id) REFERENCES Member (member_id) on delete set null,
    CONSTRAINT FK_OOTD_BORAD_comment_ref foreign key(comment_ref) references OOTD_board_comment(cmt_no) on delete CASCADE
);


-- ootd find 테이블 /시퀀스  새로만듦 / 제약조건변경(on delte cascade )  (by jin ) 
CREATE TABLE ootd_find (
	no	number,
	style_no	varchar2(10)		NOT NULL,
	OOTD_no	number		NOT NULL,
    CONSTRAINT PK_OOTD_FIND PRIMARY KEY (no),
    CONSTRAINT FK_fashionstyle_TO_ootd_find_1 FOREIGN KEY (style_no)REFERENCES fashionstyle (style_no),
--    CONSTRAINT FK_OOTD_board_TO_ootd_find_1 FOREIGN KEY (OOTD_no)REFERENCES OOTD_board (OOTD_no) 
    constraint FK_OOTD_board_TO_ootd_find_1 FOREIGN KEY (OOTD_no)REFERENCES OOTD_board (OOTD_no) on delete CASCADE
);
create sequence seq_ootd_find_no3;







-- SHARE_find 테이블 /시퀀스  새로만듦 / 제약조건변경(on delte cascade )  (by jin ) 
CREATE TABLE SHARE_find (
	no	number,
	style_no	varchar2(10)		NOT NULL,
	share_no	number		NOT NULL,
    CONSTRAINT PK_SHARE_FIND PRIMARY KEY (no),
    CONSTRAINT FK_fashionstyle_TO_SHARE_find_1 FOREIGN KEY (style_no) REFERENCES fashionstyle (style_no),
    constraint FK_SHARE_board_TO_SHARE_find FOREIGN KEY (share_no) REFERENCES SHARE_board (SHARE_no) on delete cascade 
);

create sequence seq_share_find_no;
-- 제약 조건 추가하기 (부모레코드 지우면 자식도 지운다 )  0126 jin  
ALTER TABLE SHARE_find ADD  constraint FK_SHARE_board_TO_SHARE_find FOREIGN KEY (share_no) REFERENCES SHARE_board (SHARE_no) on delete cascade 



--FAQ
create table faq(
    no number,
    writer varchar2(50),
    title varchar2(200),
    reg_date date default sysdate,
    content varchar2(4000),
    constraint pk_faq_no primary key(no),
    constraint FK_faq_writer FOREIGN KEY (writer) REFERENCES Member (member_id) on delete set null
);

create sequence seq_faq_no;

create table faq_comment(
    no number,  --pk
    comment_level number default 1, --댓글1 /대댓글2 라고 나타냄  // 기능 구현 x
    writer varchar2(20),  -- fk 
    content varchar2(2000),
    faq_no number,  --특정게시글  fk
    comment_ref number, --댓글인경우 null / 대댓글인경우? 부모댓글의 no컬럼값 / fk // 기능 구현 x
    reg_date date default sysdate,
    
    constraint pk_faq_comment_no primary key(no),
    constraint fk_faq_comment_writer FOREIGN key(writer) REFERENCES member(member_id) on delete set null,
    constraint fk_faq_comment_faq_no FOREIGN key(faq_no) REFERENCES faq(no) on delete CASCADE,
    constraint fk_faq_comment_ref FOREIGN key(comment_ref) REFERENCES faq_comment(no) on delete CASCADE
);

create sequence seq_faq_comment_no;



select * from faq;


-- faq 테이블 조회수 추가
--alter table faq add read_count number default 0;


-- 제약조건 해제 / 대신 inline에 빡세게걸기 (by jin / id부분  ootd board comment share / 0129 ) 



--블랙리스트 테이블
create table blackList(
    no number,
    email varchar2(100)
);
create sequence seq_blackList;

--블랙리스트 테이블에 insert시 자동으로 member테이블에서는 제외 될 수있도록
create or replace trigger trig_blackList
    after
    insert on blackList
    for each row
begin
    delete member where email= :new.email;
end;
/


--select * from blackList;   
ol



create table message (
    no number,
    title varchar2(50),
    sender varchar2(50),
    receiver varchar2(50) not null,
    content varchar2(1000) not null,
    reg_date date default sysdate,
    constraint pk_message_no primary key(no),
    constraint fk_message_sender foreign key(sender) references member (member_id) on delete set null
);

create sequence seq_message_no;

select * from faq;
