<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Request</title>
</head>
<body>
    <input type="hidden" value="userid" name="userid" /> 
    <div align="center">
        <h1>New/Edit Request</h1>
        <form:form action="saveRequest" method="post" modelAttribute="request">
        <table>
            <form:hidden path="prospectid"/>        
            <form:hidden path="requestid"/>
            <tr>
                <td>Request Date:</td>
                <td colspan="2"><form:input type="date" path="requestdate" /></td>
            </tr>
            <tr>
                <td>Brand:</td>
                <td colspan="2"><form:select path="brandname" items="${brandlist}" /></td>
            </tr>
            <tr>
                <td>Model:</td>
                <td colspan="2"><form:select path="modelname" items="${modellist}" /></td>
            </tr>
            <tr>
                <td>Remark:</td>
                <td colspan="2"><form:input path="remark" /></td>
            </tr>
            <tr>
                <td>Status:</td>
                <td><form:radiobutton path="status" value="Hot" />Hot
                <form:radiobutton path="status" value="Cold" />Cold
                <form:radiobutton path="status" value="Closed" />Closed</td>
            </tr>
            <tr>
            	<td></td>
            	<td>
	            <input type="button"  onclick="location.href='listRequest?userid=${userid}&prospectid=${request.prospectid}'" value="Back" >
                </td>            
                <td><input type="submit" value="Save"></td>
            </tr>
        </table>
        </form:form>
    </div>
</body>
</html>