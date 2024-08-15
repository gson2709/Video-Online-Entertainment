<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<style>
	/* menu */
	.navbar .container-fluid .navbar-brand{
		color: #c00000;
		font-weight: bold;
		text-shadow: 2px 2px 2px black;
	}
	.navbar .container-fluid .navbar-collapse>ul>li>a{
		color: #3339f0;
		font-weight: bold;
	}
	.navbar .container-fluid .navbar-collapse>ul .account>ul{
		width: 20%;
		margin: -8px 10px;
		position: absolute; 
		z-index: 2;
		display: none; 
		list-style: none;
	}
	.navbar .container-fluid .navbar-collapse>ul .account>ul>li{
		border: 1px solid #ee803d;
		background-color: #f2f2f2;
		padding: 2px 5px;
	}
	.navbar .container-fluid .navbar-collapse>ul .account>ul>li>a{
		text-decoration: none;
		color: black;
	}
	.navbar .container-fluid .navbar-collapse>ul .account>ul>li>a:hover{
		font-weight: bold;
	}
	.navbar .container-fluid .navbar-collapse>ul>.account:hover ul{
		padding-left: 0px;
		display: block;
	}
</style>
<body>
	<h1 style="text-align: right">${fullname}</h1>
	<nav class="navbar navbar-expand-lg">
		<div class="container-fluid"
			style="background-color: #ffc72a; border-radius: 10px; padding: 5px 20px">
			<a class="navbar-brand" href="/ASM/index/home">ONLINE ENTERTAINMENT</a>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="/ASM/index/myfavorite">MY FAVORITES</a></li>
					<li class="nav-item account"><a class="nav-link" href="#">MY ACCOUNT</a>
						<ul>
							<li ${isLogin?"hidden":""}><a href="/ASM/index/login">Login</a></li>
							<li ${isLogin?"":"hidden"}><a href="/ASM/index/signup">Registration</a></li>
							<li><a href="/ASM/index/logoff">Logoff</a></li>
							<li ${isLogin?"":"hidden"}><a href="/ASM/index/change">Change Password</a></li>
							<li ${isLogin?"":"hidden"}><a href="/ASM/index/edit-profile">Edit Profile</a></li>
							<li ${(role==false || role==null)?"hidden":""}><a href="/ASM/manager">Admin</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>