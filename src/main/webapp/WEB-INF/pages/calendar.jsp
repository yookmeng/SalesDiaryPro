<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_userNavigation.jsp" />
	<div id="main">
		<div class="container-fluid">
			<div class="breadcrumbs">
				<ul>
					<li>
						<a href="home">Home</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="calendar">Calendar</a>
					</li>
				</ul>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-calendar"></i>
								Calendar
							</h3>
					 	</div>
						<div class="box-content nopadding">
							<div class="calendar"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	    if ($(".calendar").length > 0 && !$(".calendar").parent().hasClass("daterangepicker")) {
	        var date = new Date();
	        var d = date.getDate();
	        var m = date.getMonth();
	        var y = date.getFullYear();
			var allevents = [{
	            title: 'All Day Event',
	            start: new Date(y, m, 1)
	        }, {
	            title: 'Long Event',
	            start: new Date(y, m, d - 5),
	            end: new Date(y, m, d - 2)
	        }, {
	            id: 999,
	            title: 'Repeating Event',
	            start: new Date('2016-10-12 16:00:00.000'),
	            end: '',
	            allDay: false
	        }, {
	            id: 999,
	            title: 'Repeating Event',
	            start: new Date(y, m, d + 4, 16, 0),
	            allDay: false
	        }, {
	            title: 'Meeting',
	            start: new Date(y, m, d, 10, 30),
	            allDay: false
	        }, {
	            title: 'Lunch',
	            start: new Date(y, m, d, 12, 0),
	            end: new Date(y, m, d, 14, 0),
	            allDay: false
	        }, {
	            title: 'Birthday Party',
	            start: new Date(y, m, d + 1, 19, 0),
	            end: new Date(y, m, d + 1, 22, 30),
	            url: '',
	            allDay: false
	        }, {
	            title: 'Click for Google',
	            start: new Date(y, m, 28),
	            end: new Date(y, m, 29),
	            url: 'http://google.com/',
	            allDay: false
	        }];
		    $('.calendar').fullCalendar({
	            header: {
	                left: '',
	                center: 'prev,title,next',
	                right: 'month,agendaWeek,agendaDay,today'
	            },
	            buttonText: {
	                today: 'Today'
	            },
	            editable: true,
		    	events: allevents,			
    	    	dayClick: function() {
    	        	alert('a day has been clicked!');
    	    	}
		    });		
	        $(".fc-button-effect").remove();
	        $(".fc-button-next .fc-button-content").html("<i class='fa fa-chevron-right'></i>");
	        $(".fc-button-prev .fc-button-content").html("<i class='fa fa-chevron-left'></i>");
	        $(".fc-button-today").addClass('fc-corner-right');
	        $(".fc-button-prev").addClass('fc-corner-left');
		 }
	</script>
</body>
</html>