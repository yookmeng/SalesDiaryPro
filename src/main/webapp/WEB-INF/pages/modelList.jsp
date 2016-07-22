<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Model List</title>
    </head>
    <body>
        <input type="hidden" value="companyid" name="companyid" /> 
        <input type="hidden" value="brandid" name="brandid" /> 
        <div align="center">
            <h1>Model List</h1>
            <h3><a href="addModel?companyid=${companyid}&brandid=${brandid}">New Model</a></h3>
            <table border="1">
            <tr>
                <th>Model Name</th>
                <th>Action</th>
            </tr>
                <c:forEach var="Model" items="${listModel}" varStatus="status">
                <tr>
                    <td>${Model.modelname}</td>
                    <td>
                        <a href="editModel?modelid=${Model.modelid}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="deleteModel?companyid=${Model.companyid}&brandid=${Model.brandid}&modelid=${Model.modelid}">Delete</a>
                    </td>
                </tr>
                </c:forEach>             
            </table>
        </div>
    <jsp:include page="_footer.jsp" />                
    </body>
</html>