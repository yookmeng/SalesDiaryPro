<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<html>
<jsp:include page="_menu.jsp" />
<body>
	<c:if test="${role == 'SA'}">
		<jsp:include page="_saNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'MD'}">
		<jsp:include page="_mdNavigation.jsp" />
	</c:if>   
	<c:if test="${role == 'MA'}">
		<jsp:include page="_maNavigation.jsp" />
	</c:if>   
	<div id="main" class="container-fluid">
        <input type="hidden" value="branch" name="branch" /> 
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listBranch">Branch</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listTeam?branchid=${branch.branchid}">Team</a>
				</li>
			</ul>
		</div>
		<br>
		<div align="center">
			<c:if test="${role == 'SA'}">
	            <h5><a href="addTeam?branchid=${branch.branchid}">Add New</a></h5>
			</c:if>
			<div class="box-content nopadding">
				<table class="table table-hover table-nomargin table-colored-header">
				<tr>
				    <th>Team Name</th>
				    <th>Team Leader</th>
				    <th>Action</th>
				</tr>
				<c:forEach var="team" items="${listTeam}" varStatus="status">
					<tr>
					    <td>${team.teamname}</td>
						<td>${team.leadername}</td>
						<td>
							<c:if test="${role == 'SA'}">		
								<button class="btn btn-small" onclick="window.location='editTeam?teamid=${team.teamid}';" >
							    	<i class="fa fa-edit"></i></button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="btn btn-small" onclick="deleteTeam(${team.teamid})">
									<i class="fa fa-trash-o"></i></button>
		                        &nbsp;&nbsp;&nbsp;&nbsp;
							</c:if>
							<a href="listMember?teamid=${team.teamid}">Member</a>				
					    </td>		                 
					</tr>
				</c:forEach>             
				</table>
			</div>
		</div>
	</div>
	<script>
	    function deleteTeam(branchid) {
	    	jQuery.ajax({
	            type: "DELETE",
	            url: "http://localhost:8080/SalesDiaryPro/team/delete/"+teamid,
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