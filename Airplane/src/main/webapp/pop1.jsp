<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="Air.js" type="text/javascript">
</script>

</head>
<%-- ref site https://creamilk88.tistory.com/104 --%>
<body>
<%
	String PT_no = request.getParameter("ticket_no");

%>




	<form name="popupForm" method="post" action="pop2.jsp?ticket_no=<%=PT_no%>">
	<p><strong>비회원 예약</strong></p>
	<p>
	<label id="name" for="name">성함: </label>
	<input type="text" id="name" name="name">
	</p>
	<p>
	<label id="phone" for="phone">연락처: </label>
	<input type="text" id="phone" name="phone">
	<p/>
	
	<input type="hidden" name="ticket_no" id="ticket_no" value="<%=PT_no%>">
	
	<button type="submit">확인</button>
	
	</form>
	
	
	
</body>
</html>