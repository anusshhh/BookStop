<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BookStop : User History</title>
<%@include file="../components/allCss.jsp"%>
<link href="../components/css3.css" rel="stylesheet" type="text/css">
</head>

<body>
	<jsp:directive.include file="../components/userNavbar.jsp" />
	<div class="container">
		<div class="row text-warning">
			<div><h4 class="col-md-12 mt-2"><i class="fas fa-history"></i> User History</h4></div>
			<div class="col-md-4 ml-auto mt-2">
			<form class="form-inline" action="userHistory" method="get">
					<input class="form-control mr-sm-2 " name="query" type="search"
						placeholder="Search" aria-label="Search">
					<button class="btn btn-warning text-light my-2 my-sm-0" type="submit">Search</button>
				</form></div>
		</div>
		<div class="row">
			<div class="col-md-4">
				
			</div>
		</div>
	</div>

	<!--  -----------------------------------------table------------------------------------------ -->
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
										<th>Book Title</th>
										<th>Category</th>
										<th>Issue Date</th>
										<th>Due Date</th>
										<th>Fine</th>
										<th>Return Date</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach var="h" items="${userHistory}">
										<tr class="alert" role="alert">

											<td>
												<div class="img"
													style="background-image: url(../img/${h.recordBook.picture})"></div>
											</td>
											<td>
												<div class="title">
													<span><c:out value="${h.recordBook.bookName}"></c:out> </span> <span>
														<c:out value="${h.recordBook.author}"></c:out>
													</span>
												</div>
											</td>
											<td>${h.recordBook.category}</td>
											<td>${h.issueDate}</td>
											<td>${h.dueDate}</td>
											<td>${h.fine}</td>
											<td>${h.returnDate}</td>

										</tr>
									</c:forEach>
								</tbody>
							</table>

							<div>
								<span style="float: left"> <a
									href="history?pageNo=${pageNo-1}&query=${query}"
									class="text-warning btn ${(pageNo == 0) ? 'disabled' : ''}"><i
										class="fa-solid fa-caret-left"></i> Previous</a></span> <span
									style="float: right"><a
									href="history?pageNo=${pageNo + 1}&query=${query}"
									class="text-warning btn ${(pageNo + 1 == noOfPage) ? 'disabled' : ''}">Next
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