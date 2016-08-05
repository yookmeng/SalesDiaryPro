<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>${title}</title>
</head>
<body>
<input type="hidden" value="companyid" name="companyid" /> 
<c:choose>
  <c:when test="${role == 'DEV'}">
    <jsp:include page="_menu.jsp" />
  </c:when>
  <c:when test="${role == 'SA'}">
		<div style="border: 1px solid #ccc;padding:5px;margin-bottom:20px;">
		 
		  <c:if test="${pageContext.request.userPrincipal.name == null}">
		   <a href="${pageContext.request.contextPath}/login">Login</a>
		  </c:if>
		  
		  <c:if test="${pageContext.request.userPrincipal.name != null}">  
			 | &nbsp;
			 <a href="${pageContext.request.contextPath}/editUser?username=${pageContext.request.userPrincipal.name}">Edit Profile</a>
			 | &nbsp;
			 <a href="${pageContext.request.contextPath}/editCompany?companyid=${companyid}">Edit Company</a>
			 | &nbsp;
			 <a href="${pageContext.request.contextPath}/listBrand?companyid=${companyid}">Brand List</a>
		     | &nbsp;
			 <a href="${pageContext.request.contextPath}/listBranch?companyid=${companyid}">Branch List</a>
		     | &nbsp;
		     <a href="${pageContext.request.contextPath}/logout"> Logout</a>     
		  </c:if>
		  
		</div>
  </c:when>
  <c:when test="${role == 'MD'}">
		<div style="border: 1px solid #ccc;padding:5px;margin-bottom:20px;">
		 
		  <c:if test="${pageContext.request.userPrincipal.name == null}">
		   <a href="${pageContext.request.contextPath}/login">Login</a>
		  </c:if>
		  
		  <c:if test="${pageContext.request.userPrincipal.name != null}">  
			 | &nbsp;
			 <a href="${pageContext.request.contextPath}/editUser?username=${pageContext.request.userPrincipal.name}">Edit Profile</a>
		     | &nbsp;
		     <a href="${pageContext.request.contextPath}/logout"> Logout</a>     
		  </c:if>
		  
		</div>
  </c:when>
  <c:when test="${role == 'MA'}">
		<div style="border: 1px solid #ccc;padding:5px;margin-bottom:20px;">
		 
		  <c:if test="${pageContext.request.userPrincipal.name == null}">
		   <a href="${pageContext.request.contextPath}/login">Login</a>
		  </c:if>
		  
		  <c:if test="${pageContext.request.userPrincipal.name != null}">  
			 | &nbsp;
			 <a href="${pageContext.request.contextPath}/editUser?username=${pageContext.request.userPrincipal.name}">Edit Profile</a>
		     | &nbsp;
		     <a href="${pageContext.request.contextPath}/logout"> Logout</a>     
		  </c:if>
		  
		</div>
  </c:when>
  <c:when test="${role == 'USER'}">
		<div style="border: 1px solid #ccc;padding:5px;margin-bottom:20px;">
		 
		  <c:if test="${pageContext.request.userPrincipal.name == null}">
		   <a href="${pageContext.request.contextPath}/login">Login</a>
		  </c:if>
		  
		  <c:if test="${pageContext.request.userPrincipal.name != null}">  
			 | &nbsp;
			 <a href="${pageContext.request.contextPath}/editUser?username=${pageContext.request.userPrincipal.name}">Edit Profile</a>
		     | &nbsp;
		     <a href="${pageContext.request.contextPath}/logout"> Logout</a>     
		  </c:if>
		  
		</div>
  </c:when>
  <c:otherwise>
  </c:otherwise>
</c:choose>
	<h1>Welcome ${username} </h1>
    <h1>Your role is ${role} </h1>
</body>
</html>