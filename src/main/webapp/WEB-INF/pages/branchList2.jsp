<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<jsp:include page="_menu.jsp" />
<body>
<div id="main">
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>Branches</h1>
			</div>
		</div>    	
     	<input type="hidden" value="companyid" name="companyid" />
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listBranch2?companyid=${companyid}">Branches</a>
				</li>
			</ul>
			<div class="close-bread">
				<a href="#">
					<i class="fa fa-times"></i>
				</a>
			</div>
		</div>
        <div align="center">
            <div class="row">
            	<ul class="shop-items">
	                <c:forEach var="Branch" items="${listBranch}" varStatus="status">            		
	            		<li class="col-sm-3">
	            			<div class="product">
	            				<div class="details">
									<div class="name">
										<a href="listTeam2?branchid=${Branch.branchid}">${Branch.branchname}</a>				
									</div>
									<div>${Branch.maname}</div>
	            				</div>
	            			</div>
	            		</li>
	                </c:forEach>             
            	</ul>
            </div>
        </div>
	</div>
</div>
</body>
</html>