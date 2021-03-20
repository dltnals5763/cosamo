package board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import board.dto.BoardDTO;

public class ListDao {
	private Connection con;
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
	public ArrayList<BoardDTO> categoryList(String category){
		//ArrayList<Emp> list = null;
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		// 1. 공통메서드 호출
		try {
			setCon();
		// 2. Statement 객체 생성 (연결객체 - Connection)
			String sql = "SELECT * FROM board\r\n"
					+ "WHERE category like '%'||lower( ? )||'%' \r\n"
					+ "ORDER BY num DESC";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);

		// 3. ResultSet 객체 생성 (대화객체 - Statement)
		
			rs = pstmt.executeQuery();		
			while(rs.next()) {
				BoardDTO e = new BoardDTO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getDate(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
		               //  2. ArrayList에 할당.
		               list.add(e);
			}
			System.out.println("객체의 갯수: " + list.size());
			System.out.println("2번째 행의 name: " + list.get(1).getCategory());
		// 4. 자원의 해제
			rs.close();
			pstmt.close();
			con.close();
		// 5. 예외 처리
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DB관련 에러: " + e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("기타 에러: " + e.getMessage());
		}
		return list;
	}
	public static void main(String[] args) {
		ListDao dao = new ListDao();
		
		dao.categoryList("");
	}
}
