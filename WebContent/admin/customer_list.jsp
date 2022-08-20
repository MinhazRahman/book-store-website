<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Manage Customers - My Bookstore Administration</title>
	
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
		<h2 class="pageheading">Customer Management</h2>
		<h3><a href="customer_form.jsp">Create New Customer</a></h3>
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
				<th>ID</th>
				<th>Email</th>
				<th>Full Name</th>
				<th>City</th>
				<th>Country</th>
				<th>Registered Date</th>
				<th>Actions</th>
			</tr>
			<c:forEach var ="customer" items= "${listCustomers}" varStatus = "status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${customer.customerId}</td>
					<td>${customer.email}</td>
					<td>${customer.fullname}</td>
					<td>${customer.city}</td>
					<td>${customer.country}</td>
					<td><fmt:formatDate pattern='MM/dd/yyyy' value='${customer.registerDate}'/></td>
					<td>
						<a href ="edit_customer?id=${customer.customerId}">Edit</a> &nbsp;
						<a href ="javascript:void(0)" class="deleteLink" id="${customer.customerId}">Delete</a> &nbsp;
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
					customerId = $(this).attr("id");
					if (confirm("Are you sure, you want to delete Customer with id "
							+ customerId + "?")) {
						window.location = "delete_customer?id=" + customerId;
					}
				});
			});
		});
		
	</script>

</body>

</html>