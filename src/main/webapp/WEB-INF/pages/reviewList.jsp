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
		   	<input type="hidden" value="${base}" name="base" id="base"/>	
		    <input type="hidden" value="userProfile" name="userProfile" /> 
			<div class="breadcrumbs">
				<ul>
					<li>
						<a href="home">Home</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="listReview">Review</a>
					</li>
				</ul>
			</div>
		    <div align="center">
				<c:if test="${role != 'USER'}">			    
	        		<h5><a href="addReview">Add Review</a></h5>
				</c:if>
				<table class="table table-hover table-nomargin table-colored-header">
	            <tr>
	                <th>Review Date</th>
	                <th>Member</th>
	                <th>Prospect</th>
	                <th>Test Drive</th>
	                <th>Closed</th>
	                <th>Minute</th>
	                <th>Action</th>
	            </tr>
                <c:forEach var="review" items="${listReview}" varStatus="status">
                <tr>
                    <td>${review.reviewdate}</td>
                    <td>${review.username}</td>
                    <td><fmt:formatNumber value="${review.prospect}"/></td>
                    <td><fmt:formatNumber value="${review.testdrive}"/></td>
                    <td><fmt:formatNumber value="${review.closed}"/></td>
                    <td>${review.minute}</td>
                    <td>
						<c:if test="${role != 'USER'}">
							<button class="btn btn-small" onclick="window.location='editReview?reviewid=${review.reviewid}';" >
						    	<i class="fa fa-edit"></i></button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="btn btn-small" onclick="deleteReview(${review.reviewid})">
								<i class="fa fa-trash-o"></i></button>
                    	</c:if>
                    </td>
                </tr>
                </c:forEach>             
	            </table>
			</div>
		</div>
	</div>
	<script>
	    function deleteReview(reviewid) {
	    	jQuery.ajax({
	            type: "DELETE",
	            url: $('#base').val()+"/review/delete/"+reviewid,
	            contentType: "application/json",
	            data: "",
	            dataType: "",
	            success: function (data, status, jqXHR) {
	                alert("record deleted!");	                
					location.replace(location);
	            },	        
	            error: function (jqXHR, status) {
	                alert("delete failed!");
	            }
	        });	
	    }
	</script>	
</body>
</html>