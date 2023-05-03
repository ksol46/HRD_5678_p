<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
<script type="text/javascript" src="script.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<section>
		<div class="title">투표하기</div>
		<div class="wrapper">
			<form action="add" name="frm">
				<table>
					<tr>
						<th>주민번호</th>
						<td><input type="text" name="v_jumin">예 : 8906153154726</td>
					</tr>
					<tr>
						<th>성명</th>
						<td><input type="text" name="v_name"></td>
					</tr>
					<tr>
						<th>투표번호</th>
						<td>
						<select name="m_no">
							<option></option>
							<option value="1">[1] 김후보</option>
							<option value="2">[2] 이후보</option>
							<option value="3">[3] 박후보</option>
							<option value="4">[4] 조후보</option>
							<option value="5">[5] 최후보</option>
						</select>
						</td>
					</tr>
					<tr>
						<th>투표시간</th>
						<td><input type="text" name="v_time"></td>
					</tr>
					<tr>
						<th>투표장소</th>
						<td><input type="text" name="v_area"></td>
					</tr>
					<tr>
						<th>유권자확인</th>
						<td>
						<input type="radio" style="float: none;" value="Y" name="v_confirm">확인
						<input type="radio" style="float: none;" value="N" name="v_confirm">미확인
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit" onclick="fn_submit(); return false;">투표하기</button>
							<button type="reset" onclick="fn_reset();">다시하기</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>