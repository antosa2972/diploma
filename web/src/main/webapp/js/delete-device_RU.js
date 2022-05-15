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
         $('#success-result').text('Продукт успешно удален из базы данных');

         $tr = $(t).closest("tr");
         $tr.find('td').fadeOut(700, function () {
            $tr.remove();
         });
      },
      error: function (message) {
         $('#error-result').text('Ошибка, код: ' + message.status + ' при удалении из БД');
         $('#result' + phoneId).text('Ошибка при удалении продукта из БД');
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
         $('#success-blocking' + userName).fadeOut(700, function () {
            $(this).text('\u0411\u043b\u043e\u043a\u0438\u0440\u043e\u0432\u043a\u0430\u002f\u0440\u0430\u0437\u0431\u043b\u043e\u043a\u0438\u0440\u043e\u0432\u043a\u0430\u0020\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u0435\u043b\u044f\u0020\u043f\u0440\u043e\u0448\u043b\u0430\u0020\u0443\u0441\u043f\u0435\u0448\u043d\u043e\u0021').fadeIn();
         })
         $('#button' + userName).fadeOut(700, function () {
            $(this).disabled().fadeIn();
         })
      },
      error: function (message) {
         $('#error-result').text('Error ' + message.status + ' while deleting from DB');
         $('#result' + userName).text('Error deleting from db');
      }
   });
}