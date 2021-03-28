package mypage_controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.dao.myPage.BoardDao;
import project.vo.login.Member;


/**
 * Servlet implementation class mypageController
 */
@WebServlet(name = "mypage.do", urlPatterns = { "/mypage.do" })
public class mypageController extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mypageController() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * http://localhost:7080/cosa/mypage.do
    * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
    */
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      
      String id = request.getParameter("id");
      String isUpdate = request.getParameter("isUpdate");
      
      request.setCharacterEncoding("utf-8");
      HttpSession session = request.getSession();
      Member m = (Member)session.getAttribute("member");
      
      if(m != null) { 
         id = m.getId();
      }
      
      boolean result=false;
      if(isUpdate!=null) {
         if(isUpdate.equals("true")) {
            String pass = request.getParameter("pass");
            String name = request.getParameter("name");
            String email = request.getParameter("email");

            Member member = new Member(id, pass, name, email);
            
            BoardDao dao = new BoardDao();
            result = dao.updateInfo(member);
            request.setAttribute("result", result);
         }
      }
      if(id!=null) {
         BoardDao dao = new BoardDao();
         request.setAttribute("member", dao.userInfo(id));
      }
      
      String page = "mypage\\myInfo.jsp";
      RequestDispatcher rd = request.getRequestDispatcher(page);
      rd.forward(request, response);
   }

}