<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="count" value="0" scope="page" />
<!DOCTYPE html>
<html>
<head>
<!-- Title here -->
<title>Web Pizza</title>
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

	<!-- Page title -->
	<div class="page-title">
		<div class="container">
			<h2>
				<i class="icon-pizza"></i> Build your own Pizza
			</h2>
			<hr />
		</div>
	</div>
	<!-- Page title -->


	<div class="shop-items">
		<div class="container">

			<div class="row">
				<div class="col-md-9 col-md-push-1">
					<div class="row">
						<div class="col-md-8 col-md-push-3">Click on the Ingridients
							"Our Ingridients" to add to your pizza</div>
					</div>
					<div class="row">
						<div class="col-md-8 col-md-push-3">Click on the Ingridients
							"Your Pizza" to delete</div>
					</div>
					<hr />
					<div class="row">
						<div class="col-md-4 col-md-push-3 ">
							Our Ingridients
							<div id="pizzaIngredientsDiv" class="list-group ">
								<c:forEach items="${listPizzaIngredients}"
									var="pizzaIngredients">
									<a id="pizzaIngredients${count}" href="#"
										class="list-group-item"><input type="hidden" value="${pizzaIngredients.id}"> ${pizzaIngredients.name} </a>
									<c:set var="count" value="${count + 1}" scope="page" />
								</c:forEach>
							</div>
						</div>
						<c:set var="count" value="0" scope="page" />
						<div class="col-md-4 col-md-push-4">
							Your Pizza
							<div id="ownPizzaIngredientsDiv"
								class="list-group table-bordered">
								<c:forEach items="${listPizzaIngredients}"
									var="pizzaIngredients">
									<a id="ownPizzaIngredients${count}" href="#"
										class="list-group-item"><input type="hidden" value="${pizzaIngredients.id}"> ${pizzaIngredients.name} </a>
									<c:set var="count" value="${count + 1}" scope="page" />
								</c:forEach>
							</div>
						</div>
					</div>
					<div id="${count}" class="count"></div>
					<div class="row">
						<div class="col-md-3 col-md-push-3 ">
							<h1>Quantity</h1>
						</div>
						<div class="col-md-2 col-md-push-6 ">
							<button class="incrdecr" id="addBuildPizza">+</button>
							<input type="text" id="qtyBuildPizza" maxlength="2" size="1"
								value="1">
							<button class="incrdecr" id="minusBuildPizza">-</button>
						</div>
					</div>
					<hr/>
					<div class="row">
						<div class="col-md-3 col-md-push-3 ">
							<h1>Total</h1>
						</div>
						<div class="col-md-1 col-md-push-2 ">
							<h1 id="totalCost">0</h1>
						</div>
						<div class="col-md-1 col-md-push-2">
							<h1>&euro;</h1>
						</div>
					</div>
					<div class="row">
						<div class="col-md-9 col-md-push-2">
							<div class="pull-right">
								<a href="#" class="btn btn-danger btn-sm" id="addToCartBuild">Add
									to Cart</a>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-3 col-md-pull-9">
					<div class="sidey">
						<ul class="nav">
							<li><a href="index.html"><i class="icon-home"></i>
									&nbsp;Home</a>
							<li><a href="pizzaList.html"><i class="icon-mobile-phone"></i>
									&nbsp;Our menu</a></li>
							<li><a href="buildPizza.html"><i class="icon-lightbulb"></i>
									&nbsp;Build your pizza</a></li>
							<li><a href="pizzaClientList.html"><i class="icon-briefcase"></i>
									&nbsp;Created by you</a></li>
						</ul>
					</div>

				</div>


			</div>
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

	<script src="resource/js/cartManager.js"></script>


</body>
</html>
