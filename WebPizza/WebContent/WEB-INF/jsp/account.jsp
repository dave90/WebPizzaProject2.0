<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
      
      <link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>
      <link href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,600italic,600' rel='stylesheet' type='text/css'>
		
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
            <h2><i class="icon-desktop color"></i> My Account </h2>
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
                         <li><a href="account.html">My Account</a></li>
                         <li><a href="checkout.html">Chechout</a></li>
                         <li><a href="orderhistory.html">Order History</a></li>                         
                         <li><a href="editprofile.html">Edit Profile</a></li>
                     </ul>
                  </div>
               </div>
               <div class="col-md-9">
                  <h3><i class="icon-user color"></i> &nbsp;My Account</h3>
                  <!-- Your details -->
                   <div class="address">
                     
                        <!-- Your name -->
                       <table>
                       <tr>
                       <td style="padding: 10px;">Name</td> <td><strong>${client.name}</strong></td>
                       </tr>
                       <tr>
                       <td style="padding: 10px;">Surname</td> <td><strong>${client.surname}</strong></td>
                                         </tr>

                       <!-- Phone number -->
                       <tr>
                        <td style="padding: 10px;">Phone</td><td>${client.phoneNumber} </td>
                       </tr>
                       <tr>
                       <td style="padding: 10px;">Mail</td> <td><a href="mailto:#">${client.mail}</a></td>
                       </tr>
                   </table>
                     
                   </div>

                   <hr />
                                      <h4>My Recent Purchases</h4>

                  <table class="table table-striped tcart">
                    <thead>
                      <tr>
                        <th>Date</th>
                        <th>ID</th>
                        <th>Number of Pizza</th>
                        <th>Price</th>
                        <th>Status</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${orders}" var="order" >
                      <tr>
                        <td>${order.date}</td>
                        <td>${order.id}</td>
                        <td>${order.numberPizza}</td>
                        <td>&euro; ${order.price}</td>
                        <td>${order.status}</td>
                      </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                   
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
	</body>	
</html>