package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
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

		case "/Reservation":
			site = Reservation(request);
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
		CUSTOMERS insertCust;

		try {
			BeanUtils.populate(customers, request.getParameterMap());
		
			insertCust = dao.insertCustomer(customers);

			request.setAttribute("insertCustomers", insertCust);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Reservation";
	}

	
	//티켓 번호와 고객 번호를 예약 테이블에 저장
	public String Reservation(HttpServletRequest request) {

		CUSTOMERS customers = new CUSTOMERS();

		try {
			BeanUtils.populate(customers, request.getParameterMap());
			dao.Reservation(customers);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "home";
	}

//	public String insertReservations(HttpServletRequest request) {
//		RESERVATIONS reservations = new RESERVATIONS();
//		
//		try {
//		BeanUtils.populate(reservations, request.getParameterMap());
//		dao.insert(reservations);
//		
//		} catch (Exception e) {
//			 e.printStackTrace();
//		}
//		
//		return "index2.jsp";
//	}

}
