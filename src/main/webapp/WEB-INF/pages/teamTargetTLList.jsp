<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}"/>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_tlNavigation.jsp" />
	<div id="main" class="container-fluid">
	   	<input type="hidden" value="${base}" name="base" id="base"/>	
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
	                    <td>${teamTarget.period}</td>
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