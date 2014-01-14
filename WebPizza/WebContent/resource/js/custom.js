/* Bootstrap Carousel */

$('.carousel').carousel({
	interval : 8000,
	pause : "hover"
});

/* Ecommerce single item carousel */

$('.ecarousel').carousel({
	interval : 8000,
	pause : "hover"
});

/* Navigation Menu */

ddlevelsmenu.setup("ddtopmenubar", "topbar");

/* Dropdown Select */

/* Navigation (Select box) */

// Create the dropdown base
$("<select />").appendTo(".navis");

// Create default option "Go to..."

$("<option />", {
	"selected" : "selected",
	"value" : "",
	"text" : "Menu"
}).appendTo(".navis select");

// Populate dropdown with menu items

$(".navi a").each(function() {
	var el = $(this);
	$("<option />", {
		"value" : el.attr("href"),
		"text" : el.text()
	}).appendTo(".navis select");
});

$(".navis select").change(function() {
	window.location = $(this).find("option:selected").val();
});

/* Recent post carousel (CarouFredSel) */

/* Carousel */

$('#carousel_container').carouFredSel({
	responsive : true,
	width : '100%',
	direction : 'right',
	scroll : {
		items : 4,
		delay : 2000,
		duration : 500,
		pauseOnHover : "true"
	},
	prev : {
		button : "#car_prev",
		key : "left"
	},
	next : {
		button : "#car_next",
		key : "right"
	},
	items : {
		visible : {
			min : 1,
			max : 4
		}
	}
});

/* Scroll to Top */

$(".totop").hide();

$(function() {
	$(window).scroll(function() {
		if ($(this).scrollTop() > 300) {
			$('.totop').slideDown();
		} else {
			$('.totop').slideUp();
		}
	});

	$('.totop a').click(function(e) {
		e.preventDefault();
		$('body,html').animate({
			scrollTop : 0
		}, 500);
	});

});

/* Support */

$("#slist a").click(function(e) {
	e.preventDefault();
	$(this).next('p').toggle(200);
});

/* Careers */

$('#myTab a').click(function(e) {
	e.preventDefault()
	$(this).tab('show')
})

/* Countdown */

$(function() {
	launchTime = new Date();
	launchTime.setDate(launchTime.getDate() + 365);
	$("#countdown").countdown({
		until : launchTime,
		format : "dHMS"
	});
});

// click per aggiungere al carrello

$(function() {
	var numButtons = $(".count").attr('id');
	for (var i = 0; i < numButtons; ++i) {

		$("#add" + i).click(function() {
			var str = $(this).attr('id');
			var res = str.split("add");
			var currentVal = parseInt($("#qty" + res[1]).val());
			if (!isNaN(currentVal)) {
				$("#qty" + res[1]).val(currentVal + 1);
			}
		});

		$("#minus" + i).click(function() {
			var str = $(this).attr('id');
			var res = str.split("minus");
			var currentVal = parseInt($("#qty" + res[1]).val());
			if (!isNaN(currentVal) && currentVal > 0) {
				$("#qty" + res[1]).val(currentVal - 1);
			}
		});
	}
	
	$("#addBuildPizza").click(function() {
		var currentVal = parseInt($("#qtyBuildPizza").val());
		if (!isNaN(currentVal)) {
			$("#qtyBuildPizza").val(currentVal + 1);
		}
	});

	$("#minusBuildPizza").click(function() {
		var currentVal = parseInt($("#qtyBuildPizza").val());
		if (!isNaN(currentVal) && currentVal > 0) {
			$("#qtyBuildPizza").val(currentVal - 1);
		}
	});

});

