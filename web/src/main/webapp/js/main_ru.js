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
            $('#error-result').text('\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438\u0020\u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d\u0438\u0438\u0020\u0432\u0020\u043a\u043e\u0440\u0437\u0438\u043d\u0443');
            $('#result' + phoneId).text('\u041d\u0435\u043f\u0440\u0430\u0432\u0438\u043b\u044c\u043d\u044b\u0439\u0020\u0432\u0432\u043e\u0434 \u0438\u043b\u0438\u0020\u043d\u0435\u0442\u0020\u0432\u0020\u043d\u0430\u043b\u0438\u0447\u0438\u0438');
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
    let password1 = form.password1.value;
    let password2 = form.password2.value;
    if (password1 == '' || password1.length < 5)
        $('#password1-err').fadeOut(700, function () {
            $(this).text('\u041f\u0430\u0440\u043e\u043b\u044c\u0020\u043d\u0435\u0020\u0434\u043e\u043b\u0436\u0435\u043d\u0020\u0431\u044b\u0442\u044c\u0020\u043f\u0443\u0441\u0442\u0020\u0438\u0020\u0434\u043e\u043b\u0436\u0435\u043d\u0020\u0441\u043e\u0434\u0435\u0440\u0436\u0430\u0442\u044c\u0020\u003e\u0020\u0035\u0020\u0441\u0438\u043c\u0432\u043e\u043b\u043e\u0432').fadeIn();
        })
    else if (password2 == '' || password2.length < 5)
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
            $('#success-result').text('\u041f\u0440\u043e\u0434\u0443\u043a\u0442\u0020\u0443\u0441\u043f\u0435\u0448\u043d\u043e\u0020\u0443\u0434\u0430\u043b\u0435\u043d\u0020\u0438\u0437\u0020\u0431\u0430\u0437\u044b\u0020\u0434\u0430\u043d\u043d\u044b\u0445');

            $tr = $(t).closest("tr");
            $tr.find('td').fadeOut(700, function () {
                $tr.remove();
            });
        },
        error: function (message) {
            $('#error-result').text('\u041e\u0448\u0438\u0431\u043a\u0430\u002c\u0020\u043a\u043e\u0434\u003a ' + message.status + ' \u043f\u0440\u0438\u0020\u0443\u0434\u0430\u043b\u0435\u043d\u0438\u0438\u0020\u0438\u0437\u0020\u0411\u0414');
            $('#result' + phoneId).text('\u041e\u0448\u0438\u0431\u043a\u0430\u0020\u043f\u0440\u0438\u0020\u0443\u0434\u0430\u043b\u0435\u043d\u0438\u0438\u0020\u043f\u0440\u043e\u0434\u0443\u043a\u0442\u0430\u0020\u0438\u0437\u0020\u0411\u0414');
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
            $('#error-result').text('\u0045\u0072\u0072\u006f\u0072 ' + message.status + ' \u0077\u0068\u0069\u006c\u0065\u0020\u0064\u0065\u006c\u0065\u0074\u0069\u006e\u0067\u0020\u0066\u0072\u006f\u006d\u0020\u0044\u0042');
            $('#result' + userName).text('\u0045\u0072\u0072\u006f\u0072\u0020\u0064\u0065\u006c\u0065\u0074\u0069\u006e\u0067\u0020\u0066\u0072\u006f\u006d\u0020\u0064\u0062');
        }
    });
}