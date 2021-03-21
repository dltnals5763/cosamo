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
 * Servlet implementation class boardNoticeController
 */
@WebServlet(name = "noticeList.do", urlPatterns = { "/noticeList.do" })
public class boardNoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public boardNoticeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1. 요청값 받기
		BoardDao dao = new BoardDao();
		ArrayList<BoardDTO> list = dao.noticeList();
				

		// 2. 모델 데이터
		request.setAttribute("nlist", list);
		HttpSession session = request.getSession();
		String id=(String) session.getAttribute("id"); // session id
		// 3. 뷰단에 넘기기
		
		
		String page="view\\board\\board_list_notice.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
