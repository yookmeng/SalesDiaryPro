<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_saNavigation.jsp" />
	<div class="container-fluid">
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listBrand">Brand</a>
				</li>
			</ul>
		</div>
		<div class="row">
			<div class="span12">
				<div class="box">
					<div class="box-content">
			        <form:form action="" method="post" modelAttribute="brand" class='form-horizontal form-wizard'>
		            <form:hidden path="companyid"/>        
		            <form:hidden path="brandid"/>
					<div class="form-group">
						<label for="brandname" class="control-label col-sm-2">Brand Name</label>
						<div class="col-sm-5">
							<form:input type="text" path="brandname" name="brandname" id="brandname" class="form-control" />
						</div>
					</div>
					<div class="form-actions">
						<input type="reset" class="btn" onclick="location.href='listBrand?companyid=${brand.companyid}'" value="Back" id="back">						
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
		
		var brandid = $('#brandid').val(); 
	    var brandname = $('#brandname').val(); 
	    var companyid = $('#companyid').val(); 
	
	    var json = {
	    		"brandid" : brandid,
	    		"brandname" : brandname,
	    		"companyid" : companyid
		};
	    if (json.brandid=="0"){
	        $.ajax({
	            url: "http://localhost:8080/SalesDiaryPro/brand/create",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json), 
	            success:function(data, Textstatus, jqXHR){
	                alert("Record created!");
	                window.location.href = "/SalesDiaryPro/listBrand";
	            },
	            error:function(jqXhr, Textstatus){
	                alert("Create failed!"+status);
	            }
	        });    	
	    }
	    else {
	        $.ajax({
	            url: "http://localhost:8080/SalesDiaryPro/brand/update/"+brandid,
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json),
	            success:function(data, Textstatus, jqXHR){
	                alert("Record updated!");
	                window.location.href = "/SalesDiaryPro/listBrand";
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