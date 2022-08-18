<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Create New Customer</title>
	
	<!-- custom and jQuery CSS -->
	<link rel="stylesheet" type="text/css" href="../css/tableStyle.css">
	<link rel="stylesheet" type="text/css" href="../css/style.css">
	<link rel="stylesheet" type="text/css" href="../css/jquery-ui.min.css">
	
	<link rel="stylesheet" href="../css/richtext.min.css">
	
	
	
	<!--  Font-qwesome for reach text area -->
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<!-- CSS only -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	
	<!-- Font Awesome CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

	<!-- JS, Popper.js, and jQuery -->
	<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
	
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">
			<c:if test="${customer != null}">
				Edit Customer
			</c:if>
			
			<c:if test="${customer == null}">
				Create New Customer
			</c:if>
			
		</h2>

	</div>

	<div align="center">
		<c:if test="${customer != null}">
			<form action="update_customer" method="post" id="customer-form">
			<input type="hidden" id = "customerId" name="customerId" value="${customer.customerId}">
		</c:if>
		<c:if test="${customer == null}">
			<form action="create_customer" method="post" id="customer-form">
		</c:if>
			<table class= "form">
				<tr>
					<td align = "right">Email:</td>
					<td align = "left"><input type="text" id = "email" name="email" size="45" value = "${customer.email}"></td>
				</tr>

				<tr>
					<td align = "right">Full Name:</td>
					<td align = "left"><input type="text" id = "fullName" name="fullName" size="45" value = "${customer.fullName}"></td>
				</tr>
				
				<tr>
					<td align = "right">Password:</td>
					<td align = "left"><input type="password" id = "password" name="password" size="45" value="${customer.password}"></td>
				</tr>

				<tr>
					<td align = "right">Confirm Password:</td>
					<td align = "left"><input type="password" id = "confirmPassword" name="confirmPassword" size="45" value="${customer.password}"></td>
				</tr>

				<tr>
					<td align = "right">Phone Number:</td>
					<td align = "left"><input type="text" id = "phone" name="phone" size="45" value="${customer.phone}"></td>
				</tr>

				<tr>
					<td align = "right">Address:</td>
					<td align = "left"><input type="text" id = "address" name="address" size="45" value="${customer.address}"></td>
				</tr>
				
				<tr>
					<td align = "right">City:</td>
					<td align = "left"><input type="text" id = "city" name="city" size="45" value="${customer.city}"></td>
				</tr>
				
				<tr>
					<td align = "right">Zip Code:</td>
					<td align = "left"><input type="text" id = "zipCode" name="zipCode" size="45" value="${customer.zipCode}"></td>
				</tr>
				
				<tr>
					<td align = "right">Country:</td>
					<td align = "left"><input type="text" id = "country" name="country" size="45" value="${customer.country}"></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td colspan = "2" align = "center">
						<button class="site-button" type="submit">Save</button>
						<button class="site-button" type="button" id="buttonCancel">Cancel</button>
					</td>
				</tr>

			</table>

		</form>

	</div>

	<jsp:directive.include file="footer.jsp" />

</body>

	<script type="text/javascript">
		
		$(document).ready(function() {
			
			/* form validation */
			$("#customer-form").validate({
				rules : {
					email: {
						required: true,
						email: true
					},
					fullName : "required",
					password : "required",
					confirmPassword : {
						required : true,
						equalTo : "#password"
					},
					phoneNumber : "required",
					address : "required",
					city : "required",
					zipCode : "required",
					country : "required"
					
				},
				messages : {
					email: { 
						required : "Please enter email address",
						email : "Please enter a valid email address"
					},
					fullName : "Please enter customer's full name",
					password : "Please enter password",
					confirmPassword : {
						required : "Please confirm password",
						equalTo : "Confirm password doesn't match actual password"
					},
					phoneNumber : "Please enter phone number",
					address : "Please enter address",
					city : "Please enter city",
					zipCode : "Please enter zip code",
					country : "Please enter country"
					
				}
			});
			
			/* cancel button */
			$("#buttonCancel").click(function() {
				history.go(-1);
			});
		});
		
	</script>
</html>