package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import DTO.CUSTOMERS;
import DTO.RESERVATION;
import DTO.RESERVATIONS;
import DTO.TICKETS;

public class ALDAO {
	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";

	// 데이터 베이스와의 연결 수행 메소드
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "air", "air1234");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

	// 티켓 리스트 가져 오기
	public ArrayList<TICKETS> getList() throws SQLException {
		Connection conn = open();
		ArrayList<TICKETS> tlist = new ArrayList<TICKETS>();

		String sql = "SELECT al_name, start_country, end_country,start_day, end_day, pt_price, pt_no FROM plane_tickets";
		PreparedStatement pr = conn.prepareStatement(sql);
		ResultSet rs = pr.executeQuery();

		try (conn; pr; rs) {
			while (rs.next()) {
				TICKETS ticket = new TICKETS();
				ticket.setAL_NAME(rs.getString(1));
				ticket.setSTART_COUNTRY(rs.getString(2));
				ticket.setEND_COUNTRY(rs.getString(3));
				ticket.setSTART_DAY(rs.getDate(4));
				ticket.setEND_DAY(rs.getDate(5));
				ticket.setPT_PRICE(rs.getInt(6));
				ticket.setPT_NO(rs.getString(7));

				tlist.add(ticket);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			pr.close();
			rs.close();
		}
		return tlist;
	}

	//DB에 고객 이름, 연락처 저장
	public CUSTOMERS insertCustomer(CUSTOMERS customers) throws Exception {
		Connection conn = open();

		String sql = "insert into CUSTOMERS values(CUST_NO_SEQ.nextval, ?, ?)";
		PreparedStatement pr = conn.prepareStatement(sql);
		//CUSTOMERS customersSub = customers;
		try (conn; pr) {
			
			pr.setString(1, customers.getCUST_NAME());
			pr.setString(2, customers.getCUST_PHONE());
			pr.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			pr.close();
		}

		return customers;
	}

	//항공권 예약
	public void Reservation( CUSTOMERS customers) throws Exception {
		Connection conn = open();

		String sql = "SELECT pt_no,cust_no FROM CUSTOMERS, RESERVATION where CUSTOMERS.cust_name=" + customers.getCUST_NAME() + " and RESERVATION.pt_no = " + customers.getPT_NO();
		PreparedStatement pr = conn.prepareStatement(sql);
		ResultSet rs = pr.executeQuery();
		RESERVATIONS ticketReservation = new RESERVATIONS();
		try (conn; pr; rs) {
				
				ticketReservation.setPT_NO(rs.getString(1));
				ticketReservation.setCUST_NO(rs.getString(2));
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
			pr.close();
			rs.close();
		}
		
		
		sql = "insert into RESERVATION values(?, ?)";
		PreparedStatement pr2 = conn.prepareStatement(sql);
	
		try (conn; pr2) {
			
			pr.setString(1, ticketReservation.getPT_NO());
			pr.setString(2, ticketReservation.getCUST_NO());
			pr2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
			pr2.close();
		}
	
	}

}
