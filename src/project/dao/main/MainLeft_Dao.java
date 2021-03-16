package project.dao.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import project.vo.login.Member;

public class MainLeft_Dao {
	private Connection con;
	private Statement stmt;
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
	
	public Member getMem(String  id){
		Member mem=null;
		try {
			setCon();
			String sql = "SELECT * FROM member where id = ?";
			System.out.println("sql:"+sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("데이터가 있는지?");
				// String id, String pass, String name, String email, int postcnt, int commentcnt, int warncnt
				mem = new Member(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));
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
		return mem;
	}

	public Member login(Member login) {
		Member m = null;
		try {
			setCon();
			String sql = "SELECT * FROM MEMBER \n"
					+ "WHERE id=? AND pass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, login.getId());
			pstmt.setString(2, login.getPass());
			pstmt.executeQuery();
			rs=pstmt.executeQuery();
			if(rs.next()) {
				// String id, String pass, String name, String email, int postcnt, int commentcnt, int warncnt
				m = new Member(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7)); 
			}
			System.out.println(m.getId());
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DB에러");
			System.out.println(e.getMessage());
		} catch(Exception e) {
			System.out.println("일반에러");
			System.out.println(e.getMessage());
		}
		return m;
	}
	
	public Member getGrade(Member login) {
		Member m = null;
		try {
			setCon();
			String sql = "SELECT m.*, g.grade\n"
					+ "FROM MEMBER m, member_grade g\n"
					+ "WHERE m.postcnt+m.COMMENTCNT BETWEEN g.scnt AND g.ecnt \n"
					+ "AND m.id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, login.getId());
			pstmt.executeQuery();
			rs=pstmt.executeQuery();
			if(rs.next()) {
				// String id, String pass, String name, String email, int postcnt, int commentcnt, int warncnt
				m = new Member(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getString(8)); 
			}
			System.out.println(m.getId());
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DB에러");
			System.out.println(e.getMessage());
		} catch(Exception e) {
			System.out.println("일반에러");
			System.out.println(e.getMessage());
		}
		return m;
	}
}
