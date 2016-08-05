<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Activity</title>
</head>
<body>
    <input type="hidden" value="userid" name="userid" /> 
    <div align="center">
        <h1>New/Edit Activity</h1>
        <form:form action="saveActivity" method="post" modelAttribute="activity">
        <table>
            <form:hidden path="prospectid"/>        
            <form:hidden path="activityid"/>
            <tr>
                <td>Activity Date:</td>
                <td colspan="3"><form:input type="date" path="activitydate" /></td>
            </tr>
            <tr>
                <td>Brand:</td>
                <td colspan="3"><form:select path="brandname" items="${brandlist}" /></td>
            </tr>
            <tr>
                <td>Model:</td>
                <td colspan="3"><form:select path="modelname" items="${modellist}" /></td>
            </tr>
            <tr>
                <td>Type:</td>
                <td><form:checkbox path="demo" />Demo</td>
                <td><form:checkbox path="testdrive" />Test Drive</td>
                <td><form:checkbox path="quotation" />Quotation</td>
            </tr>
            <tr>
                <td>Remark1:</td>
                <td colspan="3"><form:input path="remark1" /></td>
            </tr>
            <tr>
                <td>Remark2:</td>
                <td colspan="3"><form:input path="remark2" /></td>
            </tr>
            <tr>
                <td>Remark3:</td>
                <td colspan="3"><form:input path="remark3" /></td>
            </tr>
            <tr>
            	<td></td>
            	<td>
	            <input type="button"  onclick="location.href='listActivity?userid=${userid}&prospectid=${activity.prospectid}'" value="Back" >
                </td>            
                <td><input type="submit" value="Save"></td>
            	<td></td>
            </tr>
        </table>
        </form:form>
    </div>
</body>
</html>