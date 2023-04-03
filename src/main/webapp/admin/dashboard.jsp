<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BookStop</title>
<%@include file="../components/allCss.jsp"%>
<link href="../components/dashboard.css" rel="stylesheet"
	type="text/css">
</head>
<body class="bg">
	<%@include file="../components/adminNavbar.jsp"%>
	<div class="container-fluid">
		<h3 class="mt-2">Dashboard</h3>
		<div class="row">
			<div class="col-md-3 ">
				<div class="card bg-c-blue order-card">
					<div class="card-block">
						<h6 class="m-b-20">Members:</h6>
						<h2 class="text-right">
							<i class="fas fa-users f-left"></i><span>${userCount}</span>
						</h2>
						<p class="m-b-0">
							Completed Orders<span class="f-right">351</span>
						</p>
					</div>
				</div>
			</div>

			<div class="col-md-3 ">
				<div class="card bg-c-green order-card">
					<div class="card-block">
						<h6 class="m-b-20">Total Books:</h6>
						<h2 class="text-right">
							<i class="fas fa-book f-left"></i><span>${bookCount}</span>
						</h2>
						<p class="m-b-0">
							Completed Orders<span class="f-right">351</span>
						</p>
					</div>
				</div>
			</div>

			<div class="col-md-3 ">
				<div class="card bg-c-yellow order-card">
					<div class="card-block">
						<h6 class="m-b-20">Books issued:</h6>
						<h2 class="text-right">
							<i class="fas fa-user-graduate f-left"></i><span>${recordCount}</span>
						</h2>
						<p class="m-b-0">
							Completed Orders<span class="f-right">351</span>
						</p>
					</div>
				</div>
			</div>

			<div class="col-md-3">
				<div class="card bg-c-pink order-card">
					<div class="card-block">
						<h6 class="m-b-20">Total Pending Fine:</h6>
						<h2 class="text-right">
							<i class="fas fa-exclamation-triangle f-left"></i><span>Rs.${pendingFine}</span>
						</h2>
						<p class="m-b-0">
							Number of users: <span class="f-right">${pendingFineUsers}</span>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- --------------------------------------------------tables--------------------------------------- -->

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6">
				<h4>New Members</h4>
				<table class="table table-hover table-sm">
					<thead>
						<tr>
							<th scope="col">Id</th>
							<th scope="col">First Name</th>
							<th scope="col">Last Name</th>
							<th scope="col">Email</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="u" items="${userList}">
							<tr>
								<th scope="row">${u.userId}</th>
								<td>${u.firstName}</td>
								<td>${u.lastName}</td>
								<td>${u.email}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="text-center">
					<a href="#">View All</a>
				</div>
			</div>

			<div class="col-md-6">

				<h4>New Books</h4>
				<table class="table table-hover table-sm">
					<thead>
						<tr>
							<th scope="col">Book Id</th>
							<th scope="col">Book Name</th>
							<th scope="col">Author</th>
							<th scope="col">Quantity</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="b" items="${books}">
							<tr>
								<th scope="row">${b.bookId}</th>
								<td>${b.bookName}</td>
								<td>${b.author}</td>
								<td>${b.quantity}</td>
							</tr>
						</c:forEach>
					

					</tbody>
				</table>
				<div class="text-center">
					<a href="../adminLibrary">View All</a>
				</div>
			</div>

		</div>
	</div>


</body>
</html>