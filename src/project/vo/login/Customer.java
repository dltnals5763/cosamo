package project.vo.login;

public class Customer {
	private String customer_id;
	private String pw;
	private String name;
	private String address;
	private String email;
	private String phone;
	private char gender;
	private String birth_date;
	private String reg_date;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(String customer_id, String pw, String name, String address, String email, String phone, char gender,
			String birth_date, String reg_date) {
		super();
		this.customer_id = customer_id;
		this.pw = pw;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.birth_date = birth_date;
		this.reg_date = reg_date;
	}
	public Customer(String customer_id, String pw) {
		super();
		this.customer_id = customer_id;
		this.pw = pw;
	}
	public Customer(String customer_id) {
		super();
		this.customer_id = customer_id;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
}
