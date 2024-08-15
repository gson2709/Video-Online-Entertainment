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
<title>Reports</title>
</head>
<style>
	.nav-tabs .nav-item .nav-link {
		border-radius: 0px;
		color: black;
		width: 210px;
		text-align: center;
		font-weight: bold;
		background-color: #f2f2f2;
  		border: 1px solid #ed7d31;
	}
	
	.nav-tabs .nav-item .active {
		color: #c10000;
		background-color: white;
	}
	
	.tab-pane>form .col-md-6>input{
		border-radius: 0px;
		border: 1px solid #f3ad7e;
	}
</style>
<body>
	<div class="container">
		<jsp:include page="nav.jsp"></jsp:include>
		<ul class="nav nav-tabs">
			<li class="nav-item"><a class="nav-link active"
				data-bs-toggle="tab" href="#like">FAVORITE</a></li>
			<li class="nav-item"><a class="nav-link" data-bs-toggle="tab"
				href="#likeUser">FAVORITE USERS</a></li>
			<li class="nav-item"><a class="nav-link" data-bs-toggle="tab"
				href="#share">SHARE FRIENDS</a></li>
		</ul>
		<div class="tab-content" style="border: 1px solid #ed7d31;">
			<div role="tabpanel" class="tab-pane active card" id="like"
				style="border-radius: 0px">
				<table class="table table-bordered" style="margin: 0px 0px">
					<thead>
						<tr>
							<td
								style="border-bottom: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf; text-align: center">Video
								Title</td>
							<td
								style="border-bottom: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf; text-align: center">Favorite
								Count</td>
							<td
								style="border-bottom: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf; text-align: center">Latest
								Date</td>
							<td
								style="border-bottom: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf; text-align: center">Oldest
								Date</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="fav" items="${favoriteCount}">
							<tr>
								<td
									style="border-top: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf;">${fav.title}</td>
								<td
									style="border-top: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf; text-align: center">${fav.likes}</td>
								<td
									style="border-top: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf; text-align: center">${fav.newest}</td>
								<td
									style="border-top: 2px solid #cfcfcf; border-right: 2px solid #cfcfcf; text-align: center">${fav.oldest}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<form action="/ASM/manager/report/export" class="card-footer row" method="post" enctype="multipart/form-data" style="margin: 0px 0px">
					<button class="btn btn-success" style="width: 200px; color: white; font-weight: bold;">Export Excel</button>
				</form>
			</div>
			<div role="tabpanel" class="tab-pane" id="likeUser">
				<form action="" method="post">
					<div class="mb-3 row" style="margin: 15px 20px">
						<label for="inputPassword" class="col-sm-2 col-form-label">VIDEO
							TITLE</label>
						<div class="col-sm-10">
							<select id="cboVideo" class="form-select" onchange="location = this.value"
									aria-label="Default select example" name="cboVideo1">
								<c:forEach var="video" items="${videos}">
									<option value="/ASM/manager/report1/${video.id}"
										${selected1==video.id?"selected":""}>${video}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<table class="table table-bordered"
						style="margin-top: 20px; margin-bottom: 0px">
						<thead>
							<tr>
								<td style="border: 2px solid #cfcfcf">Username</td>
								<td style="border: 2px solid #cfcfcf">Fullname</td>
								<td style="border: 2px solid #cfcfcf">Email</td>
								<td style="border: 2px solid #cfcfcf">Favorite Date</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="user" items="${usersLike}">
								<tr>
									<td style="border: 2px solid #cfcfcf">${user.id}</td>
									<td style="border: 2px solid #cfcfcf">${user.fullname}</td>
									<td style="border: 2px solid #cfcfcf">${user.email}</td>
									<td style="border: 2px solid #cfcfcf">${user.likeDate}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
			</div>
			<div role="tabpanel" class="tab-pane" id="share">
				<div class="mb-3 row" style="margin: 15px 20px">
					<label for="inputPassword" class="col-sm-2 col-form-label">VIDEO
						TITLE</label>
					<div class="col-sm-10">
						<select id="cboVideo" class="form-select" onchange="location = this.value"
									aria-label="Default select example" name="cboVideo2">
								<c:forEach var="video" items="${videos}">
									<option value="/ASM/manager/report2/${video.id}"
										${selected2==video.id?"selected":""}>${video}</option>
								</c:forEach>
						</select>
					</div>
				</div>
				<table class="table table-bordered"
					style="margin-top: 20px; margin-bottom: 0px">
					<thead>
						<tr>
							<td style="border: 2px solid #cfcfcf">Sender Name</td>
							<td style="border: 2px solid #cfcfcf">Sender Email</td>
							<td style="border: 2px solid #cfcfcf">Receiver Email</td>
							<td style="border: 2px solid #cfcfcf">Sent Date</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sender" items="${senders}">
							<tr>
								<td style="border: 2px solid #cfcfcf">${sender.senderName}</td>
								<td style="border: 2px solid #cfcfcf">${sender.senderEmail}</td>
								<td style="border: 2px solid #cfcfcf">${sender.receiverEmail}</td>
								<td style="border: 2px solid #cfcfcf">${sender.sentDate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
<script>
	document.getElementById("cboVideo").onchange=function(){
		location = this.value;
	}
</script>
</html>