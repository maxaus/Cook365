<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">

    function showMonth(monthOfYear, year) {
       $.get('/calendar_body.wtf?monthOfYear=' + monthOfYear + '&year=' + year, function(data) {
            $('#calendarBody').html(data);
        });
    }

    function openMenuForSelectedDate(day, month, year) {
        window.location = '/menu.wtf?date=' + day + '-' + month + '-' + year;
    }
</script>
<div id="calendarBody">
    <jsp:include page="/calendar_body.wtf?monthOfYear=&year="/>
</div>
