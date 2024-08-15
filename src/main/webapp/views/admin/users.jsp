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
<link rel="stylesheet" href="/Lab5/fontawesome-free-6.5.1-web/css/all.css">
<title>Users Management</title>
</head>
<style>
.nav-tabs .nav-item .nav-link {
	border-radius: 0px;
	color: black;
	width: 200px;
	text-align: center;
	font-weight: bold;
	background-color: #f2f2f2;
	border: 1px solid #ed7d31;
}

.nav-tabs .nav-item .active {
	color: #c10000;
	background-color: white;
}

.tab-pane>form .col-md-6>input {
	border-radius: 0px;
	border: 1px solid #f3ad7e;
}

.card-footer>button,
.card-footer .direction>button{
	background-color: #c1c1c1;
	border: none;
	margin-left: 15px;
	color: #c0040c;
	font-weight: bold
}
</style>
<body>
	<div class="container">
		<jsp:include page="nav.jsp"></jsp:include>
		<ul class="nav nav-tabs">
			<li class="nav-item">
				<a class="nav-link active" data-bs-toggle="tab" href="#edit">USER EDITION</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" data-bs-toggle="tab" href="#list">USER LIST</a>
			</li>
		</ul>
		<div class="tab-content" style="border: 1px solid #ed7d31;">
			<div role="tabpanel" class="tab-pane active card" id="edit" style="border-radius: 0px">
				<form class="row g-3" style="padding: 10px; padding-bottom: 0px"
					action="/ASM/manager/user" method="post">
					<div class="col-md-6">
						<label for="inputEmail4" class="form-label">USERNAME</label> <input name="username"
							class="form-control" id="inputEmail4" value="${user.username}"
							disabled>
					</div>
					<div class="col-md-6">
						<label for="inputPassword4" class="form-label">PASSWORD</label> <input name="password"
							type="password" class="form-control" id="inputPassword4"
							value="${user.password}" disabled>
					</div>
					<div class="col-md-6">
						<label for="inputPassword4" class="form-label">FULLNAME</label> <input name="fullname"
							class="form-control" id="inputPassword4" value="${user.fullname}">
					</div>
					<div class="col-md-6">
						<label for="inputPassword4" class="form-label">EMAIL
							ADDRESS</label> <input type="email" class="form-control" name="email"
							id="inputPassword4" value="${user.email}">
					</div>
					<h4>${message}</h4>
					<div class="card-footer" style="text-align: right">
						<button formaction="/ASM/manager/user/update"
							class="btn btn-primary">Update</button>
						<button formaction="/ASM/manager/user/delete"
							class="btn btn-primary">Delete</button>
						<button formaction="/ASM/manager/user/reset"
							class="btn btn-primary">Reset</button>
					</div>
				</form>
			</div>
			<div role="tabpanel" class="tab-pane card" id="list" style="border-radius: 0px">
				<table class="table table-bordered" style="margin-bottom: 0px">
					<thead>
						<tr>
							<td style="border-bottom: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf">Username</td>
							<td style="border-bottom: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf">Password</td>
							<td style="border-bottom: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf">Fullname</td>
							<td style="border-bottom: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf">Email</td>
							<td style="border-bottom: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf">Role</td>
							<td style="border-bottom: 2px solid #cfcfcf; border-left: 2px solid #cfcfcf"></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${users}">
							<tr>
								<td style="border-top: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf">${user.username}</td>
								<td style="border-top: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf">${user.password}</td>
								<td style="border-top: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf">${user.fullname}</td>
								<td style="border-top: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf">${user.email}</td>
								<td style="border-top: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf">${user.admin?"Admin":"User"}</td>
								<td style="border-top: 2px solid #cfcfcf; border-left: 2px solid #cfcfcf">
									<a href="/ASM/manager/user/edit/${user.username}"><i class="fa-solid fa-pen"></i></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="card-footer">
					<div class="row">
						<div class="col-sm-2 justify-content-center">
							<h5>${userNumber}</h5>
						</div>
						<div class="col-sm-10 direction" style="text-align: right">
							<button formaction="" class="btn btn-primary">|<</button>
							<button formaction="" class="btn btn-primary"><<</button>
							<button formaction="" class="btn btn-primary">>></button>
							<button formaction="" class="btn btn-primary">>|</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>