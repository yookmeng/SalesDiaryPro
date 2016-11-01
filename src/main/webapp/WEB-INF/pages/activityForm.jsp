<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_userNavigation.jsp" />
	<div>
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
					<a href="listActivity?prospectid=${prospect.prospectid}">Activity</a>
				</li>
			</ul>
		</div>
		<div class="row">
			<div class="span12">
				<div class="box">
					<div class="box-content">
				        <form:form action="" method="post" modelAttribute="activity" class='form-horizontal form-wizard'>
			            <form:hidden path="prospectid"/>        
			            <form:hidden path="activityid"/>
					    <input type="hidden" value="userid" name="userid" /> 
						<div class="form-group">
							<label for="requestdate" class="control-label col-sm-2">Activity Date</label>
							<div class="col-sm-2">
								<form:input type="date" path="activitydate" name="activitydate" id="activitydate" data-rule-required="true" />
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
							<label for="text" class="control-label col-sm-2">Activity Type</label>
							<div class="col-sm-5">
								<form:checkbox name="demo" path="demo" id="demo" />Demo
								<form:checkbox name="testdrive" path="testdrive" id="testdrive" />Test Drive
								<form:checkbox name="quotation" path="quotation" id="quotation" />Quotation
							</div>
						</div>
						<div class="form-group">
							<label for="text" class="control-label col-sm-2">Link</label>
							<div class="col-sm-5">
								<form:checkbox name="linkevent" path="linkevent" id="linkevent" />Calendar
							</div>
						</div>
						<div class="form-group">
							<label for="haddress" class="control-label col-sm-2">Remark 1</label>
							<div class="col-sm-10">
								<form:input type="text" path="remark1" name="remark1" id="remark1" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="haddress" class="control-label col-sm-2">Remark 2</label>
							<div class="col-sm-10">
								<form:input type="text" path="remark2" name="remark2" id="remark2" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="haddress" class="control-label col-sm-2">Remark 3</label>
							<div class="col-sm-10">
								<form:input type="text" path="remark3" name="remark3" id="remark3" class="form-control" />
							</div>
						</div>
						<div class="form-actions">
							<input type="reset" class="btn" onclick="location.href='listActivity?prospectid=${activity.prospectid}'" value="Back" id="back">
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
		
		var activityid = $('#activityid').val(); 
	    var prospectid = $('#prospectid').val(); 
	    var activitydate = $('#activitydate').val(); 
	    var brandid = ""; 
	    var brandname = $('#brandname').val(); 
	    var modelid = ""; 
	    var modelname = $('#modelname').val(); 
	    var demo = $('#demo').prop('checked'); 
	    var testdrive = $('#testdrive').prop('checked'); 
	    var quotation = $('#quotation').prop('checked'); 
	    var linkevent = $('#linkevent').prop('checked'); 
	    var remark1 = $('#remark1').val(); 
	    var remark2 = $('#remark2').val(); 
	    var remark3 = $('#remark3').val(); 
	
	    var json = {
	    		"activityid" : activityid,
	    		"prospectid" : prospectid,
	    		"activitydate" : activitydate,
	    		"brandid" : brandid,
	    		"brandname" : brandname,
	    		"modelid" : modelid,
	    		"modelname" : modelname,
	    		"demo" : demo,
	    		"testdrive" : testdrive,
	    		"quotation" : quotation,
	    		"linkevent" : linkevent,
	    		"remark1" : remark1,
	    		"remark2" : remark2,
	    		"remark3" : remark3
		};
	    if (json.activityid=="0"){
	        $.ajax({
	            url: "http://localhost:8080/SalesDiaryPro/activity/create",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json), 
	            success:function(data, Textstatus, jqXHR){
	                alert("Record created!");
	                window.location.href = "/SalesDiaryPro/listActivity?prospectid="+prospectid;
	            },
	            error:function(jqXhr, Textstatus){
	                alert("Create failed!"+status);
	            }
	        });    	
	    }
	    else {
	        $.ajax({
	            url: "http://localhost:8080/SalesDiaryPro/activity/update/"+activityid,
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json),
	            success:function(data, Textstatus, jqXHR){
	                alert("Record updated!");
	                window.location.href = "/SalesDiaryPro/listActivity?prospectid="+prospectid;
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