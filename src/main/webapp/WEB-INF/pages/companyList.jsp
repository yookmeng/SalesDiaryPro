<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Company List</title>
    </head>
    <body>
        <div align="center">
            <h1>Company List</h1>
            <h3><a href="newCompany">New Company</a></h3>
            <table border="1">
            <tr>
                <th>Company Name</th>
                <th>Registration No</th>
                <th>Person In Charge</th>
                <th>Telephone</th>
                <th>Action</th>
            </tr>
                <c:forEach var="Company" items="${listCompany}" varStatus="status">
                <tr>
                    <td>${Company.companyname}</td>
                    <td>${Company.regno}</td>
                    <td>${Company.pic}</td>
                    <td>${Company.telephone}</td>
                    <td>
                        <a href="editCompany?companyid=${Company.companyid}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="deleteCompany?companyid=${Company.companyid}">Delete</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="listBranch?companyid=${Company.companyid}">Branch</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="listBrand?companyid=${Company.companyid}">Brand</a>
                    </td>                             
                </tr>
                </c:forEach>             
            </table>
        </div>
    <jsp:include page="_footer.jsp" />        
    </body>
</html>