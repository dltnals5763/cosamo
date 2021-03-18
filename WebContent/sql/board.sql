DROP TABLE board;
CREATE TABLE board(
	num number PRIMARY KEY, --게시물번호
	id varchar2(50) NOT NULL, --작성자
	title varchar2(50) NOT NULL, --제목
	category varchar2(30), --카테고리
	reg_date DATE default sysdate, --작성일자
	readcount number, --조회수
	favor NUMBER,
	content varchar2(4000) NOT NULL
);

SELECT * FROM board ORDER BY num desc;
-- 글 등록
INSERT INTO board(num,writer,title,category,readcount,favor,content)
values((SELECT nvl(max(num)+1,1) FROM board),
'작성자','제목','카테고리',0,0,
'내용');
-- 글 수정
UPDATE BOARD 
	SET title='제목(변경)',
		content='내용(변경)'
	WHERE num=1;
SELECT * FROM board;
-- 조회수 증가
UPDATE BOARD 
	SET readcount = readcount+1
	WHERE num=1;
SELECT * FROM board;
INSERT INTO board(num,writer,title,readcount,favor,content)
values((SELECT nvl(max(num)+1,1) FROM board),
'작성자','제목',0,0,
'내용');
		
CREATE TABLE MEMBER(
	id varchar2(30),
	pass varchar2(30),
	name varchar2(50),
	email varchar2(100),
	postcnt NUMBER,
	commentcnt NUMBER,
	warncnt number
);
INSERT INTO MEMBER values('himan','7777','홍길동','hong@gamil.com',7,5,0);

CREATE TABLE member_grade(
	grade varchar2(10),
	scnt NUMBER,
	ecnt number
);
INSERT INTO member_grade values('IRON',0,5);
INSERT INTO member_grade values('BRONZE',6,10);
INSERT INTO member_grade values('SILVER',11,15);
INSERT INTO member_grade values('GOLD',16,20);
INSERT INTO member_grade values('PLATINUM',21,100);
INSERT INTO member_grade values('DIAMOND',101,1000);
INSERT INTO member_grade values('MASTER',1000,2000);

CREATE TABLE com(
   table_id NUMBER,
   text_id NUMBER,
   comment_id NUMBER,
   comment_type NUMBER,
   cnt NUMBER,
   guest_id varchar2(30),
   content varchar2(100),
   written_date DATE,
   likes NUMBER,
   dislike NUMBER
);

DROP TABLE com;
SELECT * FROM com;
INSERT INTO com values(1,1,1,1,1,'tes','저작권 등 다른 사람의 권리를 침해하거나 명예를 훼손하는 게시물',sysdate,10,3);
INSERT INTO com values(1,1,2,1,1,'qqq','이용약관 및 관련 법률에 의해 제재를 받을 수 있습니다',sysdate,5,1);
INSERT INTO com values(1,1,3,1,1,'plwog','건전한 토론문화와 양질의 댓글 문화를 위해',sysdate,15,2);
INSERT INTO com values(1,1,1,2,1,'bjwiow','이용약관 및 관련 법률에 의해 제재를 받을 수 있습니다',sysdate,0,0);
INSERT INTO com values(1,1,1,2,1,'lavkie','종교 등을 비하하는 단어들은 표시가 제한',sysdate,0,10);
INSERT INTO com values(1,1,1,2,1,'hhddntr','대댓글 3',sysdate,0,0);
INSERT INTO com values(1,1,1,2,1,'rtn','대댓글4',sysdate,0,0);
INSERT INTO com values(1,1,1,2,1,'boprer','대댓글5',sysdate,0,0);
INSERT INTO com values(1,1,2,2,1,'wefjh','대댓글6',sysdate,0,0);
INSERT INTO com values(1,1,2,2,1,'hello man','suprise lol',sysdate,0,0);
INSERT INTO com values(1,1,4,1,2,'tes','같은 아이디일 때 상황을 확인하기 위한 댓글',sysdate,0,10);
INSERT INTO com values(1,1,1,2,3,'tes','같은 아이디일 때 상황을 확인하기 위한 대댓글',sysdate,5,10);
INSERT INTO com values(1,1,2,2,4,'tes','같은 아이디일 때 상황을 확인하기 위한 대댓글22',sysdate,1,2);

-- 테이블마다 시퀀스 생성 COMMENT_ID
CREATE SEQUENCE table01_seq
INCREMENT BY 1
START WITH 1
MINVALUE 1
MAXVALUE 9999;

DROP TABLE com;
DROP SEQUENCE table01_seq;
