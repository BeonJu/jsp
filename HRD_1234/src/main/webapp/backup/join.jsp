<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
section {
	position: fixed;
	top: 100px;
	height: 100%;
	width: 100%;
}

section h1 {
	text-align: center;
}

form {
 display: flex;
 align-items: center;
 justify-content: center;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<section>
		<h1>홈쇼핑 회원 등록</h1>
		<form class="join_form">
			<table border="1">
				<tr>
					<td>회원번호(자동발생)</td>
					<td><input type="text" name="num"></td>
				</tr>
				<tr>
					<td>회원성명</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>회원전화</td>
					<td><input type="text" name="tel"></td>
				</tr>
				<tr>
					<td>회원주소</td>
					<td><input type="text" name="address"></td>
				</tr>
				<tr>
					<td>가입일자</td>
					<td><input type="text" name="joindate"></td>
				</tr>
				<tr>
					<td>고객등급[A:VIP, B:일반, C:직원]</td>
					<td><input type="text" name="grade"></td>
				</tr>
				<tr>
					<td>도시코드</td>
					<td><input type="text" name="city"></td>
				</tr>
				<tr>
					<td colspan="2">
					<input type="submit" value="등록" onclick="joinformChk()"> 
					<input type="button" value="조회">
					</td>
				</tr>
			</table>
		</form>
	</section>
	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript" src="./js/check.js"></script>
</body>
</html>