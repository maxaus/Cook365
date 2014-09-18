<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title><spring:message code="cook365"/></title>
    <link href="/css/main.css" type="text/css" rel="stylesheet"/>
    <link href="/css/ui-lightness/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery-1.6.2.js"></script>
    <script type="text/javascript" src="/js/jquery.validate.js"></script>
    <script type="text/javascript" src="/js/ui/jquery-ui-1.8.16.custom.js"></script>
    <script type="text/javascript" src="/js/ui/jquery.ui.datepicker.js"></script>
    <script type="text/javascript" src="/js/ui/i18n/jquery.ui.datepicker-ru.js"></script>

    <script type="text/javascript" src="/js/ui/jquery.ui.core.js"></script>
    <script type="text/javascript" src="/js/ui/jquery.ui.widget.js"></script>

    <script type="text/javascript" src="/js/ui/jquery.ui.button.js"></script>
    <script type="text/javascript" src="/js/ui/jquery.ui.position.js"></script>
    <script type="text/javascript" src="/js/ui/jquery.ui.autocomplete.js"></script>


    <script>
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
                                minLength:0,
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

                    this.button = $("<button type='button'>&nbsp;</button>")
                            .attr("tabIndex", -1)
                            .attr("title", "<spring:message code="show-all"/>")
                            .insertAfter(input)
                            .button({
                                icons:{
                                    primary:"ui-icon-triangle-1-s"
                                },
                                text:false
                            })
                            .removeClass("ui-corner-all")
                            .addClass("ui-corner-right ui-button-icon")
                            .click(function () {
                                // close if already visible
                                if (input.autocomplete("widget").is(":visible")) {
                                    input.autocomplete("close");
                                    return;
                                }

                                // work around a bug (likely same cause as #5265)
                                $(this).blur();

                                // pass empty string as value to search for, displaying all results
                                input.autocomplete("search", "");
                                input.focus();
                            });
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
</head>
<body>
<div id="addNewProductDialog" title="" style="display: none;">
</div>
<form:form method="post" action="edit_recipe.wtf" commandName="recipeForm" enctype="multipart/form-data"
           acceptCharset="UTF-8" id="editRecipeForm">
    <form:hidden path="recipe.id"/>
    <table>
        <c:if test="${recipeForm.recipe.imagePath != null}">
            <tr>
                <td><img src="/image/${recipeForm.recipe.id}.jpg" alt="${recipeForm.recipe.title}" width="100px"
                         height="100px">
                </td>
                <td></td>
            </tr>
        </c:if>
        <tr>
            <td><form:label path="recipe.title"><spring:message code="name"/></form:label></td>
            <td><form:input path="recipe.title" cssClass="inputBorder"/><form:errors path="recipe.title"/></td>
        </tr>
        <tr>
            <td><form:label path="recipe.shortDescription"><spring:message code="shortDescription"/></form:label></td>
            <td><form:input path="recipe.shortDescription" width="750px" cssClass="inputBorder"/><form:errors
                    path="recipe.shortDescription"/></td>
        </tr>
        <tr>
            <td><form:label path="recipe.externalLink"><spring:message code="externalLink"/></form:label></td>
            <td><form:input path="recipe.externalLink" width="750px" cssClass="inputBorder"/><form:errors
                    path="recipe.externalLink"/></td>
        </tr>
        <tr>
            <td><form:label path="recipe.moderated"><spring:message code="moderated"/></form:label></td>
            <td><form:checkbox path="recipe.moderated" cssClass="inputBorder"/><form:errors
                    path="recipe.moderated"/></td>
        </tr>
        <tr>
            <td><form:label path="recipe.vegetarian"><spring:message code="vegetarian"/></form:label></td>
            <td><form:checkbox path="recipe.vegetarian" cssClass="inputBorder"/><form:errors
                    path="recipe.vegetarian"/></td>
        </tr>
        <tr>
            <td valign="top"><spring:message code="description"/></td>
            <td>
                <span class="m1">
                <FCK:editor instanceName="recipe.description" width="750px" height="600px"
                            inputName="recipe.description" basePath="/fckeditor"
                            value="${recipeForm.recipe.description}">
                </FCK:editor>
                </span>
            </td>
        </tr>
        <tr>
            <td><form:label path="date"><spring:message code="date"/></form:label></td>
            <td><form:input path="date" cssClass="inputBorder"/></td>
        </tr>
        <tr>
            <td><form:label path="imageFile"><spring:message code="mainImage"/></form:label></td>
            <td><form:input path="imageFile" type="file" cssClass="inputBorder"/></td>
        </tr>
        <tr>
            <td><spring:message code="type"/></td>
            <td>
                <form:select path="recipe.type" cssClass="inputBorder">
                    <form:option value="" label=""/>
                    <form:options items="${recipeForm.types}"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td valign="top"><spring:message code="ingredients"/></td>
            <td>
                <input type="button" value="<spring:message code="add-ingredient"/>" id="addIngredient"
                       title="<spring:message code="add-ingredient"/>"/>
                <input type="button" value="<spring:message code="add-new-product"/>" id="addNewProduct"
                       title="<spring:message code="save-new-product-to-database"/>"/>

                <div id="ingredientList">
                    <c:forEach items="${recipeForm.recipe.ingredients}" varStatus="ingredientRow">
                        <div id="ingredient${ingredientRow.index}">
                            <form:hidden path="recipe.ingredients[${ingredientRow.index}].id"/>
                            <form:label
                                    path="recipe.ingredients[${ingredientRow.index}].product.id"><spring:message
                                    code="product"/></form:label>
                            <form:select path="recipe.ingredients[${ingredientRow.index}].product.id"
                                         id="productSelect${ingredientRow.index}" cssClass="inputBorder">
                                <form:option value="" label=""/>
                                <form:options items="${recipeForm.products}"/>
                            </form:select>
                            <form:label path="recipe.ingredients[${ingredientRow.index}].value"><spring:message
                                    code="value"/></form:label>
                            <form:input path="recipe.ingredients[${ingredientRow.index}].value"
                                        id="value${ingredientRow.index}" cssClass="inputBorder"/>
                            <form:label
                                    path="recipe.ingredients[${ingredientRow.index}].measureUnit.id"><spring:message
                                    code="measureUnit"/></form:label>
                            <form:select path="recipe.ingredients[${ingredientRow.index}].measureUnit.id"
                                         id="measureSelect${ingredientRow.index}" cssClass="inputBorder">
                                <form:option value="" label=""/>
                                <form:options items="${recipeForm.measureUnits}"/>
                            </form:select>
                            <c:if test="${ingredientRow.index > 0}">
                                <input type="button" id="removeIngredient${ingredientRow.index}" value="-"
                                       onclick="onDeleteProduct(this.id);"
                                       title="<spring:message code="deleteIngredient"/>">
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="<spring:message code="save"/>"/>
            </td>
        </tr>
    </table>
