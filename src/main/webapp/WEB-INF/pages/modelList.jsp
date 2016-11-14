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
	<jsp:include page="_saNavigation.jsp" />
 	<div id="main" class="container-fluid">
	   	<input type="hidden" value="${base}" name="base" id="base"/>	
		<input type="hidden" value="company" name="company" /> 
		<input type="hidden" value="brand" name="brand" /> 
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listBrand?companyid=${company.companyid}">Brand</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listModel?brandid=${brand.brandid}">Model</a>
				</li>
			</ul>
		</div>
        <div align="center">
   	        <h5><a href="addModel?brandid=${brand.brandid}">Add New</a></h5>
			<div class="box-content nopadding">
				<table class="table table-hover table-nomargin table-colored-header">
				<tr>
				    <th>Model Name</th>
				    <th>Price</th>
				    <th>Commission</th>
				    <th>Action</th>
				</tr>
				<c:forEach var="model" items="${listModel}" varStatus="status">
					<tr>
					    <td>${model.modelname}</td>
					    <td><fmt:formatNumber value="${model.price}"/></td>
					    <td><fmt:formatNumber value="${model.commission}"/></td>
						<td>
							<button class="btn btn-small" onclick="window.location='editModel?modelid=${model.modelid}';" >
						    	<i class="fa fa-edit"></i></button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="btn btn-small" onclick="deleteModel(${model.modelid})">
								<i class="fa fa-trash-o"></i></button>
					    </td>		                 
					</tr>
				</c:forEach>             
				</table>
			</div>
        </div>
	</div>
	<script>
	    function deleteModel(modelid) {
			var base = $('#base').val();
	    	if (window.location.protocol === 'https:') {
	    	    base = base.replace("http", "https");
	    	}	    	

	    	jQuery.ajax({
		    	type: "DELETE",
	            url: base+"/model/delete/"+modelid,
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