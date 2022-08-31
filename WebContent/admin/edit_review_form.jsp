<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Edit Review</title>
	
	<!-- custom and jQuery CSS -->
	<link rel="stylesheet" type="text/css" href="../css/home-style.css">
	<link rel="stylesheet" type="text/css" href="../css/tableStyle.css">
	<link rel="stylesheet" type="text/css" href="../css/style.css">
	<link rel="stylesheet" type="text/css" href="../css/jquery-ui.min.css">
	
	
	<!--  Font-qwesome for reach text area -->
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<!-- CSS only -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	
	<!-- Font Awesome CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

	
	<!-- JS, Popper.js, and jQuery -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	
	<!-- JS, Popper.js, and jQuery -->
	<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
	
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">Edit Review</h2>

	</div>

	<div align="center">
		<form action="update_review" method="post" id="review-form">
		<input type="hidden" name="reviewId" value="${reviewToBeUpdated.reviewId}">
			<table class= "form">
				<tr>
					<td align = "right">Book:</td>
					<td align = "left"><b>${reviewToBeUpdated.book.title}</b></td>
				</tr>

				<tr>
					<td align = "right">Rating:</td>
					<td align = "left"><b>${reviewToBeUpdated.rating}</b></td>
				</tr>

				<tr>
					<td align = "right">Customer:</td>
					<td align = "left"><b>${reviewToBeUpdated.customer.fullname}</b></td>
				</tr>

				<tr>
					<td align = "right">Headline:</td>
					<td align = "left"><input type="text" id = "headline" name="headline" size="45" value="${reviewToBeUpdated.headline}"></td>
				</tr>
				
				<tr>
					<td align = "right">Comment:</td>
					<td align = "left">
						<textarea rows = "5" cols = "60" name="comment"> ${reviewToBeUpdated.comment} </textarea>
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
			
			/* form validation */
			$("#review-form").validate({
				rules : {
					headline : "required",
					comment : "required"
					
				},
				messages : {
					headline : "Please write headline of your review!",
					comment : "Please write comment!"
					
				}
			});
			
			/* cancel button */
			$("#buttonCancel").click(function() {
				history.go(-1);
			});
		});
		
	</script>
</html>