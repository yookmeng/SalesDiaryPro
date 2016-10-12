<!DOCTYPE HTML>
<html>
<jsp:include page="_menu.jsp" />
<body data-mobile-sidebar="slide">
	<jsp:include page="_saNavigation.jsp" />
	<div id="main"></div>
</body>
<script>
	function companyForm(){
		
		$('#main').load('companyForm.jsp');
	}
</script>
</html>