//click per aggiungere gli ingredienti alla propria pizza
$(function() {
	
	var numButtons = $(".count").attr('id');
	for (var i = 0; i < numButtons; ++i) {

		$("#pizzaIngredients" + i).click(function(evt) {
			var res = $(this).children().val();
			var tmp2 =$(this).attr("id").split("pizzaIngredients")[1];
				$("#ownPizzaIngredients"+tmp2).show();
				$(this).hide();
				
				 $.ajax({  
				     type : "Post",   
				     url : "getIngredient.html",   
				     data : "idIngridients="  + res,  
				     success : function(response) {
				    	 var tmp =  parseInt($("#totalCost").text());
				    	 tmp += parseInt(response);
				    	 $("#totalCost").empty();
				    	 $("#totalCost").append(tmp);
				     },  
				     error : function(e) {  
				      alert('Error: ' + e);   
				     }  
				    });
				 evt.preventDefault();
		});
		
		$("#pizzaIngredients" + i).mouseover(function() {
			//$(this).append("<span class='glyphicon glyphicon-arrow-right' > </span>");
				
		});
		
		$("#pizzaIngredients" + i).mouseout(function() {
//			var currentVal = $(this).children();
//			$(this).empty();
//			$(this).append(currentVal);
				
		});
		
		$("#ownPizzaIngredients" + i).hide();
		
		$("#ownPizzaIngredients" + i).mouseover(function() {
//			$(this).append("<span class='glyphicon glyphicon-arrow-left' > </span>");
				
		});
		$("#ownPizzaIngredients" + i).mouseout(function() {
//			var currentVal = $(this).text();
//			$(this).empty();
//			$(this).append(currentVal);
		});
		$("#ownPizzaIngredients" + i).click(function(evt) {
			var res = $(this).children().val();
			var tmp2 =$(this).attr("id").split("ownPizzaIngredients")[1];
			$("#pizzaIngredients"+tmp2).show();
			$.ajax({  
				type : "Post",   
				url : "getIngredient.html",   
				data : "idIngridients="  + res,   
				success : function(response) {
					var tmp =  parseInt($("#totalCost").text());
					tmp -= parseInt(response);
					$("#totalCost").empty();
					$("#totalCost").append(tmp);
				},  
				error : function(e) {  
					alert('Error: ' + e);   
				}  
			});
			$(this).hide();
			evt.preventDefault();
		});
	}
	

	

});




