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
		<input type="hidden" value="companyTarget" name="companyTarget" /> 
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listCompanyTarget">Company Target</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listBranchTarget?targetid=${companyTarget.targetid}">Branch Target</a>
				</li>					
			</ul>
		</div>
		<div>
			<h4>${companyTarget.companyname}  ${companyTarget.period}  Company Target</h4>
		</div>
        <div>
    		<h5>Prospect : ${companyTarget.prospect}</h5>
    		<h5>Test Drive : ${companyTarget.testdrive}</h5>
    		<h5>Closed : ${companyTarget.closed}</h5>    		
        </div>
        <div align="center">
            <h5><a href="addBranchTarget?targetid=${companyTarget.targetid}">Add New</a></h5>
			<div class="box-content nopadding">
				<table class="table table-hover table-nomargin table-colored-header">
	            <tr>
	                <th>Branch</th>
	                <th>Prospect</th>
	                <th>Test Drive</th>
	                <th>Closed</th>
	                <th>Action</th>
	            </tr>
	                <c:forEach var="branchTarget" items="${listTarget}" varStatus="status">
		                <tr>
		                    <td>${branchTarget.branchname}</td>
		                    <td><fmt:formatNumber value="${branchTarget.prospect}"/></td>
		                    <td><fmt:formatNumber value="${branchTarget.testdrive}"/></td>
		                    <td><fmt:formatNumber value="${branchTarget.closed}"/></td>
		                    <td>
							<button class="btn btn-small" onclick="window.location='editBranchTarget?targetid=${branchTarget.targetid}';" >
						    	<i class="fa fa-edit"></i></button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="btn btn-small" onclick="deleteBranchTarget(${branchTarget.targetid})">
								<i class="fa fa-trash-o"></i></button>
		                    </td>
		                </tr>
	                </c:forEach>             
	            </table>
	        </div>
		</div>
	</div>
	<script>
	    function deleteBranchTarget(targetid) {
			var base = $('#base').val();
	    	if (window.location.protocol === 'https:') {
	    	    base = base.replace("http", "https");
	    	}	    	

	    	jQuery.ajax({
	            type: "DELETE",
	            url: base+"/branchtarget/delete/"+targetid,
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