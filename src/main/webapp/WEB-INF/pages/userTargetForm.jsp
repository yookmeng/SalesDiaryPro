<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_maNavigation.jsp" />
	<div id="main" class="container-fluid">
        <input type="hidden" value="teamTarget" name="teamTarget" /> 
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listTeamTargetUser">Target</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listUserTarget?targetid=${teamTarget.targetid}">Member Target</a>
				</li>
			</ul>
		</div>
		<div class="row">
			<div class="span12">
				<div class="box">
					<div class="box-content">
			        <form:form action="" method="post" modelAttribute="userTarget" class='form-horizontal form-wizard'>
		            <form:hidden path="period"/>        
		            <form:hidden path="targetid"/>        
		            <form:hidden path="teamtargetid"/>        
					<div class="form-group">
						<label for="period" class="control-label col-sm-2">Period</label>
						<div class="col-sm-2">
							<label>${teamTarget.displayperiod}</label>
						</div>
					</div>
					<div class="form-group">
						<label for="text" class="control-label col-sm-2">Member</label>
						<div class="col-sm-5">
							<form:select name="username" path="username" id="username" items="${userlist}" data-rule-required="true" />
						</div>
					</div>
					<div class="form-group">
						<label for="prospect" class="control-label col-sm-2">Prospect</label>
						<div class="col-sm-2">
							<form:input type="text" path="prospect" name="prospect" id="prospect" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="testdrive" class="control-label col-sm-2">Test Drive</label>
						<div class="col-sm-2">
							<form:input type="text" path="testdrive" name="testdrive" id="testdrive" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="closed" class="control-label col-sm-2">Closed</label>
						<div class="col-sm-2">
							<form:input type="text" path="closed" name="closed" id="closed" class="form-control" />
						</div>
					</div>
					<div class="form-actions">
						<input type="reset" class="btn" onclick="location.href='listUserTarget?targetid=${userTarget.teamtargetid}'" value="Back" id="back">
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

		var targetid = $('#targetid').val(); 
	    var userid = ""; 
	    var username = $('#username').val(); 
	    var period = $('#period').val(); 
	    var displayperiod = ""; 
	    var teamtargetid = $('#teamtargetid').val(); 
	    var prospect = $('#prospect').val(); 
	    var testdrive = $('#testdrive').val(); 
	    var closed = $('#closed').val(); 
	
	    var json = {
	    		"targetid" : targetid,
	    		"userid" : userid,
	    		"username" : username,
	    		"period" : period,
	    		"displayperiod" : displayperiod,
	    		"teamtargetid" : teamtargetid,
	    		"prospect" : prospect,
	    		"testdrive" : testdrive,
	    		"closed" : closed
		};
	    if (json.targetid=="0"){
	        $.ajax({
	            url: "http://localhost:8080/SalesDiaryPro/usertarget/create",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json), 
	            success:function(data, Textstatus, jqXHR){
	                alert("Record created!");
	                window.location.href = "/SalesDiaryPro/listUserTarget?targetid="+teamtargetid;
	            },
	            error:function(jqXhr, Textstatus){
	                alert("Create failed!");
	            }
	        });    	
	    }
	    else {
	        $.ajax({
	            url: "http://localhost:8080/SalesDiaryPro/usertarget/update/"+targetid,
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json),
	            success:function(data, Textstatus, jqXHR){
	                alert("Record updated!");
	                window.location.href = "/SalesDiaryPro/listUserTarget?targetid="+teamtargetid;
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