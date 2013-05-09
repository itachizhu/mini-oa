if (jQuery) (function ($) {
    $.showModel = function (options) {
        var defaults = {
            url: null,
            title: null,
            width: 0,
            height: 0,
            bankwidth: 8,
            bankheight: 28
        };

        var sets = $.extend(defaults, options || {});

        function get_cover() {
            var divCover = $("#norepeat_cover");
            if (divCover == null || divCover.length <= 0) {
                divCover = $('<div id="norepeat_cover" style="z-index:69;"><iframe frameborder="0">&nbsp;</iframe></div>');
                divCover.appendTo("body");
            }
            return divCover;
        }

        function get_model() {
            var divModel = $("#norepeat_model");
            if (divModel == null || divModel.length <= 0) {
                divModel = $('<div id="norepeat_model" style="z-index:71;"><div class="modelheader">'
			    + '<div class="title"></div>'
			    + '<div class="link">'
			    + '<a href="javascript:$.showModel();">关闭</a>'
			    + '</div>'
			    + '</div><iframe frameborder="0" scrolling="no">&nbsp;</iframe></div>');
                divModel.appendTo("body");
            }
            return divModel;
        }

        function window_resize() {
            var divCover = get_cover();
            if (divCover == null || divCover.length <= 0) {
                return;
            }
            var h = Math.max($(window).height(), $(document).height());
            var w = Math.max($(window).width(), $(document).width());
            divCover.width(w - 2);
            divCover.height(h - 2);
            //
            window_scroll();
        }

        function window_scroll() {
            var divCover = get_cover();
            var divModel = get_model();
            if (divCover != null && divModel != null) {
                var scrollTop = $(window).scrollTop();
                var scrollLeft = $(window).scrollLeft();
                //设置浮动层
                divModel.css("top", ($(window).height() - divModel.height()) / 2 + scrollTop);
                divModel.css("left", ($(window).width() - divModel.width()) / 2 + scrollLeft);

                var h = Math.max($(window).height(), $(document).height());
                var w = Math.max($(window).width(), $(document).width());
                divCover.width(w - 2);
                divCover.height(h - 2);
            }
        }

        if (sets.url && sets.url.length > 0) {
            var nr_cover = get_cover();
            var nr_model = get_model();
            if (sets.height && sets.height > 0) {
                nr_model.height(sets.height);
            }
            if (sets.width && sets.width > 0) {
                nr_model.width(sets.width);
            }
            if (sets.title && sets.title.length > 0) {
                nr_model.find(".title").text(sets.title);
            }
            var iframe = nr_model.children("iframe");
            if (iframe.length > 0) {
                iframe.attr("src", sets.url);
                iframe.height(sets.height - sets.bankheight);
                iframe.width(sets.width - sets.bankwidth);
            }
            window_resize();
            nr_cover.show();
            nr_model.show();
            $(window).resize(window_resize);
            $(window).scroll(window_scroll);
        } else {
            var nr_cover = get_cover();
            var nr_model = get_model();
            nr_cover.hide();
            nr_model.hide();
            $(window).die("resize", window_resize);
            $(window).die("scroll", window_scroll);
        }
    };
})(jQuery);

