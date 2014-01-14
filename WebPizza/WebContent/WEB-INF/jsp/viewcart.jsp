<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<!-- Title here -->
		<title>View Cart</title>
		<!-- Description, Keywords and Author -->
		<meta name="description" content="Your description">
		<meta name="keywords" content="Your,Keywords">
		<meta name="author" content="ResponsiveWebInc">
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
      
      <link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>
      <link href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,600italic,600' rel='stylesheet' type='text/css'>
		
		<!-- Styles -->
		<!-- Bootstrap CSS -->
		<link href="resource/css/bootstrap.min.css" rel="stylesheet">
      <!-- Animate css -->
      <link href="resource/css/animate.min.css" rel="stylesheet">
      <!-- Dropdown menu -->
      <link href="resource/css/ddlevelsmenu-base.css" rel="stylesheet">
      <link href="resource/css/ddlevelsmenu-topbar.css" rel="stylesheet">
      <!-- Countdown -->
      <link href="resource/css/jquery.countdown.css" rel="stylesheet">     
		<!-- Font awesome CSS -->
		<link href="resource/css/font-awesome.min.css" rel="stylesheet">		
		<!-- Custom CSS -->
		<link href="resource/css/style.css" rel="stylesheet">
		
		<!-- Favicon -->
		<link rel="shortcut icon" href="#">
	</head>
	
	<body>

     <!-- Shopping cart Modal -->
 		<jsp:include page="include/shoppingCart.jsp" />
 	  <!-- Shopping cart Modal -->	
           
      <!-- Logo & Navigation starts -->            
 		<jsp:include page="include/header.jsp" />
 	  <!-- Logo & Navigation end -->
 
     
      <!-- Page title -->
      <div class="page-title">
         <div class="container">
            <h2><i class="icon-desktop color"></i> View Cart <small>Subtext for header</small></h2>
            <hr />
         </div>
      </div>
      <!-- Page title -->
      
      <!-- Page content -->
      
      <div class="view-cart blocky">
         <div class="container">
            <div class="row">
               <div class="col-md-12">
               
                  <!-- Table -->
                    <table class="table table-striped">
                      <thead>
                        <tr>
                		  <th>Name</th>
                		  <th>Quantity</th>
                		  <th>Price</th>
                        </tr>
                      </thead>
                      <tbody>
              <c:if test="${cart != null }">
              <c:forEach var="pizza" items="${cart.pizzaQuantity}" >
 
                        <tr id="tr-${pizza.key.id}">
                          <!-- Index -->
                          <!-- Product  name -->
                          <td>${pizza.key.name}</td>
                          <!-- Product image -->
                          <td><img src="resource/img/pizza/${pizza.key.name}.png" alt="" class="img-responsive"/></td>
                          <!-- Quantity with refresh and remove button -->
                          <td>
                            <div class="input-group">
                              <input type="text" class="form-control" placeholder="1" id="quantity-${pizza.key.id}" value="${pizza.value}">
                              <span class="input-group-btn">
                                <button class="btn btn-info updateItem" type="button" id="update-${pizza.key.id}"><i class="icon-refresh" ></i></button>
                                <button class="btn btn-danger removeItem" type="button" id="remove-${pizza.key.id}"><i class="icon-remove"></i></button>
                              </span>
                            </div>
                          </td>
                          <td id="price-${pizza.key.id}"> &euro; ${pizza.key.prize * pizza.value}</td>
                        </tr>
			              </c:forEach>
			              <tr>
			              <th></th>
			              <th></th>
			              <th>Total</th>
			              <th id="totalPriceCart">&euro; ${cart.totalprice}</th>
			              </tr>
			              </c:if>
 
                      </tbody>
                    </table>                    

                     
                     <div class="sep-bor"></div>
                     <!-- Button s-->
                    <div class="row">
                      <div class="span4 offset8">
                        <div class="pull-right">
                          <a href="index.html" class="btn btn-info">Continue Shopping</a>
                          <a href="checkout.html" class="btn btn-danger">CheckOut</a>
                        </div>
                      </div>
                    </div>

               
               </div>
            </div>
         </div>
      </div>
     
      
      
      <!-- Scroll to top -->
      <span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span> 
      
		<!-- Javascript files -->
		<!-- jQuery -->
		<script src="resource/js/jquery.js"></script>
		<!-- Bootstrap JS -->
		<script src="resource/js/bootstrap.min.js"></script>
		<!-- Dropdown menu -->
		<script src="resource/js/ddlevelsmenu.js"></script>      
      <!-- CaroFredSel -->
      <script src="resource/js/jquery.carouFredSel-6.2.1-packed.js"></script> 
      <!-- Countdown -->
      <script src="resource/js/jquery.countdown.min.js"></script>    
      <!-- jQuery Navco -->
      <script src="resource/js/jquery.navgoco.min.js"></script>
      <!-- Filter for support page -->
      <script src="resource/js/filter.js"></script>         
		<!-- Respond JS for IE8 -->
		<script src="resource/js/respond.min.js"></script>
		<!-- HTML5 Support for IE -->
		<script src="resource/js/html5shiv.js"></script>
		<!-- Custom JS -->
		<script src="resource/js/custom.js"></script>
		<script src="resource/js/cartUpdater.js" type="text/javascript"></script>
		<script src="resource/js/cartManager.js" type="text/javascript"></script>
	</body>	
</html>
