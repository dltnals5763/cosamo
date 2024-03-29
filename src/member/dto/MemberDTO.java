package member.dto;

public class MemberDTO {
/*
CREATE TABLE MEMBER(
	id varchar2(30) PRIMARY KEY,
	pass varchar2(30),
	name varchar2(50),
	email varchar2(100),
	postcnt NUMBER,
	commentcnt NUMBER,
	warncnt number
);
*/
	private String id;
	private String pass;
	private String name;
	private String email;
	private int postcnt;
	private int commentcnt;
	private int warncnt;
	public MemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberDTO(String id, String pass, String name, String email, int postcnt, int commentcnt, int warncnt) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.email = email;
		this.postcnt = postcnt;
		this.commentcnt = commentcnt;
		this.warncnt = warncnt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPostcnt() {
		return postcnt;
	}
	public void setPostcnt(int postcnt) {
		this.postcnt = postcnt;
	}
	public int getCommentcnt() {
		return commentcnt;
	}
	public void setCommentcnt(int commentcnt) {
		this.commentcnt = commentcnt;
	}
	public int getWarncnt() {
		return warncnt;
	}
	public void setWarncnt(int warncnt) {
		this.warncnt = warncnt;
	}
}
