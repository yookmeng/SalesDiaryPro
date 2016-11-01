<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<c:if test="${role == 'SA'}">
		<jsp:include page="_saNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'DEV'}">
		<jsp:include page="_deNavigation.jsp" />
	</c:if>   
	<div class="container-fluid">
	   	<input type="hidden" value="${role}" name="role" id="role"/>
		<div class="row">
			<div class="span12">
				<div class="box">
					<div class="box-content">
			        <form:form action="" method="post" modelAttribute="company" class='form-horizontal form-wizard'>
		            <form:hidden path="companyid"/>
					<div class="form-group">
						<label for="companyname" class="control-label col-sm-2">Company Name</label>
						<div class="col-sm-5">
							<form:input type="text" path="companyname" name="companyname" id="companyname" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="regno" class="control-label col-sm-2">Registration No</label>
						<div class="col-sm-2">
							<form:input type="text" path="regno" name="regno" id="regno" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="text" class="control-label col-sm-2">Director</label>
						<div class="col-sm-2">
							<form:select name="mdname" path="mdname" id="mdname" items="${mdlist}" data-rule-required="true" />
						</div>
					</div>
					<div class="form-group">
						<label for="address" class="control-label col-sm-2">Address</label>
						<div class="col-sm-10">
							<form:input type="address" path="address" name="address" id="regno" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="zipcode" class="control-label col-sm-2">Zip Code</label>
						<div class="col-sm-3">
							<form:input type="text" name="zipcode" path="zipcode" id="zipcode" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="city" class="control-label col-sm-2">City</label>
						<div class="col-sm-3">
							<form:input type="text" path="city" name="city" id="city" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="state" class="control-label col-sm-2">State</label>
						<div class="col-sm-3">
							<form:input type="text" path="state" name="state" id="state" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="country" class="control-label col-sm-2">Country</label>
						<div class="col-sm-3">
							<form:input type="text" path="country" name="country" id="country" class="form-control" />
						</div>
					</div>								
					<div class="form-group">
						<label for="telephone" class="control-label col-sm-2">Tel. No</label>
						<div class="col-sm-3">
							<form:input type="text" path="telephone" name="telephone" id="telephone" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="fax" class="control-label col-sm-2">Fax. No</label>
						<div class="col-sm-3">
							<form:input type="text" path="fax" name="fax" id="fax" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="control-label col-sm-2">Email</label>
						<div class="col-sm-10">
							<form:input type="text" path="email" name="email" id="email" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="website" class="control-label col-sm-2">Web Site</label>
						<div class="col-sm-10">
							<form:input type="text" path="website" name="website" id="website" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="text" class="control-label col-sm-2">System Admin</label>
						<div class="col-sm-2">
							<form:select name="saname" path="saname" id="saname" items="${salist}" data-rule-required="true" />
						</div>
					</div>
					<div class="form-actions">
						<c:if test="${role == 'SA'}">
							<input type="reset" class="btn" onclick="location.href='home'" value="Back" id="back">						
						</c:if>
						<c:if test="${role == 'DEV'}">
							<input type="reset" class="btn" onclick="location.href='listCompany'" value="Back" id="back">						
						</c:if>   
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

		var companyid = $('#companyid').val(); 
	    var companyname = $('#companyname').val(); 
	    var regno = $('#regno').val(); 
	    var mdid = ""; 
	    var mdname = $('#mdname').val(); 
	    var address = $('#address').val(); 
	    var zipcode = $('#zipcode').val(); 
	    var city = $('#city').val(); 
	    var state = $('#state').val(); 
	    var country = $('#country').val(); 
	    var telephone = $('#telephone').val(); 
	    var fax = $('#fax').val(); 
	    var email = $('#email').val(); 
	    var website = $('#website').val(); 
	    var said = ""; 
	    var saname = $('#saname').val(); 

	    var json = {
	    		"companyid" : companyid,
	    		"companyname" : companyname,
	    		"regno" : regno,
	    		"mdid" : mdid,
	    		"mdname" : mdname,		
	    		"address" : address,
	    		"zipcode" : zipcode,
	    		"city" : city,
	    		"state" : state,
	    		"country" : country,
	    		"telephone" : telephone,
	    		"fax" : fax,
	    		"email" : email,
	    		"website" : website,
	    		"said" : said,
	    		"saname" : saname			    		
		};
	    if (json.companyid=="0"){
	        $.ajax({
	            url: "http://localhost:8080/SalesDiaryPro/company/create",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json), 
	            success:function(data, Textstatus, jqXHR){
	                alert("Record created!");
	                if ($('#role').val()=="DEV"){
		                window.location.href = "/SalesDiaryPro/listCompany";	                	
	                }
	                else{
		                window.location.href = "/SalesDiaryPro/home";	                	
	                }
	            },
	            error:function(jqXhr, Textstatus){
	                alert("Create failed!"+status);
	            }
	        });    	
	    }
	    else {
	        $.ajax({
	            url: "http://localhost:8080/SalesDiaryPro/company/update/"+companyid,
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json),
	            success:function(data, Textstatus, jqXHR){
	                alert("Record updated!");
	                if ($('#role').val()=="DEV"){
		                window.location.href = "/SalesDiaryPro/listCompany";	                	
	                }
	                else{
		                window.location.href = "/SalesDiaryPro/home";	                	
	                }
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