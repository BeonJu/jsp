<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="DTO.Member"%>
<%
request.setCharacterEncoding("UTF-8");
ArrayList<Member> list = new ArrayList<Member>();
list = (ArrayList<Member>) request.getAttribute("list");

int custno = list.get(0).getCustno();
String custname = list.get(0).getCustname();
String phone = list.get(0).getPhone();
String address = list.get(0).getAddress();
String joindate = list.get(0).getJoindate();
String grade = list.get(0).getGrade();
String city = list.get(0).getCity();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<script src="script.js"></script>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="topmenu.jsp"%>
	<section>
		<div class="title">홈쇼핑 회원 정보 수정</div>
		<form name="frm" action="update">
			<input type="hidden" id="GUBUN" value="update">
			<div class="wrapper">
				<table>
					<tr>
						<th>회원번호(자동발생)</th>
						<td><input name="custno" value="<%=custno%>" readonly
							value="1"></td>
					</tr>
					<tr>
						<th>회원성명</th>
						<td><input type="text" name="custname" value="<%=custname%>"></td>
					</tr>
					<tr>
						<th>회원전화</th>
						<td><input type="text" name="phone" value="<%=phone%>"></td>
					</tr>
					<tr>
						<th>회원주소</th>
						<td><input type="text" name="address" value="<%=address%>"></td>
					</tr>
					<tr>
						<th>가입일자</th>
						<td><input type="text" name="joindate" value="<%=joindate%>"></td>
					</tr>
					<tr>
						<th>고객등급[A:VIP,B:일반,C:직원]</th>
						<td><input type="text" name="grade" value="<%=grade%>"></td>
					</tr>
					<tr>
						<th>도시코드</th>
						<td><input type="text" name="city" value="<%=city%>"></td>
					</tr>
					<tr>
						<td colspan="2">
							<button class="btn" type="submit"
								onclick="fn_submit(); return false;">수 정</button>
							<button class="btn" type="button" onclick="location='list'">조
								회</button>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>