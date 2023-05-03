<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="DTO.Dto" %>
<%
request.setCharacterEncoding("utf-8");
ArrayList<Dto> list = new ArrayList<Dto>();
list = (ArrayList<Dto>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	<section>
		<div class="title">후보조회</div>
		<div class="wrapper">
			<table>
				<tr>
					<th>후보번호</th>
					<th>성명</th>
					<th>소속정당</th>
					<th>학력</th>
					<th>주민번호</th>
					<th>지역구</th>
					<th>대표전화</th>
				</tr>
				<% for (Dto d : list){ %>
				<tr>
					<td><%=d.getM_no() %></td>
					<td><%=d.getM_name() %></td>
					<td><%=d.getP_name() %></td>
					<td><%=d.getP_school() %></td>
					<td><%=d.getM_jumin() %></td>
					<td><%=d.getM_city()%></td>
					<td><%=d.getP_tel() %></td>
				</tr>
				<%} %>
			</table>
		</div>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>