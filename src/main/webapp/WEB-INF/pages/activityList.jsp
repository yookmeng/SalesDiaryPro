<!DOCTYPE HTML>
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
		<div class="container-fluid">
		   	<input type="hidden" value="${base}" name="base" id="base"/>	
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
			<div>
				<h4>${prospect.firstname} ${prospect.lastname}</h4>
			</div>
	        <div align="center">
	            <h5><a href="addActivity?prospectid=${prospect.prospectid}">Add New</a></h5>
				<div class="box-content nopadding">
					<table class="table table-hover table-nomargin table-colored-header">
					<tr>
					    <th>Date</th>
					    <th>Model</th>
					    <th>Remark</th>
					    <th>Action</th>
					</tr>
					<c:forEach var="activity" items="${listActivity}" varStatus="status">
						<tr>
						    <td>${activity.activitydate}</td>
							<td>${activity.modelname}</td>
							<td>${activity.remark1}</td>
							<td>
								<button class="btn btn-small" onclick="window.location='editActivity?activityid=${activity.activityid}';" >
							    	<i class="fa fa-edit"></i></button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="btn btn-small" onclick="deleteActivity(${activity.activityid})">
									<i class="fa fa-trash-o"></i></button>
						    </td>		                 
						</tr>
					</c:forEach>             
					</table>
				</div>
			</div>
		</div>
	</div>
	<script>
	    function deleteActivity(activityid) {
	    	jQuery.ajax({
	            type: "DELETE",
	            url: $('#base').val()+"/activity/delete/"+activityid,
	            contentType: "application/json",
	            data: "",
	            dataType: "",
	            success: function (data, status, jqXHR) {
	                alert("record deleted!");	                
					location.replace(location);
	            },	        
	            error: function (jqXHR, status) {
	                alert("delete failed!");
	            }
	        });	
	    }
	</script>	
</body>
</html>