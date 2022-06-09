<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Results for ${keyword} - Buy books online</title>
	<!-- Styles -->
	<link rel="stylesheet" type="text/css" href="css/home-style.css">
	
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

	<jsp:directive.include  file = "header.jsp"/>

	<div class="main-content">
		<c:if test="${fn:length(result) == 0}">
			<h2>No results for "${keyword}"</h2>
		</c:if>
		
		<c:if test="${fn:length(result) > 0}">
			<div align="center" style="width: 60%; margin: 0 auto;">
				<h2>Search Results for "${keyword}"</h2>
				<c:forEach var="book" items="${result}">
					<div style="display: inline-block; margin: 10px;">
						<div>
							<a href="view_book?id=${book.bookId}"> <img
								src="data:image/jpg;base64,${book.base64Image}" width="80"
								height="100" />
							</a>
						</div>
						<div>
							<a href="view_book?id=${book.bookId}"><b>${book.title}</b></a>
						</div>
						<div>Rating *****</div>
						<div>
							<i>by ${book.author}</i>
						</div>
						<div>
							<b>$${book.price}</b>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
	</div>
	
	<div class="footer-section">
	
	</div>
	
	<jsp:directive.include  file = "footer.jsp"/>

	
</body>
</html>