function addToCart(phoneId) {
   var phoneDto = {}
   phoneDto["id"] = $("#phoneId" + phoneId).val();
   phoneDto["quantity"] = $("#quantity" + phoneId).val();
   $.ajax({
      type: 'POST',
      url: '/phoneshop-web/ajax-cart',
      data: JSON.stringify(phoneDto),
      contentType: 'application/json; charset=utf-8',
      dataType: 'json',
      success: function (dataReceived) {
         $('#cart-quantity').fadeOut(700, function () {
            $(this).text(dataReceived['totalQuantity'] + ',').fadeIn();
         })
         $('#cart-totalCost').fadeOut(700, function () {
            $(this).text(dataReceived['totalCost'] + ',').fadeIn();
         })
         $('#result' + phoneId).text('');
         $('#error-result').text('');
         $('#ajax-errors').text('');
         $('#success-result').fadeOut(700, function () {
            $(this).text('Product added to cart successfully').fadeIn();
         })
      },
      error: function (message) {
         $('#success-result').text('');
         $('#error-result').text('Error ' + message.status + ' while adding to cart');
         $('#result' + phoneId).text('Wrong input');
      }
   });
   $(document).ajaxError(function (event, xhr, settings, error) {
      //when there is an AJAX request and the user is not authenticated -> redirect to the login page
      if (xhr.status === 200) { // 403 - Forbidden
         window.location = '/phoneshop-web/login';
         alert('Admin cant do this');
      }
      if (xhr.status === 403) { // 403 - Forbidden
         window.location = '/phoneshop-web/admin/main';
      }
   });
}