function urlEncode(param, key, encode) {
    if (param == null) return '';
    var paramStr = '';
    var t = typeof (param);
    if (t === 'string' || t === 'number' || t === 'boolean') {
        paramStr += '&' + key + '=' + ((encode == null || encode) ? encodeURIComponent(param) : param);
    } else {
        for (var i in param) {
            var k = key == null ? i : key + (param instanceof Array ? '[' + i + ']' : '.' + i);
            paramStr += urlEncode(param[i], k, encode);
        }
    }
    return paramStr;
}

function postSync(url, sendObj, successCallback, failCallback) {
    $.ajax({
        url: url,
        async: false,//改为同步方式
        type: "POST",
        data: sendObj,
        success: function (data) {
            if (data.code === 200) {
                if (successCallback !== null) {
                    successCallback();
                }
            } else {
                if (failCallback !== null) {
                    failCallback()
                }
            }
        },
        fail: function (data) {
            if (failCallback !== null) {
                failCallback();
            }
        }
    });
}

function postSyncJson(url, sendObj, successCallback, failCallback) {
    $.ajax({
        url: url,
        async: false,//改为同步方式
        type: "POST",
        data: JSON.stringify(sendObj),
        dataType: "json",
        // contentType: 'application/json; charset=utf-8',
        success: function (data) {
            if (data.code === 200) {
                if (successCallback !== null) {
                    successCallback();
                }
            } else {
                if (failCallback !== null) {
                    failCallback()
                }
            }
        },
        fail: function (data) {
            if (failCallback !== null) {
                failCallback();
            }
        }
    });
}