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
	<c:if test="${role == 'MD'}">
		<jsp:include page="_mdNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'MA'}">
		<jsp:include page="_maNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'TL'}">
		<jsp:include page="_tlNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'USER'}">
		<jsp:include page="_userNavigation.jsp" />
	</c:if>
	<div id="main">
	   	<input type="hidden" value="${base}" name="base" id="base"/>	
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listProspects">Prospect</a>
				</li>
			</ul>
		</div>
        <input type="hidden" value="userProfile" name="userProfile" /> 
   	    <div align="center">
   	    	<a href="listProspects">All</a>
			<a href="listProspect?status=Hot">Hot</a>
			<a href="listProspect?status=Cold">Cold</a>
			<a href="listProspect?status=Closed">Closed</a>
			<a href="listProspect?status=Warm">Warm</a>
			<a href="listProspect?status=Lost">Lost</a>
			<c:if test="${role == 'USER'}">
   	    	<h5><a href="addProspect?userid=${userProfile.userid}" class='btn'>
				<i class="fa fa-plus-circle"></i>New Prospect</a></h5>
			</c:if>
		</div>
		<div class="box-content nopadding">
			<table class="table table-hover table-nomargin table-colored-header">
			<tr>
			    <th>Prospect</th>
			    <th>Status</th>
			    <th>Action</th>
			</tr>
			<c:forEach var="prospect" items="${listProspect}" varStatus="status">
				<tr>
				    <td>${prospect.firstname} (${prospect.mobile})</td>
					<td>${prospect.status}</td>
					<td>
						<c:if test="${role == 'USER'}">
							<button class="btn btn-small" title="Edit" onclick="window.location='editProspect?prospectid=${prospect.prospectid}';" >
						    	<i class="fa fa-edit"></i></button>
							&nbsp;
							<button class="btn btn-small" title="Delete" onclick="deleteProspect(${prospect.prospectid})">
								<i class="fa fa-trash-o"></i></button>
							&nbsp;					    
						</c:if>
						<a href="listActivity?prospectid=${prospect.prospectid}" class='btn' title="Activities">
										<i class="fa fa-th-list"></i></a>						
				    </td>		                 
				</tr>
			</c:forEach>             
			</table>
		</div>
	</div>
	<script>
	    function deleteProspect(prospectid) {
			var base = $('#base').val();
	    	if (window.location.protocol === 'https:') {
	    	    base = base.replace("http", "https");
	    	}	    	

	    	jQuery.ajax({
	            type: "DELETE",
	            url: base+"/prospect/delete/"+prospectid,
	            contentType: "application/json",
	            data: "",
	            dataType: "",
	            success: function (data, status, jqXHR) {
					location.replace(location);
	            },	        
	            error: function (jqXHR, status) {
	            }
	        });	
	    }
	</script>	
</body>
</html>