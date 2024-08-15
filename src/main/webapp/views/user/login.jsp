<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="/ASM/bootstrap/css/bootstrap.min.css">
<script src="/ASM/bootstrap/js/bootstrap.min.js"></script>
<title>Login</title>
</head>
<style>
	h3{
		color: black;
	}
	a{
		text-decoration: none; color: blue
	}
	a:hover {
		text-decoration: underline;
		font-weight: bold;
	}
</style>
<body>
	<div class="card" style="border-radius: 0px">
		<form action="/ASM/index/login" method="post">
			<div class="card-header">LOGIN</div>
			<div class="card-body">
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">USERNAME</label>
					<input class="form-control" name="username" value="${username}"
						id="exampleFormControlInput1">
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">PASSWORD</label>
					<input type="password" class="form-control" name="password"
						value="${password}" id="exampleFormControlInput1">
				</div>
				<div class="form-check">
					<input class="form-check-input" name="isRemember" type="checkbox"
						value="" id="flexCheckDefault"> <label
						class="form-check-label" for="flexCheckDefault">REMEMBER ME?</label>
					<br>
					<a href="/ASM/index/forgot">Forgot password?</a>
					<br>
					<a href="/ASM/index/signup">No account, sign up now</abbr>
				</div>
			</div>
			<div class="card-footer" style="text-align: right">
				<button type="submit" class="btn btn-primary">LOGIN</button>
			</div>
		</form>
	</div>
	<h3>${message}</h3>
</body>
</html>