package project.vo_mypage;
// jspexp.z01_vo.BoardExp
import java.util.Date;

public class BoardExp {
   private int num;
   private String kind;
   private String title;
   private Date regDate;
   public BoardExp() {
      super();
      // TODO Auto-generated constructor stub
   }
   public BoardExp(int num, String kind, String title, Date regDate) {
      super();
      this.num = num;
      this.kind = kind;
      this.title = title;
      this.regDate = regDate;
   }
   public int getNum() {
      return num;
   }
   public void setNum(int num) {
      this.num = num;
   }
   public String getKind() {
      return kind;
   }
   public void setKind(String kind) {
      this.kind = kind;
   }
   public String getTitle() {
      return title;
   }
   public void setTitle(String title) {
      this.title = title;
   }
   public Date getRegDate() {
      return regDate;
   }
   public void setRegDate(Date regDate) {
      this.regDate = regDate;
   }
   
}