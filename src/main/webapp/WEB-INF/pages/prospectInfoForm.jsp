<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Survey</title>
</head>
<body>
    <div align="center">
        <h1>New/Edit Survey</h1>
        <form:form action="saveSurvey" method="post" modelAttribute="survey">
        <table>
            <form:hidden path="prospectid"/>        
            <form:hidden path="surveyid"/>
            <tr>
                <td>Survey Date:</td>
                <td><form:input path="surveydate" /></td>
            </tr>
            <tr>
                <td>Car Owned:</td>
                <td>Car 1</td>
                <td>Car 2</td>
                <td>Car 3</td>
			</tr>
            <tr>
                <td>Brand:</td>
                <td><form:input path="brand1" /></td>
                <td><form:input path="brand2" /></td>
                <td><form:input path="brand3" /></td>
			</tr>
            <tr>
                <td>Model:</td>
                <td><form:input path="model1" /></td>
                <td><form:input path="model2" /></td>
                <td><form:input path="model3" /></td>
			</tr>
            <tr>
                <td>cc:</td>
                <td><form:input path="cc1" /></td>
                <td><form:input path="cc2" /></td>
                <td><form:input path="cc3" /></td>
			</tr>
            <tr>
                <td>Manufacturing Year:</td>
                <td><form:input path="mfgyear1" /></td>
                <td><form:input path="mfgyear2" /></td>
                <td><form:input path="mfgyear3" /></td>
			</tr>			
            <tr>
                <td>Reason:</td>
                <td><form:input path="reason" /></td>
            </tr>
            <tr>
                <td>Remark:</td>
                <td><form:input path="remark" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
        </form:form>
    </div>
</body>
</html>