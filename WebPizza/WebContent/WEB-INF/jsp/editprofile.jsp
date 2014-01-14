<!DOCTYPE html>
<html>
	<head>
		<!-- Title here -->
		<title>Edit Profile </title>
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
            <h2><i class="icon-desktop color"></i> My Account <small>Subtext for header</small></h2>
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
                  <h3><i class="icon-user color"></i> &nbsp;Edit Profile</h3>
                  <!-- Your details -->
                  <form class="form-horizontal" role="form" action="editProfile.html" method="post">
                    <div class="form-group">
                      <label for="inputName" class="col-md-2 control-label">Name</label>
                      <div class="col-md-4">
                        <input type="text" class="form-control" id="inputName" name="Name" placeholder="Name" value="${client.name}">
                      </div>
                    </div>       
                    <div class="form-group">
                      <label for="inputSurname" class="col-md-2 control-label">Surname</label>
                      <div class="col-md-4">
                        <input type="text" class="form-control" id="inputSurname" name="Surname" placeholder="Surname" value="${client.surname}">
                      </div>
                    </div>       
                    <div class="form-group">
                      <label for="inputEmail1" class="col-md-2 control-label">Email</label>
                      <div class="col-md-4">
                        <input type="email" class="form-control" id="inputEmail1" name="Mail" placeholder="Email" value="${client.mail}">
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="inputPhone" class="col-md-2 control-label">Phone Number</label>
                      <div class="col-md-4">
                        <input type="text" class="form-control" id="inputPhone" name="Phone" placeholder="Phone" value="${client.phoneNumber}">
                      </div>
                    </div>
                   <div class="form-group">
                      <label for="inputUsername" class="col-md-2 control-label">Username</label>
                      <div class="col-md-4">
                        <input type="text" class="form-control" id="inputUsername" name="User" placeholder="Username" value="${client.username}">
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="inputPassword" class="col-md-2 control-label">Password</label>
                      <div class="col-md-4">
                        <input type="password" class="form-control" id="inputPassword" name="Password" placeholder="Password">
                        <p id="notifyRegistration" style="color: red;"><strong>${notifyRegistration}</strong></p>
                        
                      </div>
                    </div>                    
                    <hr />
                    <div class="form-group">
                      <div class="col-md-offset-2 col-md-10">
                        <button type="submit" class="btn btn-danger">Save Changes</button>
                        <button type="reset" class="btn btn-default">Reset</button>
                      </div>
                    </div>
                  </form> 
                   
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