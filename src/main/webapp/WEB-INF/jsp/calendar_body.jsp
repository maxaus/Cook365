<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table cellpadding="0" cellspacing="0" width="217px" bgcolor="#cccccc" style="height: 20px;">
    <tr>
        <td align="left" width="20px" style="padding-left: 7px;vertical-align: middle;">
            <a id="showPrevMonthLink" onclick="showMonth('${month.prevMonth}', '${month.prevMonthYear}');"><img
                    align="left"
                    src="/img/calendar-left.png"
                    alt="<spring:message code="prev-month"/>"></a>
        </td>
        <td align="center" width="177px">
            <div id="monthName"
                 style="display: inline; font-family: arial, serif; font-size: 9pt;">${month.name} ${month.year}</div>

        </td>
        <td align="right" width="20px" style="padding-right: 7px;vertical-align: middle;">
            <a id="showNextMonthLink" onclick="showMonth('${month.nextMonth}', '${month.nextMonthYear}');"><img
                    align="right"
                    src="/img/calendar-right.png"
                    alt="<spring:message code="next-month"/>"></a>
        </td>
    </tr>
</table>
<table id="calendarTable" cellpadding="0" cellspacing="0" style="border: 1px solid #ff9933;">
    <tbody>
    <tr>
        <th><spring:message code="monday_short"/></th>
        <th><spring:message code="tuesday_short"/></th>
        <th><spring:message code="wednesday_short"/></th>
        <th><spring:message code="thursday_short"/></th>
        <th><spring:message code="friday_short"/></th>
        <th><spring:message code="saturday_short"/></th>
        <th><spring:message code="sunday_short"/></th>
    </tr>
    <c:forEach items="${month.weeks}" var="week" varStatus="weekRow">
        <tr>
            <c:if test="${weekRow.index == 0}">
                <c:forEach items="${month.offsetDays}" var="offsetDay">
                    <td>&nbsp;</td>
                </c:forEach>
            </c:if>
            <c:forEach items="${week.days}" var="day">
                <c:choose>
                    <c:when test="${day.holiday}">
                        <td id="day${day.dayOfMonth}"
                            onclick="openMenuForSelectedDate('${day.dayOfMonth}', '${month.monthOfYear}', '${month.year}')"
                            style="cursor: pointer; color: #ff0000;">
                            <c:choose>
                                <c:when test="${month.currentDayOfMonth == day.dayOfMonth}">
                                    <span class="bold">${day.dayOfMonth}</span>
                                </c:when>
                                <c:otherwise>
                                    ${day.dayOfMonth}
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td id="day${day.dayOfMonth}"
                            onclick="openMenuForSelectedDate('${day.dayOfMonth}', '${month.monthOfYear}', '${month.year}')"
                            style="cursor: pointer;">
                            <c:choose>
                                <c:when test="${month.currentDayOfMonth == day.dayOfMonth}">
                                    <span class="bold">${day.dayOfMonth}</span>
                                </c:when>
                                <c:otherwise>
                                    ${day.dayOfMonth}
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script type="text/javascript">
    jQuery(document).ready(function () {
        $.each(
                $("td[id^='day']"),
                function (intIndex, objValue) {
                    var dayTd = $('#' + objValue.id);
                    dayTd.mouseover(function () {
                        dayTd.css("background-color", "#cccccc");
                        dayTd.css("text-decoration", "underline");
                    });
                    dayTd.mouseout(function () {
                        dayTd.css("background-color", "#e6e4e4");
                        dayTd.css("text-decoration", "none");
                    });
                });
    });
</script>