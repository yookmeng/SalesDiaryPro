<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Activity List</title>
    </head>
    <body>
        <input type="hidden" value="userid" name="userid" /> 
        <input type="hidden" value="prospectid" name="prospectid" /> 
        <div align="center">
            <h1>Activity List</h1>
            <h2><a href="listProspect?userid=${userid}">Back To Prospect List</a></h2>
            <h3><a href="addActivity?prospectid=${prospectid}">New Activity</a></h3>
            <table border="1">
            <tr>
                <th>Activity Date</th>
                <th>Brand</th>
                <th>Model</th>
                <th>Remark 1</th>
                <th>Remark 2</th>
                <th>Remark 3</th>
                <th>Action</th>
            </tr>
                <c:forEach var="Activity" items="${listActivity}" varStatus="status">
                <tr>
                    <td>${Activity.activitydate}</td>
                    <td>${Activity.brandname}</td>
                    <td>${Activity.modelname}</td>
                    <td>${Activity.remark1}</td>
                    <td>${Activity.remark2}</td>
                    <td>${Activity.remark3}</td>
                    <td>
                        <a href="editActivity?activityid=${Activity.activityid}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="deleteActivity?prospectid=${Activity.prospectid}&activityid=${Activity.activityid}">Delete</a>
                    </td>
                </tr>
                </c:forEach>             
            </table>
        </div>
    <jsp:include page="_footer.jsp" />                
    </body>
</html>