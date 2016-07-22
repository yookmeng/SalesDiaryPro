<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Member</title>
</head>
<body>
    <div align="center">
        <h1>New/Edit Member</h1>
        <form:form action="saveMember" method="post" modelAttribute="userProfile">
        <table>
            <form:hidden path="teamid"/>
            <form:hidden path="userid"/>
            <form:hidden path="role"/>
            <tr>
                <td>User Name:</td>
                <td><form:input path="username" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:input path="password" /></td>
            </tr>
            <tr>
                <td>Mobile:</td>
                <td><form:input path="mobile" /></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
            	<td>
	            <input type="button"  onclick="location.href='listMember?teamid=${userProfile.teamid}'" value="Back" >
                </td>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
        </form:form>
    </div>
</body>
</html>