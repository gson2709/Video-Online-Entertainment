<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<title>Change password</title>
</head>
<style>
</style>
<body>
	<div class="card" style="border-radius: 0px">
		<form action="/ASM/index/change" method="post">
			<div class="card-header">CHANGE PASSWORD</div>
			<div class="card-body">
				<div class="row mb-3">
					<div class="col">
						<label for="exampleFormControlInput1" class="form-label">USERNAME</label>
						<input type="text" class="form-control" value="${username}" name="username"
							id="exampleFormControlInput1" disabled>
					</div>
					<div class="col">
						<label for="exampleFormControlInput1" class="form-label">CURRENT
							PASSWORD</label> 
						<input name="password" type="password" class="form-control" value="${password}"
							id="exampleFormControlInput1" ${forgot?'disabled':''}>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<label for="exampleFormControlInput1" class="form-label">NEW
							PASSWORD</label> 
						<input name="newPassword" type="password" class="form-control" value="${newPassword}"
							id="exampleFormControlInput1">
					</div>
					<div class="col">
						<label for="exampleFormControlInput1" class="form-label">CONFIRM
							NEW PASSWORD</label> 
						<input name="confirmPassword" type="password" class="form-control" value="${confirmPassword}"
							id="exampleFormControlInput1">
					</div>
				</div>
			</div>
			<h4>${message}</h4>
			<div class="card-footer" style="text-align: right">
				<button class="btn btn-warning">CHANGE</button>
			</div>
		</form>
	</div>
</body>
</html>