$(function() {
	$("#delivery").click(function() {
		if ($(this).hasClass("deliveryActive")) {
			$(this).removeClass("deliveryActive").addClass("delivery");
			$("#inputAddress").empty();
			$("#deliveryType").empty();
			$('#inputLocalization').empty();
		} else{
			$("#inputAddress").empty();
			$("#deliveryType").empty();
			$('#inputLocalization').empty();
			$("#inputAddress").html("<label  class='col-md-2 control-label'>Address</label>" +
					"<div class='col-md-4'>" +
					"<input type='text' class='form-control' rows='1' placeholder='Address' name='Address'/>" +
			"</div>");
			$("#deliveryType").html("<div id='house' class='col-md-4 houseActive background control-label' onClick=''>	</div>"+
									"<div id='apartment' class='col-md-4 apartment background control-label'></div>"+
									"<div id='other' class='col-md-4 other background control-label'></div>");
			$('#inputLocalization').html("<label for='inputAddress' class='col-md-2 control-label'>Localization</label>"+
					"<div class='col-md-4'>"+
					"<input class='btn btn-primary' id='startLocalization' type='button' value='Localize'/>"+
						"<div id='localization' style='height: 0'>"+
						"</div>"+
					"</div> ");
			bindClickDeliveryType();
			$(this).removeClass("delivery").addClass("deliveryActive");
		}
		
		if ($("#carryout").hasClass("carryoutActive")) {
			$("#carryout").removeClass("carryoutActive").addClass("carryout");
		} else
			$("#carryout").removeClass("carryout").addClass("carryoutActive");

	});

	$("#carryout").click(function() {
		if ($(this).hasClass("carryoutActive")) {
			$("#inputAddress").empty();
			$("#deliveryType").empty();
			$('#inputLocalization').empty();
			$("#inputAddress").html("<label  class='col-md-2 control-label'>Address</label>" +
					"<div class='col-md-4'>" +
					"<input type='text' class='form-control' rows='1' placeholder='Address' name='Address' />" +
			"</div>");
			$("#deliveryType").html("<div id='house' class='col-md-4 houseActive background control-label'>	</div>"+
					"<div id='apartment' class='col-md-4 apartment background control-label'></div>"+
					"<div id='other' class='col-md-4 other background control-label'></div>");
			$('#inputLocalization').html("<label for='inputAddress' class='col-md-2 control-label'>Localization</label>"+
					"<div class='col-md-4'>"+
					"<input class='btn btn-primary' id='startLocalization' type='button' value='Localize'/>"+
						"<div id='localization' style='height: 0'>"+
						"</div>"+
					"</div> ");
			bindClickDeliveryType();
			$(this).removeClass("carryoutActive").addClass("carryout");
		} else{
			$(this).removeClass("carryout").addClass("carryoutActive");
			$("#inputAddress").empty();
			$("#deliveryType").empty();
			$('#inputLocalization').empty();
		}
		if ($("#delivery").hasClass("deliveryActive")) {
			$("#delivery").removeClass("deliveryActive").addClass("delivery");
		} else
			$("#delivery").removeClass("delivery").addClass("deliveryActive");

	});
	
	bindClickDeliveryType();
	
//	$("#business").click(function() {
//		if ($(this).hasClass("business")) {
//			$(this).removeClass("business").addClass("businessActive");
//		} 
//		
//		if ($("#house").hasClass("houseActive")) {
//			$("#house").removeClass("houseActive").addClass("house");
//		}
//		if ($("#apartment").hasClass("apartmentActive")) {
//			$("#apartment").removeClass("apartmentActive").addClass("apartment");
//		}
//		if ($("#campus-base").hasClass("campus-baseActive")) {
//			$("#campus-base").removeClass("campus-baseActive").addClass("campus-base");
//		}
//		if ($("#hotel").hasClass("hotelActive")) {
//			$("#hotel").removeClass("hotelActive").addClass("hotel");
//		}
//		if ($("#dormitory").hasClass("dormitoryActive")) {
//			$("#dormitory").removeClass("dormitoryActive").addClass("dormitory");
//		}
//		if ($("#other").hasClass("otherActive")) {
//			$("#other").removeClass("otherActive").addClass("other");
//		}
//		
//	});
//	$("#campus-base").click(function() {
//		if ($(this).hasClass("campus-base")) {
//			$(this).removeClass("campus-base").addClass("campus-baseActive");
//		} 
//		
//		if ($("#house").hasClass("houseActive")) {
//			$("#house").removeClass("houseActive").addClass("house");
//		}
//		if ($("#apartment").hasClass("apartmentActive")) {
//			$("#apartment").removeClass("apartmentActive").addClass("apartment");
//		}
//		if ($("#business").hasClass("businessActive")) {
//			$("#business").removeClass("businessActive").addClass("business");
//		}
//		if ($("#hotel").hasClass("hotelActive")) {
//			$("#hotel").removeClass("hotelActive").addClass("hotel");
//		}
//		if ($("#dormitory").hasClass("dormitoryActive")) {
//			$("#dormitory").removeClass("dormitoryActive").addClass("dormitory");
//		}
//		if ($("#other").hasClass("otherActive")) {
//			$("#other").removeClass("otherActive").addClass("other");
//		}
//		
//	});
});

