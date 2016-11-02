<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}"/>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_saNavigation.jsp" />
	<div id="main" class="container-fluid">
	   	<input type="hidden" value="${base}" name="base" id="base"/>	
	   	<input type="hidden" value="brand" name="brand" />
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listBrand">Brand</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listModel?brandid=${brand.brandid}">Model</a>
				</li>
			</ul>
		</div>
		<div class="row">
			<div class="span12">
				<div class="box">
					<div class="box-content">
			        <form:form action="" method="post" modelAttribute="model" class='form-horizontal form-wizard'>
		            <form:hidden path="brandid"/>        
		            <form:hidden path="modelid"/>
					<div class="form-group">
						<label for="modelname" class="control-label col-sm-2">Model Name</label>
						<div class="col-sm-5">
							<form:input type="text" path="modelname" name="modelname" id="modelname" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="price" class="control-label col-sm-2">Price</label>
						<div class="col-sm-5">
							<form:input type="text" path="price" name="price" id="price" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="commission" class="control-label col-sm-2">Commission</label>
						<div class="col-sm-5">
							<form:input type="text" path="commission" name="commission" id="commission" class="form-control" />
						</div>
					</div>
					<div class="form-actions">
						<input type="reset" class="btn" onclick="location.href='listModel?brandid=${model.brandid}'" value="Back" id="back">						
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

    	var brandid = $('#brandid').val(); 
	    var modelid = $('#modelid').val(); 
	    var modelname = $('#modelname').val(); 	    
	    var price = $('#price').val(); 
	    var commission = $('#commission').val(); 
	
	    var json = {
	    		"modelid" : modelid,
	    		"modelname" : modelname,	    		
	    		"brandid" : brandid,
	    		"price" : price,
	    		"commission" : commission
		};
	    if (json.modelid=="0"){
	        $.ajax({
	            url: base+"/model/create",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json), 
	            success:function(data, Textstatus, jqXHR){
	                alert("Record created!");
	                window.location.href = base+"/listModel?brandid="+brandid;
	            },
	            error:function(jqXhr, Textstatus){
	                alert("Create failed!");
	            }
	        });    	
	    }
	    else {
	        $.ajax({
	            url: base+"/model/update/"+modelid,
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json),
	            success:function(data, Textstatus, jqXHR){
	                alert("Record updated!");
	                window.location.href = base+"/listModel?brandid="+brandid;
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