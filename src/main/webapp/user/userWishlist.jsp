<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.library.dto.Book"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>BookStop : Wishlist</title>
<%@include file="../components/allCss.jsp"%>
<link href="../components/userWishlist.css" rel="stylesheet"
	type="text/css">
<script>
	
</script>
</head>

<body>
	<%@include file="../components/userNavbar.jsp"%>

	<c:if test="${ not empty wishlistAdded}">
		<div
			class="alert alert-success alert-dismissible fade show text-center"
			role="alert">
			<strong>${wishlistAdded}</strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<c:remove var="wishlistAdded" />
	</c:if>
	<c:if test="${ not empty removeFailed}">
		<div
			class="alert alert-danger alert-dismissible fade show text-center"
			role="alert">
			<strong>${removeFailed}</strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<c:remove var="removeFailed" />
	</c:if>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-8">
				<div class="row">
					<div class="col">
						<h4 class="mt-3 ml-3 text-warning">
							<i class="fa-solid fa-bookmark"></i> Wishlist
						</h4>
					</div>
				</div>
				<div class="container">
					<div class="row gy-2">
						<c:forEach var="w" items="${wishlist}">
							<div class="ab col-lg-2 col-md-4 d-flex flex-column text-dark">
								<button class="book-link text-light" value="${w.id}">
									<img style="height: 180px; width: 120px"
										src="../img/${w.book.picture}">
								</button>
								<a href="../removeWishlist?id=${w.id}"
									class="btn btn-danger mt-2 text-white pl-2"><i
									class="fa-solid fa-trash-can"></i> Remove </a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<h5 class="mt-3 text-warning">Currently Reading :</h5>
				
				<div class='container'>
        <div class="card mx-auto col-md-6 col-10">
            <img class='mx-auto img-thumbnail'
                src="../img/${record.recordBook.picture}"
                width="500px" height="262px"/>
            <div class="card-body text-center mx-auto">
                <div class='cvp'>
                    <h5 class="card-title">${record.recordBook.bookName}</h5>
								<p class="card-text">
									<span class="text-muted"> ${record.recordBook.author}</span>
								</p>
                </div>
            </div>
        </div>

    </div>
				
				<div class="card card-style" style="max-width: 500px; height: 262px">
					<div class="row g-0">
						<div class="col-sm-5">
							<img src="../img/${record.recordBook.picture}"
								class="card-img-top h-80" alt="...">
						</div>
						<div class="col-sm-7">
							<div class="card-body pl-0">
								<h5 class="card-title">${record.recordBook.bookName}</h5>
								<p class="card-text">
									<span class="text-muted"> ${record.recordBook.author}</span>
								</p>
								<div class="card-text-section">
									<p class="card-text">
										<b>Issue Date :</b> ${record.issueDate}
									</p>
									<p class="card-text">
										<b>Due Date :</b> ${record.dueDate}
									</p>
									<p class="card-text">
										<b>Fine :</b> ${record.fine}
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="container">
					<h5 class="mt-3 text-warning">You might also like :</h5>
					<div id="carouselExampleControls"
						class="carousel slide text-center" data-bs-ride="carousel">
						<div class="carousel-inner">

							<div class="carousel-item active">
								<img src="../img/${recommendedBooks.get(0).picture}"
									style="height: 270px; width: 200px" alt="...">
							</div>
							<div class="carousel-item">
								<img src="../img/${recommendedBooks.get(1).picture}"
									style="height: 270px; width: 200px" alt="...">
							</div>
							<div class="carousel-item">
								<img src="../img/${recommendedBooks.get(2).picture}"
									style="height: 270px; width: 200px" alt="...">
							</div>

							<button class="carousel-control-prev" type="button"
								data-bs-target="#carouselExampleControls" data-bs-slide="prev">
								<span  aria-hidden="true"></span>
								<span class="visually-hidden"><i class="fa-solid fa-circle-chevron-left"></i></span>
							</button>
							<button class="carousel-control-next" type="button"
								data-bs-target="#carouselExampleControls" data-bs-slide="next">
								<span aria-hidden="true"></span>
								<span class="visually-hidden"><i class="fa-solid fa-circle-chevron-right"></i></span>
							</button>
						</div> 
						<div id="default">
							<span style="float: left"> <a
								href="../wishlist?pageNo=${pageNo-1}"
								class="btn btn-dark text-warning ${(pageNo == 0) ? 'disabled' : ''}"><i
									class="fa-solid fa-caret-left"></i> Previous</a></span> <span
								style="float: right"><a
								href="../wishlist?pageNo=${pageNo + 1}"
								class="btn btn-dark text-warning ${(pageNo + 1 == noOfPage) ? 'disabled' : ''}">Next
									<i class="fa-solid fa-caret-right"></i>
							</a></span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

</html>