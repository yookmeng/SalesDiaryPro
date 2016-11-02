<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<c:if test="${role == 'DEV'}">
		<jsp:include page="_deNavigation.jsp" />
	</c:if>   
	<c:if test="${role == 'SA'}">
		<jsp:include page="_saNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'MD'}">
		<jsp:include page="_mdNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'MA'}">
		<jsp:include page="_mdNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'USER'}">
		<jsp:include page="_userNavigation.jsp" />
	</c:if>
    <div id="main">
		<div class="container-fluid">
			<div class="row">
				<div class="span12">
					<div class="box">
						<div class="box-content">
					        <form:form action="saveUser" method="post" modelAttribute="user" class='form-horizontal form-wizard'>
				            <form:hidden path="userid"/>
							<div class="form-group">
								<label for="username" class="control-label col-sm-2">User Name</label>
								<div class="col-sm-5">
									<form:input type="text" path="username" name="username" id="username" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="password" class="control-label col-sm-2">Password</label>
								<div class="col-sm-5">
									<form:input type="password" path="password" name="password" id="password" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="text" class="control-label col-sm-2">Role</label>
								<div class="col-sm-2">
									<form:select name="role" path="role" id="role" items="${rolelist}" data-rule-required="true" />
								</div>
							</div>
							<div class="form-actions">
								<input type="reset" class="btn" onclick="location.href='home'" value="Back" id="back">						
								<input type="submit" class="btn btn-primary" value="Save">
							</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>