<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_mdNavigation.jsp" />
	<div class="container-fluid">
		<input type="hidden" value="companyTarget" name="companyTarget" /> 
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listCompanyTarget">Company Target</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listBranchTarget?targetid=${companyTarget.targetid}">Branch Target</a>
				</li>					
			</ul>
		</div>
		<div class="row">
			<div class="span12">
				<div class="box">
					<div class="box-content">
				        <form:form action="" method="post" modelAttribute="branchTarget"  class='form-horizontal form-wizard'>
			            <form:hidden path="period"/>        
			            <form:hidden path="targetid"/>        
			            <form:hidden path="companytargetid"/>        
						<div class="form-group">
							<label for="period" class="control-label col-sm-2">Period</label>
							<div class="col-sm-2">
								<label>${companyTarget.period}</label>
							</div>
						</div>
						<div class="form-group">
							<label for="text" class="control-label col-sm-2">Branch</label>
							<div class="col-sm-5">
								<form:select name="branchname" path="branchname" id="branchname" items="${branchlist}" data-rule-required="true" />
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
							<input type="reset" class="btn" onclick="location.href='listBranchTarget?companytargetid=${companyTarget.targetid}&targetid=${companyTarget.targetid}'" value="Back" id="back">
							<input id="btnSave" type="submit" class="btn btn-primary" name="Save" value="Save">
						</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	$('#btnSave').click(function (e) {
		e.preventDefault(); // <------------------ stop default behaviour of button
		
		var targetid = $('#targetid').val(); 
	    var branchid = ""; 
	    var branchname = $('#branchname').val();
	    var period = $('#period').val(); 
	    var displayperiod = ""; 
		var companytargetid = $('#companytargetid').val(); 	    
	    var prospect = $('#prospect').val(); 
	    var testdrive = $('#testdrive').val(); 
	    var closed = $('#closed').val(); 
	
	    var json = {
	    		"targetid" : targetid,
	    		"branchid" : branchid,
	    		"branchname" : branchname,
	    		"period" : period,
	    		"displayperiod" : displayperiod,
	    		"companytargetid" : companytargetid,
	    		"prospect" : prospect,
	    		"testdrive" : testdrive,
	    		"closed" : closed
		};
	    if (json.targetid=="0"){
	        $.ajax({
	            url: "http://localhost:8080/SalesDiaryPro/branchtarget/create",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json), 
	            success:function(data, Textstatus, jqXHR){
	                alert("Record created!");
	                window.location.href = "/SalesDiaryPro/listBranchTarget?targetid="+companytargetid;
	            },
	            error:function(jqXhr, Textstatus){
	                alert("Create failed!");
	            }
	        });    	
	    }
	    else {
	        $.ajax({
	            url: "http://localhost:8080/SalesDiaryPro/branchtarget/update/"+targetid,
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json),
	            success:function(data, Textstatus, jqXHR){
	                alert("Record updated!");
	                window.location.href = "/SalesDiaryPro/listBranchTarget?targetid="+companytargetid;
	            },
	            error:function(jqXhr, Textstatus){
	                alert("Update failed!");
	            }
	        });
	    }
	    return true;
	})
	</script>	
</body>
</html>