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

import board.dto.Com;
import project.dao.myPage.BoardDao;
import project.vo.login.Member;
import project.vo_mypage.Board;
import project.vo_mypage.Board3;


/**
 * Servlet implementation class myboaderController
 */
@WebServlet(name = "myboader.do", urlPatterns = { "/myboader.do" })
public class myboaderController extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myboaderController() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * http://localhost:7080/cosa/myboader.do
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
      ArrayList<Board> boardList = new ArrayList<Board>();
      ArrayList<Com> boardList3 = new ArrayList<Com>();
      boardList = dao.boardList(1,5,id);
      boardList3 = dao.boardList2(1,5,id);
      int c = dao.count();
      
      
      String pageS = request.getParameter("p");
      if(pageS!=null) {
         int p = Integer.parseInt(pageS);
         boardList = dao.boardList(p, 5,id);
         boardList3 = dao.boardList2(p, 5,id);
         
      }
      //boardList3.get(0).getBoard().getCategory()
      //boardList3.get(0).getBnum()
      request.setAttribute("boardList", boardList);
      request.setAttribute("boardList3", boardList3);
      request.setAttribute("count", c);
      
      
      
      String page = "mypage\\myWrite.jsp";
      RequestDispatcher rd = request.getRequestDispatcher(page);
      rd.forward(request, response);
      
      
      
   }

}