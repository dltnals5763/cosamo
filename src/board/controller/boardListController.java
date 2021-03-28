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
import project.vo.login.Member;

/**
 * Servlet implementation class boardListController
 */
//http://localhost:7080/cosa/boardList.do
@WebServlet(name = "boardList.do", urlPatterns = { "/boardList.do" })
public class boardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public boardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		Member mem = null;
		
		String category = request.getParameter("category");
		if(category == null || category.equals("")) category = "";
		else session.setAttribute("cate", category);
		String page= "";
		if(session != null) {
			mem = (Member)session.getAttribute("member");
			
			page = "view\\board\\board_list.jsp";
		} else
			page = "main_include.jsp";
		
		BoardDao dao = new BoardDao();

		//ArrayList<BoardDTO> list = dao.boardList();
		ArrayList<BoardDTO> list = dao.boardList(category);
		
		// 2. 모델 데이터
		request.setAttribute("dlist", list);
		// 2. 모델 데이터
		String categVal =  request.getParameter("category");
		if(categVal=="전체") categVal = "";
		
		// 3. 뷰단에 넘기기
		
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
