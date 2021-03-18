package project.dao.myPage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import project.vo_mypage.BoardExp;
import project.vo_mypage.UserInfo2;

public class BoardDao {
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
			System.out.println("접속 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<BoardExp> boardList(){
		ArrayList<BoardExp> list = new ArrayList<BoardExp>();
		try {
			setCon();
			String sql = "select * from boardExp";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				BoardExp b = new BoardExp(rs.getInt(1),
						rs.getString(2),rs.getString(3),rs.getDate(4));
				
				list.add(b);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("접속 성공");
		return list;
	}
	

	public UserInfo2 userInfo(String id){
		UserInfo2 info = new UserInfo2();
		try {
			setCon();
			String sql = "SELECT * FROM userInfo2\r\n"
					+ "WHERE id= ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				info.setId(rs.getString("id"));
				info.setPass(rs.getString("pass"));
				info.setEmail(rs.getString("email"));
				info.setPhonNum(rs.getString("phonNum"));
				info.setAddress(rs.getString("address"));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("접속 성공");
		return info;
	}

	public ArrayList<BoardExp> boardList(int page, int cnt){
		ArrayList<BoardExp> list = new ArrayList<BoardExp>();
		try {
			setCon();
			int start=1+(page-1)*cnt;
			int end = page*cnt;
			
			String sql = "SELECT * FROM (\r\n"
					+ "	SELECT ROWNUM bnum, u.* FROM (\r\n"
					+ "		SELECT * FROM boardExp ORDER BY num\r\n"
					+ "	) u\r\n"
					+ ") WHERE num BETWEEN ? AND ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardExp b = new BoardExp(rs.getInt("num"),
						rs.getString("kind"),rs.getString("title"),
						rs.getDate("regDate"));
				
				list.add(b);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("접속 성공");
		return list;
	}
	public int count(){
		int cnt=0;
		try {
			setCon();
			String sql = "SELECT count(*) cnt FROM boardExp";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				cnt=rs.getInt("cnt");
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("접속 성공");
		return cnt;
	}

	public boolean updateInfo(UserInfo2 sch){
		boolean success = false;
		try {
			setCon();
			String sql = "UPDATE userInfo2\r\n"
					+ "SET pass = ?,\r\n"
					+ "	email= ?,\r\n"
					+ "	phonNum = ?,\r\n"
					+ "	address = ?\r\n"
					+ "WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sch.getPass());
			pstmt.setString(2, sch.getEmail());
			pstmt.setString(3, sch.getPhonNum());
			pstmt.setString(4, sch.getAddress());
			pstmt.setString(5, sch.getId());
			rs = pstmt.executeQuery();
			success = rs.next();
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("접속 성공");
		return success;
	}

	public static void main(String[] args) {
		BoardDao dao = new BoardDao();
		ArrayList<BoardExp> list = dao.boardList(1,3);
		for(BoardExp be : list) {
			System.out.println(be.getTitle());
		}
	}

}
