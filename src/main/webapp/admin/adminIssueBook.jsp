<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BookStop : Records</title>
<jsp:directive.include file="../components/allCss.jsp"/>
<link href="../components/css2.css" rel="stylesheet" type="text/css">
<script>
	<jsp:directive.include file="../js/categoryDynamicDropdown.js"/>
</script>
</head>
<body>
	<jsp:directive.include file="../components/adminNavbar.jsp" />
	<c:if test="${ not empty recordAdded}">
		<div
			class="alert alert-success alert-dismissible fade show text-center"
			role="alert">
			<strong>${recordAdded}</strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<c:remove var="recordAdded" />
	</c:if>

	<c:if test="${ not empty renewed}">
		<div
			class="alert alert-success alert-dismissible fade show text-center"
			role="alert">
			<strong>${renewed}</strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<c:remove var="renewed" />
	</c:if>

	<c:if test="${ not empty renewFailed}">
		<div
			class="alert alert-danger alert-dismissible fade show text-center"
			role="alert">
			<strong>${renewFailed}</strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<c:remove var="renewFailed" />
	</c:if>
	<c:if test="${ not empty returned}">
		<div
			class="alert alert-success alert-dismissible fade show text-center"
			role="alert">
			<strong>${returned}</strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<c:remove var="returned" />
	</c:if>
	<c:if test="${ not empty returnFailed}">
		<div
			class="alert alert-danger alert-dismissible fade show text-center"
			role="alert">
			<strong>${returnFailed}</strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<c:remove var="returnFailed" />
	</c:if>

	<div class="container">
		<div class="text-info">
			<h4 class="mt-1">User Records</h4>
		</div>
		<div class="row">
			<div class="col-md-8">
				<button class="btn btn-info mt-1" data-toggle="modal"
					data-target=".add-book-form">
					<i class="fas fa-plus"></i> Add Record
				</button>

				<!-- Login / Register Modal-->
				<div class="modal fade add-book-form bg-dark " role="dialog">
					<div class="modal-dialog modal-md ">
						<div class="modal-content ">
							<div class="modal-header">

								<h5 class="text-info">Add Record</h5>
								<a class="close text-danger" data-dismiss="modal"> <i
									class="fas fa-times-circle"></i>
								</a>
							</div>
							<div class="modal-body ">
								<div class="container-fluid ">
									<div class="row vh-100 d-flex justify-content-center">
										<div class="col-md-9 ">
											<div class="w-60  m-auto">

												<form action="../addRecord" method="post">

													<div class="form-group mt-1 mb-1">
														<input type="hidden" value="1" name="adminId"> <label
															for="inputState">Select User:</label> <select name="user"
															class="form-control" id="inputState">
															<option selected>--Select--</option>
															<c:forEach var="u" items="${userList}">
																<option>${u.userId}</option>
															</c:forEach>
														</select>
													</div>


													<div class="form-group mt-1 mb-1">

														<label for="inputState">Select Category:</label> <select
															id="category" name="category" class="form-control">
															<option selected>--Select--</option>
														</select>
													</div>
													<div class="form-group mt-1 mb-1">
														<label for="inputState">Select Book:</label> <select
															id="books" name="books" class="form-control">
															<option selected>--Select--</option>
														</select>
													</div>


													<div class="row">

														<div class="col">
															<label for="exampleInputEmail1">Issue Date: </label> <input
																name="issueDate" type="date" class="form-control">
														</div>


													</div>

													<div class="modal-footer mt-3">

														<div class="mr-auto ml-auto">
															<button type="submit" class="btn btn-success">Save</button>
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

			<div class="col-md-4">
				<form class="form-inline" action="../records" method="get">
					<input class="form-control mr-sm-2" name="query" type="search"
						placeholder="Search" aria-label="Search">
					<button class="btn btn-info my-2 my-sm-0" type="submit">Search</button>
				</form>
			</div>
		</div>
	</div>

	<!--  ----------------------------------table----------------------------------- -->
	<div class="row ftco-section">
		<div class="col-md-12 pl-0">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="table">
							<table class="table text-center">
								<thead class="thead-primary">
									<tr>
										<th>Record ID</th>
										<th>Name</th>
										<th>Book Title</th>
										<th>Category</th>
										<th>Issue Date</th>
										<th>Due Date</th>
										<th>Fine</th>
										<th>Contact</th>
										<th>Action</th>

									</tr>
								</thead>

								<tbody>
									<c:forEach var="r" items="${record}">
										<tr class="alert" role="alert">

											<td>${r.recordId}</td>
											<td>${r.recordUser.firstName} ${r.recordUser.lastName}</td>
											<td>
												<div class="title">
													<span><c:out value="${r.recordBook.bookName}"></c:out> </span> <span>
														<c:out value="${r.recordBook.author}"></c:out>
													</span>
												</div>
											</td>
											<td>${r.recordBook.category}</td>
											<td>${r.issueDate}</td>
											<td>${r.dueDate}</td>
											<td>${r.fine}</td>
											<td>${r.recordUser.phone}</td>
											<td><a
												href="../Renew?id=${r.recordId}&dueDate=${r.dueDate}">
													<button class="btn btn-sm btn-success">
														<i class="fa-solid fa-plus"></i> Renew
													</button>
											</a> <a href="../Return?id=${r.recordId}"><button
														class="btn btn-sm btn-danger">
														<i class="fa-solid fa-ban"></i> Return
													</button> </a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

							<div>
								<span style="float: left"> <a
									href="../records?pageNo=${pageNo-1}&query=${query}"
									class="btn ${(pageNo == 0) ? 'disabled' : ''}"><i
										class="fa-solid fa-caret-left"></i> Previous</a></span> <span
									style="float: right"><a
									href="../records?pageNo=${pageNo + 1}&query=${query}"
									class="btn ${(pageNo + 1 == noOfPage) ? 'disabled' : ''}">Next
										<i class="fa-solid fa-caret-right"></i>
								</a></span>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>