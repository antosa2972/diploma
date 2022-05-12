function deleteDevice(phoneId,t) {
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

         $tr= $(t).closest("tr");
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