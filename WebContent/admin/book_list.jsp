<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Manage Books - My Bookstore Administration</title>
	
	 <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css?family=Muli:300,400,500,600,700,800,900&display=swap" rel="stylesheet">
	
	<!-- Styles -->
	<link rel="stylesheet" type="text/css" href="../css/style.css">
	<!-- Table styles -->
	<link rel="stylesheet" type="text/css" href="../css/tableStyle.css">
	
	<!-- CSS only -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	
	<!-- Font Awesome CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
	
	<!-- JS, Popper.js, and jQuery -->
	<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</head>
<body>
	
	<jsp:directive.include file = "header.jsp"/>
	
	<div align = "center">
		<h2 class="pageheading">Book Management</h2>
		<h3><a href="new_book">Create New Book</a></h3>
	</div>
	
	<c:if test="${message != null}">
		<div align = "center">
			<h3><i>${message}</i></h3>
		</div>
	</c:if>

	
	<div align = "center">
		<table class="list_items">
			<tr> 
				<th>Index</th>
				<th>Book Id</th>
				<th>Image</th>
				<th>Title</th>
				<th>Author</th>
				<th>Category</th>
				<th>Price</th>
				<th>Last Updated</th>
				<th>Actions</th>
			</tr>
			<c:forEach var ="book" items= "${listBooks}" varStatus = "status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${book.bookId}</td>
					<td>
						<img src="data:image/jpg;base64,${book.base64Image}" width="80" height="100"/>
					</td>
					<td>${book.title}</td>
					<td>${book.author}</td>
					<td>${book.category.name}</td>
					<td>$ ${book.price}</td>
					<td><fmt:formatDate pattern='MM/dd/yyyy' value='${book.lastUpdateTime}'/></td>
					<!-- value = "<fmt:formatDate pattern='MM/dd/yyyy' value='${book.lastUpdateTime}'/>" -->
					<td>
						<a href ="edit_book?id=${book.bookId}">Edit</a> &nbsp;
						<a href ="javascript:void(0)" class="deleteLink" id="${book.bookId}">Delete</a> &nbsp;
					</td>
				</tr>
			
			</c:forEach>
		
		</table>
	</div>
	
	<jsp:directive.include file = "footer.jsp"/>
	
	<script>
	// on confirmation send the request to the DeleteUserServlet
		$(document).ready(function(){
			$(".deleteLink").each(function(){
				$(this).on("click", function(){
					bookId = $(this).attr("id");
					if (confirm("Are you sure, you want to delete your user with id "
							+ bookId + "?")) {
						window.location = "delete_user?userId=" + bookId;
					}
				});
			});
		});
		
	</script>

</body>

</html>