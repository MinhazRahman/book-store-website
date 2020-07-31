<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="center">
	<!-- Image and text -->
	<nav id="main-header" class="navbar sticky-top navbar-light">
  		<a class="navbar-brand" href="#">
    	<img src="../images/adminpage/booklogo.svg" width="50" height="50" class="d-inline-block align-top" alt="" loading="lazy">
  		<span class="navbar-text">
    		My Bookstore
  		</span>
  		</a>
  		<form class="form-inline">
        	<div class="input-group">                    
            	<input type="text" class="form-control search-box" placeholder="Search">
              	<div class="input-group-append">
                	<button type="button" class="btn"><span class="fa fa-search"></span></button>
                </div>
             </div>
        </form>
        
         <span class="navbar-text">
         	<span>Welcome, <c:out value="${sessionScope.userEmail}" /></span>
         	<a href="logout">
         		<i class="fa fa-sign-out" aria-hidden="true"></i> Logout
         	</a>
  		</span>
	</nav>
	
	<nav id="nav-submenu" class="nav nav-tabs justify-content-center">
		<a href="#admin" class="nav-item nav-link active">
        	<i class="fa fa-user-plus active-icon"></i><span>Admin</span> 
   	 	</a>
    	<a href="list_users" class="nav-item nav-link">
        	<i class="fa fa-user"></i> Users
   	 	</a>
    	<a href="list_category" class="nav-item nav-link">
        	<i class="fa fa-braille"></i> Categories
    	</a>
    	<a href="books" class="nav-item nav-link">
        	<i class="fa fa-book"></i> Books
    	</a>
    	<a href="customers" class="nav-item nav-link">
        	<i class="fa fa-users"></i> Customers
    	</a>
    	<a href="reviews" class="nav-item nav-link">
        	<i class="fa fa-star"></i> Reviews
    	</a>
    	<a href="orders" class="nav-item nav-link">
        	<i class="fa fa-shopping-cart"></i> Orders
    	</a>
	</nav>

</div>

