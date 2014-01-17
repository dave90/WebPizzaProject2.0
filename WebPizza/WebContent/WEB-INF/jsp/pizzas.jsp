<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="count" value="0" scope="page" />
<!DOCTYPE html>
<html>
<head>
<!-- Title here -->
<title>Items - Olson Kart</title>
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
				<i class="icon-pizza"></i> Pizza List
			</h2>
			<hr />
		</div>
	</div>
	<!-- Page title -->

	<!-- Page content -->

	<div class="shop-items">
		<div class="container">

			<div class="row" >

				<div class="col-md-9 col-md-push-3">



					<!-- Items List starts -->

					<div class="row">
						<!-- Item #1 -->
						<c:forEach items="${listPizza}" var="pizza">
							<div class="col-md-5 col-sm-8 col-xs-11">


								<div class="item">
									<!-- Use the below link to put HOT icon -->
									<c:if test="${pizza.discount > 0} ">
										<div class="item-icon">
											<span>HOT</span>
										</div>
									</c:if>
									<!-- Item image -->
									<div class="item-image">
										<a href="single-item.html"><img
											 src="
											 		<c:choose>
											 		<c:when test="${clientPizzaIMG != 1}">resource/img/pizza/${pizza.name}.png</c:when>
											 		<c:otherwise>resource/img/pizza/pizza-cartoon.png</c:otherwise>
											 		</c:choose> 		
											 " 
											 alt=""
											class="img-responsive" /></a>
									</div>
									<!-- Item details -->
									<div class="item-details">
										<!-- Name -->
										<h5>
											<a href="single-item.html"><c:out value="${pizza.name}" />
											<input hidden="true" id="idPizza${count}" value="${pizza.id}" />
											</a>
										</h5>
										<div class="clearfix"></div>
										<p style="font-size: 80%">
											<c:forEach items="${pizza.ingredients}" var="ingredient">
												<!-- Para. Note more than 2 lines. -->
												<c:out value="${ingredient.name}" />
											</c:forEach>
										</p>
										<hr />
										<!-- Price -->
										<div class="item-price pull-left">${pizza.prize}&euro;</div>
										<!-- Add to cart -->
										<button class="incrdecr" id="add${count}" >+</button>
										<input type="text" id="qty${count}" maxlength="2" size="1" value="0" style="width:auto">
										<button class="incrdecr" id="minus${count}">-</button>
										<div class="pull-right">
											<a href="#" class="btn btn-danger btn-sm" id="addCart${count}">Add to Cart</a>
										</div>
										<div class="clearfix"></div>
									</div>
								</div>
							</div>
							<c:set var="count" value="${count + 1}" scope="page"/>
						</c:forEach>
						<div id="${count}" class="count"></div>


					</div>




					<!-- Items List ends -->

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

			<div class="sep-bor"></div>
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
	
	<script src="resource/js/cartManager.js"></script>
</body>
</html>
