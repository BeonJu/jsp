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


</head>
<body>
	<div class="title">
		<h3>N사 항공권 예약 사이트</h3>
	</div>
	<nav>
		<div class="menu">
			<a href="home"><button class="btMenu">항공권 예약</button></a> 
			<a href="list"><button class="btMenu">항공권 전체 조회</button></a>
			<a href="reservation"><button class="btMenu">비회원 항공권 예약 조회</button></a>
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
						<select name="startCountry">
							<option value=""></option>
							<option value="김포">김포</option>
						</select> <select name="endCountry">
							<option value=""></option>
							<option value="제주">제주</option>
							<option value="오키나와">오키나와</option>
							<option value="삿포로">삿포로</option>
						</select>
						<button type="submit" name="searchSubmit">티켓 검색</button>
					</div>
				</form>
			</div>
			<div class="searchList">
			
				<table>
					<thead>
						<tr>
							<th>Ticket.No</th>
							<th>항공사</th>
							<th>출발 국가</th>
							<th>도착 국가</th>
							<th>출발 날자</th>
							<th>도착 날자</th>
							<th>가격</th>
							<th>예약 하기</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="ticket" items="${tlist}" varStatus="status">
						
							<tr>
								<td>${ticket.PT_NO }</td>
								<td>${ticket.AL_NAME }</td>
								<td>${ticket.START_COUNTRY}</td>
								<td>${ticket.END_COUNTRY }</td>
								<td>${ticket.START_DAY}</td>
								<td>${ticket.END_DAY}</td>
								<td>${ticket.PT_PRICE }원</td>
								<td><button type="button" value="${ticket.PT_NO }"
									onclick="openPopUp(${ticket.PT_NO})" id="${ticket.PT_NO }">예약하기</button></td>
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
    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
    <script src="Air.js" type="text/javascript"></script>
</body>
</html>