<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_userNavigation.jsp" />
	<div class="container-fluid" id="content">
		<jsp:include page="_userSideBar.jsp" />	
		<div id="main">
			<div class="container-fluid">
				<div class="page-header">
					<div class="pull-left">
						<h1>Member Target</h1>
					</div>
				</div>    
		        <input type="hidden" value="targetid" name="targetid" /> 
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
					<div class="close-bread">
						<a href="#">
							<i class="fa fa-times"></i>
						</a>
					</div>
				</div>
		        <div align="center">
		            <h5><a href="addUserTarget?targetid=${teamTarget.targetid}">New Target</a></h5>
		            <table>
		            <tr>	        
		                <td>Period:</td>
		                <td>${teamTarget.period}</td>
		                <td></td>                
		                <td>Team:</td>
		                <td>${teamTarget.teamname}</td>
		            </tr>	        
		            <tr>	                        
		                <td>Prospect:</td>
		                <td><fmt:formatNumber value="${teamTarget.prospect}"/></td>
		                <td></td>                
		                <td>Test Drive:</td>
		                <td><fmt:formatNumber value="${teamTarget.testdrive}"/></td>
		                <td></td>                
		                <td>Closed:</td>
		                <td><fmt:formatNumber value="${teamTarget.closed}"/></td>
		            </tr>	        
			        </table> 
		
		            <table border="1">
		            <tr>
		                <th>Member</th>
		                <th>Prospect</th>
		                <th>Test Drive</th>
		                <th>Closed</th>
		                <th>Action</th>
		            </tr>
		                <c:forEach var="UserTarget" items="${listTarget}" varStatus="status">
		                <tr>
		                    <td>${UserTarget.username}</td>
		                    <td><fmt:formatNumber value="${UserTarget.prospect}"/></td>
		                    <td><fmt:formatNumber value="${UserTarget.testdrive}"/></td>
		                    <td><fmt:formatNumber value="${UserTarget.closed}"/></td>
		                    <td>
		                        <a href="editUserTarget?teamtargetid=${teamTarget.targetid}&targetid=${UserTarget.targetid}">Edit</a>
		                        &nbsp;&nbsp;&nbsp;&nbsp;
		                        <a href="deleteUserTarget?targetid=${UserTarget.targetid}">Delete</a>
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