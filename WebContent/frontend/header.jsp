<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div align="center">
	<div>
		<!-- Image and text -->
		<nav class="navbar sticky-top navbar-light bg-light">
  			<a class="navbar-brand" href="#">
    			<img src="images/homepage/booklogo.svg" width="50" height="50" class="d-inline-block align-top" alt="" loading="lazy">
  				<span class="navbar-text">
    				My Bookstore
  				</span>
  			</a>
  		
  			<form class="form-inline">
    			<div class="input-group mb-3">
  					<input type="text" class="form-control" placeholder="Search">
  					<div class="input-group-append">
    					<button class="btn btn-success" type="submit">Go</button>
  					</div>
				</div>
  			</form>
		</nav> <!-- navbar ends here -->
	</div>
	<div>
		<input type="text" name="keyword" size="50" /> 
		<input type="button" value="search" /> 
		
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="login">Sign In</a> |
		<a href="register">Register</a> |
		<a href="view_cart">Cart</a>
	</div>
	
	<div>&nbsp;</div>
	
	<div>
		<c:forEach var="category" items="${listCategory}" varStatus="status" >
			<a href = "view_category?id=${category.categoryId}">
				<font size="+1">
					<b>
						<c:out value = "${category.name}" />
					
					</b>
				</font>
			 </a> 
			 <c:if test="${not status.last}">
			 	&nbsp; | &nbsp;
			 </c:if>
		</c:forEach>
	
	</div>

</div>