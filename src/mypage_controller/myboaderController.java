package mypage_controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.dao.myPage.BoardDao;
import project.vo_mypage.BoardExp;


/**
 * Servlet implementation class myboaderController
 */
@WebServlet(name = "myboader", urlPatterns = { "/myboader" })
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
	 * http://localhost:7080/jspexp/myboader
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardDao dao = new BoardDao();
		ArrayList<BoardExp> boardList = new ArrayList<BoardExp>();
		boardList = dao.boardList(1,5);
		int c = dao.count();
		
		String pageS = request.getParameter("p");
		if(pageS!=null) {
			int p = Integer.parseInt(pageS);
			boardList = dao.boardList(p, 5);
		}
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("count", c);
		
		String page = "inflearn\\proView\\myWrite.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
		
		
		
	}

}
