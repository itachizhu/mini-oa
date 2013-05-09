(function ($) {
    $.validator = {};

    $.validator.format = function (source, params) {
        if (arguments.length == 1)
            return function () {
                var args = $.makeArray(arguments);
                args.unshift(source);
                return $.validator.format.apply(this, args);
            };
        if (arguments.length > 2 && params.constructor != Array) {
            params = $.makeArray(arguments).slice(1);
        }
        if (params.constructor != Array) {
            params = [params];
        }
        $.each(params, function (i, n) {
            source = source.replace(new RegExp("\\{" + i + "\\}", "g"), n);
        });
        return source;
    };

    $.extend($.validator, {
        defaults: {
            messages: {},
            rules: {}
        },
        valid: function (options) {
            var results = { flag: true, message: "" };

            var settings = $.extend(true, {}, $.validator.defaults, options);

            var rules = settings["rules"];

            for (var rule in rules) {
                if ($("#" + rule)) {
                    var cur_rules = rules[rule];
                    for (var cur_rule in cur_rules) {
                        if ((typeof (cur_rules[cur_rule]) === "boolean") && !cur_rules[cur_rule]) {
                            continue;
                        }

                        var my_rule = { method: cur_rule, parameters: cur_rules[cur_rule] };
                        var result = this.methods[cur_rule].call(this, $("#" + rule)[0].value.replace(/\r/g, ""), $("#" + rule)[0], my_rule.parameters);
                        if (!result) {
                            results.flag = false;
                            if (settings.messages[rule] && settings.messages[rule][cur_rule]) {
                                results.message += this.format(settings.messages[rule][cur_rule], my_rule.parameters);
                            }
                            break;
                        }
                    }
                }
            }

            return results;
        },
        getLength: function (value, element) {
            switch (element.nodeName.toLowerCase()) {
                case 'select':
                    return $("option:selected", element).length;
                case 'input':
                    if (this.checkable(element))
                        return this.findByName(element.name).filter(':checked').length;
            }
            return value.length;
        },
        checkable: function (element) {
            return /radio|checkbox/i.test(element.type);
        },
        methods: {
            required: function (value, element, param) {
                switch (element.nodeName.toLowerCase()) {
                    case 'select':
                        // could be an array for select-multiple or a string, both are fine this way
                        var val = $(element).val();
                        return val && val.length > 0;
                    case 'input':
                        if (this.checkable(element))
                            return this.getLength(value, element) > 0;
                    default:
                        return $.trim(value).length > 0;
                }
            },
            minlength: function (value, element, param) {
                return this.getLength($.trim(value), element) >= param;
            },
            maxlength: function (value, element, param) {
                return this.getLength($.trim(value), element) <= param;
            },
            rangelength: function (value, element, param) {
                var length = this.getLength($.trim(value), element);
                return (length >= param[0] && length <= param[1]);
            },
            min: function (value, element, param) {
                return value >= param;
            },
            max: function (value, element, param) {
                return value <= param;
            },
            range: function (value, element, param) {
                return (value >= param[0] && value <= param[1]);
            },
            email: function (value, element) {
                return /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(value);
            },
            url: function (value, element) {
                return /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(value);
            },
            date: function (value, element) {
                return !/Invalid|NaN/.test(new Date(value));
            },
            dateISO: function (value, element) {
                return /^\d{4}[\/-]\d{1,2}[\/-]\d{1,2}$/.test(value);
            },
            number: function (value, element) {
                return /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(value);
            },
            digits: function (value, element) {
                return /^\d+$/.test(value);
            },
            equalTo: function (value, param) {
                var target = $(param);
                return value == target.val();
            }
        }
    });
})(jQuery);