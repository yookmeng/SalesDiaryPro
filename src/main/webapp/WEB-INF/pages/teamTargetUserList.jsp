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
					<h1>Team Target</h1>
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
				</ul>
				<div class="close-bread">
					<a href="#">
						<i class="fa fa-times"></i>
					</a>
				</div>
			</div>
 	        <div align="center">
            <table border="1">
            <tr>
                <th>Period</th>
                <th>Prospect</th>
                <th>Test Drive</th>
                <th>Closed</th>
                <th>Action</th>
            </tr>
            <c:forEach var="TeamTarget" items="${listTarget}" varStatus="status">
            <tr>
                <td>${TeamTarget.period}</td>
                <td><fmt:formatNumber value="${TeamTarget.prospect}"/></td>
                <td><fmt:formatNumber value="${TeamTarget.testdrive}"/></td>
                <td><fmt:formatNumber value="${TeamTarget.closed}"/></td>
                <td>
                    <a href="listUserTarget?targetid=${TeamTarget.targetid}">Member Target</a>
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