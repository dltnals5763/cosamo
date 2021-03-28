package board.dto;

import java.util.Date;

public class Com {
	private int table_id;
	private int text_id;
	private int comment_id;
	private int comment_type;
	private int cnt;
	private String guest_id;
	private String content;
	private Date written_date;
	private int likes;
	private int dislike;
	public Com() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Com(int table_id, int text_id, int comment_id, int comment_type, int cnt, String guest_id, String content,
			Date written_date, int likes, int dislike) {
		super();
		this.table_id = table_id;
		this.text_id = text_id;
		this.comment_id = comment_id;
		this.comment_type = comment_type;
		this.cnt = cnt;
		this.guest_id = guest_id;
		this.content = content;
		this.written_date = written_date;
		this.likes = likes;
		this.dislike = dislike;
	}
	public int getTable_id() {
		return table_id;
	}
	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}
	public int getText_id() {
		return text_id;
	}
	public void setText_id(int text_id) {
		this.text_id = text_id;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public int getComment_type() {
		return comment_type;
	}
	public void setComment_type(int comment_type) {
		this.comment_type = comment_type;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getGuest_id() {
		return guest_id;
	}
	public void setGuest_id(String guest_id) {
		this.guest_id = guest_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWritten_date() {
		return written_date;
	}
	public void setWritten_date(Date written_date) {
		this.written_date = written_date;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDislike() {
		return dislike;
	}
	public void setDislike(int dislike) {
		this.dislike = dislike;
	}

}
