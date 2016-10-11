<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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
	<div id="main" class="container-fluid">
        <input type="hidden" value="company" name="company" />     
        <input type="hidden" value="branch" name="branch" />     
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listBranch?companyid=${company.companyid}">Branch</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listTeam?branchid=${branch.branchid}">Team</a>
				</li>
			</ul>
		</div>
		<div class="row">
			<div class="span12">
				<div class="box">
					<div class="box-content">
				        <form:form action="saveTeam" method="post" modelAttribute="team" class='form-horizontal form-wizard'>
			            <form:hidden path="branchid" value="${branch.branchid}"/>        
			            <form:hidden path="teamid"/>
						<div class="form-group">
							<label for="teamname" class="control-label col-sm-2">Team Name</label>
							<div class="col-sm-5">
								<form:input type="text" path="teamname" name="teamname" id="teamname" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="text" class="control-label col-sm-2">Team Leader</label>
							<div class="col-sm-2">
								<form:select name="leadername" path="leadername" id="leadername" items="${leaderlist}" data-rule-required="true" />
							</div>
						</div>
						<div class="form-actions">
							<input type="reset" class="btn" onclick="location.href='listTeam?branchid=${team.branchid}'" value="Back" id="back">						
							<input type="submit" class="btn btn-primary" value="Save">
						</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>