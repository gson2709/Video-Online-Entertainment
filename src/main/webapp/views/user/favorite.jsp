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
<title>My favorite videos</title>
</head>
<style>
	.center-card{
		width: 59rem;
		margin: 0 auto;
	}
	.card{
		border-radius: 0px;
		margin: 15px 15px;
		padding: 0px 0px;
	}
	.card .card-header{
		border: 1px solid #ed7d31;
		border-radius: 0px;
		text-align: center;
	}
	.card .card-header>a>img{
		width: 300px;
		height: 200px;
	}
	.card .card-body{
		border: 1px solid #ed7d31;
		padding: 7px 0px;
	}
	.card .card-body .card-title{
		text-align: center;
		font-weight: bold;
		text-shadow: 1px 1px 0px gray;
	}
	.card .card-footer{
		border: 1px solid #ed7d31;
		text-align: center;
		border-radius: 0px;
	}
	.card .card-footer .btn{
		width: 80px;
		padding: 1px 3px;
	}
</style>
<body>
	<div class="center-card row">
		<c:forEach var="vfav" items="${videoFav}">
			<div class="card col-sm-4" style="width: 18rem;">
				<div class="card-header">
					<a href="#"><img src="/ASM/images/${vfav.video.poster}"></a>
				</div>
				<div class="card-body">
					<h5 class="card-title">${vfav.video.title}</h5>
				</div>
				<div class="card-footer">
					<a href="/ASM/index/unlike/${vfav.id}" class="btn btn-success">Unlike</a>
					<a href="/ASM/index/share/${vfav.id}" class="btn btn-warning">Share</a>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>