</form:form>
<script type="text/javascript">
    jQuery(document).ready(function () {
        $("#date").datepicker({ showOn:"button",
                    buttonImage:"/img/calendar.gif",
                    buttonImageOnly:true},
                $.datepicker.regional['ru']
        );


        $("#editRecipeForm").validate({
            focusInvalid:false,
            focusCleanup:true,
            rules:{
                "recipe.title":{
                    required:true
                },
                "recipe.shortDescription":{
                    required:true
                },
                "recipe.description":{
                    required:true
                },
                "recipe.type":{
                    required:true
                },
                "date":{
                    required:true
                }
            },
            messages:{
                "recipe.title":{
                    required:"<spring:message code="fill-in-the-name"/>"
                },
                "recipe.shortDescription":{
                    required:"<spring:message code="fill-in-the-short-description"/>"
                },
                "recipe.description":{
                    required:"<spring:message code="fill-in-the-description"/>"
                },
                "recipe.type":{
                    required:"<spring:message code="select-the-type"/>"
                },
                "date":{
                    required:"<spring:message code="select-the-date"/>"
                }
            }
        });

        $('#ingredientList select').each(function (n, element) {
            if (element.id.indexOf('productSelect') != -1) {
                $("#" + element.id).combobox();
                $("#" + element.id).rules("add", { required:true, messages:{required:"<spring:message code="select-the-product"/>"}});
            }
            <%--if (element.id.indexOf('measureSelect') != -1) {--%>
            <%--$("#" + element.id).rules("add", { required: true, messages: {required :"<spring:message code="select-the-measure-unit"/>"}});--%>
            <%--}--%>

        });
        $('#ingredientList input').each(function (n, element) {
            if (element.id.indexOf('value') != -1) {
                $("#" + element.id).rules("add", { number:true, messages:{number:"<spring:message code="value-must-be-numeric"/>"}});
            }
        });

    });
    $('#addIngredient').click(function (e) {
        var $div = $('#ingredientList').children('div:last');
        var parts = $div.attr('id').match(/(\D+)(\d+)$/);
        var divId = parts[2];
        var block = '<div id="ingredient#">' +
                '<label for="recipe.ingredients#.product.id"><spring:message code="product"/></label>' +
                '<select name="recipe.ingredients[#].product.id" id="productSelect#" class="inputBorder">' +
                '</select>' +
                '<label for="recipe.ingredients#.value"><spring:message code="value"/></label>' +
                '<input type="text" value="0.0" name="recipe.ingredients[#].value" id="value#" class="inputBorder">' +
                '<label for="recipe.ingredients#.measureUnit.id"><spring:message code="measureUnit"/></label>' +
                '<select name="recipe.ingredients[#].measureUnit.id" id="measureSelect#" class="inputBorder">' +
                '</select>' +
                '<input type="button" id="removeIngredient#" value="-" onclick="onDeleteProduct(this.id);" title="<spring:message code="deleteIngredient"/>">' +
                '</div>';
        divId++;
        block = block.replace(/#/g, divId);
        $div.after(block);

        $('#productSelect0 option').clone().appendTo('#productSelect' + divId);
        $('#productSelect' + divId).combobox();
        $('#measureSelect0 option').clone().appendTo('#measureSelect' + divId);
        $('#productSelect' + divId).rules("add", { required:true, messages:{required:"<spring:message code="select-the-product"/>"}});
        $('#value' + divId).rules("add", { number:true, messages:{number:"<spring:message code="value-must-be-numeric"/>"}});
        <%--$('#measureSelect' + divId).rules("add", { required: true, messages: {required :"<spring:message code="select-the-measure-unit"/>"}});--%>
    });

    $('#addNewProduct').click(function (e) {
        $.get('/add_product.wtf', function (data) {
            $("#addNewProductDialog").attr("title", "<spring:message code="create-new-product"/>");
            $("#addNewProductDialog").html(data);
            $("#addNewProductDialog").dialog({modal:true,
                resizable:false,
                draggable:false,
                width:'auto'});
            $("#addNewProductDialog").bind('dialogclose', function (event) {
                $.each(
                        $("ingredientList div[id^='ingredient']"),
                        function (intIndex, objValue) {

                        }
                );
            });
        });
    });

    function onDeleteProduct(id) {
        var parts = id.match(/(\D+)(\d+)$/);
        if (parts != null && parts[2] != '0') {
            $('#ingredient' + parts[2]).remove();
        }
    }
</script>
</body>
</html>