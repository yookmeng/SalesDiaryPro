<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	            url: "http://localhost:8080/SalesDiaryPro/closingperiod/create",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json), 
	            success:function(data, Textstatus, jqXHR){
	                alert("Record created!");
	                window.location.href = "/SalesDiaryPro/listClosingPeriod";
	            },
	            error:function(jqXhr, Textstatus){
	                alert("Create failed!"+status);
	            }
	        });    	
	    }
	    else {
	        $.ajax({
	            url: "http://localhost:8080/SalesDiaryPro/closingperiod/update/"+id,
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json),
	            success:function(data, Textstatus, jqXHR){
	                alert("Record updated!");
	                window.location.href = "/SalesDiaryPro/listClosingPeriod";
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