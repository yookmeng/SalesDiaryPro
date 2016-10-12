<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_maNavigation.jsp" />
	<div id="main" class="container-fluid">
		<input type="hidden" value="branchTarget" name="branchTarget" /> 
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listBranchTargetMA">Branch Target</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listTeamTarget?targetid=${branchTarget.targetid}">Team Target</a>
				</li>					
			</ul>
		</div>
		<div>
			<h4>${branchTarget.branchname}  ${branchTarget.displayperiod}  Target</h4>
		</div>
        <div>
    		<h5>Prospect : ${branchTarget.prospect}</h5>
    		<h5>Test Drive : ${branchTarget.testdrive}</h5>
    		<h5>Closed : ${branchTarget.closed}</h5>    		
        </div>
       	<div align="center">
            <h5><a href="addTeamTarget?targetid=${branchTarget.targetid}">New Target</a></h5>
			<div class="box-content nopadding">
				<table class="table table-hover table-nomargin table-colored-header">
	            <tr>
	                <th>Team</th>
	                <th>Prospect</th>
	                <th>Test Drive</th>
	                <th>Closed</th>
	                <th>Action</th>
	            </tr>
                <c:forEach var="teamTarget" items="${listTarget}" varStatus="status">
	                <tr>
	                    <td>${teamTarget.teamname}</td>
	                    <td><fmt:formatNumber value="${teamTarget.prospect}"/></td>
	                    <td><fmt:formatNumber value="${teamTarget.testdrive}"/></td>
	                    <td><fmt:formatNumber value="${teamTarget.closed}"/></td>
	                    <td>
	                        <a href="editTeamTarget?targetid=${teamTarget.targetid}">Edit</a>
	                        &nbsp;&nbsp;&nbsp;&nbsp;
	                        <a href="deleteTeamTarget?targetid=${teamTarget.targetid}">Delete</a>
	                    </td>
	                </tr>
                </c:forEach>             
            	</table>
        	</div>
        </div>
	</div>
</body>
</html>