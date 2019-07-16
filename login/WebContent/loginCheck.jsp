<%@page import="dao.loginDto"%>
<%@page import="dao.loginDao"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="jdbc.loginJDBC"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<%
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
	%>

	<%
		boolean login = loginDao.getInstance().loginUser(id, pwd);
		if (login == true) {

			loginDto dto = loginDao.getInstance().getOneUser(id);
			session.setAttribute("userId", dto);
	%>
	<script>
		alert("로그인 성공.");
		location.href = "isMain.jsp";
	</script>
	<%
		} else {
	%>
	<script>
		alert("로그인 실패.");
		location.href = "Register.html";
	</script>
	<%
		}
	%>
</body>
</html>