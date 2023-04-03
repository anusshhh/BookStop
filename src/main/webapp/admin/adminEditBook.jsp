<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@page import="com.library.dao.DbConnection"%>
<%@page import="com.library.dto.Book"%>
<%@page import="com.library.dto.Admin"%>
<%@page import="com.library.dao.BookDao"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BookStop</title>
<%@include file="../components/allCss.jsp"%>
<style>
.navbar .nav-item:hover .nav-link {
	background-color: white;
	color: #17a2b8;
	border-radius: 15px;
}
</style>
</head>
<body>

	<%@include file="../components/adminNavbar.jsp"%>
	<%
	int bookid = Integer.parseInt(request.getParameter("bookid"));
	BookDao dao = new BookDao(DbConnection.jdbcConnection());
	Book b = dao.editDisplay(bookid);
	%>
	<div class="container-fluid ">
		<div class="row vh-100 d-flex justify-content-center">

			<div class="col-md-6 ">

				<div class="w-50  m-auto">
					<h4 class="text-center">Edit Book</h4>
					<form action="editBook" method="post" enctype="multipart/form-data">
						<input type="hidden" name="bookid" value="<%=b.getBookId()%>">
						<div class="form-group mt-1 mb-1 ">
							<label for="exampleInputEmail1">Book Name:</label> <input
								value="<%=b.getBookName()%>" name="bookname" type="text"
								class="form-control" id="exampleInputEmail1"
								aria-describedby="emailHelp" placeholder="Enter name">
						</div>

						<div class="form-group mt-1 mb-1">
							<label for="exampleInputEmail1">Author:</label> <input
								value="<%=b.getAuthor()%>" name="author" type="text"
								class="form-control" id="exampleInputEmail1"
								aria-describedby="emailHelp" placeholder="Enter author">
						</div>

						<div class="form-group mt-1 mb-1">
							<label for="inputState">Category:</label> <select name="category"
								class="form-control" id="inputState">
								<option selected><%=b.getCategory()%></option>
								<option>Action</option>
								<option>Adventure</option>
								<option>Horror</option>
							</select>
						</div>

						<div class="row">

							<div class="col">
								<label for="exampleInputEmail1">Price: </label> <input
									value="<%=b.getPrice()%>" name="price" type="text"
									class="form-control" placeholder="Enter price">
							</div>
							<div class="col">
								<label for="exampleInputEmail1">Quantity:</label> <input
									value="<%=b.getQuantity()%>" name="quantity" type="number"
									class="form-control" placeholder="Quantity">
							</div>
						</div>

						<div class="form-group mt-1 mb-1">
							<label for="summary">Summary: </label>
							<textarea name="summary" rows="3" cols="" id="summary"
								placeholder="Enter summary" class="form-control"><%=b.getSummary()%></textarea>
						</div>
						<div class="form-group mt-1 mb-1">
							<label for="exampleFileControlFile1">Upload Book:</label> <input
								name="bookpic" type="file" class="form-control-file"
								id="exampleFileControlFile1" aria-describedby="emailHelp"
								placeholder="Enter name">
						</div>


						<div class="modal-footer">

							<div class="mr-auto ml-auto">
								<button type="submit" class="btn btn-success">Save</button>
							</div>
						</div>

					</form>
				</div>
			</div>

		</div>
	</div>



</body>
</html>