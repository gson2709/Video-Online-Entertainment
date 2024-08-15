<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="/ASM/bootstrap/css/bootstrap.min.css">
<script src="/ASM/bootstrap/js/bootstrap.min.js"></script>
<title>Edit profile</title>
</head>
<style>
</style>
<body>
	<form action="/ASM/index/edit-profile" method="post">
		<div class="card" style="border-radius: 0px">
			<div class="card-header">EDIT PROFILE</div>
			<div class="card-body">
				<div class="row mb-3">
					<div class="col">
						<label for="exampleFormControlInput1" class="form-label">USERNAME</label>
						<input class="form-control" name="username"
							value="${user.username}" id="exampleFormControlInput1" disabled>
					</div>
					<div class="col">
						<label for="exampleFormControlInput1" class="form-label">PASSWORD</label>
						<input type="password" name="password" value="${user.password}"
							class="form-control" id="exampleFormControlInput1">
					</div>
				</div>
				<div class="row">
					<div class="col">
						<label for="exampleFormControlInput1" class="form-label">FULLNAME</label>
						<input class="form-control" name="fullname"
							value="${user.fullname}" id="exampleFormControlInput1">
					</div>
					<div class="col">
						<label for="exampleFormControlInput1" class="form-label">EMAIL
							ADDRESS</label> <input type="email" class="form-control" name="email"
							value="${user.email}" id="exampleFormControlInput1">
					</div>
				</div>
			</div>
			<div class="card-footer" style="text-align: right">
				<button class="btn btn-warning">UPDATE</button>
			</div>
		</div>
	</form>
	<h4>${message}</h4>
</body>
</html>