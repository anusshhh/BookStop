<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BookStop</title>
<%@include file="../components/allCss.jsp"%>

</head>
<body>
	<%@include file="../components/adminNavbar.jsp"%>
	<div class="container-fluid">
		<div class="row vh-100">
			<div class="w-30  m-auto">
				<h2 class="mb-2 text-center">Admin Profile</h2>
				<c:if test="${not empty passChanged}">
					<div class="alert alert-success alert-dismissible fade show"
						role="alert">
						<strong>${passChanged}</strong>
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<c:remove var="passChanged" />
				</c:if>
				<c:if test="${not empty passFailed}">
					<div class="alert alert-success alert-dismissible fade show"
						role="alert">
						<strong>${passFailed}</strong>
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<c:remove var="passFailed" />
				</c:if>
				<c:if test="${not empty successMsg}">
					<div class="alert alert-success alert-dismissible fade show"
						role="alert">
						<strong>${successMsg}</strong>
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<c:remove var="successMsg" />
				</c:if>
				<c:if test="${not empty errorMsg}">
					<div class="alert alert-success alert-dismissible fade show"
						role="alert">
						<strong>${errorMsg}</strong>
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<c:remove var="errorMsg" />
				</c:if>
				<form action="../updateAdminProfile" method="post">
					<label for="exampleInputEmail1">Name</label>
					<div class="row">

						<div class="col">
							<input name="firstName" type="text" class="form-control"
								value="${admin.firstName}">
						</div>
						<div class="col">
							<input name="lastName" type="text" class="form-control"
								value="${admin.lastName}">
						</div>
					</div>
					<div class="form-group mt-2 mb-2">
						<label for="exampleInputEmail1">Email Address</label> <input
							name="email" type="email" class="form-control"
							id="exampleInputEmail1" aria-describedby="emailHelp"
							value="${admin.email}">
					</div>
					<div class="form-group mb-2">
						<label for="exampleInputPassword1">Phone number</label> <input
							name="phone" type="number" class="form-control"
							id="exampleInputNumber" value="${admin.phone}">
					</div>

					<div class="text-center">
						<button type="submit" id="submit" class="btn btn-info">
							Save</button>
					</div>

				</form>
				<button class="btn btn-danger" data-toggle="modal"
					data-target=".change-pass">Change Password</button>
				<div class="modal fade change-pass bg-dark " role="dialog">
					<div class="modal-dialog modal-lg ">
						<div class="modal-content ">
							<div class="modal-header">

								<h5 class="text-info">Change Password</h5>
								<a class="close text-danger" data-dismiss="modal"> <i
									class="fas fa-times-circle"></i>
								</a>
							</div>
							<div class="modal-body ">
								<div class="container-fluid ">
									<div class="row d-flex justify-content-center">
										<div class="col-md-6 ">
											<div class="w-80  m-auto">

												<form action="../adminChangePassword" method="POST">

													<div class="form-group mt-1 mb-1 ">
														<label for="exampleInputEmail1">Old Password:</label> <input
															name="oldPassword" type="password" class="form-control"
															id="exampleInputEmail1" aria-describedby="emailHelp"
															placeholder="Enter Old Password">
													</div>
													<div class="form-group mt-1 mb-1 ">
														<label for="exampleInputEmail1">New Password:</label> <input
															name="newPassword" type="password" class="form-control"
															id="exampleInputEmail1" aria-describedby="emailHelp"
															placeholder="Enter New Password">
													</div>
													<div class="form-group mt-1 mb-1 ">
														<label for="exampleInputEmail1">Confirm New Password:</label> <input
															name="confirmPassword" type="password" class="form-control"
															id="exampleInputEmail1" aria-describedby="emailHelp"
															placeholder="Confirm New Password">
													</div>



													<div class="modal-footer">

														<div class="mr-auto ml-auto">
															<button type="submit" id="submit" class="btn btn-success">Save</button>
														</div>
													</div>

												</form>
											</div>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>