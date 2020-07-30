<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>

		<c:if test="${category != null}">
			Edit Category
		</c:if>
			
		<c:if test="${category == null}">
			Create New Category
		</c:if>

	</title>
	<link rel="stylesheet" type="text/css" href="../css/style.css">
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

	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">
			<c:if test="${category != null}">
				Edit Category
			</c:if>
			
			<c:if test="${category == null}">
				Create New Category
			</c:if>
			
		</h2>

	</div>

	<div align="center">
		<c:if test="${category != null}">
			<form action="update_category" method="post" id="category-form">
			<input type="hidden" name="categoryId" value="${category.categoryId}">
		</c:if>
		<c:if test="${category == null}">
			<form action="create_category" method="post" id="category-form">
		</c:if>
			<table>

				<tr>
					<td align = "right">Category Name:</td>
					<td align = "left"><input type="text" id = "name" name="name" size="20" value = "${category.name}"></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td colspan = "2" align = "center">
						<button class="site-button" type="submit">Save</button> 
						<button class="site-button" type ="button" onclick="javascript:history.go(-1)">Cancel</button>
					</td>
				</tr>

			</table>

		</form>

	</div>

	<jsp:directive.include file="footer.jsp" />

</body>

<script type="text/javascript">
	$(document).ready(function() {
		$("#category-form").validate({
			rules : {
				name : {
					required : true
				}
			},
			messages : {
				name : "Please enter category name."
			}
		});
	});
</script>
</html>