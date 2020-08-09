<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div align="center">
	<div>
			<!-- Image and text -->
	<nav id="main-header" class="navbar navbar-light">
  		<a class="navbar-brand" href="#">
    	<img src="images/homepage/booklogo.svg" width="50" height="50" class="d-inline-block align-top" alt="" loading="lazy">
  		<span class="navbar-text">
    		My Bookstore
  		</span>
  		</a>
  		<form class="form-inline">
        	<div class="input-group">                    
            	<input type="text" name="keyword" class="form-control search-box" placeholder="Search">
              	<div class="input-group-append">
                	<button type="button" class="btn"><span class="fa fa-search"></span></button>
                </div>
             </div>
        </form>
        
         <span class="navbar-text">
         	<a href="login">
         		<i class="fa fa-sign-in" aria-hidden="true"></i> Sign In
         	</a>
         	<a href="register">
         		<i class="fa fa-registered" aria-hidden="true"></i> Register
         	</a>
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