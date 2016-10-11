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
				<h1>Member Target</h1>
			</div>
		</div>
		<div>
			<div class="row">
				<div class="span12">
					<div class="box">
						<div class="box-content">
				        <form:form action="saveUserTarget" method="post" modelAttribute="userTarget" class='form-horizontal form-wizard'>
			            <form:hidden path="period"/>        
			            <form:hidden path="targetid"/>        
			            <form:hidden path="teamtargetid"/>        
						<div class="form-group">
							<label for="period" class="control-label col-sm-2">Period</label>
							<div class="col-sm-2">
								<label>${userTarget.period}</label>
							</div>
						</div>
						<div class="form-group">
							<label for="text" class="control-label col-sm-2">Brand</label>
							<div class="col-sm-5">
								<form:select name="username" path="username" id="username" items="${userlist}" data-rule-required="true" />
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
							<input type="reset" class="btn" onclick="location.href='listUserTarget?targetid=${userTarget.teamtargetid}'" value="Back" id="back">
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