<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>My Bookstore Administration</title>
	<link rel="stylesheet" type="text/css" href="../css/style.css">
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
		<h2 class="pageheading">Administration Dashboard</h2>
	</div>
	
	<div align = "center">
		<hr width = "60%"/>
		<h2>Quick Actions:</h2>
		<b>
		<a href = "create_book">New Book</a> &nbsp;
		<a href = "create_user">New User</a> &nbsp;
		<a href = "create_category">New Category</a> &nbsp;
		<a href = "create_customer">New Customer</a> &nbsp;
		</b>
	</div>
	
	<div align = "center">
		<hr width = "60%"/>
		<h2>Recent Sales</h2>
	
	</div>
	
	<div align = "center">
		<hr width = "60%"/>
		<h2>Recent Reviews</h2>
	
	</div>
	
		<div align = "center">
		<hr width = "60%"/>
		<h2>Statistics</h2>
		<hr width = "60%"/>
	
	</div>
	
	<jsp:directive.include file = "footer.jsp"/>

</body>
</html>