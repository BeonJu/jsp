<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
	margin: 0;
	padding: 0;
}

header {
	position: fixed;
	top: 0;
	height: 60px;
	width: 100%;
	background-color: blue;
	color: white;
	text-align: center;
	line-height: 60px;
	font-size: 30px;
}

nav {
	position: fixed;
	top: 60px;
	height: 40px;
	width: 100%;
	background-color: lightblue;
	line-height: 40px;
}

nav span {
 margin: 0 30px;
}

/* nav ul {
	overflow: hidden;
	margin: 0;
	line-height: 40px;
	list-style: none;
}

nav ul li {
	float: left;
	width:150px;
	text-aling: center;
} */
</style>
</head>
<body>
	<header>쇼핑몰 회원관리 ver1.0</header>
	<nav>
	  <span>회원등록</span>
	  <span>회원목록조회/수정</span>
	  <span>회원매출조회</span>
	  <span>홈으로</span>
	</nav>
</body>
</html>