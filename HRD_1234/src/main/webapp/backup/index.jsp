<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <link rel="stylesheet" type="text/css" href="./css/index.css"> -->
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
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<section>
		<h1>쇼핑몰 회원관리 프로그램</h1>
		<div>
			쇼핑몰 회원정보와 회원매출 정보 데이터베이스를 구축하고 회원관리 프로그램을 작성하는 프로그램이다. 프로그램 작성순서.<br />
			1. 회원정보 테이블을 생성한다.<br /> 
			2. 매출정보 테이블을 생성한다.<br /> 
			3. 회원정보, 매출정보 테이블에 제시된 문제지의 참조 데이터를 추가 생성한다.<br /> 
			4. 회원정보 입력 화면프로그램을 작성한다.<br />
			5. 회원정보 조회 프로그램을 작성한다.<br /> 
			6. 회원매출정보 조회 프로그램을 작성한다.<br />
		</div>
	</section>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>