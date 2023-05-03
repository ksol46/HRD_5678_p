package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Dao;

@WebServlet("/")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MainController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doPro(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doPro(request, response);

	}

	protected void doPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String context = request.getContextPath();
		String command = request.getServletPath();
		String site = null;
		
		System.out.println(context + "/" + command);
		
		Dao dao = new Dao();
		
		switch(command) {
		case "/home":
			site = "index.jsp";
			break;
		case "/search":
			site = dao.search(request, response);
			break;
		case "/vote":
			site = "insert.jsp";
			break;
		case "/add":
			int result1 = dao.vote(request, response);
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			if(result1 ==1) {
				out.println("<script>");
				out.println("alert('투표하기 정보가 정상적으로 등록 되었습니다!'); location.href='"+context+"'; ");
				out.println("</script>");
				out.flush();
			}else {
				out.println("<script>");
				out.println("alert('등록실패!'); location.href='"+context+"'; ");
				out.println("</script>");
				out.flush();
			}
			break;
		case "/inquire":
			site = dao.inquire(request, response);
			break;
		case "/grade":
			site = dao.grade(request, response);
			break;
		}
		
		getServletContext().getRequestDispatcher("/"+site).forward(request, response);
	}

}
