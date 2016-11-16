<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}"/>
<html>
	<jsp:include page="_menu.jsp" />
	<!-- Wizard -->
	<script src="resources/js/plugins/wizard/jquery.form.wizard.min.js"></script>
<body>
	<jsp:include page="_userNavigation.jsp" />
	<div id="main">
	   	<input type="hidden" value="${base}" name="base" id="base"/>		
		<br><br>
		<div class="row">
			<div class="span12">
				<div class="box">			
					<div class="box-content nopadding">
					<form:form id="entry-form" action="" method="post" modelAttribute="questionaire" class='form-horizontal form-wizard'>
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
								<form:select name="brandname" path="brandname" id="brandname" items="${brandlist}" />
							</div>
						</div>
						<div class="form-group">
							<label for="text" class="control-label col-sm-2">Model</label>
							<div class="col-sm-5">
								<form:select name="modelname" path="modelname" id="modelname" items="${modellist}" />
							</div>
						</div>
					</div>
					<div class="step" id="thirdStep">
						<div class="form-group" align="center">
							<label for="demo" class="control-label col-sm-2">Presentation</label>
							<div class="col-sm-1">
								<form:checkbox path="demo" id="demo" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="step" id="fourthStep">
						<div class="form-group" align="center">
							<label for="testdrive" class="control-label col-sm-2">Test Drive</label>
							<div class="col-sm-1">
								<form:checkbox path="testdrive" id="testdrive" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="step" id="fifthStep">
						<label for="quotation" class="control-label col-sm-2">Quotation</label>
						<div class="form-group" align="center">
							<div class="col-sm-1">
								<form:checkbox path="quotation" id="quotation" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="step" id="sixthStep">
						<div class="form-group" align="center">
							<label for="currentmodel" class="control-label col-sm-2">Trade In</label>
							<div class="col-sm-1">
								<form:checkbox path="tradein" id="tradein" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<label for="currentbrand" class="control-label col-sm-2">Brand</label>
							<div class="col-sm-5">
								<form:input type="text" path="currentbrand" name="currentbrand" id="currentbrand" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="currentmodel" class="control-label col-sm-2">Model</label>
							<div class="col-sm-5">
								<form:input type="text" path="currentmodel" name="currentmodel" id="currentmodel" class="form-control" />
							</div>
						</div>
					</div>
					<div align="center" class="form-actions">
						<input type="reset" class="btn" value="Back" id="back">
						<input type="submit" class="btn btn-primary" value="Save" id="next">
						<input type="button" class="btn" value="Done" id="done">
					</div>
					</form:form>
				</div>
				</div>
			</div>
		</div>
	</div>	
	<script>
	$("#entry-form").validate({ 
		rules: { 
			prospectname: "required",
			mobile: "required"
		}
	});
	
	$('#done').click(function (e) {
		if(!$('#entry-form').valid()) return;
	
		var base = $('#base').val();
	   	if (window.location.protocol === 'https:') {
	   	    base = base.replace("http", "https");
	   	}	    	
	
	   	var userid = $('#userid').val(); 
	    var prospectname = $('#prospectname').val(); 
	    var mobile = $('#mobile').val(); 
	    var brandname = $('#brandname').val(); 
	    var modelname = $('#modelname').val(); 
	    var demo = $('#demo').prop('checked');
	    var testdrive = $('#testdrive').prop('checked');
	    var quotation = $('#quotation').prop('checked');
	    var tradein = $('#tradein').prop('checked');
	    var currentbrand = $('#currentbrand').val(); 
	    var currentmodel = $('#currentmodel').val(); 
	
	    var json = {
	    		"userid" : userid,
	    		"prospectname" : prospectname,
		   		"mobile" : mobile,
	    		"brandname" : brandname,
	    		"modelname" : modelname,
	    		"demo" : demo,
	    		"testdrive" : testdrive,
	    		"quotation" : quotation,
	    		"tradein" : tradein,
	    		"currentbrand" : currentbrand,
	    		"currentmodel" : currentmodel
		};
		$.ajax({
		    url: base+"/questionaire",
		    type: 'POST',
		    contentType: "application/json",
		    dataType: "json",
		    data: JSON.stringify(json), 
		    success:function(data, Textstatus, jqXHR){
		        window.location.href = base+"/home";
		    },
		    error:function(jqXhr, Textstatus){
		    }
		});    	
	    return true;
	});
	
	$('#next').click(function (e) {
		e.preventDefault(); // <------------------ stop default behaviour of button
	
		if ($('#next').val()=="Submit"){
			if(!$('#entry-form').valid()) return;
			
			var base = $('#base').val();
	    	if (window.location.protocol === 'https:') {
	    	    base = base.replace("http", "https");
	    	}	    	
	
	    	var userid = $('#userid').val(); 
		    var prospectname = $('#prospectname').val(); 
		    var mobile = $('#mobile').val(); 
		    var brandname = $('#brandname').val(); 
		    var modelname = $('#modelname').val(); 
		    var demo = $('#demo').prop('checked');
		    var testdrive = $('#testdrive').prop('checked');
		    var quotation = $('#quotation').prop('checked');
		    var tradein = $('#tradein').prop('checked');
		    var currentbrand = $('#currentbrand').val(); 
		    var currentmodel = $('#currentmodel').val(); 
		
		    var json = {
		    		"userid" : userid,
		    		"prospectname" : prospectname,
			   		"mobile" : mobile,
		    		"brandname" : brandname,
		    		"modelname" : modelname,
		    		"demo" : demo,
		    		"testdrive" : testdrive,
		    		"quotation" : quotation,
		    		"tradein" : tradein,
		    		"currentbrand" : currentbrand,
		    		"currentmodel" : currentmodel
			};
			$.ajax({
			    url: base+"/questionaire",
			    type: 'POST',
			    contentType: "application/json",
			    dataType: "json",
			    data: JSON.stringify(json), 
			    success:function(data, Textstatus, jqXHR){
			        window.location.href = base+"/home";
			    },
			    error:function(jqXhr, Textstatus){
			    }
			});    	
		    return true;
		}
	});
	</script>
</body>
</html>