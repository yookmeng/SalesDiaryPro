<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Schedule</title>
</head>
<body>
    <div align="center">
        <h1>New/Edit Schedule</h1>
        <form:form action="saveSchedule" method="post" modelAttribute="schedule">
        <table>
            <form:hidden path="prospectid"/>        
            <form:hidden path="scheduleid"/>
            <tr>
                <td>Schedule Date:</td>
                <td><form:input path="scheduledate" /></td>
            </tr>
            <tr>
                <td>Type:</td>
                <td><form:input path="type" /></td>
            </tr>
            <tr>
                <td>Remark1:</td>
                <td><form:input path="remark1" /></td>
            </tr>
            <tr>
                <td>Remark2:</td>
                <td><form:input path="remark2" /></td>
            </tr>
            <tr>
                <td>Remark3:</td>
                <td><form:input path="remark3" /></td>
            </tr>
            <tr>
                <td>Remark4:</td>
                <td><form:input path="remark4" /></td>
            </tr>
            <tr>
                <td>Remark5:</td>
                <td><form:input path="remark5" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
        </form:form>
    </div>
</body>
</html>