package project.mvc.main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.dto.BoardDTO;
import project.dao.main.MainLeft_Dao;
import project.dao.main.Main_Dao;
import project.vo.login.Member;

/**
 * Servlet implementation class MainLeftController
 */
@WebServlet(name = "mainLeft.do", urlPatterns = { "/mainLeft.do" })
public class MainLeftController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainLeftController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		// 1. 요청값 처리..
		String id = request.getParameter("id"); 
		String pass = request.getParameter("pass");
		MainLeft_Dao dao = new MainLeft_Dao();
		Main_Dao dao2 = new Main_Dao(); // 전체글 가져오기 DAO 
		ArrayList<BoardDTO> blist = dao2.boardAllList();
		request.setAttribute("blist", blist); 
		
		
		// 2. 모델 데이터 처리
		//    1) 초기 페이지 - 초기 로그인 페이지 설정.
		//    2) 입력 후 페이지. - 입력 후 정상일 때 페이지 설정.
		if(id==null) id="";
		if(pass==null) pass="";
		String page = "login/login.jsp";
		if(!id.equals("") && !pass.equals("") ) {
			Member mem = dao.login(new Member(id,pass));
			if(mem!=null) {
			//	request.setAttribute("isSuccess", true);
				// DB 연동의 경우, session값을 설정해서 model데이터를 매핑한다.
				session.setAttribute("member", dao.login(mem));
				session.setAttribute("grade", dao.getGrade(mem));
				page="main.jsp";
			}else {
			//	request.setAttribute("isSuccess", false);
			}
		}	
		// 3. 화면단 호출.
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}
}
