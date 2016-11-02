<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}"/>
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
	   	<input type="hidden" value="${base}" name="base" id="base"/>	
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
			var base = $('#base').val();
	    	if (window.location.protocol === 'https:') {
	    	    base = base.replace("http", "https");
	    	}	    	

	    	jQuery.ajax({
	            type: "DELETE",
	            url: base+"/team/delete/"+teamid,
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