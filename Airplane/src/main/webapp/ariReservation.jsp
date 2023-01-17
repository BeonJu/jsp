<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>N사 항공권 예약 사이트</title>
<link rel="stylesheet" href="index.css">
<script src="Air.js" type="text/javascript"></script>
</head>
<body>
	<div class="title">
		<h3>N사 항공권 예약 사이트</h3>
	</div>
	<nav>
		<div class="menu">
			<a href="home"><button class="btMenu">항공권 예약</button></a> <a
				href="list"><button class="btMenu">항공권 전체 조회</button></a> <a
				href="reservation"><button class="btMenu">비회원 항공권 예약 조회</button></a>
		</div>
	</nav>
	<section>
		<div class="Wrapper">
			<div class="searcheWrapper">
				<form name="searchFrom" action="search">
					<div class="way">
						<span>항공권 예약</span>
					</div>
					<div class="selectWrapper">
						<label for="r_name">비회원 성함</label> <input type="text"
							name="r_name" class="r-name" placeholder="성함 입력">
						<button type="submit" name="searchSubmit">티켓 검색</button>
					</div>
				</form>
			</div>
			<div class="searchList">
				<table>
					<thead>
						<tr>
							<th>Ticket.No</th>
							<th>Customer.No</th>
							<th>성함</th>
							<th>연락처</th>
							<th>항공사</th>
							<th>출발 국가</th>
							<th>도착 국가</th>
							<th>출발 날자</th>
							<th>도착 날자</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="reservationTicket" items="${rList}"
							varStatus="status">
							
							<tr>
							<form action="delete" method="get">
								<td>${reservationTicket.PT_NO }</td>
								<td>${reservationTicket.CUST_NO }</td>
								<td>${reservationTicket.CUST_NAME }</td>
								<td>${reservationTicket.CUST_PHONE }</td>
								<td>${reservationTicket.AL_NAME }</td>
								<td>${reservationTicket.START_COUNTRY}</td>
								<td>${reservationTicket.END_COUNTRY }</td>
								<td>${reservationTicket.START_DAY}</td>
								<td>${reservationTicket.END_DAY}</td>
								<td><button type="submit">티켓 취소하기</button></td>
								</form>
								<td><form action="update?name=${reservationTicket.CUST_NAME}&custNo=${reservationTicket.CUST_NO }" method="get" ><button type="submit" name="phone" value="">연락처 변경하기</button></form></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</section>


	<script>
    	<c:if test="${param.error != null}">
    		alert("${param.error}");
    	</c:if>
    	<c:if test="${error != null}">
			alert("${error}");
	    </c:if>
    </script>
</body>
</html>