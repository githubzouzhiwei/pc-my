//https://gitee.com/jinhuoyan/codes/78tzquho2drl4myb9enx342
/*select层级联动选择组件，通过select的附加属性实现的下拉选择,异步加载数据，支持有父子关系的多层联动。
注意select上增加的属性字段：url标识当前数据源，child表示子级元素，childDataPath表示子级数据源*/
layui.define(['jquery', 'form'], function (exports) {
    var $ = layui.$, form = layui.form;
    // 监听选中事件
    form.on('select', function (data) {
        var id = data.value;
        var obj = data.elem.name;
        var _this = $("select[name='" + obj + "']");
        var child = _this.attr("child");
        var childUrl = $("select[name='" + obj + "']").attr("childDataPath");
        if ($("select[name='" + child + "']").length > 0) {
            CascadeSelect.GetListByParent(child, id, childUrl, true);
        }
    });

    var CascadeSelect = {
        init: function (initObj, isReset) {
            var url = $("select[name='" + initObj + "']").attr("url");
            // ajax请求
            $.get(url, function (data) {
                $.each(data, function (i, d) {
                    $("select[name='" + initObj + "']").append(
                        "<option value='" + d.value + "'>" + d.key + "</option>");
                });
                form.render();
                var selectValue = $("select[name='" + initObj + "']").attr("selectValue");
                var value = $("select[name='" + initObj + "']").attr("value");

                var child = $("select[name='" + initObj + "']").attr("child");
                $("select[name='" + initObj + "']").val(selectValue);
                var childUrl = $("select[name='" + initObj + "']").attr("childDataPath");
                if (childUrl) {
                    CascadeSelect.GetListByParent(child, selectValue, childUrl, isReset);
                }
            }, 'json');
        },
        GetListByParent: function (obj, id, childUrl, isReset) {
            // 先清空下拉框
            var _this = $("select[name='" + obj + "']");
            _this.empty();
            _this.append("<option value='0'>请选小类</option>");
            var child = _this.attr("child");
            var _thischild = $("select[name='" + child + "']");
            var selectValue = "";
            if (id !== "") {
                $.ajax({
                    type: "GET",
                    url: childUrl,
                    dataType: "json",
                    data: { bigTypeId: id },
                    async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, d) {
                                _this.append("<option value='" + d.typeId + "'>" + d.name + "</option>");
                            });
                            if (!isReset) {
                                selectValue = _this.attr("selectValue");
                                _this.val(selectValue);
                            }
                            if (_thischild.length > 0) {
                                childUrl = _this.attr("childDataPath");
                                CascadeSelect.GetListByParent(child, selectValue, childUrl, isReset);
                            }
                        }
                        form.render();
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        layer.msg('系统异常导致操作失败, 请联系管理员。', { icon: 5, shift: 6 });
                    },
                    statusCode: {
                        404: function () {
                            layer.msg('未找到指定请求，请检查访问路径！', { icon: 5, shift: 6 });
                        },
                        500: function () {
                            layer.msg('系统错误，请联系管理员。', { icon: 5, shift: 6 });
                        }
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                    }
                });

            } else {
                while (_thischild.length > 0) {
                    _thischild.empty();
                    _thischild.append("<option value=''>" + _thischild.attr("promtion") + "</option>");
                    _thischild = $("select[name='" + _thischild.attr("child") + "']");
                }
                form.render();
            }
        }
    };

    exports('cascadeSelect', CascadeSelect);
});