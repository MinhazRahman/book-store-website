<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin Login</title>
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

	<jsp:directive.include  file = "header.jsp"/>

	<div align="center">
		<h1>Book Store Administration</h1>
		<h2>Please login</h2>
		<form id="formLogin" action="login" method="post">
			<table>
				<tr>
					<td>Email: </td>
					<td><input type="text" name="email" id="email" size = "20"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" id="password" size = "20"/></td>
				</tr>
				<tr>
					<td></td>
					<td align="center"><button type = "submit" class="site-button">Login</button></td>		
				</tr>
			</table>
		
		</form>
	</div>

	<jsp:directive.include  file = "footer.jsp"/>
	
	<script type="text/javascript">

		$(document).ready(function() {

			$("#formLogin").validate({
				rules : {
					email : {
						required : true,
						email : true
					},
					password : "required",
				},
				messages : {
					email : {
						required : "Please enter your email",
						email : "Please enter a valid email address!"
					},
					password : "Please enter password"
				}
			});
		});
	</script>

</body>
</html>