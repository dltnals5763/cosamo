package board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import board.dto.BoardDTO;

public class BoardDao {
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
	// 글 조회
	/*int num,String writer,String title,Date reg_date,int readcount,int favor,String content*/
	public ArrayList<BoardDTO> boardList(){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			setCon();
			String sql = "select * from board order by num desc";
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				BoardDTO b = new BoardDTO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getDate(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
				list.add(b);
			}
			rs.close();
			stmt.close();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("#### DB관련에러");
			System.out.println(e.getMessage());
		}catch(Exception e) {
			System.out.println("#### 일반에러");
			System.out.println(e.getMessage());
		}
		return list;
	}
		
	// 글 등록
	public void insertWrite(BoardDTO ins) {
		try {
			setCon();
			con.setAutoCommit(false);
			stmt = con.createStatement();
			String sql=" INSERT INTO board(num,writer,title,category,readcount,favor,content)\r\n"
					+ "values((SELECT nvl(max(num)+1,1) FROM board),\r\n"
					+ "'회원','"+ins.getTitle()+"','"+ins.getCategory()+"',0,0,\r\n"
					+ "'"+ins.getContent()+"') ";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			con.commit();
			stmt.close();
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


	// 글 수정
	public void updateWrite(BoardDTO upt) {
		try {
			setCon();
			String sql = " UPDATE BOARD \r\n"
					+ "	SET title=?,\r\n"
					+ "		content=?\r\n"
					+ "	WHERE num=?  ";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, upt.getTitle());
			pstmt.setString(2, upt.getContent());
			pstmt.setInt(3, upt.getNum());
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("#### db에러");
			try {
				con.rollback();
				System.out.println("에러발생으로 원상복구 처리!!");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch(Exception e) {
			System.out.println("#### 일반 에러");
			System.out.println(e.getMessage());
		}
	} 
	
	
	// 조회수 증가
	public void updateCount(BoardDTO upc) {
		try {
			setCon();
			String sql = " UPDATE BOARD \r\n"
					+ "	SET readcount = readcount+1\r\n"
					+ "	WHERE num=? ";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, upc.getNum());
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("#### db에러");
			try {
				con.rollback();
				System.out.println("에러발생으로 원상복구 처리!!");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch(Exception e) {
			System.out.println("#### 일반 에러");
			System.out.println(e.getMessage());
		}
	} 	
	
	// 좋아요 수 증가
	public void updateFavor(BoardDTO upf) {
		try {
			setCon();
			String sql = " UPDATE BOARD \r\n"
					+ "	SET favor = favor+1\r\n"
					+ "	WHERE num=? ";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, upf.getFavor());
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("#### db에러");
			try {
				con.rollback();
				System.out.println("에러발생으로 원상복구 처리!!");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch(Exception e) {
			System.out.println("#### 일반 에러");
			System.out.println(e.getMessage());
		}
	} 
	
	//글 상세
	public BoardDTO getWrite(int num) {
		BoardDTO board = null;
		try {
			setCon();
			String sql = " SELECT * FROM board WHERE num="+num;
			System.out.println(sql);
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				board = new BoardDTO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getDate(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return board;
	}
	// 글 삭제
	public static void main(String[] args) {
		BoardDao bd = new BoardDao();
		/*String writer, String title, String content*/
		BoardDTO ins = new BoardDTO("카테고리","제목","내용");
		bd.insertWrite(ins);
	}
	
	}


	

















