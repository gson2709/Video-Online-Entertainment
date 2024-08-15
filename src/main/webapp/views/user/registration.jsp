<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="/ASM/bootstrap/css/bootstrap.min.css">
<script src="/ASM/bootstrap/js/bootstrap.min.js"></script>
<title>Registration</title>
</head>
<body>
	<div class="card" style="border-radius: 0px">
		<form action="/ASM/index/signup" method="post">
			<div class="card-header">REGISTRATION</div>
			<div class="card-body">
				<div class="row mb-3">
					<div class="col">
						<label for="exampleFormControlInput1" class="form-label">USERNAME</label>
						<input name="username" class="form-control"
							id="exampleFormControlInput1" value="${username}"> <label
							style="color: red" class="form-label">${checkUsername}</label>
					</div>
					<div class="col">
						<label for="exampleFormControlInput1" class="form-label">PASSWORD</label>
						<input name="password" type="password" class="form-control"
							id="exampleFormControlInput1" value="${password}"> <label
							style="color: red" class="form-label">${checkPassword}</label>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<label for="exampleFormControlInput1" class="form-label">FULLNAME</label>
						<input name="fullname" class="form-control"
							id="exampleFormControlInput1" value="${name}"> <label
							style="color: red" class="form-label">${checkFullname}</label>
					</div>
					<div class="col">
						<label for="exampleFormControlInput1" class="form-label">EMAIL
							ADDRESS</label> <input name="email" type="email" class="form-control"
							id="exampleFormControlInput1" value="${email}"> <label
							style="color: red" class="form-label">${checkEmail}</label>
					</div>
				</div>
			</div>
			<div class="card-footer" style="text-align: right">
				<button class="btn btn-warning">SIGN UP</button>
			</div>
		</form>
	</div>
	<h4>${message}</h4>
</body>
</html>