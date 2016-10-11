<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="_menu.jsp" />
<body>
<div id="main">
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>Review</h1>
			</div>
		</div>
		<div>
			<div class="row">
				<div class="span12">
					<div class="box">
						<div class="box-content">
					        <form:form action="saveReview" method="post" modelAttribute="review" class='form-horizontal form-wizard'>
				            <form:hidden path="reviewid"/>
				   			<input type="hidden" value="userid" name="userid" /> 
				   			<input type="hidden" value="targetid" name="targetid" /> 
				   			<input type="hidden" value="teamtargetid" name="teamtargetid" /> 
				   			<input type="hidden" value="reviewby" name="reviewby" /> 				   			
							<div class="form-group">
								<label for="reviewdate" class="control-label col-sm-2">Review Date</label>
								<div class="col-sm-2">
									<form:input type="date" path="reviewdate" name="reviewdate" id="reviewdate" class="form-control" data-rule-required="true" />
								</div>
							</div>
							<div class="form-group">
								<label for="prospect" class="control-label col-sm-2">Prospect</label>
								<div class="col-sm-2">
									<form:input type="text" path="prospect" name="prospect" id="prospect" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="sales" class="control-label col-sm-2">Sales</label>
								<div class="col-sm-2">
									<form:input type="text" path="sales" name="sales" id="sales" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="totalsales" class="control-label col-sm-2">Total Sales</label>
								<div class="col-sm-2">
									<form:input type="text" path="totalsales" name="totalsales" id="totalsales" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="minute" class="control-label col-sm-2">Minute</label>
								<div class="col-sm-10">
									<form:input type="text" path="minute" name="minute" id="minute" class="form-control" />
								</div>
							</div>
							<div class="form-actions">
								<input type="reset" class="btn" onclick="location.href='listRequest?userid=${userid}&prospectid=${request.prospectid}'" value="Back" id="back">
					            <input type="button" class="btn" onclick="location.href='deleteRequest?requestid=${request.requestid}'" value="Delete" >
								<input type="submit" class="btn btn-primary" value="Save">
							</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>	
</body>
</html>