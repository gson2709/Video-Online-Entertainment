<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="/ASM/bootstrap/css/bootstrap.min.css">
<script src="/ASM/bootstrap/js/bootstrap.min.js"></script>
<title>Forgot password</title>
</head>
<body>
	<div class="card" style="border-radius: 0px">
		<form action="/ASM/index/forgot" method="post">
			<div class="card-header">FORGOT PASSWORD</div>
			<div class="card-body">
				<div class="row">
					<div class="col">
						<label for="exampleFormControlInput1" class="form-label">USERNAME</label>
						<input class="form-control" name="username" value="${username}"
							id="exampleFormControlInput1"> <label style="color: red">${checkUserExist}</label>
					</div>
					<div class="col">
						<label for="exampleFormControlInput1" class="form-label">EMAIL</label>
						<input type="email" class="form-control" name="email"
							value="${email}" id="exampleFormControlInput1"> <label
							style="color: red">${checkEmailExist}</label>
					</div>
				</div>
			</div>
			<div class="card-footer" style="text-align: right">
				<button formaction="/ASM/index/forgot/enterCode" class="btn btn-warning">RETRIEVE</button>
			</div>
		</form>
	</div>
</body>
</html>