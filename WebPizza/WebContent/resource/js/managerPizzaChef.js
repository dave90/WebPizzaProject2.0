 $(document).ready(function() {
	 
	 $('.readyButton').click(function(){
		 var id=$(this).attr('id').split("-")[1];
		    $.ajax({  
			     type : "Post",   
			     url : "updateReadyOrder.html",   
			     data : "idOrder="  + id,  
			     success : function(response) {
			    	 	if(response == "OK"){
			    	 		$("#div-"+id).remove();
//			    	 		$("#bodyTableReadyOrder").append("<tr ><td>"+id+"</td></tr>");
//			    	 		quando ritotrna in questa pagina la tebella è vuota come comportarsi?
			    	 	}
			     },  	
			     error : function(e) {  
			      alert('Error: ' + e);   
			     }  
			    }); 
			
	 });
	 
	 setInterval(function() {
		 $("#table-Order").load();
		 }, 1000);
});