<!DOCTYPE HTML> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_saNavigation.jsp" />
 	<div id="main" class="container-fluid">
        <input type="hidden" value="company" name="company" /> 
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listBrand?companyid=${company.companyid}">Brand</a>
				</li>
			</ul>
		</div>
        <div align="center">
			<h5><a href="addBrand?companyid=${company.companyid}">Add New</a></h5>
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
						    <a href="editBrand?brandid=${brand.brandid}">Edit</a>
							&nbsp;&nbsp;&nbsp;&nbsp;					    
							<a href="deleteBrand?brandid=${brand.brandid}">Delete</a>
							&nbsp;&nbsp;&nbsp;&nbsp;					    
							<a href="listModel?brandid=${brand.brandid}">Model</a>				
					    </td>		                 
					</tr>
				</c:forEach>             
				</table>
			</div>
		</div>
	</div>
</body>
</html>