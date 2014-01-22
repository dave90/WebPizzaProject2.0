
<%@page import="it.unical.mat.webPizza.domain.PizzeriaInformation"%>
<%@page import="it.unical.mat.webPizza.daoImpl.PizzeriaDAOImpl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	PizzeriaInformation pi = new PizzeriaDAOImpl().getPizzeriaInformation();
	String address = pi.getAddress();
	String phone = pi.getTelephon();
	String mail = pi.getMail();
%>

<!-- Footer starts -->
<footer>
	<div class="container">

		<div class="row">

			<div class="col-md-4 col-sm-4">
				<div class="fwidget">

					<h4>Social</h4>
					<hr />
					<p>Follow us on facebook, twitter, google+, linkedin &
						pinterest</p>

					<div class="social">
						<a href="#" class="facebook"><i class="icon-facebook"></i></a> <a
							href="#" class="twitter"><i class="icon-twitter"></i></a> <a
							href="#" class="google-plus"><i class="icon-google-plus"></i></a>
						<a href="#" class="linkedin"><i class="icon-linkedin"></i></a> <a
							href="#" class="pinterest"><i class="icon-pinterest"></i></a>
					</div>
				</div>
			</div>

			<div class="col-md-4 col-sm-4">
				<div class="fwidget">
					<h4>Feature</h4>
					<hr />
					<ul>
						<li><a>Possibility to build your own pizza</a></li>
						<li><a>Instant location for your pizza</a></li>
						<li><a>Tracking the status of your pizza</a></li>
						<li><a>Possibility to pay online</a></li>
					</ul>
				</div>
			</div>



			<div class="col-md-4 col-sm-4">
				<div class="fwidget">

					<h4>Get In Touch</h4>
					<hr />
					<div class="address">
						<p>
							<i class="icon-home color contact-icon"></i>
							<strong id="addressPizzeria"><%=address%></strong>,
						</p>
						<p>
							<i class="icon-phone color contact-icon"></i>
							<%=phone%></p>
						<p>
							<i class="icon-envelope color contact-icon"></i> <a
								href="mailto:<%=mail%>"><%=mail%></a>
						</p>
					</div>

				</div>
			</div>

		</div>



		<hr />

	</div>
</footer>
<!-- Footer ends -->