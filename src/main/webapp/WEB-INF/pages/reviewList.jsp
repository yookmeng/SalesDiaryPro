<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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
	<div class="container-fluid" id="content">
		<div id="main">
		<div class="container-fluid">
		    <input type="hidden" value="userProfile" name="userProfile" /> 
		    <input type="hidden" value="review" name="review" /> 
			<div class="page-header">
				<h3>Review</h3>
			</div>    
			<div class="breadcrumbs">
				<ul>
					<li>
						<a href="home">Home</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="listMember?teamid=${userProfile.teamid}">Member</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="listReview?userid=${userProfile.userid}">Review</a>
					</li>
				</ul>
			</div>
		    <div align="center">
	        	<h5><a href="addReview?userid=${userid}">Add Review</a></h5>
	            <table border="1">
	            <tr>
	                <th>Review Date</th>
	                <th>Prospect</th>
	                <th>Sales</th>
	                <th>Total Sales</th>
	                <th>Minute</th>
	                <th>Action</th>
	            </tr>
	                <c:forEach var="Review" items="${listReview}" varStatus="status">
	                <tr>
	                    <td>${Review.reviewdate}</td>
	                    <td><fmt:formatNumber value="${Review.prospect}"/></td>
	                    <td><fmt:formatNumber value="${Review.sales}"/></td>
	                    <td><fmt:formatNumber value="${Review.totalsales}"/></td>
	                    <td>${Review.minute}</td>
	                    <td>
	                        <a href="editReview?reviewid=${Review.reviewid}">Edit</a>
	                        &nbsp;
	                        <a href="deleteReview?reviewid=${Review.reviewid}">Delete</a>
	                    </td>
	                </tr>
	                </c:forEach>             
	            </table>
			</div>
		</div>
	</div>
	</div>
</body>
</html>