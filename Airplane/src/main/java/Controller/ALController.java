package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;


import DAO.ALDAO;
import DTO.CUSTOMERS;
import DTO.RESERVATION;
import DTO.RESERVATIONS;
import DTO.TICKETS;

@WebServlet("/")
public class ALController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ALDAO dao;
	private ServletContext ctx;

	public ALController() {

	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// init은 서블릿 객체 생성시 딱 한번만 실행하므로 객체를 한번만 생성해 공유할 수 있다.
		dao = new ALDAO();
		ctx = getServletContext(); // ServletContext: 웹 어플리케이션의 자원 관리
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
		String context = request.getContextPath(); // 톰캣 서버 루트 주소
		String command = request.getServletPath(); // 서블릿 주소
		String site = null; // 서블릿 주소 뒤에 넣어줄 URL

		switch (command) {
		case "/home":
			site = getList(request);
			break;

		case "/list":
			site = getList(request);
			break;
		case "/custInsert":
			site = insertCustomer(request);
			break;
			
		case "/search":
			site = searchName(request);
			break;
			
		case "/delete":
			site = deleteTickets(request);
			break;
			
		case "/update":
			site = searchName(request);
			break;

		case "/reservation":
			site = "ariReservation.jsp";
			break;
		}

		getServletContext().getRequestDispatcher("/" + site).forward(request, response);

	}

	//DB에 저장 되있는 항공 티켓 리스트 전체 호출
	public String getList(HttpServletRequest request) {
		List<TICKETS> tlist;

		try {
			tlist = dao.getList();
			request.setAttribute("tlist", tlist);

			
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("티켓 목록 호출 과정에서 문제 발생");
			// 나중에 사용자 한테 에러메세지를 보여주기 위해 저장
			request.setAttribute("error", "티켓 목록을 정상적으로 불러 오지못했습니다!");
		}

		return "ariMain.jsp";
	}

	//비회원 고객 정보 저장
	public String insertCustomer(HttpServletRequest request) {
		
		CUSTOMERS customers = new CUSTOMERS();
		CUSTOMERS sub = new CUSTOMERS();

		try {	
			
			customers.setCUST_NAME(request.getParameter("name"));
			customers.setCUST_PHONE(request.getParameter("phone"));
			customers.setPT_NO(request.getParameter("ticket_no"));
			//고객 정보 저장
			dao.insertCustomer(customers);
			//티켓 예약 저장
			dao.Reservation(customers);
			
			
//			request.setAttribute("insertCustomers", insertCust);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "ariReservation.jsp";
	}

	
	//티켓 번호와 고객 번호를 예약 테이블에 저장
	public String searchName(HttpServletRequest request) {

		List<RESERVATION> rList;
		CUSTOMERS reservationName = new CUSTOMERS();

		try {
			reservationName.setCUST_NAME(request.getParameter("r_name"));
//			BeanUtils.populate(reservationName, request.getParameterMap());
			rList = dao.search(reservationName);
			request.setAttribute("rList", rList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "ariReservation.jsp";
	}


	
	
	
	//티켓 및 비회원 정보 삭제
	public String deleteTickets(HttpServletRequest request) {
		
		RESERVATION reservation = new RESERVATION();
		int result;

		try {	
			
			reservation.setCUST_NAME(request.getParameter("name"));
			reservation.setCUST_PHONE(request.getParameter("phone"));
			reservation.setCUST_NO(request.getParameter("ticket_no"));
			reservation.setPT_NO(request.getParameter(""));
			//고객 튜플 및 예약 튜플 삭제
			result = dao.deleteTicekt(reservation);
			request.setAttribute("resultDelete", result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "ariReservation.jsp";
	}
	
	
	
	
	
	//티켓 예약 한 비회원 연락처 변경
	public String updateTicketPhone(HttpServletRequest request) {
		
		RESERVATION reservation = new RESERVATION();
		int result;

		try {	
			
			reservation.setCUST_NAME(request.getParameter("name"));
			reservation.setCUST_PHONE(request.getParameter("phone"));
			reservation.setCUST_NO(request.getParameter("custNo"));
			//고객 튜플 및 예약 튜플 삭제
			result = dao.updatePhone(reservation);
			request.setAttribute("resultUpdate", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ariReservation.jsp";
	}
}
