<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_mdNavigation.jsp" />
 	<div id="main" class="container-fluid">
		<input type="hidden" value="companyTarget" name="companyTarget" /> 
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listCompanyTarget">Company Target</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listBranchTarget?targetid=${companyTarget.targetid}">Branch Target</a>
				</li>					
			</ul>
		</div>
		<div>
			<h4>${companyTarget.companyname}  ${companyTarget.displayperiod}  Company Target</h4>
		</div>
        <div>
    		<h5>Prospect : ${companyTarget.prospect}</h5>
    		<h5>Test Drive : ${companyTarget.testdrive}</h5>
    		<h5>Closed : ${companyTarget.closed}</h5>    		
        </div>
        <div align="center">
            <h5><a href="addBranchTarget?targetid=${companyTarget.targetid}">Add New</a></h5>
			<div class="box-content nopadding">
				<table class="table table-hover table-nomargin table-colored-header">
	            <tr>
	                <th>Branch</th>
	                <th>Prospect</th>
	                <th>Test Drive</th>
	                <th>Closed</th>
	                <th>Action</th>
	            </tr>
	                <c:forEach var="branchTarget" items="${listTarget}" varStatus="status">
		                <tr>
		                    <td>${branchTarget.branchname}</td>
		                    <td><fmt:formatNumber value="${branchTarget.prospect}"/></td>
		                    <td><fmt:formatNumber value="${branchTarget.testdrive}"/></td>
		                    <td><fmt:formatNumber value="${branchTarget.closed}"/></td>
		                    <td>
		                        <a href="editBranchTarget?targetid=${branchTarget.targetid}">Edit</a>
		                        &nbsp;&nbsp;&nbsp;&nbsp;
		                        <a href="deleteBranchTarget?targetid=${branchTarget.targetid}">Delete</a>
		                    </td>
		                </tr>
	                </c:forEach>             
	            </table>
	        </div>
		</div>
	</div>
</body>
</html>