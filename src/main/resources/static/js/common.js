
//选中表单，添加ajax提交事件,当表单提交时触发ajax请求
function ajaxForm(formId,url,success){
    $.ajax({
        url: url,
        type: "post",
        data:$("#"+formId).serialize(),
        headers:{"token":localStorage.token},
        success: success,
        error: function(xhr){
            alert("请求出错："+xhr.status);
        }
    });
}

//ajax请求-无参
function ajaxRequest(url,type,success){
    $.ajax({
        url: url,
        type: type,
        dataType: "json",
        headers:{"token":localStorage.token},
        success: success,
        error: function(xhr){
            alert("请求出错："+xhr.status);
        },
    });
}

//ajax请求-带参
function ajaxRequestWithParams(url,type,params,success){
    $.ajax({
        url: url,
        type: type,
        data: params,
        dataType: "json",
        headers:{"token":localStorage.token},
        success: success,
        error: function(xhr){
            alert("请求出错："+xhr.status);
        },
    });
}

//jq获取url参数
function getUrlVars() {
    var vars = [],
        hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for (var i = 0; i < hashes.length; i++) {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}