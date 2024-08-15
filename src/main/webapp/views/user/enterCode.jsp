<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="/ASM/bootstrap/css/bootstrap.min.css">
<script src="/ASM/bootstrap/js/bootstrap.min.js"></script>
<title>Enter Code</title>
</head>
<body>
	<div class="card" style="border-radius: 0px">
		<form method="post">
			<div class="card-header">Enter code</div>
			<div class="card-body">
				<div class="col">
					<div class="col">
						<label for="exampleFormControlInput1" class="form-label">Enter
							the code already sent to email</label> <input class="form-control"
							name="code" id="exampleFormControlInput1"> <label
							style="color: red">${checkCode}</label>
					</div>
				</div>
			</div>
			<div class="card-footer" style="text-align: right">
				<button formaction="/ASM/index/forgot/confirm" class="btn btn-warning">CONFIRM</button>
			</div>
		</form>
	</div>
</body>
</html>