package board.dto;

public class Like {
	private String guest_id;
	private String writer_id;
	private int table_id;
	private int text_id;
	private int cnt;
	private int like;
	private int dislike;
	public Like() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Like(String guest_id, String writer_id, int table_id, int text_id, int cnt, int like, int dislike) {
		super();
		this.guest_id = guest_id;
		this.writer_id = writer_id;
		this.table_id = table_id;
		this.text_id = text_id;
		this.cnt = cnt;
		this.like = like;
		this.dislike = dislike;
	}
	public String getGuest_id() {
		return guest_id;
	}
	public void setGuest_id(String guest_id) {
		this.guest_id = guest_id;
	}
	public String getWriter_id() {
		return writer_id;
	}
	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
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
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getDislike() {
		return dislike;
	}
	public void setDislike(int dislike) {
		this.dislike = dislike;
	}
	
}
