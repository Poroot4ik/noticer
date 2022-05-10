/*function showAlert() {
    alert("The button was clicked2!");
}*/

function getXmlHttp() {
    var xmlHttp = null;
    if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
    else if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlHttp;
}

function httpReq(URL, method, data, success, error) {
    var request = getXmlHttp();
    request.open(method, URL, true);
    request.setRequestHeader("Content-type", "multipart/form-data");
    request.send(data);
    request.onreadystatechange = function() {
        if (request.readyState == 4) {
            if (request.status == 200) {
                success(request.responseText);
            }
            else {
                if (error) error(request.status);
            }
        }
    }
}


function showAlert2() {
/*    var table = document.querySelector("селектор таблицы")
    var field = table.querySelector(".active");
    var item = field.children[0];  // первый дочерний*/

    var form = new FormData();
    form.append("some_key",
            item.innerHTML);  // ну или что там нужно вытащить
    form.append("another_key", another_value);

    httpReq("/delete", "POST", form, function(res) {
        console.log("response:", res);
    }, function(err) {
        console.error(err);
    })
};

/*
someBtn.onclick = function(e) {
    var table = document.querySelector("селектор таблицы")
    var field = table.querySelector(".active");
    var item = field.children[0];  // первый дочерний

    var form = new FormData();
    form.append("some_key",
            item.innerHTML);  // ну или что там нужно вытащить
    form.append("another_key", another_value);

    httpReq("url...", "POST", form, function(res) {
        console.log("response:", res);
    }, function(err) {
        console.error(err);
    })
};*/
