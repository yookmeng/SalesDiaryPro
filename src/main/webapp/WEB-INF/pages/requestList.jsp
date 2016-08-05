<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Request List</title>
    </head>
    <body>
        <input type="hidden" value="userid" name="userid" /> 
        <input type="hidden" value="prospectid" name="prospectid" /> 
        <div align="center">
            <h1>Request List</h1>
            <h2><a href="listProspect?userid=${userid}">Back To Prospect List</a></h2>
            <h3><a href="addRequest?prospectid=${prospectid}">New Request</a></h3>
            <table border="1">
            <tr>
                <th>Request Date</th>
                <th>Brand</th>
                <th>Model</th>
                <th>Remark</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
                <c:forEach var="Request" items="${listRequest}" varStatus="status">
                <tr>
                    <td>${Request.requestdate}</td>
                    <td>${Request.brandname}</td>
                    <td>${Request.modelname}</td>
                    <td>${Request.remark}</td>
                    <td>${Request.status}</td>
                    <td>
                        <a href="editRequest?requestid=${Request.requestid}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="deleteRequest?prospectid=${Request.prospectid}&requestid=${Request.requestid}">Delete</a>
                    </td>
                </tr>
                </c:forEach>             
            </table>
        </div>
    <jsp:include page="_footer.jsp" />                
    </body>
</html>