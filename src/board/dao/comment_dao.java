package board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import board.dto.Com;
import board.dto.Like;

public class comment_dao {

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
	
	public ArrayList<Com> commentaryList(){
		ArrayList<Com> list = new ArrayList<Com>();
		try {
			setCon();
			String sql = "SELECT * FROM com ORDER BY WRITTEN_DATE";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		
			while(rs.next()) {		
				Com c = (new Com(rs.getInt(1),rs.getInt(2),
						rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),
						rs.getTimestamp(8),rs.getInt(9),rs.getInt(10)));
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
	
	public ArrayList<Com> commentaryList2(String id){
		ArrayList<Com> list = new ArrayList<Com>();
		try {
			setCon();
			String sql = "SELECT * FROM com where guest_id = '"+id+"' ORDER BY WRITTEN_DATE";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		
			while(rs.next()) {		
				Com c = (new Com(rs.getInt(1),rs.getInt(2),
						rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),
						rs.getTimestamp(8),rs.getInt(9),rs.getInt(10)));
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

	public void insertComment(int table_id, int text_id, int comment_type,
			String id,String content) {
		int cnt = getMaxCnt(id);
		try {
			setCon();
			stmt = con.createStatement();
			String sql = "INSERT INTO com values("
					+ table_id + ","
					+ text_id + ","
					+ "table0"+table_id+"_seq.NEXTVAL,"
					+ comment_type + ","
					+ (cnt+1) +","
					+ "'"+id+"',"
					+ "'"+content+"',"
					+ "sysdate,"
					+ "0,"
					+ "0)";
			stmt.executeQuery(sql);
			con.commit();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertSubComment(int table_id, int text_id, int comment_id, 
			int comment_type, String id,String content) {
		int cnt = getMaxCnt(id);
		try {
			setCon();
			stmt = con.createStatement();
			String sql = "INSERT INTO com values("
					+ table_id + ","
					+ text_id + ","
					+ comment_id + ","
					+ comment_type + ","
					+ (cnt+1) +","
					+ "'"+id+"',"
					+ "'"+content+"',"
					+ "sysdate,"
					+ "0,"
					+ "0)";
			//System.out.println(sql);
			stmt.executeQuery(sql);
			con.commit();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 좋아요 클릭 시, 현재 사용자의 좋아요, 싫어요 상태 생성(기존에 없다면)
	public void insertLike(String id,String writer, int table_id, int text_id,int cnt) {
		
		try {
			setCon();
			stmt = con.createStatement();
			String sql = "INSERT INTO goodorhate values('"
					+ id + "',"
					+ "'"+ writer + "',"
					+ table_id + ","
					+ text_id +","
					+ cnt + ","
					+ "0,"
					+ "0)";
			stmt.executeQuery(sql);
			con.commit();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getLikeState(String id,String writer, int table_id, int text_id, int cnt) {
		int result = 0;
		try {
			setCon();
			String sql ="SELECT good FROM goodorhate\r\n"
					+ "WHERE guest_id = ?\r\n"
					+ "AND writer_id = ?\r\n"
					+ "AND table_id = ?\r\n"
					+ "AND text_id = ?\r\n"
					+ "AND cnt = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,writer);
			pstmt.setInt(3,table_id);
			pstmt.setInt(4,text_id);
			pstmt.setInt(5,cnt);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
				System.out.println("getLikeState(result): " + rs.getInt(1));
			} 
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public int getdisLikeState(String id,int table_id, int text_id, int cnt) {
		int result = 0;
		try {
			setCon();
			String sql ="SELECT hate FROM goodorhate\r\n"
					+ "WHERE guest_id = ?\r\n"
					+ "AND table_id = ?\r\n"
					+ "AND text_id = ?\r\n"
					+ "AND cnt = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setInt(2,table_id);
			pstmt.setInt(3,text_id);
			pstmt.setInt(4,cnt);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			} 
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public void UpdateGood(String id,String writer, int table_id, int text_id, int cnt) {
		
		//int exist = IsExistGoodTable(id,writer,table_id,text_id,cnt);
		Like exist = IsExistGoodTable(id,writer,table_id,text_id,cnt);
		if (exist == null) {
			insertLike(id,writer,table_id,text_id,cnt);
			System.out.println("현재 사용자에 대한 해당 댓글 좋아요 상태 객체 생성!!(goodorhate)");
		}
		
		int like = getLikeState(id,writer,table_id,text_id,cnt);
		System.out.println("updateGood_like:" + like);
		
		String sql = "";
		try {
			setCon();
			if(like == 0) {
				sql ="UPDATE goodorhate \r\n"
						+ "	SET good = 1\r\n"
						+ "	WHERE guest_id = ?\r\n"
						+ "	AND writer_id = ?\r\n"
						+ "	AND table_id = ?\r\n"
						+ "	AND text_id = ?\r\n"
						+ "	AND cnt = ?";
			} else if(like == 1) {
				sql ="UPDATE goodorhate \r\n"
						+ "	SET good = 0\r\n"
						+ "	WHERE guest_id = ?\r\n"
						+ "	AND writer_id = ?\r\n"
						+ "	AND table_id = ?\r\n"
						+ "	AND text_id = ?\r\n"
						+ "	AND cnt = ?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,writer);
			pstmt.setInt(3,table_id);
			pstmt.setInt(4,text_id);
			pstmt.setInt(5,cnt);
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
	
	public Like IsExistGoodTable(String id, String writer, int table_id, int text_id, int cnt) {
		
		//int result = 0;
		Like result = null;
		try {
			setCon();
			String sql ="SELECT * FROM goodorhate\r\n"
					+ "WHERE guest_id = ? "
					+ "AND writer_id = ?"
					+ "AND TABLE_ID = ?"
					+ "AND text_id = ?"
					+ "AND cnt = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,writer);
			pstmt.setInt(3,table_id);
			pstmt.setInt(4,text_id);
			pstmt.setInt(5,text_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = new Like(rs.getString(1),rs.getString(2),
						rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));
			} 
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public void UpdateLike(String writer,String session_id, int table_id, int text_id, int cnt) {
		
		// 작성자 id로 변경
		int like = getLikeState(session_id,writer, table_id,text_id,cnt);
		System.out.println("updateLike_like:" + like);
		String sql = "";
		try {
			setCon();
			if(like == 0) {
				if(cnt == 0) {
					sql = "UPDATE board \r\n"
							+ "	SET favor = favor-1\r\n"
							+ "	WHERE id = ?\r\n"
							+ "	AND table_id = ?\r\n"
							+ "	AND text_id = ?\r\n"
							+ "	AND cnt = ?";
				}
				else {
					sql = "UPDATE com \r\n"
							+ "	SET likes = likes-1\r\n"
							+ "	WHERE guest_id = ?\r\n"
							+ "	AND table_id = ?\r\n"
							+ "	AND text_id = ?\r\n"
							+ "	AND cnt = ?";
				}
			}
			else {
				if(cnt == 0) {
					sql = "UPDATE board \r\n"
							+ "	SET favor = favor+1\r\n"
							+ "	WHERE id = ?\r\n"
							+ "	AND table_id = ?\r\n"
							+ "	AND text_id = ?\r\n"
							+ "	AND cnt = ?";
				}
				else {
					sql = "UPDATE com \r\n"
							+ "	SET likes = likes+1\r\n"
							+ "	WHERE guest_id = ?\r\n"
							+ "	AND table_id = ?\r\n"
							+ "	AND text_id = ?\r\n"
							+ "	AND cnt = ?";
				}
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,writer);
			pstmt.setInt(2,table_id);
			pstmt.setInt(3,text_id);
			pstmt.setInt(4,cnt);
			
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

	public void UpdateHate(String id,int table_id, int text_id, int cnt) {
		//Test m = null;
		int dislike = getdisLikeState(id,table_id,text_id,cnt);
		String sql = "";
		try {
			setCon();
			if(dislike == 0) {
				sql ="UPDATE goodorhate \r\n"
						+ "	SET hate = 1\r\n"
						+ "	WHERE guest_id = ?\r\n"
						+ "	WHERE table_id = ?\r\n"
						+ "	WHERE text_id = ?\r\n"
						+ "	WHERE cnt = ?\r\n";
			} else if(dislike == 1) {
				sql ="UPDATE goodorhate \r\n"
						+ "	SET hate = 0\r\n"
						+ "	WHERE guest_id = ?\r\n"
						+ "	WHERE table_id = ?\r\n"
						+ "	WHERE text_id = ?\r\n"
						+ "	WHERE cnt = ?\r\n";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setInt(2,table_id);
			pstmt.setInt(3,text_id);
			pstmt.setInt(4,cnt);
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
	
	public void UpdatedisLike(String id,String writer, int table_id, int text_id, int cnt) {
		
		int like = getLikeState(id,writer,table_id,text_id,cnt);
		String sql = "";
		try {
			setCon();
			if(like == 0) {
				if(cnt == 0) {
					sql = "UPDATE board \r\n"
							+ "	SET favor = favor+1\r\n"
							+ "	WHERE id = ?\r\n"
							+ "	AND table_id = ?\r\n"
							+ "	AND text_id = ?\r\n"
							+ "	AND cnt = ?";
				}
				else {
					sql = "UPDATE com \r\n"
							+ "	SET dislike = dislike+1\r\n"
							+ "	WHERE id = ?\r\n"
							+ "	AND table_id = ?\r\n"
							+ "	AND text_id = ?\r\n"
							+ "	AND cnt = ?";
				}
			}
			else {
				if(cnt == 0) {
					sql = "UPDATE board \r\n"
							+ "	SET favor = favor-1\r\n"
							+ "	WHERE id = ?\r\n"
							+ "	AND table_id = ?\r\n"
							+ "	AND text_id = ?"
							+ "	AND cnt = ?";
				}
				else {
					sql = "UPDATE com \r\n"
							+ "	SET likes = likes-1\r\n"
							+ "	WHERE id = ?\r\n"
							+ "	AND table_id = ?\r\n"
							+ "	AND text_id = ?\r\n"
							+ "	AND cnt = ?";
				}
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setInt(2,table_id);
			pstmt.setInt(3,text_id);
			pstmt.setInt(4,cnt);
			
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

	public void delComment(String id, int cnt) {
		
		try {
			setCon();
			stmt = con.createStatement();
			String sql = "DELETE FROM com WHERE guest_id = ?  AND cnt = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setInt(2,cnt);
			
			rs = pstmt.executeQuery();
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getMaxCnt(String id) {
		//Test m = null;
		int cnt = 0;
		try {
			setCon();
			String sql ="SELECT max(cnt) FROM com\r\n"
					+ "WHERE guest_id= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);
			} 
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return cnt;
	}
	
	public int getComTotalCnt(int table_id, int text_id) {
		
		int cnt = 0;
		try {
			setCon();
			String sql ="SELECT count(*) FROM COM\r\n"
					+ "WHERE TABLE_ID = ? AND text_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,table_id);
			pstmt.setInt(2,text_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);
			} 
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return cnt;
	}
	
	public boolean login(String id, String pass) {
		//Test m = null;
		boolean check = false;
		try {
			setCon();
			String sql ="SELECT * FROM Member\r\n"
					+ "WHERE id= ? AND pass= ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,pass);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				check = true;
				//m = new Test(rs.getString("id"), rs.getString("pw"));
			} 
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return check;
	}
	
	// 현재 회원테이블의 글 작성갯수 가져오기
	// +1 , -1 하기 ( 댓글, 대댓글 상관없이)
	/*
	UPDATE member 
	SET commentcnt = commentcnt+1
	WHERE id = 'himan';
	
	UPDATE member 
	SET commentcnt = commentcnt-1
	WHERE id = 'himan';
	
	SELECT commentcnt FROM MEMBER
	WHERE id = 'himan';
	*/
	
	public int getMemComCnt(String id) {
		//Test m = null;
		int cnt = 0;
		try {
			setCon();
			String sql ="SELECT commentcnt FROM MEMBER\r\n"
					+ "	WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);
			} 
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return cnt;
	}
	
	public void PlusComCnt(String id,int commentCnt) {
		//Test m = null;
		try {
			setCon();
			String sql ="UPDATE member \r\n"
					+ "	SET commentcnt = commentcnt+1\r\n"
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
	
	public void MinusComCnt(String id,int commentCnt) {
		//Test m = null;
		try {
			setCon();
			String sql ="UPDATE member \r\n"
					+ "	SET commentcnt = commentcnt-1\r\n"
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		comment_dao dao = new comment_dao();
		//dao.insertComment(9, 9, 1, "aa", "sdfsdg");
		//System.out.println(dao.login("aa","aa"));
		//dao.insertComment(2, 1, 1, "yyy", "과연");
	}

}
