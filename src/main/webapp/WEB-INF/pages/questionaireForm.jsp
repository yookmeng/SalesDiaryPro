<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
	<jsp:include page="_menu.jsp" />
	<!-- Wizard -->
	<script src="resources/js/plugins/wizard/jquery.form.wizard.min.js"></script>
<body>
	<jsp:include page="_userNavigation.jsp" />
	<div id="main">
		<br><br>
		<div class="row">
			<div class="span12">
				<div class="box">			
					<div class="box-content nopadding">
					<form:form action="" method="post" modelAttribute="questionaire" class='form-horizontal form-wizard'>
		            <form:hidden path="userid"/>
					<div class="step" id="firstStep">
							<div class="form-group">
								<label for="prospectname" class="control-label col-sm-2">Prospect Name</label>
								<div class="col-sm-5">
									<form:input type="text" path="prospectname" name="prospectname" id="prospectname" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="mobile" class="control-label col-sm-2">Mobile</label>
								<div class="col-sm-5">
									<form:input type="text" path="mobile" name="mobile" id="mobile" class="form-control" />
								</div>
							</div>
					</div>
					<div class="step" id="secondStep">
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
					</div>
					<div class="step" id="thirdStep">
							<div class="form-group">
								<label for="currentbrand" class="control-label col-sm-2">Current Brand</label>
								<div class="col-sm-5">
									<form:input type="text" path="currentbrand" name="currentbrand" id="currentbrand" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="currentmodel" class="control-label col-sm-2">Current Model</label>
								<div class="col-sm-5">
									<form:input type="text" path="currentmodel" name="currentmodel" id="currentmodel" class="form-control" />
								</div>
							</div>
					</div>
					<div class="step" id="fourthStep">
							<label for="currentmodel" class="control-label col-sm-1">Trade In</label>
							<div class="form-group" align="center">
								<div class="col-sm-1">
									<form:checkbox path="tradein" id="tradein" class="form-control"/>
								</div>
							</div>
					</div>
					<div class="step" id="fifthStep">
							<label for="currentmodel" class="control-label col-sm-1">Test Drive</label>
							<div class="form-group" align="center">
								<div class="col-sm-1">
									<form:checkbox path="testdrive" id="testdrive" class="form-control"/>
								</div>
							</div>
					</div>
					<div align="center" class="form-actions">
						<input type="reset" class="btn" value="Back" id="back">
						<input type="submit" class="btn btn-primary" value="Save" id="next">
					</div>
					</form:form>
				</div>
				</div>
			</div>
		</div>
	</div>	
<script>
$('#next').click(function (e) {
	if ($('#next').val()=="Submit"){
		e.preventDefault(); // <------------------ stop default behaviour of button

		var userid = $('#userid').val(); 
	    var prospectname = $('#prospectname').val(); 
	    var mobile = $('#mobile').val(); 
	    var brandname = $('#brandname').val(); 
	    var modelname = $('#modelname').val(); 
	    var currentbrand = $('#currentbrand').val(); 
	    var currentmodel = $('#currentmodel').val(); 
	    var tradein = $('#tradein').prop('checked');
	    var testdrive = $('#testdrive').prop('checked');
	
	    var json = {
	    		"userid" : userid,
	    		"prospectname" : prospectname,
		   		"mobile" : mobile,
	    		"brandname" : brandname,
	    		"modelname" : modelname,
	    		"currentbrand" : currentbrand,
	    		"currentmodel" : currentmodel,
	    		"tradein" : tradein,
	    		"testdrive" : testdrive,
		};
		$.ajax({
		    url: "http://localhost:8080/SalesDiaryPro/questionaire",
		    type: 'POST',
		    contentType: "application/json",
		    dataType: "json",
		    data: JSON.stringify(json), 
		    success:function(data, Textstatus, jqXHR){
		        alert("Record created!");
		        window.location.href = "/SalesDiaryPro/home";
		    },
		    error:function(jqXhr, Textstatus){
		        alert("Create failed!"+status);
		    }
		});    	
	    return true;
	}
})
</script>
</body>
</html>