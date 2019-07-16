<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// 인증된 세션이 없는경우, 해당페이지를 볼 수 없게 함. 
	if (session.getAttribute("userId") == null) {
		response.sendRedirect("logout.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1><%=session.getAttribute("userId")%>님 <small>반갑습니다.</small>
	</h1>
	<a href="logout.jsp">로그아웃</a>
</body>
</html>