package project.dao.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import board.dto.BoardDTO;
import project.vo.login.Member;

public class Main_Dao {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public void setCon() throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String info = "jdbc:oracle:thin:@localhost:1521:xe";
		con = DriverManager.getConnection(info, "scott", "tiger");
		System.out.println("접속 성공!!!");
	}
	
	public ArrayList<BoardDTO> boardAllList(){
		ArrayList<BoardDTO> blist =new ArrayList<BoardDTO>();
		try {
			setCon();
			String sql = "SELECT * FROM board WHERE category != '공지' ORDER BY reg_date DESC";
			System.out.println("sql:"+sql);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println("데이터가 있는지?");
				// int num, String id, String title, String category, Date reg_date, int readcount, int favor, String content
				BoardDTO board = new BoardDTO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
				blist.add(board);
			}
			rs.close();
			pstmt.close();
			con.close();
//			5. 예외 처리..								
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("##DB 관련 에러##");
			System.out.println(e.getMessage());
		} catch(Exception e) {
			System.out.println("##기타 에러##");
			System.out.println(e.getMessage());
		}
		return blist;
	}
	
	public ArrayList<BoardDTO> AnnouceList(){
		ArrayList<BoardDTO> blist =new ArrayList<BoardDTO>();
		try {
			setCon();
			String sql = "SELECT * FROM board WHERE category = '공지' ORDER BY reg_date DESC";
			System.out.println("sql:"+sql);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println("데이터가 있는지?");
				// int num, String id, String title, String category, Date reg_date, int readcount, int favor, String content
				BoardDTO board = new BoardDTO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
				blist.add(board);
			}
			rs.close();
			pstmt.close();
			con.close();
//			5. 예외 처리..								
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("##DB 관련 에러##");
			System.out.println(e.getMessage());
		} catch(Exception e) {
			System.out.println("##기타 에러##");
			System.out.println(e.getMessage());
		}
		return blist;
	}
}
