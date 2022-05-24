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
         alert('Please authorize to do this');
      }
      if (xhr.status === 403) { // 403 - Forbidden
         window.location = '/phoneshop-web/admin/main';
         alert('Admin cant do this');
      }
   });
}

function checkPassword(form) {
   $('#password1-err').fadeOut(700, function () {
      $(this).text('').fadeIn();
   })
   $('#password2-err').fadeOut(700, function () {
      $(this).text('').fadeIn();
   })
   $('#password-mismatch').fadeOut(700, function () {
      $(this).text('').fadeIn();
   })
   password1 = form.password1.value;
   password2 = form.password2.value;
   if (password1 == '' || password1.length < 5)
      $('#password1-err').fadeOut(700, function () {
         $(this).text('Password cant be empty and it should be >5 symbols').fadeIn();
      })
   else if (password2 == '' || password2.length < 5)
      $('#password2-err').fadeOut(700, function () {
         $(this).text('Password cant be empty and it should be >5 symbols').fadeIn();
      })
   else if (password1 != password2) {
      $('#password-mismatch').fadeOut(700, function () {
         $(this).text('Password mismatch').fadeIn();
      })
      return false;
   } else {
      $('#password1-err').text('');
      $('#password2-err').text("");
      $('#password-mismatch').text("");
      return true;
   }
}
function deleteDevice(phoneId, t) {
   var id = $("#phoneId" + phoneId).val();
   $.ajax({
      type: 'POST',
      url: '/phoneshop-web/admin/device-operations/update-delete/delete',
      data: JSON.stringify(id),
      contentType: 'application/json; charset=utf-8',
      dataType: 'json',
      success: function (dataReceived) {
         $('#error-result').text('');
         $('#success-result').text('Product deleted from database successfully');

         $tr = $(t).closest("tr");
         $tr.find('td').fadeOut(700, function () {
            $tr.remove();
         });
      },
      error: function (message) {
         $('#error-result').text('Error ' + message.status + ' while deleting from DB');
         $('#result' + phoneId).text('Error deleting from db');
      }
   });
}

function updateStatus(userName, status, t) {
   var userDto = {}
   userDto["userName"] = userName;
   userDto["status"] = status;
   $.ajax({
      type: 'POST',
      url: '/phoneshop-web/admin/user-management/update-status',
      data: JSON.stringify(userDto),
      contentType: 'application/json; charset=utf-8',
      dataType: 'json',
      success: function (dataReceived) {
         $('#error-result').text('');
         $('#success-result').text('Product deleted from database successfully');
         $('#success-blocking'+userName).fadeOut(700, function () {
            $(this).text('Successfully updated user restriction!').fadeIn();
         })
         $('#button'+userName).fadeOut(700, function () {
            $(this).disabled().fadeIn();
         })
      },
      error: function (message) {
         $('#error-result').text('Error ' + message.status + ' while deleting from DB');
         $('#result' + userName).text('Error deleting from db');
      }
   });
}