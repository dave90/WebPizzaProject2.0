<!DOCTYPE html>
<html>
	<head>
		<!-- Title here -->
		<title>Contact Us</title>
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
      
      <div class="contactus ">
            <!-- Google maps -->
            <div class="gmap">
               <!-- Google Maps. Replace the below iframe with your Google Maps embed code -->
           	   <iframe width="425" height="350" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.it/maps?f=q&amp;source=s_q&amp;hl=it&amp;geocode=&amp;q=${pizzeria.addressNoSpace}&amp;aq=&amp;sll=39.029842,16.418107&amp;sspn=2.470636,5.410767&amp;ie=UTF8&amp;hq=${pizzeria.addressNoSpace}&amp;hnear=&amp;radius=15000&amp;t=m&amp;ll=39.358143,16.225803&amp;spn=0.071946,0.071946&amp;output=embed"></iframe><br />
            </div>
            
            <div class="container">
                        <div class="row">
                           <div class="col-md-6 col-sm-7">
                              <div class="cwell">
                                 <!-- Contact form -->
                                    <h5>Contact Form</h5>
                                    
                                    <form role="form">
                                      <div class="form-group">
                                        <label for="name">Your Name</label>
                                        <input type="text" class="form-control" id="name" placeholder="Enter Name">
                                      </div>                                    
                                      <div class="form-group">
                                        <label for="exampleInputEmail1">Email address</label>
                                        <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">
                                      </div>
                                      <div class="form-group">
                                        <label for="exampleInputEmail1">Content</label>
                                        <textarea class="form-control" rows="3"></textarea>
                                      </div>                                      
                                      <div class="checkbox">
                                        <label>
                                          <input type="checkbox"> Important?
                                        </label>
                                      </div>
                                      <button type="submit" class="btn btn-info">Send</button>
                                      <button type="reset" class="btn btn-default">Reset</button>
                                    </form>
                                    
                                 </div>
                           </div>
                           <div class="col-md-6 col-sm-5">
                                 <div class="cwell">
                                    <!-- Address section -->
                                       <h5>Address</h5>
                                       <div class="address">
                                           <address>
                                              <!-- Company name -->
                                              <h6>${pizzeria.name }</h6>
                                              <!-- Address -->
                                              ${pizzeria.address}<br>
                                              <!-- Phone number -->
                                              <abbr title="Phone">Phone Number : </abbr> ${pizzeria.telephon} 
                                           </address>
                                            
                                           <address>
                                              <!-- Email -->
                                              <a href="mailto:${pizzeria.telephon} ">${pizzeria.mail} </a>
                                           </address>
                                           
                                       </div>
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
	</body>	
</html>