DROP TABLE board;
CREATE TABLE board(
	num number PRIMARY KEY, --게시물번호
	writer varchar2(50) NOT NULL, --작성자
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
		


