function showModel(_url, _title, _width, _height) {
    $.showModel({ url: _url, title: _title, width: _width, height: _height });
}

function showNorepeat(tipsText) {
    $.showNorepeat({ image_src: "/images/loading.gif", tips: tipsText });
}

function hideNorepeat() {
    $.hideNorepeat();
}

//得到URL参数的值 item是需要获得的参数 如“id”
function QueryString(pItem) {
    var _sValue = location.search.match(new RegExp("[?&]" + pItem + "=([^&]*)(&?)", "i"));
    var _res = _sValue ? _sValue[1] : _sValue;
    return decodeURI(_res);
}

/*Cookies操作*/
function SetCookie(pName, pValue) { //增、改
    var _exp = new Date();
    _exp.setTime(_exp.getTime() + 24 * 60 * 60 * 1000);
    document.cookie = pName + "=" + escape(pValue) + ";expires=" + _exp.toUTCString() + ";path=/";
}

function GetCookie(pName) { //读
    var _arr, _reg = new RegExp("(^| )" + pName + "=([^;]*)(;|$)");
    if (document.cookie.match(_reg)) {
        _arr = document.cookie.match(_reg);
        return unescape(_arr[2]);
    }
    else return null;
}

function DelCookie(pName) { //删
    var _exp = new Date();
    _exp.setTime(_exp.getTime() - 1);
    var _cval = GetCookie(pName);
    if (_cval != null) document.cookie = pName + "=" + _cval + ";expires=" + _exp.toUTCString() + ";path=/";
}

function DelCookies() { //删本站点所有
    var _cookarr = document.cookie.toString().split(";");
    var _exp = new Date()
    _exp.setTime(_exp.getTime() - 1);
    for (var i = 0; i < _cookarr.length; i++) {
        var _cookarrsub = _cookarr[i].split("=");
        document.cookie = _cookarrsub[0] + "=" + _cookarrsub[1] + ";expires=" + _exp.toUTCString() + ";path=/";
    }
}