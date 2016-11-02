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
					<a href="listPeriod">Closing</a>
				</li>
			</ul>
		</div>
        <br>
        <div align="center">
			<c:if test="${role == 'SA'}">
				<h5><a href="addClosingPeriod">Add New</a></h5>
			</c:if>
			<div class="box-content nopadding">
				<table class="table table-hover table-nomargin table-colored-header">
				<tr>
				    <th>Control Year</th>
				    <th>Control Month</th>
				    <th>Opening Date</th>
				    <th>Closing Date</th>
				    <th>Action</th>
				</tr>
				<c:forEach var="closingPeriod" items="${listClosingPeriod}" varStatus="status">
					<tr>
					    <td>${closingPeriod.controlyear}</td>
					    <td>${closingPeriod.controlmonth}</td>
					    <td>${closingPeriod.opendate}</td>
					    <td>${closingPeriod.closedate}</td>
						<td>
							<button class="btn btn-small" onclick="window.location='editClosingPeriod?id=${closingPeriod.id}';" >
						    	<i class="fa fa-edit"></i></button>
							<c:if test="${role == 'SA'}">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="btn btn-small" onclick="deleteClosingPeriod(${closingPeriod.id})">
									<i class="fa fa-trash-o"></i></button>
							</c:if>
					    </td>		                 
					</tr>
				</c:forEach>             
				</table>
			</div>
		</div>
	</div>
	<script>
	    function deleteClosingPeriod(id) {
			var base = $('#base').val();
	    	if (window.location.protocol === 'https:') {
	    	    base = base.replace("http", "https");
	    	}	    	

	    	jQuery.ajax({
	            type: "DELETE",
	            url: base+"/closingperiod/delete/"+id,
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