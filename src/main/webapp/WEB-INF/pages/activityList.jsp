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
	<c:if test="${role == 'MD'}">
		<jsp:include page="_mdNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'MA'}">
		<jsp:include page="_maNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'TL'}">
		<jsp:include page="_tlNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'USER'}">
		<jsp:include page="_userNavigation.jsp" />
	</c:if>
	<div id="main">
	   	<input type="hidden" value="${base}" name="base" id="base"/>	
        <input type="hidden" value="prospect" name="prospect" /> 
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listProspects">Prospect</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listActivity?prospectid=${prospect.prospectid}">Activity</a>
				</li>
			</ul>
		</div>
		<div>
			<h4>${prospect.firstname} ${prospect.lastname}</h4>
		</div>
        <div align="center">
			<c:if test="${role == 'USER'}">
	   	    	<h5><a href="addActivity?prospectid=${prospect.prospectid}" class='btn'>
					<i class="fa fa-plus-circle"></i>New Activity</a></h5>
			</c:if>
			<div class="box-content nopadding">
				<table class="table table-hover table-nomargin table-colored-header">
				<tr>
				    <th>Date</th>
				    <th>Model</th>
				    <th>Activities</th>
				    <th>Action</th>
				    <th></th>
				    <th></th>
				    <th></th>
				</tr>
				<c:forEach var="activity" items="${listActivity}" varStatus="status">
					<tr>
					    <td>${activity.activitydate}</td>
						<td>${activity.modelname}</td>
						<td>
							<c:if test="${activity.demo==false && activity.demostatus==false}">
								<a class="btn" title=Demo onclick="window.location='editActivity?activityid=${activity.activityid}&step=2';">D</a>
							</c:if>
							<c:if test="${activity.demo==true && activity.demostatus==false}">
								<a class="btn btn-orange" title="Demo" onclick="window.location='editActivity?activityid=${activity.activityid}&step=2';">D</a>
							</c:if>
							<c:if test="${activity.demo==true && activity.demostatus==true}">
								<a class="btn btn-green" title="Demo" onclick="window.location='editActivity?activityid=${activity.activityid}&step=2';">D</a>
							</c:if>
							<c:if test="${activity.testdrive==false && activity.testdrivestatus==false}">
								<a class="btn" title="Test Drive" onclick="window.location='editActivity?activityid=${activity.activityid}&step=3';">T</a>
							</c:if>
							<c:if test="${activity.testdrive==true && activity.testdrivestatus==false}">
								<a class="btn btn-orange" title="Test Drive" onclick="window.location='editActivity?activityid=${activity.activityid}&step=3';">T</a>
							</c:if>
							<c:if test="${activity.testdrive==true && activity.testdrivestatus==true}">
								<a class="btn btn-green" title="Test Drive" onclick="window.location='editActivity?activityid=${activity.activityid}&step=3';">T</a>
							</c:if>
							<c:if test="${activity.quotation==false && activity.quotationid==0}">
								<a class="btn" title="Quotation">Q</a>
							</c:if>
							<c:if test="${activity.quotation==true && activity.quotationid==0}">
								<a class="btn btn-orange" title="Quotation" onclick="window.location='addQuotation?activityid=${activity.activityid}';">Q</a>
							</c:if>
							<c:if test="${activity.quotation==true && activity.quotationid!=0}">
								<a class="btn btn-green" title="Quotation" onclick="window.location='editQuotation?quotationid=${activity.quotationid}';">Q</a>
							</c:if>
							<c:if test="${activity.followup==false && activity.followupstatus==false}">
								<a class="btn" title="Follow Up" onclick="window.location='editActivity?activityid=${activity.activityid}&step=5';">F</a>
							</c:if>
							<c:if test="${activity.followup==true && activity.followupstatus==false}">
								<a class="btn btn-orange" title="Follow Up" onclick="window.location='editActivity?activityid=${activity.activityid}&step=5';">F</a>
							</c:if>
							<c:if test="${activity.followup==true && activity.followupstatus==true}">
								<a class="btn btn-green" title="Follow Up" onclick="window.location='editActivity?activityid=${activity.activityid}&step=5';">F</a>
							</c:if>
							<c:if test="${activity.closed==false}">
								<a class="btn" title="Closed" onclick="window.location='editActivity?activityid=${activity.activityid}&step=6';">C</a>
							</c:if>
							<c:if test="${activity.closed==true}">
								<a class="btn btn-green" title="Closed" onclick="window.location='editActivity?activityid=${activity.activityid}&step=6';">C</a>
							</c:if>
							<c:if test="${activity.lost==false}">
								<a class="btn" title="Lost" onclick="window.location='editActivity?activityid=${activity.activityid}&step=7';">L</a>
							</c:if>
							<c:if test="${activity.lost==true}">
								<a class="btn btn-green" title="Lost" onclick="window.location='editActivity?activityid=${activity.activityid}&step=7';">L</a>
							</c:if>					
						</td>
						<td>
							<button class="btn btn-small" onclick="window.location='editActivity?activityid=${activity.activityid}';" >
						    	<i class="fa fa-edit"></i></button>
							<c:if test="${role == 'USER'}">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="btn btn-small" onclick="deleteActivity(${activity.activityid})">
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
	    function deleteActivity(activityid) {
			var base = $('#base').val();
	    	if (window.location.protocol === 'https:') {
	    	    base = base.replace("http", "https");
	    	}	    	

	    	jQuery.ajax({
	            type: "DELETE",
	            url: base+"/activity/delete/"+activityid,
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