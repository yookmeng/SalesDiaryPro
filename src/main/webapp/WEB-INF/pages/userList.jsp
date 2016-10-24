<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
<jsp:include page="_menu.jsp" />
<body>
	<c:if test="${role == 'MA'}">
		<jsp:include page="_maNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'SA'}">
		<jsp:include page="_saNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'DE'}">
		<jsp:include page="_deNavigation.jsp" />
	</c:if>
<div id="main" class="container-fluid">	
	<div align="center">
		<h5><a href="newUser">Add New</a></h5>
		<div class="box-content nopadding">
		  <table class="table table-hover table-nomargin table-colored-header">
		  <tr>
		      <th>User Name</th>
		      <th>Role</th>
		      <th>Action</th>
		  </tr>
		      <c:forEach var="user" items="${listUser}" varStatus="status">
		    <tr>
		        <td>${user.username}</td>
		        <td>${user.role}</td>
		        <td>
		            <a href="editUser?username=${user.username}">Edit</a>
		            &nbsp;&nbsp;&nbsp;&nbsp;
		            <a href="deleteUser?username=${user.username}">Delete</a>
		        </td>
		                 
		    </tr>
		    </c:forEach>             
		</table>
    </div>
    </div>
</div>
</body>
</html>