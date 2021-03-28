package board.dto;

public class Tab {
	private int table_id;
	private String table_name;
	private int text_id;
	private String title;
	private String guest_id;
	private String content;
	public Tab() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tab(int table_id, String table_name, int text_id, String title, String guest_id, String content) {
		super();
		this.table_id = table_id;
		this.table_name = table_name;
		this.text_id = text_id;
		this.title = title;
		this.guest_id = guest_id;
		this.content = content;
	}
	public int getTable_id() {
		return table_id;
	}
	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public int getText_id() {
		return text_id;
	}
	public void setText_id(int text_id) {
		this.text_id = text_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	
}
