<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Prospect</title>
<script src="~/Scripts/jquery-1.8.2.min.js"></script>
<script src="~/Scripts/jquery-ui-1.8.24.min.js"></script>
</head>
<body>
    <div align="center">
        <h1>New/Edit Prospect</h1>
        <form:form action="saveProspect" method="post" modelAttribute="prospect">
        <table>
            <form:hidden path="userid"/>
            <form:hidden path="prospectid"/>
            <tr>
                <td>Prospect Name:</td>
                <td colspan="2"><form:input path="prospectname" /></td>
            </tr>
            <tr>
                <td>Source:</td>
                <td colspan="2"><form:select path="source" items="${sourcelist}"/></td>
            </tr>
            <tr>
                <td>Home Address:</td>
                <td colspan="2"><form:input path="haddress" /></td>
            </tr>
            <tr>
                <td>Zip Code:</td>
                <td colspan="2"><form:input path="hzipcode" /></td>
            </tr>
            <tr>
                <td>City:</td>
                <td colspan="2"><form:input path="hcity" /></td>
            </tr>
            <tr>
                <td>State:</td>
                <td colspan="2"><form:input path="hstate" /></td>
            </tr>
            <tr>
                <td>Country:</td>
                <td colspan="2"><form:input path="hcountry" /></td>
            </tr>
            <tr>
                <td>Mobile:</td>
                <td colspan="2"><form:input path="mobile" /></td>
            </tr>
            <tr>
                <td>Tel No:</td>
                <td colspan="2"><form:input path="htelno" /></td>
            </tr>
            <tr>
                <td>Office Address:</td>
                <td colspan="2"><form:input path="waddress" /></td>
            </tr>
            <tr>
                <td>Zip Code:</td>
                <td colspan="2"><form:input path="wzipcode" /></td>
            </tr>
            <tr>
                <td>City:</td>
                <td colspan="2"><form:input path="wcity" /></td>
            </tr>
            <tr>
                <td>State:</td>
                <td colspan="2"><form:input path="wstate" /></td>
            </tr>
            <tr>
                <td>Country:</td>
                <td colspan="2"><form:input path="wcountry" /></td>
            </tr>
            <tr>
                <td>Tel No:</td>
                <td colspan="2"><form:input path="wtelno" /></td>
            </tr>
            <tr>
                <td>Occupation:</td>
                <td colspan="2"><form:input path="occupation" /></td>
            </tr>
            <tr>
                <td>Age:</td>
                <td colspan="2"><form:input path="age" /></td>
            </tr>
            <tr>
                <td>Income:</td>
                <td colspan="2"><form:input path="income" /></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td colspan="2"><form:input path="email" /></td>
            </tr>
            <tr>
            	<td></td>
            	<td><input type="button"  onclick="location.href='listProspect?userid=${prospect.userid}'" value="Back" ></td>
                <td><input type="submit" value="Save"></td>
            </tr>
        </table>
        </form:form>
    </div>
</body>
</html>