<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div align="center">
	<div>
			<!-- Image and text -->
	<nav id="main-header" class="navbar navbar-light">
  		<a class="navbar-brand" href="${pageContext.request.contextPath}/">
    	<img src="images/homepage/booklogo.svg" width="50" height="50" class="d-inline-block align-top" alt="" loading="lazy">
  		<span class="navbar-text">
    		The Online Bookstore
  		</span>
  		</a>
  		<form action="search" method="get" class="form-inline">
        	<div class="input-group">                    
            	<input type="text" name="keyword" class="form-control search-box" placeholder="Search">
              	<div class="input-group-append">
                	<button type="submit" class="btn"><span class="fa fa-search"></span></button>
                </div>
             </div>
        </form>
        
         <span class="navbar-text">
         	<c:if test="${loggedInCustomer == null}">
	         	<a href="login">
	         		<i class="fa fa-sign-in" aria-hidden="true"></i> Sign In
	         	</a>
	         	<a href="register">
	         		<i class="fa fa-user-plus" aria-hidden="true"></i> Register
	         	</a>
         	</c:if>
         	
         	<c:if test="${loggedInCustomer != null}">
	         	<a href="view_profile"> Welcome, ${loggedInCustomer.fullname}</a>
	         	<a href="view_orders">
	         		<i class="fa fa-history" aria-hidden="true"></i> My Orders
	         	</a>
	         	
	         	<a href="logout" class="logout-link">
	         		
         			<i class="fa fa-sign-out" aria-hidden="true"></i> <span>Logout</span>
         		</a>
         	</c:if>

         	<a href="view_cart">
         		<i class="fa fa-shopping-cart" aria-hidden="true"></i> Cart
         	</a>
  		</span>
	</nav>
	</div>
	
	<div>
		<nav id="home-nav-submenu" class="nav nav-tabs justify-content-center">
			<a href="." class="nav-item nav-link active">  <!-- href="." will invoke the HomeServlet -->
				<i class="fa fa-home" aria-hidden="true"></i><span>Home</span>
			</a>
			<c:forEach var="category" items="${listCategory}" varStatus="status">
				<a href="view_category?id=${category.categoryId}" class="nav-item nav-link home-nav-item"> 
					<span><c:out value="${category.name}" /></span>
				</a>
			</c:forEach>
		</nav>

	</div>

</div>