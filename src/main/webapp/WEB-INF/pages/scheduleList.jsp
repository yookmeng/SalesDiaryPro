<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Schedule List</title>
    </head>
    <body>
        <input type="hidden" value="prospectid" name="prospectid" /> 
        <div align="center">
            <h1>Schedule List</h1>
            <h3><a href="addSchedule?prospectid=${prospectid}">New Schedule</a></h3>
            <table border="1">
            <tr>
                <th>Schedule Date</th>
                <th>Type</th>
                <th>Remark1</th>
                <th>Remark2</th>
                <th>Remark3</th>
                <th>Remark4</th>
                <th>Remark5</th>
                <th>Action</th>
            </tr>
                <c:forEach var="Schedule" items="${listSchedule}" varStatus="status">
                <tr>
                    <td>${Schedule.scheduledate}</td>
                    <td>${Schedule.type}</td>
                    <td>${Schedule.remark1}</td>
                    <td>${Schedule.remark2}</td>
                    <td>${Schedule.remark3}</td>
                    <td>${Schedule.remark4}</td>
                    <td>${Schedule.remark5}</td>
                    <td>
                        <a href="editSchedule?scheduleid=${Schedule.scheduleid}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="deleteSchedule?prospectid=${Schedule.prospectid}&scheduleid=${Schedule.scheduleid}">Delete</a>
                    </td>
                </tr>
                </c:forEach>             
            </table>
        </div>
    <jsp:include page="_footer.jsp" />                
    </body>
</html>