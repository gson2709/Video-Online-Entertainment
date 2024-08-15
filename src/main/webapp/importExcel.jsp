<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="/ASM/bootstrap/css/bootstrap.min.css">
<script src="/ASM/bootstrap/js/bootstrap.min.js"></script>
<title>Insert Excel File</title>
</head>
<body class="container">
	<form action="/ASM/importExcel" method="post" enctype="multipart/form-data">
		<div class="mb-3">
			<label class="form-label">Insert excel file here</label>
			<input name="excel" class="form-control" type="file">
		</div>
		<button formaction="/ASM/importExcel/import" class="btn btn-primary">Import</button>
	</form>
	<table class="table table-bordered" style="margin-bottom: 0px; margin-top: 20px">
		<thead>
			<tr>
				<td
					style="border-bottom: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf">Username</td>
				<td
					style="border-bottom: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf">Password</td>
				<td
					style="border-bottom: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf">Fullname</td>
				<td
					style="border-bottom: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf">Email</td>
				<td
					style="border-bottom: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf">Role</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${users}">
				<tr>
					<td
						style="border-top: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf">${user.username}</td>
					<td
						style="border-top: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf">${user.password}</td>
					<td
						style="border-top: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf">${user.fullname}</td>
					<td
						style="border-top: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf">${user.email}</td>
					<td
						style="border-top: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf">${user.admin?"Admin":"User"}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<h3>${message}</h3>
</body>
</html>