<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User List</title>
    </head>
    <body>
        <div align="center">
            <h1>User List</h1>
            <h3><a href="newUser">New User</a></h3>
            <table border="1">
            <tr>
                <th>User Name</th>
                <th>Role</th>
                <th>Action</th>
            </tr>
                <c:forEach var="user" items="${listUser}" varStatus="status">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.role}</td>
                    <td>
                        <a href="editUser?username=${user.username}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="deleteUser?username=${user.username}">Delete</a>
                    </td>
                             
                </tr>
                </c:forEach>             
            </table>
        </div>
        
    <jsp:include page="_footer.jsp" />        
    </body>
</html>