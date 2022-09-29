<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Customer Profile - The online Bookstore</title>
	
	<!-- Custom CSS -->
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/home-style.css">
	<link rel="stylesheet" type="text/css" href="css/tableStyle.css">
	
	<!-- CSS only -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

	<!-- Font Awesome CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
	
	<!-- JS, Popper.js, and jQuery -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</head>
<body>
	
	<jsp:directive.include file = "header.jsp"/>
	
	<div align = "center">
		<br/>
		<h3>Welcome, ${loggedInCustomer.fullname}</h3>
		<br/>
		
		<table class="list_items">
			<tr>
				<td><b>Email address: </b></td>
				<td>${loggedInCustomer.email}</td>	
			</tr>
			
			<tr>
				<td><b>Full Name: </b></td>
				<td>${loggedInCustomer.fullname}</td>	
			</tr>
			
			<tr>
				<td><b>Phone Number: </b></td>
				<td>${loggedInCustomer.phone}</td>	
			</tr>
			
			<tr>
				<td><b>Address: </b></td>
				<td>${loggedInCustomer.address}</td>	
			</tr>
			
			<tr>
				<td><b>City: </b></td>
				<td>${loggedInCustomer.city}</td>	
			</tr>
			
			<tr>
				<td><b>Zip Code: </b></td>
				<td>${loggedInCustomer.zipcode}</td>	
			</tr>
			
			<tr>
				<td><b>Country: </b></td>
				<td>${loggedInCustomer.country}</td>	
			</tr>
			
			<tr><td colspan="2">&nbsp;</td></tr>
			
			<tr><td colspan="2"><a href="edit_customer_profile">Edit My Profile</a></td></tr>
			
		</table>
	</div>
	
	<jsp:directive.include file = "footer.jsp"/>

</body>
</html>