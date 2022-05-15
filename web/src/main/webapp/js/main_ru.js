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
                $(this).text('\u041f\u0440\u043e\u0434\u0443\u043a\u0442\u0020\u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d\u0020\u0432\u0020\u043a\u043e\u0440\u0437\u0438\u043d\u0443').fadeIn();
            })
        },
        error: function (message) {
            $('#success-result').text('');
            $('#error-result').text('\u041e\u0448\u0438\u0431\u043a\u0430 ' + message.status + ' \u043f\u0440\u0438\u0020\u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d\u0438\u0438\u0020\u0432\u0020\u043a\u043e\u0440\u0437\u0438\u043d\u0443');
            $('#ajax-errors').text(message.responseText);
            $('#result' + phoneId).text('\u041d\u0435\u043f\u0440\u0430\u0432\u0438\u043b\u044c\u043d\u044b\u0439\u0020\u0432\u0432\u043e\u0434');
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
    if (password1 == '' && password1.length < 5)
        $('#password1-err').fadeOut(700, function () {
            $(this).text('\u041f\u0430\u0440\u043e\u043b\u044c\u0020\u043d\u0435\u0020\u0434\u043e\u043b\u0436\u0435\u043d\u0020\u0431\u044b\u0442\u044c\u0020\u043f\u0443\u0441\u0442\u0020\u0438\u0020\u0434\u043e\u043b\u0436\u0435\u043d\u0020\u0441\u043e\u0434\u0435\u0440\u0436\u0430\u0442\u044c\u0020\u003e\u0020\u0035\u0020\u0441\u0438\u043c\u0432\u043e\u043b\u043e\u0432').fadeIn();
        })
    else if (password2 == '' && password2.length < 5)
        $('#password2-err').fadeOut(700, function () {
            $(this).text('\u041f\u0430\u0440\u043e\u043b\u044c\u0020\u043d\u0435\u0020\u0434\u043e\u043b\u0436\u0435\u043d\u0020\u0431\u044b\u0442\u044c\u0020\u043f\u0443\u0441\u0442\u0020\u0438\u0020\u0434\u043e\u043b\u0436\u0435\u043d\u0020\u0441\u043e\u0434\u0435\u0440\u0436\u0430\u0442\u044c\u0020\u003e\u0020\u0035\u0020\u0441\u0438\u043c\u0432\u043e\u043b\u043e\u0432').fadeIn();
        })
    else if (password1 != password2) {
        $('#password-mismatch').fadeOut(700, function () {
            $(this).text('\u041d\u0435\u0441\u043e\u0432\u043f\u0430\u0434\u0435\u043d\u0438\u0435\u0020\u043f\u0430\u0440\u043e\u043b\u0435\u0439').fadeIn();
        })
        return false;
    } else {
        $('#password1-err').text('');
        $('#password2-err').text("");
        $('#password-mismatch').text("");
        return true;
    }
}