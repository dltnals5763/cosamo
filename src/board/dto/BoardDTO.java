package board.dto;

import java.util.Date;

public class BoardDTO {
/*
	num number PRIMARY KEY, --게시물번호
	writer varchar2(50) NOT NULL, --작성자
	title varchar2(50) NOT NULL, --제목
	reg_date DATE default sysdate, --작성일자
	readcount number, --조회수
	favor NUMBER,
	content varchar2(4000) NOT NULL,
	filename varchar2(200),
	filesize number
*/	
	private int num;
	private String writer;
	private String title;
	private String category;
	private Date reg_date;
	private int readcount;
	private int favor;
	private String content;
	public BoardDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardDTO(String title, String category, String content) {
		super();
		this.title = title;
		this.category = category;
		this.content = content;
	}
	public BoardDTO(int num, String writer, String title, String category, Date reg_date, int readcount, int favor, String content) {
		super();
		this.num = num;
		this.writer = writer;
		this.title = title;
		this.reg_date = reg_date;
		this.readcount = readcount;
		this.favor = favor;
		this.content = content;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public BoardDTO(int num, Date reg_date, int readcount, int favor) {
		super();
		this.num = num;
		this.reg_date = reg_date;
		this.readcount = readcount;
		this.favor = favor;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getFavor() {
		return favor;
	}
	public void setFavor(int favor) {
		this.favor = favor;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "BoardDto [num="+num+", writer="+writer+", title="+title+", reg_date="+reg_date+
				", readcount="+readcount+", favor="+favor+", content="+content;
	}
	
}












