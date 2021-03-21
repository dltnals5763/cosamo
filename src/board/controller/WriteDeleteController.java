package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.dao.BoardDao;
import board.dto.BoardDTO;

/**
 * Servlet implementation class WriteDeleteController
 */
@WebServlet(name = "writeDelete.do", urlPatterns = { "/writeDelete.do" })
public class WriteDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8");		
		BoardDao dao = new BoardDao();
		HttpSession session = request.getSession();
		String id=(String) session.getAttribute("id");
		String num = request.getParameter("num");
		String category = request.getParameter("category");
		// 아이디가 아이디와 같아야 함 그래야 삭제 가능
		if(request.getParameter("id").equals(id)) {
			BoardDTO del = new BoardDTO(Integer.parseInt(num),category);
			dao.delete(del);
		}
		ArrayList<BoardDTO> list = dao.boardList(category);
	      request.setAttribute("dlist", list);
		String page ="view\\board\\board_list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
		
	}

}
