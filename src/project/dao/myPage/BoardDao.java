package project.dao.myPage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import board.dto.Com;
import project.vo.login.Member;
import project.vo_mypage.Board;
import project.vo_mypage.Board3;

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
   
   public ArrayList<Board> boardList(){
      ArrayList<Board> list = new ArrayList<Board>();
      try {
         setCon();
         String sql = "select * from board";
         stmt = con.createStatement();
         rs = stmt.executeQuery(sql);
         
         while(rs.next()) {
            Board b = new Board(rs.getInt("num"),
                  rs.getString("title"),rs.getString("category"),rs.getDate("reg_date"));
            
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
   

   public Member userInfo(String id){
      Member info = new Member();
      try {
         setCon();
         String sql = "SELECT * FROM member\r\n"
               + "WHERE id= ? ";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, id);
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            info.setId(rs.getString("id"));
            info.setPass(rs.getString("pass"));
            info.setName(rs.getString("name"));
            info.setEmail(rs.getString("email"));
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

   public ArrayList<Board> boardList(int page, int cnt, String id){
      ArrayList<Board> list = new ArrayList<Board>();
      try {
         setCon();
         int start=1+(page-1)*cnt;
         int end = page*cnt;
         
         String sql = "SELECT rownum bnum, u.* from (\r\n"
               + "   SELECT * FROM board ORDER BY num\r\n"
               + "   ) u\r\n"
               + "WHERE num BETWEEN ? AND ?\r\n"
               + "AND id=?";
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, start);
         pstmt.setInt(2, end);
         pstmt.setString(3, id);
         
         
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            Board b = new Board(rs.getInt("num"),
                  rs.getString("title"),rs.getString("category"),
                  rs.getDate("reg_date"));
            
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
   
   public ArrayList<Com> boardList2(int page, int cnt, String id){
	      ArrayList<Com> list = new ArrayList<Com>();
	      try {
	         setCon();
	         int start=1+(page-1)*cnt;
	         int end = page*cnt;
	         
	         String sql = "SELECT rownum bnum, u.* from (\r\n"
	               + "   SELECT * FROM com ORDER BY cnt\r\n"
	               + "   ) u\r\n"
	               + "WHERE cnt BETWEEN ? AND ?\r\n"
	               + "AND guest_id=?";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setInt(1, start);
	         pstmt.setInt(2, end);
	         pstmt.setString(3, id);
	         
	         
	         rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	        	 Com b = new Com(rs.getInt(1),rs.getInt(2),
							rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),
							rs.getTimestamp(8),rs.getInt(9),rs.getInt(10));
	            
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
         String sql = "SELECT count(*) cnt FROM Board";
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

   public boolean updateInfo(Member sch){
      boolean success = false;
      try {
         setCon();
         String sql = "UPDATE member\r\n"
               + "SET pass = ?,\r\n"
               + "   name = ?,\r\n"
               + "   email= ?\r\n"
               + "WHERE id=?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, sch.getPass());
         pstmt.setString(2, sch.getName());
         pstmt.setString(3, sch.getEmail());
         pstmt.setString(4, sch.getId());
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
//      ArrayList<Board> list = dao.boardList(1,3,"himan");
//      for(Board be : list) {
//         System.out.println(be.getTitle());
//      }
      //int a = dao.boardList2(1, 3, "himan");
      //System.out.println(dao.boardList2(1, 3, "tes").get(0).getBnum());
      
   }

}