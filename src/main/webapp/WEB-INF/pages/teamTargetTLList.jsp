<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_tlNavigation.jsp" />
	<div id="main" class="container-fluid">
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listTeamTargetTL">Target</a>
				</li>
			</ul>
		</div>
		<br><br>
		<div align="center">
			<div class="box-content nopadding">
				<table class="table table-hover table-nomargin table-colored-header">
	            <tr>
	                <th>Period</th>
	                <th>Prospect</th>
	                <th>Test Drive</th>
	                <th>Closed</th>
	                <th>Action</th>
	            </tr>
                <c:forEach var="teamTarget" items="${listTarget}" varStatus="status">
	                <tr>
	                    <td>${teamTarget.displayperiod}</td>
	                    <td><fmt:formatNumber value="${teamTarget.prospect}"/></td>
	                    <td><fmt:formatNumber value="${teamTarget.testdrive}"/></td>
	                    <td><fmt:formatNumber value="${teamTarget.closed}"/></td>
	                    <td>
		                    <a href="listUserTarget?targetid=${teamTarget.targetid}">Member Target</a>
	                    </td>
	                </tr>
                </c:forEach>             
            	</table>
        	</div>
		</div>
	</div>	
</body>
</html>