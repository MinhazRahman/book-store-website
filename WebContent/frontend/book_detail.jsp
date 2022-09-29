<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${book.title}-Buy books online</title>

<!-- Styles -->
<link rel="stylesheet" type="text/css" href="css/home-style.css">
<link rel="stylesheet" type="text/css" href="css/book-detail-style.css">

<!-- CSS only -->
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
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
	crossorigin="anonymous"></script>

<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div class="main-content-book-detail">
		<div class="container">

			<div class="left-book-detail">
				<h2>${book.title}</h2>
				by ${book.author} <br> <br> <img
					src="data:image/jpg;base64,${book.base64Image}" width="240"
					height="300" />
			</div>

			<div class="center-book-detail">
				<h4>
					<jsp:directive.include file="book_rating.jsp" />
					<a href="#reviews"> ${fn:length(book.reviews)} Reviews</a>
				</h4>
				<br> ${book.description}
			</div>

			<div class="right-book-detail">
				$${book.price} <br>
				<button type="submit" class="site-button">Add to Cart</button>
			</div>

			<div class="clear-book-detail"></div>

			<div class="bottom-book-detail">
				<h2>
					<a id="reviews">Customer reviews</a>
				</h2>
				<button type="submit" id="btnWriteReview" class="site-button">Write a Review</button>

			</div>

			<div class="book-review">
				<table border="0">
					<c:forEach items="${book.reviews}" var="review">
						<tr>
							<td><c:forTokens items="${review.stars}" delims=","
									var="star">
									<c:if test="${star eq 'on'}">
										<img src="images/rating-stars/rating_on.png" />
									</c:if>

									<c:if test="${star eq 'off'}">
										<img src="images/rating-stars/rating_off.png" />
									</c:if>
								</c:forTokens> &nbsp;<b>${review.headline}</b></td>
						</tr>
						<tr>
							<td>by ${review.customer.fullname} on ${review.reviewTime}</td>
						</tr>
						<tr>
							<td><i>${review.comment}</i></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>

					</c:forEach>
				</table>

			</div>

		</div>

	</div>


	<jsp:directive.include file="footer.jsp" />
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#btnWriteReview").click(function() {
				window.location = 'write_review?bookId=' + ${book.bookId};
			});
		});
	
	</script>


</body>
</html>