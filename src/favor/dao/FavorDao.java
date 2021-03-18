package favor.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import board.dto.BoardDTO;

public class FavorDao {
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	public void setCon() throws SQLException{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		String info = "jdbc:oracle:thin:@localhost:1521:XE";
			con = DriverManager.getConnection(info,"scott","tiger");
			System.out.println("접속성공");
		}	
	// favor
	public void favor(String id, String num) {
		try {
			setCon();
			con.setAutoCommit(false);
			String sql=" INSERT INTO favor VALUES(?,?) ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, num);
			System.out.println(sql);
			pstmt.executeUpdate(sql);
			con.commit();
			pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("#### db에러");
			try {
				con.rollback();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}catch(Exception e) {
			System.out.println("#### 일반에러");
			System.out.println(e.getMessage());
		}
	}
	
}


	

















