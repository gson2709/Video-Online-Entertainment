<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="/ASM/bootstrap/css/bootstrap.min.css">
<script src="/ASM/bootstrap/js/bootstrap.min.js"></script>
<title>Share video</title>
</head>
<style>
	.card .card-footer{
		text-align: right;
	}
</style>
<body>
	<form method="post">
		<div class="card">
			<div class="card-header">SEND VIDEO TO YOUR FRINED</div>
			<div class="card-body">
				<h5 class="card-title">YOUR FRIEND'S EMAIL</h5>
				<input class="form-control" name="email">
			</div>
			<div class="card-footer">
				<button class="btn btn-primary">SEND</button>
			</div>
		</div>
	</form>
	<h4>${message}</h4>
</body>
</html>