package project.vo_join;

import java.util.Date;

public class Customer {
	/*
	   customer_id varchar2(30), 
	   pw varchar2(30),
	   name varchar2(30),
	   address varchar2(200),
	   email varchar2(40),
	   phone varchar2(20),
	   gender char(1), -- 'M','F'
	   birth_date DATE,
	   reg_date DATE -- 가입일
	 */
	private String customer_id;
	private String pw;
	private String name;
	private String address;
	private String email;
	private String phone;
	private String gender;
	private Date birth_date;
	private Date reg_date;
	
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Customer(String customer_id, String pw, String name, String address, String email, String phone,
			String gender, Date birth_date, Date reg_date) {
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

	
	public Customer(String customer_id, String pw, String name, String address, String email, String phone) {
		super();
		this.customer_id = customer_id;
		this.pw = pw;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
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


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public Date getBirth_date() {
		return birth_date;
	}


	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}


	public Date getReg_date() {
		return reg_date;
	}


	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
