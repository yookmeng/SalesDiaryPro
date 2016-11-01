<!DOCTYPE HTML> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_saNavigation.jsp" />
 	<div class="container-fluid">
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listBrand">Brand</a>
				</li>
			</ul>
		</div>
        <div align="center">
			<h5><a href="addBrand">Add New</a></h5>
			<div class="box-content nopadding">
				<table class="table table-hover table-nomargin table-colored-header">
				<tr>
				    <th>Brand Name</th>
				    <th>Action</th>
				</tr>
				<c:forEach var="brand" items="${listBrand}" varStatus="status">
					<tr>
					    <td>${brand.brandname}</td>
						<td>
							<button class="btn btn-small" onclick="window.location='editBrand?brandid=${brand.brandid}';" >
						    	<i class="fa fa-edit"></i></button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="btn btn-small" onclick="deleteBrand(${brand.brandid})">
								<i class="fa fa-trash-o"></i></button>
							&nbsp;&nbsp;&nbsp;&nbsp;					    
							<a href="listModel?brandid=${brand.brandid}">Model</a>				
					    </td>		                 
					</tr>
				</c:forEach>             
				</table>
			</div>
		</div>
	</div>
	<script>
	    function deleteBrand(brandid) {
	    	jQuery.ajax({
	            type: "DELETE",
	            url: "http://localhost:8080/SalesDiaryPro/brand/delete/"+brandid,
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