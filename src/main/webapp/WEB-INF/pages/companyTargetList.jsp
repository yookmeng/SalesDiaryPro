<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}"/>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_mdNavigation.jsp" />
 	<div id="main" class="container-fluid">
	   	<input type="hidden" value="${base}" name="base" id="base"/>	
        <input type="hidden" value="company" name="company" /> 
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listCompanyTarget">Target</a>
				</li>
			</ul>
		</div>
        <div align="center">
            <h5><a href="addCompanyTarget">Add New</a></h5>
			<div class="box-content nopadding">
				<table class="table table-hover table-nomargin table-colored-header">
				<tr>
	                <th>Period</th>
	                <th>Prospect</th>
	                <th>Test Drive</th>
	                <th>Closed</th>
	                <th>Action</th>
				</tr>
				<c:forEach var="companyTarget" items="${listTarget}" varStatus="status">
					<tr>
	                    <td>${companyTarget.displayperiod}</td>
	                    <td><fmt:formatNumber value="${companyTarget.prospect}"/></td>
	                    <td><fmt:formatNumber value="${companyTarget.testdrive}"/></td>
	                    <td><fmt:formatNumber value="${companyTarget.closed}"/></td>
						<td>
							<button class="btn btn-small" onclick="window.location='editCompanyTarget?targetid=${companyTarget.targetid}';" >
						    	<i class="fa fa-edit"></i></button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="btn btn-small" onclick="deleteCompanyTarget(${companyTarget.targetid})">
								<i class="fa fa-trash-o"></i></button>
	                        &nbsp;&nbsp;&nbsp;&nbsp;
	                        <a href="listBranchTarget?targetid=${companyTarget.targetid}">Branch Target</a>
					    </td>		                 
					</tr>
				</c:forEach>             
				</table>
			</div>
		</div>
	</div>
	<script>
	    function deleteCompanyTarget(targetid) {
	    	jQuery.ajax({
	            type: "DELETE",
	            url: $('#base').val()+"/companytarget/delete/"+targetid,
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