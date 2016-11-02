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
	<c:if test="${role == 'SA'}">
		<jsp:include page="_saNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'MD'}">
		<jsp:include page="_mdNavigation.jsp" />
	</c:if>
	<div id="main" class="container-fluid">
	   	<input type="hidden" value="${base}" name="base" id="base"/>	
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listClosingPeriod">Closing</a>
				</li>
			</ul>
		</div>
		<div class="row">
			<div class="span12">
				<div class="box">
					<div class="box-content">
			        <form:form action="" method="post" modelAttribute="closingPeriod" class='form-horizontal form-wizard'>
		            <form:hidden path="id"/>
		            <form:hidden path="companyid"/>
					<div class="form-group">
						<label for="controlyear" class="control-label col-sm-2">Control Year</label>
						<div class="col-sm-2">
							<form:select type="text" path="controlyear" name="controlyear" id="controlyear" items="${yearlist}" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="controlmonth" class="control-label col-sm-2">Control Month</label>
						<div class="col-sm-2">
							<form:select type="text" path="controlmonth" name="controlmonth" id="controlmonth" items="${monthlist}" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="opendate" class="control-label col-sm-2">Opening Date</label>
						<div class="col-sm-3">
							<form:input type="date" path="opendate" name="opendate" id="opendate" class="form-control" />
						</div>
					</div>					
					<div class="form-group">
						<label for="closedate" class="control-label col-sm-2">Closing Date</label>
						<div class="col-sm-3">
							<form:input type="date" path="closedate" name="closedate" id="closedate" class="form-control" />
						</div>
					</div>					
					<div class="form-actions">
						<input type="reset" class="btn" onclick="location.href='listClosingPeriod'" value="Back" id="back">						
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
		
		var id = $('#id').val(); 
		var companyid = $('#companyid').val(); 
	    var controlyear = $('#controlyear').val(); 
	    var controlmonth = $('#controlmonth').val(); 
	    var opendate = $('#opendate').val(); 
	    var closedate = $('#closedate').val(); 
	
	    var json = {
	    		"id" : id,
	    		"companyid" : companyid,
	    		"controlyear" : controlyear,
	    		"controlmonth" : controlmonth,
	    		"opendate" : opendate,
	    		"closedate" : closedate
		};
	    if (json.id=="0"){
	        $.ajax({
	            url: $('#base').val()+"/closingperiod/create",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json), 
	            success:function(data, Textstatus, jqXHR){
	                alert("Record created!");
	                window.location.href = $('#base').val()+"/listClosingPeriod";
	            },
	            error:function(jqXhr, Textstatus){
	                alert("Create failed!");
	            }
	        });    	
	    }
	    else {
	        $.ajax({
	            url: $('#base').val()+"/closingperiod/update/"+id,
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json),
	            success:function(data, Textstatus, jqXHR){
	                alert("Record updated!");
	                window.location.href = $('#base').val()+"/listClosingPeriod";
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