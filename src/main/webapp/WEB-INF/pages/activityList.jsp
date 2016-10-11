<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
<jsp:include page="_menu.jsp" />
<body>
	<div id="main">
		<div class="container-fluid">
			<div class="page-header">
				<div class="pull-left">
					<h1>Prospect</h1>
				</div>
			</div> 
        <input type="hidden" value="userid" name="userid" /> 
        <input type="hidden" value="prospectid" name="prospectid" /> 
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
						<a href="listActivity?userid=${userid}&prospectid=${prospectid}">Activity</a>
					</li>
				</ul>
				<div class="close-bread">
					<a href="#">
						<i class="fa fa-times"></i>
					</a>
				</div>
			</div>
	        <div align="center">
	            <h5><a href="addActivity?prospectid=${prospectid}">New Activity</a></h5>
	            <table border="1">
	            <tr>
	                <th>Activity Date</th>
	                <th>Brand</th>
	                <th>Model</th>
	                <th>Remark 1</th>
	                <th>Remark 2</th>
	                <th>Remark 3</th>
	                <th>Action</th>
	            </tr>
	            <c:forEach var="Activity" items="${listActivity}" varStatus="status">
	            <tr>
	                <td>${Activity.activitydate}</td>
	                <td>${Activity.brandname}</td>
	                <td>${Activity.modelname}</td>
	                <td>${Activity.remark1}</td>
	                <td>${Activity.remark2}</td>
	                <td>${Activity.remark3}</td>
	                <td>
	                    <a href="editActivity?activityid=${Activity.activityid}">Edit</a>
	                    &nbsp;&nbsp;&nbsp;&nbsp;
	                    <a href="deleteActivity?prospectid=${Activity.prospectid}&activityid=${Activity.activityid}">Delete</a>
	                </td>
	            </tr>
	            </c:forEach>             
	            </table>
	        </div>
		</div>
	</div>
</body>
</html>