<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Company</title>
</head>
<body>
    <div align="center">
        <h1>New/Edit Company</h1>
        <form:form action="saveCompany" method="post" modelAttribute="company">
        <table>
            <form:hidden path="companyid"/>
            <tr>
                <td>Company Name:</td>
                <td><form:input path="companyname" /></td>
            </tr>
            <tr>
                <td>Registration No:</td>
                <td><form:input path="regno" /></td>
            </tr>
            <tr>
                <td>Person In Charge:</td>
                <td><form:select path="mdname" items="${mdlist}"/></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><form:input path="address" /></td>
            </tr>
            <tr>
                <td>Zip Code:</td>
                <td><form:input path="zipcode" /></td>
            </tr>
            <tr>
                <td>City:</td>
                <td><form:input path="city" /></td>
            </tr>
            <tr>
                <td>State:</td>
                <td><form:input path="state" /></td>
            </tr>
            <tr>
                <td>Country:</td>
                <td><form:input path="country" /></td>
            </tr>
            <tr>
                <td>Telephone:</td>
                <td><form:input path="telephone" /></td>
            </tr>
            <tr>
                <td>Fax:</td>
                <td><form:input path="fax" /></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td>Web Site:</td>
                <td><form:input path="website" /></td>
            </tr>
            <tr>
                <td>System Admin:</td>
                <td><form:select path="saname" items="${salist}"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
        </form:form>
    </div>
</body>
</html>