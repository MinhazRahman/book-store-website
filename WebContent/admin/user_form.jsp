<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Create New User</title>
	<link rel="stylesheet" type="text/css" href="../css/tableStyle.css">
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

	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">
			<c:if test="${user != null}">
				Edit User
			</c:if>
			
			<c:if test="${user == null}">
				Create New User
			</c:if>
			
		</h2>

	</div>

	<div align="center">
		<c:if test="${user != null}">
			<form action="update_user" method="post" onsubmit="return validateFormInput()">
			<input type="hidden" name="userId" value="${user.userId}">
		</c:if>
		<c:if test="${user == null}">
			<form action="create_user" method="post" onsubmit="return validateFormInput()">
		</c:if>
			<table>
				<tr>
					<td align = "right">Email:</td>
					<td align = "left"><input type="text" id = "email" name="email" size="20" value="${user.email}"></td>
				</tr>

				<tr>
					<td align = "right">Full Name:</td>
					<td align = "left"><input type="text" id = "fullname" name="fullname" size="20" value = "${user.fullName}"></td>
				</tr>

				<tr>
					<td align = "right">Password:</td>
					<td align = "left"><input type="password" id = "password" name="password" size="20" value = "${user.password}"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td colspan = "2" align = "center">
						<button class="site-button" type="submit">Save</button>
						<button class="site-button" type="button" onclick="javascript:history.go(-1)">Cancel</button>
					</td>
				</tr>

			</table>

		</form>

	</div>

	<jsp:directive.include file="footer.jsp" />

</body>

	<script type="text/javascript">
		
		function validateFormInput(){
			var txtEmail = document.getElementById("email");
			var txtFullName = document.getElementById("fullname");
			var txtPassword = document.getElementById("password");
			
			if(txtEmail.value.length == 0){
				alert("Email is required!");
				txtEmail.focus();
				return false;
			}
			
			if(txtFullName.value.length == 0){
				alert("Name is required!");
				txtFullName.focus();
				return false;
			}
			
			if(txtPassword.value.length == 0){
				alert("Password is required!");
				txtPassword.focus();
				return false;
			}
			
			return true;
		}
	</script>
</html>