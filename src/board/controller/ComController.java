package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.dao.comment_dao;
import board.dao.table_dao;
import project.vo.login.Member;

/**
 * Servlet implementation class ComController
 */
@WebServlet(name = "com.do", urlPatterns = { "/com.do" })
public class ComController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public String pStr(String s) {
    	String ret="";
    	if( s != null) ret = s;
    	return ret;
    }
    
    public int pInt(String s) {
    	int ret = 0;
    	try {
    		ret = Integer.parseInt(s);
    	} catch(Exception e) {
    		System.out.println("pInt: " + e.getMessage());
    	}
    	return ret;
    }
    
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		String id = ""; String page = "";
		int table_id = 0;	int text_id = 0; String cate = "";
		String table_idS = request.getParameter("tnum");
		String text_idS = request.getParameter("wnum");
		//String comment_idS = request.getParameter("comment_id");
		//String comment_type = request.getParameter("comment_type"); //comment_type = pStr(comment_type);
		//String comment = request.getParameter("comment"); //comment = pStr(comment);
		System.out.println("table_idS1: " + table_idS);
		System.out.println("text_idS1: " + text_idS);
		HttpSession session = request.getSession(false);
		
		if(table_idS != null) { session.setAttribute("table_id", new Integer(table_idS)); }
		if(text_idS != null) { session.setAttribute("text_id", new Integer(text_idS)); }
		Member m = (Member)session.getAttribute("member");
		
		if(session != null) {
			id = m.getId();
			//id = (String)session.getAttribute("id");
			table_id = (Integer)session.getAttribute("table_id");
			text_id = (Integer)session.getAttribute("text_id");
			//cate = (String)session.getAttribute("cate");
			//System.out.println("cate: " + cate);
			
			page = "view\\board\\comment.jsp";
		} else
			page = "comment\\login.jsp";
		
		System.out.println("table_id2: " + table_id);
		System.out.println("text_id2: " + text_id);
		
//		System.out.println("id: " + id);
//		System.out.println("tnum: " + table_id);
//		System.out.println("wnum: " + text_id);
//		System.out.println("comment_type: " + comment_type);
//		System.out.println("comment: " + comment);
		
		comment_dao dao = new comment_dao();
		//table_dao tdao = new table_dao();
		//ListDao ldao = new ListDao();
		
		String proc = request.getParameter("proc");
		if(proc != null) {
			if(proc.equals("ins")) {
				System.out.println("등록!!");
				if(pInt(request.getParameter("comment_type")) == 1){
					System.out.println("댓글!!");
				dao.insertComment(table_id, text_id, pInt(request.getParameter("comment_type")),
						id,pStr(request.getParameter("comment")));
				} else if (pInt(request.getParameter("comment_type")) == 2){
					System.out.println("대댓글!!");
					dao.insertSubComment(table_id, text_id, pInt(request.getParameter("comment_id")),
							pInt(request.getParameter("comment_type")),
							id,pStr(request.getParameter("comment")));
				}
				int commentCnt = dao.getMemComCnt(id);
				// member 테이블의 댓글작성카운트++
				System.out.println("작성 갯수: " + commentCnt);
				dao.PlusComCnt(id, commentCnt);
				commentCnt = dao.getMemComCnt(id);
				System.out.println("작성 갯수(update): " + commentCnt);
				// 댓글에 대한 상태 저장 테이블, 새로운 열 추가 ( 내가 생성한 것이기 때문에 필요없음)
				/*
				dao.insertLike(id, table_id, text_id);
				System.out.println("새로운 댓글에 대한 좋아요 상태 확인 객체 생성!!");
				*/
			}
			if(proc.equals("del")) {
				System.out.println("삭제" + (request.getParameter("cnt")));
				dao.delComment(id, pInt((request.getParameter("cnt"))));
				int commentCnt = dao.getMemComCnt(id);
				System.out.println("작성 갯수: " + commentCnt);
				dao.MinusComCnt(id, commentCnt);
				commentCnt = dao.getMemComCnt(id);
				System.out.println("작성 갯수(update): " + commentCnt);
			}
			if(proc.equals("like")) {
				System.out.println("좋아요(cnt): " + pInt(request.getParameter("cnt")));
				// type 0 : 게시글에 대한 좋아요 , 1 : 댓글, 대댓글 ==> cnt로 구분함
				System.out.println("좋아요(type): " + pStr(request.getParameter("type")));
				System.out.println("작성자(wid): " + pStr(request.getParameter("wid")));
				String type = request.getParameter("type");
				if(type.equals("1")) {
					// goodorhate 테이블에 있는지 확인
					// 있다면 update, 없다면 생성 후 update
					dao.UpdateGood(id, pStr(request.getParameter("wid")),
							table_id, text_id, pInt(request.getParameter("cnt")));
					// 작성자 id 보내야함
					dao.UpdateLike(pStr(request.getParameter("wid")),
							id, table_id, text_id, pInt(request.getParameter("cnt")));
				} else {
					dao.UpdateGood(id, pStr(request.getParameter("wid")), table_id, text_id, 0);
					dao.UpdateLike(pStr(request.getParameter("wid")),id, table_id, text_id, 0);
				}
			}
		}
		
		
		
		// 글 내용 가져오기 ( tab 객체 1개)
		//request.setAttribute("content",tdao.getTab(table_id,text_id));
		//request.setAttribute("content", ldao.categoryList(""));
		// 게시판id, 글id , 회원아이디 가져와야함
		request.setAttribute("comList", dao.commentaryList());
		// 해당 게시글 안의 댓글 총 숫자
		request.setAttribute("getComTotalCnt",dao.getComTotalCnt(table_id, text_id));
		
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
