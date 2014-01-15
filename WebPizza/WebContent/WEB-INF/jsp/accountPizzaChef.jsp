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
				<i class="icon-pizza"></i> Manager Pizza Chef Order
			</h2>
			<hr />
		</div>
	</div>
	<!-- Page title -->


		<div class="container">

			<div class="row">
				<div class="col-md-8 ">
				<div>Ordine N.</div>
					<table class="table table-striped table-condensed">

						<thead>
							<tr>
								<th>Name</th>
								<th>Quantity</th>
							</tr>
						</thead>
						<tbody>
							<tr id="tr-103">
								<td>Quattrostagioni</td>
								<td>
									2
								</td>
							</tr>
							<tr>
								<th></th>
								<th><button id="update-102" class="btn btn-info pull-right"
										type="button">Ready</button></th>
							</tr>
						</tbody>

					</table>
					<div class="sep-bor"></div>
				<div>Ordine N.</div>
					<table class="table table-striped">

						<thead>
							<tr>
								<th>Name</th>
								<th>Quantity</th>
							</tr>
						</thead>
						<tbody>
							<tr id="tr-102">
								<td>Calabrese</td>
								<td>
									<div>2</div>
								</td>
							</tr>
							<tr>
								<th></th>
								<th><button id="update-102" class="btn btn-info pull-right"
										type="button">Ready</button></th>
							</tr>
						</tbody>

					</table>
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
