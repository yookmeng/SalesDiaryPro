<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Branch List</title>
    </head>
    <body>
     <input type="hidden" value="companyid" name="companyid" />
        <div align="center">
            <h1>Branch List</h1>
            <h3><a href="addBranch?companyid=${companyid}">New Branch</a></h3>
            <table border="1">
            <tr>
                <th>Branch Name</th>
                <th>Registration No</th>
                <th>Person In Charge</th>
                <th>Telephone</th>
                <th>Action</th>
            </tr>
                <c:forEach var="Branch" items="${listBranch}" varStatus="status">
                <tr>
                    <td>${Branch.branchname}</td>
                    <td>${Branch.regno}</td>
                    <td>${Branch.pic}</td>
                    <td>${Branch.telephone}</td>
                    <td>
                        <a href="editBranch?branchid=${Branch.branchid}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="deleteBranch?companyid=${Branch.companyid}&branchid=${Branch.branchid}">Delete</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="listTeam?branchid=${Branch.branchid}">Team</a>
                    </td>
                             
                </tr>
                </c:forEach>             
            </table>
        </div>
    <jsp:include page="_footer.jsp" />                
    </body>
</html>