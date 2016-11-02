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
        <input type="hidden" value="userProfile" name="userProfile" /> 
   	    <div align="center">
       	    <h5><a href="addProspect?userid=${userProfile.userid}">Add New</a></h5>
			<div class="box-content nopadding">
				<table class="table table-hover table-nomargin table-colored-header">
				<tr>
				    <th>Prospect Name</th>
				    <th>Mobile</th>
				    <th>Action</th>
				</tr>
				<c:forEach var="prospect" items="${listProspect}" varStatus="status">
					<tr>
					    <td>${prospect.firstname} ${prospect.lastname}</td>
						<td>${prospect.mobile}</td>
						<td>
							<button class="btn btn-small" onclick="window.location='editProspect?prospectid=${prospect.prospectid}';" >
						    	<i class="fa fa-edit"></i></button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="btn btn-small" onclick="deleteProspect(${prospect.prospectid})">
								<i class="fa fa-trash-o"></i></button>
							&nbsp;&nbsp;&nbsp;&nbsp;					    
							<a href="listRequest?prospectid=${prospect.prospectid}">Request</a>				
							&nbsp;&nbsp;&nbsp;&nbsp;					    
							<a href="listActivity?prospectid=${prospect.prospectid}">Activity</a>				
					    </td>		                 
					</tr>
				</c:forEach>             
				</table>
			</div>
        </div>
	</div>
	<script>
	    function deleteProspect(prospectid) {
	    	jQuery.ajax({
	            type: "DELETE",
	            url: $('#base').val()+"/prospect/delete/"+prospectid,
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