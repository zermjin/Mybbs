<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html >
<html>
<head>
</head>
<body>
	<select name="kiss" id="kiss" onchange="kisschange();">
		<option value="5" selected>5</option>
		<option value="20">20</option>
		<option value="50">50</option>
		<option value="100">100</option>
	</select>
	<input type="hidden" id="kissnum" name="kissnum" value="0">
	<script type="text/javascript">
		var numkiss;
		function kisschange() {
			var kiss = document.getElementById('kiss');
			numkiss = kiss.value;
			document.getElementById('kissnum').value=kiss.value;
		}
	</script>
	<%
		int kiss = Integer.parseInt(request.getParameter("numkiss"));
		session.setAttribute("kiss", kiss);
	%>
	<input type="text" value="${kiss }">
</body>
</html>
