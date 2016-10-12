<!DOCTYPE HTML>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="_menu.jsp" />
<body data-mobile-sidebar="slide">
<div>
	<jsp:include page="_userNavigation.jsp" />
	<div id="main">
		<input type="hidden" value="userMonthlySummary" name="userMonthlySummary" /> 
		<div class="row margin-top">
			<div class="col-sm-3">
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
			<div class="col-sm-3">
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
			<div class="col-sm-3">
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