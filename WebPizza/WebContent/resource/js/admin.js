var oldDiscount = 0;
$(document)
		.ready(
				function() {

					$("#ingredients").click(function() {
						chargeIngredients();

					});
					$("#editPizza").click(function() {
						chargePizza();
					});

					$("#inputDiscount").change(function() {
						var total = $("#totalCost").text();
						var discount = $("#inputDiscount").val();
						var newTotal = total - discount + oldDiscount;
						$("#totalCost").text(newTotal);
						oldDiscount = parseInt(discount);
					});

					$("#addAdminPizza")
							.click(
									function() {
										var numButtons = $(".count").attr('id');
										// var str = $(this).attr('id');
										// var res = str.split("-")[1];
										var send = "";
										for (var i = 0; i < numButtons; ++i) {
											if ($('#ownPizzaIngredients' + i)
													.is(":visible"))
												send += $(
														'#ownPizzaIngredients'
																+ i).children()
														.val()
														+ ",";
										}
										var currentVal = parseInt($(
												"#inputDiscount").val());
										var name = $("#NamePizza").val();
										$
												.ajax({
													type : "Post",
													url : "addAdminPizza.html",
													data : "Discount="
															+ currentVal
															+ "&send=" + send
															+ "&Name=" + name,
													success : function(response) {
														if (response != "OK")
															$("#resultAdding")
																	.html(
																			"<strong style='color: red;'>Pizza not added</strong>");
														else
															$("#resultAdding")
																	.html(
																			"<strong style='color: green;'>Pizza added</strong>");
													},
													error : function(e) {
														alert('Error: ' + e);
													}
												});
									});

				});

function chargeIngredients() {
	$.ajax({
		type : "Post",
		url : "adminIgredients.html",
		data : "",
		success : function(response) {
			$("#mainDiv").empty();
			$("#mainDiv").html(response);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function chargePizza() {
	$.ajax({
		type : "Post",
		url : "adminEditPizza.html",
		data : "",
		success : function(response) {
			$("#mainDiv").empty();
			$("#mainDiv").html(response);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function addIngredients() {
	$.ajax({
		type : "Post",
		url : "addIngredients.html",
		data : "Name=" + $("#newName").val() + "&Cost=" + $("#newCost").val(),
		success : function(response) {
			chargeIngredients();
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function sendModifyEditPizza() {
	var toSend = "";
	$('#PizzaTable tr').each(
			function(indexR, value) {
				if (indexR != 0
						&& indexR != $('#PizzaTable tr').length - 1) {
					$(this).find('td').each(function(index, value) {
						if (index == 3) {
							if ($(this).children().is(':checked'))
								toSend += "true;";
							else
								toSend += "false;";
						} else if (index == 0) {
							toSend += $(this).text() + ",";
						}
					});
				}
			});
	$.ajax({
		type : "Post",
		url : "editAdminPizza.html",
		data : "Data=" + toSend,
		success : function(response) {
			chargePizza();
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function sendModify() {
	var toSend = "";
	$('#ingredientsTable tr').each(
			function(indexR, value) {
				if (indexR != 0 && indexR != 1
						&& indexR != $('#ingredientsTable tr').length - 1) {
					$(this).find('td').each(function(index, value) {
						if (index == 3) {
							if ($(this).children().is(':checked'))
								toSend += "true;";
							else
								toSend += "false;";
						} else if (index == 0) {
							toSend += $(this).text() + ",";
						} else {
							toSend += $(this).children().val() + ",";
						}
					});
				}
			});
	$.ajax({
		type : "Post",
		url : "modifyIngredients.html",
		data : "Data=" + toSend,
		success : function(response) {
			chargeIngredients();
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}