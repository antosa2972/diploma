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
            $('#cart-quantity').text(dataReceived['totalQuantity'] + ',');
            $('#cart-totalCost').text(dataReceived['totalCost']);
            $('#result' + phoneId).text('');
            $('#error-result').text('');
            $('#ajax-errors').text('');
            $('#success-result').text('\u041f\u0440\u043e\u0434\u0443\u043a\u0442\u0020\u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d\u0020\u0432\u0020\u043a\u043e\u0440\u0437\u0438\u043d\u0443');
        },
        error: function (message) {
            $('#success-result').text('');
            $('#error-result').text('\u041e\u0448\u0438\u0431\u043a\u0430 ' + message.status + ' \u043f\u0440\u0438\u0020\u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d\u0438\u0438\u0020\u0432\u0020\u043a\u043e\u0440\u0437\u0438\u043d\u0443');
            $('#ajax-errors').text(message.responseText);
            $('#result' + phoneId).text('\u041d\u0435\u043f\u0440\u0430\u0432\u0438\u043b\u044c\u043d\u044b\u0439\u0020\u0432\u0432\u043e\u0434');
        }
    });
}