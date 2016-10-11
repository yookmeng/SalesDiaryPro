<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_deNavigation.jsp" />
	<div id="main" class="container-fluid">
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
					        <a href="editCompany?companyid=${Company.companyid}">Edit</a>
					        <a href="deleteCompany?companyid=${Company.companyid}">Delete</a>
					    </td>                             
					</tr>
				</c:forEach>             
				</table>
			</div>
		</div>	
	</div>
</body>
</html>