<!DOCTYPE HTML">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}"/>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<c:if test="${role == 'MD'}">
		<jsp:include page="_mdNavigation.jsp" />
	</c:if>   
	<c:if test="${role == 'MA'}">
		<jsp:include page="_maNavigation.jsp" />
	</c:if>   
	<c:if test="${role == 'USER'}">
		<jsp:include page="_userNavigation.jsp" />
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
					<a href="listReview">Review</a>
				</li>
			</ul>
		</div>
		<div class="row">
			<div class="span12">
				<div class="box">
					<div class="box-content">
				        <form:form action="" method="post" modelAttribute="review" class='form-horizontal form-wizard'>
			            <form:hidden path="reviewid"/>
			   			<input type="hidden" value="userid" name="userid" /> 
			   			<input type="hidden" value="targetid" name="targetid" /> 
			   			<input type="hidden" value="teamtargetid" name="teamtargetid" /> 
			   			<input type="hidden" value="reviewby" name="reviewby" /> 				   			
						<div class="form-group">
							<label for="username" class="control-label col-sm-2">Member</label>
							<div class="col-sm-2">
								<form:select type="text" path="username" name="username" id="username"  items="${userlist}" class="form-control" data-rule-required="true" />
							</div>
						</div>
						
						<div class="box box-bordered">
							<div class="box-title">
								<h4>
									<i class="fa fa-bars"></i>
									Previous Reviews
								</h4>
							</div>
							<div id="prevreview" class="box-content">
				                <c:forEach var="prevreview" items="${prevreview}" varStatus="status">
									<h5>Date : ${prevreview.reviewdate}</h5>
									<h5>Prospect : ${prevreview.prospect}</h5>
									<h5>Test Drive : ${prevreview.testdrive}</h5>
									<h5>Closed : ${prevreview.closed}</h5>
									<h5>Minute : ${prevreview.minute}</h5>
									<hr/>
								</c:forEach>
							</div>
						</div>						
						<br>
						<div class="form-group">
							<label for="reviewdate" class="control-label col-sm-2">Review Date</label>
							<div class="col-sm-2">
								<form:input type="date" path="reviewdate" name="reviewdate" id="reviewdate" class="form-control" data-rule-required="true" />
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
						<div class="form-group">
							<label for="minute" class="control-label col-sm-2">Minute</label>
							<div class="col-sm-10">
								<form:input type="text" path="minute" name="minute" id="minute" class="form-control" />
							</div>
						</div>
						<div class="form-actions">
							<input type="reset" class="btn" onclick="location.href='listRequest?userid=${userid}&prospectid=${request.prospectid}'" value="Back" id="back">
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

    	var reviewid = $('#reviewid').val(); 
	    var userid = $('#userid').val(); 
	    var username = $('#username').val(); 
	    var targetid = $('#targetid').val();
	    var teamtargetid = $('#teamtargetid').val();	    
	    var reviewdate = $('#reviewdate').val(); 
	    var prospect = $('#prospect').val(); 
	    var testdrive = $('#testdrive').val(); 
	    var closed = $('#closed').val(); 
	    var minute = $('#minute').val(); 
	    var reviewby = $('#reviewby').val(); 
	
	    var json = {
	    		"reviewid" : reviewid,
	    		"userid" : userid,
	    		"username" : username,
	    		"targetid" : targetid,
	    		"teamtargetid" : teamtargetid,
	    		"reviewdate" : reviewdate,
	    		"prospect" : prospect,
	    		"testdrive" : testdrive,
	    		"closed" : closed,
	    		"minute" : minute,
	    		"reviewby" : reviewby,
	    		"status" : status
		};
	    if (json.reviewid=="0"){
	        $.ajax({
	            url: base+"/review/create",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json), 
	            success:function(data, Textstatus, jqXHR){
	                alert("Record created!");
	                window.location.href = base+"/listReview";
	            },
	            error:function(jqXhr, Textstatus){
	                alert("Create failed!");
	            }
	        });    	
	    }
	    else {
	        $.ajax({
	            url: base+"/review/update/"+reviewid,
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json),
	            success:function(data, Textstatus, jqXHR){
	                alert("Record updated!");
	                window.location.href = base+"/listReview";
	            },
	            error:function(jqXhr, Textstatus){
	                alert("Update failed!");
	            }
	        });
	    }
	    return true;
	})
	
	document.getElementById('reviewdate').valueAsDate = new Date();
	
	</script>		
</body>
</html>