<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Brand</title>
</head>
<body>
    <div align="center">
        <h1>New/Edit Brand</h1>
        <form:form action="saveBrand" method="post" modelAttribute="brand">
        <table>
            <form:hidden path="companyid"/>        
            <form:hidden path="brandid"/>
            <tr>
                <td>Brand Name:</td>
                <td colspan="2"><form:input path="brandname" /></td>
            </tr>
            <tr>            
            	<td>
     	        </td>
            	<td>
	            <input type="button"  onclick="location.href='listBrand?companyid=${brand.companyid}'" value="Back" >
                </td>
                <td><input type="submit" value="Save"></td>
            </tr>
        </table>
        </form:form>
    </div>
</body>
</html>