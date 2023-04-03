<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BookStop : Library</title>
<%@include file="../components/allCss.jsp"%>
<link href="../components/css2.css" rel="stylesheet" type="text/css">
</head>

<body>
	<jsp:directive.include file="../components/adminNavbar.jsp" />
	
	<c:if test="${empty books}">
		<c:redirect url="adminLibrary?pageNo=${param.pageNo}&query=${param.query}" />
	</c:if>

	<c:if test="${ not empty bookAdded}">
		<div
			class="alert alert-success alert-dismissible fade show text-center"
			role="alert">
			<strong>${bookAdded}</strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<c:remove var="bookAdded" />
	</c:if>
	<c:if test="${ not empty bookUpdated}">
		<div
			class="alert alert-success alert-dismissible fade show text-center"
			role="alert">
			<strong>${bookUpdated}</strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<c:remove var="bookUpdated" />
	</c:if>

	<c:if test="${ not empty bookNotAdded}">
		<div
			class="alert alert-danger alert-dismissible fade show text-center"
			role="alert">
			<strong>${bookNotAdded}</strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<c:remove var="bookNotAdded" />
	</c:if>


	<c:if test="${ not empty updateFailed}">
		<div
			class="alert alert-danger alert-dismissible fade show text-center"
			role="alert">
			<strong>${updateFailed}</strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<c:remove var="updateFailed" />
	</c:if>


	<div class="container">
		<div class="text-info ">
			<h4 class="mt-1">Library</h4>
		</div>
		<div class="row">
			<div class="col-md-8">
				<button class="btn btn-info" data-toggle="modal"
					data-target=".add-book-form">
					<i class="fas fa-plus"></i> Add Book
				</button>

				<!-- Login / Register Modal-->
				<div class="modal fade add-book-form bg-dark " role="dialog">
					<div class="modal-dialog modal-lg ">
						<div class="modal-content ">
							<div class="modal-header">

								<h5 class="text-info">Add Book</h5>
								<a class="close text-danger" data-dismiss="modal"> <i
									class="fas fa-times-circle"></i>
								</a>
							</div>
							<div class="modal-body ">
								<div class="container-fluid ">
									<div class="row vh-100 d-flex justify-content-center">
										<div class="col-md-6 ">
											<div class="w-80  m-auto">

												<form action="../addBook" enctype="multipart/form-data"
													method="POST">

													<div class="form-group mt-1 mb-1 ">
														<label for="exampleInputEmail1">Book Name:</label> <input
															name="bookname" type="text" class="form-control"
															id="exampleInputEmail1" aria-describedby="emailHelp"
															placeholder="Enter name">
													</div>

													<div class="form-group mt-1 mb-1">
														<label for="exampleInputEmail1">Author:</label> <input
															name="author" type="text" class="form-control"
															id="exampleInputEmail1" aria-describedby="emailHelp"
															placeholder="Enter author">
													</div>

													<div class="form-group mt-1 mb-1">
														<label for="inputState">Category:</label> <select
															name="category" class="form-control" id="inputState">
															<option selected>--select--</option>
															<c:forEach var="c" items="${category}">
																<option>${c.getCategory()}</option>
															</c:forEach>
														</select>
													</div>

													<div class="row">

														<div class="col">
															<label for="exampleInputEmail1">Price: </label> <input
																name="price" type="text" class="form-control"
																placeholder="Enter price">
														</div>
														<div class="col">
															<label for="exampleInputEmail1">Quantity:</label> <input
																name="quantity" type="number" class="form-control"
																placeholder="Quantity">
														</div>
													</div>

													<div class="form-group mt-1 mb-1">
														<label for="summary">Summary: </label>
														<textarea name="summary" rows="3" cols="" id="summary"
															placeholder="Enter summary" class="form-control"></textarea>
													</div>
													<div class="form-group mt-1 mb-1">
														<label for="exampleFileControlFile1">Upload Book:</label>
														<input name="bookpic" type="file"
															class="form-control-file" id="exampleFileControlFile1"
															aria-describedby="emailHelp" placeholder="Enter name">
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

			<div class="col-md-4">
				<form class="form-inline" action="adminLibrary" method="get">
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
										<th>&nbsp;</th>
										<th>Book</th>
										<th>Category</th>
										<th>Price</th>
										<th>Quantity</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>

									<c:forEach var="b"
										items="${books}">
										<tr class="alert" role="alert">

											<td>
												<div class="img"
													style="background-image: url(../img/${b.picture})"></div>
											</td>
											<td>
												<div class="title">
													<span><c:out value="${b.getBookName()}"></c:out> </span> <span>
														<c:out value="${b.getAuthor()}"></c:out>
													</span>
												</div>
											</td>
											<td>${b.getCategory()}</td>
											<td>Rs.${b.getPrice()}</td>
											<td>${b.getQuantity()}</td>

											<td>
												<button tabindex="0" class="btn btn-sm btn-danger"
													role="button" data-toggle="popover" data-trigger="focus"
													title="${b.getSummary()}"
													data-content="And here's some amazing content. It's very engaging. Right?">
													<i class="fas fa-info-circle"></i>
												</button> <a href="adminEditBook.jsp?bookid=${b.getBookId()}">
													Edit</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							
							<div>
								<span style="float: left"> <a
									href="adminLibrary?pageNo=${pageNo-1}&query=${query}"
									class="btn ${(pageNo == 0) ? 'disabled' : ''}"><i
										class="fa-solid fa-caret-left"></i> Previous</a></span> <span
									style="float: right"><a
									href="adminLibrary?pageNo=${pageNo+1}&query=${query}"
									class="btn ${(pageNo+1 == noOfPage) ? 'disabled' : ''}">Next
										<i class="fa-solid fa-caret-right"></i>
								</a></span>
							</div>


						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(function() {
			$('[data-toggle="popover"]').popover()
		})
		$('.popover-dismiss').popover({
			trigger : 'focus'
		})
	</script>
</body>
</html>
