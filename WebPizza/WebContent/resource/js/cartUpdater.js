 $(document).ready(function() {
	 
	 $('.updateItem').click(function(){
		 var id=$(this).attr('id').split("-")[1];
		 var currentVal=$("#quantity-"+id).val();
		    $.ajax({  
			     type : "Post",   
			     url : "updateCart.html",   
			     data : "idPizza="  + id+"&quantity="+currentVal,  
			     success : function(response) {
			    	 	
			    	 	var tmp = response.split("*");
			    	 	$("#price-"+id).empty();
			    	 	$("#price-"+id).html("&euro;"+tmp[0]);
			    		 $('#cartBody').empty();
			    		 $('#cartBody').append(tmp[1]);
			    		 $('#shoppingCartPrice').empty();
			    		 $('#shoppingCartPrice').append(tmp[2]);
			    		 $('#totalPriceCart2').empty();
			    		 $('#totalPriceCart2').html("&euro;" +tmp[3]);
			     },  	
			     error : function(e) {  
			      alert('Error: ' + e);   
			     }  
			    }); 
			
	 });
	 
	 
});
  
