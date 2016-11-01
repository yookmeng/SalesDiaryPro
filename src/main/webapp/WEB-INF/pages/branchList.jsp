<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<c:if test="${role == 'SA'}">	
		<jsp:include page="_saNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'MD'}">	
		<jsp:include page="_mdNavigation.jsp" />
	</c:if>
	<div class="container-fluid">
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listBranch">Branch</a>
				</li>
			</ul>
		</div>
		<br>
		<div align="center">
			<c:if test="${role == 'SA'}">		
				<h5><a href="addBranch">Add New</a></h5>
			</c:if>	
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
							<c:if test="${role == 'SA'}">		
								<button class="btn btn-small" onclick="window.location='editBranch?branchid=${branch.branchid}';" >
							    	<i class="fa fa-edit"></i></button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="btn btn-small" onclick="deleteBranch(${branch.branchid})">
									<i class="fa fa-trash-o"></i></button>
	                        	&nbsp;&nbsp;&nbsp;&nbsp;							
							</c:if>
							<a href="listTeam?branchid=${branch.branchid}">Team</a>				
					    </td>		                 
					</tr>
				</c:forEach>             
				</table>
			</div>
		</div>
	</div>
	<script>
	    function deleteBranch(branchid) {
	    	jQuery.ajax({
	            type: "DELETE",
	            url: "http://localhost:8080/SalesDiaryPro/branch/delete/"+branchid,
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