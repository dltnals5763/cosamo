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
 * Servlet implementation class noticeDetailController
 */
@WebServlet(name = "noticeDeatil.do", urlPatterns = { "/noticeDeatil.do" })
public class noticeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public noticeDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청값 처리
		request.setCharacterEncoding("utf-8");
		String numS = request.getParameter("num");
		String proc = request.getParameter("proc");
		if(numS==null) numS="0";
		int num=0;
		try {
			num = Integer.parseInt(numS);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		// 2. 모델 처리
		BoardDao dao = new BoardDao ();
		if(proc!=null) {
			if(proc.equals("upt")) {
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				BoardDTO upt = new BoardDTO(num,title,content);
				dao.updateNotice(upt);
			}
			if(proc.equals("del")) {
				System.out.println("삭제 준비 완료");
				dao.deleteNotice(num);
			}
		}
		request.setAttribute("dto", dao.getWrite2(num));
		// 3. view단 처리
		String page ="view\\board\\example2.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
