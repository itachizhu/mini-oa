var BOX_BORDER_WIDTH = 10;
var BOX_BORDER_HEIGHT = 38;

var MSG_TYPE = {
    "SUCCESS": 1,
    "ERROR": -1,
    "DEFAULT": 0,
    "YESNO": 2
};

(function ($) {
    var ajax = $.ajax;
    $.ajax = function (s) {
        var error = s.error;
        s.error = function (xhr, status, err) {
            var errMsg = xhr.responseText;
            if (errMsg)
                err = errMsg;
            if (error)
                error(xhr, status, err);
            else {
                if (typeof (errMsg) == "string" && errMsg != "undefined" && errMsg != "null") {
                    alert(errMsg);
                }
                try {
                    hideNorepeat();
                }
                    catch (err) {
                }
                try {
                    top.closeBox();
                }
                    catch (e) {
                }
            }
        }
        ajax(s);
    }
})(jQuery);

$.ajaxSetup({ cache: false });

function showBox(msg, msgType, onUnblockFunc) {
    jQuery.prompt.close();
    if (!msg) {
        msg = "系统处理中请稍候...";
    }
    var html = '';
    switch (msgType) {
        case MSG_TYPE.SUCCESS:
            html += '<table border="0" cellpadding="0" cellspacing="0" width="100%">';
            html += '   <tr>';
            html += '       <td style="vertical-align:top;text-align:left;" width="55">';
            html += '           <img src="/images/messagebox/prompt1.gif" border="0" />';
            html += '       </td>';
            html += '       <td style="vertical-align:center;text-align:left;color:#505050">';
            html += '           ' + msg;
            html += '       </td>';
            html += '   </tr>';
            html += '</table>';
            jQuery.prompt(html, { buttons: { "确定": "OK" }, zIndex: 99999, callback: onUnblockFunc }); //, overlayspeed: 0, promptspeed: 0
            break;
        case MSG_TYPE.ERROR:
            html += '<table border="0" cellpadding="0" cellspacing="0" width="100%">';
            html += '   <tr>';
            html += '       <td style="vertical-align:top;text-align:left;" width="55">';
            html += '           <img src="/images/messagebox/error.gif" border="0" />';
            html += '       </td>';
            html += '       <td style="vertical-align:center;text-align:left;color:#505050">';
            html += '           ' + msg;
            html += '       </td>';
            html += '   </tr>';
            html += '</table>';
            jQuery.prompt(html, { buttons: { "确定": "OK" }, zIndex: 99999, callback: onUnblockFunc });
            break;
        case MSG_TYPE.YESNO:
            html += '<table border="0" cellpadding="0" cellspacing="0" width="100%">';
            html += '   <tr>';
            html += '       <td style="vertical-align:top;text-align:left;" width="55">';
            html += '           <img src="/images/messagebox/prompt_10.gif" border="0" />';
            html += '       </td>';
            html += '       <td style="vertical-align:center;text-align:left;color:#505050">';
            html += '           ' + msg;
            html += '       </td>';
            html += '   </tr>';
            html += '</table>';
            jQuery.prompt(html, { buttons: { "是": "Yes", "否": "No" }, zIndex: 99999, callback: onUnblockFunc });
            break;
        default:
            html += '<table border="0" cellpadding="0" cellspacing="0" width="100%">';
            html += '   <tr>';
            html += '       <td style="vertical-align:top;text-align:left;" width="55">';
            html += '           <img src="/images/messagebox/loading.gif" border="0" />';
            html += '       </td>';
            html += '       <td style="vertical-align:center;text-align:left;color:#505050">';
            html += '           ' + msg;
            html += '       </td>';
            html += '   </tr>';
            html += '</table>';
            jQuery.prompt(html, { buttons: {}, zIndex: 99999, callback: onUnblockFunc });
            break;
    }
}

function closeBox() {
    $.prompt.close();
}

function showWindow(title, url, width, height) {
    jQuery.colorbox({
        href: url,
        width: width + BOX_BORDER_WIDTH,
        height: height + BOX_BORDER_HEIGHT,
        iframe: true,
        title: title,
        overlayClose: false,
        opacity: 0.5
    });
}

function closeWindow() {
    $.colorbox.close();
}

function getInput(panelId) {
    var input = {};
    $("#" + panelId).find("input[id], textarea[id], select[id]").each(function () {
        var itemType = $(this).attr("type");
        if (itemType == "text" || itemType == "hidden" || itemType == "password" || itemType == "select" || itemType == "select-one" || itemType == "textarea") {
            input[$(this).attr("key") || $(this).attr("name") || $(this).attr("id")] = $(this).val();
        }
        else if (itemType == "radio" || itemType == "checkbox") {
            var selectValue = "";
            if ($(this).attr("name")) {
                $("#" + panelId).find("input[name='" + $(this).attr("name") + "']").each(function () {
                    if ($(this).attr("checked")) {
                        selectValue += $(this).attr("value") + ",";
                    }
                });
                if (selectValue) {
                    selectValue = selectValue.slice(0, -1);
                }
                input[$(this).attr("key") || $(this).attr("name") || $(this).attr("id")] = selectValue;
            } else {
                if (j(this).attr("checked")) {
                    input[$(this).attr("key") || $(this).attr("name") || $(this).attr("id")] = $(this).attr("value");
                }
                else {
                    input[$(this).attr("key") || $(this).attr("name") || $(this).attr("id")] = "";
                }
            }
        }
    });
    return input;
}