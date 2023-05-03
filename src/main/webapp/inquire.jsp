<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="DTO.Dto" %>
<%
request.setCharacterEncoding("utf-8");
ArrayList<Dto> ilist = new ArrayList<Dto>();
ilist = (ArrayList<Dto>) request.getAttribute("ilist");
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
		<div class="title">투표검수조회</div>
		<div class="wrapper">
			<table>
				<tr>
					<th>성명</th>
					<th>생년월일</th>
					<th>나이</th>
					<th>성별</th>
					<th>후보번호</th>
					<th>투표시간</th>
					<th>유권자확인</th>
				</tr>
				<% for (Dto d : ilist) { %>
				<tr>
					<td><%=d.getV_name() %></td>
					<td><%=d.getM_birth() %></td>
					<td><%=d.getM_age() %></td>
					<td><%=d.getM_gender() %></td>
					<td><%=d.getM_no() %></td>
					<td><%=d.getV_time() %></td>
					<td><%=d.getV_confirm() %></td>
				</tr>
				<%} %>
			</table>
		</div>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>