<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_tlNavigation.jsp" />
	<div id="main" class="container-fluid">
		<input type="hidden" value="teamTarget" name="teamTarget" /> 
		<div>
			<h4>${teamTarget.teamname}  ${teamTarget.period}  Target</h4>
		</div>
        <div>
    		<h5>Prospect : ${teamTarget.prospect}</h5>
    		<h5>Test Drive : ${teamTarget.testdrive}</h5>
    		<h5>Closed : ${teamTarget.closed}</h5>    		
        </div>
       	<div align="center">
			<div class="box-content nopadding">
				<table class="table table-hover table-nomargin table-colored-header">
	            <tr>
	                <th>Member</th>
	                <th>Prospect</th>
	                <th>Test Drive</th>
	                <th>Closed</th>
	                <th>Commission</th>
	                <th>Hot</th>
	            </tr>
                <c:forEach var="listSummary" items="${listUserMonthlySummary}" varStatus="status">
	                <tr>
	                    <td>${listSummary.username}</td>
	                    <td><fmt:formatNumber value="${listSummary.actualprospect}"/> / 
	                    	<fmt:formatNumber value="${listSummary.targetprospect}"/></td>
	                    <td><fmt:formatNumber value="${listSummary.actualtestdrive}"/> /
	                    	<fmt:formatNumber value="${listSummary.targettestdrive}"/></td>
	                    <td><fmt:formatNumber value="${listSummary.actualclosed}"/> /
	                    	<fmt:formatNumber value="${listSummary.targetclosed}"/></td>
	                    <td><fmt:formatNumber value="${listSummary.commission}"/></td>
	                    <td><fmt:formatNumber value="${listSummary.totalhot}"/></td>				
	                </tr>
                </c:forEach>             
            	</table>
        	</div>
        </div>
	</div>
</body>
</html>