function bindClickDeliveryType(){
	
	$("#house").click(function() {
		if ($(this).hasClass("house")) {
			$("#inputLevel").empty();
			$(this).removeClass("house").addClass("houseActive");
		}

		if ($("#apartment").hasClass("apartmentActive")) {
			$("#apartment").removeClass("apartmentActive").addClass("apartment");
		}
		if ($("#business").hasClass("businessActive")) {
			$("#business").removeClass("businessActive").addClass("business");
		}
		if ($("#campus-base").hasClass("campus-baseActive")) {
			$("#campus-base").removeClass("campus-baseActive").addClass("campus-base");
		}
		if ($("#hotel").hasClass("hotelActive")) {
			$("#hotel").removeClass("hotelActive").addClass("hotel");
		}
		if ($("#dormitory").hasClass("dormitoryActive")) {
			$("#dormitory").removeClass("dormitoryActive").addClass("dormitory");
		}
		if ($("#other").hasClass("otherActive")) {
			$("#other").removeClass("otherActive").addClass("other");
		}

	});
	
	$("#apartment").click(function() {
		if ($(this).hasClass("apartment")) {
			$("#inputLevel").html("<label  class='col-md-2 control-label'>Floor</label>" +
					"<div class='col-md-4'>" +
					"<input type='text' class='form-control' rows='1' placeholder='Floor' name='Floor' />" +
					"</div>");
			$(this).removeClass("apartment").addClass("apartmentActive");
		} 
		
		if ($("#house").hasClass("houseActive")) {
			$("#house").removeClass("houseActive").addClass("house");
		}
		if ($("#business").hasClass("businessActive")) {
			$("#business").removeClass("businessActive").addClass("business");
		}
		if ($("#campus-base").hasClass("campus-baseActive")) {
			$("#campus-base").removeClass("campus-baseActive").addClass("campus-base");
		}
		if ($("#hotel").hasClass("hotelActive")) {
			$("#hotel").removeClass("hotelActive").addClass("hotel");
		}
		if ($("#dormitory").hasClass("dormitoryActive")) {
			$("#dormitory").removeClass("dormitoryActive").addClass("dormitory");
		}
		if ($("#other").hasClass("otherActive")) {
			$("#other").removeClass("otherActive").addClass("other");
		}
		
		
	});
	$("#other").click(function() {
		if ($(this).hasClass("other")) {
			$("#inputLevel").empty();
			$(this).removeClass("other").addClass("otherActive");
		} 
		
		if ($("#house").hasClass("houseActive")) {
			$("#house").removeClass("houseActive").addClass("house");
		}
		if ($("#business").hasClass("businessActive")) {
			$("#business").removeClass("businessActive").addClass("business");
		}
		if ($("#campus-base").hasClass("campus-baseActive")) {
			$("#campus-base").removeClass("campus-baseActive").addClass("campus-base");
		}
		if ($("#hotel").hasClass("hotelActive")) {
			$("#hotel").removeClass("hotelActive").addClass("hotel");
		}
		if ($("#dormitory").hasClass("dormitoryActive")) {
			$("#dormitory").removeClass("dormitoryActive").addClass("dormitory");
		}
		if ($("#apartment").hasClass("apartmentActive")) {
			$("#apartment").removeClass("apartmentActive").addClass("apartment");
		}
		
	});
	$('#startLocalization').click(function(){getLocation();});
}

$("#count1").hide();
$('body').delegate('.removeItem','click',function(){
	 var id=$(this).attr('id').split("-")[1];
	    $.ajax({  
		     type : "Post",   
		     url : "removeItemCart.html",   
		     data : "idPizza="  + id,
		     success : function(response) {
		    	 	$('#tr-'+id).remove();
		    	 	var tmp = response.split("*");
		    		 $('#cartBody').empty();
		    		 $('#cartBody').append(tmp[0]);
		    		 $('#shoppingCartPrice').empty();
		    		 $('#shoppingCartPrice').append(tmp[1]);
		    		 $('#totalPriceCart2').empty();
		    		 $('#totalPriceCart2').html("&euro;" +tmp[2]);
		     },  	
		     error : function(e) {  
		      alert('Error: ' + e);   
		     }  
		    }); 
		
});

/* Ecommerce sidebar */

$(document).ready(function() {
	$('.sidey .nav').navgoco();
});
