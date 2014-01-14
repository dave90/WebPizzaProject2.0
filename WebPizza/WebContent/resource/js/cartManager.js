 $(document).ready(function() {
	 	var numButtons = $(".count").attr('id');
	 	
		for (var i = 0; i < numButtons; ++i) {

			$("#addCart" + i).click(function(evt) {
				var str = $(this).attr('id');
				var res = str.split("addCart");
				var currentVal = parseInt($("#qty" + res[1]).val());
				var idPizza= $("#idPizza"+res[1]).val();
				if(currentVal!=0){
			    $.ajax({  
				     type : "Post",   
				     url : "addToCart.html",   
				     data : "idPizza="  + idPizza+"&quantity="+currentVal,  
				     success : function(response) {  
				    	 	var tmp = response.split("*");
				    		 $('#cartBody').empty();
				    		 $('#cartBody').append(tmp[0]);
				    		 $('#shoppingCartPrice').empty();
				    		 $('#shoppingCartPrice').append(tmp[1]);
				     },  
				     error : function(e) {  
				      alert('Error: ' + e);   
				     }  
				    }); 
				}
				evt.preventDefault();
			});

		}
	 
		
		$("#addToCartBuild").click(function(evt) {
			var numButtons = $(".count").attr('id');
//			var str = $(this).attr('id');
//			var res = str.split("-")[1];
			var send="";
			for (var i = 0; i < numButtons; ++i){
				if($('#ownPizzaIngredients'+i ).is(":visible"))
				send+= $('#ownPizzaIngredients'+i).children().val()+",";
			}
			var currentVal = parseInt($("#qtyBuildPizza").val());
			if(currentVal!=0){
		    $.ajax({  
			     type : "Post",   
			     url : "addToCartBuild.html",   
			     data : "quantity="+currentVal+"&send="+send,  
			     success : function(response) {  
			    	 	var tmp = response.split("*");
			    		 $('#cartBody').empty();
			    		 $('#cartBody').append(tmp[0]);
			    		 $('#shoppingCartPrice').empty();
			    		 $('#shoppingCartPrice').append(tmp[1]);
			     },  
			     error : function(e) {  
			      alert('Error: ' + e);   
			     }  
			    }); 
			}
			evt.preventDefault();
		});
		
		$("#count1").hide();
		// forse da cambiare
//			var numButtons = 30;
//			for (var i = 0; i < numButtons; ++i) {
//				$("#cartBody").delegate( "#deleteCart" + i, "click", function(evt) {
//					var str = $(this).attr('id');
//					var res = str.split("deleteCart");
//					var currentName = $("#nameCart" + res[1]).text();
//					
//					$.ajax({  
//						type : "Post",   
//						url : "removeFromCartBuild.html",   
//						data : "name="+currentName,  
//						success : function(response) {
//							$('#trCart'+res[1]).remove();
//							$("#totalPriceCart").empty();
//							$("#totalPriceCart").append(response);
//							 $('#shoppingCartPrice').empty();
//				    		 $('#shoppingCartPrice').append("Items - "+response + "&euro;");
//						},  
//						error : function(e) {  
//							alert('Error: ' + e);   
//						}  
//					}); 
//					evt.preventDefault();
//				});
//
//			}
		

			
		
  });
  
 
