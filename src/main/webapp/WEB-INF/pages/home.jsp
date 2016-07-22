<%@page session="false"%>
<html>
<head>
<title>${title}</title>
</head>
<body>
    <jsp:include page="_menu.jsp" />
 
 
    <h1>Welcome ${username} </h1>
    <h1>Your role is ${role} </h1>
</body>
</html>