package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.BoardDao;
import board.dto.BoardDTO;

/**
 * Servlet implementation class WriteUpdateController
 */
@WebServlet(name = "writeUpdate.do", urlPatterns = { "/writeUpdate.do" })
public class WriteUpdateController extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
    */
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      // 요청값 받기
      request.setCharacterEncoding("utf-8");
      String numS = request.getParameter("num");
      if(numS==null) numS="0";
      int num = Integer.parseInt(numS);
      
      // 모델 데이터
      BoardDao dao = new BoardDao();
      
      String title = request.getParameter("title");
      String content = request.getParameter("content");
      String category = request.getParameter("category");
      String proc = request.getParameter("proc");
      if(title==null) title="";
      if(content==null) content="";
      if(category==null) category="";
      request.setAttribute("dto",dao.getWrite(num,category));
      System.out.println("##title:"+title);
      System.out.println("##content:"+content);
      System.out.println("##num:"+num);
      System.out.println("##category:"+category);      
      
      if(proc!=null) {
         if(proc.equals("upt")) {
            BoardDTO upt = new BoardDTO(num,title,content,category);
             dao.updateWrite(upt);   
         }
      }
      // 페이지 이동 
      String page ="view\\board\\board_update.jsp";
      RequestDispatcher rd = request.getRequestDispatcher(page);
      rd.forward(request, response);      
   }

}