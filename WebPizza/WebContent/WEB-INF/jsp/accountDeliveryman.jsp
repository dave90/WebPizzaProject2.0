<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<!-- Title here -->
<title>My Account</title>
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
<link href="resource/account.jspcss/animate.min.css" rel="stylesheet">
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

	<!-- Logo & Navigation starts -->
	<jsp:include page="include/header.jsp" />
	<!-- Logo & Navigation ends -->

	<!-- Page title -->
	<div class="page-title">
		<div class="container">
			<h2>
				<i class="icon-desktop color"></i> My Account
			</h2>
			<hr />
		</div>
	</div>
	<!-- Page title -->

	<!-- Page content -->

	<div class="account-content">
		<div class="container">

			<div class="row">
				<div class="col-md-3">
					<div class="sidey">
						<ul class="nav">
							<li><a href="accountDeliveryman.html">My Account</a></li>
							<li><a href="deliverymanTour.html" id="ingredients">Starts</a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-9" id="mainDiv">
					<h3>
						<i class="icon-user color"></i> &nbsp;My Account
					</h3>
					<!-- Your details -->
					<div class="address">

						<!-- Your name -->
						<table>
							<tr>
								<td style="padding: 10px;">Name</td>
								<td><strong>${deliveryman.name}</strong></td>
							</tr>
							<tr>
								<td style="padding: 10px;">Surname</td>
								<td><strong>${deliveryman.surname}</strong></td>
							</tr>

						</table>
						<hr />
						<hr />

						<div id="map-canvas"
							style="background-color: black; height: 400px"></div>
						<hr />

						<c:forEach items="${orders}" var="order">
							<table class="table table-striped tcart">
								<h4>Order ${order.id}:</h4>
								<thead>
									<tr>
										<th>Date</th>
										<th>Delivery Status</th>
										<th>Address</th>
										<th>Paid</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${order.date}</td>
										<td>${order.deliveryStatus}</td>
										<td class="orderAddress">${order.address}</td>
										<td>${order.paid}</td>
									</tr>
								</tbody>
							</table>
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Name</th>
										<th>Quantity</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="pizza" items="${order.pizzas}">
										<tr>
											<td>${pizza.pizza.name}</td>
											<td>${pizza.quantity}</td>
										</tr>
									</c:forEach>
									<tr>
										<th></th>
										<th>Total</th>
										<th>&euro; ${order.prize}</th>
									</tr>
								</tbody>
							</table>
							<hr />
						</c:forEach>




					</div>

					<hr />


				</div>
			</div>

			<div class="sep-bor"></div>
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
	<script
		src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>


	<!-- TSP Display  -->
	<script>
		var directionsDisplay;
		var directionsService = new google.maps.DirectionsService();
		var map;

		function initialize() {

			directionsDisplay = new google.maps.DirectionsRenderer();
			var chicago = new google.maps.LatLng(41.850033, -87.6500523);
			var mapOptions = {
				zoom : 6,
				center : chicago
			}
			map = new google.maps.Map(document.getElementById('map-canvas'),
					mapOptions);

			var waypts = [];
			var address =[];
			$(".orderAddress").each(function(index, element) {
			      if(index!=0 && index !=$(".orderAddress").size()-1)
				  waypts.push({
			          location:$(this).text(),
			          stopover:true});
			      address.push($(this).text());

			});
			var start=$(".orderAddress").first().text().replace(/ /g,'+');
			var end=$(".orderAddress").last().text().replace(/ /g,'+');
			var request = {
				      origin: start,
				      destination: end,
				      waypoints: waypts,
				      optimizeWaypoints: false,
				      travelMode: google.maps.TravelMode.DRIVING
			};
			directionsService.route(request, function(response, status) {
			    if (status == google.maps.DirectionsStatus.OK) {
			      directionsDisplay.setDirections(response);
			    }
			  });


			directionsDisplay.setMap(map);
		}
		
		if($(".orderAddress").size()>0)
			google.maps.event.addDomListener(window, 'load', initialize);
	</script>

</body>
</html>
