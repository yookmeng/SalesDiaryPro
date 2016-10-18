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
						<div class="spark" align="center">
							<div class="chart" data-percent="${userMonthlySummary.percentprospect}" data-color="#f96d6d" data-trackcolor="#fae2e2">${userMonthlySummary.percentprospect}%</div>
						</div>
						<div class="bottom" align="center">
							<span class="name">Prospect (${userMonthlySummary.actualprospect}/${userMonthlySummary.targetprospect})</span>						
						</div>
					</li>
				</ul>
			</div>
			<div class="col-sm-3">
				<ul class="pagestats style-3">
					<li>
						<div class="spark" align="center">
							<div class="chart" data-percent="${userMonthlySummary.percenttestdrive}" data-color="#368ee0" data-trackcolor="#d5e7f7">${userMonthlySummary.percenttestdrive}%</div>
						</div>
						<div class="bottom" align="center">
							<span class="name">Test Drive (${userMonthlySummary.actualtestdrive}/${userMonthlySummary.targettestdrive})</span>
						</div>
					</li>
				</ul>
			</div>
			<div class="col-sm-3">
				<ul class="pagestats style-3">
					<li>
						<div class="spark" align="center">
							<div class="chart" data-percent="${userMonthlySummary.percentclosed}" data-color="#56af45" data-trackcolor="#dcf8d7">${userMonthlySummary.percentclosed}%</div>
						</div>
						<div class="bottom" align="center">
							<span class="name">Closed (${userMonthlySummary.actualclosed}/${userMonthlySummary.targetclosed})</span>
						</div>
					</li>
				</ul>
			</div>
			<br><br><br><br><br><br><br><br><br><br>
			<div class="col-sm-10" align="center">
				<a href="addQuestionaire" class='btn'>
					<i class="fa fa-plus-circle"></i>New Prospect</a>
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