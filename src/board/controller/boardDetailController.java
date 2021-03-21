package board.controller;

import java.io.IOException;

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
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int readcount = Integer.parseInt(readcountS); // 조회수 처리
		String proc = request.getParameter("proc"); // 수정 삭제 처리를 위함
		String category = request.getParameter("category");
		BoardDao dao = new BoardDao();
		HttpSession session = request.getSession();
		String id=(String) session.getAttribute("id"); // session id
		
		
		String idW =dao.getId(num);  // 글작성 id
		System.out.println("##session id:"+id);
		System.out.println("##작성자 id:"+idW);
		System.out.println("##글번호:"+num);
		System.out.println("##카테고리:"+category);
		System.out.println("##proc:"+proc);
		// 2. 모델 처리
		
		dao.updateCount(num);
		request.setAttribute("dto", dao.getWrite(num,category));
		if(proc!=null) {
			if(proc.equals("del")) {
				BoardDTO del = new BoardDTO(num,category);
				dao.delete(del);
			}
			if(proc.equals("upt")) {
				BoardDTO upt = new BoardDTO(num,title,content);
				dao.updateWrite(upt);
			}
		}
		// 3. view단 처리
		String page ="view\\board\\example.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
