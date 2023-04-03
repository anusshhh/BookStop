<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
					<h2 class="text-center mb-2">User Register</h2>
					<c:if test="${not empty userRegFail}">
						<div class="alert alert-danger alert-dismissible fade show"
							role="alert">
							<strong>${userRegFail}</strong>
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						
						
						<c:remove var="userRegFail" />
					</c:if>


					<form action="userRegister" method="post">
						<label for="exampleInputEmail1">Name</label>
						<div class="row">

							<div class="col">
								<input name="firstName" type="text" class="form-control"
									placeholder="First name">
							</div>
							<div class="col">
								<input name="lastName" type="text" class="form-control"
									placeholder="Last name">
							</div>
						</div>
						<div class="form-group mt-2 mb-2">
							<label for="exampleInputEmail1">Email Address</label> <input
								name="email" type="email" class="form-control"
								id="exampleInputEmail1" aria-describedby="emailHelp"
								placeholder="Email">
						</div>
						<div class="form-group mb-2">
							<label for="exampleInputPassword1">Password</label> <input
								name="password" type="password" class="form-control"
								id="exampleInputPassword1" placeholder="Password">
						</div>

						<div class="form-group mb-2">
							<label for="exampleInputPassword1">Phone number</label> <input
								name="phone" type="number" class="form-control"
								id="exampleInputPassword1" placeholder="Phone number">
						</div>

						<div class="form-group mb-2">
							<label for="exampleInputPassword1">Library Code</label> <input
								name="userAdminId" type="number" class="form-control"
								id="exampleInputPassword1" placeholder="Library code">
						</div>

						<div class="form-group row justify-content-center">
							<div class="col-md-auto">
								<a href="user.jsp" class="text-warning">Already have an
									account?</a>
							</div>
						</div>
						<div class="text-center">
							<button type="submit" class="btn btn-warning">Register</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>