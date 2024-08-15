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
<title>Detail video</title>
</head>
<style>
	.card{
		border-radius: 0px;
		margin: 15px 15px;
		padding: 0px 0px;
	}
	.card .card-header{
		border: 1px solid #ed7d31;
		border-radius: 0px;
		text-align: center;
		padding: 50px;
		background-color: white;
	}
	.card .card-header>img{
		width: 650px;
		height: 300px;
	}
	.card .card-body{
		border: 1px solid #ed7d31;
		padding-left: 10px;
		height: 20px;
	}
	.card .card-body .card-title{
		font-weight: bold;
		text-shadow: 1px 1px 0px gray;
	}
	.card .descrip{
		height: 15%;
	}
	.card .card-body .card-text{
		font-weight: bold;
	}
	.card .card-footer{
		border: 1px solid #ed7d31;
		text-align: right;
		border-radius: 0px;
	}
	.card .card-footer .btn{
		width: 80px;
		padding: 1px 3px;
		box-shadow: 2px 2px 2px black;
	}
	.col-sm-4{
		height: 738px;
	}
	.col-sm-4 .card-header{
		border: 1px solid #8fbe6f;
		text-align: center;
	}
	.col-sm-4 .card-body{
		border: 1px solid #8fbe6f;
		padding: 50px;
	}
	.col-sm-4 .card-body .card-title>a{
		text-decoration: none;
		color: black;
	}
	.col-sm-4 .card-body .card-title>a:hover{
		text-decoration: underline;
		
	}
	.col-sm-4 .row{
		margin-bottom: 20px;
		line-height: 100px;
	}
	.other{
		width: 100px;
		height: 100px;
	}
</style>
<body>
	<div class="row" style="">
		<div class="card col-sm-7">
			<div class="card-header">
				<img src="/ASM/images/${video.poster}">
			</div>
			<div class="card-body" style="background-color: #e2f0d9">
				<h5 class="card-title">${video.title}</h5>
			</div>
			<div class="card-body descrip">
				<h5 class="card-text">${video.description}</h5>
			</div>
			<div class="card-footer">
				<a href="/ASM/index/favorite/${video.id}" class="btn btn-primary">Like</a>
				<a href="/ASM/index/share/${video.id}" class="btn btn-warning">Share</a>
			</div>
		</div>
		<div class="col-sm-4" style="margin: 15px; margin-left: 60px">
			<c:forEach var="video" items="${other}">
				<div class="row">
					<div class="card-header col-sm-5 text-center">
						<a href="/ASM/index/detail/${video.id}"> <img class="other"
							src="/ASM/images/${video.poster}">
						</a>
					</div>
					<div class="card-body col-sm-7">
						<h5 class="card-title">
							<a href="/ASM/index/detail/${video.id}">${video.title}</a>
						</h5>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>