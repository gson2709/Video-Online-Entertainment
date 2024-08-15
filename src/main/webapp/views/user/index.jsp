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
<title>Home</title>
</head>
<style>
</style>
<body>
	<div class="container">
		<jsp:include page="${roleNav}"></jsp:include>
		<jsp:include page="${choose}"></jsp:include>
	</div>
</body>
</html>