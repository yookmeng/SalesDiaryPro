<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}"/>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_userNavigation.jsp" />
	<div id="main">
	   	<input type="hidden" value="${base}" name="base" id="base"/>	
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listContacts">Contact</a>
				</li>
			</ul>
		</div>
        <input type="hidden" value="userProfile" name="userProfile" /> 
		<div align="center">
			<a href="listContacts">All</a>
			<a href="listContact?filter=A">A</a>
			<a href="listContact?filter=B">B</a>
			<a href="listContact?filter=C">C</a>
			<a href="listContact?filter=D">D</a>
			<a href="listContact?filter=E">E</a>
			<a href="listContact?filter=F">F</a>
			<a href="listContact?filter=G">G</a>
			<a href="listContact?filter=H">H</a>
			<a href="listContact?filter=I">I</a>
			<a href="listContact?filter=J">J</a>
			<a href="listContact?filter=K">K</a>
			<a href="listContact?filter=L">L</a>
			<a href="listContact?filter=M">M</a>
			<a href="listContact?filter=N">N</a>
			<a href="listContact?filter=O">O</a>
			<a href="listContact?filter=P">P</a>
			<a href="listContact?filter=Q">Q</a>
			<a href="listContact?filter=R">R</a>
			<a href="listContact?filter=S">S</a>
			<a href="listContact?filter=T">T</a>
			<a href="listContact?filter=U">U</a>
			<a href="listContact?filter=V">V</a>
			<a href="listContact?filter=W">W</a>
			<a href="listContact?filter=X">X</a>
			<a href="listContact?filter=Y">Y</a>
			<a href="listContact?filter=Z">Z</a>
   	    	<h5>
   				<a href="addContact?userid=${userProfile.userid}" class='btn'>
				<i class="fa fa-plus-circle"></i>New Contact</a></h5>
		</div>
		<div align="center" class="box-content nopadding">
			<table class="table table-hover table-nomargin table-colored-header">
			<tr>
			    <th>Contact Name</th>
			    <th>Mobile</th>
			    <th>Action</th>
			</tr>
			<c:forEach var="contact" items="${listContact}" varStatus="status">
				<tr>
				    <td>${contact.firstname} ${contact.lastname}</td>
					<td>${contact.mobile}</td>
					<td>
						<button class="btn btn-small" onclick="window.location='editContact?contactid=${contact.contactid}';" >
							<i class="fa fa-edit"></i></button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="btn btn-small" onclick="deleteContact(${contact.contactid})">
							<i class="fa fa-trash-o"></i></button>
				    </td>		                 
				</tr>
			</c:forEach>             
			</table>
		</div>
	</div>
	<script>
    function deleteContact(contactid) {
		var base = $('#base').val();
    	if (window.location.protocol === 'https:') {
    	    base = base.replace("http", "https");
    	}	    	

    	jQuery.ajax({
            type: "DELETE",
            url: base+"/contact/delete/"+contactid,
            contentType: "application/json",
            data: "",
            dataType: "",
            success: function (data, status, jqXHR) {
				location.replace(location);
            },	        
            error: function (jqXHR, status) {
            }
        });	
    }
	</script>
</body>
</html>