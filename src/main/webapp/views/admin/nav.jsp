<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
	/* menu */
	.navbar .container-fluid .navbar-brand{
		color: yellow;
		font-weight: bold;
		text-shadow: 2px 2px 2px black;
	}
	.navbar .container-fluid>ul>li>a{
		color: #92d050;
		font-weight: bold;
	}
</style>
<body>
	<h1 style="text-align: right">${fullname}</h1>
	<nav class="navbar navbar-expand-lg">
		<div class="container-fluid"
			style="background-color: black; border-radius: 10px; padding: 5px 20px">
			<a class="navbar-brand" href="/ASM/manager">ADMINISTRATION TOOL</a>
			<ul class="nav mb-2 mb-lg-0 justify-content-end ">
				<li class="nav-item"><a class="nav-link" href="/ASM/index/home">HOME</a>
				</li>
				<li class="nav-item"><a class="nav-link"
					href="/ASM/manager/video">VIDEOS</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/ASM/manager/user">USERS</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/ASM/manager/report">REPORTS</a></li>
			</ul>
		</div>
	</nav>
</body>
</html>