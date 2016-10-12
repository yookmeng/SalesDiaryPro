<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_userNavigation.jsp" />
	<div id="main">
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listProspect">Prospect</a>
				</li>
			</ul>
		</div>
        <input type="hidden" value="userProfile" name="userProfile" /> 
   	    <div align="center">
       	    <h5><a href="addProspect?userid=${userProfile.userid}">Add New</a></h5>
			<div class="box-content nopadding">
				<table class="table table-hover table-nomargin table-colored-header">
				<tr>
				    <th>Prospect Name</th>
				    <th>Mobile</th>
				    <th>Action</th>
				</tr>
				<c:forEach var="prospect" items="${listProspect}" varStatus="status">
					<tr>
					    <td>${prospect.firstname} ${prospect.lastname}</td>
						<td>${prospect.mobile}</td>
						<td>
						    <a href="editProspect?prospectid=${prospect.prospectid}">Edit</a>
							&nbsp;&nbsp;&nbsp;&nbsp;					    
							<a href="deleteProspect?prospectid=${prospect.prospectid}">Delete</a>
							&nbsp;&nbsp;&nbsp;&nbsp;					    
							<a href="listRequest?prospectid=${prospect.prospectid}">Request</a>				
							&nbsp;&nbsp;&nbsp;&nbsp;					    
							<a href="listActivity?prospectid=${prospect.prospectid}">Activity</a>				
					    </td>		                 
					</tr>
				</c:forEach>             
				</table>
			</div>
        </div>
	</div>
</body>
</html>