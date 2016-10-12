<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_userNavigation.jsp" />
	<div id="main" class="container-fluid">
        <input type="hidden" value="teamTarget" name="teamTarget" /> 
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listTeamTargetUser">Target</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listUserTarget?targetid=${teamTarget.targetid}">Member Target</a>
				</li>
			</ul>
		</div>
		<div>
			<h4>${teamTarget.teamname}  ${teamTarget.displayperiod}  Target</h4>
		</div>
        <div>
    		<h5>Prospect : ${teamTarget.prospect}</h5>
    		<h5>Test Drive : ${teamTarget.testdrive}</h5>
    		<h5>Closed : ${teamTarget.closed}</h5>    		
        </div>
        <div align="center">
            <h5><a href="addUserTarget?targetid=${teamTarget.targetid}">New Target</a></h5>
			<div class="box-content nopadding">
				<table class="table table-hover table-nomargin table-colored-header">
	            <tr>
	                <th>Member</th>
	                <th>Prospect</th>
	                <th>Test Drive</th>
	                <th>Closed</th>
	                <th>Action</th>
	            </tr>
	                <c:forEach var="userTarget" items="${listTarget}" varStatus="status">
	                <tr>
	                    <td>${userTarget.firstname} ${userTarget.lastname}</td>
	                    <td><fmt:formatNumber value="${userTarget.prospect}"/></td>
	                    <td><fmt:formatNumber value="${userTarget.testdrive}"/></td>
	                    <td><fmt:formatNumber value="${userTarget.closed}"/></td>
	                    <td>
	                        <a href="editUserTarget?targetid=${userTarget.targetid}">Edit</a>
	                        &nbsp;&nbsp;&nbsp;&nbsp;
	                        <a href="deleteUserTarget?targetid=${userTarget.targetid}">Delete</a>
	                    </td>
	                </tr>
	                </c:forEach>             
	            </table>
        	</div>
		</div>
	</div>
</body>
</html>