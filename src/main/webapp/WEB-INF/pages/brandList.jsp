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
        <input type="hidden" value="companyid" name="companyid" /> 
        <div align="center">
            <h1>Brand List</h1>
            <h3><a href="addBrand?companyid=${companyid}">New Brand</a></h3>
            <table border="1">
            <tr>
                <th>Brand Name</th>
                <th>Action</th>
            </tr>
                <c:forEach var="Brand" items="${listBrand}" varStatus="status">
                <tr>
                    <td>${Brand.brandname}</td>
                    <td>
                        <a href="editBrand?brandid=${Brand.brandid}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="deleteBrand?companyid=${Brand.companyid}&brandid=${Brand.brandid}">Delete</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="listModel?companyid=${Brand.companyid}&brandid=${Brand.brandid}">Model</a>
                    </td>
                </tr>
                </c:forEach>             
            </table>
        </div>
    <jsp:include page="_footer.jsp" />                
    </body>
</html>