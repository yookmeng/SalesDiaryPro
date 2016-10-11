<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
		<div class="row">
			<div class="span12">
				<div class="box">
					<div class="box-content">
			        <form:form action="saveBrand" method="post" modelAttribute="brand" class='form-horizontal form-wizard'>
		            <form:hidden path="companyid"/>        
		            <form:hidden path="brandid"/>
					<div class="form-group">
						<label for="brandname" class="control-label col-sm-2">Brand Name</label>
						<div class="col-sm-5">
							<form:input type="text" path="brandname" name="brandname" id="brandname" class="form-control" />
						</div>
					</div>
					<div class="form-actions">
						<input type="reset" class="btn" onclick="location.href='listBrand?companyid=${brand.companyid}'" value="Back" id="back">						
						<input type="submit" class="btn btn-primary" value="Save">
					</div>
					</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>