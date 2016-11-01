<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<c:if test="${role == 'SA'}">
		<jsp:include page="_saNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'MD'}">
		<jsp:include page="_mdNavigation.jsp" />
	</c:if>   
	<c:if test="${role == 'MA'}">
		<jsp:include page="_maNavigation.jsp" />
	</c:if>   
	<c:if test="${role == 'TL'}">
		<jsp:include page="_tlNavigation.jsp" />
	</c:if>   
	<div class="container-fluid">
        <input type="hidden" value="company" name="company" />     
        <input type="hidden" value="branch" name="branch" /> 
		<input type="hidden" value="${role}" name="role" id="role"/>	            
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listBranch?companyid=${company.companyid}">Branch</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listTeam?branchid=${branch.branchid}">Team</a>
				</li>
			</ul>
		</div>
		<div class="row">
			<div class="span12">
				<div class="box">
					<div class="box-content">
				        <form:form action="" method="post" modelAttribute="team" class='form-horizontal form-wizard'>
			            <form:hidden path="branchid" value="${branch.branchid}"/>        
			            <form:hidden path="teamid"/>
						<div class="form-group">
							<label for="teamname" class="control-label col-sm-2">Team Name</label>
							<div class="col-sm-5">
								<form:input type="text" path="teamname" name="teamname" id="teamname" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="text" class="control-label col-sm-2">Team Leader</label>
							<div class="col-sm-2">
								<form:select name="leadername" path="leadername" id="leadername" items="${leaderlist}" data-rule-required="true" />
							</div>
						</div>
						<div class="form-actions">
							<input type="reset" class="btn" onclick="location.href='listTeam?branchid=${team.branchid}'" value="Back" id="back">						
							<input id="btnSave" type="submit" class="btn btn-primary" name="Save" value="Save">
						</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	$('#btnSave').click(function (e) {
		e.preventDefault(); // <------------------ stop default behaviour of button
		
		var teamid = $('#teamid').val(); 
	    var teamname = $('#teamname').val(); 	    
	    var branchid = $('#branchid').val(); 
	    var leaderid = ""; 
	    var leadername = $('#leadername').val(); 	    
	
	    var json = {
	    		"teamid" : teamid,
	    		"teamname" : teamname,	    		
	    		"branchid" : branchid,
	    		"leaderid" : leaderid,
	    		"leadername" : leadername
	    };
	    if (json.teamid=="0"){
	        $.ajax({
	            url: "http://localhost:8080/SalesDiaryPro/team/create",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json), 
	            success:function(data, Textstatus, jqXHR){
	                alert("Record created!");
	                if ($('#role').val()=="TL"){
		                window.location.href = "/SalesDiaryPro/home";
	                }
	                else{
		                window.location.href = "/SalesDiaryPro/listTeam?branchid="+branchid;	                	
	                }	                
	            },
	            error:function(jqXhr, Textstatus){
	                alert("Create failed!"+status);
	            }
	        });    	
	    }
	    else {
	        $.ajax({
	            url: "http://localhost:8080/SalesDiaryPro/team/update/"+teamid,
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json),
	            success:function(data, Textstatus, jqXHR){
	                alert("Record updated!");
	                if ($('#role').val()=="TL"){
		                window.location.href = "/SalesDiaryPro/home";
	                }
	                else{
		                window.location.href = "/SalesDiaryPro/listTeam?branchid="+branchid;	                	
	                }	                
	            },
	            error:function(jqXhr, Textstatus){
	                alert("Update failed!");
	            }
	        });
	    }
	    return true;
	})
	</script>	
</body>
</html>