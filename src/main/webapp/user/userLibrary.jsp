<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>BookStop : Library</title>
<%@include file="../components/allCss.jsp"%>
<link href="../components/userLibrary.css" rel="stylesheet"
	type="text/css">
<script>
	<jsp:directive.include file="../js/userLibrary.js"/>
</script>
</head>

<body>
	<%@include file="../components/userNavbar.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-8">
				<div class="row">
					<div class="col">
						<h4 class="mt-3 ml-3 text-warning">
							<i class="fa-solid fa-book-open"></i> Library
						</h4>
					</div>
					<div class="col">
						<h6 class="text-right mt-4 text-warning">Total Books :
							${totalBooks}</h6>
					</div>
				</div>
				<div class="container">
					<div class="row gy-2 books"></div>

					<div class="row gy-2 allBooks">
						<c:forEach var="b" items="${books}">
							<div class="ab col-lg-2 col-md-4 d-flex flex-column text-dark">
								<button class="book-link text-light" value="${b.bookId}">
									<img style="height: 180px; width: 120px"
										src="../img/${b.picture}">
								</button>
								<a href="../addWishlist?bookId=${b.bookId}"
									class="btn btn-warning mt-2 text-white pl-2"><i
									class="fas fa-plus"></i> Wishlist </a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<h5 class="mt-3">Browse</h5>
				<form class="form-inline" action="../userLibrary" method="GET">
					<input class="form-control mr-sm-2 ml-3" name="query"
						type="search" placeholder="Search" aria-label="Search"> <input
						 class="btn btn-warning text-white" type="submit" value="Search">
					<i class="fa-solid fa-magnifying-glass"></i>

				</form>

				<div class="container">
					<div class="accordion" id="accordionExample">
						<div class="item">
							<div class="item-header" id="headingOne">
								<h2 class="mb-0">
									<button class="btn btn-link collapsed" type="button"
										data-toggle="collapse" data-target="#collapseOne"
										aria-expanded="true" aria-controls="collapseOne">
										Category <i class="fa fa-angle-down"></i>
									</button>
								</h2>
							</div>
							<div id="collapseOne" class="collapse"
								aria-labelledby="headingOne" data-parent="#accordionExample">
								<div class="t-p pl-1">
									<select id="category"
										class="form-control bg-dark text-white pl-1">
										<option class="text-white">Select Category</option>
									</select>
								</div>
							</div>
						</div>
						<div class="item">
							<div class="item-header" id="headingTwo">
								<h2 class="mb-0">
									<button class="btn btn-link collapsed" type="button"
										data-toggle="collapse" data-target="#collapseTwo"
										aria-expanded="false" aria-controls="collapseTwo">
										Book Summary <i class="fa fa-angle-down"></i>
									</button>
								</h2>
							</div>
							<div id="collapseTwo" class="collapse"
								aria-labelledby="headingTwo" data-parent="#accordionExample">
								<div id="displaySummary" class="t-p-1 text-warning"></div>
							</div>
						</div>
						<div class="item">
							<div class="item-header" id="headingThree">
								<h2 class="mb-0">
									<button class="btn btn-link collapsed" type="button"
										data-toggle="collapse" data-target="#collapseThree"
										aria-expanded="false" aria-controls="collapseThree">
										My Other Projects <i class="fa fa-angle-down"></i>
									</button>
								</h2>
							</div>
							<div id="collapseThree" class="collapse"
								aria-labelledby="headingThree" data-parent="#accordionExample">
								<div class="t-p">It is a long established fact that a
									reader will be distracted by the readable content of a page
									when looking at its layout. The point of using Lorem Ipsum is
									that it has a more-or-less normal distribution of letters, as
									opposed to using 'Content here, content here', making it look
									like readable English. </div>
							</div>
						</div>
						<div id="default">
							<span style="float: left"> <a
								href="../userLibrary?pageNo=${pageNo-1}&query=${query}"
								class="btn btn-dark text-warning ${(pageNo == 0) ? 'disabled' : ''}"><i
									class="fa-solid fa-caret-left"></i> Previous</a></span> <span
								style="float: right"><a
								href="../userLibrary?pageNo=${pageNo + 1}&query=${query}"
								class="btn btn-dark text-warning ${(pageNo + 1 == noOfPage) ? 'disabled' : ''}">Next
									<i class="fa-solid fa-caret-right"></i>
							</a></span>
						</div> 
						<div id="filter"></div>
					</div>
				</div>
			</div>
		</div>
		</div>
</body>

</html>