<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="Air.js" type="text/javascript"></script>
</head>
<%-- ref site https://creamilk88.tistory.com/104 --%>
<body>
<!-- onunload="closePopup()" -->
	<%
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");
	String PT_no = request.getParameter("ticket_no");
	%>

	<form name="popupForm2" action="custInsert" method="get">
		<p>
			<strong>비회원 예약 확인</strong>
		</p>
		<p>
			<label>성함:</label> <input type="text" readonly id="name"
				name="name" value=<%=name%>>
		</p>
		<p>
			<label>연락처: </label> <input type="text" readonly id="phone"
				name="phone" value=<%=phone%>>
		<p />
		<label>티켓 번호:</label> <input type="text" readonly id="ticket_no"
			name="ticket_no" value=<%=PT_no%>> <br>
		<br>
		<button type="button" name="cust" onclick="closePopup()">예약 하기</button>

	</form>
	<%-- 
	<input type="button" value="확인" onclick="closePopup()">
	--%>
</body>
</html>


<%-- pop1에서 입력 받은 걸 insert 하지 말고 세션으로 이어 받아서 pop2에서 띄어주고 pop2에서 insert --%>