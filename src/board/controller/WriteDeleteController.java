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
		HttpSession session = request.getSession();
		String id=(String) request.getAttribute("id");
		String num = null;
		if(request.getParameter("num")!=null) {
			num = request.getParameter("num");
		}
		BoardDao dao = new BoardDao();
		if(id!=null) {
			int result = new BoardDao().delete(num);
			if(id.equals(dao.getId(num))&&result==1) {
				response.setCharacterEncoding("UTF-8"); 
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('삭제 완료');");
				script.println("location.href='boardList.do'");
				script.println("</script>");
			}else {
				response.setCharacterEncoding("UTF-8"); 
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter script = response.getWriter();				
				script.println("<script>");
				script.println("alert('본인이 작성한 글만 삭제 가능합니다.');");
				script.println("history.back();");
				script.println("</script>");
			}
		}else if(id==null) {
			response.setCharacterEncoding("UTF-8"); 
			response.setContentType("text/html; charset=UTF-8");			
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인창으로 이동합니다.');");
			script.println("location.href='login.do'");
			script.println("</script>");			
		}
		
		String page ="view\\board\\board_list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
		
	}

}
