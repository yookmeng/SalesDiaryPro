<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Team List</title>
    </head>
    <body>
        <input type="hidden" value="branchid" name="branchid" /> 
        <div align="center">
            <h1>Team List</h1>
            <h3><a href="addTeam?branchid=${branchid}">New Team</a></h3>
            <table border="1">
            <tr>
                <th>Team Name</th>
                <th>Person In Charge</th>
                <th>Action</th>
            </tr>
                <c:forEach var="Team" items="${listTeam}" varStatus="status">
                <tr>
                    <td>${Team.teamname}</td>
                    <td>${Team.pic}</td>
                    <td>
                        <a href="editTeam?teamid=${Team.teamid}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="deleteTeam?branchid=${Team.branchid}&teamid=${Team.teamid}">Delete</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="listMember?teamid=${Team.teamid}">Member</a>
                    </td>
                </tr>
                </c:forEach>             
            </table>
        </div>
    <jsp:include page="_footer.jsp" />                
    </body>
</html>