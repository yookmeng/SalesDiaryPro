<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_mdNavigation.jsp" />
	<div id="main" class="container-fluid">
	   	<input type="hidden" value="company" name="company" />
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listCompanyTarget">Target</a>
				</li>
			</ul>
		</div>
		<div class="row">
			<div class="span12">
				<div class="box">
					<div class="box-content">
			        <form:form action="saveCompanyTarget" method="post" modelAttribute="companyTarget" class='form-horizontal form-wizard'>
		            <form:hidden path="companyid"/>        
		            <form:hidden path="targetid"/>			            
					<div class="form-group">
						<label for="period" class="control-label col-sm-2">Period</label>
						<div class="col-sm-2">
							<form:input type = "date" name="period" path="period" id="period" data-rule-required="true" />
						</div>
					</div>
					<div class="form-group">
						<label for="prospect" class="control-label col-sm-2">Prospect</label>
						<div class="col-sm-2">
							<form:input type="text" path="prospect" name="prospect" id="prospect" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="testdrive" class="control-label col-sm-2">Test Drive</label>
						<div class="col-sm-2">
							<form:input type="text" path="testdrive" name="testdrive" id="testdrive" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="closed" class="control-label col-sm-2">Closed</label>
						<div class="col-sm-2">		
							<form:input type="text" path="closed" name="closed" id="closed" class="form-control" />
						</div>
					</div>
					<div class="form-actions">
						<input type="reset" class="btn" onclick="location.href='listCompanyTarget?username=${pageContext.request.userPrincipal.name}'" value="Back" id="back">
						<input type="submit" class="btn btn-primary" value="Save">
					</div>
					</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>