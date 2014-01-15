var directionsDisplay;
var directionsService = new google.maps.DirectionsService();
var map;
var latLang;
var sendLat;
var sendLongi;
var start;

function getLocation(sendPosition) {
	if (navigator.geolocation) {
		if(!sendPosition)
			navigator.geolocation.getCurrentPosition(setLatLang);
		else
			navigator.geolocation.getCurrentPosition(getLatLang);
	} else {
		x.innerHTML = "Geolocation is not supported by this browser.";
	}
}

function setLatLang(position) {
	lat = position.coords.latitude;
	longi = position.coords.longitude;
	latLang = new google.maps.LatLng(lat, longi);
	var geocoder = new google.maps.Geocoder();
	geocoder.geocode({
		'latLng' : latLang
	}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			if (results[0]) {
				start=results[0].formatted_address;
				calcRoute();
			} else {
				alert('No results found');
			}
		} else {
			alert('Geocoder failed due to: ' + status);
		}
	});
}

function getLatLang(position) {
	sendLat = position.coords.latitude;
	sendLongi = position.coords.longitude;
    $.ajax({  
	     type : "Post",   
	     url : "deliveryLatLong.html",   
	     data : "lat="  + sendLat+"&long="+sendLongi,  
	     success : function(response) {  
	     },  
	     error : function(e) {  
	      alert('Error: ' + e);   
	     }  
	    });  
}


function initialize() {
	directionsDisplay = new google.maps.DirectionsRenderer();
	var chicago = new google.maps.LatLng(0,0);
	var mapOptions = {
		zoom : 7,
		center : chicago
	}
	map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
	directionsDisplay.setMap(map);
	getLocation(false);
}
function calcRoute() {
	var end = document.getElementById('end').value;
	var request = {
		origin : start,
		destination : end,
		travelMode : google.maps.TravelMode.DRIVING
	};
	directionsService.route(request, function(response, status) {
		if (status == google.maps.DirectionsStatus.OK) {
			directionsDisplay.setDirections(response);
		}
	});
}
$(document).ready(function() {
	google.maps.event.addDomListener(window, 'load', initialize);
	
	$(".recalculateRoute").click(function(){
		var id=$(this).attr("id").split("-")[1];
	    $.ajax({  
		     type : "Post",   
		     url : "deliveryOrder.html",   
		     data : "id="  + id,  
		     success : function(response) {  
		    	 $("#orderId").text("Order "+id+":");
		    	 var splittedResult=response.split("*");
		    	 $("#end").val(splittedResult[0]);
		    	 $("#orderDetail").empty();
		    	 $("#orderDetail").append(splittedResult[1]);
		    	 $("#orderPizza").empty();
		    	 $("#orderPizza").append(splittedResult[2]);
		    	 getLocation(false);
		     },  
		     error : function(e) {  
		      alert('Error: ' + e);   
		     }  
		    });  
	});
	
	$(".modifyOrderStatus").click(function(){
		var status=$(this).attr("id").split("-")[1];
		var id= $("#orderId").text().split("Order ")[1].split(":")[0];
	    $.ajax({  
		     type : "Post",   
		     url : "deliveryModifyStatusOrder.html",   
		     data : "id="  + id+"&status="+status,  
		     success : function(response) {  
		    	 $("#delStatus").empty();
		    	 $("#delStatus").append(response);
		     },  
		     error : function(e) {  
		      alert('Error: ' + e);   
		     }  
		    });  
		
	});
	
	setInterval(function(){ 
		getLocation(true);
	 }, 2000);
});