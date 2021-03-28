package project.vo_mypage;

import java.util.Date;

public class Board {
   private int num;
   private String id;
   private String title;
   private String category;
   private Date reg_date;
   private int readcount;
   private int favor;
   private String content;
   public Board() {
      super();
      // TODO Auto-generated constructor stub
   }
   public Board(int num, String id, String title, String category, Date reg_date, int readcount, int favor,
         String content) {
      super();
      this.num = num;
      this.id = id;
      this.title = title;
      this.category = category;
      this.reg_date = reg_date;
      this.readcount = readcount;
      this.favor = favor;
      this.content = content;
   }
   
   
   public Board(int num, String title, String category, Date reg_date) {
      super();
      this.num = num;
      this.title = title;
      this.category = category;
      this.reg_date = reg_date;
   }
   public int getNum() {
      return num;
   }
   public void setNum(int num) {
      this.num = num;
   }
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getTitle() {
      return title;
   }
   public void setTitle(String title) {
      this.title = title;
   }
   public String getCategory() {
      return category;
   }
   public void setCategory(String category) {
      this.category = category;
   }
   public Date getReg_date() {
      return reg_date;
   }
   public void setReg_date(Date reg_date) {
      this.reg_date = reg_date;
   }
   public int getReadcount() {
      return readcount;
   }
   public void setReadcount(int readcount) {
      this.readcount = readcount;
   }
   public int getFavor() {
      return favor;
   }
   public void setFavor(int favor) {
      this.favor = favor;
   }
   public String getContent() {
      return content;
   }
   public void setContent(String content) {
      this.content = content;
   }
   

}