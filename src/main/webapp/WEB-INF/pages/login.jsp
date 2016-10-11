<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/css/plugins/icheck/all.css">
	<link rel="stylesheet" href="resources/css/style.css">
	<link rel="stylesheet" href="resources/css/themes.css">

	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/plugins/nicescroll/jquery.nicescroll.min.js"></script>
	<script src="resources/js/plugins/validation/jquery.validate.min.js"></script>
	<script src="resources/js/plugins/validation/additional-methods.min.js"></script>
	<script src="resources/js/plugins/icheck/jquery.icheck.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/eakroko.js"></script>		
</head>

<body class='login'>
<div class="wrapper">
	<div class="login-body">
		<h2>Sign In</h2>   
		<c:if test="${param.error == 'true'}">
		    <div style="color:red;margin:10px 0px;">
		           Login Failed!!!<br />
		           Reason :  ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		    </div>
		</c:if>
       
   		<form name='f' action="${pageContext.request.contextPath}/j_spring_security_check" method='POST'>
		<div class="form-group">
			<div class="email controls">
				<input type="text" name='username' placeholder="Email address" class='form-control' data-rule-required="true" data-rule-email="true">
			</div>
		</div>
		<div class="form-group">
			<div class="pw controls">
				<input type="password" name="password" placeholder="Password" class='form-control' data-rule-required="true">
			</div>
		</div>
		<div class="submit">
			<div class="remember">
				<input type="checkbox" name="remember" class='icheck-me' data-skin="square" data-color="blue" id="remember">
				<label for="remember">Remember me</label>
			</div>
			<input type="submit" value="Sign me in" class='btn btn-primary'>
		</div>
		</form>
		<div class="forget">
			<a href="#">
				<span>Forgot password?</span>
			</a>
		</div>
	</div>
</div>
</body>
</html>