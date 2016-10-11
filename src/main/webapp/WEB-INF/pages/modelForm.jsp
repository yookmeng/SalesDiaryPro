<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
		<div class="row">
			<div class="span12">
				<div class="box">
					<div class="box-content">
			        <form:form action="saveModel" method="post" modelAttribute="model" class='form-horizontal form-wizard'>
		            <form:hidden path="brandid"/>        
		            <form:hidden path="modelid"/>
					<div class="form-group">
						<label for="modelname" class="control-label col-sm-2">Model Name</label>
						<div class="col-sm-5">
							<form:input type="text" path="modelname" name="modelname" id="modelname" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="price" class="control-label col-sm-2">Price</label>
						<div class="col-sm-5">
							<form:input type="text" path="price" name="price" id="price" class="form-control" />
						</div>
					</div>
					<div class="form-actions">
						<input type="reset" class="btn" onclick="location.href='listModel?brandid=${model.brandid}'" value="Back" id="back">						
						<input type="submit" class="btn btn-primary" value="Save">
					</div>
					</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	   var dataJSON = {model : { modelid : "100",
			   			modelname : "test model",
			   			brandid : "1",
			   			price : "100000"}};

	   $.ajax({
           type: 'POST',
           url: 'model/create',
           data: JSON.stringify(dataJSON),
           contentType: 'application/x-www-form-urlencoded',
       });
	</script>
</body>
</html>