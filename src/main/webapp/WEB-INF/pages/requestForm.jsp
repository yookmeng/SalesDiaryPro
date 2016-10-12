<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_userNavigation.jsp" />
	<div id="main">
		<input type="hidden" value="userProfile" name="userProfile" /> 
	    <input type="hidden" value="prospect" name="prospect" /> 
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listProspect">Prospect</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listRequest?prospectid=${prospect.prospectid}">Request</a>
				</li>
			</ul>
		</div>
		<div class="row">
			<div class="span12">
				<div class="box">
					<div class="box-content">
				        <form:form action="saveRequest" method="post" modelAttribute="request" class='form-horizontal form-wizard'>
			            <form:hidden path="prospectid"/>        
			            <form:hidden path="requestid"/>
						<div class="form-group">
							<label for="requestdate" class="control-label col-sm-2">Request Date</label>
							<div class="col-sm-2">
								<form:input type="date" path="requestdate" name="requestdate" id="requestdate" class="form-control" data-rule-required="true" />
							</div>
						</div>
						<div class="form-group">
							<label for="text" class="control-label col-sm-2">Brand</label>
							<div class="col-sm-5">
								<form:select name="brandname" path="brandname" id="brandname" items="${brandlist}" data-rule-required="true" />
							</div>
						</div>
						<div class="form-group">
							<label for="text" class="control-label col-sm-2">Model</label>
							<div class="col-sm-5">
								<form:select name="modelname" path="modelname" id="modelname" items="${modellist}" data-rule-required="true" />
							</div>
						</div>
						<div class="form-group">
							<label for="haddress" class="control-label col-sm-2">Remark</label>
							<div class="col-sm-10">
								<form:input type="text" path="remark" name="remark" id="remark" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="haddress" class="control-label col-sm-2">Status</label>
							<div class="col-sm-10">
								<form:radiobutton path="status" name="status" id="status" value="Hot" />Hot
								<form:radiobutton path="status" name="status" id="status" value="Cold" />Cold
								<form:radiobutton path="status" name="status" id="status" value="Closed" />Closed
							</div>
						</div>
						<div class="form-actions">
							<input type="reset" class="btn" onclick="location.href='listRequest?userid=${userid}&prospectid=${request.prospectid}'" value="Back" id="back">
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