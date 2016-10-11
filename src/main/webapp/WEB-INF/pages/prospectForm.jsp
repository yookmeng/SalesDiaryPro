<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_userNavigation.jsp" />
	<div class="container-fluid" id="content">
		<jsp:include page="_userSideBar.jsp" />	
		<div id="main">
			<div class="breadcrumbs">
				<ul>
					<li>
						<a href="home">Home</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="listProspect">Prospect</a>
					</li>
				</ul>
			</div>		
			<div class="row">
				<div class="span12">
					<div class="box">
						<div class="box-content">
							<form:form action="saveProspect" method="post" modelAttribute="prospect" class='form-horizontal form-wizard'>
				            <form:hidden path="prospectid"/>
				            <form:hidden path="userid"/>
							<div class="form-group">
								<label for="firstname" class="control-label col-sm-2">First Name</label>
								<div class="col-sm-5">
									<form:input type="text" path="firstname" name="firstname" id="firstname" class="form-control" data-rule-required="true" />
								</div>
							</div>
							<div class="form-group">
								<label for="lastname" class="control-label col-sm-2">Last Name</label>
								<div class="col-sm-5">
									<form:input type="text" path="lastname" name="lastname" id="lastname" class="form-control" data-rule-required="true" />
								</div>
							</div>
							<div class="form-group">
								<label for="age" class="control-label col-sm-2">Age</label>
								<div class="col-sm-1">
									<form:input type="text" name="age" path="age" id="age" class="form-control" data-rule-required="true" data-rule-minlength="2" />
								</div>
							</div>
							<div class="form-group">
								<label for="text" class="control-label col-sm-2">Gender</label>
								<div class="col-sm-1">
									<form:select name="gender" path="gender" id="gender" items="${genderlist}" data-rule-required="true" />
								</div>
							</div>
							<div class="form-group">
								<label for="haddress" class="control-label col-sm-2">Home Address</label>
								<div class="col-sm-10">
									<form:input type="text" path="haddress" name="haddress" id="haddress" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="hzipcode" class="control-label col-sm-2">Zip Code</label>
								<div class="col-sm-3">
									<form:input type="text" name="hzipcode" path="hzipcode" id="hzipcode" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="hcity" class="control-label col-sm-2">City</label>
								<div class="col-sm-3">
									<form:input type="text" path="hcity" name="hcity" id="hcity" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="hstate" class="control-label col-sm-2">State</label>
								<div class="col-sm-3">
									<form:input type="text" path="hstate" name="hstate" id="hstate" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="hcountry" class="control-label col-sm-2">Country</label>
								<div class="col-sm-3">
									<form:input type="text" path="hcountry" name="hcountry" id="hcountry" class="form-control" />
								</div>
							</div>								
							<div class="form-group">
								<label for="mobile" class="control-label col-sm-2">Mobile</label>
								<div class="col-sm-3">
									<form:input type="text" path="mobile" name="mobile" id="mobile" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="htelno" class="control-label col-sm-2">Tel. No</label>
								<div class="col-sm-3">
									<form:input type="text" path="htelno" name="htelno" id="htelno" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="waddress" class="control-label col-sm-2">Work Address</label>
								<div class="col-sm-10">
									<form:input type="text" path="waddress" name="waddress" id="waddress" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="wzipcode" class="control-label col-sm-2">Zip Code</label>
								<div class="col-sm-3">
									<form:input type="text" name="wzipcode" path="wzipcode" id="wzipcode" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="wcity" class="control-label col-sm-2">City</label>
								<div class="col-sm-3">
									<form:input type="text" path="wcity" name="wcity" id="wcity" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="wstate" class="control-label col-sm-2">State</label>
								<div class="col-sm-3">
									<form:input type="text" path="wstate" name="wstate" id="wstate" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="wcountry" class="control-label col-sm-2">Country</label>
								<div class="col-sm-3">
									<form:input type="text" path="wcountry" name="wcountry" id="wcountry" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="wtelno" class="control-label col-sm-2">Tel. No</label>
								<div class="col-sm-3">
									<form:input type="text" path="wtelno" name="wtelno" id="wtelno" class="form-control" />
								</div>
							</div>
							
							<div class="form-group">
								<label for="text" class="control-label col-sm-2">Source</label>
								<div class="col-sm-3">
									<form:select name="source" path="source" id="source" items="${sourcelist}" />
								</div>
							</div>
							<div class="form-group">
								<label for="text" class="control-label col-sm-2">Occupation</label>
								<div class="col-sm-3">
									<form:input type="text" name="occupation" path="occupation" id="occupation" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="text" class="control-label col-sm-2">Income</label>
								<div class="col-sm-3">
									<form:input type="text" name="income" path="income" id="income" class="form-control" />
								</div>
							</div>
							<div class="form-actions">
								<input type="reset" class="btn" onclick="location.href='listProspect'" value="Back" id="back">
								<input type="submit" class="btn btn-primary" value="Save">
							</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>