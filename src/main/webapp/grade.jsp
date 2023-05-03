<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="DTO.Dto" %>
<%
request.setCharacterEncoding("utf-8");
ArrayList<Dto> glist = new ArrayList<Dto>();
glist = (ArrayList<Dto>) request.getAttribute("glist");
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
					<th>총투표건수</th>
				</tr>
				<% for (Dto d : glist){ %>
				<tr>
					<td><%=d.getM_no() %></td>
					<td><%=d.getM_name() %></td>
					<td><%=d.getV_total() %></td>
				</tr>
				<%} %>
			</table>
		</div>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>