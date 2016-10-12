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
						<a href="listActivity?prospectid=${prospect.prospectid}">Activity</a>
					</li>
				</ul>
			</div>
			<div>
				<h4>${prospect.firstname} ${prospect.lastname}</h4>
			</div>
	        <div align="center">
	            <h5><a href="addActivity?prospectid=${prospect.prospectid}">Add New</a></h5>
				<div class="box-content nopadding">
					<table class="table table-hover table-nomargin table-colored-header">
					<tr>
					    <th>Date</th>
					    <th>Model</th>
					    <th>Remark</th>
					    <th>Action</th>
					</tr>
					<c:forEach var="activity" items="${listActivity}" varStatus="status">
						<tr>
						    <td>${activity.activitydate}</td>
							<td>${activity.modelname}</td>
							<td>${activity.remark1}</td>
							<td>
							    <a href="editActivity?activityid=${activity.activityid}">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp;					    
								<a href="deleteActivity?activityid=${activity.activityid}">Delete</a>
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