package board.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class writeController
 */
@WebServlet(name = "write.do", urlPatterns = { "/write.do" })
public class writeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public writeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    // get방식으로 접근했을 때 발생하는 메서드
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1. 요청값 작성
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		// 요청값 처리
		String id = (String)session.getAttribute("id");
		System.out.println("##id값:"+id);
		
		String category = request.getParameter("category"); 
		request.setAttribute("category", category);
		
		if(category ==null) category="";
		String title = request.getParameter("title"); 
		if(title ==null) title="";
		String content = request.getParameter("content"); 
		if(content ==null) content="";
		log("#category:"+category);log("#title:"+title);log("#content:"+content);
		// db 처리
		if(!title.equals("")) {
			BoardDao dao = new BoardDao();
			BoardDTO ins = new BoardDTO(id,title,category,content);
			dao.insertWrite(ins);
			// postcnt ++ dao
			dao.PlusPostCnt(id);
		};
		// view단 호출
		
		String page="view\\board\\board_write.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);		
		
	}
	
}








