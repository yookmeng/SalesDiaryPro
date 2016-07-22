<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member List</title>
    </head>
    <body>
        <input type="hidden" value="teamid" name="teamid" />     
        <div align="center">
            <h1>Team Member List</h1>
            <h3><a href="addMember?teamid=${teamid}">New Member</a></h3>
            <table border="1">
            <tr>
                <th>Member Name</th>
                <th>Mobile</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
                <c:forEach var="member" items="${userProfile}" varStatus="status">
                <tr>
                    <td>${member.username}</td>
                    <td>${member.mobile}</td>
                    <td>${member.email}</td>                    
                    <td>
                        <a href="editMember?username=${member.username}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="deleteMember?username=${member.username}">Delete</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="listProspect?userid=${member.userid}">Prospect</a>
                    </td>                             
                </tr>
                </c:forEach>             
            </table>
        </div>
    <jsp:include page="_footer.jsp" />                        
    </body>
</html>