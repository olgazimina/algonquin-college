function Ajax(script, data) {
    var o_ajax = new Object();
    var xhr = new XMLHttpRequest();

    this.send_request = function sendXHRequest(__callback) {
        if (typeof data != "object") {
            tmp = data.split("&");
            post_data = "";
        } else {
            tmp = data;
        }

        for (var i = 0; i < tmp.length; i++) post_data += tmp[i] + "&";

        xhr.upload.addEventListener('loadstart', onloadstartHandler, false);
        xhr.upload.addEventListener('progress', onprogressHandler, false);
        xhr.upload.addEventListener('load', onloadHandler, false);
        xhr.addEventListener('readystatechange',function(){onreadystatechangeHandler(__callback)}, false);

        xhr.open('POST', script, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        xhr.send(post_data);
    }

    function onloadstartHandler(evt) {};
    function onloadHandler(evt) {};
    function onprogressHandler(evt) {};
    function onreadystatechangeHandler(__callback) {
        if (xhr.readyState == 4 && xhr.status == 200 && xhr.responseText) __callback(xhr.responseText);
    }
}