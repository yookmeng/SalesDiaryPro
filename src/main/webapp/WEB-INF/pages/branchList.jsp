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
				<a href="listBranch?companyid=${company.companyid}">Branch</a>
			</li>
		</ul>
	</div>
	<div align="center">	
		<h5><a href="addBranch?companyid=${company.companyid}">Add New</a></h5>
		<div class="box-content nopadding">
			<table class="table table-hover table-nomargin table-colored-header">
			<tr>
			    <th>Branch Name</th>
			    <th>Registration No</th>
			    <th>Manager Name</th>
			    <th>Action</th>
			</tr>
			<c:forEach var="branch" items="${listBranch}" varStatus="status">
				<tr>
				    <td>${branch.branchname}</td>
					<td>${branch.regno}</td>
					<td>${branch.maname}</td>
					<td>
					    <a href="editBranch?branchid=${branch.branchid}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp;					    
						<a href="deleteBranch?branchid=${branch.branchid}">Delete</a>
						&nbsp;&nbsp;&nbsp;&nbsp;					    
						<a href="listTeam?branchid=${branch.branchid}">Team</a>				
				    </td>		                 
				</tr>
			</c:forEach>             
			</table>
		</div>
	</div>
</div>
</body>
</html>