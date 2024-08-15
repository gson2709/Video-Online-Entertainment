<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<style>
	.center-card {
		width: 59rem;
		margin: 0 auto;
	}
	
	.card {
		border-radius: 0px;
		margin: 15px 15px;
		padding: 0px 0px;
	}
	
	.card .card-header {
		border: 1px solid #ed7d31;
		border-radius: 0px;
		text-align: center;
	}
	
	.card .card-header>a>img {
		width: 300px;
		height: 200px;
	}
	
	.card .card-body {
		border: 1px solid #ed7d31;
		padding: 7px 0px;
	}
	
	.card .card-body .card-title {
		text-align: center;
		font-weight: bold;
		text-shadow: 1px 1px 0px gray;
	}
	
	.card .card-footer {
		border: 1px solid #ed7d31;
		text-align: center;
		border-radius: 0px;
	}
	
	.card .card-footer .btn {
		width: 80px;
		padding: 1px 3px;
	}
	
	.card .card-footer a .disabled{
		pointer-events: none;
	}
	
	.center {
		width: 260px;
		margin: 0 auto;
	}
	
	.direct {
		width: 60px;
		padding: 1px 3px;
		border-radius: 7px;
		border: none;
		background-color: #adadad;
		color: white;
	}
</style>
<body>
	<div class="center-card row">
			<c:forEach var="video" items="${videos}">
				<div class="card col-sm-4" style="width: 18rem;">
					<div class="card-header">
						<a href="/ASM/index/detail/${video.id}"> 
							<img src="/ASM/images/${video.poster}">
						</a>
					</div>
					<div class="card-body">
						<h5 class="card-title">${video.title}</h5>
					</div>
					<div class="card-footer">
						<a type="submit" href="/ASM/index/favorite/${video.id}" class="btn btn-success ${video.favorites!=null?'disabled':''}">Like</a> 
						<a type="submit" href="/ASM/index/share/${video.id}" class="btn btn-warning">Share</a>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="center">
			<button class="direct">|<</button>
			<button class="direct"><<</button>
			<button class="direct">>></button>
			<button class="direct">>|</button>
		</div>
</body>
</html>