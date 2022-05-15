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