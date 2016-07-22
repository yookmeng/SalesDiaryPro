<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Request</title>
	<link rel="stylesheet" href="js/jquery.mobile.datepicker.css" />
	<link rel="stylesheet" href="js/jquery.mobile.datepicker.theme.css" />
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
	<script src="js/external/jquery-ui/datepicker.js"></script>
	<script type="text/javascript" src="js/jquery.mobile.datepicker.js"></script>
	<script>
		$(function(){
			$( ".date-input-css" ).datepicker();
		})
	</script>
</head>
<body>
    <div align="center">
        <h1>New/Edit Request</h1>
        <form:form action="saveRequest" method="post" modelAttribute="request">
        <table>
            <form:hidden path="companyid"/>        
            <form:hidden path="prospectid"/>        
            <form:hidden path="requestid"/>
            <tr>
                <td>Request Date:</td>
                <td><form:input type="text" class="date-input-css" path="requestdate" /></td>
            </tr>
            <tr>
                <td>Brand:</td>
                <td><form:select path="brandname" items="${brandlist}" /></td>
            </tr>
            <tr>
                <td>Model:</td>
                <td><form:select path="modelname" items="${modellist}" /></td>
            </tr>
            <tr>
                <td>Remark:</td>
                <td><form:input path="remark" /></td>
            </tr>
            <tr>
                <td>Status:</td>
                <td><form:checkbox path="status" value="Hot"/>Hot</td>
                <td><form:checkbox path="status" value="Cold"/>Cold</td>
                <td><form:checkbox path="status" value="Closed"/>Closed</td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
        </form:form>
    </div>
</body>
</html>