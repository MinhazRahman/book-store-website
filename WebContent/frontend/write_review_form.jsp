<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Write a Review - The Online Bookstore</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css?family=Muli:300,400,500,600,700,800,900&display=swap"
	rel="stylesheet">

<!-- Custom Styles -->
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/tableStyle.css">
<link rel="stylesheet" type="text/css" href="css/home-style.css">

<!-- Bootstrap CSS only -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

<!-- Font Awesome CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">

<!-- JS, Popper.js, and jQuery -->
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
	crossorigin="anonymous"></script>

<!-- rateYo CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
<!-- rateYo JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>

</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">

		<form id="formWriteReview" action="submit_review" method="post">
			<table width="60%">
				<tr>
					<td><h2>Your Reviews</h2></td>
					<td>&nbsp;</td>
					<td><h2>${loggedInCustomer.fullname}</h2></td>
				</tr>
				<tr>
					<td colspan="3"><hr /></td>

				</tr>
				<tr>
					<td><span><b>${book.title}</b></span><br /> <img
						src="data:image/jpg;base64,${book.base64Image}" width="240"
						height="300" /></td>
					<td>
							<!-- rateyo HTML -->
						<div id="rateYo"></div>
						<input type="hidden" id="rating" name="rating"/>
						<input type="hidden" name="bookId" value="${book.bookId}"/>
						<br/>
						<input type="text" name="headline" size="50" placeholder="Headline or summary for the review" /> <br /> <br />
					
						<textarea rows="6" cols="50" name="comment" placeholder="Review details goes here..."></textarea>
					</td>
				</tr>
				<tr>
					<td colspan = "3" align="center">
						<button type="submit" class="site-button">Submit</button>
						&nbsp;&nbsp;
						<button type="submit" class="site-button" id="buttonCancel">Cancel</button>
					</td>
					
				</tr>
			</table>

		</form>
	</div>

	<jsp:directive.include file="footer.jsp" />

	<script type="text/javascript">
		$(document).ready(function() {
			
			/* form validation */
			$("#formWriteReview").validate({
				rules : {
					
					headline : "required",
					comment : "required"	
					
				},
				messages : {
					
					headline : "Please write headline or summary of the review",
					comment : "Please write review details"	
				}
			});

			/* rate Yo */
			$("#rateYo").rateYo({
				starWidth : "40px",
				fullStar : true,
				onSet : function(rating, rateYoInstance) {

					$("#rating").val(rating);
				}
			});
			
			/* cancel button */
			$("#buttonCancel").click(function() {
				history.go(-1);
			});
		});
	</script>

</body>
</html>