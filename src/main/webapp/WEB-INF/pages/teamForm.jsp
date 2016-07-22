<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Team</title>
</head>
<body>
    <div align="center">
        <h1>New/Edit Team</h1>
        <form:form action="saveTeam" method="post" modelAttribute="team">
        <table>
            <form:hidden path="branchid"/>        
            <form:hidden path="teamid"/>
            <tr>
                <td>Team Name:</td>
                <td colspan="2"><form:input path="teamname" /></td>
            </tr>
            <tr>
                <td>Person In Charge:</td>
                <td colspan="2"><form:input path="pic" /></td>
            </tr>
            <tr>            
            	<td>
     	        </td>
            	<td>
	            <input type="button"  onclick="location.href='listTeam?branchid=${team.branchid}'" value="Back" >
                </td>
                <td><input type="submit" value="Save"></td>
            </tr>
        </table>
        </form:form>
    </div>
</body>
</html>