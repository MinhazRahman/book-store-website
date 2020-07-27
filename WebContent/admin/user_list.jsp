<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Manage Users - My Bookstore Administration</title>
	
	 <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css?family=Muli:300,400,500,600,700,800,900&display=swap" rel="stylesheet">
	
	<!-- Styles -->
	<link rel="stylesheet" type="text/css" href="../css/style.css">
	<!-- Table styles -->
	<link rel="stylesheet" type="text/css" href="../css/tableStyle.css">
</head>
<body>
	
	<jsp:directive.include file = "header.jsp"/>
	
	<div align = "center">
		<h2 class="pageheading">Users Management</h2>
		<h3><a href="user_form.jsp">Create New User</a></h3>
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
				<th>Id</th>
				<th>Email</th>
				<th>Full Name</th>
				<th>Actions</th>
			</tr>
			<c:forEach var ="user" items= "${listUsers}" varStatus = "status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${user.userId}</td>
					<td>${user.email}</td>
					<td>${user.fullName}</td>
					<td>
						<a href ="edit_user?id=${user.userId}">Edit</a> &nbsp;
						<a href ="javascript:confirmDelete(${user.userId})">Delete</a> &nbsp;
					</td>
				</tr>
			
			</c:forEach>
		
		</table>
	</div>
	
	<jsp:directive.include file = "footer.jsp"/>

</body>

	<script>
		// on confirmation send the request to the DeleteUserServlet
		function confirmDelete(userId){
			if(confirm("Are you sure, you want to delete your user with id " + userId + "?" )){
				window.location = "delete_user?userId=" + userId;
			}
		}
	
	</script>
</html>