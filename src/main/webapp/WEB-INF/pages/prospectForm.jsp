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
<body>
	<jsp:include page="_userNavigation.jsp" />
	<div id="main">
	   	<input type="hidden" value="${base}" name="base" id="base"/>		
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
						<form:form action="" method="post" modelAttribute="prospect" class='form-horizontal form-wizard'>
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
		
		var base = $('#base').val();
    	if (window.location.protocol === 'https:') {
    	    base = base.replace("http", "https");
    	}	    	

    	var prospectid = $('#prospectid').val(); 
	    var firstname = $('#firstname').val(); 
	    var lastname = $('#lastname').val(); 
	    var userid = $('#userid').val(); 
	    var source = $('#source').val(); 
	    var haddress = $('#haddress').val(); 
	    var hzipcode = $('#hzipcode').val(); 
	    var hcity = $('#hcity').val(); 
	    var hstate = $('#hstate').val(); 
	    var hcountry = $('#hcountry').val(); 
	    var mobile = $('#mobile').val(); 
	    var htelno = $('#htelno').val(); 
	    var waddress = $('#waddress').val(); 
	    var wzipcode = $('#wzipcode').val(); 
	    var wcity = $('#wcity').val(); 
	    var wstate = $('#wstate').val(); 
	    var wcountry = $('#wcountry').val(); 
	    var wtelno = $('#wtelno').val(); 
	    var occupation = $('#occupation').val(); 
	    var age = $('#age').val(); 
	    var gender = $('#gender').val(); 
	    var income = $('#income').val(); 
	    var email = $('#email').val(); 
	
	    var json = {
	    		"prospectid" : prospectid,
	    		"firstname" : firstname,
	    		"lastname" : lastname,
	    		"userid" : userid,
	    		"source" : source,
	    		"haddress" : haddress,
	    		"hzipcode" : hzipcode,
	    		"hcity" : hcity,
	    		"hstate" : hstate,
	    		"hcountry" : hcountry,
	    		"mobile" : mobile,
	    		"htelno" : htelno,
	    		"waddress" : waddress,
	    		"wzipcode" : wzipcode,
	    		"wcity" : wcity,
	    		"wstate" : wstate,
	    		"wcountry" : wcountry,
	    		"wtelno" : wtelno,
	    		"occupation" : occupation,
	    		"age" : age,
	    		"gender" : gender,
	    		"income" : income,
	    		"email" : email
		};
	    if (json.prospectid=="0"){
	        $.ajax({
	            url: base+"/prospect/create",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json), 
	            success:function(data, Textstatus, jqXHR){
	                alert("Record created!");
	                window.location.href = base+"/listProspect";
	            },
	            error:function(jqXhr, Textstatus){
	                alert("Create failed!");
	            }
	        });    	
	    }
	    else {
	        $.ajax({
	            url: base+"/prospect/update/"+prospectid,
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json),
	            success:function(data, Textstatus, jqXHR){
	                alert("Record updated!");
	                window.location.href = base+"/listProspect";
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