if (jQuery) (function ($) {
    var get_norepeat_cover = function (obj) {
        var divCover = null;
        try {
            divCover = obj.document.getElementById("top_norepeat_cover");
        }
        catch (e) {
            divCover = null; // window.document.getElementById("top_norepeat_cover");
        }

        if (divCover == null) {
            divCover = $('<div id="top_norepeat_cover" style="border:solid 1px #888888;background-color:#ffffff;display:none;width:0;height:0;text-align:center;vertical-align:middle;top:0;left:0;position:absolute;z-index:99;overflow:hidden;"><iframe frameborder="0" style="width:100%;height:100%;background-color:#666666;">&nbsp;</iframe></div>').get()[0];
            $(divCover).css("opacity", "0.8");
            $(divCover).css("filter", "alpha(opacity=80)");
            $(divCover).css("zoom", "1");
            $(divCover).appendTo($(obj.document.body));
        }
        return divCover;
    };

    var get_norepeat_tips = function (obj) {
        var divTip = null;
        try {
            divTip = obj.document.getElementById("top_norepeat_tips");
        }
        catch (e) {
            divTip = null; // window.document.getElementById("top_norepeat_tips");
        }
        if (divTip == null) {
            divTip = $('<div id="top_norepeat_tips" style="display:none;border:solid 1px #266392;background-color:#eeeeee;padding:10px 5px;width:202px;position:absolute;z-index:101;"><div style="float:left;width:32px;"><img src="" alt="请稍候"/></div><div id="text" style=" padding-left: 20px; line-height: 17px; font-family: Verdana; font-size: 13px; overflow: hidden;">请稍候 ……</div></div>').get()[0];
            $(divTip).appendTo($(obj.document.body));
        }
        return divTip;
    };

    var window_scroll_norepeat = function () {
        //浏览器窗口滚动的时候设置遮罩层和浮动层的位置
        var win = window.top;
        try { var tmp = win.document.body; } catch (e) { win = window; }
        var divCover = get_norepeat_cover(win);
        var divTips = get_norepeat_tips(win);
        if (divCover != null && divTips != null) {
            //滚动上边距
            var scrollTop;
            if (typeof win.pageYOffset != 'undefined') {
                scrollTop = win.pageYOffset;
            }
            else if (typeof win.document.compatMode != 'undefined' && win.document.compatMode != 'BackCompat') {
                scrollTop = win.document.documentElement.scrollTop;
            }
            else if (typeof win.document.body != 'undefined') {
                scrollTop = win.document.body.scrollTop;
            }
            //滚动左边距
            var scrollLeft;
            if (typeof win.pageXOffset != 'undefined') {
                scrollLeft = win.pageXOffset;
            }
            else if (typeof win.document.compatMode != 'undefined' && win.document.compatMode != 'BackCompat') {
                scrollLeft = win.document.documentElement.scrollLeft;
            }
            else if (typeof win.document.body != 'undefined') {
                scrollLeft = win.document.body.scrollLeft;
            }
            //设置遮罩层
            divCover.style.top = scrollTop + 'px';
            divCover.style.left = scrollLeft + 'px';

            //设置浮动层
            divTips.style.top = ((Number(divCover.style.height.replace("px", "")) - Number(divTips.style.height.replace("px", ""))) / 2 + scrollTop) + 'px';
            divTips.style.left = ((Number(divCover.style.width.replace("px", "")) - Number(divTips.style.width.replace("px", ""))) / 2 + scrollLeft) + 'px';

            var width = height = Number.MAX_VALUE;
            if (typeof (win.innerWidth) == "number") {
                width = win.innerWidth;
                height = win.innerHeight;
            }
            else if (win.document.documentElement && (win.document.documentElement.clientWidth || win.document.documentElement.clientHeight)) {
                width = win.document.documentElement.clientWidth;
                height = win.document.documentElement.clientHeight;
            }
            else if (win.document.body && (win.document.body.clientWidth || win.document.body.clientHeight)) {
                width = win.document.body.clientWidth;
                height = win.document.body.clientHeight;
            }
            divCover.style.width = (width - 2) + 'px';
            divCover.style.height = (height - 2) + 'px';
        }
    };

    var window_resize_norepeat = function () {
        var win = window.top;
        try { var tmp = win.document.body; } catch (e) { win = window; }
        var divCover = get_norepeat_cover(win);
        if (divCover == null || divCover.className == null) {
            return;
        }
        var width = height = Number.MAX_VALUE;
        //遮罩层size设为窗口size
        if (typeof (win.innerWidth) == "number") {
            width = win.innerWidth;
            height = win.innerHeight;
        }
        else if (win.document.documentElement && (win.document.documentElement.clientWidth || win.document.documentElement.clientHeight)) {
            width = win.document.documentElement.clientWidth;
            height = win.document.documentElement.clientHeight;
        }
        else if (win.document.body && (win.document.body.clientWidth || win.document.body.clientHeight)) {
            width = win.document.body.clientWidth;
            height = win.document.body.clientHeight;
        }
        divCover.style.width = (width - 2) + 'px';
        divCover.style.height = (height - 2) + 'px';
        //
        window_scroll_norepeat();
    };

    $.extend({
        showNorepeat: function (options) {
            var defaults = {
                image_src: null,
                tips: null
            };

            var sets = $.extend(defaults, options || {});

            var win = window.top;
            try { var tmp = win.document.body; } catch (e) { win = window; }
            var nr_cover = get_norepeat_cover(win);
            var nr_tips = get_norepeat_tips(win);
            window_resize_norepeat();
            $(nr_cover).show();
            $(nr_tips).show();
            //
            $(win).resize(window_resize_norepeat);
            $(win).scroll(window_scroll_norepeat);
            //
            var tipsText = sets.tips;
            if (tipsText == null || tipsText.length < 1) {
                tipsText = "请稍候";
            }
            $(nr_tips).children("#text").html(tipsText);
            if (sets.image_src) {
                $(nr_tips).children("div:eq(0)").children("img").attr("src", sets.image_src);
            }
        },
        hideNorepeat: function () {
            var win = window.top;
            try { var tmp = win.document.body; } catch (e) { win = window; }
            var nr_cover = get_norepeat_cover(win);
            var nr_tips = get_norepeat_tips(win);
            $(nr_cover).hide();
            $(nr_tips).hide();
            //
            $(win).die("resize", window_resize_norepeat);
            $(win).die("scroll", window_scroll_norepeat);
        }
    });
})(jQuery);