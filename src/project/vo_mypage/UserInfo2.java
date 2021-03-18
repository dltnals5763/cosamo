package project.vo_mypage;

public class UserInfo2 {
	private String id;
	private String pass;
	private String email;
	private String phonNum;
	private String  address;
	public UserInfo2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserInfo2(String id, String pass, String email, String phonNum, String address) {
		super();
		this.id = id;
		this.pass = pass;
		this.email = email;
		this.phonNum = phonNum;
		this.address = address;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonNum() {
		return phonNum;
	}
	public void setPhonNum(String phonNum) {
		this.phonNum = phonNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
