<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<!-- Title here -->
<title>Checkout</title>
<!-- Description, Keywords and Author -->
<meta name="description" content="Your description">
<meta name="keywords" content="Your,Keywords">
<meta name="author" content="ResponsiveWebInc">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link
	href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,600italic,600'
	rel='stylesheet' type='text/css'>

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
 		

	<!-- Page content -->

	<div class="checkout">
		<div class="container">
			<h2 class="text-center">Shipping & Billing Details</h2>
			<h4><span style="color: red;">${errorMessage}</span></h4>
			<br />
					<h4>Is
						your order for delivery or carryout?</h4>
					
			<form class="form-horizontal" role="form" action="checkOut.html" method="post">
				<div class="form-group">
					<div id="delivery" class="col-md-4 deliveryActive control-label">
					</div>
					<div id="carryout" class="col-md-4 carryout control-label"></div>

				</div>
				
				
				<div class="form-group" id="deliveryType">
					<div id="house" class="col-md-4 houseActive background control-label">
					</div>
	 				<div id="apartment" class="col-md-4 apartment background control-label"></div>
<!-- 				<div id="business" class="col-md-4 business background control-label"></div>
					<div id="campus-base" class="col-md-4 campus-base background  control-label"></div>
					<div id="hotel" class="col-md-4 hotel background control-label"></div>
					<div id="dormitory" class="col-md-4 dormitory background control-label"></div>
 -->					<div id="other" class="col-md-4 other background control-label"></div>

				</div>
				
				
				<div class="form-group">
					<label for="inputName" class="col-md-2 control-label">Name</label>
					<div class="col-md-4">
						<input readonly="readonly" type="text" class="form-control" id="inputName"
							placeholder="Name" name="Name" value="${client.name}">
					</div>
				</div>
				<div class="form-group">
					<label for="inputSurname" class="col-md-2 control-label">Surname</label>
					<div class="col-md-4">
						<input type="text" readonly="readonly" class="form-control" id="inputSurname"
							placeholder="Surname" name="Surname" value="${client.surname}">
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail1" class="col-md-2 control-label">Email</label>
					<div class="col-md-4">
						<input type="email" class="form-control" id="inputEmail1"
							placeholder="Email" name="Mail" value="${client.mail}">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPhone" class="col-md-2 control-label">Phone
						Number</label>
					<div class="col-md-4">
						<input type="text" class="form-control" id="inputPhone"
							placeholder="Phone" name="Phone" value="${client.phoneNumber}">
					</div>
				</div>
				


				<div class="form-group" id="inputAddress">
					<label for="inputAddress" class="col-md-2 control-label">Address</label>
					<div class="col-md-4">
						<input type='text' class="form-control" rows="3" name="Address" placeholder="Address"/>
					</div> 
				</div>
				<div id="inputLevel" class="form-group">
					
				</div>
				
				<div class="form-group" id="inputLocalization">
					<label for="inputAddress" class="col-md-2 control-label">Localization</label>
					<div class="col-md-4">
					<input class="btn btn-primary" id="startLocalization" type="button" value="Localize"/>
						<div id="localization" style="height: 0">
						</div>
					</div> 
				</div>


				<hr />
				<h4>Payment Details</h4>

				<div class="form-group">
					<label for="inputPayment" class="col-md-2 control-label">Payment
						Method</label>
					<div class="col-md-4">
						<select class="form-control" name="PaymentMethod">
							<option>Payment Method</option>
							<option>Credit Card</option>
							<option>Cash on Delivery</option>
						</select>
					</div>
				</div>
				
			<!-- Items table -->
			<hr />
			<h4>Your Pizza</h4>
            <table class="table table-striped">
              <!-- PUT CART OBJECT -->
               <thead>
                <tr>
                  <th>Name</th>
                  <th>Quantity</th>
                  <th>Price</th>
                </tr>
              </thead>
              <tbody id="cartBody">
             <c:if test="${cart != null }">
              <c:forEach var="pizza" items="${cart.pizzaQuantity}" >
              <tr>
              	<td>${pizza.key.name}</td>
              	<td>${pizza.value}</td>
              	<td><c:out value="${pizza.key.prize * pizza.value}" /></td>
              </tr>
              </c:forEach>
              <tr>
              <th></th>
              <th>Total</th>
              <th>${cart.totalprice}</th>
              </tr>
              </c:if>
              </tbody>
            </table>

				<div class="form-group">
					<div class="col-md-offset-2 col-md-4">
						<div class="checkbox">
							<label> <input type="checkbox" name="Accept"> Accept Terms &
								Conditions
							</label>
						</div>
					</div>
				</div>
				<hr />
				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<button type="submit" class="btn btn-danger">Confirm
							Order</button>
						<button type="reset" class="btn btn-default">Reset</button>
					</div>
				</div>
			</form>

		</div>
	</div>




	<!-- Footer starts -->
		<jsp:include page="include/footer.jsp" />
      <!-- Footer ends -->

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
	<!-- Google maps -->
	<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&language=it"></script>
	<script src="resource/js/localization.js"></script>
	

</body>
</html>