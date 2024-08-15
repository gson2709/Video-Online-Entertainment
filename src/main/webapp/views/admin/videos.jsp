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
<title>Videos Management</title>
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

.tab-content .card>form .col-sm-8 .mb-3>input {
	border-radius: 0px;
	border: 1px solid #ef8d4a;
}

.card-footer>a,
.card-footer .direction>a{
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
				<a class="nav-link active" data-bs-toggle="tab" href="#edit">VIDEO EDITION</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" data-bs-toggle="tab" href="#list">VIDEO LIST</a>
			</li>
		</ul>
		<div class="tab-content" style="border: 1px solid #ed7d31;">
			<div role="tabpanel" class="tab-pane active card" id="edit" style="border-radius: 0px">
				<form class="row g-3" action="/ASM/manager/video" method="post" enctype="multipart/form-data"
						style="padding: 10px; padding-bottom: 0px;">
					<div class="col-sm-4" style="padding: 5px 10px; margin-top: 60px; margin-bottom: 30px; text-align: center">
						<img name="viewImage" src="/ASM/images/${video.poster}" style="width: 100%; height: 250px; margin-bottom: 10px">
						<input type="file" name="poster" class="form-control" value="${video.poster}">
					</div>
					<div class="col-sm-8" style="padding-right: 10px; padding-left: 25px;">
						<div class="col-12 mb-3">
							<label class="form-label">Youtube Id</label> 
							<input name="id" class="form-control" value="${video.id}">
						</div>
						<div class="col-12 mb-3">
							<label class="form-label">Video Title</label> 
							<input name="title" class="form-control" value="${video.title}">
						</div>
						<div class="col-12 mb-3">
							<label class="form-label">View Count</label> 
							<input name="views" type="number" class="form-control" value="${video.views}">
						</div>
						<div class="col-12 mb-3">
							<div class="form-check form-check-inline">
								<input class="form-check-input" name="active" type="radio" value="true" style="border: 1px solid #ef8d4a" ${video.active?'checked':''}>
								<label class="form-check-label" >Active</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" name="active" type="radio" value="false" style="border: 1px solid #ef8d4a" ${!video.active?'checked':''}> 
								<label class="form-check-label" for="inlineCheckbox2">Inactive</label>
							</div>
						</div>
					</div>
					<div class="col-sm-12" style="margin-top: 0px; margin-bottom: 15px">
						<label class="form-label">Description</label>
						<textarea name="description" rows="3" cols="10" class="form-control" style="border-radius: 0px; border: 1px solid #ef8d4a">${video.description}</textarea>
					</div>
					<h4>${message}</h4>
					<div class="card-footer" style="text-align: right; padding-bottom: 10px">
						<button type="submit" class="btn btn-primary" formaction="/ASM/manager/video/create">Create</button>
						<button type="submit" class="btn btn-primary" formaction="/ASM/manager/video/update">Update</button>
						<button type="submit" class="btn btn-primary" formaction="/ASM/manager/video/delete">Delete</button>
						<button type="submit" class="btn btn-primary" formaction="/ASM/manager/video/reset">Reset</button>
					</div>
				</form>
			</div>
			<div role="tabpanel" class="tab-pane card" style="border-radius: 0px" id="list">
				<table class="table table-bordered" style="margin-bottom: 0px">
					<thead>
						<tr>
							<td>Youtube Id</td>
							<td>Video Title</td>
							<td>View Count</td>
							<td>Status</td>
							<td></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="video" items="${videos}">
							<tr>
								<td>${video.id}</td>
								<td>${video.title}</td>
								<td>${video.views}</td>
								<td>${video.active?"Active":"Inactive"}</td>
								<td style="text-align: center"><a href="/ASM/manager/video/edit/${video.id}"><i class="fa-solid fa-pen"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="card-footer">
					<div class="row">
						<div class="col-sm-2"><h5>${videoNumber}</h5></div>
						<div class="col-sm-10 direction" style="text-align: right">
							<a href="/ASM/manager/video/first" class="btn btn-primary">|<</a>
							<a href="/ASM/manager/video/prev" class="btn btn-primary"><<</a>
							<a href="/ASM/manager/video/next" class="btn btn-primary">>></a>
							<a href="/ASM/manager/video/last" class="btn btn-primary">>|</a>
						</div>
					</div>
				</div>
				<input name="page" type="hidden" value="${page}">
			</div>
		</div>
	</div>
</body>
</html>