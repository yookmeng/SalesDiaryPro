<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_userNavigation.jsp" />
	<div id="main">
		<div class="container-fluid">
		    <input type="hidden" value="userProfile" name="userProfile" /> 
		    <input type="hidden" value="prospect" name="prospect" /> 
			<div class="breadcrumbs">
				<ul>
					<li>
						<a href="home">Home</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="listProspect">Prospect</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="listRequest?prospectid=${prospect.prospectid}">Request</a>
					</li>
				</ul>
			</div>
			<div>
				<h4>${prospect.firstname} ${prospect.lastname}</h4>
			</div>
		    <div align="center">
	        	<h5><a href="addRequest?prospectid=${prospect.prospectid}">Add New</a></h5>
				<div class="box-content nopadding">
					<table class="table table-hover table-nomargin table-colored-header">
					<tr>
					    <th>Request Date</th>
					    <th>Model</th>
					    <th>Status</th>
					    <th>Action</th>
					</tr>
					<c:forEach var="request" items="${listRequest}" varStatus="status">
						<tr>
						    <td>${request.requestdate}</td>
							<td>${request.modelname}</td>
							<td>${request.status}</td>
							<td>
							    <a href="editRequest?requestid=${request.requestid}">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp;					    
								<a href="deleteRequest?requestid=${request.requestid}">Delete</a>
						    </td>		                 
						</tr>
					</c:forEach>             
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>