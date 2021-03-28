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
	public ArrayList<BoardDTO> boardList(String category){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			setCon();
			String sql = "select * from board where category='"+category+"'order by num desc";
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
	// 글 등록
	   public void insertWrite(BoardDTO ins) {
	      int num = 0;   // 사용하려는 변수 초기화
	      System.out.println("카테고리: " + ins.getCategory());
	      if(ins.getCategory() == "사담" || ins.getCategory().equals("사담")) num = 1;
	      else if(ins.getCategory() == "Java" || ins.getCategory().equals("Java")) num = 2;
	      else if(ins.getCategory() == "Python" || ins.getCategory().equals("Python")) num = 3;
	      else if(ins.getCategory() == "Javascript" || ins.getCategory().equals("Javascript")) num = 4;
	      else if(ins.getCategory() == "html" || ins.getCategory().equals("html")) num = 5;
	      else if(ins.getCategory() == "C/C++" || ins.getCategory().equals("C/C++")) num = 6;
	      else if(ins.getCategory() == "Spring" || ins.getCategory().equals("Spring")) num = 7;
	      try {
	         setCon();
	         con.setAutoCommit(false);
	         stmt = con.createStatement();
	         String sql=" INSERT INTO board(num,id,title,category,readcount,favor,content)\r\n"
	               + "values(board"+num+"_seq.NEXTVAL,\r\n"
	               + "'"+ins.getId()+"','"+ins.getTitle()+"','"+ins.getCategory()+"',0,0,\r\n"
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
	   
	   public void PlusPostCnt(String id) {
			//Test m = null;
			try {
				setCon();
				String sql ="UPDATE member \r\n"
						+ "	SET postcnt = postcnt+1\r\n"
						+ "	WHERE id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,id);
				
				rs = pstmt.executeQuery();
				
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	   
	   public void MinusPostCnt(String id) {
			//Test m = null;
			try {
				setCon();
				String sql ="UPDATE member \r\n"
						+ "	SET postcnt = postcnt-1\r\n"
						+ "	WHERE id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,id);
				
				rs = pstmt.executeQuery();
				
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
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
					+ "	WHERE num=? and category=? ";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, upt.getTitle());
			pstmt.setString(2, upt.getContent());
			pstmt.setInt(3, upt.getNum());
			pstmt.setString(4, upt.getCategory());
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
	public void updateCount(int num) {
		try {
			setCon();
			String sql = " UPDATE BOARD \r\n"
					+ "	SET readcount = readcount+1\r\n"
					+ "	WHERE num=? ";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
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
	// 조회수 증가(관리자)
	public void updateCount2(int num) {
		try {
			setCon();
			String sql = " UPDATE notice \r\n"
					+ "	SET readcount = readcount+1\r\n"
					+ "	WHERE num=? ";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
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
	
	// 좋아요
	public void favorr(String num) {
		try {
			setCon();
			con.setAutoCommit(false);
			String sql=" UPDATE BOARD SET favor = favor+1 WHERE num= ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, num);
			System.out.println(sql);
			pstmt.executeUpdate();
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
	// 좋아요 관리자
	public void favor2(String num) {
		try {
			setCon();
			con.setAutoCommit(false);
			String sql=" UPDATE BOARD SET favor = favor+1 WHERE num= ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, num);
			System.out.println(sql);
			pstmt.executeUpdate();
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
	//글 상세
	public BoardDTO getWrite(int num,String category) {
		BoardDTO board = null;
		try {
			setCon();
			String sql = "SELECT * FROM board WHERE num ="+num
					+ " AND category = '"+category+"'";
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
	// 글 상세(공지사항)
	public BoardDTO getWrite2(int num) {
		BoardDTO board = null;
		try {
			setCon();
			String sql = " SELECT * FROM notice WHERE num = '"+num+"'";
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

	// 글삭제
	public void delete(BoardDTO del) {
		try {
			setCon();
			String sql = " DELETE FROM BOARD \r\n"
					+ "	WHERE num= ? and category = ? ";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, del.getNum());
			pstmt.setString(2, del.getCategory());
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

	// 회원 아이디 가져오기
	public String getId(int num){
		try {
			setCon();
			String sql = "select id from board where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				return rs.getString(1);
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
		return "null";
	}
	
	
	
	// 관리자
	// 글 조회
	/*int num,String writer,String title,Date reg_date,int readcount,int favor,String content*/
	public ArrayList<BoardDTO> noticeList(){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			setCon();
			String sql = "select * from notice order by num desc";
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
	public void insertNotice(BoardDTO ins) {
		try {
			setCon();
			con.setAutoCommit(false);
			stmt = con.createStatement();
			String sql=" INSERT INTO notice(num,id,title,category,readcount,favor,content)\r\n"
					+ "values((SELECT nvl(max(num)+1,1) FROM notice), ?, ?, ?, 0, 0, ? )";
			System.out.println(sql);
			//id,title,category,content
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ins.getId());
			pstmt.setString(2, ins.getTitle());
			pstmt.setString(3, ins.getCategory());
			pstmt.setString(4, ins.getContent());
			pstmt.executeUpdate();
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
	public void updateNotice(BoardDTO upt) {
		try {
			setCon();
			con.setAutoCommit(false);
			// 대화
			String sql = "  UPDATE NOTICE \r\n"
					+ "SET title = ?,\r\n"
					+ "	content = ?\r\n"
					+ "WHERE num= ?  ";
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
			System.out.println("# db처리 에러 #");
			try {
				con.rollback();
				System.out.println("에러 발생으로 원복 처리");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch(Exception e) {
			System.out.println("# 일반 에러 #");
		}
	}	
	// 글 삭제
	public void deleteNotice(int num) {
		//1. 접속 autocommit(false)
		try {
			setCon();
			con.setAutoCommit(false);
			// 대화
			String sql = " DELETE FROM notice WHERE num=?  ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("# db처리 에러 #");
			try {
				con.rollback();
				System.out.println("에러 발생으로 원복 처리");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch(Exception e) {
			System.out.println("# 일반 에러 #");
		}
	}	
	
	
	public static void main(String[] args) {
		BoardDao dao = new BoardDao();
		BoardDTO ins = new BoardDTO("아이디","제목","카테고리","내용");
	//	dao.insertWrite(ins);
		dao.insertNotice(ins);
		BoardDTO del = new BoardDTO(4,"Java");
		dao.delete(del);
		BoardDTO upt = new BoardDTO(1,"제목","내용","Javascript");
		dao.updateWrite(upt);
	}
}
