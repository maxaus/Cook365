<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    (function ($) {
        $.widget("ui.combobox", {
            _create:function () {
                var self = this,
                        select = this.element.hide(),
                        selected = select.children(":selected"),
                        value = selected.val() ? selected.text() : "";
                var input = this.input = $("<input>")
                        .insertAfter(select)
                        .val(value)
                        .autocomplete({
                            delay:0,
                            minLength:2,
                            source:function (request, response) {
                                var matcher = new RegExp($.ui.autocomplete.escapeRegex(request.term), "i");
                                response(select.children("option").map(function () {
                                    var text = $(this).text();
                                    if (this.value && ( !request.term || matcher.test(text) ))
                                        return {
                                            label:text.replace(
                                                    new RegExp(
                                                            "(?![^&;]+;)(?!<[^<>]*)(" +
                                                                    $.ui.autocomplete.escapeRegex(request.term) +
                                                                    ")(?![^<>]*>)(?![^&;]+;)", "gi"
                                                    ), "<strong>$1</strong>"),
                                            value:text,
                                            option:this
                                        };
                                }));
                            },
                            select:function (event, ui) {
                                ui.item.option.selected = true;
                                self._trigger("selected", event, {
                                    item:ui.item.option
                                });
                            },
                            change:function (event, ui) {
                                if (!ui.item) {
                                    var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex($(this).val()) + "$", "i"),
                                            valid = false;
                                    select.children("option").each(function () {
                                        if ($(this).text().match(matcher)) {
                                            this.selected = valid = true;
                                            return false;
                                        }
                                    });
                                    if (!valid) {
                                        // remove invalid value, as it didn't match anything
                                        $(this).val("");
                                        select.val("");
                                        input.data("autocomplete").term = "";
                                        return false;
                                    }
                                }
                            }
                        })
                        .addClass("ui-widget ui-widget-content ui-corner-left");

                input.data("autocomplete")._renderItem = function (ul, item) {
                    return $("<li></li>")
                            .data("item.autocomplete", item)
                            .append("<a>" + item.label + "</a>")
                            .appendTo(ul);
                };
            },

            destroy:function () {
                this.input.remove();
                this.button.remove();
                this.element.show();
                $.Widget.prototype.destroy.call(this);
            }
        });
    })(jQuery);


</script>

<form:form method="post" action="extended_search_results.wtf" commandName="extendedSearchForm">
    <form:hidden path="pageNumber"/>
    <p><spring:message code='ingredients'/>:</p>

    <div id="ingredientList">
        <c:forEach items="${extendedSearchForm.ingredients}" varStatus="ingredientRow">
            <div id="ingredient${ingredientRow.index}" class="m4">
                <form:select path="ingredients[${ingredientRow.index}].product.id"
                             id="productSelect${ingredientRow.index}">
                    <form:option value="" label=""/>
                    <form:options items="${extendedSearchForm.products}"/>
                </form:select>
                <c:if test="${ingredientRow.index > 0}">
                    <img id="removeIngredient${ingredientRow.index}" src="/img/minus-icon.png" alt="-"
                         title="<spring:message code='delete-product-from-search'/>" style="cursor: pointer; margin-bottom: -10px;"
                         onclick="onDeleteProduct(this.id);"/>
                </c:if>
            </div>
        </c:forEach>
    </div>
    <div class="m4">
        <a id="addIngredient" style="cursor: pointer;"><img src="/img/plus-icon.png" alt="+" height="22px"
                                                            title="<spring:message code='add-product'/>"
                                                            style="margin-bottom: -5px; margin-right: 5px"/><spring:message code='add-product'/></a>
    </div>

    <input type="submit" value="<spring:message code='search'/>" class="m4">
</form:form>

<script type="text/javascript">
    jQuery(document).ready(function () {


        $('#ingredientList select').each(function (n, element) {
            if (element.id.indexOf('productSelect') != -1) {
                $("#" + element.id).combobox();
            }
        });


    });
    $('#addIngredient').click(function (e) {
        var $div = $('#ingredientList').children('div:last');
        var parts = $div.attr('id').match(/(\D+)(\d+)$/);
        var divId = parts[2];
        var block = '<div id="ingredient#" class="m4">' +
                '<select name="ingredients[#].product.id" id="productSelect#">' +
                '</select>' +
                '<img id="removeIngredient#" src="/img/minus-icon.png" alt="-"' +
                ' title="<spring:message code='delete-product-from-search'/>" style="cursor: pointer; margin-bottom: -10px; margin-left: 3px;"' +
                ' onclick="onDeleteProduct(this.id);"/>' +
                '</div>';
        divId++;
        block = block.replace(/#/g, divId);
        $div.after(block);

        $('#productSelect0 option').clone().appendTo('#productSelect' + divId);
        $('#productSelect' + divId).combobox();
    });

    function onDeleteProduct(id) {
        var parts = id.match(/(\D+)(\d+)$/);
        if (parts != null && parts[2] != '0') {
            $('#ingredient' + parts[2]).remove();
        }
    }
</script>