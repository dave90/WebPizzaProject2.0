  $(document).ready(function() {
	  $("#inputUser").change(function(){
		  var usr=$("#inputUser").val();
		  doAjaxPostExistUsername(usr);
	  });
  });
  
  function doAjaxPostExistUsername(usr) {  
	    $.ajax({  
	     type : "Post",   
	     url : "existClientUser.html",   
	     data : "user="  + usr,  
	     success : function(response) {  
	    	 if(response == "OK"){
	    		 $('#existUser').css('color','green');
	    		 $('#existUser').html("&#10004");
	    	 }else{
	    		 $('#existUser').css("color","red"); 
	    		 $('#existUser').text(response);
	    	 }
	     },  
	     error : function(e) {  
	      alert('Error: ' + e);   
	     }  
	    });  
}  