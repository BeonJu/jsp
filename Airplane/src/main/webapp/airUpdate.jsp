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
				<form name="searchFrom" action="searchPhone">
					<div class="way">
						<span>비회원 연락처 변경</span>
					</div>
					<div class="selectWrapper">
						<label for="r_name">비회원 성함</label> <input type="text"
							name="r_name" class="r-name" placeholder="성함 입력">
							<label for="r_name">연락처</label> <input type="text"
							name="r_phone" class="r-name" placeholder="연락처 입력">
						<button type="submit" name="searchSubmit">검색</button>
					</div>
				</form>
			</div>
			<div class="searchList">
				<table>
					<thead>
						<tr>
							<th>성함</th>
							<th>연락처</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="searchPhone" items="${cList}"
							varStatus="status">
							<form name= "updatePhone" action="update" method="get">
							<tr>
								<input type="hidden" name="cust_name" value="${searchPhone.CUST_NAME  }">
								<input type="hidden" name="cust_phone" value="${searchPhone.CUST_PHONE }">

								<td>${searchPhone.CUST_NAME }</td>
								<td>${searchPhone.CUST_PHONE }</td>
								<td><input type="text" name="changePhone" placeholder="변경할 연락처를 입력하세요"></td>
								<td><button type="submit">연락처 변경 하기</button></td>
								
							</tr>
							</form>
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