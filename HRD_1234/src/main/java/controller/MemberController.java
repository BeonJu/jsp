package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;

@WebServlet("/")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}

	protected void doPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 프로젝트 + 파일경로까지 가져옵니다. request.getRequestURI() 
		 * 예) http://localhost:8080/project/list.jsp [return] /project/list.jsp
		 * 
		 * 전체 경로를 가져옵니다. request.getRequestURL() 
		 * 예) http://localhost:8080/project/list.jsp [return]
		 * http://localhost:8080/project/list.jsp
		 * 
		 * 파일명만 가져옵니다. request.getServletPath() 
		 * 예) http://localhost:8080/project/list.jsp
		 * [return] /list.jsp
		 * 
		 */
		

		String context = request.getContextPath(); //톰캣의 Context path를 가져온다(server.xml에서 확인)
		String command = request.getServletPath();
		String site = null;

		System.out.println("getContextPath:" + request.getContextPath());
		System.out.println("getServletPath:" + request.getServletPath());
		System.out.println("command:" + command);

		MemberDAO member = new MemberDAO();

		switch (command) {
		case "/home":
			site = "index.jsp";
			break;

		case "/add":
			site = member.nextCustno(request, response);
			break;

		case "/list":
			site = member.selectAll(request, response);
			break;

		case "/result":
			site = member.selectResult(request, response);
			break;
		case "/insert":
			site = member.insert(request, response);

			break;
		case "/modify":
			site = member.modify(request, response);
			break;
		case "/update":
			int result1 = member.update(request, response);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if (result1 == 1) {
				out.println("<script>");
				out.println(" alert('회원수정이 완료 되었습니다!'); location.href='" + context + "';  ");
				out.println("</script>");
				out.flush();
			} else {
				out.println("<script>");
				out.println("alert('수정실패!'); location.href='" + context + "'; ");
				out.println("</script>");
				out.flush();
			}
			break;
		case "/delete":
			int result2 = member.delete(request, response);
			response.setContentType("text/html; charset=UTF-8");
			out = response.getWriter();
			if (result2 == 1) {
				out.println("<script>");
				out.println(" alert('회원삭제가 완료 되었습니다!'); location.href='" + context + "';  ");
				out.println("</script>");
				out.flush();
			} else {
				out.println("<script>");
				out.println("alert('삭제실패!'); location.href='" + context + "'; ");
				out.println("</script>");
				out.flush();
			}
			break;
		}

		//RequestDispatcher dispatcher = request.getRequestDispatcher(site);
		//dispatcher.forward(request, response);
		
		getServletContext().getRequestDispatcher("/"+site).forward(request, response);
	}

}
