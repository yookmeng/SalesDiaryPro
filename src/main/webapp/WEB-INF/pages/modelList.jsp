<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_saNavigation.jsp" />
 	<div id="main" class="container-fluid">
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
				    <th>Action</th>
				</tr>
				<c:forEach var="model" items="${listModel}" varStatus="status">
					<tr>
					    <td>${model.modelname}</td>
					    <td><fmt:formatNumber value="${model.price}"/></td>
						<td>
						    <a href="editModel?modelid=${model.modelid}">Edit</a>
							&nbsp;&nbsp;&nbsp;&nbsp;					    
							<a href="deleteModel?modelid=${model.modelid}">Delete</a>
					    </td>		                 
					</tr>
				</c:forEach>             
				</table>
			</div>
        </div>
	</div>
</body>
</html>