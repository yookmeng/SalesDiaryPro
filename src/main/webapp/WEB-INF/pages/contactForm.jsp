<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_userNavigation.jsp" />
	<div id="main">
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listContact">Contact</a>
				</li>
			</ul>
		</div>		
		<div class="row">
			<div class="span12">
				<div class="box">
					<div class="box-content nopadding">
						<form:form action="updateContact" method="post" modelAttribute="contact" class='form-horizontal form-wizard'>
						    <form:hidden path="contactid"/>
						    <form:hidden path="userid"/>
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<label for="firstname" class="control-label col-sm-3">First Name</label>
										<div class="col-sm-8">
											<form:input type="text" path="firstname" name="firstname" id="firstname" class="form-control" data-rule-required="true" />
										</div>
									</div>
									<div class="form-group">
										<label for="lastname" class="control-label col-sm-3">Last Name</label>
										<div class="col-sm-8">
											<form:input type="text" path="lastname" name="lastname" id="lastname" class="form-control" data-rule-required="true" />
										</div>
									</div>
									<div class="form-group">
										<label for="mobile" class="control-label col-sm-3">Mobile</label>
										<div class="col-sm-5">
											<form:input type="text" name="mobile" path="mobile" id="mobile" class="form-control" data-rule-required="true" />
										</div>
									</div>
									<div class="form-group">
										<label for="home" class="control-label col-sm-3">Home</label>
										<div class="col-sm-5">
											<form:input type="text" name="home" path="home" id="home" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<label for="work" class="control-label col-sm-3">Work</label>
										<div class="col-sm-5">
											<form:input type="text" name="work" path="work" id="work" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<label for="birthday" class="control-label col-sm-3">Birthday</label>
										<div class="col-sm-8">
											<form:input type="date" path="birthday" name="birthday" id="birthday" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<label for="email" class="control-label col-sm-3">Email</label>
										<div class="col-sm-8">
											<form:input type="text" path="email" name="email" id="email" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label for="website" class="control-label col-sm-3">Web Site</label>
										<div class="col-sm-8">
											<form:input type="text" path="website" name="website" id="website" class="form-control" />
										</div>
									</div>
								</div>
								<div class="col-sm-6">
									<div class="form-group">
										<label for="country" class="control-label col-sm-3">Country</label>
										<div class="col-sm-5">
											<form:input type="text" path="country" name="country" id="country" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label for="zipcode" class="control-label col-sm-3">Zip Code</label>
										<div class="col-sm-5">
											<form:input type="text" path="zipcode" name="zipcode" id="zipcode" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label for="state" class="control-label col-sm-3">State</label>
										<div class="col-sm-5">
											<form:input type="text" path="state" name="state" id="state" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label for="city" class="control-label col-sm-3">City</label>
										<div class="col-sm-5">
											<form:input type="text" path="city" name="city" id="city" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label for="street" class="control-label col-sm-3">Street</label>
										<div class="col-sm-8">
											<form:input type="text" path="street" name="street" id="street" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label for="company" class="control-label col-sm-3">Company</label>
										<div class="col-sm-8">
											<form:input type="text" path="company" name="company" id="company" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label for="title" class="control-label col-sm-3">Title</label>
										<div class="col-sm-8">
											<form:input type="text" path="title" name="title" id="title" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label for="note" class="control-label col-sm-3">Note</label>
										<div class="col-sm-8">
											<form:input type="text" name="note" path="note" id="note" class="form-control" />
										</div>
									</div>
								</div>
							</div>
							<div align="center" class="form-actions">
								<input type="reset" class="btn" onclick="location.href='listContact'" value="Back" id="back">
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