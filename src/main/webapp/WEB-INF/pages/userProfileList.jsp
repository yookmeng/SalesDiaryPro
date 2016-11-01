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
	<c:if test="${role == 'TL'}">
		<jsp:include page="_tlNavigation.jsp" />
	</c:if>   
	<c:if test="${role == 'USER'}">
		<jsp:include page="_userNavigation.jsp" />
	</c:if>   
	<div>
		<div class="container-fluid">
	        <input type="hidden" value="companyid" name="companyid" />     
	        <input type="hidden" value="branchid" name="branchid" />     
	        <input type="hidden" value="team" name="team" />     
			<div class="breadcrumbs">
				<ul>
					<li>
						<a href="home">Home</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<c:if test="${role == 'MD' || role == 'SA'}">
						<li>
							<a href="listBranch?companyid=${company.companyid}">Branch</a>
							<i class="fa fa-angle-right"></i>
						</li>
					</c:if>
					<c:if test="${role == 'MD' || role == 'SA' || role == 'MA'}" >
						<li>
							<a href="listTeam?branchid=${branch.branchid}">Team</a>
							<i class="fa fa-angle-right"></i>
						</li>
					</c:if>
					<c:if test="${role == 'MD' || role == 'SA' || role == 'MA' || role == 'TL'}" >
						<li>
							<a href="listMember?teamid=${team.teamid}">Member</a>
						</li>
					</c:if>
				</ul>
			</div>
			<div align="center">
	            <h5><a href="addMember?teamid=${team.teamid}">Add New</a></h5>
				<div class="box-content nopadding">
					<table class="table table-hover table-nomargin table-colored-header">
					<tr>
					    <th>Member Name</th>
					    <th>Mobile</th>
					    <th>Email</th>
					    <th>Action</th>
					</tr>
					<c:forEach var="member" items="${userProfile}" varStatus="status">
						<tr>
						    <td>${member.username}</td>
							<td>${member.mobile}</td>
							<td>${member.email}</td>
							<td>
								<button class="btn btn-small" onclick="window.location='editMember?userid=${member.userid}';" >
							    	<i class="fa fa-edit"></i></button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="btn btn-small" onclick="deleteMember('${member.username}')">
									<i class="fa fa-trash-o"></i></button>
						    </td>		                 
						</tr>
					</c:forEach>             
					</table>
				</div>
			</div>
		</div>
	</div>	
	<script>
	    function deleteMember(username) {
	    	jQuery.ajax({
	            type: "DELETE",
	            url: "http://localhost:8080/SalesDiaryPro/userprofile/delete/"+username,
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