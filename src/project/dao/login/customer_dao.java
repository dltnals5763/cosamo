package project.dao.login;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import project.vo.login.Customer;
//import project.vo.Purchase_record;

public class customer_dao {

	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public void setCon() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String info = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			con = DriverManager.getConnection(info, "scott", "tiger");
			//System.out.println("접속 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public boolean isInTable(String customer_id,String pw) {
		boolean check = false;
		
		try {
			setCon();
			String sql = "SELECT * FROM customer\r\n"
					+ "WHERE customer_id = ? AND pw = ?" ;
					
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,customer_id);
			pstmt.setString(2,pw);
			rs = pstmt.executeQuery();
			
			check = rs.next();
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB관련 에러: " + e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("기타 에러: " + e.getMessage());
		}
		return check;
	}
	
	
	
	
	
	public static void main(String[] args) {
			customer_dao dao = new customer_dao();
			System.out.println(dao.isInTable("test", "2222"));
	}

}
