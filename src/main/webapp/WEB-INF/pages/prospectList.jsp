<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prospect List</title>
    </head>
    <body>
        <input type="hidden" value="userid" name="userid" /> 
        <div align="center">
            <h1>Prospect List</h1>
            <h3><a href="addProspect?userid=${userid}">New Prospect</a></h3>
            <table border="1">
            <tr>
                <th>Prospect Name</th>
                <th>Mobile</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
                <c:forEach var="Prospect" items="${listProspect}" varStatus="status">
                <tr>
                    <td>${Prospect.prospectname}</td>
                    <td>${Prospect.mobile}</td>
                    <td>${Prospect.email}</td>
                    <td>
                        <a href="editProspect?prospectid=${Prospect.prospectid}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="deleteProspect?prospectid=${Prospect.prospectid}">Delete</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="listRequest?prospectid=${Prospect.prospectid}">Request</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="listSchedule?prospectid=${Prospect.prospectid}">Activity</a>
                    </td>                             
                </tr>
                </c:forEach>             
            </table>
        </div>
    <jsp:include page="_footer.jsp" />                        
    </body>
</html>