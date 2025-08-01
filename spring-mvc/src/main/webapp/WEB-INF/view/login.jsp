<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Login form</title>
</head>
<body>
    <h2>Please, authenticate yourself!</h2>
  	<hr>
  	
  	<form action="${pageContext.request.contextPath}/doLogin" method="POST">
      	
      	<p> Username: <input type="text" name="username"> </p>
      	<p> Password: <input type="password" name="password"> </p>
      	
       	<input type="submit" value="Log in"/>
       	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  		
  		<c:if test="${param.error != null }">
  			<p style="color:red; font-weigth:bold">
  				Invalid credentials, please enter 'student' as username and password :)
  			</p>
  		</c:if>
  	</form>
</body>
</html>