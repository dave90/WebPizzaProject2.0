var lat;
var longi;
var map;
var marker;
function getLocation()
  {
  if (navigator.geolocation)
    {
    navigator.geolocation.getCurrentPosition(showPosition);
    }
  else{x.innerHTML="Geolocation is not supported by this browser.";}
  }
function showPosition(position)
  {
  lat=position.coords.latitude;
  longi=position.coords.longitude;
  $('#localization').css('height','400px');
  var latlng=new google.maps.LatLng(lat,longi);
  
  var geocoder = new google.maps.Geocoder();
  geocoder.geocode({'latLng': latlng}, function(results, status) {
	    if (status == google.maps.GeocoderStatus.OK) {
	      if (results[1]) {
	    	  var mapOptions = {
	    			    scaleControl: true,
	    			    center: new google.maps.LatLng(lat, longi),
	    			    zoom: 12
	    			  };
	    	  map = new google.maps.Map(document.getElementById('localization'),
	   		      mapOptions);
	    	  marker = new google.maps.Marker({
	    		  position: latlng,
	    		  map: map
	    	  });
	    	$("input[name='Address']").val(results[0].formatted_address);
	      } else {
	        alert('No results found');
	      }
	    } else {
	      alert('Geocoder failed due to: ' + status);
	    }
	  });
  }
function initialize() {
	  var mapOptions = {
	    scaleControl: true,
	    center: new google.maps.LatLng(lat, longi),
	    zoom: 10
	  };

	   map = new google.maps.Map(document.getElementById('localization'),
	      mapOptions);

	  marker = new google.maps.Marker({
	    map: map,
	    position: map.getCenter()
	  });
	  var infowindow = new google.maps.InfoWindow();
	  infowindow.setContent('<b></b>');
	  google.maps.event.addListener(marker, 'click', function() {
	      infowindow.open(map, marker);
	  });
	}



  google.maps.event.addDomListener(window, 'load', initialize);
  $('#startLocalization').click(function(){getLocation();});
