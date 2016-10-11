<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="_menu.jsp" />
<body>
<div id="main" class="container-fluid">
	<c:if test="${role == 'SA'}">
		<jsp:include page="_saNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'MD'}">
		<jsp:include page="_mdNavigation.jsp" />
	</c:if>   
	<c:if test="${role == 'MA'}">
		<jsp:include page="_maNavigation.jsp" />
	</c:if>   
	<c:if test="${role == 'USER'}">
		<jsp:include page="_userNavigation.jsp" />
	</c:if>   
	<input type="hidden" value="companyid" name="companyid" />     
	<input type="hidden" value="branchid" name="branchid" />     
	<input type="hidden" value="team" name="team" />     
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
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<a href="listMember?teamid=${team.teamid}">Member</a>
			</li>
		</ul>
		<div class="close-bread">
			<a href="#">
				<i class="fa fa-times"></i>
			</a>
		</div>
	</div>
	<div class="row">
		<div class="span12">
			<div class="box">
				<div class="box-content">
		        <form:form action="saveMember" method="post" modelAttribute="userProfile" class='form-horizontal form-wizard'>
	            <form:hidden path="teamid"/>
	            <form:hidden path="userid"/>
	            <form:hidden path="role"/>
				<div class="form-group">
					<label for="username" class="control-label col-sm-2">Member Name</label>
					<div class="col-sm-5">
						<form:input type="text" path="username" name="username" id="username" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="control-label col-sm-2">Password</label>
					<div class="col-sm-5">
						<form:input type="text" path="password" name="password" id="password" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label for="mobile" class="control-label col-sm-2">Member Name</label>
					<div class="col-sm-2">
						<form:input type="text" path="mobile" name="mobile" id="mobile" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label for="email" class="control-label col-sm-2">Email</label>
					<div class="col-sm-5">
						<form:input type="text" path="email" name="email" id="email" class="form-control" />
					</div>
				</div>
				<div class="form-actions">
					<input type="reset" class="btn" onclick="location.href='listMember?teamid=${userProfile.teamid}'" value="Back" id="back">						
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