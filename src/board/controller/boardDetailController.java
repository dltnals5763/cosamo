package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.BoardDao;

/**
 * Servlet implementation class boardDetailController
 */
@WebServlet(name = "boardDetail.do", urlPatterns = { "/boardDetail.do" })
public class boardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public boardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1. 요청값 처리
		request.setCharacterEncoding("utf-8");
		
		String numS = request.getParameter("num");
		if(numS==null) numS="0";
		int num = Integer.parseInt(numS); // 게시글 번호(primary key)
		String readcountS = request.getParameter("readcount");
		if(readcountS==null) readcountS="0";
		int readcount = Integer.parseInt(readcountS); // 조회수 처리
		
		// 2. 모델 처리
		BoardDao dao = new BoardDao();
		dao.updateCount(num);
		request.setAttribute("dto", dao.getWrite(num));
		
		// 3. view단 처리
		String page ="view\\board\\example.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
