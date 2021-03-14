package board.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration; 

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
		String realFolder = "";

		String saveFolder = "upload";
		String encType ="utf-8";
		int maxSize = 5*1024*1024;

		ServletContext context = getServletContext();
		realFolder = context.getRealPath(saveFolder);
		System.out.println("the realpath is : " + realFolder + "<br>");

		try
		{
			MultipartRequest multi = null;

			multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());

			Enumeration params = multi.getParameterNames();

			while(params.hasMoreElements())
			{
				String name = (String)params.nextElement();
				String value = multi.getParameter(name);
				System.out.println(name + "=" + value + "<br>");
			}

			System.out.println("------------------------<br>");

			Enumeration files = multi.getFileNames();

			while(files.hasMoreElements())
			{
				String name = (String)files.nextElement();
				String filename = multi.getFilesystemName(name);
				String original = multi.getOriginalFileName(name);
				String type = multi.getContentType(name);
				File file = multi.getFile(name);

				System.out.println("파라미터 이름 : " + name + "<br>");
				System.out.println("실제 파일 이름 : " + original + "<br>");
				System.out.println("저장된 파일 이름 : " + filename +"<br>");
				System.out.println("파일 타입 : " + type + "<br>");

				if(file!=null)
				{
					System.out.println("크기 : " + file.length());
					System.out.println("<br>");
				}				
				
				
				String category = multi.getParameter("category");
				String title = multi.getParameter("title"); 
				String content = multi.getParameter("content");
				String file1 = multi.getParameter("file");
				// 2. 모델 작성
				if(category==null) category="";
				if(title==null) title="";
				if(content==null) content="";
				if(file1==null) file1="";
				log("#category:"+category);
				log("#title:"+title);
				log("#content:"+content);
				log("#file:"+file1);
				if(!title.equals("")) {
					BoardDTO ins = new BoardDTO(title,category,content);
					log("# 입력내용 확인:"+ins.getTitle());
					BoardDao dao = new BoardDao();
					dao.insertWrite(ins);
				}
			}
		}
		catch(IOException ioe)
		{
			System.out.println(ioe);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		} 
		
		

				
		// db 처리
			BoardDao dao = new BoardDao();
			request.setAttribute("blist", dao.boardList());
		
		// view단 호출
		String page="view\\board\\board_write.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
		
		
		
	}
	
}








