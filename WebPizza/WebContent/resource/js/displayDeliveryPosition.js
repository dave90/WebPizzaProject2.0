 
var map;
var marker;
var lat;
var longi;
var mapOptions;


function initialize() 
{     
    var latlng = new google.maps.LatLng(longi, lat);     
    var myOptions = {       zoom: 8,       center: latlng,       mapTypeId: google.maps.MapTypeId.ROADMAP     };     
     map = new google.maps.Map(document.getElementById("map-canvas"),         myOptions);


     marker = new google.maps.Marker({
        position: latlng,
        map: map,
        title: 'Deliveryman'
    });
    var contentString = 'Deliveryman';

    var infowindow = new google.maps.InfoWindow({
        content: contentString,
        maxWidth: 50
    });
    google.maps.event.addListener(marker, 'click', function() {
      infowindow.open(map,marker);
    });

} 


$(document).ready(function() {
	findDeliveryman();
	setInterval(function(){setMarker();}, 10000);
});

function findDeliveryman(){
	  var id=$("#orderId").text();
	    $.ajax({  
		     type : "Post",   
		     url : "getdeliveryLatLong.html",   
		     data : "id="  + id,  
		     success : function(response) {  
		    	 if(response != "no latitude longitude find"){
		    		 
		    		 lat=response.split(";")[0];
		    		 longi=response.split(";")[1];
		    		 
		    		 initialize();

		    		 
		    	 }
		     },  
		     error : function(e) {  
		      alert('Error: ' + e);   
		     }  
		    });  
}

function setMarker(){
	  var id=$("#orderId").text();
	    $.ajax({  
		     type : "Post",   
		     url : "getdeliveryLatLong.html",   
		     data : "id="  + id,  
		     success : function(response) {  
		    	 if(response != "no latitude longitude find"){
		    		 
		    		 lat=response.split(";")[0];
		    		 longi=response.split(";")[1];
		    		 
	    		    var latlng = new google.maps.LatLng(longi, lat);     

		    		 marker.setPosition(latlng);
		    		 map.panTo(latlng);

		    		 
		    	 }
		     },  
		     error : function(e) {  
		      alert('Error: ' + e);   
		     }  
		    });  
}