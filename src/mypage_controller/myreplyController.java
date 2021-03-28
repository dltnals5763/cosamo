package mypage_controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.dao.comment_dao;
import project.dao.myPage.BoardDao;
import project.vo.login.Member;
import project.vo_mypage.Board;


/**
 * Servlet implementation class myreplyController
 */
@WebServlet(name = "myreply.do", urlPatterns = { "/myreply.do" })
public class myreplyController extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myreplyController() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * http://localhost:7080/cosa/myreply.do
    * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
    */
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub    
         String id = request.getParameter("id");
         
         request.setCharacterEncoding("utf-8");
         HttpSession session = request.getSession();
         Member m = (Member)session.getAttribute("member");
         
         if(m != null) { 
            id = m.getId();
         }
      BoardDao dao = new BoardDao();
      comment_dao cdao = new comment_dao();
      ArrayList<Board> boardList = new ArrayList<Board>();
      boardList = dao.boardList(1,5,id);
      int c = dao.count();
      
      String pageS = request.getParameter("p");
      if(pageS!=null) {
         int p = Integer.parseInt(pageS);
         boardList = dao.boardList(p, 5,id);
      }
      request.setAttribute("comList", cdao.commentaryList2(id));
      
      request.setAttribute("boardList", boardList);
      request.setAttribute("count", c);
      
      String page = "mypage\\myReply.jsp";
      RequestDispatcher rd = request.getRequestDispatcher(page);
      rd.forward(request, response);
      
   }

}