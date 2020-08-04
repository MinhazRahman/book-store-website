<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Create New Book</title>
	
	<!-- custom and jQuery CSS -->
	<link rel="stylesheet" type="text/css" href="../css/tableStyle.css">
	<link rel="stylesheet" type="text/css" href="../css/style.css">
	<link rel="stylesheet" type="text/css" href="../css/jquery-ui.min.css">
	
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
			<c:if test="${book != null}">
				Edit Book
			</c:if>
			
			<c:if test="${book == null}">
				Create New Book
			</c:if>
			
		</h2>

	</div>

	<div align="center">
		<c:if test="${book != null}">
			<form action="update_book" method="post" id="book-form">
			<input type="hidden" name="userId" value="${user.userId}">
		</c:if>
		<c:if test="${user == null}">
			<form action="create_book" method="post" id="book-form">
		</c:if>
			<table>
				<tr>
					<td align = "right">Category:</td>
					<td align = "left">
						<select name = "category">
							<c:forEach items="${listCategory}" var = "category">
								<option value = "${category.categoryId}">${category.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>

				<tr>
					<td align = "right">Title:</td>
					<td align = "left"><input type="text" id = "title" name="title" size="20" value = "${book.title}"></td>
				</tr>

				<tr>
					<td align = "right">Author:</td>
					<td align = "left"><input type="text" id = "author" name="author" size="20" value = "${book.author}"></td>
				</tr>
								<tr>
					<td align = "right">ISBN:</td>
					<td align = "left"><input type="text" id = "isbn" name="isbn" size="20" value="${book.isbn}"></td>
				</tr>

				<tr>
					<td align = "right">Publish Date:</td>
					<td align = "left"><input type="text" id = "publishDate" name="publishDate" size="20" value = "${book.publishDate}"></td>
				</tr>

				<tr>
					<td align = "right">Book Image:</td>
					<td align = "left">
						<input type="file" id = "bookImage" name="bookImage" size="20"><br />
						<img id= "thumbnail" alt = "Image Preview"  style = "width:20%; margin-top: 10px;"/>	
					</td>
				</tr>
								<tr>
					<td align = "right">Price:</td>
					<td align = "left"><input type="text" id = "price" name="price" size="20" value="${book.price}"></td>
				</tr>

				<tr>
					<td align = "right">Description:</td>
					<td align = "left">
						<textarea rows = "5" cols = "50" id = "description" name="description"></textarea>
					</td>
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
			/* datepicker */
			$("#publishDate").datepicker({
				showOn : "button",
				buttonImage : "../css/images/calendar.svg",
				buttonImageOnly : true,
				buttonText : "Select date"
			});
			
			/* book thumbnail image */
			$('#bookImage').change(function(){
				showImageThumbnail(this);
			});

			/* form validation */
			$("#book-form").validate({
				rules : {
					category : "required",
					title : "required",
					author : "required",
					isbn : "required",
					publishDate : "required",
					bookImage : "required",
					price : "required",
					description: "required"
				},
				messages : {
					category : "Please select a category",
					title: "Please enter the title of the book",
					author : "Please enter the name of the author",
					isbn : "Please enter the ISBN of the book",
					publishDate: "Please enter the publish date",
					bookImage : "Please choose an image of the book",
					price : "Please enter the price",
					description: "Please enter the description of the book"
				}
			});
			
			/* cancel button */
			$("#buttonCancel").click(function() {
				history.go(-1);
			});
		});
		
		/* function shows thumbnail image */
		function showImageThumbnail(fileInput){
			var file = fileInput.files[0];
			
			var reader = new FileReader();
			
			reader.onload = function(e){
				$('#thumbnail').attr('src', e.target.result);
			};
			
			reader.readAsDataURL(file);
		}
	</script>
</html>