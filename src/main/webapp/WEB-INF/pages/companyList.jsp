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
	<jsp:include page="_deNavigation.jsp" />
	<div id="main" class="container-fluid">
	   	<input type="hidden" value="${base}" name="base" id="base"/>	
		<div align="center">
			<h5><a href="addCompany">Add New</a></h5>
			<div class="box-content nopadding">
				<table class="table table-hover table-nomargin table-colored-header">
		        <tr>
		            <th>Company Name</th>
		            <th>Registration No</th>
		            <th>Person In Charge</th>
		            <th>Telephone</th>
		            <th>Action</th>
		        </tr>
	            <c:forEach var="Company" items="${listCompany}" varStatus="status">
					<tr>
					    <td>${Company.companyname}</td>
					    <td>${Company.regno}</td>
					    <td>${Company.mdname}</td>
					    <td>${Company.telephone}</td>
					    <td>
							<button class="btn btn-small" onclick="window.location='editCompany?companyid=${Company.companyid}';" >
						    	<i class="fa fa-edit"></i></button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="btn btn-small" onclick="deleteCompany(${Company.companyid})">
								<i class="fa fa-trash-o"></i></button>
					    </td>                             
					</tr>
				</c:forEach>             
				</table>
			</div>
		</div>	
	</div>
	<script>
	    function deleteCompany(companyid) {
			var base = $('#base').val();
	    	if (window.location.protocol === 'https:') {
	    	    base = base.replace("http", "https");
	    	}	    	
	    	jQuery.ajax({
	            type: "DELETE",
	            url: base+"/company/delete/"+companyid,
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