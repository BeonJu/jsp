package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


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
	public void insertCustomer(CUSTOMERS customers) throws SQLException {
		Connection conn = open();

		String sql = "insert into CUSTOMERS VALUES(CUST_NO_SEQ.nextval, ?, ?)";
		PreparedStatement pr = conn.prepareStatement(sql);
		
		CUSTOMERS customersSub = new CUSTOMERS();
		customersSub.setCUST_NAME(customers.getCUST_NAME());
		customersSub.setCUST_PHONE(customers.getCUST_PHONE());
		customersSub.setPT_NO(customers.getPT_NO());
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
	}

	//항공권 예약
	public void Reservation( CUSTOMERS customers) throws Exception {
		Connection conn = open();
		System.out.println(customers.getCUST_NAME());
		System.out.println(customers.getCUST_PHONE());
		System.out.println(customers.getPT_NO());
		String name = customers.getCUST_NAME();
		String sql = "SELECT cust_no FROM CUSTOMERS where cust_name =";
		sql += "'"+name+"'";
		PreparedStatement pr = conn.prepareStatement(sql);
		ResultSet rs = pr.executeQuery();
		rs.next();
		RESERVATIONS ticketReservation = new RESERVATIONS();
		try (conn; pr; rs) {
				
				ticketReservation.setPT_NO(customers.getPT_NO());
				ticketReservation.setCUST_NO(rs.getString(1));
				System.out.println("ticketReservation.getPT_no:"+ticketReservation.getPT_NO());
				System.out.println("ticketReservation.getCust_no:"+ticketReservation.getCUST_NO());
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
			pr.close();
			rs.close();
		}
		
		Connection conn2 = open();
		String sql2 = "insert into RESERVATION values(?, ?)";
		PreparedStatement pr2 = conn2.prepareStatement(sql2);
	
		try (conn2; pr2) {
			
			pr2.setString(1, ticketReservation.getPT_NO());
			pr2.setString(2, ticketReservation.getCUST_NO());
			pr2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn2.close();
			pr2.close();
		}
	
	}
	
	
	
	// 티켓 리스트 가져 오기
	public ArrayList<RESERVATION> search(CUSTOMERS customers) throws SQLException {
		Connection conn = open();
		ArrayList<RESERVATION> Rlist = new ArrayList<RESERVATION>();
		String name = customers.getCUST_NAME();
		String sql = "select p.pt_no, c.cust_no, c.cust_name, c.cust_phone, p.al_name, p.start_country, p.end_country, p.start_day, p.end_day ";
		sql += "from plane_tickets p,customers c, reservation r ";
		sql += "where c.cust_name =" + "'" +name+"'"+" and p.pt_no = r.pt_no and c.cust_no = r.cust_no";
		PreparedStatement pr = conn.prepareStatement(sql);
		ResultSet rs = pr.executeQuery();

		try (conn; pr; rs) {
			while (rs.next()) {
				RESERVATION reservationTicket = new RESERVATION();
				reservationTicket.setPT_NO(rs.getString(1));
				reservationTicket.setCUST_NO(rs.getString(2));
				reservationTicket.setCUST_NAME(rs.getString(3));
				reservationTicket.setCUST_PHONE(rs.getString(4));
				reservationTicket.setAL_NAME(rs.getString(5));
				reservationTicket.setSTART_COUNTRY(rs.getString(6));
				reservationTicket.setEND_COUNTRY(rs.getString(7));
				reservationTicket.setSTART_DAY(rs.getDate(8));
				reservationTicket.setEND_DAY(rs.getDate(9));
				Rlist.add(reservationTicket);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			pr.close();
			rs.close();
		}
		return Rlist;
	}

	
	
	
	
	public int deleteTicekt(RESERVATION reservation) throws SQLException{
		RESERVATION reservation2 = new RESERVATION();
		reservation2.setCUST_NO(reservation.getPT_NO());
		reservation2.setCUST_NAME(reservation.getCUST_NAME());
		reservation2.setCUST_PHONE(reservation.getCUST_PHONE());
		reservation2.setPT_NO(reservation.getPT_NO());
		int result = 0;
		
		//customer 정보 삭제
		Connection conn = open();
		String sql =  "DELETE FROM CUSTOMERS WHERE cust_no = "+"'"+reservation2.getCUST_NO()+"'";
		sql += " and cust_name = "+"'"+reservation2.getCUST_NAME()+"'";
		sql += " and cust_phone = "+"'"+reservation2.getCUST_PHONE()+"'";
		PreparedStatement pr = conn.prepareStatement(sql);
		//??
		try(conn; pr) {
			result = pr.executeUpdate();
			if(result == 0) {
				
				conn.close();
				pr.close();
				
				return result;}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
			pr.close();
		}
		
		//reservation 정보 삭제
		Connection conn2 = open();
		String sql2 =  "DELETE FROM reservation WHERE cust_no = "+"'"+reservation2.getCUST_NO()+"'";
		sql2 += " and pt_no = "+"'"+reservation2.getPT_NO()+"'";
		PreparedStatement pr2 = conn2.prepareStatement(sql2);
		
		try(conn2; pr2) {
			result = pr2.executeUpdate();
			if(result == 0) {
				
				conn2.close();
				pr2.close();
				
				return result;}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn2.close();
			pr2.close();
		}
		
		return result;
	}
	
	
	
	public int updatePhone(RESERVATION reservation) throws SQLException {
	
		RESERVATION reservation2 = new RESERVATION();
		reservation2.setCUST_NO(reservation.getPT_NO());
		reservation2.setCUST_NAME(reservation.getCUST_NAME());
		reservation2.setCUST_PHONE(reservation.getCUST_PHONE());
		int result = 0;
		
		//customer 정보 수정
		Connection conn = open();
		String sql =  "UPDATE CUSTOMERS SET cust_phone = ? "; 
		sql += "where cust_no = " + "'" + reservation2.getCUST_NO() + "'";
		sql += " and cust_name = " + "'" + reservation2.getCUST_NAME() + "'";
		
		PreparedStatement pr = conn.prepareStatement(sql);
		
		try(conn; pr) {
			pr.setString(1, reservation2.getCUST_PHONE());
			result = pr.executeUpdate();
			if(result == 0) {
				
				conn.close();
				pr.close();
				
				return result;}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
			pr.close();
		}
		
		return result;
	}

}
