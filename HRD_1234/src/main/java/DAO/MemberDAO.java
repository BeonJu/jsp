package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Member;
import DTO.Money;

public class MemberDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;	 
	
	public static Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection
				("jdbc:oracle:thin:@//localhost:1524/xe","system","sys1234");
		return con;
	}
	
	public String nextCustno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			conn = getConnection();
			// 회원번호+1 가져오기
			String sql = "SELECT max(custno)+1 custno FROM member_tbl_02";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			int custno = 0;
			if(rs.next()) custno = rs.getInt(1);
			
			request.setAttribute("custno", custno);
			conn.close();
			ps.close();
			rs.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		} 
		return "add.jsp";
	}
	
	public String selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Member>list = new ArrayList<Member>();
		try {
			conn = getConnection();
			// 회원리스트 가져오기
			String sql = "SELECT custno, custname, phone, address, TO_CHAR(joindate,'YYYY-MM-DD') joindate,";
				   sql+= "DECODE(grade,'A','VIP','B','일반','직원') grade, city FROM member_tbl_02 ORDER BY custno";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Member member = new Member();
				member.setCustno(rs.getInt(1));
				member.setCustname(rs.getString(2));
				member.setPhone(rs.getString(3));
				member.setAddress(rs.getString(4));
				member.setJoindate(rs.getString(5));
				member.setGrade(rs.getString(6));
				member.setCity(rs.getString(7));
				list.add(member);
			}		
			request.setAttribute("list",list);
			
			conn.close();
			ps.close();
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "list.jsp";
	}
	public String modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Member>list = new ArrayList<Member>();
		try {
			conn = getConnection();
			int custno = Integer.parseInt(request.getParameter("custno"));
			// 수정할 회원정보 가져오기
			String sql = "SELECT custname, phone, address, TO_CHAR(joindate,'YYYY-MM-DD') joindate, grade, city ";
			       sql+= "FROM member_tbl_02 WHERE custno ="+custno;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
			Member member = new Member();
			member.setCustno(custno);
			member.setCustname(rs.getString(1));
			member.setPhone(rs.getString(2));
			member.setAddress(rs.getString(3));
			member.setJoindate(rs.getString(4));
			member.setGrade(rs.getString(5));
			member.setCity(rs.getString(6));
			list.add(member);
			}
				
			request.setAttribute("list",list);	
			request.setAttribute("custno", custno);
			
			conn.close();
			ps.close();
			rs.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "modify.jsp";
	}
	public String selectResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Money>list = new ArrayList<Money>();
		try {
			conn = getConnection();
			// 회원번호+1 가져오기
			String sql = "SELECT m1.custno, m1.custname, DECODE(m1.grade,'A','VIP','B','일반','직원')grade, SUM(m2.price)price ";
			       sql+= "FROM member_tbl_02 m1, money_tbl_02 m2 ";
			       sql+= "WHERE m1.custno = m2.custno ";
			       sql+= "GROUP BY (m1.custno, m1.custname, grade) ";
			       sql+= "ORDER BY price DESC";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Money money = new Money();
				money.setCustno(rs.getInt(1));
				money.setCustname(rs.getString(2));
				money.setGrade(rs.getString(3));
				money.setPrice(rs.getInt(4));
				list.add(money);
			}
			request.setAttribute("list", list);
			
			conn.close();
			ps.close();
			rs.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "result.jsp";
	}
	public String insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int custno = Integer.parseInt(request.getParameter("custno"));
		String custname = request.getParameter("custname");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String joindate = request.getParameter("joindate");
		String grade = request.getParameter("grade");
		String city = request.getParameter("city");
		int result = 0;
		try {
			conn = getConnection();
			// prepareStatement는 순서에 따라 값이 등록된다.
			String sql = "INSERT INTO member_tbl_02 VALUES(?,?,?,?,TO_DATE(?,'YYYY-MM-DD'),?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, custno);
			ps.setString(2, custname);
			ps.setString(3, phone);
			ps.setString(4, address);
			ps.setString(5, joindate);
			ps.setString(6, grade);
			ps.setString(7, city);
			result = ps.executeUpdate();	
			System.out.println(result);
			conn.close();
			ps.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "add";
	}
	public int update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int custno = Integer.parseInt(request.getParameter("custno"));
		String custname = request.getParameter("custname");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String joindate = request.getParameter("joindate");
		String grade = request.getParameter("grade");
		String city = request.getParameter("city");
		int result = 0;
		try {
			conn = getConnection();
			
			String sql = "UPDATE member_tbl_02 SET";
				   sql+= " custname = ? , ";
				   sql+= " phone = ? , ";
				   sql+= " address = ? , ";
				   sql+= " joindate = TO_DATE(?,'YYYY-MM-DD'), ";
				   sql+= " grade = ? , ";
				   sql+= " city = ? ";
				   sql+= " WHERE custno = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, custname);
			ps.setString(2, phone);
			ps.setString(3, address);
			ps.setString(4, joindate);
			ps.setString(5, grade);
			ps.setString(6, city);
			ps.setInt(7, custno);
			result = ps.executeUpdate();	
			
			conn.close();
			ps.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
		try {
			conn = getConnection();
			String custno = request.getParameter("custno");
			String sql = "DELETE FROM member_tbl_02 WHERE custno="+custno;
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
			
			conn.close();
			ps.close();			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
