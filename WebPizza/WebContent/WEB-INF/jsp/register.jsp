<!DOCTYPE html>
<html>
	<head>
		<!-- Title here -->
		<title>Register</title>
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
 
      
      <!-- Page content -->
      
      <div class="blocky">
         <div class="container">
            <div class="row">
               <div class="col-md-6">
                  <div class="reg-login-info">
                     <h2>Login to Access Amazing Benefits <span class="color">!!!</span></h2>
                     <img src="resource/img/clients/woman-pizza2.jpg" alt="" class="img-responsive img-thumbnail" />
                     <p></p>
                  </div>
               </div>
               <div class="col-md-6">
                  <div class="register-login">
                     <div class="cool-block">
                        <div class="cool-block-bor">
                        
                           <h3>Register</h3>
                           <form class="form-horizontal" role="form" action="registration.html" method="post">
                             <div class="form-group">
                               <label for="inputName" class="col-lg-2 control-label">Name</label>
                               <div class="col-lg-10">
                                 <input type="text" class="form-control" id="inputName" name="Name" placeholder="Name">
                               </div>
                             </div>
                             <div class="form-group">
                               <label for="inputSurname" class="col-lg-2 control-label">Surname</label>
                               <div class="col-lg-10">
                                 <input type="text" class="form-control" id="inputSurname" name="Surname" placeholder="Surname">
                               </div>
                             </div>                            
                             <div class="form-group">
                               <label for="inputEmail1" class="col-lg-2 control-label">Email</label>
                               <div class="col-lg-10">
                                 <input type="email" class="form-control" id="inputEmail1" name="Mail" placeholder="Email">
                               </div>
                             </div>
                             <div class="form-group">
                               <label for="inputPhone" class="col-lg-2 control-label">Phone</label>
                               <div class="col-lg-10">
                                 <input type="text" class="form-control" id="inputPhone" name="Phone" placeholder="Phone Number">
                               </div>
                             </div>
                             <div class="form-group">
                               <label for="inputUser" class="col-lg-2 control-label">Username</label>
                               <div class="col-lg-10">
                                 <input type="text" class="form-control" id="inputUser" name="User" placeholder="Username"><span id="existUser"></span>
                               </div>
                             </div>
                             <div class="form-group">
                               <label for="inputPassword1" class="col-lg-2 control-label">Password</label>
                               <div class="col-lg-10">
                                 <input type="password" class="form-control" id="inputPassword1" name="Password" placeholder="Password">
                                 <p id="notifyRegistration" style="color: red;"><strong>${notifyRegistration}</strong></p>
                               </div>
                             </div>                                

                             <div class="form-group">
                               <div class="col-lg-offset-2 col-lg-10">
                                 <button type="submit" class="btn btn-info">Register</button>
                                 <button type="reset" class="btn btn-default">Reset</button>
                               </div>
                             </div>
                           </form>
                           
                        </div>
                     </div>   
                  </div>
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
		
	  <!-- Javascript for ajax username -->	
		 <script type="text/javascript" src="resource/js/registration.js"></script>
		
	</body>	
</html>