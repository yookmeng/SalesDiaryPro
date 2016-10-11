<!DOCTYPE HTML>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<!-- Apple devices fullscreen -->
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<!-- Apple devices fullscreen -->
	<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />

	<title>FLAT - Page statistics</title>

	<!-- Bootstrap -->
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
	<!-- jQuery UI -->
	<link rel="stylesheet" href="resources/css/plugins/jquery-ui/smoothness/jquery-ui.css">
	<link rel="stylesheet" href="resources/css/plugins/jquery-ui/smoothness/jquery.ui.theme.css">
	<!-- Easy pie  -->
	<link rel="stylesheet" href="resources/css/plugins/easy-pie-chart/jquery.easy-pie-chart.css">
	<!-- chosen -->
	<link rel="stylesheet" href="resources/css/plugins/chosen/chosen.css">
	<!-- Theme CSS -->
	<link rel="stylesheet" href="resources/css/style.css">
	<!-- Color CSS -->
	<link rel="stylesheet" href="resources/css/themes.css">


	<!-- jQuery -->
	<script src="resources/js/jquery.min.js"></script>

	<!-- Nice Scroll -->
	<script src="resources/js/plugins/nicescroll/jquery.nicescroll.min.js"></script>
	<!-- imagesLoaded -->
	<script src="resources/js/plugins/imagesLoaded/jquery.imagesloaded.min.js"></script>
	<!-- jQuery UI -->
	<script src="resources/js/plugins/jquery-ui/jquery.ui.core.min.js"></script>
	<script src="resources/js/plugins/jquery-ui/jquery.ui.widget.min.js"></script>
	<script src="resources/js/plugins/jquery-ui/jquery.ui.mouse.min.js"></script>
	<script src="resources/js/plugins/jquery-ui/jquery.ui.resizable.min.js"></script>
	<script src="resources/js/plugins/jquery-ui/jquery.ui.sortable.min.js"></script>
	<!-- slimScroll -->
	<script src="resources/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<!-- Bootstrap -->
	<script src="resources/js/bootstrap.min.js"></script>
	<!-- Chosen -->
	<script src="resources/js/plugins/chosen/chosen.jquery.min.js"></script>
	<!-- Bootbox -->
	<script src="resources/js/plugins/bootbox/jquery.bootbox.js"></script>
	<!-- Bootbox -->
	<script src="resources/js/plugins/form/jquery.form.min.js"></script>
	<!-- Validation -->
	<script src="resources/js/plugins/validation/jquery.validate.min.js"></script>
	<script src="resources/js/plugins/validation/additional-methods.min.js"></script>
	<!-- Sparkline -->
	<script src="resources/js/plugins/sparklines/jquery.sparklines.min.js"></script>
	<!-- Easy pie -->
	<script src="resources/js/plugins/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>
	<!-- Flot -->
	<script src="resources/js/plugins/flot/jquery.flot.min.js"></script>
	<script src="resources/js/plugins/flot/jquery.flot.resize.min.js"></script>

	<!-- Theme framework -->
	<script src="resources/js/eakroko.min.js"></script>
	<!-- Theme scripts -->
	<script src="resources/js/application.min.js"></script>
	<!-- Just for demonstration -->
	<script src="resources/js/demonstration.min.js"></script>
<body>
	<jsp:include page="_userNavigation.jsp" />
	<div class="container-fluid" id="content">
		<jsp:include page="_userSideBar.jsp" />
		<div id="main">
			<input type="hidden" value="userMonthlySummary" name="userMonthlySummary" /> 
			<div class="row margin-top">
				<div class="col-sm-4">
					<ul class="pagestats style-3">
						<li>
							<div class="spark">
								<div class="chart" data-percent="${userMonthlySummary.percentprospect}" data-color="#f96d6d" data-trackcolor="#fae2e2">${userMonthlySummary.percentprospect}%</div>
							</div>
							<div class="bottom">
								<span class="name">Prospect</span>						
							</div>
						</li>
					</ul>
				</div>
				<div class="col-sm-4">
					<ul class="pagestats style-3">
						<li>
							<div class="spark">
								<div class="chart" data-percent="${userMonthlySummary.percenttestdrive}" data-color="#368ee0" data-trackcolor="#d5e7f7">${userMonthlySummary.percenttestdrive}%</div>
							</div>
							<div class="bottom">
								<span class="name">Test Drive</span>
							</div>
						</li>
					</ul>
				</div>
				<div class="col-sm-4">
					<ul class="pagestats style-3">
						<li>
							<div class="spark">
								<div class="chart" data-percent="${userMonthlySummary.percentclosed}" data-color="#56af45" data-trackcolor="#dcf8d7">${userMonthlySummary.percentclosed}%</div>
							</div>
							<div class="bottom">
								<span class="name">Closed</span>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>		
	</div>
<script>
	function getpercent(x, y) {
		return x/y*100;
	}
</script>
</body>
</html>