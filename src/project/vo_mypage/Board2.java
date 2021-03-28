package project.vo_mypage;

public class Board2 {
   private int bnum;
   private Board boardList;
   public Board2() {
      super();
      // TODO Auto-generated constructor stub
   }
   public Board2(int bnum) {
      super();
      this.bnum = bnum;
   }
   public Board2(int bnum, Board boardList) {
      super();
      this.bnum = bnum;
      this.boardList = boardList;
   }
   public int getBnum() {
      return bnum;
   }
   public void setBnum(int bnum) {
      this.bnum = bnum;
   }
   public Board getBoardList() {
      return boardList;
   }
   public void setBoardList(Board boardList) {
      this.boardList = boardList;
   }
   
}