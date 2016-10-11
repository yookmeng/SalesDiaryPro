<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="_menu.jsp" />
<body>
<div id="main">
	<div class="container-fluid">
        <input type="hidden" value="companyid" name="companyid" />     
        <input type="hidden" value="branchid" name="branchid" />     
        <input type="hidden" value="team" name="team" />     
		<div class="page-header">
				<div>
					<ul>
						<li  class="pull-left">
							<div>
								<h3>${team.teamname}</h3>
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
					<a href="listBranch2?companyid=${companyid}">Branches</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listTeam2?branchid=${branchid}">Teams</a>
					<i class="fa fa-angle-right"></i>
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
	                <c:forEach var="member" items="${userProfile}" varStatus="status">
	            		<li class="col-sm-3">
	            			<div class="product">
	            				<div class="details">
									<div class="name">
										<a href="listReview?userid=${member.userid}">${member.username}</a>			
									</div>
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