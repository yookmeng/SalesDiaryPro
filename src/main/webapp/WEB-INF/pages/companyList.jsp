<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_deNavigation.jsp" />
	<div class="container-fluid">
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
	    	jQuery.ajax({
	            type: "DELETE",
	            url: "http://localhost:8080/SalesDiaryPro/company/delete/"+companyid,
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