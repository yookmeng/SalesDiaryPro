<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_userNavigation.jsp" />
	<div class="container-fluid" id="content">
		<jsp:include page="_userSideBar.jsp" />	
		<div id="main">
		<div class="container-fluid">
		    <input type="hidden" value="userid" name="userid" /> 
		    <input type="hidden" value="prospect" name="prospect" /> 
			<div class="page-header">
				<div>
					<ul>
						<li  class="pull-left">
							<div>
								<h3>${prospect.firstname} ${prospect.lastname}</h3>
								<a href="editProspect?prospectid=${prospect.prospectid}">Edit</a>
							</div>
						</li>
					</ul>			
				</div>
			</div>    
			<div class="breadcrumbs">
				<ul>
					<li>
						<a href="home">Home</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="listProspect">Prospect</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="listRequest?userid=${userid}&prospectid=${prospect.prospectid}">Request</a>
					</li>
				</ul>
				<div class="close-bread">
					<a href="#">
						<i class="fa fa-times"></i>
					</a>
				</div>
			</div>
		    <div align="center">
	        	<h5><a href="addRequest?prospectid=${prospect.prospectid}">New Request</a></h5>
	            <div class="row">
	                <c:forEach var="Request" items="${listRequest}" varStatus="status">            		
					<div class="col-sm-4">
						<div class="box box-color box-bordered blue">
							<div class="box-title">
								<h3>
									<i class="fa fa-file"></i>
									${Request.requestdate}
								</h3>
								<div class="actions">
									<a href="editRequest?requestid=${Request.requestid}" class="btn btn-mini content-edit">
										<i class="fa fa-edit"></i>
									</a>
									<a href="#" class="btn btn-mini content-slideUp">
										<i class="fa fa-angle-down"></i>
									</a>
								</div>
							</div>
							<div class="box-content">
								<div>Brand  : ${Request.brandname} ${Request.modelname}</div>
								<div>Remark : ${Request.remark}</div>
								<div>${Request.status}</div>
							</div>
						</div>
					</div>
	                </c:forEach>             
	            </div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>