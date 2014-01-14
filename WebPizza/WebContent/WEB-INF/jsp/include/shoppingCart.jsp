 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <c:set var="count" value="0" scope="page" />
    <!-- Shopping cart Modal -->
     <div class="modal fade" id="shoppingcart" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
       <div class="modal-dialog">
         <div class="modal-content">
           <div class="modal-header">
             <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <h4 class="modal-title">Shopping Cart</h4>
           </div>
           <div class="modal-body">
           
            <!-- Items table -->
            <table class="table table-striped">
              <!-- PUT CART OBJECT -->
               <thead>
                <tr>
                  <th>Name</th>
                  <th>Quantity</th>
                  <th>Price</th>
                  <th></th>
                </tr>
              </thead>
              <tbody id="cartBody">
             <c:if test="${cart != null }">
              <c:forEach var="pizza" items="${cart.pizzaQuantity}" >
              <tr id="trCart${count}">
              	<td id="nameCart${count}">${pizza.key.name}</td>
              	<td id="quantityCart${count}">${pizza.value}</td>
              	<td id="priceCart${count}"><c:out value="${pizza.key.prize * pizza.value}" /></td>
              	<td><img id="deleteCart-${pizza.key.id}" src="resource/img/x.png" width="20" height="20" class="removeItem"></td>
              </tr>
              	<c:set var="count" value="${count + 1}" scope="page" />
              </c:forEach>
              <tr>
              <th></th>
              <th>Total</th>
              <th id="totalPriceCart">${cart.totalprice}</th>
              <th></th>	
              </tr>
              </c:if>
              </tbody>
            </table>
            <div id="count1" >${count}</div>
           </div>
           <div class="modal-footer">
             <button type="button" class="btn btn-default" data-dismiss="modal">Continue Shopping</button>
             <a href="checkout.html"><button type="button" class="btn btn-info">Checkout</button></a>
           </div>
         </div><!-- /.modal-content -->
       </div><!-- /.modal-dialog -->
     </div><!-- /.modal -->
     
    <!-- End Shopping cart Modal -->