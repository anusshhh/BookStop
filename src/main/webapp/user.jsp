<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.Connection"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BookStop</title>
<%@include file="components/allCss.jsp"%>
<style type="text/css">
.login-img {
	background: url("img/books.jpg");
	width: 100%;
	height: 90.5vh;
	background-repeat: no-repeat;
	background-size: cover;
}
</style>
</head>
<body>
	<%@include file="components/navbar.jsp"%>


	<div class="container-fluid">
		<div class="row vh-100">
			<div class="col-md-6 login-img"></div>
			<div class="col-md-6 align-self-center">
				<div class="w-50 m-auto">
					<h2 class="mb-2">User Login</h2>
					<c:if test="${not empty userRegSuccess}">
						<div class="alert alert-success alert-dismissible fade show"
							role="alert">
							<strong>${userRegSuccess}</strong>
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<c:remove var="userRegSuccess" />
					</c:if>
					<c:if test="${not empty successLogout}">
						<div class="alert alert-success alert-dismissible fade show"
							role="alert">
							<strong>${successLogout}</strong>
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<c:remove var="successLogout" />
					</c:if>


					<c:if test="${not empty userLoginFail}">
						<div class="alert alert-danger alert-dismissible fade show"
							role="alert">
							<strong>${userLoginFail}</strong>
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<c:remove var="userLoginFail" />
					</c:if>

					<form action="userLogin" method="post">
						<div class="form-group">
							<label for="exampleInputEmail1">Email address</label> <input
								name="email" type="email" class="form-control" id="exampleInputEmail1"
								aria-describedby="emailHelp" placeholder="Enter email">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Password</label> <input
								name="password" type="password" class="form-control" id="exampleInputPassword1"
								placeholder="Password">
						</div>

						<div class="form-group row justify-content-between">
							<div class="col-md-auto">
								<a class="text-warning" href="userReg.jsp">Create Account</a>
							</div>
							<div class="col-md-auto">
								<a class="text-warning" href="#">Forgot Password?</a>
							</div>
						</div>
						<div class="text-right">
							<button type="submit" class="btn btn-warning">Login</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>