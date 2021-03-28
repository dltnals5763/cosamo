package board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import board.dto.Tab;

public class table_dao {
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
	
	public ArrayList<Tab> TableList(){
		ArrayList<Tab> list = new ArrayList<Tab>();
		try {
			setCon();
			String sql = "SELECT * FROM Tab";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		
			while(rs.next()) {		
				
				Tab c = (new Tab(rs.getInt(1),rs.getString(2),
						rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6)));
				//c.setWritten_date((new Date(new java.util.Date().getTime())));
				//System.out.println(rs.getTimestamp(6));
				list.add(c);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB관련 에러: " + e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("기타 에러: " + e.getMessage());
		}
		return list;
	}
	
	public Tab getTab(int table_id, int text_id){
		Tab t = null;
		try {
			setCon();
		
			String sql = "SELECT * FROM tab WHERE table_id= " + table_id + " AND text_id=" + text_id;
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				t = new Tab(rs.getInt(1),rs.getString(2),rs.getInt(3),
						rs.getString(4), rs.getString(5),
						rs.getString(6));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB관련 에러: " + e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("기타 에러: " + e.getMessage());
		}
		return t;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
