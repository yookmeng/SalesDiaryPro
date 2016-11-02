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
	<jsp:include page="_maNavigation.jsp" />
	<div id="main" class="container-fluid">
	   	<input type="hidden" value="${base}" name="base" id="base"/>	
		<input type="hidden" value="branch" name="branch" /> 
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listBranchTargetMA">Target</a>
				</li>
			</ul>
		</div>
		<div>
			<h4>${branch.branchname}</h4>
		</div>
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
				<c:forEach var="branchTarget" items="${listTarget}" varStatus="status">
					<tr>
						<td>${branchTarget.displayperiod}</td>
						<td><fmt:formatNumber value="${branchTarget.prospect}"/></td>
						<td><fmt:formatNumber value="${branchTarget.testdrive}"/></td>
						<td><fmt:formatNumber value="${branchTarget.closed}"/></td>
						<td>
						    <a href="listTeamTarget?targetid=${branchTarget.targetid}">Team Target</a>
						</td>
					</tr>
				</c:forEach>             
		        </table>
			</div>
		</div>
	</div>
</body>
</html>