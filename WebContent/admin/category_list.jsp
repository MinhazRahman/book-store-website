<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Categories - My Bookstore Administration</title>
</head>
<body>
	
	<jsp:directive.include file = "header.jsp"/>
	
	<div align = "center">
		<h2>Category Management</h2>
		<h3><a href="category_form.jsp">Create New Category</a></h3>
	</div>
	
	<c:if test="${message != null}">
		<div align = "center">
			<h3><i>${message}</i></h3>
		</div>
	</c:if>

	
	<div align = "center">
		<table border = "1" cellpadding = "5">
			<tr> 
				<th>Index</th>
				<th>Id</th>
				<th>Category Name</th>
				<th>Actions</th>
			</tr>
			<c:forEach var ="category" items= "${listCategory}" varStatus = "status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${category.categoryId}</td>
					<td>${category.name}</td>
					<td>
						<a href ="edit_category?id=${category.categoryId}">Edit</a> &nbsp;
						<a href ="javascript:confirmDelete(${category.categoryId})">Delete</a> &nbsp;
					</td>
				</tr>
			
			</c:forEach>
		
		</table>
	</div>
	
	<jsp:directive.include file = "footer.jsp"/>

</body>

	<script>
		// on confirmation send the request to the DeleteCategoryServlet class
		function confirmDelete(categoryId){
			if(confirm("Are you sure, you want to delete your category with id " + categoryId + "?" )){
				window.location = "delete_category?categoryId=" + categoryId;
			}
		}
	
	</script>
</html>