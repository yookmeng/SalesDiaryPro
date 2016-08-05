<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Model</title>
</head>
<body>
    <div align="center">
        <h1>New/Edit Model</h1>
        <form:form action="saveModel" method="post" modelAttribute="model">
        <table>
            <form:hidden path="brandid"/>        
            <form:hidden path="modelid"/>
            <tr>
                <td>Model Name:</td>
                <td colspan="2"><form:input path="modelname" /></td>
            </tr>
            <tr>            
            	<td>
     	        </td>
            	<td>
	            <input type="button"  onclick="location.href='listModel?brandid=${model.brandid}'" value="Back" >
                </td>
                <td><input type="submit" value="Save"></td>
            </tr>
        </table>
        </form:form>
    </div>
</body>
</html>